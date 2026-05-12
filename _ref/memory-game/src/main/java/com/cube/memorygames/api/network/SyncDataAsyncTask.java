package com.cube.memorygames.api.network;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.activeandroid.ActiveAndroid;
import com.cube.memorygames.MemoryApplicationModel;
import com.cube.memorygames.api.local.LocalDataManager;
import com.cube.memorygames.api.local.model.LocalCoinsTransaction;
import com.cube.memorygames.api.local.model.LocalGameSession;
import com.cube.memorygames.api.local.model.LocalUser;
import com.cube.memorygames.api.network.body.BodyCoinsTransaction;
import com.cube.memorygames.api.network.body.BodyGameSession;
import com.cube.memorygames.api.network.body.BodyRatingTransaction;
import com.cube.memorygames.api.network.body.BodyRegistrationInfo;
import com.cube.memorygames.api.network.body.BodyUpdateProfile;
import com.cube.memorygames.api.network.model.RetrofitUser;
import com.google.firebase.iid.FirebaseInstanceId;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit2.Response;

public class SyncDataAsyncTask extends AsyncTask<Void, Void, Boolean> {
    private LocalDataManager localDataManager;
    private OnFinishListener onFinishListener;
    private String pushRegistrationId;

    public interface OnFinishListener {
        void onFinishUpload(boolean z);
    }

    public SyncDataAsyncTask(LocalDataManager localDataManager) {
        this.localDataManager = localDataManager;
        try {
            this.pushRegistrationId = FirebaseInstanceId.getInstance().getToken();
        } catch (IllegalStateException e) {
        }
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    protected Boolean doInBackground(Void... voids) {
        List<LocalCoinsTransaction> localCoinsTransactions = this.localDataManager.getNewLocalCoinsTransaction();
        List<LocalGameSession> localGameSessions = this.localDataManager.getNewGameSessions();
        LocalUser localUser = this.localDataManager.getLocalUser();
        try {
            APIService service = MemoryApplicationModel.getInstance().getService();
            BodyRegistrationInfo bodyRegistrationInfo = new BodyRegistrationInfo();
            bodyRegistrationInfo.displayName = localUser.displayName;
            bodyRegistrationInfo.authentication = localUser.authentication;
            bodyRegistrationInfo.facebookId = localUser.facebookId;
            bodyRegistrationInfo.invitedBy = localUser.invitedBy;
            try {
                bodyRegistrationInfo.pushRegistrationId = FirebaseInstanceId.getInstance().getToken();
            } catch (IllegalStateException e) {
                bodyRegistrationInfo.pushRegistrationId = this.pushRegistrationId;
            }
            Response<RetrofitUser> userResponse = service.register(bodyRegistrationInfo).execute();
            if (userResponse.isSuccessful()) {
                localUser.authentication = ((RetrofitUser) userResponse.body()).authentication;
                localUser.displayName = ((RetrofitUser) userResponse.body()).displayName;
                localUser.token = ((RetrofitUser) userResponse.body()).token;
                localUser.objectId = ((RetrofitUser) userResponse.body()).id;
                localUser.photoUrl = ((RetrofitUser) userResponse.body()).photoUrl;
                if (parseUnlockedContent(((RetrofitUser) userResponse.body()).unlockedContent).contains("game18")) {
                    localUser.deepLinkSent = true;
                    localUser.addUnlockedContent("game18");
                }
                localUser.save();
                MemoryApplicationModel.getInstance().saveToken(localUser.token);
            }
            service = MemoryApplicationModel.getInstance().getService();
            if (localUser.updated) {
                BodyUpdateProfile updateProfile = new BodyUpdateProfile();
                updateProfile.unlockedContent = localUser.unlockedContent;
                updateProfile.userId = localUser.objectId;
                updateProfile.displayName = localUser.displayName;
                updateProfile.photoUrl = localUser.photoUrl;
                if (service.updateProfile(updateProfile).execute().isSuccessful()) {
                    localUser.updated = false;
                    localUser.save();
                }
            }
            if (!(TextUtils.isEmpty(localUser.token) || TextUtils.isEmpty(localUser.objectId))) {
                List<BodyGameSession> bodyGameSessions = new ArrayList();
                for (LocalGameSession session : localGameSessions) {
                    BodyGameSession bodyGameSession = new BodyGameSession();
                    bodyGameSession.game = session.game;
                    bodyGameSession.startLevel = session.startLevel;
                    bodyGameSession.endLevel = session.endLevel;
                    bodyGameSession.moneyEarned = session.moneyEarned;
                    bodyGameSession.moneyPaid = session.moneyPaid;
                    bodyGameSession.replaysUsed = session.replaysUsed;
                    bodyGameSession.createdAt = session.createdAt.getTime();
                    bodyGameSession.updatedAt = session.updatedAt.getTime();
                    bodyGameSession.userId = localUser.objectId;
                    bodyGameSessions.add(bodyGameSession);
                }
                if (!bodyGameSessions.isEmpty() && service.addGameSessions(bodyGameSessions).execute().isSuccessful()) {
                    ActiveAndroid.beginTransaction();
                    for (LocalGameSession session2 : localGameSessions) {
                        session2.uploaded = true;
                        session2.save();
                    }
                    ActiveAndroid.setTransactionSuccessful();
                    ActiveAndroid.endTransaction();
                }
                List<BodyCoinsTransaction> bodyCoinsTransactions = new ArrayList();
                for (LocalCoinsTransaction transaction : localCoinsTransactions) {
                    BodyCoinsTransaction bodyCoinsTransaction = new BodyCoinsTransaction();
                    bodyCoinsTransaction.amount = transaction.amount;
                    bodyCoinsTransaction.item = transaction.item;
                    bodyCoinsTransaction.createdAt = transaction.createdAt.getTime();
                    bodyCoinsTransaction.updatedAt = transaction.updatedAt.getTime();
                    bodyCoinsTransaction.userId = localUser.objectId;
                    bodyCoinsTransactions.add(bodyCoinsTransaction);
                }
                if (!bodyCoinsTransactions.isEmpty() && service.addCoinsTransactions(bodyCoinsTransactions).execute().isSuccessful()) {
                    ActiveAndroid.beginTransaction();
                    for (LocalCoinsTransaction transaction2 : localCoinsTransactions) {
                        transaction2.uploaded = true;
                        transaction2.save();
                    }
                    ActiveAndroid.setTransactionSuccessful();
                    ActiveAndroid.endTransaction();
                }
                if (this.localDataManager.isNeedChallengeSynchronization()) {
                    BodyRatingTransaction bodyRatingTransaction = new BodyRatingTransaction();
                    bodyRatingTransaction.rating = this.localDataManager.getTotalChallengeRating(MemoryApplicationModel.getInstance());
                    bodyRatingTransaction.userId = localUser.objectId;
                    if (service.setChallengeRating(bodyRatingTransaction).execute().isSuccessful()) {
                        this.localDataManager.setNeedChallengeSynchronization(false);
                    }
                }
            }
            return Boolean.valueOf(true);
        } catch (IOException e2) {
            e2.printStackTrace();
            return Boolean.valueOf(false);
        } catch (Throwable th) {
            ActiveAndroid.endTransaction();
        }
    }

    private static List<String> parseUnlockedContent(String unlockedContent) {
        List<String> result = new ArrayList();
        if (!TextUtils.isEmpty(unlockedContent)) {
            Collections.addAll(result, unlockedContent.split(","));
        }
        return result;
    }

    protected void onPostExecute(Boolean result) {
        if (this.onFinishListener != null) {
            this.onFinishListener.onFinishUpload(result.booleanValue());
        }
    }
}
