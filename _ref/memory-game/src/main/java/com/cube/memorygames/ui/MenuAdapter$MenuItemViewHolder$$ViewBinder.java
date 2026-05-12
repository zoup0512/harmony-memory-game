package com.cube.memorygames.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.cube.memorygames.ui.MenuAdapter.MenuItemViewHolder;
import com.memory.brain.training.games.R;

public class MenuAdapter$MenuItemViewHolder$$ViewBinder<T extends MenuItemViewHolder> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.name = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.name, "field 'name'"), R.id.name, "field 'name'");
        target.icon = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.icon, "field 'icon'"), R.id.icon, "field 'icon'");
    }

    public void unbind(T target) {
        target.name = null;
        target.icon = null;
    }
}
