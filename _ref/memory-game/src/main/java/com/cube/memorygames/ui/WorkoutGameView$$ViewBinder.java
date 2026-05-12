package com.cube.memorygames.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;

public class WorkoutGameView$$ViewBinder<T extends WorkoutGameView> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.gameImage = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.gameImage, "field 'gameImage'"), R.id.gameImage, "field 'gameImage'");
        target.categoryColorView = (View) finder.findRequiredView(source, R.id.categoryColorView, "field 'categoryColorView'");
        target.gameName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.gameName, "field 'gameName'"), R.id.gameName, "field 'gameName'");
        target.finishedLayout = (View) finder.findRequiredView(source, R.id.finishedLayout, "field 'finishedLayout'");
    }

    public void unbind(T target) {
        target.gameImage = null;
        target.categoryColorView = null;
        target.gameName = null;
        target.finishedLayout = null;
    }
}
