package com.cube.memorygames;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatDialog;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.cube.memorygames.ui.AppRater;
import com.memory.brain.training.games.R;

public class NewPuzzleGameDialog extends AppCompatDialog {
    private static final String GAME248_PACKAGE = "com.puzzle.game248&referrer=utm_source%3Dmemory_games";
    public static final String TYPE_NEW = "New";
    public static final String TYPE_OLD = "Old";
    @Bind({2131624442})
    ImageView mBanner;
    private String mDialogType;

    public NewPuzzleGameDialog(Context context, String type) {
        super(context, R.style.GdxTheme);
        getWindow().setBackgroundDrawableResource(17170445);
        getWindow().requestFeature(1);
        setContentView((int) R.layout.new_puzzle_game_dialog);
        ButterKnife.bind((Dialog) this);
        this.mDialogType = type;
        if (type.equals(TYPE_NEW)) {
            this.mBanner.setImageResource(R.drawable.promo);
        }
    }

    @OnClick({2131624247})
    void backClick() {
        dismiss();
    }

    @OnClick({2131624443})
    void openPlayMarket() {
        openAppInGooglePlay(getContext(), this.mDialogType);
        dismiss();
    }

    public static void openAppInGooglePlay(Context context, String dialogType) {
        String type = AppRater.getGame248PromoType(context) == 1 ? "afterGame" : "beforeExit";
        MemoryApplicationModel.getInstance().logEvent("Promo248", "Promo248", dialogType + " dialog Clicked " + type);
        Answers.getInstance().logCustom((CustomEvent) ((CustomEvent) new CustomEvent("Promo248 Clicked").putCustomAttribute("bannerType", dialogType)).putCustomAttribute("type", type));
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.puzzle.game248&referrer=utm_source%3Dmemory_games")));
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.puzzle.game248&referrer=utm_source%3Dmemory_games")));
        }
    }
}
