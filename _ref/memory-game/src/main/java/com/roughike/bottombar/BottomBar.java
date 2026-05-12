package com.roughike.bottombar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.volley.DefaultRetryPolicy;
import com.roughike.bottombar.scrollsweetness.BottomNavigationBehavior;
import com.yalantis.ucrop.view.CropImageView;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.util.HashMap;

public class BottomBar extends RelativeLayout implements OnClickListener, OnLongClickListener {
    private static final long ANIMATION_DURATION = 150;
    private static final String STATE_BADGE_STATES_BUNDLE = "STATE_BADGE_STATES_BUNDLE";
    private static final String STATE_CURRENT_SELECTED_TAB = "STATE_CURRENT_SELECTED_TAB";
    private static final String TAG_BADGE = "BOTTOMBAR_BADGE_";
    private static final String TAG_BOTTOM_BAR_VIEW_ACTIVE = "BOTTOM_BAR_VIEW_ACTIVE";
    private static final String TAG_BOTTOM_BAR_VIEW_INACTIVE = "BOTTOM_BAR_VIEW_INACTIVE";
    private int mActiveShiftingItemWidth;
    private View mBackgroundOverlay;
    private View mBackgroundView;
    private HashMap<Integer, Object> mBadgeMap;
    private HashMap<Integer, Boolean> mBadgeStateMap;
    private HashMap<Integer, Integer> mColorMap;
    private Context mContext;
    private int mCurrentBackgroundColor;
    private int mCurrentTabPosition;
    private int mCustomActiveTabColor;
    private Integer mDarkBackgroundColor;
    private int mDefaultBackgroundColor;
    private boolean mDrawBehindNavBar = true;
    private int mEightDp;
    private int mFragmentContainer;
    private Object mFragmentManager;
    private boolean mIgnoreNightMode;
    private boolean mIgnoreScalingResize;
    private boolean mIgnoreShiftingResize;
    private boolean mIgnoreTabletLayout;
    private Integer mInActiveColor;
    private int mInActiveShiftingItemWidth;
    private boolean mIsComingFromRestoredState;
    private boolean mIsDarkTheme;
    private boolean mIsShiftingMode;
    private boolean mIsShy;
    private boolean mIsTabletMode;
    private ViewGroup mItemContainer;
    private BottomBarItemBase[] mItems;
    private Object mListener;
    private int mMaxFixedItemWidth;
    private int mMaxFixedTabCount = 3;
    private int mMaxInActiveShiftingItemWidth;
    private Object mMenuListener;
    private ViewGroup mOuterContainer;
    private int mPendingTextAppearance = -1;
    private Typeface mPendingTypeface;
    private View mPendingUserContentView;
    private Integer mPrimaryColor;
    private int mScreenWidth;
    private View mShadowView;
    private boolean mShouldUpdateFragmentInitially;
    private boolean mShyHeightAlreadyCalculated;
    private int mSixDp;
    private int mSixteenDp;
    private float mTabAlpha = 0.6f;
    private View mTabletRightBorder;
    private int mTenDp;
    private boolean mUseExtraOffset;
    private boolean mUseOnlyStatusBarOffset;
    private boolean mUseTopOffset = true;
    private ViewGroup mUserContentContainer;
    private Integer mWhiteColor;

    public static BottomBar attach(Activity activity, Bundle savedInstanceState) {
        BottomBar bottomBar = new BottomBar(activity);
        bottomBar.onRestoreInstanceState(savedInstanceState);
        ViewGroup contentView = (ViewGroup) activity.findViewById(16908290);
        View oldLayout = contentView.getChildAt(0);
        contentView.removeView(oldLayout);
        bottomBar.setPendingUserContentView(oldLayout);
        contentView.addView(bottomBar, 0);
        return bottomBar;
    }

    public static BottomBar attach(Activity activity, Bundle savedInstanceState, int backgroundColor, int activeIconColor, float alpha) {
        BottomBar bottomBar = new BottomBar((Context) activity, backgroundColor, activeIconColor, alpha);
        bottomBar.onRestoreInstanceState(savedInstanceState);
        ViewGroup contentView = (ViewGroup) activity.findViewById(16908290);
        View oldLayout = contentView.getChildAt(0);
        contentView.removeView(oldLayout);
        bottomBar.setPendingUserContentView(oldLayout);
        contentView.addView(bottomBar, 0);
        return bottomBar;
    }

    private void setPendingUserContentView(View oldLayout) {
        this.mPendingUserContentView = oldLayout;
    }

    public static BottomBar attach(View view, Bundle savedInstanceState) {
        BottomBar bottomBar = new BottomBar(view.getContext());
        bottomBar.onRestoreInstanceState(savedInstanceState);
        ViewGroup contentView = (ViewGroup) view.getParent();
        if (contentView != null) {
            View oldLayout = contentView.getChildAt(0);
            contentView.removeView(oldLayout);
            bottomBar.setPendingUserContentView(oldLayout);
            contentView.addView(bottomBar, 0);
        } else {
            bottomBar.setPendingUserContentView(view);
        }
        return bottomBar;
    }

    @Deprecated
    public static BottomBar attachShy(CoordinatorLayout coordinatorLayout, Bundle savedInstanceState) {
        return attachShy(coordinatorLayout, null, savedInstanceState);
    }

    public static BottomBar attachShy(CoordinatorLayout coordinatorLayout, View userContentView, Bundle savedInstanceState) {
        BottomBar bottomBar = new BottomBar(coordinatorLayout.getContext());
        bottomBar.onRestoreInstanceState(savedInstanceState);
        bottomBar.toughChildHood(ViewCompat.getFitsSystemWindows(coordinatorLayout));
        if (userContentView != null && coordinatorLayout.getContext().getResources().getBoolean(R.bool.bb_bottom_bar_is_tablet_mode)) {
            bottomBar.setPendingUserContentView(userContentView);
        }
        coordinatorLayout.addView(bottomBar);
        return bottomBar;
    }

