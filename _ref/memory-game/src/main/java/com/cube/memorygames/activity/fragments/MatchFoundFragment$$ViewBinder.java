package com.cube.memorygames.activity.fragments;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;

public class MatchFoundFragment$$ViewBinder<T extends MatchFoundFragment> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.wins1 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.wins1, "field 'wins1'"), R.id.wins1, "field 'wins1'");
        target.losses1 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.loses1, "field 'losses1'"), R.id.loses1, "field 'losses1'");
        target.draws1 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.draws1, "field 'draws1'"), R.id.draws1, "field 'draws1'");
        target.ratio1 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.ratio1, "field 'ratio1'"), R.id.ratio1, "field 'ratio1'");
        target.wins2 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.wins2, "field 'wins2'"), R.id.wins2, "field 'wins2'");
        target.losses2 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.loses2, "field 'losses2'"), R.id.loses2, "field 'losses2'");
        target.draws2 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.draws2, "field 'draws2'"), R.id.draws2, "field 'draws2'");
        target.ratio2 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.ratio2, "field 'ratio2'"), R.id.ratio2, "field 'ratio2'");
        target.statsTitle = (View) finder.findRequiredView(source, R.id.statsTitle, "field 'statsTitle'");
        target.winsContainer = (View) finder.findRequiredView(source, R.id.winsContainer, "field 'winsContainer'");
        target.losesContainer = (View) finder.findRequiredView(source, R.id.losesContainer, "field 'losesContainer'");
        target.drawsContainer = (View) finder.findRequiredView(source, R.id.drawsContainer, "field 'drawsContainer'");
        target.ratioContainer = (View) finder.findRequiredView(source, R.id.ratioContainer, "field 'ratioContainer'");
        target.betsTitle = (View) finder.findRequiredView(source, R.id.betsTitle, "field 'betsTitle'");
        target.betsContainer = (View) finder.findRequiredView(source, R.id.betsContainer, "field 'betsContainer'");
    }

    public void unbind(T target) {
        target.wins1 = null;
        target.losses1 = null;
        target.draws1 = null;
        target.ratio1 = null;
        target.wins2 = null;
        target.losses2 = null;
        target.draws2 = null;
        target.ratio2 = null;
        target.statsTitle = null;
        target.winsContainer = null;
        target.losesContainer = null;
        target.drawsContainer = null;
        target.ratioContainer = null;
        target.betsTitle = null;
        target.betsContainer = null;
    }
}
