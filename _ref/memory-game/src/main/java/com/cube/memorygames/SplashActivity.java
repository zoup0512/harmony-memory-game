package com.cube.memorygames;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.appodeal.ads.Appodeal;
import com.crashlytics.android.Crashlytics;
import com.cube.memorygames.api.local.model.LocalUser;
import com.facebook.AccessToken;
import io.branch.referral.Branch;
import io.branch.referral.Branch.BranchReferralInitListener;
import io.branch.referral.BranchError;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            String appKey = "5e3673841c102ff542de7d46cbfb8e6611327355b5fb9c19";
            Appodeal.disableLocationPermissionCheck();
            if (VERSION.SDK_INT > 23) {
                Appodeal.disableNetwork(this, "yandex");
            }
            Appodeal.initialize(this, appKey, 129);
        } catch (Exception e) {
            Crashlytics.logException(e);
        }
    }

    public void onStart() {
        super.onStart();
        Branch.getInstance().initSession(new BranchReferralInitListener() {
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                boolean showSecretGame = false;
                if (error == null) {
                    System.err.println("referringParams = " + referringParams);
                    String userId = referringParams.optString(AccessToken.USER_ID_KEY, null);
                    LocalUser localUser = MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser();
                    String localUserId = localUser.objectId;
                    if (!(TextUtils.isEmpty(userId) || userId.equals(localUserId))) {
                        showSecretGame = true;
                        localUser.addUnlockedContent("game18");
                        localUser.fromDeepLink = true;
                        localUser.invitedBy = userId;
                        localUser.save();
                        Games.resetGames();
                    }
                }
                SplashActivity.this.startActivity(MainMenuActivity.newIntent(SplashActivity.this, showSecretGame));
                SplashActivity.this.finish();
            }
        }, getIntent().getData(), (Activity) this);
    }

    public void onNewIntent(Intent intent) {
        setIntent(intent);
    }
}
