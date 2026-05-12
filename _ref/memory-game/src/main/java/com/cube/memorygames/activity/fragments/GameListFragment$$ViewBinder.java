package com.cube.memorygames.activity.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.cube.memorygames.ui.TimerView;
import com.memory.brain.training.games.R;

public class GameListFragment$$ViewBinder<T extends GameListFragment> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.recyclerView = (RecyclerView) finder.castView((View) finder.findRequiredView(source, R.id.recyclerView, "field 'recyclerView'"), R.id.recyclerView, "field 'recyclerView'");
        target.timerView = (TimerView) finder.castView((View) finder.findRequiredView(source, R.id.timerView, "field 'timerView'"), R.id.timerView, "field 'timerView'");
    }

    public void unbind(T target) {
        target.recyclerView = null;
        target.timerView = null;
    }
}
