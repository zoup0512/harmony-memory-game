package com.cube.memorygames;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;

public class ChallengeAdapter$ButtonBottomHolder$$ViewBinder<T extends ButtonBottomHolder> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.nextLevel = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.nextLevel, "field 'nextLevel'"), R.id.nextLevel, "field 'nextLevel'");
        target.levelName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.levelName, "field 'levelName'"), R.id.levelName, "field 'levelName'");
        target.starsCount = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.starsCount, "field 'starsCount'"), R.id.starsCount, "field 'starsCount'");
        target.star = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.star, "field 'star'"), R.id.star, "field 'star'");
        target.next = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.next, "field 'next'"), R.id.next, "field 'next'");
        target.contentContainer = (View) finder.findRequiredView(source, R.id.contentContainer, "field 'contentContainer'");
        target.starsContainer = (View) finder.findRequiredView(source, R.id.starsContainer, "field 'starsContainer'");
    }

    public void unbind(T target) {
        target.nextLevel = null;
        target.levelName = null;
        target.starsCount = null;
        target.star = null;
        target.next = null;
        target.contentContainer = null;
        target.starsContainer = null;
    }
}
