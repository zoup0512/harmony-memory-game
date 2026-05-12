package com.cube.memorygames.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.cube.memorygames.ui.MenuAdapter.DividerViewHolder;
import com.memory.brain.training.games.R;

public class MenuAdapter$DividerViewHolder$$ViewBinder<T extends DividerViewHolder> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.divider = (View) finder.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind(T target) {
        target.divider = null;
    }
}
