package com.cube.memorygames.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.cube.memorygames.MemoryApplicationModel;
import com.cube.memorygames.NewPuzzleGameDialog;
import com.memory.brain.training.games.R;

public class AppRater {
    private static final Long DAYS_UNTIL_PROMPT = Long.valueOf(1);
    private static final Long DAY_MILLIS = Long.valueOf(86400000);
    private static final Long GAMES_UNTIL_PROMPT_GAME248 = Long.valueOf(8);
    private static final Long LAUNCHES_UNTIL_PROMPT_NEW = Long.valueOf(2);
    private static final Long LAUNCHES_UNTIL_PROMPT_OLD = Long.valueOf(3);
    private static final String PREF248TYPE = "prefGame248PromoType";
    private static final String PREF_TYPE = "prefRateUsDialogType";
    public static final int TYPE248_AFTER_GAME = 1;
    public static final int TYPE248_BEFORE_EXIT = 2;
    public static final int TYPE248_NONE = 0;
    private static final int TYPE_NEW = 2;
    private static final int TYPE_NONE = 0;
    private static final int TYPE_OLD = 1;
    private static Editor editor;
    private static Integer game248type;
    private static SharedPreferences prefs;

    private static class LeaveFeedbackCancelListener implements OnClickListener {
        private Context context;
        private String dialogType;

        LeaveFeedbackCancelListener(Context context, String dialogType) {
            this.context = context;
            this.dialogType = dialogType;
        }

        public void onClick(View v) {
            MemoryApplicationModel.getInstance().logEvent("Rate Dialog", MemoryApplicationModel.ANALYTICS_CATEGORY_RATING, "Rate Us. Feedback No Click." + this.dialogType);
            Answers.getInstance().logCustom(new CustomEvent("Rate Us. Feedback No Click." + this.dialogType));
            if (AppRater.editor != null) {
                AppRater.editor.putLong("launch_count", 0);
                AppRater.editor.putLong("date_firstlaunch", System.currentTimeMillis());
                AppRater.editor.commit();
            }
        }
    }

    private static class LeaveFeedbackYesListener implements OnClickListener {
        private Context context;
        private String dialogType;

        LeaveFeedbackYesListener(Context context, String dialogType) {
            this.context = context;
            this.dialogType = dialogType;
        }

        public void onClick(View v) {
            MemoryApplicationModel.getInstance().logEvent("Rate Dialog", MemoryApplicationModel.ANALYTICS_CATEGORY_RATING, "Rate Us. Feedback Yes Click." + this.dialogType);
            Answers.getInstance().logCustom(new CustomEvent("Rate Us. Feedback Yes Click." + this.dialogType));
            if (AppRater.editor != null) {
                AppRater.editor.putBoolean("dontshowagain", true);
                AppRater.editor.commit();
            }
            String uriText = "mailto:apps@pixign.com?subject=" + Uri.encode(this.context.getResources().getString(R.string.app_name) + " Feedback");
            Intent intent = new Intent("android.intent.action.SENDTO");
            intent.setData(Uri.parse(uriText));
            this.context.startActivity(Intent.createChooser(intent, "Send Feedback"));
        }
    }

    private static class LikeUsNoListener implements OnClickListener {
        private Context context;
        private String dialogType;

        LikeUsNoListener(Context context, String dialogType) {
            this.context = context;
            this.dialogType = dialogType;
        }

        public void onClick(View v) {
            MemoryApplicationModel.getInstance().logEvent("Rate Dialog", MemoryApplicationModel.ANALYTICS_CATEGORY_RATING, "Rate Us. No Click." + this.dialogType);
            Answers.getInstance().logCustom(new CustomEvent("Rate Us. No Click." + this.dialogType));
            new RateDialog(this.context, R.string.dialog_feedback_title, R.string.dialog_leave_feedback_yes, R.string.dialog_leave_feedback_no, false, R.drawable.lionkid3, new LeaveFeedbackYesListener(this.context, this.dialogType), new LeaveFeedbackCancelListener(this.context, this.dialogType)).show();
        }
    }

    private static class RateUsYesListener implements OnClickListener {
        private Context context;
        private String dialogType;

        RateUsYesListener(Context context, String dialogType) {
            this.context = context;
            this.dialogType = dialogType;
        }

