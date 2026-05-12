package com.flurry.sdk;

import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ky {
    private kh<jq> a = new kh<jq>(this) {
        final /* synthetic */ ky a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(kg kgVar) {
            jq jqVar = (jq) kgVar;
            km.a(4, this.a.b, "onNetworkStateChanged : isNetworkEnable = " + jqVar.a);
            if (jqVar.a) {
                this.a.b();
            }
        }
    };
    public final String b;
    public String c = "defaultDataKey_";
    public Set<String> d = new HashSet();
    public la e;

    public interface a {
    }

    public abstract void a(byte[] bArr, String str, String str2);

    public ky(final String str, String str2) {
        this.b = str2;
        ki.a().a("com.flurry.android.sdk.NetworkStateEvent", this.a);
        jy.a().b(new ma(this) {
            final /* synthetic */ ky b;

            public final void a() {
                this.b.e = new la(str);
            }
        });
    }

    public final void b() {
        jy.a().b(new ma(this) {
            final /* synthetic */ a a = null;
            final /* synthetic */ ky b;

            {
                this.b = r2;
            }

            public final void a() {
                ky kyVar = this.b;
                if (jr.a().b) {
                    List<String> arrayList = new ArrayList(kyVar.e.c.keySet());
                    if (arrayList.isEmpty()) {
                        km.a(4, kyVar.b, "No more reports to send.");
                        return;
                    }
                    for (String str : arrayList) {
                        if (kyVar.c()) {
                            List<String> a = kyVar.e.a(str);
                            km.a(4, kyVar.b, "Number of not sent blocks = " + a.size());
                            for (String str2 : a) {
                                if (!kyVar.d.contains(str2)) {
                                    if (!kyVar.c()) {
                                        break;
                                    }
                                    kz kzVar = (kz) new kf(jy.a().a.getFileStreamPath(kz.a(str2)), ".yflurrydatasenderblock.", 1, new lj<kz>(kyVar) {
                                        final /* synthetic */ ky a;

                                        {
                                            this.a = r1;
                                        }

                                        public final lg<kz> a(int i) {
                                            return new com.flurry.sdk.kz.a();
                                        }
                                    }).a();
                                    if (kzVar == null) {
                                        km.a(6, kyVar.b, "Internal ERROR! Cannot read!");
                                        kyVar.e.a(str2, str);
                                    } else {
                                        byte[] bArr = kzVar.b;
                                        if (bArr == null || bArr.length == 0) {
                                            km.a(6, kyVar.b, "Internal ERROR! Report is empty!");
                                            kyVar.e.a(str2, str);
                                        } else {
                                            km.a(5, kyVar.b, "Reading block info " + str2);
                                            kyVar.d.add(str2);
                                            kyVar.a(bArr, str2, str);
                                        }
                                    }
                                }
                            }
                        } else {
                            return;
                        }
                    }
                    return;
                }
                km.a(5, kyVar.b, "Reports were not sent! No Internet connection!");
            }
        });
    }

    public void a(final String str, final String str2, int i) {
        jy.a().b(new ma(this) {
            final /* synthetic */ ky c;

            public final void a() {
                if (!this.c.e.a(str, str2)) {
                    km.a(6, this.c.b, "Internal error. Block wasn't deleted with id = " + str);
                }
                if (!this.c.d.remove(str)) {
                    km.a(6, this.c.b, "Internal error. Block with id = " + str + " was not in progress state");
                }
            }
        });
    }

    public final void a(final String str) {
        jy.a().b(new ma(this) {
            final /* synthetic */ ky b;

            public final void a() {
                if (!this.b.d.remove(str)) {
                    km.a(6, this.b.b, "Internal error. Block with id = " + str + " was not in progress state");
                }
            }
        });
    }

    public final void b(final byte[] bArr, final String str, final String str2) {
        if (bArr == null || bArr.length == 0) {
            km.a(6, this.b, "Report that has to be sent is EMPTY or NULL");
            return;
        }
        jy.a().b(new ma(this) {
            final /* synthetic */ ky d;

            public final void a() {
                ky kyVar = this.d;
                byte[] bArr = bArr;
                String str = str;
                str = kyVar.c + str + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + str2;
                kz kzVar = new kz(bArr);
                String str2 = kzVar.a;
                new kf(jy.a().a.getFileStreamPath(kz.a(str2)), ".yflurrydatasenderblock.", 1, new lj<kz>(kyVar) {
                    final /* synthetic */ ky a;

                    {
                        this.a = r1;
                    }

                    public final lg<kz> a(int i) {
                        return new com.flurry.sdk.kz.a();
                    }
                }).a(kzVar);
                km.a(5, kyVar.b, "Saving Block File " + str2 + " at " + jy.a().a.getFileStreamPath(kz.a(str2)));
                kyVar.e.a(kzVar, str);
            }
        });
        b();
    }

    final boolean c() {
        return this.d.size() <= 5;
    }
}
