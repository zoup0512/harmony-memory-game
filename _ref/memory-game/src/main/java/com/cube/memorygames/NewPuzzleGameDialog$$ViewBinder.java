package com.cube.memorygames;

import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class NewPuzzleGameDialog$$ViewBinder<T extends NewPuzzleGameDialog> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.mBanner = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.puzzle_game_banner, "field 'mBanner'"), R.id.puzzle_game_banner, "field 'mBanner'");
        ((View) finder.findRequiredView(source, R.id.close, "method 'backClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.backClick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.open_play_market, "method 'openPlayMarket'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.openPlayMarket();
            }
        });
    }

    public void unbind(T target) {
        target.mBanner = null;
    }
}
