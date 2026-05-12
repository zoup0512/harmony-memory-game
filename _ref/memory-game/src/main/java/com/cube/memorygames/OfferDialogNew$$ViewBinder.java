package com.cube.memorygames;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class OfferDialogNew$$ViewBinder<T extends OfferDialogNew> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        View view = (View) finder.findRequiredView(source, R.id.close, "field 'close' and method 'backClick'");
        target.close = (ImageView) finder.castView(view, R.id.close, "field 'close'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.backClick();
            }
        });
        view = (View) finder.findRequiredView(source, R.id.upgrade, "field 'upgrade' and method 'upgradeClick'");
        target.upgrade = (TextView) finder.castView(view, R.id.upgrade, "field 'upgrade'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.upgradeClick();
            }
        });
        target.text = (TextView) finder.castView((View) finder.findOptionalView(source, R.id.text, null), R.id.text, "field 'text'");
    }

    public void unbind(T target) {
        target.close = null;
        target.upgrade = null;
        target.text = null;
    }
}
