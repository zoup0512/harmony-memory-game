package com.cube.memorygames;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cmcm.adsdk.CMAdError;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.cube.memorygames.OfferDialog.PurchaseDialogType;
import com.cube.memorygames.SharingDialog.IabStatus;
import com.cube.memorygames.api.local.LocalDataManager;
import com.cube.memorygames.api.local.model.LocalUser;
import com.cube.memorygames.billing.IabHelper;
import com.cube.memorygames.billing.IabHelper.IabAsyncInProgressException;
import com.cube.memorygames.billing.IabHelper.OnIabPurchaseFinishedListener;
import com.cube.memorygames.billing.IabResult;
import com.cube.memorygames.billing.Purchase;
import com.cube.memorygames.billing.SkuDetails;
import com.cube.memorygames.ui.MenuAdapter;
import com.facebook.AccessToken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.memory.brain.training.games.R;
import io.branch.indexing.BranchUniversalObject;
import io.branch.indexing.BranchUniversalObject.CONTENT_INDEX_MODE;
import io.branch.referral.Branch.BranchLinkCreateListener;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;
import java.util.Map;

public class OfferDialogNew extends AppCompatDialog implements OnIabPurchaseFinishedListener {
    public static final String BANNER_URL = "http://promo.pixign.com/images/banner_secretgame1.png";
    public static final String PREFERNCES_WAS_PRO_DIALOG_SHOWN = "was_pro_dialog_shown";
    public static final String PREFERNCES_WAS_PRO_DISCOUNT_DIALOG_SHOWN = "was_pro_discount_dialog_shown";
    public static final String PREFERNCES_WAS_REMOVE_ADS_DIALOG_SHOWN = "was_remove_ads_dialog_shown";
    public static final String PREFERNCES_WAS_SECRET_GAME_DIALOG_SHOWN = "was_secret_game_dialog_shown";
    public static final long PRO_DISCOUNT_FIRST_OPEN_DELAY = 604800000;
    public static final int PRO_DISCOUNT_FIRST_OPEN_MIN_GAMES = 50;
    public static final long PRO_FIRST_OPEN_DELAY = 86400000;
    public static final int PRO_FIRST_OPEN_MIN_GAMES = 25;
    public static final long REMOVE_ADS_FIRST_OPEN_DELAY = 345600000;
    public static final int REMOVE_ADS_FIRST_OPEN_MIN_GAMES = 35;
    public static final long SECRET_GAME_DELAY = 86400000;
    public static final int SECRET_GAME_MIN_GAMES = 1;
    private Activity activity;
    private MemoryApplicationModel application = MemoryApplicationModel.getInstance();
    private String clickEvent;
    @Bind({2131624247})
    ImageView close;
    private IabHelper iabHelper;
    private IabStatus iabStatus;
    private ProgressDialog progressDialog;
    private PurchaseDialogType purchaseDialogType;
    private String successEvent;
    @Nullable
    @Bind({2131624255})
    TextView text;
    @Bind({2131624256})
    TextView upgrade;

    public OfferDialogNew(Activity activity, IabHelper iabHelper, IabStatus iabStatus, @NonNull PurchaseDialogType purchaseDialogType) {
        super(activity, R.style.GdxTheme);
        this.activity = activity;
        this.iabHelper = iabHelper;
        this.iabStatus = iabStatus;
        this.purchaseDialogType = purchaseDialogType;
        getWindow().setBackgroundDrawableResource(17170445);
        getWindow().requestFeature(1);
        String event = null;
        switch (purchaseDialogType) {
            case PURCHASE_PRO:
                setContentView((int) R.layout.dialog_offer_pro);
                event = MemoryApplicationModel.ANALYTICS_EVENT_OFFER_NEW_PRO;
                this.clickEvent = MemoryApplicationModel.ANALYTICS_EVENT_OFFER_NEW_PRO_CLICKED;
                this.successEvent = MemoryApplicationModel.ANALYTICS_EVENT_OFFER_NEW_PRO_SUCCESS;
                break;
            case PURCHASE_REMOVE_ADS:
                setContentView((int) R.layout.dialog_offer_ads);
                event = MemoryApplicationModel.ANALYTICS_EVENT_OFFER_NEW_REMOVE_ADS;
                this.clickEvent = MemoryApplicationModel.ANALYTICS_EVENT_OFFER_NEW_REMOVE_ADS_CLICKED;
                this.successEvent = MemoryApplicationModel.ANALYTICS_EVENT_OFFER_NEW_REMOVE_ADS_SUCCESS;
                break;
            case PURCHASE_UNLIMITED_ONLINE:
                setContentView((int) R.layout.dialog_offer_online);
                event = MemoryApplicationModel.ANALYTICS_EVENT_OFFER_NEW_UNLIMITED_ONLINE;
                this.clickEvent = MemoryApplicationModel.ANALYTICS_EVENT_OFFER_NEW_UNLIMITED_ONLINE_CLICKED;
                this.successEvent = MemoryApplicationModel.ANALYTICS_EVENT_OFFER_NEW_UNLIMITED_ONLINE_SUCCESS;
                break;
            case PURCHASE_PRO_DISCOUNT:
                if (getContext().getString(R.string.game_name_1).equals("Memory Grid")) {
                    setContentView((int) R.layout.dialog_offer_pro_discount_en);
                } else {
                    setContentView((int) R.layout.dialog_offer_pro_discount);
                }
                event = MemoryApplicationModel.ANALYTICS_EVENT_OFFER_NEW_PRO_DISCOUNT;
                this.clickEvent = MemoryApplicationModel.ANALYTICS_EVENT_OFFER_NEW_PRO_DISCOUNT_CLICKED;
                this.successEvent = MemoryApplicationModel.ANALYTICS_EVENT_OFFER_NEW_PRO_DISCOUNT_SUCCESS;
                break;
        }
        ButterKnife.bind((Dialog) this);
        String json = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(MenuAdapter.PREF_SKU_DETAILS, null);
        if (!TextUtils.isEmpty(json)) {
            Map<String, SkuDetails> map = (Map) new Gson().fromJson(json, new TypeToken<Map<String, SkuDetails>>() {
            }.getType());
            if (map.containsKey(purchaseDialogType.getSku())) {
                SkuDetails skuDetails = (SkuDetails) map.get(purchaseDialogType.getSku());
                this.upgrade.setText(this.upgrade.getText() + " " + skuDetails.getPrice());
                if (purchaseDialogType.equals(PurchaseDialogType.PURCHASE_UNLIMITED_ONLINE)) {
                    this.text.setText(this.text.getText() + " " + skuDetails.getPrice());
                }
            }
        }
        MemoryApplicationModel.getInstance().logEvent("OfferDialog", MemoryApplicationModel.ANALYTICS_CATEGORY_OFFERS, event);
        Answers.getInstance().logCustom(new CustomEvent(event));
    }

