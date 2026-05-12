package com.cube.memorygames.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.cube.memorygames.activity.OnlineGameListAdapter.GameHolder;
import com.memory.brain.training.games.R;

public class OnlineGameListAdapter$GameHolder$$ViewBinder<T extends GameHolder> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.image = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.image, "field 'image'"), R.id.image, "field 'image'");
        target.name = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.name, "field 'name'"), R.id.name, "field 'name'");
        target.result = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.result, "field 'result'"), R.id.result, "field 'result'");
        target.player1 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.player1, "field 'player1'"), R.id.player1, "field 'player1'");
        target.player2 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.player2, "field 'player2'"), R.id.player2, "field 'player2'");
    }

    public void unbind(T target) {
        target.image = null;
        target.name = null;
        target.result = null;
        target.player1 = null;
        target.player2 = null;
    }
}
