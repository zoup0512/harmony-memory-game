package com.cube.memorygames.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class OnlineResultDialog$$ViewBinder<T extends OnlineResultDialog> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.title = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.title, "field 'title'"), R.id.title, "field 'title'");
        target.newCoins = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.newCoins, "field 'newCoins'"), R.id.newCoins, "field 'newCoins'");
        target.totalCoins = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.totalCoins, "field 'totalCoins'"), R.id.totalCoins, "field 'totalCoins'");
        target.newRating = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.newRating, "field 'newRating'"), R.id.newRating, "field 'newRating'");
        target.totalRating = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.totalRating, "field 'totalRating'"), R.id.totalRating, "field 'totalRating'");
        View view = (View) finder.findRequiredView(source, R.id.done, "field 'done' and method 'doneClick'");
        target.done = (TextView) finder.castView(view, R.id.done, "field 'done'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.doneClick();
            }
        });
        target.doodle = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.doodle, "field 'doodle'"), R.id.doodle, "field 'doodle'");
    }

    public void unbind(T target) {
        target.title = null;
        target.newCoins = null;
        target.totalCoins = null;
        target.newRating = null;
        target.totalRating = null;
        target.done = null;
        target.doodle = null;
    }
}
