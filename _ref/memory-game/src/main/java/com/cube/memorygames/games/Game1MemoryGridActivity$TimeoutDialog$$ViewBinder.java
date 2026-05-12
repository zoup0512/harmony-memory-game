package com.cube.memorygames.games;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class Game1MemoryGridActivity$TimeoutDialog$$ViewBinder<T extends TimeoutDialog> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        View view = (View) finder.findRequiredView(source, R.id.fail_dialog_game_suggestion_image, "field 'gameSuggestionImage' and method 'gameSuggestionClick'");
        target.gameSuggestionImage = (ImageButton) finder.castView(view, R.id.fail_dialog_game_suggestion_image, "field 'gameSuggestionImage'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.gameSuggestionClick();
            }
        });
        target.gameSuggestionName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.fail_dialog_game_suggestion_game_name, "field 'gameSuggestionName'"), R.id.fail_dialog_game_suggestion_game_name, "field 'gameSuggestionName'");
        target.restartTitle = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.fail_dialog_restart_title, "field 'restartTitle'"), R.id.fail_dialog_restart_title, "field 'restartTitle'");
        target.failDialogTitle = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.fail_dialog_title, "field 'failDialogTitle'"), R.id.fail_dialog_title, "field 'failDialogTitle'");
        target.failDialogInnerTitle = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.fail_dialog_inner_title, "field 'failDialogInnerTitle'"), R.id.fail_dialog_inner_title, "field 'failDialogInnerTitle'");
        target.gameSuggestionsContainer = (View) finder.findRequiredView(source, R.id.fail_dialog_game_suggestion_container, "field 'gameSuggestionsContainer'");
        view = (View) finder.findRequiredView(source, R.id.mainMenu, "field 'mainMenu' and method 'mainMenuContainerCLick'");
        target.mainMenu = (TextView) finder.castView(view, R.id.mainMenu, "field 'mainMenu'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.mainMenuContainerCLick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.button_dialog_fail_retry, "method 'retryClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.retryClick();
            }
        });
    }

    public void unbind(T target) {
        target.gameSuggestionImage = null;
        target.gameSuggestionName = null;
        target.restartTitle = null;
        target.failDialogTitle = null;
        target.failDialogInnerTitle = null;
        target.gameSuggestionsContainer = null;
        target.mainMenu = null;
    }
}
