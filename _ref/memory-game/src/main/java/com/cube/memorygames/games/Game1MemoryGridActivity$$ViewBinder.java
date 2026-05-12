package com.cube.memorygames.games;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.cube.memorygames.ui.TimerView;
import com.memory.brain.training.games.R;

public class Game1MemoryGridActivity$$ViewBinder<T extends Game1MemoryGridActivity> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.life1 = (View) finder.findRequiredView(source, R.id.life1, "field 'life1'");
        target.life2 = (View) finder.findRequiredView(source, R.id.life2, "field 'life2'");
        target.panelLives = (View) finder.findRequiredView(source, R.id.panel_lives, "field 'panelLives'");
        target.progressBar = (ProgressBar) finder.castView((View) finder.findRequiredView(source, R.id.progressBar, "field 'progressBar'"), R.id.progressBar, "field 'progressBar'");
        target.soundToggle = (ToggleButton) finder.castView((View) finder.findRequiredView(source, R.id.sound, "field 'soundToggle'"), R.id.sound, "field 'soundToggle'");
        target.pauseButton = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.pauseButton, "field 'pauseButton'"), R.id.pauseButton, "field 'pauseButton'");
        View view = (View) finder.findRequiredView(source, R.id.panel_pause, "field 'panelPause' and method 'soundClick'");
        target.panelPause = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.soundClick();
            }
        });
        target.correctItem = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.correct_item, "field 'correctItem'"), R.id.correct_item, "field 'correctItem'");
        target.gameName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.game_name, "field 'gameName'"), R.id.game_name, "field 'gameName'");
        target.levelNumber = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.level_number, "field 'levelNumber'"), R.id.level_number, "field 'levelNumber'");
        target.timerContainer = (View) finder.findRequiredView(source, R.id.timerContainer, "field 'timerContainer'");
        target.timerView = (TimerView) finder.castView((View) finder.findRequiredView(source, R.id.timerView, "field 'timerView'"), R.id.timerView, "field 'timerView'");
        target.textLevelReady = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.text_level_ready, "field 'textLevelReady'"), R.id.text_level_ready, "field 'textLevelReady'");
        target.root = (View) finder.findRequiredView(source, R.id.root, "field 'root'");
        target.foreground = (View) finder.findRequiredView(source, R.id.foreground, "field 'foreground'");
        target.levelHint = (View) finder.findRequiredView(source, R.id.level_hint, "field 'levelHint'");
    }

    public void unbind(T target) {
        target.life1 = null;
        target.life2 = null;
        target.panelLives = null;
        target.progressBar = null;
        target.soundToggle = null;
        target.pauseButton = null;
        target.panelPause = null;
        target.correctItem = null;
        target.gameName = null;
        target.levelNumber = null;
        target.timerContainer = null;
        target.timerView = null;
        target.textLevelReady = null;
        target.root = null;
        target.foreground = null;
        target.levelHint = null;
    }
}
