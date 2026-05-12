package com.cube.memorygames.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.cube.memorygames.MainMenuActivity;
import com.cube.memorygames.MemoryApplicationModel;
import com.cube.memorygames.TopRanksActivity;
import com.cube.memorygames.activity.NoInternetActivity;
import com.cube.memorygames.activity.OnShowMoneyDialogListener;
import com.cube.memorygames.activity.OnlineHistoryActivity;
import com.cube.memorygames.activity.OnlineTutorialActivity;
import com.cube.memorygames.activity.PlayOnlineActivity;
import com.cube.memorygames.activity.ProfileActivity;
import com.cube.memorygames.api.local.model.LocalUser;
import com.memory.brain.training.games.R;
import com.squareup.picasso.Picasso;

public class OnlineAdapter extends Adapter<UserViewHolder> {
    public static final String PROFILE_PHOTO_NAME = "profile.png";
    private MainMenuActivity activity;
    private Typeface typeface;

    public class UserViewHolder extends ViewHolder {
        @Bind({2131624416})
        TextView buttonHistory;
        @Bind({2131624415})
        TextView buttonPlay;
        @Bind({2131624417})
        TextView buttonStatistics;
        @Bind({2131624146})
        ImageView profilePicture;
        @Bind({2131624414})
        TextView profileRating;
        @Bind({2131624145})
        TextView userName;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind((Object) this, itemView);
        }
    }

    public OnlineAdapter(MainMenuActivity activity) {
        this.activity = activity;
        this.typeface = Typeface.createFromAsset(activity.getAssets(), "Roboto-Light.ttf");
    }

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserViewHolder userViewHolder = new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_online_menu, parent, false));
        userViewHolder.userName.setTypeface(this.typeface);
        userViewHolder.buttonHistory.setTypeface(this.typeface);
        userViewHolder.buttonPlay.setTypeface(this.typeface);
        userViewHolder.buttonStatistics.setTypeface(this.typeface);
        userViewHolder.profileRating.setTypeface(this.typeface);
        return userViewHolder;
    }

    public void onBindViewHolder(UserViewHolder holder, int position) {
        final LocalUser localUser = MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser();
        holder.userName.setText(localUser.displayName);
        holder.buttonPlay.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MemoryApplicationModel.getInstance().logEvent(OnlineAdapter.this.activity, MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_PLAY_CLICKED);
                Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_PLAY_CLICKED));
                final MemoryApplicationModel application = MemoryApplicationModel.getInstance();
                if (TextUtils.isEmpty(localUser.displayName)) {
                    OnlineAdapter.this.activity.showLogin(false);
                } else if (!localUser.unlimitedOnline && localUser.money < 30) {
                    OnlineAdapter.this.activity.showMoneyDialog(true);
                } else if (!application.isNetworkAvailable()) {
                    OnlineAdapter.this.activity.startActivity(new Intent(OnlineAdapter.this.activity, NoInternetActivity.class));
                } else if (PreferenceManager.getDefaultSharedPreferences(OnlineAdapter.this.activity).getBoolean(OnlineTutorialActivity.PREF_ONLINE_SHOWED, false)) {
                    OnlineAdapter.this.activity.startActivity(new Intent(OnlineAdapter.this.activity, PlayOnlineActivity.class));
                    application.setOnShowMoneyDialogListener(new OnShowMoneyDialogListener() {
                        public void onShowMoneyDialog() {
                            OnlineAdapter.this.activity.showMoneyDialog(true);
                            application.setOnShowMoneyDialogListener(null);
                        }
                    });
                } else {
                    OnlineAdapter.this.activity.startActivity(new Intent(OnlineAdapter.this.activity, OnlineTutorialActivity.class));
                }
            }
        });
        holder.buttonHistory.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MemoryApplicationModel.getInstance().logEvent(OnlineAdapter.this.activity, MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_HISTORY_CLICKED);
                Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_HISTORY_CLICKED));
                if (TextUtils.isEmpty(localUser.displayName)) {
                    OnlineAdapter.this.activity.showLogin(false);
                    return;
                }
                OnlineAdapter.this.activity.startActivity(new Intent(OnlineAdapter.this.activity, OnlineHistoryActivity.class));
            }
        });
        holder.buttonStatistics.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MemoryApplicationModel.getInstance().logEvent(OnlineAdapter.this.activity, MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_RANKS_CLICKED);
                Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_RANKS_CLICKED));
                if (TextUtils.isEmpty(localUser.displayName)) {
                    OnlineAdapter.this.activity.showLogin(false);
                    return;
                }
                OnlineAdapter.this.activity.startActivity(TopRanksActivity.newIntent(OnlineAdapter.this.activity, 2));
            }
        });
        holder.profileRating.setText(String.valueOf(localUser.onlineRank));
        if (TextUtils.isEmpty(localUser.photoUrl)) {
            holder.profilePicture.setImageResource(R.drawable.ic_user_placeholder);
        } else {
            holder.profilePicture.setVisibility(0);
            int size = this.activity.getResources().getDimensionPixelSize(R.dimen.avatar_size);
            Picasso.with(this.activity).load(localUser.photoUrl).resize(size, size).noFade().placeholder((int) R.drawable.ic_user_placeholder).error((int) R.drawable.ic_user_placeholder).centerInside().into(holder.profilePicture);
        }
        holder.profilePicture.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                OnlineAdapter.this.activity.startActivity(new Intent(OnlineAdapter.this.activity, ProfileActivity.class));
            }
        });
    }

    public int getItemCount() {
        return 1;
    }
}