        public void onClick(View v) {
            MemoryApplicationModel.getInstance().logEvent("Rate Dialog", MemoryApplicationModel.ANALYTICS_CATEGORY_RATING, "Rate Us. Yes Click." + this.dialogType);
            Answers.getInstance().logCustom(new CustomEvent("Rate Us. Yes Click." + this.dialogType));
            Answers.getInstance().logCustom(new CustomEvent("Rate Us NEW. YES"));
            if (AppRater.editor != null) {
                AppRater.editor.putBoolean("dontshowagain", true);
                AppRater.editor.commit();
            }
            this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + this.context.getPackageName())));
        }
    }

    public static int getGame248PromoType(Context context) {
        if (game248type == null) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            game248type = Integer.valueOf(preferences.getInt(PREF248TYPE, 0));
            if (game248type.intValue() == 0) {
                game248type = Integer.valueOf(Math.random() < 0.5d ? 1 : 2);
                preferences.edit().putInt(PREF248TYPE, game248type.intValue()).apply();
            }
        }
        return game248type.intValue();
    }

    public static String getGame248BannerType() {
        return Math.random() < 0.5d ? NewPuzzleGameDialog.TYPE_NEW : NewPuzzleGameDialog.TYPE_OLD;
    }

    public static void app_launched(Context mContext, boolean fromStart) {
        prefs = mContext.getSharedPreferences("apprater", 0);
        editor = prefs.edit();
        Long date_firstLaunch = Long.valueOf(prefs.getLong("date_firstlaunch", 0));
        if (date_firstLaunch.longValue() == 0) {
            date_firstLaunch = Long.valueOf(System.currentTimeMillis());
            editor.putLong("date_firstlaunch", date_firstLaunch.longValue());
        }
        Long finalDate_firstLaunch = date_firstLaunch;
        if (!prefs.getBoolean("dontshowagain", false)) {
            long launch_count = prefs.getLong("launch_count", 0);
            if (fromStart) {
                launch_count++;
            }
            editor.putLong("launch_count", launch_count);
            if (launch_count >= LAUNCHES_UNTIL_PROMPT_OLD.longValue() && System.currentTimeMillis() >= finalDate_firstLaunch.longValue() + (DAYS_UNTIL_PROMPT.longValue() * DAY_MILLIS.longValue())) {
                showRateDialog(mContext, " Old");
            }
        }
        if (!prefs.getBoolean("dontshowagain248offer", false)) {
            int game248promoType = getGame248PromoType(mContext);
            if (!fromStart && game248promoType == 1 && ((long) MemoryApplicationModel.getInstance().getLocalDataManager().getGamesSessionCount()) >= GAMES_UNTIL_PROMPT_GAME248.longValue() && System.currentTimeMillis() >= finalDate_firstLaunch.longValue() + (DAYS_UNTIL_PROMPT.longValue() * DAY_MILLIS.longValue())) {
                new NewPuzzleGameDialog(mContext, getGame248BannerType()).show();
                if (editor != null) {
                    editor.putBoolean("dontshowagain248offer", true);
                    editor.commit();
                }
                MemoryApplicationModel.getInstance().logEvent("Promo248", "Promo248", "DialogShowed afterGame");
                Answers.getInstance().logCustom((CustomEvent) new CustomEvent("Promo248").putCustomAttribute("type", "afterGame"));
            }
        }
        editor.apply();
    }

    public static boolean checkGame248Offer(Activity activity) {
        prefs = activity.getSharedPreferences("apprater", 0);
        editor = prefs.edit();
        if (getGame248PromoType(activity) != 2 || prefs.getBoolean("dontshowagain248offer", false)) {
            return true;
        }
        Long date_firstLaunch = Long.valueOf(prefs.getLong("date_firstlaunch", 0));
        if (date_firstLaunch.longValue() == 0) {
            date_firstLaunch = Long.valueOf(System.currentTimeMillis());
            editor.putLong("date_firstlaunch", date_firstLaunch.longValue());
        }
        if (System.currentTimeMillis() < date_firstLaunch.longValue() + (DAYS_UNTIL_PROMPT.longValue() * DAY_MILLIS.longValue()) || ((long) MemoryApplicationModel.getInstance().getLocalDataManager().getGamesSessionCount()) < GAMES_UNTIL_PROMPT_GAME248.longValue()) {
            return true;
        }
        new NewPuzzleGameDialog(activity, getGame248BannerType()).show();
        if (editor != null) {
            editor.putBoolean("dontshowagain248offer", true);
            editor.commit();
        }
        MemoryApplicationModel.getInstance().logEvent("Promo248", "Promo248", "DialogShowed beforeExit");
        Answers.getInstance().logCustom((CustomEvent) new CustomEvent("Promo248").putCustomAttribute("type", "beforeExit"));
        return false;
    }

    private static void showRateDialog(Context context, String dialogType) {
        MemoryApplicationModel.getInstance().logEvent("Rate Dialog", MemoryApplicationModel.ANALYTICS_CATEGORY_RATING, "Rate Us. Open." + dialogType);
        Answers.getInstance().logCustom(new CustomEvent("Rate Us. Open." + dialogType));
        RateDialog likeUsDialog = new RateDialog(context, R.string.dialog_new_like_title, R.string.dialog_new_like_us_yes, R.string.dialog_new_like_us_no, true, R.drawable.lionkid2, new RateUsYesListener(context, dialogType), new LikeUsNoListener(context, dialogType));
        if (!((Activity) context).isFinishing()) {
            likeUsDialog.show();
        }
    }
}
