package com.cube.memorygames;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class ChallengeResultDialog$$ViewBinder<T extends ChallengeResultDialog> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.score = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.score, "field 'score'"), R.id.score, "field 'score'");
        View view = (View) finder.findRequiredView(source, R.id.home, "field 'home' and method 'homeClick'");
        target.home = (TextView) finder.castView(view, R.id.home, "field 'home'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.homeClick();
            }
        });
        view = (View) finder.findRequiredView(source, R.id.next, "field 'next' and method 'nextClick'");
        target.next = (TextView) finder.castView(view, R.id.next, "field 'next'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.nextClick();
            }
        });
        target.gameName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.gameName, "field 'gameName'"), R.id.gameName, "field 'gameName'");
        target.star1 = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.star1, "field 'star1'"), R.id.star1, "field 'star1'");
        target.star2 = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.star2, "field 'star2'"), R.id.star2, "field 'star2'");
        target.star3 = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.star3, "field 'star3'"), R.id.star3, "field 'star3'");
    }

    public void unbind(T target) {
        target.score = null;
        target.home = null;
        target.next = null;
        target.gameName = null;
        target.star1 = null;
        target.star2 = null;
        target.star3 = null;
    }
}
