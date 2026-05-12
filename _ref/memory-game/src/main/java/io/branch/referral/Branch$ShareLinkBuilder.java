package io.branch.referral;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import io.branch.referral.SharingHelper.SHARE_WITH;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class Branch$ShareLinkBuilder {
    private final Activity activity_;
    private final Branch branch_;
    private Branch$BranchLinkShareListener callback_;
    private Branch$IChannelProperties channelPropertiesCallback_;
    private String copyURlText_;
    private Drawable copyUrlIcon_;
    private String defaultURL_;
    private int dividerHeight;
    private List<String> excludeFromShareSheet;
    private List<String> includeInShareSheet;
    private Drawable moreOptionIcon_;
    private String moreOptionText_;
    private ArrayList<SHARE_WITH> preferredOptions_;
    private boolean setFullWidthStyle_;
    private String shareMsg_;
    private String shareSub_;
    private String sharingTitle;
    private View sharingTitleView;
    BranchShortLinkBuilder shortLinkBuilder_;
    private int styleResourceID_;
    private String urlCopiedMessage_;

    public Branch$ShareLinkBuilder(Activity activity, JSONObject parameters) {
        this.callback_ = null;
        this.channelPropertiesCallback_ = null;
        this.dividerHeight = -1;
        this.sharingTitle = null;
        this.sharingTitleView = null;
        this.includeInShareSheet = new ArrayList();
        this.excludeFromShareSheet = new ArrayList();
        this.activity_ = activity;
        this.branch_ = Branch.access$2200();
        this.shortLinkBuilder_ = new BranchShortLinkBuilder(activity);
        try {
            Iterator<String> keys = parameters.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                this.shortLinkBuilder_.addParameters(key, (String) parameters.get(key));
            }
        } catch (Exception e) {
        }
        this.shareMsg_ = "";
        this.callback_ = null;
        this.channelPropertiesCallback_ = null;
        this.preferredOptions_ = new ArrayList();
        this.defaultURL_ = null;
        this.moreOptionIcon_ = BranchUtil.getDrawable(activity.getApplicationContext(), 17301573);
        this.moreOptionText_ = "More...";
        this.copyUrlIcon_ = BranchUtil.getDrawable(activity.getApplicationContext(), 17301582);
        this.copyURlText_ = "Copy link";
        this.urlCopiedMessage_ = "Copied link to clipboard!";
    }

    public Branch$ShareLinkBuilder(Activity activity, BranchShortLinkBuilder shortLinkBuilder) {
        this(activity, new JSONObject());
        this.shortLinkBuilder_ = shortLinkBuilder;
    }

    public Branch$ShareLinkBuilder setMessage(String message) {
        this.shareMsg_ = message;
        return this;
    }

    public Branch$ShareLinkBuilder setSubject(String subject) {
        this.shareSub_ = subject;
        return this;
    }

    public Branch$ShareLinkBuilder addTag(String tag) {
        this.shortLinkBuilder_.addTag(tag);
        return this;
    }

    public Branch$ShareLinkBuilder addTags(ArrayList<String> tags) {
        this.shortLinkBuilder_.addTags(tags);
        return this;
    }

    public Branch$ShareLinkBuilder setFeature(String feature) {
        this.shortLinkBuilder_.setFeature(feature);
        return this;
    }

    public Branch$ShareLinkBuilder setStage(String stage) {
        this.shortLinkBuilder_.setStage(stage);
        return this;
    }

    public Branch$ShareLinkBuilder setCallback(Branch$BranchLinkShareListener callback) {
        this.callback_ = callback;
        return this;
    }

    public Branch$ShareLinkBuilder setChannelProperties(Branch$IChannelProperties channelPropertiesCallback) {
        this.channelPropertiesCallback_ = channelPropertiesCallback;
        return this;
    }

    public Branch$ShareLinkBuilder addPreferredSharingOption(SHARE_WITH preferredOption) {
        this.preferredOptions_.add(preferredOption);
        return this;
    }

    public Branch$ShareLinkBuilder addPreferredSharingOptions(ArrayList<SHARE_WITH> preferredOptions) {
        this.preferredOptions_.addAll(preferredOptions);
        return this;
    }

    public Branch$ShareLinkBuilder addParam(String key, String value) {
        try {
            this.shortLinkBuilder_.addParameters(key, value);
        } catch (Exception e) {
        }
        return this;
    }

    public Branch$ShareLinkBuilder setDefaultURL(String url) {
        this.defaultURL_ = url;
        return this;
    }

    public Branch$ShareLinkBuilder setMoreOptionStyle(Drawable icon, String label) {
        this.moreOptionIcon_ = icon;
        this.moreOptionText_ = label;
        return this;
    }

    public Branch$ShareLinkBuilder setMoreOptionStyle(int drawableIconID, int stringLabelID) {
        this.moreOptionIcon_ = BranchUtil.getDrawable(this.activity_.getApplicationContext(), drawableIconID);
        this.moreOptionText_ = this.activity_.getResources().getString(stringLabelID);
        return this;
    }

    public Branch$ShareLinkBuilder setCopyUrlStyle(Drawable icon, String label, String message) {
        this.copyUrlIcon_ = icon;
        this.copyURlText_ = label;
        this.urlCopiedMessage_ = message;
        return this;
    }

    public Branch$ShareLinkBuilder setCopyUrlStyle(int drawableIconID, int stringLabelID, int stringMessageID) {
        this.copyUrlIcon_ = BranchUtil.getDrawable(this.activity_.getApplicationContext(), drawableIconID);
        this.copyURlText_ = this.activity_.getResources().getString(stringLabelID);
        this.urlCopiedMessage_ = this.activity_.getResources().getString(stringMessageID);
        return this;
    }

    public Branch$ShareLinkBuilder setAlias(String alias) {
        this.shortLinkBuilder_.setAlias(alias);
        return this;
    }

    public Branch$ShareLinkBuilder setMatchDuration(int matchDuration) {
        this.shortLinkBuilder_.setDuration(matchDuration);
        return this;
    }

    public Branch$ShareLinkBuilder setAsFullWidthStyle(boolean setFullWidthStyle) {
        this.setFullWidthStyle_ = setFullWidthStyle;
        return this;
    }

    public Branch$ShareLinkBuilder setDividerHeight(int height) {
        this.dividerHeight = height;
        return this;
    }

    public Branch$ShareLinkBuilder setSharingTitle(String title) {
        this.sharingTitle = title;
        return this;
    }

    public Branch$ShareLinkBuilder setSharingTitle(View titleView) {
        this.sharingTitleView = titleView;
        return this;
    }

    public Branch$ShareLinkBuilder excludeFromShareSheet(@NonNull String packageName) {
        this.excludeFromShareSheet.add(packageName);
        return this;
    }

    public Branch$ShareLinkBuilder excludeFromShareSheet(@NonNull String[] packageName) {
        this.excludeFromShareSheet.addAll(Arrays.asList(packageName));
        return this;
    }

    public Branch$ShareLinkBuilder excludeFromShareSheet(@NonNull List<String> packageNames) {
        this.excludeFromShareSheet.addAll(packageNames);
        return this;
    }

    public Branch$ShareLinkBuilder includeInShareSheet(@NonNull String packageName) {
        this.includeInShareSheet.add(packageName);
        return this;
    }

    public Branch$ShareLinkBuilder includeInShareSheet(@NonNull String[] packageName) {
        this.includeInShareSheet.addAll(Arrays.asList(packageName));
        return this;
    }

    public Branch$ShareLinkBuilder includeInShareSheet(@NonNull List<String> packageNames) {
        this.includeInShareSheet.addAll(packageNames);
        return this;
    }

    public void setStyleResourceID(@StyleRes int resourceID) {
        this.styleResourceID_ = resourceID;
    }

    public void setShortLinkBuilderInternal(BranchShortLinkBuilder shortLinkBuilder) {
        this.shortLinkBuilder_ = shortLinkBuilder;
    }

    public void shareLink() {
        Branch.access$2700(Branch.access$2200(), this);
    }

    public Activity getActivity() {
        return this.activity_;
    }

    public ArrayList<SHARE_WITH> getPreferredOptions() {
        return this.preferredOptions_;
    }

    List<String> getExcludedFromShareSheet() {
        return this.excludeFromShareSheet;
    }

    List<String> getIncludedInShareSheet() {
        return this.includeInShareSheet;
    }

    public Branch getBranch() {
        return this.branch_;
    }

    public String getShareMsg() {
        return this.shareMsg_;
    }

    public String getShareSub() {
        return this.shareSub_;
    }

    public Branch$BranchLinkShareListener getCallback() {
        return this.callback_;
    }

    public Branch$IChannelProperties getChannelPropertiesCallback() {
        return this.channelPropertiesCallback_;
    }

    public String getDefaultURL() {
        return this.defaultURL_;
    }

    public Drawable getMoreOptionIcon() {
        return this.moreOptionIcon_;
    }

    public String getMoreOptionText() {
        return this.moreOptionText_;
    }

    public Drawable getCopyUrlIcon() {
        return this.copyUrlIcon_;
    }

    public String getCopyURlText() {
        return this.copyURlText_;
    }

    public String getUrlCopiedMessage() {
        return this.urlCopiedMessage_;
    }

    public BranchShortLinkBuilder getShortLinkBuilder() {
        return this.shortLinkBuilder_;
    }

    public boolean getIsFullWidthStyle() {
        return this.setFullWidthStyle_;
    }

    public int getDividerHeight() {
        return this.dividerHeight;
    }

    public String getSharingTitle() {
        return this.sharingTitle;
    }

    public View getSharingTitleView() {
        return this.sharingTitleView;
    }

    public int getStyleResourceID() {
        return this.styleResourceID_;
    }
}
