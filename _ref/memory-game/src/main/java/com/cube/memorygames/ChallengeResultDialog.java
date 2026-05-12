package com.cube.memorygames;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.memory.brain.training.games.R;

public class ChallengeResultDialog extends Dialog {
    @Bind({2131624228})
    TextView gameName;
    @Bind({2131623941})
    TextView home;
    private OnDialogClickListener homeClickListener;
    @Bind({2131624227})
    TextView next;
    private OnDialogClickListener nextClickListener;
    private OnDialogClickListener replayClickListener;
    @Bind({2131624229})
    TextView score;
    @Bind({2131624220})
    ImageView star1;
    @Bind({2131624221})
    ImageView star2;
    @Bind({2131624222})
    ImageView star3;
    private int stars;

    public interface OnDialogClickListener {
        void onDialogClick(Dialog dialog);
    }

    public ChallengeResultDialog(Activity activity, int gameName, int score, int stars, OnDialogClickListener homeClickListener, OnDialogClickListener nextClickListener, OnDialogClickListener replayClickListener) {
        super(activity);
        getWindow().setBackgroundDrawableResource(17170445);
        getWindow().requestFeature(1);
        getWindow().clearFlags(2);
        setCancelable(false);
        this.homeClickListener = homeClickListener;
        this.nextClickListener = nextClickListener;
        this.replayClickListener = replayClickListener;
        this.stars = stars;
        setContentView(R.layout.dialog_challenge_result);
        ButterKnife.bind((Dialog) this);
        Typeface typeface = Typeface.createFromAsset(activity.getAssets(), "Roboto-Light.ttf");
        this.gameName.setTypeface(typeface);
        this.score.setTypeface(typeface);
        this.home.setTypeface(typeface);
        this.next.setTypeface(typeface);
        this.gameName.setText(gameName);
        if (stars < 3) {
            this.star3.setColorFilter(ContextCompat.getColor(activity, R.color.background));
        }
        if (stars < 2) {
            this.star2.setColorFilter(ContextCompat.getColor(activity, R.color.background));
        }
        if (stars < 1) {
            this.star1.setColorFilter(ContextCompat.getColor(activity, R.color.background));
            this.next.setText(R.string.replay);
        }
        this.score.setText("" + score);
    }

    @OnClick({2131624227})
    void nextClick() {
        if (this.stars <= 0) {
            if (this.replayClickListener != null) {
                this.replayClickListener.onDialogClick(this);
            }
        } else if (this.nextClickListener != null) {
            this.nextClickListener.onDialogClick(this);
        }
    }

    @OnClick({2131623941})
    void homeClick() {
        if (this.homeClickListener != null) {
            this.homeClickListener.onDialogClick(this);
        }
    }

    public void onBackPressed() {
        if (this.homeClickListener != null) {
            this.homeClickListener.onDialogClick(this);
        }
    }
}
