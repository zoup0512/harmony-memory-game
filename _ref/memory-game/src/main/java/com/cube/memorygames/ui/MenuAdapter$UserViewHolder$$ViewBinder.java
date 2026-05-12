package com.cube.memorygames.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.cube.memorygames.ui.MenuAdapter.UserViewHolder;
import com.memory.brain.training.games.R;

public class MenuAdapter$UserViewHolder$$ViewBinder<T extends UserViewHolder> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.userName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.userName, "field 'userName'"), R.id.userName, "field 'userName'");
        target.profilePicture = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.profilePicture, "field 'profilePicture'"), R.id.profilePicture, "field 'profilePicture'");
    }

    public void unbind(T target) {
        target.userName = null;
        target.profilePicture = null;
    }
}
