package com.cube.memorygames;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class TrophiesActivity$$ViewBinder<T extends TrophiesActivity> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.recyclerView = (RecyclerView) finder.castView((View) finder.findRequiredView(source, R.id.recyclerView, "field 'recyclerView'"), R.id.recyclerView, "field 'recyclerView'");
        target.title = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.title, "field 'title'"), R.id.title, "field 'title'");
        ((View) finder.findRequiredView(source, R.id.back, "method 'backClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.backClick();
            }
        });
    }

    public void unbind(T target) {
        target.recyclerView = null;
        target.title = null;
    }
}
