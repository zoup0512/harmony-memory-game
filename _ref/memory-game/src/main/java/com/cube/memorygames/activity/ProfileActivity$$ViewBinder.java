package com.cube.memorygames.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class ProfileActivity$$ViewBinder<T extends ProfileActivity> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        View view = (View) finder.findRequiredView(source, R.id.profilePicture, "field 'profilePicture' and method 'showPickerDialog'");
        target.profilePicture = (ImageView) finder.castView(view, R.id.profilePicture, "field 'profilePicture'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.showPickerDialog();
            }
        });
        target.userName = (EditText) finder.castView((View) finder.findRequiredView(source, R.id.userName, "field 'userName'"), R.id.userName, "field 'userName'");
        target.progress = (View) finder.findRequiredView(source, R.id.progress, "field 'progress'");
        view = (View) finder.findRequiredView(source, R.id.save, "field 'save' and method 'saveClick'");
        target.save = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.saveClick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.back, "method 'backClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.backClick();
            }
        });
    }

    public void unbind(T target) {
        target.profilePicture = null;
        target.userName = null;
        target.progress = null;
        target.save = null;
    }
}
