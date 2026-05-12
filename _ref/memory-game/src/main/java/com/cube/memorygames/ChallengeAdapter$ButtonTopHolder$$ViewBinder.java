package com.cube.memorygames;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;

public class ChallengeAdapter$ButtonTopHolder$$ViewBinder<T extends ButtonTopHolder> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.levelName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.levelName, "field 'levelName'"), R.id.levelName, "field 'levelName'");
        target.starsCount = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.starsCount, "field 'starsCount'"), R.id.starsCount, "field 'starsCount'");
        target.prev = (View) finder.findRequiredView(source, R.id.prev, "field 'prev'");
    }

    public void unbind(T target) {
        target.levelName = null;
        target.starsCount = null;
        target.prev = null;
    }
}
