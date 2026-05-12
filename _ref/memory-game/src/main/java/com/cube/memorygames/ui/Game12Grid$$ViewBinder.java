package com.cube.memorygames.ui;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;
import com.wenchao.cardstack.CardStack;

public class Game12Grid$$ViewBinder<T extends Game12Grid> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.cardStack1 = (CardStack) finder.castView((View) finder.findRequiredView(source, R.id.container1, "field 'cardStack1'"), R.id.container1, "field 'cardStack1'");
        target.textEquals = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.textEquals, "field 'textEquals'"), R.id.textEquals, "field 'textEquals'");
        target.cardStack2 = (CardStack) finder.castView((View) finder.findRequiredView(source, R.id.container2, "field 'cardStack2'"), R.id.container2, "field 'cardStack2'");
        target.click1 = (View) finder.findRequiredView(source, R.id.click1, "field 'click1'");
        target.click2 = (View) finder.findRequiredView(source, R.id.click2, "field 'click2'");
        target.click3 = (View) finder.findRequiredView(source, R.id.click3, "field 'click3'");
    }

    public void unbind(T target) {
        target.cardStack1 = null;
        target.textEquals = null;
        target.cardStack2 = null;
        target.click1 = null;
        target.click2 = null;
        target.click3 = null;
    }
}
