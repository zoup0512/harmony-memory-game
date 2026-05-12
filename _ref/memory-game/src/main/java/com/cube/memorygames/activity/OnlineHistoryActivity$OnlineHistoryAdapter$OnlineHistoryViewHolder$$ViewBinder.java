package com.cube.memorygames.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.cube.memorygames.activity.OnlineHistoryActivity.OnlineHistoryAdapter.OnlineHistoryViewHolder;
import com.memory.brain.training.games.R;

public class OnlineHistoryActivity$OnlineHistoryAdapter$OnlineHistoryViewHolder$$ViewBinder<T extends OnlineHistoryViewHolder> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.avatar1 = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.avatar1, "field 'avatar1'"), R.id.avatar1, "field 'avatar1'");
        target.avatar2 = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.avatar2, "field 'avatar2'"), R.id.avatar2, "field 'avatar2'");
        target.name1 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.name1, "field 'name1'"), R.id.name1, "field 'name1'");
        target.name2 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.name2, "field 'name2'"), R.id.name2, "field 'name2'");
        target.score1 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.score1, "field 'score1'"), R.id.score1, "field 'score1'");
        target.score2 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.score2, "field 'score2'"), R.id.score2, "field 'score2'");
        target.winnerBadge1 = (View) finder.findRequiredView(source, R.id.winnerBadge1, "field 'winnerBadge1'");
        target.winnerBadge2 = (View) finder.findRequiredView(source, R.id.winnerBadge2, "field 'winnerBadge2'");
        target.timeHeader = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.timeHeader, "field 'timeHeader'"), R.id.timeHeader, "field 'timeHeader'");
    }

    public void unbind(T target) {
        target.avatar1 = null;
        target.avatar2 = null;
        target.name1 = null;
        target.name2 = null;
        target.score1 = null;
        target.score2 = null;
        target.winnerBadge1 = null;
        target.winnerBadge2 = null;
        target.timeHeader = null;
    }
}
