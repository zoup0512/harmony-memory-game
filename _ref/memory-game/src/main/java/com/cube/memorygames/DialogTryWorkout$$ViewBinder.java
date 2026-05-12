package com.cube.memorygames;

import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class DialogTryWorkout$$ViewBinder<T extends DialogTryWorkout> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        View view = (View) finder.findRequiredView(source, R.id.dialog_try_workout_cancel_button, "field 'nCancelButton' and method 'onCancelClick'");
        target.nCancelButton = (Button) finder.castView(view, R.id.dialog_try_workout_cancel_button, "field 'nCancelButton'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onCancelClick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.dialog_try_workout_confirm_button, "method 'onConfirmClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onConfirmClick();
            }
        });
    }

    public void unbind(T target) {
        target.nCancelButton = null;
    }
}
