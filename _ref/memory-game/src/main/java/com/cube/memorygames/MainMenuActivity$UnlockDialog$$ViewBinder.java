package com.cube.memorygames;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;

public class MainMenuActivity$UnlockDialog$$ViewBinder<T extends UnlockDialog> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.message = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.message, "field 'message'"), R.id.message, "field 'message'");
        target.ok = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.ok, "field 'ok'"), R.id.ok, "field 'ok'");
        target.cancel = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.cancel, "field 'cancel'"), R.id.cancel, "field 'cancel'");
    }

    public void unbind(T target) {
        target.message = null;
        target.ok = null;
        target.cancel = null;
    }
}
