package com.cube.memorygames;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class LoginActivity$$ViewBinder<T extends LoginActivity> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.enterNickname = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.enterNick, "field 'enterNickname'"), R.id.enterNick, "field 'enterNickname'");
        target.nickname = (EditText) finder.castView((View) finder.findRequiredView(source, R.id.nickname, "field 'nickname'"), R.id.nickname, "field 'nickname'");
        View view = (View) finder.findRequiredView(source, R.id.skip, "field 'skip' and method 'skipClicked'");
        target.skip = (TextView) finder.castView(view, R.id.skip, "field 'skip'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.skipClicked();
            }
        });
        view = (View) finder.findRequiredView(source, R.id.play, "field 'play' and method 'playClicked'");
        target.play = (TextView) finder.castView(view, R.id.play, "field 'play'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.playClicked();
            }
        });
        view = (View) finder.findRequiredView(source, R.id.facebook_login, "field 'facebookLogin' and method 'facebookClicked'");
        target.facebookLogin = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.facebookClicked();
            }
        });
        target.plus100 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.plus100, "field 'plus100'"), R.id.plus100, "field 'plus100'");
        target.plus50 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.plus50, "field 'plus50'"), R.id.plus50, "field 'plus50'");
        target.login = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.login, "field 'login'"), R.id.login, "field 'login'");
    }

    public void unbind(T target) {
        target.enterNickname = null;
        target.nickname = null;
        target.skip = null;
        target.play = null;
        target.facebookLogin = null;
        target.plus100 = null;
        target.plus50 = null;
        target.login = null;
    }
}
