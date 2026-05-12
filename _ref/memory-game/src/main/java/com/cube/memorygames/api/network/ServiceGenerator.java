package com.cube.memorygames.api.network;

import android.text.TextUtils;
import com.cube.memorygames.MemoryApplicationModel;
import com.cube.memorygames.games.Game1MemoryGridActivity;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static final String API_URL = "http://1.braintraining.me:8080/";
    private static final String API_URL2 = "http://2.braintraining.me:8080/";
    private static final String TEXT_URL = "http://78.46.69.102:8080/";
    private static boolean alreadyChanged = false;
    private static String apiBaseUrl = (Math.random() < 0.5d ? API_URL : API_URL2);

    private static final class RetryInterceptor implements Interceptor {
        private RetryInterceptor() {
        }

        public Response intercept(Chain chain) throws IOException {
            IOException exception;
            Request request = chain.request();
            Response responseFirst = null;
            try {
                responseFirst = chain.proceed(request);
            } catch (ConnectException e) {
                exception = e;
                if (ServiceGenerator.alreadyChanged) {
                    throw exception;
                }
                if (responseFirst == null) {
                }
                if (!ServiceGenerator.alreadyChanged) {
                    ServiceGenerator.apiBaseUrl = ServiceGenerator.apiBaseUrl.equals(ServiceGenerator.API_URL) ? ServiceGenerator.API_URL : ServiceGenerator.API_URL2;
                    ServiceGenerator.alreadyChanged = true;
                }
                MemoryApplicationModel.getInstance().createService();
                return chain.proceed(request.newBuilder().url(ServiceGenerator.apiBaseUrl).method(request.method(), request.body()).build());
            } catch (EOFException e2) {
                exception = e2;
                if (ServiceGenerator.alreadyChanged) {
                    throw exception;
                }
                if (responseFirst == null) {
                }
                if (ServiceGenerator.alreadyChanged) {
                    if (ServiceGenerator.apiBaseUrl.equals(ServiceGenerator.API_URL)) {
                    }
                    ServiceGenerator.apiBaseUrl = ServiceGenerator.apiBaseUrl.equals(ServiceGenerator.API_URL) ? ServiceGenerator.API_URL : ServiceGenerator.API_URL2;
                    ServiceGenerator.alreadyChanged = true;
                }
                MemoryApplicationModel.getInstance().createService();
                return chain.proceed(request.newBuilder().url(ServiceGenerator.apiBaseUrl).method(request.method(), request.body()).build());
            } catch (UnknownHostException e3) {
                exception = e3;
                if (ServiceGenerator.alreadyChanged) {
                    throw exception;
                }
                if (responseFirst == null) {
                }
                if (ServiceGenerator.alreadyChanged) {
                    if (ServiceGenerator.apiBaseUrl.equals(ServiceGenerator.API_URL)) {
                    }
                    ServiceGenerator.apiBaseUrl = ServiceGenerator.apiBaseUrl.equals(ServiceGenerator.API_URL) ? ServiceGenerator.API_URL : ServiceGenerator.API_URL2;
                    ServiceGenerator.alreadyChanged = true;
                }
                MemoryApplicationModel.getInstance().createService();
                return chain.proceed(request.newBuilder().url(ServiceGenerator.apiBaseUrl).method(request.method(), request.body()).build());
            }
            if (responseFirst == null && responseFirst.code() >= 200 && responseFirst.code() < Game1MemoryGridActivity.START_ANIMATION_DURATION) {
                return responseFirst;
            }
            if (ServiceGenerator.alreadyChanged) {
                if (ServiceGenerator.apiBaseUrl.equals(ServiceGenerator.API_URL)) {
                }
                ServiceGenerator.apiBaseUrl = ServiceGenerator.apiBaseUrl.equals(ServiceGenerator.API_URL) ? ServiceGenerator.API_URL : ServiceGenerator.API_URL2;
                ServiceGenerator.alreadyChanged = true;
            }
            MemoryApplicationModel.getInstance().createService();
            return chain.proceed(request.newBuilder().url(ServiceGenerator.apiBaseUrl).method(request.method(), request.body()).build());
        }
    }

    private static final class TokenInterceptor implements Interceptor {
        private String token;

        TokenInterceptor(String token) {
            this.token = token;
        }

        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Builder requestBuilder = original.newBuilder().header("Accept", "application/json").method(original.method(), original.body());
            if (!TextUtils.isEmpty(this.token)) {
                requestBuilder.header("X-Auth-Token", this.token);
            }
            return chain.proceed(requestBuilder.build());
        }
    }

    public static <S> S createService(Class<S> serviceClass, String token) {
        return new Retrofit.Builder().baseUrl(apiBaseUrl).addConverterFactory(getGsonConverterFactory()).client(getOkHttpClient(token)).build().create(serviceClass);
    }

    private static GsonConverterFactory getGsonConverterFactory() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        return GsonConverterFactory.create(builder.create());
    }

    private static OkHttpClient getOkHttpClient(String token) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(Level.NONE);
        return httpClient.addInterceptor(new RetryInterceptor()).addInterceptor(new TokenInterceptor(token)).addInterceptor(loggingInterceptor).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build();
    }
}
