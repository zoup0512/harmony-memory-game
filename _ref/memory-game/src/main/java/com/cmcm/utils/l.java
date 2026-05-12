package com.cmcm.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.cmcm.picks.market.MarketUtils;

/* compiled from: ParseWebViewUrlUtils */
public class l {
    private WebView a;
    private boolean b;
    private Handler c;
    private a d = null;
    private String e;
    private String f;
    private String g;
    private String h;

    /* compiled from: ParseWebViewUrlUtils */
    public interface a {
        void a(String str);
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public l(Context context) {
        if (!Commons.isWebViewProbablyCorrupt(context)) {
            try {
                this.a = new WebView(context);
            } catch (Exception e) {
            }
        }
        if (this.a != null) {
            this.c = new Handler(this, Looper.getMainLooper()) {
                final /* synthetic */ l a;

                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case 1:
                            this.a.b = true;
                            this.a.a((String) msg.obj);
                            return;
                        case 2:
                            if (this.a.a != null) {
                                this.a.a.stopLoading();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            };
            this.a.getSettings().setJavaScriptEnabled(true);
            this.a.getSettings().setCacheMode(2);
            if (VERSION.SDK_INT < 18) {
                this.a.getSettings().setSavePassword(false);
            }
        }
    }

    public void a(String str, String str2, String str3, String str4) {
        if (this.a != null) {
            this.e = str;
            this.f = str2;
            this.g = str3;
            this.h = str4;
            this.a.setWebViewClient(new WebViewClient(this) {
                final /* synthetic */ l a;

                {
                    this.a = r1;
                }

                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (view == null) {
                        return super.shouldOverrideUrlLoading(view, url);
                    }
                    view.loadUrl(url);
                    return true;
                }

                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    if (!this.a.b) {
                        this.a.c.removeMessages(1);
                        this.a.c.removeMessages(2);
                        if (TextUtils.isEmpty(url)) {
                            if (view != null) {
                                view.stopLoading();
                            }
                            this.a.b = true;
                            this.a.c.sendMessage(this.a.c.obtainMessage(1, url));
                        } else if (MarketUtils.isGooglePlayUrl(url)) {
                            if (view != null) {
                                view.stopLoading();
                            }
                            this.a.b = true;
                            this.a.c.sendMessage(this.a.c.obtainMessage(1, url));
                        } else {
                            this.a.c.sendMessageDelayed(this.a.c.obtainMessage(2, url), 10000);
                            super.onPageStarted(view, url, favicon);
                        }
                    } else if (view != null) {
                        try {
                            view.stopLoading();
                        } catch (Exception e) {
                        }
                    }
                }

                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    if (!this.a.b) {
                        this.a.b = true;
                        this.a.c.removeMessages(2);
                        this.a.c.sendMessage(this.a.c.obtainMessage(1, failingUrl));
                        super.onReceivedError(view, errorCode, description, failingUrl);
                    }
                }

                public void onPageFinished(WebView view, String url) {
                    if (!this.a.b) {
                        this.a.c.removeMessages(2);
                        this.a.c.sendMessageDelayed(this.a.c.obtainMessage(1, url), 4000);
                        super.onPageFinished(view, url);
                    }
                }
            });
            this.a.loadUrl(str);
        }
    }

    private void a(String str) {
        if (this.a != null) {
            this.a.destroy();
            this.a = null;
        }
        if (this.d != null) {
            this.d.a(str);
        }
    }
}
