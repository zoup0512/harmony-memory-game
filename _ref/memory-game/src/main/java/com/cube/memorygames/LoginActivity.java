package com.cube.memorygames;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cube.memorygames.api.local.LocalDataManager;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.GraphJSONObjectCallback;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.memory.brain.training.games.R;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private static final String EXTRA_ACTIVITY_TYPE = "login_activity_type";
    private MemoryApplicationModel application;
    private CallbackManager callbackManager;
    @Bind({2131624101})
    TextView enterNickname;
    @Bind({2131624109})
    View facebookLogin;
    @Bind({2131624110})
    TextView login;
    @Bind({2131624102})
    EditText nickname;
    @Bind({2131624108})
    TextView play;
    @Bind({2131624111})
    TextView plus100;
    @Bind({2131624105})
    TextView plus50;
    private ProgressDialog progressDialog;
    @Bind({2131624107})
    TextView skip;

    public static Intent newIntent(Context context, boolean type) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(EXTRA_ACTIVITY_TYPE, type);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra(EXTRA_ACTIVITY_TYPE, true)) {
            setContentView((int) R.layout.activity_login);
        } else {
            setContentView((int) R.layout.dialog_lookranks);
        }
        ButterKnife.bind((Activity) this);
        Typeface typeFaceRoboto = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
        Typeface typeFaceRobotoRegular = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        this.login.setTypeface(typeFaceRobotoRegular);
        this.plus100.setTypeface(typeFaceRobotoRegular);
        this.plus50.setTypeface(typeFaceRobotoRegular);
        this.enterNickname.setTypeface(typeFaceRoboto);
        this.nickname.setTypeface(typeFaceRoboto);
        this.skip.setTypeface(typeFaceRobotoRegular);
        this.play.setTypeface(typeFaceRobotoRegular);
        this.application = MemoryApplicationModel.getInstance();
        this.callbackManager = Factory.create();
        LoginManager.getInstance().registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() {
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphJSONObjectCallback() {
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        String name = "";
                        String surname = "";
                        try {
                            if (object.has("first_name")) {
                                name = object.getString("first_name");
                            }
                            if (object.has("last_name")) {
                                surname = object.getString("last_name");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (!(TextUtils.isEmpty(name) || TextUtils.isEmpty(surname))) {
                            MemoryApplicationModel.getInstance().getLocalDataManager().setFbId(loginResult.getAccessToken().getUserId());
                            MemoryApplicationModel.getInstance().getLocalDataManager().setDisplayName(name + " " + surname);
                            MemoryApplicationModel.getInstance().getLocalDataManager().addCoinsTransaction(LocalDataManager.TYPE_FACEBOOK, 100);
                            LoginActivity.this.application.logEvent(LoginActivity.this, MemoryApplicationModel.ANALYTICS_CATEGORY_GENERAL, MemoryApplicationModel.ANALYTICS_EVENT_FACEBOOK_LOGIN);
                            LoginActivity.this.setResult(-1);
                            LoginActivity.this.finish();
                        }
                        if (LoginActivity.this.progressDialog != null) {
                            LoginActivity.this.progressDialog.cancel();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString(GraphRequest.FIELDS_PARAM, "first_name, last_name");
                request.setParameters(parameters);
                request.executeAsync();
            }

            public void onCancel() {
                if (LoginActivity.this.progressDialog != null) {
                    LoginActivity.this.progressDialog.cancel();
                }
            }

            public void onError(FacebookException exception) {
                if (LoginActivity.this.progressDialog != null) {
                    LoginActivity.this.progressDialog.cancel();
                }
            }
        });
        init();
    }

    private void init() {
        this.nickname.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    LoginActivity.this.play.setEnabled(true);
                    LoginActivity.this.play.setTextColor(-1);
                    return;
                }
                LoginActivity.this.play.setEnabled(false);
                LoginActivity.this.play.setTextColor(ContextCompat.getColor(LoginActivity.this, R.color.current_top));
            }
        });
    }

    @OnClick({2131624107})
    protected void skipClicked() {
        this.application.logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_GENERAL, MemoryApplicationModel.ANALYTICS_EVENT_NICK_SKIPPED);
        finish();
    }

    @OnClick({2131624109})
    protected void facebookClicked() {
        this.application.logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_GENERAL, MemoryApplicationModel.ANALYTICS_EVENT_FACEBOOK_REGISTER);
        LoginManager.getInstance().logInWithReadPermissions((Activity) this, Arrays.asList(new String[]{"public_profile", "user_friends"}));
        this.progressDialog = ProgressDialog.show(this, null, "Loading...", false, false);
    }

    @OnClick({2131624108})
    protected void playClicked() {
        this.application.logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_GENERAL, MemoryApplicationModel.ANALYTICS_EVENT_NICK_ENTERED);
        MemoryApplicationModel.getInstance().getLocalDataManager().setDisplayName(this.nickname.getText().toString());
        MemoryApplicationModel.getInstance().getLocalDataManager().addCoinsTransaction(LocalDataManager.TYPE_USERNAME, 50);
        setResult(-1);
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
