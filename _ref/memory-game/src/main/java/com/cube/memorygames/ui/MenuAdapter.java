package com.cube.memorygames.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.cmcm.adsdk.CMAdError;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.cube.memorygames.MainMenuActivity;
import com.cube.memorygames.MemoryApplicationModel;
import com.cube.memorygames.SecretGameDialog;
import com.cube.memorygames.SharingDialog.IabStatus;
import com.cube.memorygames.TrophiesActivity;
import com.cube.memorygames.activity.ProfileActivity;
import com.cube.memorygames.api.local.LocalDataManager;
import com.cube.memorygames.api.local.model.LocalUser;
import com.cube.memorygames.billing.IabHelper;
import com.cube.memorygames.billing.IabHelper.IabAsyncInProgressException;
import com.cube.memorygames.billing.IabHelper.OnIabPurchaseFinishedListener;
import com.cube.memorygames.billing.IabResult;
import com.cube.memorygames.billing.Purchase;
import com.memory.brain.training.games.R;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends Adapter<ViewHolder> implements OnIabPurchaseFinishedListener {
    public static final String PREF_SKU_DETAILS = "skuDetailsInfo";
    public static final int PRO_COINS_AMOUNT = 1000;
    public static final String SKU_PRO_VERSION = "pro_version";
    public static final String SKU_PRO_VERSION_DISCOUNT = "pro_version05";
    public static final String SKU_REMOVE_ADS = "remove_ads";
    public static final String SKU_UNLIMITEDONLINE = "unlimitedonline";
    public static final String SKU_UNLOCK_GAMES = "unlock_games";
    private static final int TYPE_DIVIDER = 2;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_USER = 1;
    private static final String WALLPAPER_PACKAGE_NAME = "com.wallpapers.backgrounds.hd.pixign";
    OnClickListener avatarClickListener = new OnClickListener() {
        public void onClick(View view) {
            MenuAdapter.this.mainMenuActivity.startActivity(new Intent(MenuAdapter.this.mainMenuActivity, ProfileActivity.class));
        }
    };
    private IabHelper iabHelper;
    private IabStatus iabStatus;
    private List<Object> items;
    private MainMenuActivity mainMenuActivity;
    private ProgressDialog progressDialog;
    private Typeface typeface;

    public class DividerViewHolder extends ViewHolder {
        @Bind({2131624076})
        View divider;

        public DividerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind((Object) this, itemView);
        }
    }

    private static class MenuItem {
        public int iconResId;
        public OnClickListener onClickListener;
        public int textResId;
        public boolean tint;

        public MenuItem(int iconResId, int textResId, OnClickListener onClickListener) {
            this(iconResId, textResId, onClickListener, true);
        }

        public MenuItem(int iconResId, int textResId, OnClickListener onClickListener, boolean tint) {
            this.iconResId = iconResId;
            this.textResId = textResId;
            this.onClickListener = onClickListener;
            this.tint = tint;
        }
    }

    public class MenuItemViewHolder extends ViewHolder {
        @Bind({2131624031})
        ImageView icon;
        @Bind({2131624383})
        TextView name;

        public MenuItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind((Object) this, itemView);
        }
    }

    public class UserViewHolder extends ViewHolder {
        private File profileFile;
        @Bind({2131624146})
        ImageView profilePicture;
        @Bind({2131624145})
        TextView userName;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind((Object) this, itemView);
        }
    }

    public MenuAdapter(MainMenuActivity mainMenuActivity, IabHelper iabHelper, IabStatus iabStatus) {
        this.mainMenuActivity = mainMenuActivity;
        this.iabHelper = iabHelper;
        this.iabStatus = iabStatus;
        fillMenuItems();
        this.typeface = Typeface.createFromAsset(mainMenuActivity.getAssets(), "Roboto-Light.ttf");
    }

    public void fillMenuItems() {
        LocalUser localUser = MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser();
        this.items = new ArrayList();
        this.items.add(localUser);
        this.items.add(new MenuItem(R.drawable.ic_person_black_24dp, R.string.edit_profile, this.avatarClickListener));
        if (!(localUser.adsRemoved || localUser.vip)) {
            this.items.add(new MenuItem(R.drawable.ic_menu_removeads, R.string.remove_ads, new OnClickListener() {
                public void onClick(View view) {
                    MenuAdapter.this.buyProduct(MenuAdapter.SKU_REMOVE_ADS);
                }
            }));
        }
        if (!localUser.vip) {
            this.items.add(new MenuItem(R.drawable.ic_menu_proversion, R.string.pro_version, new OnClickListener() {
                public void onClick(View view) {
                    MenuAdapter.this.mainMenuActivity.startVipActivity();
                }
            }));
        }
        if (!(localUser.gamesUnlocked || localUser.vip)) {
            this.items.add(new MenuItem(R.drawable.ic_menu_unlockgames, R.string.unlock_games, new OnClickListener() {
                public void onClick(View view) {
                    MenuAdapter.this.buyProduct(MenuAdapter.SKU_UNLOCK_GAMES);
                }
            }));
        }
        this.items.add(new MenuItem(R.drawable.ic_menu_coins, R.string.get_more_coins, new OnClickListener() {
            public void onClick(View view) {
                MenuAdapter.this.mainMenuActivity.showMoneyDialog(false);
            }
        }));
        this.items.add(Integer.valueOf(0));
        this.items.add(new MenuItem(R.drawable.ic_reward, R.string.rank, new OnClickListener() {
            public void onClick(View view) {
                MenuAdapter.this.mainMenuActivity.topClick();
            }
        }));
        this.items.add(new MenuItem(R.drawable.ic_reward, R.string.your_trophies, new OnClickListener() {
            public void onClick(View view) {
                MenuAdapter.this.mainMenuActivity.startActivity(new Intent(MenuAdapter.this.mainMenuActivity, TrophiesActivity.class));
            }
        }));
    }

    private void buyProduct(String sku) {
        if (this.iabStatus.isIabSetupFinished()) {
            try {
                this.iabHelper.launchPurchaseFlow(this.mainMenuActivity, sku, CMAdError.EXTERNAL_CONFIG_ERROR, this, MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser().objectId);
                this.progressDialog = ProgressDialog.show(this.mainMenuActivity, null, "Loading...", false, false);
            } catch (IabAsyncInProgressException e) {
                if (this.progressDialog != null) {
                    this.progressDialog.dismiss();
                }
            }
        }
    }

    public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
        }
        if (!result.isFailure()) {
            String sku = purchase.getSku();
            LocalUser localUser = MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser();
            boolean z = true;
            switch (sku.hashCode()) {
                case -949882362:
                    if (sku.equals(SKU_UNLOCK_GAMES)) {
                        z = true;
                        break;
                    }
                    break;
                case -671909562:
                    if (sku.equals(SKU_PRO_VERSION)) {
                        z = true;
                        break;
                    }
                    break;
                case 1098890869:
                    if (sku.equals(SKU_REMOVE_ADS)) {
                        z = false;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    localUser.adsRemoved = true;
                    Answers.getInstance().logCustom(new CustomEvent(SKU_REMOVE_ADS));
                    break;
                case true:
                    localUser.vip = true;
                    MemoryApplicationModel.getInstance().getLocalDataManager().addCoinsTransaction(LocalDataManager.TYPE_PRO, 1000);
                    SecretGameDialog.showDialogIfNeeded(this.mainMenuActivity);
                    break;
                case true:
                    localUser.gamesUnlocked = true;
                    Answers.getInstance().logCustom(new CustomEvent(SKU_UNLOCK_GAMES));
                    SecretGameDialog.showDialogIfNeeded(this.mainMenuActivity);
                    break;
            }
            localUser.save();
            fillMenuItems();
            notifyDataSetChanged();
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == 0) {
            MenuItemViewHolder menuItemViewHolder = new MenuItemViewHolder(layoutInflater.inflate(R.layout.item_menu, parent, false));
            menuItemViewHolder.name.setTypeface(this.typeface);
            return menuItemViewHolder;
        } else if (viewType == 1) {
            ViewHolder userViewHolder = new UserViewHolder(layoutInflater.inflate(R.layout.item_menu_user, parent, false));
            userViewHolder.userName.setTypeface(this.typeface);
            return userViewHolder;
        } else if (viewType == 2) {
            return new DividerViewHolder(layoutInflater.inflate(R.layout.item_menu_divider, parent, false));
        } else {
            throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types correctly");
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        if (getItemViewType(position) == 0) {
            MenuItemViewHolder menuItemViewHolder = (MenuItemViewHolder) holder;
            MenuItem menuItem = (MenuItem) this.items.get(position);
            menuItemViewHolder.name.setText(menuItem.textResId);
            menuItemViewHolder.icon.setImageResource(menuItem.iconResId);
            menuItemViewHolder.itemView.setOnClickListener(menuItem.onClickListener);
            if (menuItem.tint) {
                menuItemViewHolder.icon.setColorFilter(-16777216);
            } else {
                menuItemViewHolder.icon.clearColorFilter();
            }
        } else if (getItemViewType(position) == 1) {
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            LocalUser localUser = (LocalUser) this.items.get(position);
            userViewHolder.userName.setText(localUser.displayName);
            userViewHolder.profilePicture.setOnClickListener(this.avatarClickListener);
            if (TextUtils.isEmpty(localUser.photoUrl)) {
                userViewHolder.profilePicture.setImageResource(R.drawable.ic_user_placeholder);
                return;
            }
            userViewHolder.profilePicture.setVisibility(0);
            int size = context.getResources().getDimensionPixelSize(R.dimen.avatar_size);
            Picasso.with(this.mainMenuActivity).load(localUser.photoUrl).resize(size, size).noFade().centerCrop().placeholder((int) R.drawable.ic_user_placeholder).error((int) R.drawable.ic_user_placeholder).into(userViewHolder.profilePicture);
        }
    }

    public int getItemViewType(int position) {
        Object item = this.items.get(position);
        if (item instanceof MenuItem) {
            return 0;
        }
        if (item instanceof LocalUser) {
            return 1;
        }
        return 2;
    }

    public int getItemCount() {
        return this.items.size();
    }
}
