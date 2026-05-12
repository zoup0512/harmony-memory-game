package com.appodeal.ads.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.mopub.volley.DefaultRetryPolicy;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class h extends ArrayAdapter<u> {
    private final ArrayList<u> a;

    public h(Context context, ArrayList<u> arrayList) {
        super(context, -1, arrayList);
        this.a = arrayList;
    }

    @SuppressLint({"SetTextI18n"})
    @NonNull
    public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LayoutParams(-1, -2));
        linearLayout.setOrientation(0);
        View textView = new TextView(getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        textView.setText(String.valueOf(((u) this.a.get(i)).a));
        textView.setTextSize(22.0f);
        textView.setTextColor(-1);
        textView.setGravity(17);
        View textView2 = new TextView(getContext());
        textView2.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 8.5f));
        textView2.setText(String.format("%s\n%s", new Object[]{((u) this.a.get(i)).b, Integer.valueOf(((u) this.a.get(i)).c)}));
        textView2.setTextSize(22.0f);
        textView2.setTextColor(-1);
        textView2.setGravity(17);
        View linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 2.5f));
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(17);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        View textView3 = new TextView(getContext());
        textView3.setLayoutParams(layoutParams);
        textView3.setText(new DecimalFormat("#.##").format(((u) this.a.get(i)).d));
        textView3.setTextSize(22.0f);
        textView3.setTextColor(-1);
        textView3.setGravity(17);
        linearLayout2.addView(textView3);
        if (((u) this.a.get(i)).e || ((u) this.a.get(i)).f) {
            textView3 = new TextView(getContext());
            if (((u) this.a.get(i)).e) {
                textView3.setText("precache");
            } else if (((u) this.a.get(i)).f) {
                textView3.setText("offer");
            }
            textView3.setTextSize(RadialCountdown.TEXT_SIZE_SP);
            textView3.setTextColor(-1);
            textView3.setGravity(17);
            textView3.setLayoutParams(layoutParams);
            linearLayout2.addView(textView3);
        }
        linearLayout.addView(textView);
        linearLayout.addView(textView2);
        linearLayout.addView(linearLayout2);
        return linearLayout;
    }

    public void a() {
        ListAdapter arrayAdapter = new ArrayAdapter(getContext(), 17367057);
        arrayAdapter.add("Waterfall order");
        arrayAdapter.add("Alphabetical order");
        Builder builder = new Builder(getContext());
        builder.setAdapter(arrayAdapter, new OnClickListener(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.a(i);
                dialogInterface.dismiss();
            }
        }).setTitle("Sort items");
        builder.create().show();
    }

    private void a(int i) {
        switch (i) {
            case 0:
                b();
                break;
            case 1:
                c();
                break;
        }
        notifyDataSetChanged();
    }

    private void b() {
        Collections.sort(this.a, new Comparator<u>(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((u) obj, (u) obj2);
            }

            public int a(u uVar, u uVar2) {
                return uVar.a - uVar2.a;
            }
        });
    }

    private void c() {
        Collections.sort(this.a, new Comparator<u>(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((u) obj, (u) obj2);
            }

            public int a(u uVar, u uVar2) {
                return uVar.b.compareToIgnoreCase(uVar2.b);
            }
        });
    }
}
