package com.cube.memorygames.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.cube.memorygames.MemoryApplicationModel;
import com.memory.brain.training.games.R;

public class TrophyDialog extends AppCompatDialog {
    @Bind({2131624312})
    TextView continueButton;
    @Bind({2131624310})
    ImageView cupImage;
    @Bind({2131624080})
    View root;
    @Bind({2131624311})
    TextView subTitle;
    @Bind({2131624032})
    TextView title;

    public TrophyDialog(Context context, int cupResId, int textResId, OnDismissListener onDismissListener) {
        super(context, R.style.GdxTheme);
        setContentView((int) R.layout.dialog_trophy);
        getWindow().setWindowAnimations(R.style.WinDialogAnimation);
        ButterKnife.bind((Dialog) this);
        setOnDismissListener(onDismissListener);
        this.cupImage.setImageResource(cupResId);
        Typeface typeFaceRoboto = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
        this.continueButton.setTypeface(typeFaceRoboto);
        this.title.setTypeface(typeFaceRoboto);
        this.subTitle.setTypeface(typeFaceRoboto);
        this.subTitle.setText(textResId);
        MemoryApplicationModel.getInstance().logEvent("TrophyDialog", MemoryApplicationModel.ANALYTICS_CATEGORY_GENERAL, MemoryApplicationModel.ANALYTICS_EVENT_GAME_NEW_TROPHY);
        Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_GAME_NEW_TROPHY));
    }

    @OnClick({2131624312})
    void continueButtonClick() {
        dismiss();
    }
}
