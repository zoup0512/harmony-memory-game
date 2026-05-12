package com.cube.memorygames;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.cube.memorygames.ui.TimerView;
import com.memory.brain.training.games.R;

public class FailDialog$$ViewBinder<T extends FailDialog> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.timerView = (TimerView) finder.castView((View) finder.findRequiredView(source, R.id.timerView, "field 'timerView'"), R.id.timerView, "field 'timerView'");
        target.buyText = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.buyText, "field 'buyText'"), R.id.buyText, "field 'buyText'");
        View view = (View) finder.findRequiredView(source, R.id.buyImage, "field 'buyImage' and method 'buyContainerClick'");
        target.buyImage = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.buyContainerClick();
            }
        });
        target.restartContainer = (View) finder.findRequiredView(source, R.id.restartContainer, "field 'restartContainer'");
        ((View) finder.findRequiredView(source, R.id.restartImage, "method 'restartContainerClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.restartContainerClick();
            }
        });
    }

    public void unbind(T target) {
        target.timerView = null;
        target.buyText = null;
        target.buyImage = null;
        target.restartContainer = null;
    }
}
