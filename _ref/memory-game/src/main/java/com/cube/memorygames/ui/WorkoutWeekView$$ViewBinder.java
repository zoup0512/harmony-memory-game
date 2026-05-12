package com.cube.memorygames.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;

public class WorkoutWeekView$$ViewBinder<T extends WorkoutWeekView> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.dayOfWeek = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.dayOfWeek, "field 'dayOfWeek'"), R.id.dayOfWeek, "field 'dayOfWeek'");
        target.dayOfMonth = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.dayOfMonth, "field 'dayOfMonth'"), R.id.dayOfMonth, "field 'dayOfMonth'");
        target.result = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.result, "field 'result'"), R.id.result, "field 'result'");
    }

    public void unbind(T target) {
        target.dayOfWeek = null;
        target.dayOfMonth = null;
        target.result = null;
    }
}
