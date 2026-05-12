package com.cube.memorygames;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class SharingDialog$$ViewBinder<T extends SharingDialog> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.hint = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.hint, "field 'hint'"), R.id.hint, "field 'hint'");
        target.starsView = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.text_top_bar_stars_count, "field 'starsView'"), R.id.text_top_bar_stars_count, "field 'starsView'");
        View view = (View) finder.findRequiredView(source, R.id.text_top_bar_rating, "field 'ratingView' and method 'topClick'");
        target.ratingView = (TextView) finder.castView(view, R.id.text_top_bar_rating, "field 'ratingView'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.topClick();
            }
        });
        target.shareText = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.shareText, "field 'shareText'"), R.id.shareText, "field 'shareText'");
        target.videoText = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.videoText, "field 'videoText'"), R.id.videoText, "field 'videoText'");
        target.fbCoins = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.fbCoins, "field 'fbCoins'"), R.id.fbCoins, "field 'fbCoins'");
        target.videoCoins = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.videoCoins, "field 'videoCoins'"), R.id.videoCoins, "field 'videoCoins'");
        target.dollar1Coins = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.dollar1Coins, "field 'dollar1Coins'"), R.id.dollar1Coins, "field 'dollar1Coins'");
        target.dollar1Count = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.dollar1Count, "field 'dollar1Count'"), R.id.dollar1Count, "field 'dollar1Count'");
        target.dollar2Coins = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.dollar2Coins, "field 'dollar2Coins'"), R.id.dollar2Coins, "field 'dollar2Coins'");
        target.dollar2Count = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.dollar2Count, "field 'dollar2Count'"), R.id.dollar2Count, "field 'dollar2Count'");
        target.dollar3Coins = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.dollar3Coins, "field 'dollar3Coins'"), R.id.dollar3Coins, "field 'dollar3Coins'");
        target.dollar3Count = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.dollar3Count, "field 'dollar3Count'"), R.id.dollar3Count, "field 'dollar3Count'");
        view = (View) finder.findRequiredView(source, R.id.fb_share, "field 'fbContainer' and method 'fbShareClick'");
        target.fbContainer = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.fbShareClick();
            }
        });
        view = (View) finder.findRequiredView(source, R.id.video, "field 'videoContainer' and method 'videoClick'");
        target.videoContainer = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.videoClick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.back, "method 'backClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.backClick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.rating_icon, "method 'topClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.topClick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.dollar1Container, "method 'dollar1ContainerClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.dollar1ContainerClick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.dollar2Container, "method 'dollar2ContainerClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.dollar2ContainerClick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.dollar3Container, "method 'dollar3ContainerClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.dollar3ContainerClick();
            }
        });
    }

    public void unbind(T target) {
        target.hint = null;
        target.starsView = null;
        target.ratingView = null;
        target.shareText = null;
        target.videoText = null;
        target.fbCoins = null;
        target.videoCoins = null;
        target.dollar1Coins = null;
        target.dollar1Count = null;
        target.dollar2Coins = null;
        target.dollar2Count = null;
        target.dollar3Coins = null;
        target.dollar3Count = null;
        target.fbContainer = null;
        target.videoContainer = null;
    }
}
