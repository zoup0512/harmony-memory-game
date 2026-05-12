package com.cube.memorygames.ui;

import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.memory.brain.training.games.R;

public class PauseDialog$$ViewBinder<T extends PauseDialog> implements ViewBinder<T> {
    public void bind(Finder finder, final T target, Object source) {
        target.root = (View) finder.findRequiredView(source, R.id.root, "field 'root'");
        target.resumeButton = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.resumeButton, "field 'resumeButton'"), R.id.resumeButton, "field 'resumeButton'");
        target.replayButton = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.replayButton, "field 'replayButton'"), R.id.replayButton, "field 'replayButton'");
        target.tutorialButton = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.tutorialButton, "field 'tutorialButton'"), R.id.tutorialButton, "field 'tutorialButton'");
        target.exitButton = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.exitButton, "field 'exitButton'"), R.id.exitButton, "field 'exitButton'");
        target.title = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.title, "field 'title'"), R.id.title, "field 'title'");
        target.sound = (ToggleButton) finder.castView((View) finder.findRequiredView(source, R.id.sound, "field 'sound'"), R.id.sound, "field 'sound'");
        ((View) finder.findRequiredView(source, R.id.panel_pause, "method 'soundClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.soundClick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.tutorialContainer, "method 'tutorialContainerClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.tutorialContainerClick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.exitContainer, "method 'exitContainerClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.exitContainerClick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.replayContainer, "method 'replayContainerClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.replayContainerClick();
            }
        });
        ((View) finder.findRequiredView(source, R.id.resumeContainer, "method 'resumeContainerClick'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.resumeContainerClick();
            }
        });
    }

    public void unbind(T target) {
        target.root = null;
        target.resumeButton = null;
        target.replayButton = null;
        target.tutorialButton = null;
        target.exitButton = null;
        target.title = null;
        target.sound = null;
    }
}
