package com.cube.memorygames.ui;

import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;
import com.wenchao.cardstack.CardStack;

public class Game16Grid$$ViewBinder<T extends Game16Grid> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.cardStack = (CardStack) finder.castView((View) finder.findRequiredView(source, R.id.container, "field 'cardStack'"), R.id.container, "field 'cardStack'");
        target.correct = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.correct, "field 'correct'"), R.id.correct, "field 'correct'");
        target.incorrect = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.incorrect, "field 'incorrect'"), R.id.incorrect, "field 'incorrect'");
    }

    public void unbind(T target) {
        target.cardStack = null;
        target.correct = null;
        target.incorrect = null;
    }
}
