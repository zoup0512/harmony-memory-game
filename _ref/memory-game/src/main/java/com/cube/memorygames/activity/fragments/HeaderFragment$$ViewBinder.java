package com.cube.memorygames.activity.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;

public class HeaderFragment$$ViewBinder<T extends HeaderFragment> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.userName1 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.userName1, "field 'userName1'"), R.id.userName1, "field 'userName1'");
        target.userName2 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.userName2, "field 'userName2'"), R.id.userName2, "field 'userName2'");
        target.rating1 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.rating1, "field 'rating1'"), R.id.rating1, "field 'rating1'");
        target.rating2 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.rating2, "field 'rating2'"), R.id.rating2, "field 'rating2'");
        target.profilePicture1 = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.profilePicture1, "field 'profilePicture1'"), R.id.profilePicture1, "field 'profilePicture1'");
        target.profilePicture2 = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.profilePicture2, "field 'profilePicture2'"), R.id.profilePicture2, "field 'profilePicture2'");
        target.bolt = (View) finder.findRequiredView(source, R.id.bolt, "field 'bolt'");
        target.ratingContainer1 = (View) finder.findRequiredView(source, R.id.ratingContainer1, "field 'ratingContainer1'");
        target.ratingContainer2 = (View) finder.findRequiredView(source, R.id.ratingContainer2, "field 'ratingContainer2'");
        target.circles = Finder.listOf((View) finder.findRequiredView(source, R.id.circle1, "field 'circles'"), (View) finder.findRequiredView(source, R.id.circle2, "field 'circles'"), (View) finder.findRequiredView(source, R.id.circle3, "field 'circles'"));
    }

    public void unbind(T target) {
        target.userName1 = null;
        target.userName2 = null;
        target.rating1 = null;
        target.rating2 = null;
        target.profilePicture1 = null;
        target.profilePicture2 = null;
        target.bolt = null;
        target.ratingContainer1 = null;
        target.ratingContainer2 = null;
        target.circles = null;
    }
}
