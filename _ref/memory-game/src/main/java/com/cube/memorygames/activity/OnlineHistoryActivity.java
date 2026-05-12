package com.cube.memorygames.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cube.memorygames.MemoryApplicationModel;
import com.cube.memorygames.api.network.body.BodyUserId;
import com.cube.memorygames.api.network.model.RetrofitOnlineMatch;
import com.cube.memorygames.utils.DateUtils;
import com.memory.brain.training.games.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineHistoryActivity extends AppCompatActivity {
    public static final String DATE_FORMAT = "EEEE, dd.MM.yy";
    private MemoryApplicationModel application;
    private OnlineHistoryAdapter historyAdapter;
    @Bind({2131624127})
    View progressContainer;
    @Bind({2131624126})
    RecyclerView recyclerHistory;

    public class OnlineHistoryAdapter extends Adapter<OnlineHistoryViewHolder> {
        private List<RetrofitOnlineMatch> onlineMatches;

        public class OnlineHistoryViewHolder extends ViewHolder {
            @Bind({2131624406})
            ImageView avatar1;
            @Bind({2131624410})
            ImageView avatar2;
            @Bind({2131624407})
            TextView name1;
            @Bind({2131624411})
            TextView name2;
            @Bind({2131624408})
            TextView score1;
            @Bind({2131624412})
            TextView score2;
            @Bind({2131624405})
            TextView timeHeader;
            @Bind({2131624409})
            View winnerBadge1;
            @Bind({2131624413})
            View winnerBadge2;

            public OnlineHistoryViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind((Object) this, itemView);
            }

            public void setData(RetrofitOnlineMatch match, int position) {
                String myName;
                int myScore;
                String myPhoto;
                String opponentName;
                int opponentScore;
                String opponentPhoto;
                boolean isMeWinner = false;
                if (OnlineHistoryActivity.this.application.getLocalDataManager().getLocalUser().objectId.equals(match.getPlayerId1())) {
                    myName = match.getPlayerName1();
                    myScore = match.getTotalScorePlayer1();
                    myPhoto = match.getPlayerPhotoUrl1();
                    opponentName = match.getPlayerName2();
                    opponentScore = match.getTotalScorePlayer2();
                    opponentPhoto = match.getPlayerPhotoUrl2();
                } else {
                    myName = match.getPlayerName2();
                    myScore = match.getTotalScorePlayer2();
                    myPhoto = match.getPlayerPhotoUrl2();
                    opponentName = match.getPlayerName1();
                    opponentScore = match.getTotalScorePlayer1();
                    opponentPhoto = match.getPlayerPhotoUrl1();
                }
                if (OnlineHistoryActivity.this.application.getLocalDataManager().getLocalUser().objectId.equals(match.getWinnerUser())) {
                    isMeWinner = true;
                }
                Picasso.with(this.itemView.getContext()).load(myPhoto).into(this.avatar1);
                Picasso.with(this.itemView.getContext()).load(opponentPhoto).into(this.avatar2);
                this.name1.setText(myName);
                this.name2.setText(opponentName);
                this.score1.setText(myScore + "");
                this.score2.setText(opponentScore + "");
                this.avatar1.setBackgroundResource(R.drawable.drawable_circle_grey);
                this.avatar2.setBackgroundResource(R.drawable.drawable_circle_grey);
                this.winnerBadge1.setVisibility(8);
                this.winnerBadge2.setVisibility(8);
                if (!TextUtils.isEmpty(match.getWinnerUser())) {
                    if (isMeWinner) {
                        this.avatar1.setBackgroundResource(R.drawable.drawable_circle_green);
                        this.winnerBadge1.setVisibility(0);
                    } else {
                        this.avatar2.setBackgroundResource(R.drawable.drawable_circle_green);
                        this.winnerBadge2.setVisibility(0);
                    }
                }
                if (position == 0 || shouldDisplayHeader(match, position)) {
                    this.timeHeader.setVisibility(0);
                    this.timeHeader.setText(DateUtils.getTodayYesterdayOrFormattedDateString(OnlineHistoryActivity.this.getApplicationContext(), match.getCreated().getTime(), OnlineHistoryActivity.DATE_FORMAT));
                    return;
                }
                this.timeHeader.setVisibility(8);
            }

            private boolean shouldDisplayHeader(RetrofitOnlineMatch match, int position) {
                return Math.abs(TimeUnit.DAYS.convert(((RetrofitOnlineMatch) OnlineHistoryAdapter.this.onlineMatches.get(position + -1)).getCreated().getTime(), TimeUnit.MILLISECONDS) - TimeUnit.DAYS.convert(match.getCreated().getTime(), TimeUnit.MILLISECONDS)) > 0;
            }
        }

        public OnlineHistoryAdapter(List<RetrofitOnlineMatch> onlineMatches) {
            if (onlineMatches == null) {
                onlineMatches = new ArrayList();
            }
            this.onlineMatches = onlineMatches;
        }

        public OnlineHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new OnlineHistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_online_history, parent, false));
        }

        public void onBindViewHolder(OnlineHistoryViewHolder holder, int position) {
            holder.setData((RetrofitOnlineMatch) this.onlineMatches.get(position), position);
        }

        public int getItemCount() {
            return this.onlineMatches.size();
        }

        public void setOnlineMatches(List<RetrofitOnlineMatch> onlineMatches) {
            this.onlineMatches = onlineMatches;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_online_history);
        ButterKnife.bind((Activity) this);
        this.application = MemoryApplicationModel.getInstance();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, 1, false);
        this.recyclerHistory.setItemAnimator(new DefaultItemAnimator());
        this.recyclerHistory.setLayoutManager(layoutManager);
        this.recyclerHistory.setAdapter(new OnlineHistoryAdapter(Collections.EMPTY_LIST));
        this.application.getService().getOnlineHistory(new BodyUserId(this.application.getLocalDataManager().getLocalUser().objectId)).enqueue(new Callback<List<RetrofitOnlineMatch>>() {
            public void onResponse(Call<List<RetrofitOnlineMatch>> call, Response<List<RetrofitOnlineMatch>> response) {
                OnlineHistoryActivity.this.recyclerHistory.setVisibility(0);
                OnlineHistoryActivity.this.progressContainer.setVisibility(8);
                if (OnlineHistoryActivity.this.historyAdapter == null) {
                    OnlineHistoryActivity.this.historyAdapter = new OnlineHistoryAdapter((List) response.body());
                    OnlineHistoryActivity.this.recyclerHistory.setAdapter(OnlineHistoryActivity.this.historyAdapter);
                    return;
                }
                OnlineHistoryActivity.this.historyAdapter.setOnlineMatches((List) response.body());
                OnlineHistoryActivity.this.historyAdapter.notifyDataSetChanged();
            }

            public void onFailure(Call<List<RetrofitOnlineMatch>> call, Throwable t) {
                OnlineHistoryActivity.this.progressContainer.setVisibility(8);
                Log.e("ONLINE_HISTORY", t.getMessage());
            }
        });
    }

    @OnClick({2131624115})
    public void backClick() {
        finish();
    }
}
