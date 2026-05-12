package com.cube.memorygames.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.cube.memorygames.MemoryApplicationModel;
import com.memory.brain.training.games.R;

public class ReplyOnlineDialog extends AppCompatDialog {
    private static final int DELAY = 5000;
    private static final int REPLAY_COINS = 30;
    @Bind({2131624141})
    TextView buttonNext;
    private boolean enableReplay;
    @Bind({2131624260})
    TextView gameNumberView;
    private OnClickListener nextClickListener;
    private OnClickListener replayClickListener;
    @Bind({2131624264})
    View replyButton;
    @Bind({2131624263})
    TextView replyButtonLevel;
    @Bind({2131624265})
    TextView replyButtonText;
    @Bind({2131624262})
    TextView replyButtonTitle;
    private Runnable runnable = new Runnable() {
        public void run() {
            ReplyOnlineDialog.this.onNextClick();
        }
    };
    @Bind({2131624261})
    TextView subtitle;
    @Bind({2131624093})
    TimerView timerView;
    @Bind({2131624032})
    TextView title;

    public ReplyOnlineDialog(Context context, int level, boolean enableReplay, int gameNumber, int totalGamesCount, OnClickListener nextClickListener, OnClickListener replayClickListener) {
        super(context, R.style.GdxTheme);
        getWindow().setBackgroundDrawableResource(17170445);
        requestWindowFeature(1);
        getWindow().clearFlags(2);
        setCancelable(false);
        setContentView((int) R.layout.dialog_online_replay);
        ButterKnife.bind((Dialog) this);
        this.nextClickListener = nextClickListener;
        this.replayClickListener = replayClickListener;
        if (MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser().money < 30) {
            enableReplay = false;
        }
        this.enableReplay = enableReplay;
        Typeface typeFaceRobotoLight = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
        Typeface typeFaceRoboto = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        this.title.setTypeface(typeFaceRoboto);
        this.subtitle.setTypeface(typeFaceRoboto);
        this.replyButtonTitle.setTypeface(typeFaceRobotoLight);
        this.replyButtonLevel.setTypeface(typeFaceRoboto);
        this.gameNumberView.setTypeface(typeFaceRobotoLight);
        this.buttonNext.setTypeface(typeFaceRoboto);
        this.subtitle.setText(context.getString(R.string.new_current_level_placeholder, new Object[]{Integer.valueOf(level)}));
        this.replyButtonLevel.setText(context.getString(R.string.new_current_level_placeholder, new Object[]{Integer.valueOf(level)}));
        this.gameNumberView.setText(context.getString(R.string.game) + " " + gameNumber + "/" + totalGamesCount);
        if (gameNumber == totalGamesCount) {
            this.buttonNext.setText(R.string.finish);
        } else {
            this.buttonNext.setText(R.string.next);
        }
        this.replyButtonText.setText(String.valueOf(30));
        this.replyButton.setEnabled(enableReplay);
        this.timerView.showTimer(DELAY, false);
        this.timerView.postDelayed(this.runnable, 5000);
    }

    @OnClick({2131624141})
    void onNextClick() {
        this.timerView.removeCallbacks(this.runnable);
        if (this.nextClickListener != null) {
            this.nextClickListener.onClick(null);
            this.nextClickListener = null;
        }
    }

    @OnClick({2131624264})
    void onReplayClick() {
        dismiss();
        MemoryApplicationModel.getInstance().logEvent(getClass().getSimpleName(), MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_REPLAY_CLICKED);
        Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_REPLAY_CLICKED));
        this.timerView.removeCallbacks(this.runnable);
        this.nextClickListener = null;
        if (this.enableReplay && this.replayClickListener != null) {
            this.replayClickListener.onClick(null);
        }
    }
}