    @OnClick({2131624247})
    void backClick() {
        dismiss();
    }

    @OnClick({2131624256})
    void upgradeClick() {
        buyProduct(this.purchaseDialogType.getSku());
    }

    public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
        }
        if (result.isSuccess()) {
            MemoryApplicationModel.getInstance().logEvent("OfferDialog", MemoryApplicationModel.ANALYTICS_CATEGORY_OFFERS, this.successEvent);
            Answers.getInstance().logCustom(new CustomEvent(this.successEvent));
            switch (this.purchaseDialogType) {
                case PURCHASE_PRO:
                case PURCHASE_PRO_DISCOUNT:
                    handleProPurchase();
                    break;
                case PURCHASE_REMOVE_ADS:
                    handleRemoveAdsPurchase();
                    break;
                case PURCHASE_UNLIMITED_ONLINE:
                    handleUnlimitedOnlinePurchase();
                    break;
            }
            dismiss();
        }
    }

    private void buyProduct(String sku) {
        if (sku == null) {
            this.progressDialog = ProgressDialog.show(getContext(), null, "Loading...", false, false);
            new BranchUniversalObject().setCanonicalIdentifier("item/12345").setTitle(getContext().getString(R.string.app_name)).setContentDescription("").setContentImageUrl("http://promo.pixign.com/images/banner_secretgame1.png").setContentIndexingMode(CONTENT_INDEX_MODE.PUBLIC).addContentMetadata(AccessToken.USER_ID_KEY, MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser().objectId).generateShortUrl(getContext(), new LinkProperties().setFeature("sharing"), new BranchLinkCreateListener() {
                public void onLinkCreate(String url, BranchError error) {
                    if (OfferDialogNew.this.progressDialog != null) {
                        OfferDialogNew.this.progressDialog.dismiss();
                    }
                    if (error == null) {
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
                        intent.putExtra("android.intent.extra.TEXT", url);
                        intent.putExtra("android.intent.extra.SUBJECT", OfferDialogNew.this.getContext().getString(R.string.app_name));
                        OfferDialogNew.this.getContext().startActivity(Intent.createChooser(intent, null));
                        return;
                    }
                    Toast.makeText(OfferDialogNew.this.getContext(), error.getMessage(), 1).show();
                }
            });
        } else if (this.iabStatus.isIabSetupFinished()) {
            try {
                this.iabHelper.launchPurchaseFlow(this.activity, sku, CMAdError.EXTERNAL_CONFIG_ERROR, this, MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser().objectId);
                this.progressDialog = ProgressDialog.show(getContext(), null, "Loading...", false, false);
                MemoryApplicationModel.getInstance().logEvent("OfferDialog", MemoryApplicationModel.ANALYTICS_CATEGORY_OFFERS, this.clickEvent);
                Answers.getInstance().logCustom(new CustomEvent(this.clickEvent));
            } catch (IabAsyncInProgressException e) {
                if (this.progressDialog != null) {
                    this.progressDialog.dismiss();
                }
            }
        }
    }

    private void handleProPurchase() {
        Answers.getInstance().logCustom(new CustomEvent(this.purchaseDialogType.getSku()));
        LocalDataManager localDataManager = this.application.getLocalDataManager();
        LocalUser localUser = localDataManager.getLocalUser();
        localUser.vip = true;
        localUser.save();
        localDataManager.addCoinsTransaction(LocalDataManager.TYPE_PRO, 1000);
        SecretGameDialog.showDialogIfNeeded(getContext());
    }

    private void handleUnlimitedOnlinePurchase() {
        LocalUser localUser = this.application.getLocalDataManager().getLocalUser();
        localUser.unlimitedOnline = true;
        Answers.getInstance().logCustom(new CustomEvent(this.purchaseDialogType.getSku()));
        localUser.save();
    }

    private void handleRemoveAdsPurchase() {
        LocalUser localUser = this.application.getLocalDataManager().getLocalUser();
        localUser.adsRemoved = true;
        Answers.getInstance().logCustom(new CustomEvent(this.purchaseDialogType.getSku()));
        localUser.save();
    }
}
