package com.cube.memorygames.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class TrophyDialog$$ViewBinder<T extends TrophyDialog> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.root = (View) finder.findRequiredView(source, R.id.root, "field 'root'");
        target.cupImage = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.cupImage, "field 'cupImage'"), R.id.cupImage, "field 'cupImage'");
        View view = (View) finder.findRequiredView(source, R.id.continueButton, "field 'continueButton' and method 'continueButtonClick'");
        target.continueButton = (TextView) finder.castView(view, R.id.continueButton, "field 'continueButton'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.continueButtonClick();
            }
        });
        target.title = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.title, "field 'title'"), R.id.title, "field 'title'");
        target.subTitle = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.subTitle, "field 'subTitle'"), R.id.subTitle, "field 'subTitle'");
    }

    public void unbind(T target) {
        target.root = null;
        target.cupImage = null;
        target.continueButton = null;
        target.title = null;
        target.subTitle = null;
    }
}
