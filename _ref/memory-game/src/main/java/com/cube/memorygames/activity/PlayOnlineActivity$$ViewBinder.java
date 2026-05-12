package com.cube.memorygames.activity;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.cube.memorygames.ui.TimerView;
import com.memory.brain.training.games.R;

public class PlayOnlineActivity$$ViewBinder<T extends PlayOnlineActivity> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        View view = (View) finder.findRequiredView(source, R.id.back, "field 'backButton' and method 'backClick'");
        target.backButton = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.backClick(p0);
            }
        });
        view = (View) finder.findRequiredView(source, R.id.buttonCancel, "field 'buttonCancel' and method 'cancelSearch'");
        target.buttonCancel = (TextView) finder.castView(view, R.id.buttonCancel, "field 'buttonCancel'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.cancelSearch(p0);
            }
        });
        view = (View) finder.findRequiredView(source, R.id.buttonStart, "field 'buttonStart' and method 'play'");
        target.buttonStart = (TextView) finder.castView(view, R.id.buttonStart, "field 'buttonStart'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.play(p0);
            }
        });
        view = (View) finder.findRequiredView(source, R.id.buttonNext, "field 'buttonNext' and method 'nextClick'");
        target.buttonNext = (TextView) finder.castView(view, R.id.buttonNext, "field 'buttonNext'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.nextClick(p0);
            }
        });
        target.starsCount = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.text_top_bar_stars_count, "field 'starsCount'"), R.id.text_top_bar_stars_count, "field 'starsCount'");
        target.buttonsContainer = (View) finder.findRequiredView(source, R.id.buttonsContainer, "field 'buttonsContainer'");
        target.bottomDivider = (View) finder.findRequiredView(source, R.id.bottomDivider, "field 'bottomDivider'");
        target.timerContainer = (View) finder.findRequiredView(source, R.id.timerContainer, "field 'timerContainer'");
        target.timerView2 = (TimerView) finder.castView((View) finder.findRequiredView(source, R.id.timerView2, "field 'timerView2'"), R.id.timerView2, "field 'timerView2'");
        target.timerLabel = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.timer_label, "field 'timerLabel'"), R.id.timer_label, "field 'timerLabel'");
    }

    public void unbind(T target) {
        target.backButton = null;
        target.buttonCancel = null;
        target.buttonStart = null;
        target.buttonNext = null;
        target.starsCount = null;
        target.buttonsContainer = null;
        target.bottomDivider = null;
        target.timerContainer = null;
        target.timerView2 = null;
        target.timerLabel = null;
    }
}
