package com.cube.memorygames.ui;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDialog;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cube.memorygames.OfferDialog.PurchaseDialogType;
import com.cube.memorygames.OfferDialogSelector;
import com.cube.memorygames.SharingDialog.IabStatus;
import com.cube.memorygames.billing.IabHelper;
import com.memory.brain.training.games.R;

public class OnlineResultDialog extends AppCompatDialog {
    private static final String PREFERENCE_FREE_DIALOG_SHOWN = "preferenceFreeDialogShown";
    private Activity activity;
    @Bind({2131624271})
    TextView done;
    @Bind({2131624215})
    ImageView doodle;
    private IabHelper iabHelper;
    private IabStatus iabStatus;
    @Bind({2131624266})
    TextView newCoins;
    @Bind({2131624268})
    TextView newRating;
    private boolean showFreeOnline;
    @Bind({2131624032})
    TextView title;
    @Bind({2131624267})
    TextView totalCoins;
    @Bind({2131624270})
    TextView totalRating;

    public OnlineResultDialog(Activity activity, int doodleResId, int titleResId, int newCoinsValue, int totalCoinsValue, int newRatingValue, int totalRatingValue, boolean showFreeOnline, IabHelper iabHelper, IabStatus iabStatus) {
        super(activity);
        this.activity = activity;
        this.iabHelper = iabHelper;
        this.iabStatus = iabStatus;
        setContentView((int) R.layout.dialog_online_result);
        getWindow().setWindowAnimations(R.style.WinDialogAnimation);
        ButterKnife.bind((Dialog) this);
        this.showFreeOnline = showFreeOnline;
        Typeface typeFaceRoboto = Typeface.createFromAsset(activity.getAssets(), "Roboto-Light.ttf");
        this.title.setTypeface(typeFaceRoboto);
        this.newCoins.setTypeface(typeFaceRoboto);
        this.totalCoins.setTypeface(typeFaceRoboto);
        this.newRating.setTypeface(typeFaceRoboto);
        this.totalRating.setTypeface(typeFaceRoboto);
        this.done.setTypeface(typeFaceRoboto);
        this.title.setText(titleResId);
        this.doodle.setImageResource(doodleResId);
        this.newCoins.setText((newCoinsValue > 0 ? "+" : "") + String.valueOf(newCoinsValue));
        this.totalCoins.setText(String.valueOf(totalCoinsValue));
        this.newRating.setText((newRatingValue > 0 ? "+" : "") + String.valueOf(newRatingValue));
        this.totalRating.setText(String.valueOf(totalRatingValue));
    }

    @OnClick({2131624271})
    void doneClick() {
        dismiss();
        boolean isDialogShown = PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(PREFERENCE_FREE_DIALOG_SHOWN, false);
        if (this.showFreeOnline && !isDialogShown) {
            OfferDialogSelector.showDialog(this.activity, this.iabHelper, this.iabStatus, PurchaseDialogType.PURCHASE_UNLIMITED_ONLINE);
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(PREFERENCE_FREE_DIALOG_SHOWN, true).apply();
        }
    }
}
