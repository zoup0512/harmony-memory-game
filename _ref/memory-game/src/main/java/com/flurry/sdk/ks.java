package com.flurry.sdk;

import com.flurry.sdk.ku.c;
import java.io.InputStream;
import java.io.OutputStream;

public final class ks<RequestObjectType, ResponseObjectType> extends ku {
    public a<RequestObjectType, ResponseObjectType> a;
    public RequestObjectType b;
    public lg<RequestObjectType> c;
    public lg<ResponseObjectType> d;
    private ResponseObjectType v;

    public interface a<RequestObjectType, ResponseObjectType> {
        void a(ks<RequestObjectType, ResponseObjectType> ksVar, ResponseObjectType responseObjectType);
    }

    public final void a() {
        this.l = new c(this) {
            final /* synthetic */ ks a;

            {
                this.a = r1;
            }

            public final void a(OutputStream outputStream) throws Exception {
                if (this.a.b != null && this.a.c != null) {
                    this.a.c.a(outputStream, this.a.b);
                }
            }

            public final void a(ku kuVar, InputStream inputStream) throws Exception {
                if (kuVar.d() && this.a.d != null) {
                    this.a.v = this.a.d.a(inputStream);
                }
            }

            public final void a(ku kuVar) {
                ks.d(this.a);
            }
        };
        super.a();
    }

    static /* synthetic */ void d(ks ksVar) {
        if (ksVar.a != null && !ksVar.g()) {
            ksVar.a.a(ksVar, ksVar.v);
        }
    }
}
