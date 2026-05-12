package com.appodeal.ads;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.appodeal.ads.ao.b;
import org.nexage.sourcekit.mraid.MRAIDView;

public class as {
    static void a(InterstitialActivity interstitialActivity, o oVar, int i) {
        try {
            if (!interstitialActivity.isFinishing()) {
                interstitialActivity.finish();
                interstitialActivity.overridePendingTransition(0, 0);
            }
            if (interstitialActivity.a != null) {
                interstitialActivity.a.setVisibility(8);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        q.c(i, oVar);
    }

    static void a(VideoActivity videoActivity, b bVar, int i, ap apVar) {
        try {
            if (!videoActivity.isFinishing()) {
                videoActivity.finish();
                videoActivity.overridePendingTransition(0, 0);
            }
            if (videoActivity.a != null) {
                videoActivity.a.setVisibility(8);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        switch (bVar) {
            case NON_REWARDED:
                aj.d(i, apVar);
                return;
            case REWARDED:
                am.d(i, apVar);
                return;
            default:
                return;
        }
    }

    public static void a(InterstitialActivity interstitialActivity) {
        interstitialActivity.a = new RelativeLayout(interstitialActivity);
        interstitialActivity.a.setLayoutParams(new LayoutParams(-1, -1));
        interstitialActivity.a.setBackgroundColor(Color.parseColor("#7F000000"));
        interstitialActivity.setContentView(interstitialActivity.a);
    }

    public static void a(VideoActivity videoActivity) {
        videoActivity.a = new RelativeLayout(videoActivity);
        videoActivity.a.setLayoutParams(new LayoutParams(-1, -1));
        videoActivity.a.setBackgroundColor(Color.parseColor("#7F000000"));
        videoActivity.setContentView(videoActivity.a);
    }

    public static void b(InterstitialActivity interstitialActivity) {
        interstitialActivity.b = new ProgressBar(interstitialActivity);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        interstitialActivity.b.setLayoutParams(layoutParams);
        interstitialActivity.b.setBackgroundColor(0);
        interstitialActivity.a.addView(interstitialActivity.b);
    }

    public static void b(VideoActivity videoActivity) {
        videoActivity.b = new ProgressBar(videoActivity);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        videoActivity.b.setLayoutParams(layoutParams);
        videoActivity.b.setBackgroundColor(0);
        videoActivity.a.addView(videoActivity.b);
    }

    public static void a(View view) {
        if (view instanceof MRAIDView) {
            View progressBar = new ProgressBar(view.getContext());
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(13, -1);
            progressBar.setLayoutParams(layoutParams);
            progressBar.setBackgroundColor(0);
            progressBar.setTag("Appodeal Spinner View");
            ((MRAIDView) view).addView(progressBar);
        }
    }

    public static void b(View view) {
        if (view instanceof MRAIDView) {
            int childCount = ((MRAIDView) view).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = ((MRAIDView) view).getChildAt(i);
                Object tag = childAt.getTag();
                if (tag != null && tag.equals("Appodeal Spinner View")) {
                    childAt.setVisibility(8);
                }
            }
        }
    }
}
