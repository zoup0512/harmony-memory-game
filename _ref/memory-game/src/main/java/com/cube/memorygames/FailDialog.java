package com.cube.memorygames;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cube.memorygames.ui.TimerView;
import com.memory.brain.training.games.R;

public class FailDialog extends Dialog {
    private static final int DELAY = 5000;
    private OnDialogClickListener buyClickListener;
    @Bind({2131624234})
    View buyImage;
    @Bind({2131624235})
    TextView buyText;
    private OnDialogClickListener exitCLickListener;
    Handler handler;
    private OnDialogClickListener restartClickListener;
    @Bind({2131624236})
    View restartContainer;
    private Runnable runnable = new Runnable() {
        public void run() {
            FailDialog.this.onBackPressed();
        }
    };
    @Bind({2131624093})
    TimerView timerView;

    public interface OnDialogClickListener {
        void onDialogClick(Dialog dialog);
    }

    public FailDialog(Activity activity, int buyStarsClickedCountCount, OnDialogClickListener buyClickListener, OnDialogClickListener restartClickListener, OnDialogClickListener exitCLickListener) {
        super(activity);
        this.buyClickListener = buyClickListener;
        this.restartClickListener = restartClickListener;
        this.exitCLickListener = exitCLickListener;
        getWindow().setBackgroundDrawableResource(17170445);
        getWindow().requestFeature(1);
        getWindow().clearFlags(2);
        setCancelable(false);
        setContentView(R.layout.dialog_fail);
        ButterKnife.bind((Dialog) this);
        this.timerView.showTimer(DELAY, true);
        this.handler = new Handler();
        this.handler.postDelayed(this.runnable, 5000);
        this.buyText.setText("30 " + getContext().getString(R.string.coins));
        if (buyStarsClickedCountCount >= 7) {
            this.buyImage.setEnabled(false);
        } else {
            this.buyImage.setEnabled(true);
        }
        setOnDismissListener(new OnDismissListener() {
            public void onDismiss(DialogInterface dialog) {
                FailDialog.this.handler.removeCallbacks(FailDialog.this.runnable);
            }
        });
    }

    public void dismiss() {
        this.handler.removeCallbacks(this.runnable);
        try {
            super.dismiss();
        } catch (Exception e) {
        }
    }

    public void onBackPressed() {
        if (this.exitCLickListener != null) {
            this.exitCLickListener.onDialogClick(this);
        }
    }

    @OnClick({2131624237})
    void restartContainerClick() {
        if (this.restartClickListener != null) {
            this.restartClickListener.onDialogClick(this);
        }
    }

    @OnClick({2131624234})
    void buyContainerClick() {
        if (this.buyClickListener != null) {
            this.handler.removeCallbacks(this.runnable);
            this.buyClickListener.onDialogClick(this);
        }
    }
}
