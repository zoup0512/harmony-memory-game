package com.cube.memorygames.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class WorkoutHelpDialog$$ViewBinder<T extends WorkoutHelpDialog> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        View view = (View) finder.findRequiredView(source, R.id.close, "field 'close' and method 'onCloseClick'");
        target.close = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onCloseClick();
            }
        });
    }

    public void unbind(T target) {
        target.close = null;
    }
}