    public void setFragmentItems(FragmentManager fragmentManager, @IdRes int containerResource, BottomBarFragment... fragmentItems) {
        if (fragmentItems.length > 0) {
            int index = 0;
            int length = fragmentItems.length;
            int i = 0;
            while (i < length) {
                BottomBarFragment fragmentItem = fragmentItems[i];
                if (fragmentItem.getFragment() != null || fragmentItem.getSupportFragment() == null) {
                    index++;
                    i++;
                } else {
                    throw new IllegalArgumentException("Conflict: cannot use android.app.FragmentManager to handle a android.support.v4.app.Fragment object at position " + index + ". If you want BottomBar to handle support Fragments, use getSupportFragment" + "Manager() instead of getFragmentManager().");
                }
            }
        }
        clearItems();
        this.mFragmentManager = fragmentManager;
        this.mFragmentContainer = containerResource;
        this.mItems = fragmentItems;
        updateItems(this.mItems);
    }

    @Deprecated
    public void setFragmentItems(android.support.v4.app.FragmentManager fragmentManager, @IdRes int containerResource, BottomBarFragment... fragmentItems) {
        if (fragmentItems.length > 0) {
            int index = 0;
            int length = fragmentItems.length;
            int i = 0;
            while (i < length) {
                BottomBarFragment fragmentItem = fragmentItems[i];
                if (fragmentItem.getSupportFragment() != null || fragmentItem.getFragment() == null) {
                    index++;
                    i++;
                } else {
                    throw new IllegalArgumentException("Conflict: cannot use android.support.v4.app.FragmentManager to handle a android.app.Fragment object at position " + index + ". If you want BottomBar to handle normal Fragments, use getFragment" + "Manager() instead of getSupportFragmentManager().");
                }
            }
        }
        clearItems();
        this.mFragmentManager = fragmentManager;
        this.mFragmentContainer = containerResource;
        this.mItems = fragmentItems;
        updateItems(this.mItems);
    }

    public void setItems(BottomBarTab... bottomBarTabs) {
        clearItems();
        this.mItems = bottomBarTabs;
        updateItems(this.mItems);
    }

    public void setItems(@MenuRes int menuRes) {
        clearItems();
        this.mItems = MiscUtils.inflateMenuFromResource((Activity) getContext(), menuRes);
        updateItems(this.mItems);
    }

    @Deprecated
    public void setItemsFromMenu(@MenuRes int menuRes, OnMenuTabSelectedListener listener) {
        clearItems();
        this.mItems = MiscUtils.inflateMenuFromResource((Activity) getContext(), menuRes);
        this.mMenuListener = listener;
        updateItems(this.mItems);
    }

    @Deprecated
    public void setItemsFromMenu(@MenuRes int menuRes, OnMenuTabClickListener listener) {
        clearItems();
        this.mItems = MiscUtils.inflateMenuFromResource((Activity) getContext(), menuRes);
        this.mMenuListener = listener;
        updateItems(this.mItems);
        if (this.mItems != null && this.mItems.length > 0 && (this.mItems instanceof BottomBarTab[])) {
            listener.onMenuTabSelected(((BottomBarTab) this.mItems[this.mCurrentTabPosition]).id);
        }
    }

    @Deprecated
    public void setOnItemSelectedListener(OnTabSelectedListener listener) {
        this.mListener = listener;
    }

    public void setOnTabClickListener(@Nullable OnTabClickListener listener) {
        this.mListener = listener;
        if (this.mListener != null && this.mItems != null && this.mItems.length > 0) {
            listener.onTabSelected(this.mCurrentTabPosition);
        }
    }

    public void setOnMenuTabClickListener(@Nullable OnMenuTabClickListener listener) {
        this.mMenuListener = listener;
        if (this.mMenuListener != null && this.mItems != null && this.mItems.length > 0 && (this.mItems instanceof BottomBarTab[])) {
            listener.onMenuTabSelected(((BottomBarTab) this.mItems[this.mCurrentTabPosition]).id);
        }
    }

    public void selectTabAtPosition(int position, boolean animate) {
        if (this.mItems == null || this.mItems.length == 0) {
            throw new UnsupportedOperationException("Can't select tab at position " + position + ". This BottomBar has no items set yet.");
        } else if (position > this.mItems.length - 1 || position < 0) {
            throw new IndexOutOfBoundsException("Can't select tab at position " + position + ". This BottomBar has no items at that position.");
        } else {
            View oldTab = this.mItemContainer.findViewWithTag(TAG_BOTTOM_BAR_VIEW_ACTIVE);
            View newTab = this.mItemContainer.getChildAt(position);
            unselectTab(oldTab, animate);
            selectTab(newTab, animate);
            updateSelectedTab(position);
            shiftingMagic(oldTab, newTab, animate);
        }
    }

    public void setDefaultTabPosition(int defaultTabPosition) {
        if (!this.mIsComingFromRestoredState) {
            if (this.mItems == null) {
                this.mCurrentTabPosition = defaultTabPosition;
            } else if (this.mItems.length == 0 || defaultTabPosition > this.mItems.length - 1 || defaultTabPosition < 0) {
                throw new IndexOutOfBoundsException("Can't set default tab at position " + defaultTabPosition + ". This BottomBar has no items at that position.");
            } else {
                selectTabAtPosition(defaultTabPosition, false);
            }
        }
    }

    public int getCurrentTabPosition() {
        return this.mCurrentTabPosition;
    }

    public void hide() {
        setBarVisibility(8);
    }

    public void show() {
        setBarVisibility(0);
    }

    public void setMaxFixedTabs(int count) {
        if (this.mItems != null) {
            throw new UnsupportedOperationException("This BottomBar already has items! You must call the setMaxFixedTabs() method before specifying any items.");
        }
        this.mMaxFixedTabCount = count;
    }

