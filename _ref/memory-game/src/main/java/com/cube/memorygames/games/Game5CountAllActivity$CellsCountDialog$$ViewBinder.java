package com.cube.memorygames.games;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.memory.brain.training.games.R;

public class Game5CountAllActivity$CellsCountDialog$$ViewBinder<T extends CellsCountDialog> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.button1 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.button_dialog_game5_cell_count_1, "field 'button1'"), R.id.button_dialog_game5_cell_count_1, "field 'button1'");
        target.button2 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.button_dialog_game5_cell_count_2, "field 'button2'"), R.id.button_dialog_game5_cell_count_2, "field 'button2'");
        target.button3 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.button_dialog_game5_cell_count_3, "field 'button3'"), R.id.button_dialog_game5_cell_count_3, "field 'button3'");
        target.button4 = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.button_dialog_game5_cell_count_4, "field 'button4'"), R.id.button_dialog_game5_cell_count_4, "field 'button4'");
        target.panelPause = (View) finder.findRequiredView(source, R.id.panel_pause, "field 'panelPause'");
        target.title = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.title, "field 'title'"), R.id.title, "field 'title'");
    }

    public void unbind(T target) {
        target.button1 = null;
        target.button2 = null;
        target.button3 = null;
        target.button4 = null;
        target.panelPause = null;
        target.title = null;
    }
}
