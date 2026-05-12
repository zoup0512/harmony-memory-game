package com.cmcm.adsdk;

import java.util.HashMap;
import java.util.Map;

public class CMRequestParams {
    public static final String KEY_BANNER_VIEW_SIZE = "key_banner_view_size";
    public static final String KEY_REPORT_SHOW_IGNORE_VIEW = "report_show_ignore_view";
    public static final String KEY_SELECT_ALL_PRIORITYAD = "key_select_all_priority";
    protected Map<String, Object> mParams;

    public CMRequestParams() {
        this.mParams = null;
        this.mParams = new HashMap();
    }

    public void setReportShowIgnoreView(boolean ignoreView) {
        if (this.mParams != null) {
            this.mParams.put(KEY_REPORT_SHOW_IGNORE_VIEW, Boolean.valueOf(ignoreView));
        }
    }

    public boolean getReportShowIgnoreView() {
        if (this.mParams != null) {
            Object obj = this.mParams.get(KEY_REPORT_SHOW_IGNORE_VIEW);
            if (obj != null) {
                return Boolean.valueOf(obj.toString()).booleanValue();
            }
        }
        return false;
    }

    public void setSelectAllPriorityAd(boolean select) {
        if (this.mParams != null) {
            this.mParams.put(KEY_SELECT_ALL_PRIORITYAD, Boolean.valueOf(select));
        }
    }

    public boolean isSelectAllPriorityAd() {
        if (this.mParams == null || !this.mParams.containsKey(KEY_SELECT_ALL_PRIORITYAD)) {
            return true;
        }
        return ((Boolean) this.mParams.get(KEY_SELECT_ALL_PRIORITYAD)).booleanValue();
    }
}
