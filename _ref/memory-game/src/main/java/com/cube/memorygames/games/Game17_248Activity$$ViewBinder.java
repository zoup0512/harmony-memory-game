package com.cube.memorygames.games;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import com.memory.brain.training.games.R;

public class Game17_248Activity$$ViewBinder<T extends Game17_248Activity> extends Game1MemoryGridActivity$$ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        super.bind(finder, (Game1MemoryGridActivity) target, source);
        target.totalScoreTextView = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.level_number, "field 'totalScoreTextView'"), R.id.level_number, "field 'totalScoreTextView'");
        target.currentScoreText = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.currentScore, "field 'currentScoreText'"), R.id.currentScore, "field 'currentScoreText'");
    }

    public void unbind(T target) {
        super.unbind((Game1MemoryGridActivity) target);
        target.totalScoreTextView = null;
        target.currentScoreText = null;
    }
}
