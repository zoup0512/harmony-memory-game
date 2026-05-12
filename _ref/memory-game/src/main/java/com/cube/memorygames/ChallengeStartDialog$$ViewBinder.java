package com.cube.memorygames;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class ChallengeStartDialog$$ViewBinder<T extends ChallengeStartDialog> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.score = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.score, "field 'score'"), R.id.score, "field 'score'");
        View view = (View) finder.findRequiredView(source, R.id.play, "field 'play' and method 'playClick'");
        target.play = (TextView) finder.castView(view, R.id.play, "field 'play'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.playClick();
            }
        });
        view = (View) finder.findRequiredView(source, R.id.tutorial, "field 'tutorial' and method 'tutorialClick'");
        target.tutorial = (TextView) finder.castView(view, R.id.tutorial, "field 'tutorial'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.tutorialClick();
            }
        });
        target.gameName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.gameName, "field 'gameName'"), R.id.gameName, "field 'gameName'");
        target.categoryColorView = (View) finder.findRequiredView(source, R.id.categoryColorView, "field 'categoryColorView'");
        target.image = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.image, "field 'image'"), R.id.image, "field 'image'");
    }

    public void unbind(T target) {
        target.score = null;
        target.play = null;
        target.tutorial = null;
        target.gameName = null;
        target.categoryColorView = null;
        target.image = null;
    }
}
