package com.cmcm.adsdk.utils;

public interface IEventReport {
    void reportClick(String str, String str2);

    void reportClick(String str, String str2, String str3);

    void reportOther(String str, int i);

    void reportOther(String str, int i, String str2);

    void reportShow(String str, String str2);

    void reportShow(String str, String str2, String str3);
}
