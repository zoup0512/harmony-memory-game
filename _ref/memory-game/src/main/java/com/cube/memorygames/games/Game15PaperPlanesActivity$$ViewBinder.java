package com.cube.memorygames.games;

import android.view.View;
import butterknife.ButterKnife.Finder;
import com.memory.brain.training.games.R;

public class Game15PaperPlanesActivity$$ViewBinder<T extends Game15PaperPlanesActivity> extends Game1MemoryGridActivity$$ViewBinder<T> {
    public void bind(Finder finder, T target, Object source) {
        super.bind(finder, (Game1MemoryGridActivity) target, source);
        target.correctHint = (View) finder.findRequiredView(source, R.id.correct_hint, "field 'correctHint'");
    }

    public void unbind(T target) {
        super.unbind((Game1MemoryGridActivity) target);
        target.correctHint = null;
    }
}
