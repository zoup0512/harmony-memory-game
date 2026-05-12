package com.cube.memorygames;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class TrophyInfoFragment$$ViewBinder<T extends TrophyInfoFragment> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.title = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.title, "field 'title'"), R.id.title, "field 'title'");
        View view = (View) finder.findRequiredView(source, R.id.viewAll, "field 'viewAll' and method 'viewAllCLick'");
        target.viewAll = (TextView) finder.castView(view, R.id.viewAll, "field 'viewAll'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.viewAllCLick();
            }
        });
        target.cupImage = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.cupImage, "field 'cupImage'"), R.id.cupImage, "field 'cupImage'");
    }

    public void unbind(T target) {
        target.title = null;
        target.viewAll = null;
        target.cupImage = null;
    }
}
