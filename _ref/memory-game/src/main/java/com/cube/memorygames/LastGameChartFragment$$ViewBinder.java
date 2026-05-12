package com.cube.memorygames;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.github.mikephil.charting.charts.LineChart;
import com.memory.brain.training.games.R;

public class LastGameChartFragment$$ViewBinder<T extends LastGameChartFragment> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.max = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.max, "field 'max'"), R.id.max, "field 'max'");
        target.average = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.average, "field 'average'"), R.id.average, "field 'average'");
        target.maxValue = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.max_value, "field 'maxValue'"), R.id.max_value, "field 'maxValue'");
        target.averageValue = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.average_value, "field 'averageValue'"), R.id.average_value, "field 'averageValue'");
        target.chart = (LineChart) finder.castView((View) finder.findRequiredView(source, R.id.chart, "field 'chart'"), R.id.chart, "field 'chart'");
        target.title = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.title, "field 'title'"), R.id.title, "field 'title'");
        target.lastGames = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.last_games, "field 'lastGames'"), R.id.last_games, "field 'lastGames'");
    }

    public void unbind(T target) {
        target.max = null;
        target.average = null;
        target.maxValue = null;
        target.averageValue = null;
        target.chart = null;
        target.title = null;
        target.lastGames = null;
    }
}
