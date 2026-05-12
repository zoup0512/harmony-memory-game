package com.cube.memorygames;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class TopRanksActivity$$ViewBinder<T extends TopRanksActivity> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.recyclerTopRanks = (RecyclerView) finder.castView((View) finder.findRequiredView(source, R.id.recyclerTopRanks, "field 'recyclerTopRanks'"), R.id.recyclerTopRanks, "field 'recyclerTopRanks'");
        target.title = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.title, "field 'title'"), R.id.title, "field 'title'");
        target.progressContainer = (View) finder.findRequiredView(source, R.id.progressContainer, "field 'progressContainer'");
        ((View) finder.findRequiredView(source, R.id.back, "method 'backClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.backClick();
            }
        });
    }

    public void unbind(T target) {
        target.recyclerTopRanks = null;
        target.title = null;
        target.progressContainer = null;
    }
}
