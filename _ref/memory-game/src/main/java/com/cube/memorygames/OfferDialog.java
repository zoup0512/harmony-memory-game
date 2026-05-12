package com.cube.memorygames;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.cube.memorygames.ui.MenuAdapter;
import com.facebook.AccessToken;
import com.memory.brain.training.games.R;
import com.squareup.picasso.Picasso;
import io.branch.indexing.BranchUniversalObject;
import io.branch.indexing.BranchUniversalObject.CONTENT_INDEX_MODE;
import io.branch.referral.Branch.BranchLinkCreateListener;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;

public class OfferDialog extends Dialog {
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
    @Bind({2131624254})
    ImageView banner;
    @Bind({2131624247})
    ImageView close;
    @Bind({2131624077})
    TextView gameName;
    private ProgressDialog progressDialog;
    @Bind({2131624253})
    ImageView sales;
    @Bind({2131624255})
    TextView text;
    @Bind({2131624032})
    TextView title;
    @Bind({2131624256})
    TextView upgrade;

    public enum PurchaseDialogType {
        PURCHASE_PRO_DISCOUNT(MenuAdapter.SKU_PRO_VERSION_DISCOUNT),
        PURCHASE_REMOVE_ADS(MenuAdapter.SKU_REMOVE_ADS),
        PURCHASE_PRO(MenuAdapter.SKU_PRO_VERSION),
        PURCHASE_UNLIMITED_ONLINE(MenuAdapter.SKU_UNLIMITEDONLINE),
        SECRET_GAME(null);
        
        String sku;

        private PurchaseDialogType(String sku) {
            this.sku = sku;
        }

        public String getSku() {
            return this.sku;
        }
    }

    public OfferDialog(Activity activity) {
        super(activity);
        getWindow().setBackgroundDrawableResource(17170445);
        getWindow().requestFeature(1);
        setContentView(R.layout.dialog_offer);
        ButterKnife.bind((Dialog) this);
        this.text.setMovementMethod(new ScrollingMovementMethod());
        this.title.setText(R.string.secret_game_title);
        this.banner.setImageResource(R.drawable.lion_6);
        Picasso.with(getContext()).load("http://promo.pixign.com/images/banner_secretgame1.png").into(this.banner);
        this.sales.setVisibility(8);
        MarginLayoutParams layoutParams = (MarginLayoutParams) this.banner.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);
        layoutParams.height = getContext().getResources().getDimensionPixelOffset(R.dimen.secret_game_banner_height);
        this.banner.setScaleType(ScaleType.CENTER_INSIDE);
        this.banner.setLayoutParams(layoutParams);
        this.text.setText(R.string.secret_game_description);
        this.upgrade.setText(R.string.secret_game_button);
        String event = MemoryApplicationModel.ANALYTICS_EVENT_OFFER_OLD_SECRET_GAME;
        this.gameName.setVisibility(0);
        setCancelable(false);
        MemoryApplicationModel.getInstance().logEvent("OfferDialog", MemoryApplicationModel.ANALYTICS_CATEGORY_OFFERS, event);
        Answers.getInstance().logCustom(new CustomEvent(event));
    }

    @OnClick({2131624247})
    void backClick() {
        dismiss();
    }

    @OnClick({2131624256})
    void upgradeClick() {
        inviteUser();
    }

    private void inviteUser() {
        this.progressDialog = ProgressDialog.show(getContext(), null, "Loading...", false, false);
        new BranchUniversalObject().setCanonicalIdentifier("item/12345").setTitle(getContext().getString(R.string.app_name)).setContentDescription("").setContentImageUrl("http://promo.pixign.com/images/banner_secretgame1.png").setContentIndexingMode(CONTENT_INDEX_MODE.PUBLIC).addContentMetadata(AccessToken.USER_ID_KEY, MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser().objectId).generateShortUrl(getContext(), new LinkProperties().setFeature("sharing"), new BranchLinkCreateListener() {
            public void onLinkCreate(String url, BranchError error) {
                if (OfferDialog.this.progressDialog != null) {
                    OfferDialog.this.progressDialog.dismiss();
                }
                if (error == null) {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
                    intent.putExtra("android.intent.extra.TEXT", url);
                    intent.putExtra("android.intent.extra.SUBJECT", OfferDialog.this.getContext().getString(R.string.app_name));
                    OfferDialog.this.getContext().startActivity(Intent.createChooser(intent, null));
                    return;
                }
                Toast.makeText(OfferDialog.this.getContext(), error.getMessage(), 1).show();
            }
        });
    }
}
