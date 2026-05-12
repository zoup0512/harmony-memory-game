package com.cube.memorygames.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.cube.memorygames.ui.OnlineAdapter.UserViewHolder;
import com.memory.brain.training.games.R;

public class OnlineAdapter$UserViewHolder$$ViewBinder<T extends UserViewHolder> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.userName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.userName, "field 'userName'"), R.id.userName, "field 'userName'");
        target.profilePicture = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.profilePicture, "field 'profilePicture'"), R.id.profilePicture, "field 'profilePicture'");
        target.profileRating = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.profileRating, "field 'profileRating'"), R.id.profileRating, "field 'profileRating'");
        target.buttonPlay = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.buttonPlay, "field 'buttonPlay'"), R.id.buttonPlay, "field 'buttonPlay'");
        target.buttonHistory = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.buttonHistory, "field 'buttonHistory'"), R.id.buttonHistory, "field 'buttonHistory'");
        target.buttonStatistics = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.buttonStatistics, "field 'buttonStatistics'"), R.id.buttonStatistics, "field 'buttonStatistics'");
    }

    public void unbind(T target) {
        target.userName = null;
        target.profilePicture = null;
        target.profileRating = null;
        target.buttonPlay = null;
        target.buttonHistory = null;
        target.buttonStatistics = null;
    }
}
