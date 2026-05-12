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

public class OnlineTutorialActivity$$ViewBinder<T extends OnlineTutorialActivity> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.viewPager = (ViewPager) finder.castView((View) finder.findRequiredView(source, R.id.view_pager, "field 'viewPager'"), R.id.view_pager, "field 'viewPager'");
        target.indicator = (CirclePageIndicator) finder.castView((View) finder.findRequiredView(source, R.id.indicator, "field 'indicator'"), R.id.indicator, "field 'indicator'");
        target.btnBack = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.btnBack, "field 'btnBack'"), R.id.btnBack, "field 'btnBack'");
        target.textBotton = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.txt_start_game_description, "field 'textBotton'"), R.id.txt_start_game_description, "field 'textBotton'");
        target.textPlayButton = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.text_play_button, "field 'textPlayButton'"), R.id.text_play_button, "field 'textPlayButton'");
        ((View) finder.findRequiredView(source, R.id.play_button, "method 'onPlayClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onPlayClick();
            }
        });
    }

    public void unbind(T target) {
        target.viewPager = null;
        target.indicator = null;
        target.btnBack = null;
        target.textBotton = null;
        target.textPlayButton = null;
    }
}
