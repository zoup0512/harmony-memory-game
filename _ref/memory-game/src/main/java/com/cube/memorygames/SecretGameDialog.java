package com.cube.memorygames;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cube.memorygames.api.local.model.LocalUser;
import com.memory.brain.training.games.R;
import com.squareup.picasso.Picasso;

public class SecretGameDialog extends Dialog {
    public static final String BANNER_URL = "http://promo.pixign.com/images/banner_secretgame1.png";
    private static final String PREF_SECRET_GAME_ALREADY_SHOWER = "secretGameAlreadyShowed";
    @Bind({2131624280})
    ImageView bannerSecretGame;

    public static void showDialogIfNeeded(Context context) {
        showDialogIfNeeded(context, null);
    }

    public static void showDialogIfNeeded(Context context, OnClickListener listener) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!preferences.getBoolean(PREF_SECRET_GAME_ALREADY_SHOWER, false)) {
            LocalUser localUser = MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser();
            if (localUser.vip || localUser.gamesUnlocked || localUser.deepLinkSent || localUser.fromDeepLink) {
                new SecretGameDialog(context).show();
                preferences.edit().putBoolean(PREF_SECRET_GAME_ALREADY_SHOWER, true).apply();
                Games.resetGames();
                if (listener != null) {
                    listener.onClick(null);
                }
            }
        }
    }

    private SecretGameDialog(Context context) {
        super(context);
        getWindow().setBackgroundDrawableResource(17170445);
        getWindow().requestFeature(1);
        setContentView(R.layout.dialog_secret_game);
        ButterKnife.bind((Dialog) this);
        Picasso.with(getContext()).load("http://promo.pixign.com/images/banner_secretgame1.png").into(this.bannerSecretGame);
    }

    @OnClick({2131624281})
    void clickOk() {
        dismiss();
    }
}
