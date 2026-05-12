package com.cube.memorygames;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.cube.memorygames.GameListAdapter.GameHolder;
import com.memory.brain.training.games.R;

public class GameListAdapter$GameHolder$$ViewBinder<T extends GameHolder> implements ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        target.image = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.image, "field 'image'"), R.id.image, "field 'image'");
        target.name = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.name, "field 'name'"), R.id.name, "field 'name'");
        target.categoryName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.categoryName, "field 'categoryName'"), R.id.categoryName, "field 'categoryName'");
        target.maxLevel = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.max_level, "field 'maxLevel'"), R.id.max_level, "field 'maxLevel'");
        target.level = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.level, "field 'level'"), R.id.level, "field 'level'");
        target.star = (View) finder.findRequiredView(source, R.id.star, "field 'star'");
        target.currentGame = (View) finder.findRequiredView(source, R.id.currentGame, "field 'currentGame'");
        target.lockContainer = (View) finder.findRequiredView(source, R.id.lock_container, "field 'lockContainer'");
        target.starsToUnlock = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.stars_to_unlock, "field 'starsToUnlock'"), R.id.stars_to_unlock, "field 'starsToUnlock'");
        target.mGoogleLogo = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.google_play_image_button, "field 'mGoogleLogo'"), R.id.google_play_image_button, "field 'mGoogleLogo'");
        target.mUnlockAllGamesText = (LinearLayout) finder.castView((View) finder.findRequiredView(source, R.id.unlock_all_games_text, "field 'mUnlockAllGamesText'"), R.id.unlock_all_games_text, "field 'mUnlockAllGamesText'");
    }

    public void unbind(T target) {
        target.image = null;
        target.name = null;
        target.categoryName = null;
        target.maxLevel = null;
        target.level = null;
        target.star = null;
        target.currentGame = null;
        target.lockContainer = null;
        target.starsToUnlock = null;
        target.mGoogleLogo = null;
        target.mUnlockAllGamesText = null;
    }
}
