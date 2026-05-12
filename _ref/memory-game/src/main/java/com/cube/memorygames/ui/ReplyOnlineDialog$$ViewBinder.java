package com.cube.memorygames.ui;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class ReplyOnlineDialog$$ViewBinder<T extends ReplyOnlineDialog> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.title = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.title, "field 'title'"), R.id.title, "field 'title'");
        target.subtitle = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.subtitle, "field 'subtitle'"), R.id.subtitle, "field 'subtitle'");
        target.replyButtonTitle = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.replyButtonTitle, "field 'replyButtonTitle'"), R.id.replyButtonTitle, "field 'replyButtonTitle'");
        target.replyButtonLevel = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.replyButtonLevel, "field 'replyButtonLevel'"), R.id.replyButtonLevel, "field 'replyButtonLevel'");
        View view = (View) finder.findRequiredView(source, R.id.buttonNext, "field 'buttonNext' and method 'onNextClick'");
        target.buttonNext = (TextView) finder.castView(view, R.id.buttonNext, "field 'buttonNext'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onNextClick();
            }
        });
        target.replyButtonText = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.replyButtonText, "field 'replyButtonText'"), R.id.replyButtonText, "field 'replyButtonText'");
        target.timerView = (TimerView) finder.castView((View) finder.findRequiredView(source, R.id.timerView, "field 'timerView'"), R.id.timerView, "field 'timerView'");
        view = (View) finder.findRequiredView(source, R.id.replyButton, "field 'replyButton' and method 'onReplayClick'");
        target.replyButton = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onReplayClick();
            }
        });
        target.gameNumberView = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.gameNumber, "field 'gameNumberView'"), R.id.gameNumber, "field 'gameNumberView'");
    }

    public void unbind(T target) {
        target.title = null;
        target.subtitle = null;
        target.replyButtonTitle = null;
        target.replyButtonLevel = null;
        target.buttonNext = null;
        target.replyButtonText = null;
        target.timerView = null;
        target.replyButton = null;
        target.gameNumberView = null;
    }
}
