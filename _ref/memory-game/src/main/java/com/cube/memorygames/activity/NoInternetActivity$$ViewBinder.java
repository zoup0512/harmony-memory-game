package com.cube.memorygames.activity;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class NoInternetActivity$$ViewBinder<T extends NoInternetActivity> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.title = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.title, "field 'title'"), R.id.title, "field 'title'");
        target.ops = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.ops, "field 'ops'"), R.id.ops, "field 'ops'");
        target.info = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.info, "field 'info'"), R.id.info, "field 'info'");
        View view = (View) finder.findRequiredView(source, R.id.buttonSettings, "field 'buttonSettings' and method 'buttonSettingsClick'");
        target.buttonSettings = (TextView) finder.castView(view, R.id.buttonSettings, "field 'buttonSettings'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.buttonSettingsClick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.back, "method 'backButtonClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.backButtonClick();
            }
        });
    }

    public void unbind(T target) {
        target.title = null;
        target.ops = null;
        target.info = null;
        target.buttonSettings = null;
    }
}
