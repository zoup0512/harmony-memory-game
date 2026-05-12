package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class zzci {
    private static zzci awP;
    private volatile String auF;
    private volatile zza awQ;
    private volatile String awR;
    private volatile String awS;

    enum zza {
        NONE,
        CONTAINER,
        CONTAINER_DEBUG
    }

    zzci() {
        clear();
    }

    static zzci zzcci() {
        zzci com_google_android_gms_tagmanager_zzci;
        synchronized (zzci.class) {
            if (awP == null) {
                awP = new zzci();
            }
            com_google_android_gms_tagmanager_zzci = awP;
        }
        return com_google_android_gms_tagmanager_zzci;
    }

    private String zzoz(String str) {
        return str.split("&")[0].split("=")[1];
    }

    private String zzu(Uri uri) {
        return uri.getQuery().replace("&gtm_debug=x", "");
    }

    void clear() {
        this.awQ = zza.NONE;
        this.awR = null;
        this.auF = null;
        this.awS = null;
    }

    String getContainerId() {
        return this.auF;
    }

    zza zzccj() {
        return this.awQ;
    }

    String zzcck() {
        return this.awR;
    }

    synchronized boolean zzt(Uri uri) {
        boolean z = true;
        synchronized (this) {
            try {
                String decode = URLDecoder.decode(uri.toString(), "UTF-8");
                String str;
                String valueOf;
                if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                    str = "Container preview url: ";
                    valueOf = String.valueOf(decode);
                    zzbn.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    if (decode.matches(".*?&gtm_debug=x$")) {
                        this.awQ = zza.CONTAINER_DEBUG;
                    } else {
                        this.awQ = zza.CONTAINER;
                    }
                    this.awS = zzu(uri);
                    if (this.awQ == zza.CONTAINER || this.awQ == zza.CONTAINER_DEBUG) {
                        decode = String.valueOf("/r?");
                        valueOf = String.valueOf(this.awS);
                        this.awR = valueOf.length() != 0 ? decode.concat(valueOf) : new String(decode);
                    }
                    this.auF = zzoz(this.awS);
                } else if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                    str = "Invalid preview uri: ";
                    String valueOf2 = String.valueOf(decode);
                    zzbn.zzcx(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
                    z = false;
                } else if (zzoz(uri.getQuery()).equals(this.auF)) {
                    decode = "Exit preview mode for container: ";
                    valueOf = String.valueOf(this.auF);
                    zzbn.v(valueOf.length() != 0 ? decode.concat(valueOf) : new String(decode));
                    this.awQ = zza.NONE;
                    this.awR = null;
                } else {
                    z = false;
                }
            } catch (UnsupportedEncodingException e) {
                z = false;
            }
        }
        return z;
    }
}
