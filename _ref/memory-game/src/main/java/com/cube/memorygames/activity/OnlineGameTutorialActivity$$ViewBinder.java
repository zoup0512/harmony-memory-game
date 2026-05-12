package com.cube.memorygames.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;
import com.viewpagerindicator.CirclePageIndicator;

public class OnlineGameTutorialActivity$$ViewBinder<T extends OnlineGameTutorialActivity> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.viewPager = (ViewPager) finder.castView((View) finder.findRequiredView(source, R.id.view_pager, "field 'viewPager'"), R.id.view_pager, "field 'viewPager'");
        target.indicator = (CirclePageIndicator) finder.castView((View) finder.findRequiredView(source, R.id.indicator, "field 'indicator'"), R.id.indicator, "field 'indicator'");
        View view = (View) finder.findRequiredView(source, R.id.btnBack, "field 'btnBack' and method 'backClick'");
        target.btnBack = (ImageView) finder.castView(view, R.id.btnBack, "field 'btnBack'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.backClick();
            }
        });
        target.textBotton = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.txt_start_game_description, "field 'textBotton'"), R.id.txt_start_game_description, "field 'textBotton'");
        target.title = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.title, "field 'title'"), R.id.title, "field 'title'");
        ((View) finder.findRequiredView(source, R.id.skip_button, "method 'skipClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.skipClick();
            }
        });
    }

    public void unbind(T target) {
        target.viewPager = null;
        target.indicator = null;
        target.btnBack = null;
        target.textBotton = null;
        target.title = null;
    }
}
