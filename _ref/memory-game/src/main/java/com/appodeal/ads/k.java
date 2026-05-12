package com.appodeal.ads;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.appodeal.ads.g.b;
import com.appodeal.ads.utils.t;
import com.mopub.volley.DefaultRetryPolicy;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public abstract class k extends d {
    protected int b;

    public abstract void a(Activity activity, int i, int i2);

    protected abstract void a(View view);

    public abstract ViewGroup c();

    protected int d() {
        return -2;
    }

    protected int e() {
        return Math.round(((float) this.b) * an.i(Appodeal.b));
    }

    public void a(Activity activity, final h hVar, final int i, b bVar, boolean z, b bVar2) {
        final View view = g.p;
        if (!z || view != null) {
            View c;
            if (view == null || !z) {
                c = c();
            } else {
                c = view;
            }
            if (c != null) {
                Object findViewById;
                int i2;
                View view2;
                ViewGroup viewGroup;
                boolean z2;
                boolean z3;
                View view3;
                LayoutParams layoutParams;
                boolean z4;
                BannerView bannerView;
                if (c.equals(view) && c.getParent() != null) {
                    if (bVar == b.VIEW) {
                        findViewById = activity.findViewById(g.o);
                        if (findViewById == null) {
                            findViewById = g.v;
                        }
                        if (bVar2 == bVar && view.getContext().equals(activity) && view.getParent() != null && view.getParent().equals(r1)) {
                            findViewById = null;
                        } else {
                            g.a(view, true, true);
                        }
                    } else if (bVar2 == bVar && view.getContext().equals(activity)) {
                        findViewById = null;
                    } else {
                        g.a(view, true, true);
                        i2 = 1;
                    }
                    view2 = null;
                    if (g.u || bVar == b.VIEW) {
                        if (!(view == null || view.getParent() == null || !(view.getParent() instanceof ViewGroup))) {
                            viewGroup = (ViewGroup) view.getParent();
                            if (viewGroup.getTag() != null && viewGroup.getTag().equals("Appodeal")) {
                                view2 = (FrameLayout) view.getParent();
                            }
                        }
                        if (view2 == null) {
                            z2 = true;
                            view2 = null;
                        } else {
                            z2 = false;
                        }
                        z3 = z2;
                        view3 = view2;
                        layoutParams = new FrameLayout.LayoutParams(d(), e());
                        z4 = z3;
                    } else {
                        View a;
                        boolean z5;
                        if (!(view == null || view.getParent() == null || !(view.getParent() instanceof ViewGroup))) {
                            viewGroup = (ViewGroup) view.getParent();
                            if (viewGroup.getTag() != null && viewGroup.getTag().equals("Appodeal")) {
                                view2 = (FrameLayout) view.getParent();
                            }
                        }
                        if (!(view2 == null || (view2.getHeight() == e() && bVar2 == bVar && view.getContext().equals(activity)))) {
                            view2 = null;
                        }
                        if (view2 == null) {
                            a = a(activity);
                            z5 = true;
                        } else {
                            a = view2;
                            z5 = false;
                        }
                        z3 = z5;
                        layoutParams = (FrameLayout.LayoutParams) a.getLayoutParams();
                        view3 = a;
                        z4 = z3;
                    }
                    switch (bVar) {
                        case BOTTOM:
                            layoutParams.gravity = 81;
                            if (findViewById != null) {
                                if (view3 == null) {
                                    activity.addContentView(c, layoutParams);
                                    break;
                                }
                                if (view3.getParent() == null) {
                                    activity.addContentView(view3, layoutParams);
                                }
                                view3.addView(c);
                                break;
                            }
                            break;
                        case TOP:
                            layoutParams.gravity = 49;
                            if (findViewById != null) {
                                if (view3 == null) {
                                    activity.addContentView(c, layoutParams);
                                    break;
                                }
                                if (view3.getParent() == null) {
                                    activity.addContentView(view3, layoutParams);
                                }
                                view3.addView(c);
                                break;
                            }
                            break;
                        case VIEW:
                            layoutParams.gravity = 1;
                            view3 = activity.findViewById(g.o);
                            if (view3 == null) {
                                view3 = g.v;
                            }
                            if (view3 != null) {
                                bannerView = (BannerView) view3;
                                if (findViewById != null) {
                                    bannerView.addView(c, layoutParams);
                                }
                                bannerView.setVisibility(0);
                                break;
                            }
                            break;
                    }
                    t.a(this, c, g.F, new t.b(this) {
                        final /* synthetic */ k c;

                        public void a() {
                            j.a(i, hVar);
                        }

                        public void b() {
                            j.b(i, hVar);
                        }
                    });
                    g.p = c;
                    g.r = bVar;
                    g.q = i;
                    g.p.setVisibility(0);
                    if (VERSION.SDK_INT < 16 && g.r == bVar2 && view != null && !c.equals(view) && g.G) {
                        view.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                        view.animate().alpha(0.0f).setDuration(800).withLayer();
                        c.setAlpha(0.0f);
                        final b bVar3 = bVar;
                        final Activity activity2 = activity;
                        c.animate().alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).setDuration(800).withLayer().setListener(new AnimatorListenerAdapter(this) {
                            final /* synthetic */ k f;

                            public void onAnimationEnd(Animator animator) {
                                super.onAnimationEnd(animator);
                                if (!c.equals(view)) {
                                    try {
                                        g.a(view, bVar3 != b.VIEW, z4);
                                        this.f.a(activity2, view);
                                    } catch (Throwable e) {
                                        Appodeal.a(e);
                                    }
                                }
                            }
                        });
                        return;
                    } else if (!c.equals(view)) {
                        try {
                            g.a(view, bVar == b.VIEW, z4);
                            a(activity, view);
                        } catch (Throwable e) {
                            Appodeal.a(e);
                            return;
                        }
                    }
                }
                i2 = 1;
                view2 = null;
                if (g.u) {
                }
                viewGroup = (ViewGroup) view.getParent();
                view2 = (FrameLayout) view.getParent();
                if (view2 == null) {
                    z2 = false;
                } else {
                    z2 = true;
                    view2 = null;
                }
                z3 = z2;
                view3 = view2;
                layoutParams = new FrameLayout.LayoutParams(d(), e());
                z4 = z3;
                switch (bVar) {
                    case BOTTOM:
                        layoutParams.gravity = 81;
                        if (findViewById != null) {
                            if (view3 == null) {
                                if (view3.getParent() == null) {
                                    activity.addContentView(view3, layoutParams);
                                }
                                view3.addView(c);
                                break;
                            }
                            activity.addContentView(c, layoutParams);
                            break;
                        }
                        break;
                    case TOP:
                        layoutParams.gravity = 49;
                        if (findViewById != null) {
                            if (view3 == null) {
                                if (view3.getParent() == null) {
                                    activity.addContentView(view3, layoutParams);
                                }
                                view3.addView(c);
                                break;
                            }
                            activity.addContentView(c, layoutParams);
                            break;
                        }
                        break;
                    case VIEW:
                        layoutParams.gravity = 1;
                        view3 = activity.findViewById(g.o);
                        if (view3 == null) {
                            view3 = g.v;
                        }
                        if (view3 != null) {
                            bannerView = (BannerView) view3;
                            if (findViewById != null) {
                                bannerView.addView(c, layoutParams);
                            }
                            bannerView.setVisibility(0);
                            break;
                        }
                        break;
                }
                t.a(this, c, g.F, /* anonymous class already generated */);
                g.p = c;
                g.r = bVar;
                g.q = i;
                g.p.setVisibility(0);
                if (VERSION.SDK_INT < 16) {
                }
                if (!c.equals(view)) {
                    if (bVar == b.VIEW) {
                    }
                    g.a(view, bVar == b.VIEW, z4);
                    a(activity, view);
                }
            }
        }
    }

    private void a(Activity activity, View view) {
        for (h hVar : g.a((Context) activity)) {
            if (hVar.f() != null) {
                hVar.f().a(view);
            }
        }
    }

    public void a(Activity activity, int i) {
    }

    public void b(Activity activity, int i) {
    }

    public void f() {
    }

    public boolean g() {
        return false;
    }

    private FrameLayout a(Activity activity) {
        FrameLayout frameLayout = new FrameLayout(activity);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, e()));
        byte[] decode = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAOYUlEQVR42u2dWW7sug5FPZzze/q+mf+k8sACFkAs1DblOBevCvAH47DcSaLEXvT25cuXl4JPnz69vHv37uXjx48vnz9/7nj9X8cb/v37d+CGf/jw4eX9+/d1vOFfv36t+zl/O/748QP89v+3b98KCq931z233/vzf/78Wfjt+OvXL87Xswvc3jpynjbzPp7HecDX+zzPBwqnrwX0P+K0v95ffej9Aae/NQaFbwx2DUz9UDi/NZwG10DWzQVuAAQBuN4DXv+D17FfXw2lE5yHiGnAIBDt7ecZjDr6fl0vXASZCGC8CAcBIAL479+/b8c+gek/K6Sgz5jeQGYQM5oZvkoQZik47/OAg6cZ5RWSZjx94H31jHpu6p/vhwheISNBzDGYEEUA2s+EA/d4bPXngseBjeVcwIxsLAX5AgXNQz1jOV9Hz8g9GVDw6hkJsMLoU2MRBaxgeDb9K5wVWv97xfYVzrPr6PfzPNj0q1bUxgvhacIhRoEfyHmWvc+bpSSWwW9neDZ4DXy1v470h0HuBKE/HsA6ZxZrgiEX/H4T0DJljSC8jBcwA+iAtTA/UDLIDWA17PHsgv78MysEecOAMLnAIQY4xKgjeEEnGOPROcjeCrEWCpjDGL9kyCPCVpQ5odaxPD0jYRnMwD21kxXRr6+ZBo5d09VEZjSzG9wsyiwJ+dDVcFaV1fxBTY4smj7taa1RZm7th7TEChKB4KkQAP7ahSgDkuwGoPP4PuAAA2y1urMg3885roc9W4h3wxEWbZZ6iCBmycb7eGeCHF8hzDA6ZMvUhqQ7ZDsHAnIent95PESxpQvO+U5QG6a0D2J0gkCUaYAtlCdPQNIygUuGPCJsTS9PM9A8HNUOHJ7M/eDW2jpLA+f5wfIfXDWDL2rwTU1qqHFrRfSPVdb7bxZKfzkXzYhNPJ4HdJbBSzpulmHfVCcI9/cG0QF4fGyg2qfzs9C1UB2UmMO47TZNcKvVmAhRLd5kqVqL4eHmyQygDS3OWeuxJ8Ba0rRCQgc04BKaYYWcIYBxVjzt83jAcarPqb+XHfKowAqp2TNZ4mYBYQZKD39bFjGyNHDzcGYpKx585unZbup4Hr/5eR7PbXKNiGA8YM85mNW6tycIKqp9T2YZnUWCWwb6+Q6AgZtF4u/bUxIKrPaa5S4RhJdxQ2qQKX4+4pZx6/0MKgOcZBzn7atCpk0zOuKModqXxjM975IhjwibKMby9xK3L6h+sxprS577UwzeK8zxF82guELPuu9pj0POuPOJ+nXXTVejkSuWYX7+knd4a0kMds6Bc5OFJOdxncw83izL8RTHX+aQre+HGHVcjUcAvb/djnII2EoAbbDdsfQ+J3lsbYbZ8gb3ijBBCAqtxksAEwAcsNZ23NnnCTATyIatY+LYYda6urOV89OKNAEvGfKw8RBgcBdP7uNphYBPMiHh/XrHT6IdUkf8TWbJszc7x8ztCgHPEdLZl2aCJCHpB6YBcocSQQA3eNk5aBYD2Jnn8ADX5xDsjKPk9OejBB2Pf5iFra+QtYhXjqn3vK41AswrJCXeMWMRygSlnJWyKnMA+6YckZwscbf/8mU9A2zKfbXdYLvAM8LxD/5PWpUz94hnW88ParJkxlpEjt/EMg6rxbb0o5qPpmW1GZwxvyeTNtzHO3lLsBwbTh2XIRlcCUpcUx6VhXLqMB0AB2a1+HyaEWMTEwctU3DN19GJiJ5gnSBdzwa6YUcHWAEhe13J0yGRjVkjGeCkhSW9XYZtNiSz4QjMSR4OQGVDmPMpHsR487wrt/dp7JABh50UZeMMNItj1vQlDS5vs0K6cT9JYGlLWhO/mSWfDRGfDTccJohDuKnBgHlqJ6gT5zqBnCjnRDeuGVwTy/GUKBPWwwvg53OTt1HLyDHuyXAzAZKhyIYhXw8B1mXUcRwN0ivEBJHWdjCrZV6xBZcMedR4CFqH84rMY/uMcgTOaq/xlD1e16F1TXq/87iSd5gV7C1jzhGYLX3LNGuhTkVlBWTXzsyylMppoWseW0d4qnk8OAAOm3EaUMeDIRjzwMBpe7/eBAFs6FppMIEs05y3NsWDvKVvnSAFbYUwo7wijFvI00APCJ3ulmoBOOcTQaxEsGIgSFYimOFZq3MSBPfSfhvCnE8TzEoF5+6tkMuX9Wx2iNP9pdZFtTaFLH2/dzCl+IV59CoPTs+nT3tpOX0b+EpaU3i/LHOt2FmNnglC40JQX9sFhrSXkGoJEegQv9kXtOoeByzkp2Rt2julok4BLG8YgiiRIIAJwoBZz54KCTADVnJvHbN3B+y8QwtbXSFMjqBV6fqJAF5Bsy8MeVNHa4lpPPntkiFPIUO8b93xC12/skLQzOZaJXNE0tnj4GaJiUXBnvr1loHJjqC/xnlf3D9CyKFn6YDbrNhCAMnu4j2CpDQcG5YmQMrLykLUiXp2x2fnowsZBENyiH+EtKiJoCTbeQOT32+COPl4xZKGGGEApPdrhQy7Vh1Qst2Q9gSmDTEmuO2KZEfwvo6HiKneF/Y0Mp7alHrJkEeEragL1QZ3++S+BgcmNc8sIb8/syD7kuiL03SC3TPvCmb1myWC21ttGeb2WwtzbZUNgb3DQxNB7PtaDegAk1LA+T2CkSDRk6MhUPJt8Vs0DAvAh02x9u1xbbSr6ui8Ltrr4jPusBuUhHaKj0wyg/vz8+csFozGLjRNEBu60wQCUoST36y0pPpglsmA88QuGfKIsN0JyWbflff0WYYcX0GotC6tUZAilLRnLwumIFX/YUZa7bZdMWfr2zMwZ71wLtc6abVBkpCzWop7paeGDjJmOSnCMov7TdDkukFgmufb/V9Hs1Du53kpKcIECa6RcVNsJgggClrrMM+3lrGyB9B2gJ9vIe4BtWHJ+b2IH9c4HkP7cjZ9LthmrSon7mUCXbVOnqbm4r9//17+/PljnjrHP/KuVFvCKSLnLXOWCVYbLeOYrcYDy9OKCDj9VXzjcKbjfL21PhMk6+VH7A4TxO5wWIbjKdgRNuy8xw9IPJ029Pfvpe1EIWxn5vEainOemJNKtiJEDcDfv39X6/Yi2JPMYZXYMLPQdH0re5ttZ9g5mDwFice7vZ6AyXDN2e7nE/Vo32WHPCpsmjHazzDo0UPmn2bg0fjHtAdwrrVu73VOVbUMszfXavNKeMK4ZaTPR4KwFdgEccjTPHy1KKQT296qNoonFDIJSM5B5JpZrAsp2DXiLXOeMKn0+D5BVBWUF1QHknPQBFktpDzh855F47OzkWgnA8Zq6DIC8P4Ux8STN5l3TDLEBL9yex8f5qqk9paaB4N3HlvgLXJ7VUnT+6a0Hfue0rboVMFtqrQ9+7asxc3VkmZLXdsHBoJIDRSuXF3fn1wnwFTSL7FMq9HONQaf6mHNds4cr6F/k92TfVmKWQ/xEOn9titc+8S5tbaMTRCvwEwQe4fdnjpOAzoN4JSZad/eJCMvX9Yzwqba6k5rYfYm/7/jD66GYxZGjtIKT/YMN8sz/l9/DiPu8LL32CFfV2HtEVCvwK2VG0ohz5S+n17IPV3t7DJlSpvh+b7edswubqE62zlzOSe7jmgfdhu4CQLukoKWmZ0gfYWkiKADQrMl7K+kuTrPXK/K3lbaMtVG8fkjBDHuLBfej+FJn3r7zDHSV+o4f/myHrpuLzC4s4/mXZ13hfgrbXaFsEJzFgjszzzclvmKe32qW7wa/9hNnd004HIWRhbhBzpG/pa1RZwbS8zEMifFQ/L3RCxUZxaWC6zNBEkBwFhIORWhJGY+79M2ngt2HVkhEKWvEGQSWo2TLlxPCwI4CQJ5txIRdP/PrJCrbu+z+bK62pvqX1nrSV/STPGOeUVZhuV6WK+raTizJO8XsRng8/jiwi7g2Tdn7/KmDrrcEv/b0OkhV8BCKg3YOkHmEKtxBlilzQ+VDgFSsjbnky/unp2SPpHEeIogmedC7WCniCBhhSxoZSFGLmfcfD/EWDf8ctVRW+YpF9nf0LISBBETQa56WY8M25Cdzv+vszO8H8MsKX8PfaXMaprRdl2wgnt7VrQiZ9OnXGHeebpur2u/HxWaqwRx2pCXtGXOqqHpGo8WkuD+RhSQWJoJkr6Na19e8p0dcm5uwTt6JgYO+CNYNjxTXVs6lc4D6Sto4N7EaiE9EcS7blNtF66ZlJarbu+z1+1N+znu1R5xfITrUyUFuz78rdxUk7Ha5Rma0v+Pl4k9nvrpnAH7vuyKwatQsPQNrY0OK14xfSYvfJdPSzyryebJKWbvABAsIidNgJ93dppAju+YIN4karMgEgQg2bpT0DF0jJq9TY/pI1zWQrynLgW4HHGEINaq/i/eZ1YHK3wo3sO1aYUw2S875FFhu+PLccU0KGy1cm8/SahfpRkbPAFAYFnWolh1tNc7tqY9kIBzCKZdxdYKz+Ymx9IaHhD4JudXvidiV0IdrRamTZ73ik6GAJO/DzJv6vQEVPgB/EhI2Js46x2vVhq2yZdjLcExYDdQVU61iVPX2/mWP3ackh4gSMcd4wZWvxIHTNn71vJWY/ZXzcVngk3bkp01wYqZarU7T6vj1rKAtE3ZqaBmeZ6xbr/b4xnvur3W8pLM6rjrAods+nlFWEYRoEq5tXYfW0jSgVhW1SxIdo8NLcsQ3t/xtCm1T4DVPYi2k4y7/WaJEMSl0cGXQ7oFXiGpeg0zKnXQQjdpTemDJ+6gN8wAyfJ2opzbx28W2nv9Bad/Nmyr3cmwzbnJOYvliqk/R16W7YLsHoeHc+20w8rPL3DJvvlzGOb5VmuP6/2znbGe1pRqw2Q7bP58t2VGNnzAvWTdARGcJT35rpJrBPnTCXJiC5zsoikHYI5ncH+cgCa4ca8QBOpULGa2xLPMcQ1DCGqtxkIPmeMVDBz1TSU7IlSeHj9+nDgC/XV855IhzwD/A3I+u8n88KU/AAAAAElFTkSuQmCC", 0);
        Drawable bitmapDrawable = new BitmapDrawable(activity.getResources(), BitmapFactory.decodeByteArray(decode, 0, decode.length));
        bitmapDrawable.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
        if (VERSION.SDK_INT >= 16) {
            frameLayout.setBackground(bitmapDrawable);
        } else {
            frameLayout.setBackgroundDrawable(bitmapDrawable);
        }
        frameLayout.setTag("Appodeal");
        return frameLayout;
    }

    public RtbInfo a(String str, int i) {
        try {
            return new RtbInfo(this.a, ((l) g.x.get(i)).l.getString("id"), ((l) g.x.get(i)).m, str, 4);
        } catch (Throwable e) {
            Appodeal.a(e);
            return null;
        }
    }
}
