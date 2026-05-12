package com.cube.memorygames.api.network;

import com.cube.memorygames.api.network.body.BodyCoinsTransaction;
import com.cube.memorygames.api.network.body.BodyGameSession;
import com.cube.memorygames.api.network.body.BodyLoginInfo;
import com.cube.memorygames.api.network.body.BodyNoResults;
import com.cube.memorygames.api.network.body.BodyOnlineQueueIdUserId;
import com.cube.memorygames.api.network.body.BodyOnlineResults;
import com.cube.memorygames.api.network.body.BodyRatingTransaction;
import com.cube.memorygames.api.network.body.BodyRegistrationInfo;
import com.cube.memorygames.api.network.body.BodyRoundResults;
import com.cube.memorygames.api.network.body.BodyUpdateProfile;
import com.cube.memorygames.api.network.body.BodyUserId;
import com.cube.memorygames.api.network.model.RetrofitOnlineMatch;
import com.cube.memorygames.api.network.model.RetrofitTopResult;
import com.cube.memorygames.api.network.model.RetrofitUser;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    @POST("addCoinsTransactions")
    Call<RetrofitUser> addCoinsTransactions(@Body List<BodyCoinsTransaction> list);

    @POST("addGameSessions")
    Call<RetrofitUser> addGameSessions(@Body List<BodyGameSession> list);

    @POST("addToQueue")
    Call<ResponseBody> addToQueue(@Body BodyUserId bodyUserId);

    @POST("cancelFoundGame")
    Call<ResponseBody> cancelFoundGame(@Body BodyOnlineQueueIdUserId bodyOnlineQueueIdUserId);

    @POST("checkAgainForMatch")
    Call<ResponseBody> checkAgainForMatch(@Body BodyUserId bodyUserId);

    @POST("getOnlineHistory")
    Call<List<RetrofitOnlineMatch>> getOnlineHistory(@Body BodyUserId bodyUserId);

    @POST("login")
    Call<RetrofitUser> login(@Body BodyLoginInfo bodyLoginInfo);

    @POST("noResultsReceived")
    Call<ResponseBody> noResultsReceived(@Body BodyNoResults bodyNoResults);

    @POST("register")
    Call<RetrofitUser> register(@Body BodyRegistrationInfo bodyRegistrationInfo);

    @POST("removeFromQueue")
    Call<ResponseBody> removeFromQueue(@Body BodyUserId bodyUserId);

    @POST("sendRoundResults")
    Call<ResponseBody> sendRoundResults(@Body BodyRoundResults bodyRoundResults);

    @POST("setChallengeRating")
    Call<RetrofitUser> setChallengeRating(@Body BodyRatingTransaction bodyRatingTransaction);

    @POST("startOnlineGame")
    Call<ResponseBody> startGame(@Body BodyOnlineQueueIdUserId bodyOnlineQueueIdUserId);

    @POST("submitMatchResults")
    Call<ResponseBody> submitMatchResults(@Body BodyOnlineResults bodyOnlineResults);

    @POST("top")
    Call<List<RetrofitTopResult>> top(@Body BodyUserId bodyUserId);

    @POST("topOnline")
    Call<List<RetrofitTopResult>> topOnline(@Body BodyUserId bodyUserId);

    @POST("updateProfile")
    Call<RetrofitUser> updateProfile(@Body BodyUpdateProfile bodyUpdateProfile);
}
