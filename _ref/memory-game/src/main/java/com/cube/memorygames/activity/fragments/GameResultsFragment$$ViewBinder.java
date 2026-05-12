package com.cube.memorygames.activity.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;

public class GameResultsFragment$$ViewBinder<T extends GameResultsFragment> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.recyclerView = (RecyclerView) finder.castView((View) finder.findRequiredView(source, R.id.recyclerView, "field 'recyclerView'"), R.id.recyclerView, "field 'recyclerView'");
    }

    public void unbind(T target) {
        target.recyclerView = null;
    }
}
