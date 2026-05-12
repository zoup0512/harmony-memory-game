package com.mopub.mobileads;

import android.content.Context;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.mopub.common.MoPubHttpUrlConnection;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Streams;
import com.mopub.common.util.Strings;
import com.mopub.mobileads.VastResource.Type;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.network.Networking;
import com.mopub.network.TrackingRequest;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VastXmlManagerAggregator extends AsyncTask<String, Void, VastVideoConfig> {
    public static final String ADS_BY_AD_SLOT_ID = "adsBy";
    private static final double AREA_WEIGHT = 30.0d;
    private static final double ASPECT_RATIO_WEIGHT = 70.0d;
    static final int MAX_TIMES_TO_FOLLOW_VAST_REDIRECT = 10;
    private static final int MINIMUM_COMPANION_AD_HEIGHT = 250;
    private static final int MINIMUM_COMPANION_AD_WIDTH = 300;
    private static final String MOPUB = "MoPub";
    public static final String SOCIAL_ACTIONS_AD_SLOT_ID = "socialActions";
    private static final List<String> VIDEO_MIME_TYPES = Arrays.asList(new String[]{"video/mp4", "video/3gpp"});
    @NonNull
    private final Context mContext;
    private final int mScreenAreaDp;
    private final double mScreenAspectRatio;
    private int mTimesFollowedVastRedirect;
    @NonNull
    private final WeakReference<VastXmlManagerAggregatorListener> mVastXmlManagerAggregatorListener;

    VastXmlManagerAggregator(@NonNull VastXmlManagerAggregatorListener vastXmlManagerAggregatorListener, double d, int i, @NonNull Context context) {
        Preconditions.checkNotNull(vastXmlManagerAggregatorListener);
        Preconditions.checkNotNull(context);
        this.mVastXmlManagerAggregatorListener = new WeakReference(vastXmlManagerAggregatorListener);
        this.mScreenAspectRatio = d;
        this.mScreenAreaDp = i;
        this.mContext = context.getApplicationContext();
    }

    protected void onPreExecute() {
        Networking.getUserAgent(this.mContext);
    }

    protected VastVideoConfig doInBackground(@Nullable String... strArr) {
        VastVideoConfig vastVideoConfig = null;
        if (!(strArr == null || strArr.length == 0 || strArr[0] == null)) {
            try {
                vastVideoConfig = evaluateVastXmlManager(strArr[0], new ArrayList());
            } catch (Throwable e) {
                MoPubLog.d("Unable to generate VastVideoConfig.", e);
            }
        }
        return vastVideoConfig;
    }

    protected void onPostExecute(@Nullable VastVideoConfig vastVideoConfig) {
        VastXmlManagerAggregatorListener vastXmlManagerAggregatorListener = (VastXmlManagerAggregatorListener) this.mVastXmlManagerAggregatorListener.get();
        if (vastXmlManagerAggregatorListener != null) {
            vastXmlManagerAggregatorListener.onAggregationComplete(vastVideoConfig);
        }
    }

    protected void onCancelled() {
        VastXmlManagerAggregatorListener vastXmlManagerAggregatorListener = (VastXmlManagerAggregatorListener) this.mVastXmlManagerAggregatorListener.get();
        if (vastXmlManagerAggregatorListener != null) {
            vastXmlManagerAggregatorListener.onAggregationComplete(null);
        }
    }

    @Nullable
    @VisibleForTesting
    VastVideoConfig evaluateVastXmlManager(@NonNull String str, @NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(str, "vastXml cannot be null");
        Preconditions.checkNotNull(list, "errorTrackers cannot be null");
        VastXmlManager vastXmlManager = new VastXmlManager();
        try {
            vastXmlManager.parseVastXml(str);
            List<VastAdXmlManager> adXmlManagers = vastXmlManager.getAdXmlManagers();
            if (fireErrorTrackerIfNoAds(adXmlManagers, vastXmlManager, this.mContext)) {
                return null;
            }
            for (VastAdXmlManager vastAdXmlManager : adXmlManagers) {
                if (isValidSequenceNumber(vastAdXmlManager.getSequence())) {
                    VastVideoConfig evaluateInLineXmlManager;
                    VastInLineXmlManager inLineXmlManager = vastAdXmlManager.getInLineXmlManager();
                    if (inLineXmlManager != null) {
                        evaluateInLineXmlManager = evaluateInLineXmlManager(inLineXmlManager, list);
                        if (evaluateInLineXmlManager != null) {
                            populateMoPubCustomElements(vastXmlManager, evaluateInLineXmlManager);
                            return evaluateInLineXmlManager;
                        }
                    }
                    VastBaseInLineWrapperXmlManager wrapperXmlManager = vastAdXmlManager.getWrapperXmlManager();
                    if (wrapperXmlManager != null) {
                        List arrayList = new ArrayList(list);
                        arrayList.addAll(wrapperXmlManager.getErrorTrackers());
                        String evaluateWrapperRedirect = evaluateWrapperRedirect(wrapperXmlManager, arrayList);
                        if (evaluateWrapperRedirect != null) {
                            evaluateInLineXmlManager = evaluateVastXmlManager(evaluateWrapperRedirect, arrayList);
                            if (evaluateInLineXmlManager != null) {
                                evaluateInLineXmlManager.addImpressionTrackers(wrapperXmlManager.getImpressionTrackers());
                                for (VastLinearXmlManager populateLinearTrackersAndIcon : wrapperXmlManager.getLinearXmlManagers()) {
                                    populateLinearTrackersAndIcon(populateLinearTrackersAndIcon, evaluateInLineXmlManager);
                                }
                                populateVideoViewabilityTracker(wrapperXmlManager, evaluateInLineXmlManager);
                                List<VastCompanionAdXmlManager> companionAdXmlManagers = wrapperXmlManager.getCompanionAdXmlManagers();
                                if (evaluateInLineXmlManager.hasCompanionAd()) {
                                    VastCompanionAdConfig vastCompanionAd = evaluateInLineXmlManager.getVastCompanionAd(2);
                                    VastCompanionAdConfig vastCompanionAd2 = evaluateInLineXmlManager.getVastCompanionAd(1);
                                    if (!(vastCompanionAd == null || vastCompanionAd2 == null)) {
                                        for (VastCompanionAdXmlManager vastCompanionAdXmlManager : companionAdXmlManagers) {
                                            if (!vastCompanionAdXmlManager.hasResources()) {
                                                vastCompanionAd.addClickTrackers(vastCompanionAdXmlManager.getClickTrackers());
                                                vastCompanionAd.addCreativeViewTrackers(vastCompanionAdXmlManager.getCompanionCreativeViewTrackers());
                                                vastCompanionAd2.addClickTrackers(vastCompanionAdXmlManager.getClickTrackers());
                                                vastCompanionAd2.addCreativeViewTrackers(vastCompanionAdXmlManager.getCompanionCreativeViewTrackers());
                                            }
                                        }
                                    }
                                } else {
                                    evaluateInLineXmlManager.setVastCompanionAd(getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.LANDSCAPE), getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.PORTRAIT));
                                }
                                if (evaluateInLineXmlManager.getSocialActionsCompanionAds().isEmpty()) {
                                    evaluateInLineXmlManager.setSocialActionsCompanionAds(getSocialActionsCompanionAds(companionAdXmlManagers));
                                }
                                populateMoPubCustomElements(vastXmlManager, evaluateInLineXmlManager);
                                return evaluateInLineXmlManager;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
            return null;
        } catch (Throwable e) {
            MoPubLog.d("Failed to parse VAST XML", e);
            TrackingRequest.makeVastTrackingHttpRequest(list, VastErrorCode.XML_PARSING_ERROR, null, null, this.mContext);
            return null;
        }
    }

    @Nullable
    private VastVideoConfig evaluateInLineXmlManager(@NonNull VastInLineXmlManager vastInLineXmlManager, @NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(vastInLineXmlManager);
        Preconditions.checkNotNull(list);
        for (VastLinearXmlManager vastLinearXmlManager : vastInLineXmlManager.getLinearXmlManagers()) {
            String bestMediaFileUrl = getBestMediaFileUrl(vastLinearXmlManager.getMediaXmlManagers());
            if (bestMediaFileUrl != null) {
                VastVideoConfig vastVideoConfig = new VastVideoConfig();
                vastVideoConfig.addImpressionTrackers(vastInLineXmlManager.getImpressionTrackers());
                populateLinearTrackersAndIcon(vastLinearXmlManager, vastVideoConfig);
                vastVideoConfig.setClickThroughUrl(vastLinearXmlManager.getClickThroughUrl());
                vastVideoConfig.setNetworkMediaFileUrl(bestMediaFileUrl);
                List companionAdXmlManagers = vastInLineXmlManager.getCompanionAdXmlManagers();
                vastVideoConfig.setVastCompanionAd(getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.LANDSCAPE), getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.PORTRAIT));
                vastVideoConfig.setSocialActionsCompanionAds(getSocialActionsCompanionAds(companionAdXmlManagers));
                list.addAll(vastInLineXmlManager.getErrorTrackers());
                vastVideoConfig.addErrorTrackers(list);
                populateVideoViewabilityTracker(vastInLineXmlManager, vastVideoConfig);
                return vastVideoConfig;
            }
        }
        return null;
    }

    private void populateVideoViewabilityTracker(@NonNull VastBaseInLineWrapperXmlManager vastBaseInLineWrapperXmlManager, @NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(vastBaseInLineWrapperXmlManager);
        Preconditions.checkNotNull(vastVideoConfig);
        if (vastVideoConfig.getVideoViewabilityTracker() == null) {
            VastExtensionParentXmlManager vastExtensionParentXmlManager = vastBaseInLineWrapperXmlManager.getVastExtensionParentXmlManager();
            if (vastExtensionParentXmlManager != null) {
                for (VastExtensionXmlManager vastExtensionXmlManager : vastExtensionParentXmlManager.getVastExtensionXmlManagers()) {
                    if (MOPUB.equals(vastExtensionXmlManager.getType())) {
                        vastVideoConfig.setVideoViewabilityTracker(vastExtensionXmlManager.getVideoViewabilityTracker());
                        return;
                    }
                }
            }
        }
    }

    @Nullable
    private String evaluateWrapperRedirect(@NonNull VastWrapperXmlManager vastWrapperXmlManager, @NonNull List<VastTracker> list) {
        String str = null;
        String vastAdTagURI = vastWrapperXmlManager.getVastAdTagURI();
        if (vastAdTagURI != null) {
            try {
                str = followVastRedirect(vastAdTagURI);
            } catch (Throwable e) {
                MoPubLog.d("Failed to follow VAST redirect", e);
                if (!list.isEmpty()) {
                    TrackingRequest.makeVastTrackingHttpRequest(list, VastErrorCode.WRAPPER_TIMEOUT, str, str, this.mContext);
                }
            }
        }
        return str;
    }

    private void populateLinearTrackersAndIcon(@NonNull VastLinearXmlManager vastLinearXmlManager, @NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(vastLinearXmlManager, "linearXmlManager cannot be null");
        Preconditions.checkNotNull(vastVideoConfig, "vastVideoConfig cannot be null");
        vastVideoConfig.addAbsoluteTrackers(vastLinearXmlManager.getAbsoluteProgressTrackers());
        vastVideoConfig.addFractionalTrackers(vastLinearXmlManager.getFractionalProgressTrackers());
        vastVideoConfig.addPauseTrackers(vastLinearXmlManager.getPauseTrackers());
        vastVideoConfig.addResumeTrackers(vastLinearXmlManager.getResumeTrackers());
        vastVideoConfig.addCompleteTrackers(vastLinearXmlManager.getVideoCompleteTrackers());
        vastVideoConfig.addCloseTrackers(vastLinearXmlManager.getVideoCloseTrackers());
        vastVideoConfig.addSkipTrackers(vastLinearXmlManager.getVideoSkipTrackers());
        vastVideoConfig.addClickTrackers(vastLinearXmlManager.getClickTrackers());
        if (vastVideoConfig.getSkipOffsetString() == null) {
            vastVideoConfig.setSkipOffset(vastLinearXmlManager.getSkipOffset());
        }
        if (vastVideoConfig.getVastIconConfig() == null) {
            vastVideoConfig.setVastIconConfig(getBestIcon(vastLinearXmlManager.getIconXmlManagers()));
        }
    }

    private void populateMoPubCustomElements(@NonNull VastXmlManager vastXmlManager, @NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(vastXmlManager, "xmlManager cannot be null");
        Preconditions.checkNotNull(vastVideoConfig, "vastVideoConfig cannot be null");
        vastVideoConfig.addImpressionTrackers(vastXmlManager.getMoPubImpressionTrackers());
        if (vastVideoConfig.getCustomCtaText() == null) {
            vastVideoConfig.setCustomCtaText(vastXmlManager.getCustomCtaText());
        }
        if (vastVideoConfig.getCustomSkipText() == null) {
            vastVideoConfig.setCustomSkipText(vastXmlManager.getCustomSkipText());
        }
        if (vastVideoConfig.getCustomCloseIconUrl() == null) {
            vastVideoConfig.setCustomCloseIconUrl(vastXmlManager.getCustomCloseIconUrl());
        }
        if (!vastVideoConfig.isCustomForceOrientationSet()) {
            vastVideoConfig.setCustomForceOrientation(vastXmlManager.getCustomForceOrientation());
        }
    }

    private boolean fireErrorTrackerIfNoAds(@NonNull List<VastAdXmlManager> list, @NonNull VastXmlManager vastXmlManager, @NonNull Context context) {
        if (!list.isEmpty() || vastXmlManager.getErrorTracker() == null) {
            return false;
        }
        TrackingRequest.makeVastTrackingHttpRequest(Collections.singletonList(vastXmlManager.getErrorTracker()), this.mTimesFollowedVastRedirect > 0 ? VastErrorCode.NO_ADS_VAST_RESPONSE : VastErrorCode.UNDEFINED_ERROR, null, null, context);
        return true;
    }

    @Nullable
    @VisibleForTesting
    String getBestMediaFileUrl(@NonNull List<VastMediaXmlManager> list) {
        Preconditions.checkNotNull(list, "managers cannot be null");
        double d = Double.POSITIVE_INFINITY;
        String str = null;
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            VastMediaXmlManager vastMediaXmlManager = (VastMediaXmlManager) it.next();
            String type = vastMediaXmlManager.getType();
            String mediaUrl = vastMediaXmlManager.getMediaUrl();
            if (!VIDEO_MIME_TYPES.contains(type) || mediaUrl == null) {
                it.remove();
            } else {
                Integer width = vastMediaXmlManager.getWidth();
                Integer height = vastMediaXmlManager.getHeight();
                if (width != null && width.intValue() > 0 && height != null && height.intValue() > 0) {
                    String str2;
                    double d2;
                    double calculateFitness = calculateFitness(width.intValue(), height.intValue());
                    if (calculateFitness < d) {
                        str2 = mediaUrl;
                        d2 = calculateFitness;
                    } else {
                        str2 = str;
                        d2 = d;
                    }
                    d = d2;
                    str = str2;
                }
            }
        }
        return str;
    }

    @Nullable
    @VisibleForTesting
    VastCompanionAdConfig getBestCompanionAd(@NonNull List<VastCompanionAdXmlManager> list, @NonNull CompanionOrientation companionOrientation) {
        VastResource fromVastResourceXmlManager;
        VastCompanionAdXmlManager vastCompanionAdXmlManager;
        Preconditions.checkNotNull(list, "managers cannot be null");
        Preconditions.checkNotNull(companionOrientation, "orientation cannot be null");
        List<VastCompanionAdXmlManager> arrayList = new ArrayList(list);
        double d = Double.POSITIVE_INFINITY;
        VastCompanionAdXmlManager vastCompanionAdXmlManager2 = null;
        VastResource vastResource = null;
        Point point = null;
        for (Type type : Type.values()) {
            for (VastCompanionAdXmlManager vastCompanionAdXmlManager3 : arrayList) {
                Integer width = vastCompanionAdXmlManager3.getWidth();
                Integer height = vastCompanionAdXmlManager3.getHeight();
                if (width != null && width.intValue() >= 300 && height != null && height.intValue() >= 250) {
                    Point scaledDimensions = getScaledDimensions(width.intValue(), height.intValue(), type, companionOrientation);
                    fromVastResourceXmlManager = VastResource.fromVastResourceXmlManager(vastCompanionAdXmlManager3.getResourceXmlManager(), type, scaledDimensions.x, scaledDimensions.y);
                    if (fromVastResourceXmlManager != null) {
                        double calculateFitness;
                        VastCompanionAdXmlManager vastCompanionAdXmlManager4;
                        double d2;
                        Point point2;
                        VastResource vastResource2;
                        if (CompanionOrientation.PORTRAIT == companionOrientation) {
                            calculateFitness = calculateFitness(height.intValue(), width.intValue());
                        } else {
                            calculateFitness = calculateFitness(width.intValue(), height.intValue());
                        }
                        if (calculateFitness < d) {
                            vastCompanionAdXmlManager4 = vastCompanionAdXmlManager3;
                            d2 = calculateFitness;
                            point2 = scaledDimensions;
                            vastResource2 = fromVastResourceXmlManager;
                        } else {
                            point2 = point;
                            vastResource2 = vastResource;
                            vastCompanionAdXmlManager4 = vastCompanionAdXmlManager2;
                            d2 = d;
                        }
                        d = d2;
                        vastResource = vastResource2;
                        vastCompanionAdXmlManager2 = vastCompanionAdXmlManager4;
                        point = point2;
                    }
                }
            }
            if (vastCompanionAdXmlManager2 != null) {
                fromVastResourceXmlManager = vastResource;
                vastCompanionAdXmlManager = vastCompanionAdXmlManager2;
                break;
            }
        }
        fromVastResourceXmlManager = vastResource;
        vastCompanionAdXmlManager = vastCompanionAdXmlManager2;
        if (vastCompanionAdXmlManager != null) {
            return new VastCompanionAdConfig(point.x, point.y, fromVastResourceXmlManager, vastCompanionAdXmlManager.getClickThroughUrl(), vastCompanionAdXmlManager.getClickTrackers(), vastCompanionAdXmlManager.getCompanionCreativeViewTrackers());
        }
        return null;
    }

    @NonNull
    @VisibleForTesting
    Map<String, VastCompanionAdConfig> getSocialActionsCompanionAds(@NonNull List<VastCompanionAdXmlManager> list) {
        Preconditions.checkNotNull(list, "managers cannot be null");
        Map<String, VastCompanionAdConfig> hashMap = new HashMap();
        for (VastCompanionAdXmlManager vastCompanionAdXmlManager : list) {
            Integer width = vastCompanionAdXmlManager.getWidth();
            Integer height = vastCompanionAdXmlManager.getHeight();
            if (!(width == null || height == null)) {
                String adSlotId = vastCompanionAdXmlManager.getAdSlotId();
                if (ADS_BY_AD_SLOT_ID.equals(adSlotId)) {
                    if (width.intValue() >= 25) {
                        if (width.intValue() <= 75) {
                            if (height.intValue() >= 10) {
                                if (height.intValue() > 50) {
                                }
                            }
                        }
                    }
                } else if (SOCIAL_ACTIONS_AD_SLOT_ID.equals(adSlotId) && width.intValue() >= 50 && width.intValue() <= CtaButton.WIDTH_DIPS && height.intValue() >= 10) {
                    if (height.intValue() > 50) {
                    }
                }
                VastResource fromVastResourceXmlManager = VastResource.fromVastResourceXmlManager(vastCompanionAdXmlManager.getResourceXmlManager(), Type.HTML_RESOURCE, width.intValue(), height.intValue());
                if (fromVastResourceXmlManager != null) {
                    hashMap.put(adSlotId, new VastCompanionAdConfig(width.intValue(), height.intValue(), fromVastResourceXmlManager, vastCompanionAdXmlManager.getClickThroughUrl(), vastCompanionAdXmlManager.getClickTrackers(), vastCompanionAdXmlManager.getCompanionCreativeViewTrackers()));
                }
            }
        }
        return hashMap;
    }

    @NonNull
    @VisibleForTesting
    Point getScaledDimensions(int i, int i2, Type type, CompanionOrientation companionOrientation) {
        int max;
        Point point = new Point(i, i2);
        Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        int dipsToIntPixels = Dips.dipsToIntPixels((float) i, this.mContext);
        int dipsToIntPixels2 = Dips.dipsToIntPixels((float) i2, this.mContext);
        if (CompanionOrientation.LANDSCAPE == companionOrientation) {
            max = Math.max(width, height);
            height = Math.min(width, height);
            width = max;
            max = height;
        } else {
            max = Math.min(width, height);
            height = Math.max(width, height);
            width = max;
            max = height;
        }
        if (dipsToIntPixels <= width - 16 && dipsToIntPixels2 <= max - 16) {
            return point;
        }
        Point point2 = new Point();
        if (Type.HTML_RESOURCE == type) {
            point2.x = Math.min(width, dipsToIntPixels);
            point2.y = Math.min(max, dipsToIntPixels2);
        } else {
            float f = ((float) dipsToIntPixels) / ((float) width);
            float f2 = ((float) dipsToIntPixels2) / ((float) max);
            if (f >= f2) {
                point2.x = width;
                point2.y = (int) (((float) dipsToIntPixels2) / f);
            } else {
                point2.x = (int) (((float) dipsToIntPixels) / f2);
                point2.y = max;
            }
        }
        point2.x -= 16;
        point2.y -= 16;
        if (point2.x < 0 || point2.y < 0) {
            return point;
        }
        point2.x = Dips.pixelsToIntDips((float) point2.x, this.mContext);
        point2.y = Dips.pixelsToIntDips((float) point2.y, this.mContext);
        return point2;
    }

    @Nullable
    @VisibleForTesting
    VastIconConfig getBestIcon(@NonNull List<VastIconXmlManager> list) {
        Preconditions.checkNotNull(list, "managers cannot be null");
        List<VastIconXmlManager> arrayList = new ArrayList(list);
        for (Type type : Type.values()) {
            for (VastIconXmlManager vastIconXmlManager : arrayList) {
                Integer width = vastIconXmlManager.getWidth();
                Integer height = vastIconXmlManager.getHeight();
                if (width != null && width.intValue() > 0 && width.intValue() <= 300 && height != null && height.intValue() > 0 && height.intValue() <= 300) {
                    VastResource fromVastResourceXmlManager = VastResource.fromVastResourceXmlManager(vastIconXmlManager.getResourceXmlManager(), type, width.intValue(), height.intValue());
                    if (fromVastResourceXmlManager != null) {
                        return new VastIconConfig(vastIconXmlManager.getWidth().intValue(), vastIconXmlManager.getHeight().intValue(), vastIconXmlManager.getOffsetMS(), vastIconXmlManager.getDurationMS(), fromVastResourceXmlManager, vastIconXmlManager.getClickTrackingUris(), vastIconXmlManager.getClickThroughUri(), vastIconXmlManager.getViewTrackingUris());
                    }
                }
            }
        }
        return null;
    }

    private double calculateFitness(int i, int i2) {
        return (Math.abs(Math.log((((double) i) / ((double) i2)) / this.mScreenAspectRatio)) * ASPECT_RATIO_WEIGHT) + (Math.abs(Math.log(((double) (i * i2)) / ((double) this.mScreenAreaDp))) * AREA_WEIGHT);
    }

    static boolean isValidSequenceNumber(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            if (Integer.parseInt(str) >= 2) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    @Nullable
    private String followVastRedirect(@NonNull String str) {
        Closeable bufferedInputStream;
        Throwable th;
        String str2 = null;
        Preconditions.checkNotNull(str);
        if (this.mTimesFollowedVastRedirect < 10) {
            this.mTimesFollowedVastRedirect++;
            HttpURLConnection httpUrlConnection;
            try {
                httpUrlConnection = MoPubHttpUrlConnection.getHttpUrlConnection(str);
                try {
                    bufferedInputStream = new BufferedInputStream(httpUrlConnection.getInputStream());
                    try {
                        str2 = Strings.fromStream(bufferedInputStream);
                        Streams.closeStream(bufferedInputStream);
                        if (httpUrlConnection != null) {
                            httpUrlConnection.disconnect();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        Streams.closeStream(bufferedInputStream);
                        if (httpUrlConnection != null) {
                            httpUrlConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    bufferedInputStream = null;
                    th = th4;
                    Streams.closeStream(bufferedInputStream);
                    if (httpUrlConnection != null) {
                        httpUrlConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th32) {
                httpUrlConnection = null;
                th = th32;
                bufferedInputStream = null;
                Streams.closeStream(bufferedInputStream);
                if (httpUrlConnection != null) {
                    httpUrlConnection.disconnect();
                }
                throw th;
            }
        }
        return str2;
    }

    @Deprecated
    @VisibleForTesting
    void setTimesFollowedVastRedirect(int i) {
        this.mTimesFollowedVastRedirect = i;
    }
}
