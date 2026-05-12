package com.cube.memorygames.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.cube.memorygames.WorkoutProgressView;
import com.memory.brain.training.games.R;

public class WorkoutAdapter$UserViewHolder$$ViewBinder<T extends UserViewHolder> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.weekResults = (LinearLayout) finder.castView((View) finder.findRequiredView(source, R.id.weekResults, "field 'weekResults'"), R.id.weekResults, "field 'weekResults'");
        target.workoutInfoButton = (View) finder.findRequiredView(source, R.id.workout_info_button, "field 'workoutInfoButton'");
        target.workoutProgressView = (WorkoutProgressView) finder.castView((View) finder.findRequiredView(source, R.id.workoutProgressView, "field 'workoutProgressView'"), R.id.workoutProgressView, "field 'workoutProgressView'");
        target.iqRank = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.workout_iq_rank, "field 'iqRank'"), R.id.workout_iq_rank, "field 'iqRank'");
        target.currentIq = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.workout_current_iq, "field 'currentIq'"), R.id.workout_current_iq, "field 'currentIq'");
        target.iqToNextLevel = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.workout_iq_to_next_level, "field 'iqToNextLevel'"), R.id.workout_iq_to_next_level, "field 'iqToNextLevel'");
        target.generatingText = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.generatingText, "field 'generatingText'"), R.id.generatingText, "field 'generatingText'");
        target.generatingLayout = (View) finder.findRequiredView(source, R.id.generatingLayout, "field 'generatingLayout'");
        target.squareLoading = (SquareLoading) finder.castView((View) finder.findRequiredView(source, R.id.squareLoading, "field 'squareLoading'"), R.id.squareLoading, "field 'squareLoading'");
        target.gameViews = Finder.listOf((WorkoutGameView) finder.findRequiredView(source, R.id.game1, "field 'gameViews'"), (WorkoutGameView) finder.findRequiredView(source, R.id.game2, "field 'gameViews'"), (WorkoutGameView) finder.findRequiredView(source, R.id.game3, "field 'gameViews'"), (WorkoutGameView) finder.findRequiredView(source, R.id.game4, "field 'gameViews'"));
        target.weekViews = Finder.listOf((WorkoutWeekView) finder.findRequiredView(source, R.id.week1, "field 'weekViews'"), (WorkoutWeekView) finder.findRequiredView(source, R.id.week2, "field 'weekViews'"), (WorkoutWeekView) finder.findRequiredView(source, R.id.week3, "field 'weekViews'"), (WorkoutWeekView) finder.findRequiredView(source, R.id.week4, "field 'weekViews'"), (WorkoutWeekView) finder.findRequiredView(source, R.id.week5, "field 'weekViews'"), (WorkoutWeekView) finder.findRequiredView(source, R.id.week6, "field 'weekViews'"), (WorkoutWeekView) finder.findRequiredView(source, R.id.week7, "field 'weekViews'"));
    }

    public void unbind(T target) {
        target.weekResults = null;
        target.workoutInfoButton = null;
        target.workoutProgressView = null;
        target.iqRank = null;
        target.currentIq = null;
        target.iqToNextLevel = null;
        target.generatingText = null;
        target.generatingLayout = null;
        target.squareLoading = null;
        target.gameViews = null;
        target.weekViews = null;
    }
}
