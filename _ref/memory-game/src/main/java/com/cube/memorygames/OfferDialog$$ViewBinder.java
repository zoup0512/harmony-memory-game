package com.cube.memorygames;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class OfferDialog$$ViewBinder<T extends OfferDialog> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        View view = (View) finder.findRequiredView(source, R.id.close, "field 'close' and method 'backClick'");
        target.close = (ImageView) finder.castView(view, R.id.close, "field 'close'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.backClick();
            }
        });
        target.gameName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.game_name, "field 'gameName'"), R.id.game_name, "field 'gameName'");
        target.text = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.text, "field 'text'"), R.id.text, "field 'text'");
        target.title = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.title, "field 'title'"), R.id.title, "field 'title'");
        view = (View) finder.findRequiredView(source, R.id.upgrade, "field 'upgrade' and method 'upgradeClick'");
        target.upgrade = (TextView) finder.castView(view, R.id.upgrade, "field 'upgrade'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.upgradeClick();
            }
        });
        target.banner = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.banner, "field 'banner'"), R.id.banner, "field 'banner'");
        target.sales = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.sales, "field 'sales'"), R.id.sales, "field 'sales'");
    }

    public void unbind(T target) {
        target.close = null;
        target.gameName = null;
        target.text = null;
        target.title = null;
        target.upgrade = null;
        target.banner = null;
        target.sales = null;
    }
}
