package com.cube.memorygames.ui;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.memory.brain.training.games.R;

public class WorkoutHelpDialog extends AppCompatDialog {
    @Bind({2131624247})
    View close;

    public WorkoutHelpDialog(Activity activity) {
        super(activity, R.style.GdxTheme);
        setContentView((int) R.layout.dialog_workout_help);
        ButterKnife.bind((Dialog) this);
        getWindow().setBackgroundDrawableResource(R.color.dark_transparent);
        getWindow().clearFlags(2);
    }

    @OnClick({2131624247})
    public void onCloseClick() {
        dismiss();
    }
}
