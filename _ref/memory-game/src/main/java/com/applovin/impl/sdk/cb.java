package com.applovin.impl.sdk;

import com.cube.memorygames.games.Game1MemoryGridActivity;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class cb {
    public static final cd A = a("ad_preload_enabled", Boolean.valueOf(true));
    public static final cd B = a("ad_resource_caching_enabled", Boolean.valueOf(true));
    public static final cd C = a("resource_cache_prefix", "https://vid.applovin.com/,https://pdn.applovin.com/,https://img.applovin.com/,https://d.applovin.com/,https://assets.applovin.com/,https://cdnjs.cloudflare.com/,http://vid.applovin.com/,http://pdn.applovin.com/,http://img.applovin.com/,http://d.applovin.com/,http://assets.applovin.com/,http://cdnjs.cloudflare.com/");
    public static final cd D = a("ad_auto_preload_sizes", "BANNER,INTER");
    public static final cd E = a("ad_auto_preload_incent", Boolean.valueOf(true));
    public static final cd F = a("is_tracking_enabled", Boolean.valueOf(true));
    public static final cd G = a("force_back_button_enabled_always", Boolean.valueOf(false));
    public static final cd H = a("countdown_color", "#C8FFFFFF");
    public static final cd I = a("close_fade_in_time", Integer.valueOf(Game1MemoryGridActivity.START_ANIMATION_DURATION));
    public static final cd J = a("show_close_on_exit", Boolean.valueOf(true));
    public static final cd K = a("text_incent_prompt_title", "Earn a Reward");
    public static final cd L = a("text_incent_prompt_body", "Would you like to watch a video for a reward?");
    public static final cd M = a("text_incent_prompt_yes_option", "Watch Now");
    public static final cd N = a("text_incent_prompt_no_option", "No Thanks");
    public static final cd O = a("text_incent_completion_title", "Video Reward");
    public static final cd P = a("text_incent_completion_body_success", "You have earned a reward!");
    public static final cd Q = a("text_incent_completion_body_quota_exceeded", "You have already earned the maximum reward for today.");
    public static final cd R = a("text_incent_completion_body_reward_rejected", "Your reward was rejected.");
    public static final cd S = a("text_incent_completion_body_network_failure", "We were unable to contact the rewards server. Please try again later.");
    public static final cd T = a("text_incent_completion_close_option", "Okay");
    public static final cd U = a("incent_warning_enabled", Boolean.valueOf(false));
    public static final cd V = a("text_incent_warning_title", "Attention!");
    public static final cd W = a("text_incent_warning_body", "You won’t get your reward if the video hasn’t finished.");
    public static final cd X = a("text_incent_warning_close_option", "Close");
    public static final cd Y = a("text_incent_warning_continue_option", "Keep Watching");
    public static final cd Z = a("show_incent_prepopup", Boolean.valueOf(true));
    public static final cd a = a("is_disabled", Boolean.valueOf(false));
    public static final cd aA = a("draw_countdown_clock", Boolean.valueOf(true));
    public static final cd aB = a("countdown_clock_size", Integer.valueOf(32));
    public static final cd aC = a("countdown_clock_stroke_size", Integer.valueOf(4));
    public static final cd aD = a("countdown_clock_text_size", Integer.valueOf(28));
    public static final cd aE = a("ad_auto_preload_native", Boolean.valueOf(true));
    public static final cd aF = a("widget_fail_on_slot_count_diff", Boolean.valueOf(true));
    public static final cd aG = a("video_zero_length_as_computed", Boolean.valueOf(false));
    public static final cd aH = a("video_countdown_clock_margin", Integer.valueOf(10));
    public static final cd aI = a("video_countdown_clock_gravity", Integer.valueOf(83));
    public static final cd aJ = a("preload_capacity_widget", Integer.valueOf(1));
    public static final cd aK = a("widget_latch_timeout_ms", Integer.valueOf(500));
    public static final cd aL = a("android_gc_on_widget_detach", Boolean.valueOf(true));
    public static final cd aM = a("lhs_close_button_video", Boolean.valueOf(false));
    public static final cd aN = a("lhs_close_button_graphic", Boolean.valueOf(false));
    public static final cd aO = a("lhs_skip_button", Boolean.valueOf(true));
    public static final cd aP = a("countdown_toggleable", Boolean.valueOf(false));
    public static final cd aQ = a("native_batch_precache_count", Integer.valueOf(1));
    public static final cd aR = a("mute_controls_enabled", Boolean.valueOf(false));
    public static final cd aS = a("allow_user_muting", Boolean.valueOf(true));
    public static final cd aT = a("mute_button_size", Integer.valueOf(32));
    public static final cd aU = a("mute_button_margin", Integer.valueOf(10));
    public static final cd aV = a("mute_button_gravity", Integer.valueOf(85));
    public static final cd aW = a("qq", Boolean.valueOf(false));
    public static final cd aX = a("hw_accelerate_webviews", Boolean.valueOf(false));
    public static final cd aY = a("mute_videos", Boolean.valueOf(false));
    public static final cd aZ = a("show_mute_by_default", Boolean.valueOf(false));
    public static final cd aa = a("show_incent_postpopup", Boolean.valueOf(true));
    public static final cd ab = a("preload_capacity_banner", Integer.valueOf(1));
    public static final cd ac = a("preload_capacity_mrec", Integer.valueOf(1));
    public static final cd ad = a("preload_capacity_inter", Integer.valueOf(1));
    public static final cd ae = a("preload_capacity_leader", Integer.valueOf(1));
    public static final cd af = a("preload_capacity_incent", Integer.valueOf(2));
    public static final cd ag = a("dismiss_video_on_error", Boolean.valueOf(true));
    public static final cd ah = a("precache_delimiters", ")]',");
    public static final cd ai = a("close_button_size_graphic", Integer.valueOf(27));
    public static final cd aj = a("close_button_size_video", Integer.valueOf(30));
    public static final cd ak = a("close_button_top_margin_graphic", Integer.valueOf(10));
    public static final cd al = a("close_button_right_margin_graphic", Integer.valueOf(10));
    public static final cd am = a("close_button_top_margin_video", Integer.valueOf(8));
    public static final cd an = a("close_button_right_margin_video", Integer.valueOf(4));
    public static final cd ao = a("force_back_button_enabled_poststitial", Boolean.valueOf(false));
    public static final cd ap = a("force_back_button_enabled_close_button", Boolean.valueOf(false));
    public static final cd aq = a("close_button_touch_area", Integer.valueOf(0));
    public static final cd ar = a("is_video_skippable", Boolean.valueOf(false));
    public static final cd as = a("cache_cleanup_enabled", Boolean.valueOf(false));
    public static final cd at = a("cache_file_ttl_seconds", Long.valueOf(86400));
    public static final cd au = a("cache_max_size_mb", Integer.valueOf(-1));
    public static final cd av = a("preload_merge_init_tasks_incent", Boolean.valueOf(true));
    public static final cd aw = a("preload_merge_init_tasks_inter", Boolean.valueOf(true));
    public static final cd ax = a("submit_postback_timeout", Integer.valueOf(10000));
    public static final cd ay = a("submit_postback_retries", Integer.valueOf(10));
    public static final cd az = a("widget_imp_tracking_delay", Integer.valueOf(2000));
    public static final cd b = a("honor_publisher_settings", Boolean.valueOf(true));
    public static final cd ba = a("mute_with_user_settings", Boolean.valueOf(true));
    public static final cd bb = a("event_tracking_endpoint", "http://rt.applovin.com/pix");
    public static final cd bc = a("top_level_events", "landing,checkout,iap");
    public static final cd bd = a("events_enabled", Boolean.valueOf(true));
    public static final cd be = a("force_ssl", Boolean.valueOf(false));
    public static final cd bf = a("postback_service_max_queue_size", Integer.valueOf(100));
    public static final cd bg = a("max_postback_attempts", Integer.valueOf(3));
    public static final cd bh = a("click_overlay_enabled", Boolean.valueOf(false));
    public static final cd bi = a("click_overlay_color", "#66000000");
    public static final cd bj = a("click_tracking_retry_count", Integer.valueOf(3));
    public static final cd bk = a("click_tracking_retry_delay", Integer.valueOf(2000));
    public static final cd bl = a("click_tracking_timeout", Integer.valueOf(10000));
    public static final cd bm = a("android_click_spinner_size", Integer.valueOf(50));
    public static final cd bn = a("android_click_spinner_style", Integer.valueOf(16973853));
    public static final cd bo = a("android_dismiss_inters_on_click", Boolean.valueOf(false));
    public static final cd bp = a("android_require_external_storage_permission", Boolean.valueOf(true));
    public static final cd bq = a("android_drop_nomedia", Boolean.valueOf(true));
    public static final cd br = a("native_auto_cache_preload_resources", Boolean.valueOf(true));
    public static final cd bs = a("video_immersive_mode_enabled", Boolean.valueOf(false));
    public static final cd bt = a("sanitize_webview", Boolean.valueOf(false));
    public static final cd bu = a("webview_package_name", "com.google.android.webview");
    private static final List bv = Arrays.asList(new Class[]{Boolean.class, Float.class, Integer.class, Long.class, String.class});
    private static final List bw = new ArrayList();
    public static final cd c = a("device_id", "");
    public static final cd d = a("publisher_id", "");
    public static final cd e = a("device_token", "");
    public static final cd f = a("submit_data_retry_count", Integer.valueOf(1));
    public static final cd g = a("vr_retry_count", Integer.valueOf(1));
    public static final cd h = a("fetch_ad_retry_count", Integer.valueOf(1));
    public static final cd i = a("is_verbose_logging", Boolean.valueOf(false));
    public static final cd j = a("api_endpoint", "http://d.applovin.com/");
    public static final cd k = a("adserver_endpoint", "http://a.applovin.com/");
    public static final cd l = a("get_retry_delay", Long.valueOf(10000));
    public static final cd m = a("hash_algorithm", CommonUtils.SHA1_INSTANCE);
    public static final cd n = a("short_hash_size", Integer.valueOf(16));
    public static final cd o = a("http_connection_timeout", Integer.valueOf(30000));
    public static final cd p = a("fetch_ad_connection_timeout", Integer.valueOf(30000));
    public static final cd q = a("http_socket_timeout", Integer.valueOf(20000));
    public static final cd r = a("ad_session_minutes", Integer.valueOf(60));
    public static final cd s = a("ad_request_parameters", "");
    public static final cd t = a("ad_refresh_enabled", Boolean.valueOf(true));
    public static final cd u = a("ad_refresh_seconds", Long.valueOf(120));
    public static final cd v = a("mrec_ad_refresh_enabled", Boolean.valueOf(true));
    public static final cd w = a("mrec_ad_refresh_seconds", Long.valueOf(120));
    public static final cd x = a("leader_ad_refresh_enabled", Boolean.valueOf(true));
    public static final cd y = a("leader_ad_refresh_seconds", Long.valueOf(120));
    public static final cd z = a("plugin_version", "");

    private static cd a(String str, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("No default value specified");
        } else if (bv.contains(obj.getClass())) {
            cd cdVar = new cd(str, obj);
            bw.add(cdVar);
            return cdVar;
        } else {
            throw new IllegalArgumentException("Unsupported value type: " + obj.getClass());
        }
    }

    public static Collection a() {
        return Collections.unmodifiableList(bw);
    }

    public static int b() {
        return bw.size();
    }
}
