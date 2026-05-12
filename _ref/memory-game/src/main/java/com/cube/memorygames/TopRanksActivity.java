package com.cube.memorygames;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cube.memorygames.api.network.SyncDataAsyncTask;
import com.cube.memorygames.api.network.SyncDataAsyncTask.OnFinishListener;
import com.cube.memorygames.api.network.body.BodyUserId;
import com.cube.memorygames.api.network.model.RetrofitTopResult;
import com.cube.memorygames.ui.TopRanksAdapter;
import com.facebook.GraphResponse;
import com.memory.brain.training.games.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRanksActivity extends AppCompatActivity {
    public static final String EXTRA_TYPE = "type";
    public static final int TYPE_ONLINE_TOP = 2;
    public static final int TYPE_TOP = 1;
    private MemoryApplicationModel application;
    @Bind({2131624127})
    View progressContainer;
    @Bind({2131624149})
    RecyclerView recyclerTopRanks;
    @Bind({2131624032})
    TextView title;
    private int type;

    public static Intent newIntent(Context context, int type) {
        Intent intent = new Intent(context, TopRanksActivity.class);
        intent.putExtra("type", type);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_top_ranks);
        ButterKnife.bind((Activity) this);
        this.application = MemoryApplicationModel.getInstance();
        this.type = getIntent().getIntExtra("type", 1);
        this.title.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf"), 1);
        if (this.type == 2) {
            this.title.setText(R.string.online_top_title);
        } else if (this.type == 1) {
            this.title.setText(R.string.top_ranks_text);
        }
        SyncDataAsyncTask syncDataAsyncTask = new SyncDataAsyncTask(this.application.getLocalDataManager());
        syncDataAsyncTask.setOnFinishListener(new OnFinishListener() {
            public void onFinishUpload(boolean result) {
                if (result) {
                    BodyUserId bodyGetTop = new BodyUserId(TopRanksActivity.this.application.getLocalDataManager().getLocalUser().objectId);
                    Call<List<RetrofitTopResult>> call = null;
                    if (TopRanksActivity.this.type == 2) {
                        call = MemoryApplicationModel.getInstance().getService().topOnline(bodyGetTop);
                    } else if (TopRanksActivity.this.type == 1) {
                        call = MemoryApplicationModel.getInstance().getService().top(bodyGetTop);
                    }
                    if (call != null) {
                        call.enqueue(new Callback<List<RetrofitTopResult>>() {
                            public void onResponse(Call<List<RetrofitTopResult>> call, Response<List<RetrofitTopResult>> response) {
                                if (!response.isSuccessful() || response.body() == null || ((List) response.body()).isEmpty()) {
                                    TopRanksActivity.this.showError(null);
                                    return;
                                }
                                Log.e(getClass().getSimpleName(), GraphResponse.SUCCESS_KEY);
                                TopRanksActivity.this.progressContainer.setVisibility(8);
                                TopRanksActivity.this.recyclerTopRanks.setVisibility(0);
                                TopRanksActivity.this.recyclerTopRanks.setLayoutManager(new LinearLayoutManager(TopRanksActivity.this));
                                TopRanksActivity.this.recyclerTopRanks.setAdapter(new TopRanksAdapter((List) response.body(), TopRanksActivity.this.type, TopRanksActivity.this));
                            }

                            public void onFailure(Call<List<RetrofitTopResult>> call, Throwable t) {
                                TopRanksActivity.this.showError(t);
                            }
                        });
                        return;
                    }
                    return;
                }
                TopRanksActivity.this.showError(null);
            }
        });
        syncDataAsyncTask.execute(new Void[0]);
    }

    private void showError(Throwable t) {
        Toast.makeText(this, R.string.internet_warning, 1).show();
        finish();
        if (t != null) {
            Log.e(getClass().getSimpleName(), "error ", t);
        }
    }

    @OnClick({2131624115})
    public void backClick() {
        finish();
    }
}
