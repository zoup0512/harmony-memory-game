package com.cube.memorygames.ui;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;

public class OnlineProgressDialog$$ViewBinder<T extends OnlineProgressDialog> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.title = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.dialog_title, "field 'title'"), R.id.dialog_title, "field 'title'");
        target.timerView = (TimerView) finder.castView((View) finder.findRequiredView(source, R.id.timerView, "field 'timerView'"), R.id.timerView, "field 'timerView'");
        target.yourScore = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.your_score, "field 'yourScore'"), R.id.your_score, "field 'yourScore'");
        target.gameNumberView = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.gameNumber, "field 'gameNumberView'"), R.id.gameNumber, "field 'gameNumberView'");
    }

    public void unbind(T target) {
        target.title = null;
        target.timerView = null;
        target.yourScore = null;
        target.gameNumberView = null;
    }
}
