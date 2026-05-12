package com.cube.memorygames.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatDialog;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.memory.brain.training.games.R;

public class OnlineProgressDialog extends AppCompatDialog {
    @Bind({2131624260})
    TextView gameNumberView;
    private OnClickListener nextListener;
    private Runnable runnable = new Runnable() {
        public void run() {
            OnlineProgressDialog.this.onNextClick();
        }
    };
    @Bind({2131624093})
    TimerView timerView;
    @Bind({2131624258})
    TextView title;
    @Bind({2131624259})
    TextView yourScore;

    public OnlineProgressDialog(Context context, int score, String gameId, int gameNumber, int totalGamesCount, int delay, OnClickListener nextClickListener) {
        super(context, R.style.GdxTheme);
        getWindow().setBackgroundDrawableResource(R.color.black_50_opacity);
        requestWindowFeature(1);
        getWindow().clearFlags(2);
        setCancelable(false);
        setContentView((int) R.layout.dialog_online_progress);
        ButterKnife.bind((Dialog) this);
        this.nextListener = nextClickListener;
        Typeface typeFaceRobotoLight = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
        Typeface typeFaceRoboto = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        this.title.setTypeface(typeFaceRoboto);
        this.gameNumberView.setTypeface(typeFaceRobotoLight);
        this.yourScore.setTypeface(typeFaceRoboto);
        this.gameNumberView.setText(context.getString(R.string.game) + " " + gameNumber + "/" + totalGamesCount);
        this.timerView.showTimer(delay, false);
        this.timerView.postDelayed(this.runnable, (long) delay);
        this.yourScore.setText(context.getString(R.string.your_score) + ": " + score);
    }

    private void onNextClick() {
        this.timerView.removeCallbacks(this.runnable);
        if (this.nextListener != null) {
            this.nextListener.onClick(null);
            this.nextListener = null;
        }
    }
}
