package com.cube.memorygames;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;

public class ChallengeAdapter$GameHolder$$ViewBinder<T extends GameHolder> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.image = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.image, "field 'image'"), R.id.image, "field 'image'");
        target.categoryColorView = (View) finder.findRequiredView(source, R.id.categoryColorView, "field 'categoryColorView'");
        target.starsContainer = (View) finder.findRequiredView(source, R.id.starsContainer, "field 'starsContainer'");
        target.contentContainer = (View) finder.findRequiredView(source, R.id.contentContainer, "field 'contentContainer'");
        target.previewBackground = (View) finder.findRequiredView(source, R.id.previewBackground, "field 'previewBackground'");
        target.wayContainer = (View) finder.findRequiredView(source, R.id.wayContainer, "field 'wayContainer'");
        target.name = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.name, "field 'name'"), R.id.name, "field 'name'");
        target.categoryName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.categoryName, "field 'categoryName'"), R.id.categoryName, "field 'categoryName'");
        target.star1 = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.star1, "field 'star1'"), R.id.star1, "field 'star1'");
        target.star2 = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.star2, "field 'star2'"), R.id.star2, "field 'star2'");
        target.star3 = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.star3, "field 'star3'"), R.id.star3, "field 'star3'");
    }

    public void unbind(T target) {
        target.image = null;
        target.categoryColorView = null;
        target.starsContainer = null;
        target.contentContainer = null;
        target.previewBackground = null;
        target.wayContainer = null;
        target.name = null;
        target.categoryName = null;
        target.star1 = null;
        target.star2 = null;
        target.star3 = null;
    }
}