    public void useFixedMode() {
        if (this.mItems != null) {
            throw new UnsupportedOperationException("This BottomBar already has items! You must call the useFixedMode() method before specifying any items.");
        }
        this.mMaxFixedTabCount = -1;
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_CURRENT_SELECTED_TAB, this.mCurrentTabPosition);
        if (this.mBadgeMap != null && this.mBadgeMap.size() > 0) {
            if (this.mBadgeStateMap == null) {
                this.mBadgeStateMap = new HashMap();
            }
            for (Integer key : this.mBadgeMap.keySet()) {
                BottomBarBadge badgeCandidate = (BottomBarBadge) this.mOuterContainer.findViewWithTag(this.mBadgeMap.get(key));
                if (badgeCandidate != null) {
                    this.mBadgeStateMap.put(key, Boolean.valueOf(badgeCandidate.isVisible()));
                }
            }
            outState.putSerializable(STATE_BADGE_STATES_BUNDLE, this.mBadgeStateMap);
        }
        if (this.mFragmentManager != null && this.mFragmentContainer != 0 && this.mItems != null && (this.mItems instanceof BottomBarFragment[])) {
            BottomBarFragment bottomBarFragment = this.mItems[this.mCurrentTabPosition];
            if (bottomBarFragment.getFragment() != null) {
                bottomBarFragment.getFragment().onSaveInstanceState(outState);
            } else if (bottomBarFragment.getSupportFragment() != null) {
                bottomBarFragment.getSupportFragment().onSaveInstanceState(outState);
            }
        }
    }

    public void mapColorForTab(int tabPosition, int color) {
        if (this.mItems == null || this.mItems.length == 0) {
            throw new UnsupportedOperationException("You have no BottomBar Tabs set yet. Please set them first before calling the mapColorForTab method.");
        } else if (tabPosition > this.mItems.length - 1 || tabPosition < 0) {
            throw new IndexOutOfBoundsException("Cant map color for Tab index " + tabPosition + ". You have no BottomBar Tabs at that position.");
        } else if (!this.mIsDarkTheme && this.mIsShiftingMode && !this.mIsTabletMode) {
            if (this.mColorMap == null) {
                this.mColorMap = new HashMap();
            }
            if (tabPosition == this.mCurrentTabPosition && this.mCurrentBackgroundColor != color) {
                this.mCurrentBackgroundColor = color;
                this.mBackgroundView.setBackgroundColor(color);
            }
            this.mColorMap.put(Integer.valueOf(tabPosition), Integer.valueOf(color));
        }
    }

    public void mapColorForTab(int tabPosition, String color) {
        mapColorForTab(tabPosition, Color.parseColor(color));
    }

    @Deprecated
    public void useDarkTheme(boolean darkThemeEnabled) {
        this.mIsDarkTheme = darkThemeEnabled;
        useDarkTheme();
    }

    public void useDarkTheme() {
        if (!(this.mIsDarkTheme || this.mItems == null || this.mItems.length <= 0)) {
            darkThemeMagic();
            for (int i = 0; i < this.mItemContainer.getChildCount(); i++) {
                View bottomBarTab = this.mItemContainer.getChildAt(i);
                ((AppCompatImageView) bottomBarTab.findViewById(R.id.bb_bottom_bar_icon)).setColorFilter(this.mWhiteColor.intValue());
                if (i == this.mCurrentTabPosition) {
                    selectTab(bottomBarTab, false);
                } else {
                    unselectTab(bottomBarTab, false);
                }
            }
        }
        this.mIsDarkTheme = true;
    }

    public void ignoreNightMode() {
        if (this.mItems == null || this.mItems.length <= 0) {
            this.mIgnoreNightMode = true;
            return;
        }
        throw new UnsupportedOperationException("This BottomBar already has items! You must call ignoreNightMode() before setting any items.");
    }

    public void setActiveTabColor(String activeTabColor) {
        setActiveTabColor(Color.parseColor(activeTabColor));
    }

    public void setActiveTabColor(int activeTabColor) {
        this.mCustomActiveTabColor = activeTabColor;
        if (this.mItems != null && this.mItems.length > 0) {
            selectTabAtPosition(this.mCurrentTabPosition, false);
        }
    }

    public void setFixedInactiveIconColor(int iconColor) {
        this.mInActiveColor = Integer.valueOf(iconColor);
        if (this.mItems != null && this.mItems.length > 0) {
            throw new UnsupportedOperationException("This BottomBar already has items! You must call setFixedInactiveIconColor() before setting any items.");
        }
    }

    public void setShiftingIconColor(int iconColor) {
        this.mWhiteColor = Integer.valueOf(iconColor);
        if (this.mItems != null && this.mItems.length > 0) {
            throw new UnsupportedOperationException("This BottomBar already has items! You must call setShiftingIconColor() before setting any items.");
        }
    }

    public BottomBarBadge makeBadgeForTabAt(int tabPosition, String backgroundColor, int initialCount) {
        return makeBadgeForTabAt(tabPosition, Color.parseColor(backgroundColor), initialCount);
    }

    public BottomBarBadge makeBadgeForTabAt(int tabPosition, int backgroundColor, int initialCount) {
        if (this.mItems == null || this.mItems.length == 0) {
            throw new UnsupportedOperationException("You have no BottomBar Tabs set yet. Please set them first before calling the makeBadgeForTabAt() method.");
        } else if (tabPosition > this.mItems.length - 1 || tabPosition < 0) {
            throw new IndexOutOfBoundsException("Cant make a Badge for Tab index " + tabPosition + ". You have no BottomBar Tabs at that position.");
        } else {
            View tab = this.mItemContainer.getChildAt(tabPosition);
            BottomBarBadge badge = new BottomBarBadge(this.mContext, tabPosition, tab, backgroundColor);
            badge.setTag(TAG_BADGE + tabPosition);
            badge.setCount(initialCount);
            tab.setOnClickListener(new 1(this, tab));
            tab.setOnLongClickListener(new 2(this, tab));
            if (this.mBadgeMap == null) {
                this.mBadgeMap = new HashMap();
            }
            this.mBadgeMap.put(Integer.valueOf(tabPosition), badge.getTag());
            boolean canShow = true;
            if (this.mIsComingFromRestoredState && this.mBadgeStateMap != null && this.mBadgeStateMap.containsKey(Integer.valueOf(tabPosition))) {
                canShow = ((Boolean) this.mBadgeStateMap.get(Integer.valueOf(tabPosition))).booleanValue();
            }
            if (!canShow || this.mCurrentTabPosition == tabPosition || initialCount == 0) {
                badge.hide();
            } else {
                badge.show();
            }
            return badge;
        }
    }

    public void setTypeFace(String typeFacePath) {
        Typeface typeface = Typeface.createFromAsset(this.mContext.getAssets(), typeFacePath);
        if (this.mItemContainer == null || this.mItemContainer.getChildCount() <= 0) {
            this.mPendingTypeface = typeface;
            return;
        }
        for (int i = 0; i < this.mItemContainer.getChildCount(); i++) {
            ((TextView) this.mItemContainer.getChildAt(i).findViewById(R.id.bb_bottom_bar_title)).setTypeface(typeface);
        }
    }

    public void setTextAppearance(@StyleRes int resId) {
        if (this.mItemContainer == null || this.mItemContainer.getChildCount() <= 0) {
            this.mPendingTextAppearance = resId;
            return;
        }
        for (int i = 0; i < this.mItemContainer.getChildCount(); i++) {
            MiscUtils.setTextAppearance((TextView) this.mItemContainer.getChildAt(i).findViewById(R.id.bb_bottom_bar_title), resId);
        }
    }

    public void hideShadow() {
        if (this.mShadowView != null) {
            this.mShadowView.setVisibility(8);
        }
    }

    public void noNavBarGoodness() {
        if (this.mItems != null) {
            throw new UnsupportedOperationException("This BottomBar already has items! You must call noNavBarGoodness() before setting the items, preferably right after attaching it to your layout.");
        }
        this.mDrawBehindNavBar = false;
    }

    public void noTabletGoodness() {
        if (this.mItems != null) {
            throw new UnsupportedOperationException("This BottomBar already has items! You must call noTabletGoodness() before setting the items, preferably right after attaching it to your layout.");
        }
        this.mIgnoreTabletLayout = true;
    }

    public void noResizeGoodness() {
        if (this.mItems != null) {
            throw new UnsupportedOperationException("This BottomBar already has items! You must call noResizeGoodness() before setting the items, preferably right after attaching it to your layout.");
        }
        this.mIgnoreShiftingResize = true;
    }

    public void noScalingGoodness() {
        if (this.mItems != null) {
            throw new UnsupportedOperationException("This BottomBar already has items! You must call noScalingGoodness() before setting the items, preferably right after attaching it to your layout.");
        }
        this.mIgnoreScalingResize = true;
    }

    public void getBarSize(OnSizeDeterminedListener listener) {
        int sizeCandidate;
        if (this.mIsTabletMode) {
            sizeCandidate = this.mOuterContainer.getWidth();
        } else {
            sizeCandidate = this.mOuterContainer.getHeight();
        }
        if (sizeCandidate == 0) {
            this.mOuterContainer.getViewTreeObserver().addOnGlobalLayoutListener(new 3(this, listener));
        } else {
            listener.onSizeReady(sizeCandidate);
        }
    }

    public View getBar() {
        return this.mOuterContainer;
    }

    public void noTopOffset() {
        this.mUseTopOffset = false;
    }

    public void useOnlyStatusBarTopOffset() {
        this.mUseOnlyStatusBarOffset = true;
    }

    public BottomBar(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public BottomBar(Context context, int backgroundColor, int activeColor, float alpha) {
        super(context);
        this.mTabAlpha = alpha;
        this.mWhiteColor = Integer.valueOf(activeColor);
        this.mPrimaryColor = Integer.valueOf(backgroundColor);
        init(context, null, 0, 0);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.mContext = context;
        this.mDarkBackgroundColor = Integer.valueOf(ContextCompat.getColor(getContext(), R.color.bb_darkBackgroundColor));
        if (this.mWhiteColor == null) {
            this.mWhiteColor = Integer.valueOf(ContextCompat.getColor(getContext(), R.color.white));
            this.mPrimaryColor = Integer.valueOf(MiscUtils.getColor(getContext(), R.attr.colorPrimary));
            this.mInActiveColor = Integer.valueOf(ContextCompat.getColor(getContext(), R.color.bb_inActiveBottomBarItemColor));
        }
        this.mScreenWidth = MiscUtils.getScreenWidth(this.mContext);
        this.mTenDp = MiscUtils.dpToPixel(this.mContext, CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER);
        this.mSixteenDp = MiscUtils.dpToPixel(this.mContext, 16.0f);
        this.mSixDp = MiscUtils.dpToPixel(this.mContext, 6.0f);
        this.mEightDp = MiscUtils.dpToPixel(this.mContext, CloseButton.STROKE_WIDTH);
        this.mMaxFixedItemWidth = MiscUtils.dpToPixel(this.mContext, 168.0f);
        this.mMaxInActiveShiftingItemWidth = MiscUtils.dpToPixel(this.mContext, 96.0f);
    }

    private void initializeViews() {
        boolean z;
        if (this.mIgnoreTabletLayout || !this.mContext.getResources().getBoolean(R.bool.bb_bottom_bar_is_tablet_mode)) {
            z = false;
        } else {
            z = true;
        }
        this.mIsTabletMode = z;
        ViewCompat.setElevation(this, (float) MiscUtils.dpToPixel(this.mContext, CloseButton.STROKE_WIDTH));
        View rootView = inflate(this.mContext, this.mIsTabletMode ? R.layout.bb_bottom_bar_item_container_tablet : R.layout.bb_bottom_bar_item_container, this);
        this.mTabletRightBorder = rootView.findViewById(R.id.bb_tablet_right_border);
        this.mUserContentContainer = (ViewGroup) rootView.findViewById(R.id.bb_user_content_container);
        this.mShadowView = rootView.findViewById(R.id.bb_bottom_bar_shadow);
        this.mOuterContainer = (ViewGroup) rootView.findViewById(R.id.bb_bottom_bar_outer_container);
        this.mItemContainer = (ViewGroup) rootView.findViewById(R.id.bb_bottom_bar_item_container);
        this.mBackgroundView = rootView.findViewById(R.id.bb_bottom_bar_background_view);
        this.mBackgroundOverlay = rootView.findViewById(R.id.bb_bottom_bar_background_overlay);
        if (this.mIsShy && this.mIgnoreTabletLayout) {
            this.mPendingUserContentView = null;
        }
        if (this.mPendingUserContentView != null) {
            LayoutParams params = this.mPendingUserContentView.getLayoutParams();
            if (params == null) {
                params = new LayoutParams(-1, -1);
            }
            if (this.mIsTabletMode && this.mIsShy) {
                ((ViewGroup) this.mPendingUserContentView.getParent()).removeView(this.mPendingUserContentView);
            }
            this.mUserContentContainer.addView(this.mPendingUserContentView, 0, params);
            this.mPendingUserContentView = null;
        }
        if (this.mIsShy && !this.mIsTabletMode) {
            getViewTreeObserver().addOnGlobalLayoutListener(new 4(this));
        }
    }

    private void toughChildHood(boolean useExtraOffset) {
        this.mIsShy = true;
        this.mUseExtraOffset = useExtraOffset;
    }

    protected boolean isShy() {
        return this.mIsShy;
    }

    protected void shyHeightAlreadyCalculated() {
        this.mShyHeightAlreadyCalculated = true;
    }

    protected boolean useExtraOffset() {
        return this.mUseExtraOffset;
    }

    protected ViewGroup getUserContainer() {
        return this.mUserContentContainer;
    }

    protected View getOuterContainer() {
        return this.mOuterContainer;
    }

    protected boolean drawBehindNavBar() {
        return this.mDrawBehindNavBar;
    }

    protected boolean useTopOffset() {
        return this.mUseTopOffset;
    }

    protected boolean useOnlyStatusbarOffset() {
        return this.mUseOnlyStatusBarOffset;
    }

    protected void setBarVisibility(int visibility) {
        if (this.mIsShy) {
            boolean z;
            if (visibility == 0) {
                z = true;
            } else {
                z = false;
            }
            toggleShyVisibility(z);
            return;
        }
        if (this.mOuterContainer != null) {
            this.mOuterContainer.setVisibility(visibility);
        }
        if (this.mBackgroundView != null) {
            this.mBackgroundView.setVisibility(visibility);
        }
        if (this.mBackgroundOverlay != null) {
            this.mBackgroundOverlay.setVisibility(visibility);
        }
    }

    protected void toggleShyVisibility(boolean visible) {
        BottomNavigationBehavior<BottomBar> from = BottomNavigationBehavior.from(this);
        if (from != null) {
            from.setHidden(this, visible);
        }
    }

    public void onClick(View v) {
        handleClick(v);
    }

    private void handleClick(View v) {
        boolean z = false;
        if (v.getTag().equals(TAG_BOTTOM_BAR_VIEW_INACTIVE)) {
            boolean z2;
            View oldTab = findViewWithTag(TAG_BOTTOM_BAR_VIEW_ACTIVE);
            if (this.mIgnoreScalingResize) {
                z2 = false;
            } else {
                z2 = true;
            }
            unselectTab(oldTab, z2);
            if (!this.mIgnoreScalingResize) {
                z = true;
            }
            selectTab(v, z);
            shiftingMagic(oldTab, v, true);
        }
        updateSelectedTab(findItemPosition(v));
    }

    private void shiftingMagic(View oldTab, View newTab, boolean animate) {
        if (!this.mIsTabletMode && this.mIsShiftingMode && !this.mIgnoreShiftingResize) {
            if (oldTab instanceof FrameLayout) {
                oldTab = ((FrameLayout) oldTab).getChildAt(0);
            }
            if (newTab instanceof FrameLayout) {
                newTab = ((FrameLayout) newTab).getChildAt(0);
            }
            if (animate) {
                MiscUtils.resizeTab(oldTab, (float) oldTab.getWidth(), (float) this.mInActiveShiftingItemWidth);
                MiscUtils.resizeTab(newTab, (float) newTab.getWidth(), (float) this.mActiveShiftingItemWidth);
                return;
            }
            oldTab.getLayoutParams().width = this.mInActiveShiftingItemWidth;
            newTab.getLayoutParams().width = this.mActiveShiftingItemWidth;
        }
    }

    private void updateSelectedTab(int newPosition) {
        boolean notifyMenuListener;
        boolean notifyRegularListener;
        if (this.mMenuListener == null || !(this.mItems instanceof BottomBarTab[])) {
            notifyMenuListener = false;
        } else {
            notifyMenuListener = true;
        }
        if (this.mListener != null) {
            notifyRegularListener = true;
        } else {
            notifyRegularListener = false;
        }
        if (newPosition != this.mCurrentTabPosition) {
            handleBadgeVisibility(this.mCurrentTabPosition, newPosition);
            this.mCurrentTabPosition = newPosition;
            if (notifyRegularListener) {
                notifyRegularListener(this.mListener, false, this.mCurrentTabPosition);
            }
            if (notifyMenuListener) {
                notifyMenuListener(this.mMenuListener, false, ((BottomBarTab) this.mItems[this.mCurrentTabPosition]).id);
            }
            updateCurrentFragment();
            return;
        }
        if (notifyRegularListener) {
            notifyRegularListener(this.mListener, true, this.mCurrentTabPosition);
        }
        if (notifyMenuListener) {
            notifyMenuListener(this.mMenuListener, true, ((BottomBarTab) this.mItems[this.mCurrentTabPosition]).id);
        }
    }

    private void notifyRegularListener(Object listener, boolean isReselection, int position) {
        if (listener instanceof OnTabClickListener) {
            OnTabClickListener onTabClickListener = (OnTabClickListener) listener;
            if (isReselection) {
                onTabClickListener.onTabReSelected(position);
            } else {
                onTabClickListener.onTabSelected(position);
            }
        } else if (listener instanceof OnTabSelectedListener) {
            OnTabSelectedListener onTabSelectedListener = (OnTabSelectedListener) listener;
            if (!isReselection) {
                onTabSelectedListener.onItemSelected(position);
            }
        }
    }

    private void notifyMenuListener(Object listener, boolean isReselection, @IdRes int menuItemId) {
        if (listener instanceof OnMenuTabClickListener) {
            OnMenuTabClickListener onMenuTabClickListener = (OnMenuTabClickListener) listener;
            if (isReselection) {
                onMenuTabClickListener.onMenuTabReSelected(menuItemId);
            } else {
                onMenuTabClickListener.onMenuTabSelected(menuItemId);
            }
        } else if (listener instanceof OnMenuTabSelectedListener) {
            OnMenuTabSelectedListener onMenuTabSelectedListener = (OnMenuTabSelectedListener) listener;
            if (!isReselection) {
                onMenuTabSelectedListener.onMenuItemSelected(menuItemId);
            }
        }
    }

    private void handleBadgeVisibility(int oldPosition, int newPosition) {
        if (this.mBadgeMap != null) {
            if (this.mBadgeMap.containsKey(Integer.valueOf(oldPosition))) {
                BottomBarBadge oldBadge = (BottomBarBadge) this.mOuterContainer.findViewWithTag(this.mBadgeMap.get(Integer.valueOf(oldPosition)));
                if (oldBadge.getAutoShowAfterUnSelection()) {
                    oldBadge.show();
                } else {
                    oldBadge.hide();
                }
            }
            if (this.mBadgeMap.containsKey(Integer.valueOf(newPosition))) {
                BottomBarBadge newBadge = (BottomBarBadge) this.mOuterContainer.findViewWithTag(this.mBadgeMap.get(Integer.valueOf(newPosition)));
                if (newBadge.getAutoHideOnSelection()) {
                    newBadge.hide();
                }
            }
        }
    }

    public boolean onLongClick(View v) {
        return handleLongClick(v);
    }

    private boolean handleLongClick(View v) {
        if ((this.mIsShiftingMode || this.mIsTabletMode) && v.getTag().equals(TAG_BOTTOM_BAR_VIEW_INACTIVE)) {
            Toast.makeText(this.mContext, this.mItems[findItemPosition(v)].getTitle(this.mContext), 0).show();
        }
        return true;
    }

    private void updateItems(BottomBarItemBase[] bottomBarItems) {
        if (this.mItemContainer == null) {
            initializeViews();
        }
        int index = 0;
        int biggestWidth = 0;
        boolean z = this.mMaxFixedTabCount >= 0 && this.mMaxFixedTabCount < bottomBarItems.length;
        this.mIsShiftingMode = z;
        if (!(this.mIsDarkTheme || this.mIgnoreNightMode || !MiscUtils.isNightMode(this.mContext))) {
            this.mIsDarkTheme = true;
        }
        if (this.mIsDarkTheme) {
            darkThemeMagic();
        } else if (!this.mIsTabletMode && this.mIsShiftingMode) {
            int intValue = this.mPrimaryColor.intValue();
            this.mCurrentBackgroundColor = intValue;
            this.mDefaultBackgroundColor = intValue;
            this.mBackgroundView.setBackgroundColor(this.mDefaultBackgroundColor);
            if (this.mContext instanceof Activity) {
                navBarMagic((Activity) this.mContext, this);
            }
        }
        View[] viewsToAdd = new View[bottomBarItems.length];
        for (BottomBarItemBase bottomBarItemBase : bottomBarItems) {
            int layoutResource;
            if (!this.mIsShiftingMode || this.mIsTabletMode) {
                layoutResource = this.mIsTabletMode ? R.layout.bb_bottom_bar_item_fixed_tablet : R.layout.bb_bottom_bar_item_fixed;
            } else {
                layoutResource = R.layout.bb_bottom_bar_item_shifting;
            }
            View bottomBarTab = View.inflate(this.mContext, layoutResource, null);
            AppCompatImageView icon = (AppCompatImageView) bottomBarTab.findViewById(R.id.bb_bottom_bar_icon);
            icon.setImageDrawable(bottomBarItemBase.getIcon(this.mContext));
            if (!this.mIsTabletMode) {
                TextView title = (TextView) bottomBarTab.findViewById(R.id.bb_bottom_bar_title);
                title.setText(bottomBarItemBase.getTitle(this.mContext));
                if (this.mPendingTextAppearance != -1) {
                    MiscUtils.setTextAppearance(title, this.mPendingTextAppearance);
                }
                if (this.mPendingTypeface != null) {
                    title.setTypeface(this.mPendingTypeface);
                }
            }
            if (this.mIsDarkTheme || (!this.mIsTabletMode && this.mIsShiftingMode)) {
                icon.setColorFilter(this.mWhiteColor.intValue());
            }
            if (bottomBarItemBase instanceof BottomBarTab) {
                bottomBarTab.setId(((BottomBarTab) bottomBarItemBase).id);
            }
            if (index == this.mCurrentTabPosition) {
                selectTab(bottomBarTab, false);
            } else {
                unselectTab(bottomBarTab, false);
            }
            if (this.mIsTabletMode) {
                this.mItemContainer.addView(bottomBarTab);
            } else {
                if (bottomBarTab.getWidth() > biggestWidth) {
                    biggestWidth = bottomBarTab.getWidth();
                }
                viewsToAdd[index] = bottomBarTab;
            }
            bottomBarTab.setOnClickListener(this);
            bottomBarTab.setOnLongClickListener(this);
            index++;
        }
        if (!this.mIsTabletMode) {
            int proposedItemWidth = Math.min(MiscUtils.dpToPixel(this.mContext, (float) (this.mScreenWidth / bottomBarItems.length)), this.mMaxFixedItemWidth);
            this.mInActiveShiftingItemWidth = (int) (((double) proposedItemWidth) * 0.9d);
            this.mActiveShiftingItemWidth = (int) (((double) proposedItemWidth) + (((double) proposedItemWidth) * (((double) bottomBarItems.length) * 0.1d)));
            int height = Math.round(this.mContext.getResources().getDimension(R.dimen.bb_height));
            for (View bottomBarView : viewsToAdd) {
                LinearLayout.LayoutParams params;
                if (!this.mIsShiftingMode || this.mIgnoreShiftingResize) {
                    params = new LinearLayout.LayoutParams(proposedItemWidth, height);
                } else if (TAG_BOTTOM_BAR_VIEW_ACTIVE.equals(bottomBarView.getTag())) {
                    params = new LinearLayout.LayoutParams(this.mActiveShiftingItemWidth, height);
                } else {
                    params = new LinearLayout.LayoutParams(this.mInActiveShiftingItemWidth, height);
                }
                bottomBarView.setLayoutParams(params);
                this.mItemContainer.addView(bottomBarView);
            }
        }
        if (this.mPendingTextAppearance != -1) {
            this.mPendingTextAppearance = -1;
        }
        if (this.mPendingTypeface != null) {
            this.mPendingTypeface = null;
        }
    }

    private void darkThemeMagic() {
        if (this.mIsTabletMode) {
            this.mItemContainer.setBackgroundColor(this.mDarkBackgroundColor.intValue());
            this.mTabletRightBorder.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.bb_tabletRightBorderDark));
            return;
        }
        this.mBackgroundView.setBackgroundColor(this.mDarkBackgroundColor.intValue());
    }

    private void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.mCurrentTabPosition = savedInstanceState.getInt(STATE_CURRENT_SELECTED_TAB, -1);
            this.mBadgeStateMap = (HashMap) savedInstanceState.getSerializable(STATE_BADGE_STATES_BUNDLE);
            if (this.mCurrentTabPosition == -1) {
                this.mCurrentTabPosition = 0;
                Log.e("BottomBar", "You must override the Activity's onSaveInstanceState(Bundle outState) and call BottomBar.onSaveInstanceState(outState) there to restore the state properly.");
            }
            this.mIsComingFromRestoredState = true;
            this.mShouldUpdateFragmentInitially = true;
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            updateTitleBottomPadding();
        }
    }

    private void updateTitleBottomPadding() {
        if (this.mItemContainer != null) {
            int childCount = this.mItemContainer.getChildCount();
            for (int i = 0; i < childCount; i++) {
                TextView title = (TextView) this.mItemContainer.getChildAt(i).findViewById(R.id.bb_bottom_bar_title);
                if (title != null) {
                    int missingPadding = this.mTenDp - (title.getHeight() - title.getBaseline());
                    if (missingPadding > 0) {
                        title.setPadding(title.getPaddingLeft(), title.getPaddingTop(), title.getPaddingRight(), title.getPaddingBottom() + missingPadding);
                    }
                }
            }
        }
    }

    private void selectTab(View tab, boolean animate) {
        tab.setTag(TAG_BOTTOM_BAR_VIEW_ACTIVE);
        AppCompatImageView icon = (AppCompatImageView) tab.findViewById(R.id.bb_bottom_bar_icon);
        TextView title = (TextView) tab.findViewById(R.id.bb_bottom_bar_title);
        int tabPosition = findItemPosition(tab);
        if (!this.mIsShiftingMode || this.mIsTabletMode) {
            int activeColor;
            if (this.mCustomActiveTabColor != 0) {
                activeColor = this.mCustomActiveTabColor;
            } else {
                activeColor = this.mPrimaryColor.intValue();
            }
            icon.setColorFilter(activeColor);
            if (title != null) {
                title.setTextColor(activeColor);
            }
        } else {
            title.setTextColor(this.mWhiteColor.intValue());
        }
        if (this.mIsDarkTheme) {
            if (title != null) {
                ViewCompat.setAlpha(title, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            }
            ViewCompat.setAlpha(icon, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
        if (title != null) {
            if (animate) {
                ViewPropertyAnimatorCompat titleAnimator = ViewCompat.animate(title).setDuration(ANIMATION_DURATION).scaleX(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).scaleY(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                if (this.mIsShiftingMode) {
                    titleAnimator.alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                }
                titleAnimator.start();
                MiscUtils.resizePaddingTop(icon, icon.getPaddingTop(), this.mSixDp, ANIMATION_DURATION);
                if (this.mIsShiftingMode) {
                    ViewCompat.animate(icon).setDuration(ANIMATION_DURATION).alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).start();
                }
                handleBackgroundColorChange(tabPosition, tab);
                return;
            }
            ViewCompat.setScaleX(title, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            ViewCompat.setScaleY(title, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            icon.setPadding(icon.getPaddingLeft(), this.mSixDp, icon.getPaddingRight(), icon.getPaddingBottom());
            if (this.mIsShiftingMode) {
                ViewCompat.setAlpha(icon, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                ViewCompat.setAlpha(title, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            }
        }
    }

    private void unselectTab(View tab, boolean animate) {
        tab.setTag(TAG_BOTTOM_BAR_VIEW_INACTIVE);
        AppCompatImageView icon = (AppCompatImageView) tab.findViewById(R.id.bb_bottom_bar_icon);
        TextView title = (TextView) tab.findViewById(R.id.bb_bottom_bar_title);
        if (!this.mIsShiftingMode || this.mIsTabletMode) {
            int inActiveColor = (this.mIsDarkTheme ? this.mWhiteColor : this.mInActiveColor).intValue();
            icon.setColorFilter(inActiveColor);
            if (title != null) {
                title.setTextColor(inActiveColor);
            }
        }
        if (this.mIsDarkTheme) {
            if (title != null) {
                ViewCompat.setAlpha(title, this.mTabAlpha);
            }
            ViewCompat.setAlpha(icon, this.mTabAlpha);
        }
        if (title != null) {
            float scale = this.mIsShiftingMode ? 0.0f : 0.86f;
            int iconPaddingTop = this.mIsShiftingMode ? this.mSixteenDp : this.mEightDp;
            if (animate) {
                ViewPropertyAnimatorCompat titleAnimator = ViewCompat.animate(title).setDuration(ANIMATION_DURATION).scaleX(scale).scaleY(scale);
                if (this.mIsShiftingMode) {
                    titleAnimator.alpha(0.0f);
                }
                titleAnimator.start();
                MiscUtils.resizePaddingTop(icon, icon.getPaddingTop(), iconPaddingTop, ANIMATION_DURATION);
                if (this.mIsShiftingMode) {
                    ViewCompat.animate(icon).setDuration(ANIMATION_DURATION).alpha(this.mTabAlpha).start();
                    return;
                }
                return;
            }
            ViewCompat.setScaleX(title, scale);
            ViewCompat.setScaleY(title, scale);
            icon.setPadding(icon.getPaddingLeft(), iconPaddingTop, icon.getPaddingRight(), icon.getPaddingBottom());
            if (this.mIsShiftingMode) {
                ViewCompat.setAlpha(icon, this.mTabAlpha);
                ViewCompat.setAlpha(title, 0.0f);
            }
        }
    }

    private void handleBackgroundColorChange(int tabPosition, View tab) {
        if (!this.mIsDarkTheme && this.mIsShiftingMode && !this.mIsTabletMode) {
            if (this.mColorMap == null || !this.mColorMap.containsKey(Integer.valueOf(tabPosition))) {
                handleBackgroundColorChange(tab, this.mDefaultBackgroundColor);
            } else {
                handleBackgroundColorChange(tab, ((Integer) this.mColorMap.get(Integer.valueOf(tabPosition))).intValue());
            }
        }
    }

    private void handleBackgroundColorChange(View tab, int color) {
        MiscUtils.animateBGColorChange(tab, this.mBackgroundView, this.mBackgroundOverlay, color);
        this.mCurrentBackgroundColor = color;
    }

    private int findItemPosition(View viewToFind) {
        for (int i = 0; i < this.mItemContainer.getChildCount(); i++) {
            if (this.mItemContainer.getChildAt(i).equals(viewToFind)) {
                return i;
            }
        }
        return 0;
    }

    private void updateCurrentFragment() {
        if (!(this.mShouldUpdateFragmentInitially || this.mFragmentManager == null || this.mFragmentContainer == 0 || this.mItems == null || !(this.mItems instanceof BottomBarFragment[]))) {
            BottomBarFragment newFragment = this.mItems[this.mCurrentTabPosition];
            if ((this.mFragmentManager instanceof android.support.v4.app.FragmentManager) && newFragment.getSupportFragment() != null) {
                ((android.support.v4.app.FragmentManager) this.mFragmentManager).beginTransaction().replace(this.mFragmentContainer, newFragment.getSupportFragment()).commit();
            } else if ((this.mFragmentManager instanceof FragmentManager) && newFragment.getFragment() != null) {
                ((FragmentManager) this.mFragmentManager).beginTransaction().replace(this.mFragmentContainer, newFragment.getFragment()).commit();
            }
        }
        this.mShouldUpdateFragmentInitially = false;
    }

    private void clearItems() {
        if (this.mItemContainer != null) {
            this.mItemContainer.removeAllViews();
        }
        if (this.mFragmentManager != null) {
            this.mFragmentManager = null;
        }
        if (this.mFragmentContainer != 0) {
            this.mFragmentContainer = 0;
        }
        if (this.mItems != null) {
            this.mItems = null;
        }
    }

    private static void navBarMagic(Activity activity, BottomBar bottomBar) {
        Resources res = activity.getResources();
        int softMenuIdentifier = res.getIdentifier("config_showNavigationBar", "bool", AbstractSpiCall.ANDROID_CLIENT_TYPE);
        int navBarIdentifier = res.getIdentifier("navigation_bar_height", "dimen", AbstractSpiCall.ANDROID_CLIENT_TYPE);
        int navBarHeight = 0;
        if (navBarIdentifier > 0) {
            navBarHeight = res.getDimensionPixelSize(navBarIdentifier);
        }
        if (bottomBar.drawBehindNavBar() && navBarHeight != 0) {
            if (VERSION.SDK_INT >= 14 && ViewConfiguration.get(activity).hasPermanentMenuKey()) {
                return;
            }
            if (VERSION.SDK_INT >= 17 || (softMenuIdentifier > 0 && res.getBoolean(softMenuIdentifier))) {
                if (VERSION.SDK_INT >= 17) {
                    Display d = activity.getWindowManager().getDefaultDisplay();
                    DisplayMetrics realDisplayMetrics = new DisplayMetrics();
                    d.getRealMetrics(realDisplayMetrics);
                    int realHeight = realDisplayMetrics.heightPixels;
                    int realWidth = realDisplayMetrics.widthPixels;
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    d.getMetrics(displayMetrics);
                    boolean hasSoftwareKeys = realWidth - displayMetrics.widthPixels > 0 || realHeight - displayMetrics.heightPixels > 0;
                    if (!hasSoftwareKeys) {
                        return;
                    }
                }
                if (VERSION.SDK_INT >= 19 && res.getConfiguration().orientation == 1) {
                    WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                    attributes.flags |= 134217728;
                    if (bottomBar.useTopOffset()) {
                        int offset;
                        int statusBarResource = res.getIdentifier("status_bar_height", "dimen", AbstractSpiCall.ANDROID_CLIENT_TYPE);
                        if (statusBarResource > 0) {
                            offset = res.getDimensionPixelSize(statusBarResource);
                        } else {
                            offset = MiscUtils.dpToPixel(activity, 25.0f);
                        }
                        if (!bottomBar.useOnlyStatusbarOffset()) {
                            TypedValue tv = new TypedValue();
                            if (activity.getTheme().resolveAttribute(16843499, tv, true)) {
                                offset += TypedValue.complexToDimensionPixelSize(tv.data, res.getDisplayMetrics());
                            } else {
                                offset += MiscUtils.dpToPixel(activity, 56.0f);
                            }
                        }
                        bottomBar.getUserContainer().setPadding(0, offset, 0, 0);
                    }
                    bottomBar.getViewTreeObserver().addOnGlobalLayoutListener(new 5(bottomBar, bottomBar.getOuterContainer(), navBarHeight));
                }
            }
        }
    }
}
