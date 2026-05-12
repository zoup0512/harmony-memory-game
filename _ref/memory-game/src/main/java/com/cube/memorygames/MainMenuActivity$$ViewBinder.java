package com.cube.memorygames;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class MainMenuActivity$$ViewBinder<T extends MainMenuActivity> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.recyclerView = (RecyclerView) finder.castView((View) finder.findRequiredView(source, R.id.recyclerView, "field 'recyclerView'"), R.id.recyclerView, "field 'recyclerView'");
        target.panelHeader = (View) finder.findRequiredView(source, R.id.panel_header, "field 'panelHeader'");
        target.divider = (View) finder.findRequiredView(source, R.id.divider, "field 'divider'");
        View view = (View) finder.findRequiredView(source, R.id.text_top_bar_stars_count, "field 'starsView' and method 'showMoneyDialogFromStarClick'");
        target.starsView = (TextView) finder.castView(view, R.id.text_top_bar_stars_count, "field 'starsView'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.showMoneyDialogFromStarClick();
            }
        });
        view = (View) finder.findRequiredView(source, R.id.text_top_bar_rating, "field 'ratingView' and method 'topClick2'");
        target.ratingView = (TextView) finder.castView(view, R.id.text_top_bar_rating, "field 'ratingView'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.topClick2();
            }
        });
        target.coinsHint = (View) finder.findRequiredView(source, R.id.coins_hint, "field 'coinsHint'");
        target.topHint = (View) finder.findRequiredView(source, R.id.top_hint, "field 'topHint'");
        view = (View) finder.findRequiredView(source, R.id.star, "field 'star' and method 'showMoneyDialogClick'");
        target.star = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.showMoneyDialogClick();
            }
        });
        target.root = (View) finder.findRequiredView(source, R.id.root, "field 'root'");
        ((View) finder.findRequiredView(source, R.id.rating_icon, "method 'topClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.topClick();
            }
        });
    }

    public void unbind(T target) {
        target.recyclerView = null;
        target.panelHeader = null;
        target.divider = null;
        target.starsView = null;
        target.ratingView = null;
        target.coinsHint = null;
        target.topHint = null;
        target.star = null;
        target.root = null;
    }
}
