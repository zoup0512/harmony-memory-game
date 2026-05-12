package com.cube.memorygames.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class OnlineHistoryActivity$$ViewBinder<T extends OnlineHistoryActivity> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.recyclerHistory = (RecyclerView) finder.castView((View) finder.findRequiredView(source, R.id.recyclerHistory, "field 'recyclerHistory'"), R.id.recyclerHistory, "field 'recyclerHistory'");
        target.progressContainer = (View) finder.findRequiredView(source, R.id.progressContainer, "field 'progressContainer'");
        ((View) finder.findRequiredView(source, R.id.back, "method 'backClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.backClick();
            }
        });
    }

    public void unbind(T target) {
        target.recyclerHistory = null;
        target.progressContainer = null;
    }
}
