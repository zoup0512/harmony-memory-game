package com.cube.memorygames;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;
import com.viewpagerindicator.CirclePageIndicator;

public class StartGameActivity$$ViewBinder<T extends StartGameActivity> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.viewPager = (ViewPager) finder.castView((View) finder.findRequiredView(source, R.id.view_pager, "field 'viewPager'"), R.id.view_pager, "field 'viewPager'");
        target.indicator = (CirclePageIndicator) finder.castView((View) finder.findRequiredView(source, R.id.indicator, "field 'indicator'"), R.id.indicator, "field 'indicator'");
        target.btnBack = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.btnBack, "field 'btnBack'"), R.id.btnBack, "field 'btnBack'");
        target.textBotton = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.txt_start_game_description, "field 'textBotton'"), R.id.txt_start_game_description, "field 'textBotton'");
        target.textPlayButton = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.text_play_button, "field 'textPlayButton'"), R.id.text_play_button, "field 'textPlayButton'");
        target.playButton = (View) finder.findRequiredView(source, R.id.play_button, "field 'playButton'");
        target.divider = (View) finder.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind(T target) {
        target.viewPager = null;
        target.indicator = null;
        target.btnBack = null;
        target.textBotton = null;
        target.textPlayButton = null;
        target.playButton = null;
        target.divider = null;
    }
}
