package org.nexage.sourcekit.mraid.rtb;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.mopub.volley.DefaultRetryPolicy;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONObject;

public class ReportView extends LinearLayout {
    private View adView;
    ComplainedCallback complainedCallback;
    private RtbInfo rtbInfo;

    public interface ComplainedCallback {
        void wasComplained();
    }

    public void registerCallback(ComplainedCallback complainedCallback) {
        this.complainedCallback = complainedCallback;
    }

    public ReportView(Context context) {
        super(context);
        init();
    }

    public ReportView(Context context, View view) {
        super(context);
        this.adView = view;
        init();
    }

    public ReportView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    void init() {
        View backButton = new BackButton(getContext());
        backButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((ViewGroup) ReportView.this.getParent()).removeView(ReportView.this);
            }
        });
        addView(backButton);
        createButton(0);
        createButton(1);
        createButton(2);
        new Paint(1).setStyle(Style.FILL);
    }

    private void createButton(final int i) {
        CharSequence charSequence;
        switch (i) {
            case 0:
                charSequence = "Irrelevant";
                break;
            case 1:
                charSequence = "Repetitive";
                break;
            default:
                charSequence = "Inappropriate";
                break;
        }
        View button = new Button(getContext());
        button.setLayoutParams(new LayoutParams(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        button.setBackgroundDrawable(null);
        button.setTextColor(-16711936);
        button.setTextSize(2, 9.0f);
        button.setGravity(17);
        button.setText(charSequence);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((ViewGroup) ReportView.this.getParent()).removeView(ReportView.this);
                if (ReportView.this.complainedCallback != null) {
                    ReportView.this.complainedCallback.wasComplained();
                }
                ReportView.this.sendReport(i);
            }
        });
        addView(button);
    }

    private void sendReport(int i) {
        final JSONObject reportInfo = this.rtbInfo.getReportInfo(i, getContext(), an.c(this.adView));
        AsyncTask anonymousClass3 = new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... voidArr) {
                try {
                    try {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://adwatch.appodeal.com/api/v1/complains/submit").openConnection();
                        httpURLConnection.setRequestProperty("Accept", "application/json");
                        httpURLConnection.setRequestProperty("Content-type", "application/json");
                        httpURLConnection.setConnectTimeout(2000);
                        httpURLConnection.setReadTimeout(2000);
                        httpURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
                        httpURLConnection.setDoOutput(true);
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        dataOutputStream.write(reportInfo.toString().getBytes(Charset.forName("UTF-8")));
                        dataOutputStream.flush();
                        dataOutputStream.close();
                        switch (httpURLConnection.getResponseCode()) {
                            case 200:
                                break;
                            default:
                                Appodeal.a("rtb_banner_report_failed");
                                break;
                        }
                    } catch (Throwable e) {
                        Appodeal.a(e);
                    }
                } catch (Throwable e2) {
                    try {
                        Appodeal.a(e2);
                    } catch (Throwable e22) {
                        Appodeal.a(e22);
                    }
                }
                return null;
            }
        };
        if (VERSION.SDK_INT >= 11) {
            anonymousClass3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            anonymousClass3.execute(new Void[0]);
        }
    }

    public void setInfo(RtbInfo rtbInfo) {
        this.rtbInfo = rtbInfo;
    }
}
