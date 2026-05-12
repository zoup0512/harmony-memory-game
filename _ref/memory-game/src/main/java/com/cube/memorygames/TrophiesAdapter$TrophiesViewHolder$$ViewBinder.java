package com.cube.memorygames;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.cube.memorygames.TrophiesAdapter.TrophiesViewHolder;
import com.memory.brain.training.games.R;

public class TrophiesAdapter$TrophiesViewHolder$$ViewBinder<T extends TrophiesViewHolder> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.gameName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.gameName, "field 'gameName'"), R.id.gameName, "field 'gameName'");
        target.trophy = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.trophy, "field 'trophy'"), R.id.trophy, "field 'trophy'");
    }

    public void unbind(T target) {
        target.gameName = null;
        target.trophy = null;
    }
}
