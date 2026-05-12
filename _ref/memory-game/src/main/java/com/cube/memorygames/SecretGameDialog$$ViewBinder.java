package com.cube.memorygames;

import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class SecretGameDialog$$ViewBinder<T extends SecretGameDialog> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.bannerSecretGame = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.banner_secret_game, "field 'bannerSecretGame'"), R.id.banner_secret_game, "field 'bannerSecretGame'");
        ((View) finder.findRequiredView(source, R.id.button_ok, "method 'clickOk'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.clickOk();
            }
        });
    }

    public void unbind(T target) {
        target.bannerSecretGame = null;
    }
}
