package com.yandex.metrica;

import com.yandex.metrica.impl.ob.d;
import java.io.IOException;
import java.util.Arrays;

public interface c {

    public static final class a extends com.yandex.metrica.impl.ob.d {
        public b b;
        public g[] c;
        public c[] d;
        public f[] e;
        public String[] f;
        public h[] g;

        public static final class a extends com.yandex.metrica.impl.ob.d {
            public String b;
            public String c;
            public String d;

            public a() {
                d();
            }

            public a d() {
                this.b = "";
                this.c = "";
                this.d = "";
                this.a = -1;
                return this;
            }

            public void a(com.yandex.metrica.impl.ob.b bVar) throws IOException {
                bVar.a(1, this.b);
                if (!this.c.equals("")) {
                    bVar.a(2, this.c);
                }
                if (!this.d.equals("")) {
                    bVar.a(3, this.d);
                }
                super.a(bVar);
            }

            protected int c() {
                int c = super.c() + com.yandex.metrica.impl.ob.b.b(1, this.b);
                if (!this.c.equals("")) {
                    c += com.yandex.metrica.impl.ob.b.b(2, this.c);
                }
                if (this.d.equals("")) {
                    return c;
                }
                return c + com.yandex.metrica.impl.ob.b.b(3, this.d);
            }
        }

        public static final class b extends com.yandex.metrica.impl.ob.d {
            private static volatile b[] k;
            public int b;
            public int c;
            public int d;
            public int e;
            public int f;
            public String g;
            public boolean h;
            public int i;
            public int j;

            public static b[] d() {
                if (k == null) {
                    synchronized (com.yandex.metrica.impl.ob.c.a) {
                        if (k == null) {
                            k = new b[0];
                        }
                    }
                }
                return k;
            }

            public b() {
                e();
            }

            public b e() {
                this.b = -1;
                this.c = 0;
                this.d = -1;
                this.e = -1;
                this.f = -1;
                this.g = "";
                this.h = false;
                this.i = 0;
                this.j = -1;
                this.a = -1;
                return this;
            }

            public void a(com.yandex.metrica.impl.ob.b bVar) throws IOException {
                if (this.b != -1) {
                    bVar.b(1, this.b);
                }
                if (this.c != 0) {
                    bVar.c(2, this.c);
                }
                if (this.d != -1) {
                    bVar.b(3, this.d);
                }
                if (this.e != -1) {
                    bVar.b(4, this.e);
                }
                if (this.f != -1) {
                    bVar.b(5, this.f);
                }
                if (!this.g.equals("")) {
                    bVar.a(6, this.g);
                }
                if (this.h) {
                    bVar.a(7, this.h);
                }
                if (this.i != 0) {
                    bVar.a(8, this.i);
                }
                if (this.j != -1) {
                    bVar.b(9, this.j);
                }
                super.a(bVar);
            }

            protected int c() {
                int c = super.c();
                if (this.b != -1) {
                    c += com.yandex.metrica.impl.ob.b.e(1, this.b);
                }
                if (this.c != 0) {
                    c += com.yandex.metrica.impl.ob.b.f(2, this.c);
                }
                if (this.d != -1) {
                    c += com.yandex.metrica.impl.ob.b.e(3, this.d);
                }
                if (this.e != -1) {
                    c += com.yandex.metrica.impl.ob.b.e(4, this.e);
                }
                if (this.f != -1) {
                    c += com.yandex.metrica.impl.ob.b.e(5, this.f);
                }
                if (!this.g.equals("")) {
                    c += com.yandex.metrica.impl.ob.b.b(6, this.g);
                }
                if (this.h) {
                    c += com.yandex.metrica.impl.ob.b.e(7);
                }
                if (this.i != 0) {
                    c += com.yandex.metrica.impl.ob.b.d(8, this.i);
                }
                if (this.j != -1) {
                    return c + com.yandex.metrica.impl.ob.b.e(9, this.j);
                }
                return c;
            }
        }

        public static final class c extends com.yandex.metrica.impl.ob.d {
            private static volatile c[] d;
            public String b;
            public String c;

            public static c[] d() {
                if (d == null) {
                    synchronized (com.yandex.metrica.impl.ob.c.a) {
                        if (d == null) {
                            d = new c[0];
                        }
                    }
                }
                return d;
            }

            public c() {
                e();
            }

            public c e() {
                this.b = "";
                this.c = "";
                this.a = -1;
                return this;
            }

            public void a(com.yandex.metrica.impl.ob.b bVar) throws IOException {
                bVar.a(1, this.b);
                bVar.a(2, this.c);
                super.a(bVar);
            }

            protected int c() {
                return (super.c() + com.yandex.metrica.impl.ob.b.b(1, this.b)) + com.yandex.metrica.impl.ob.b.b(2, this.c);
            }
        }

        public static final class d extends com.yandex.metrica.impl.ob.d {
            public double b;
            public double c;
            public long d;
            public int e;
            public int f;
            public int g;
            public int h;
            public int i;

            public d() {
                d();
            }

            public d d() {
                this.b = 0.0d;
                this.c = 0.0d;
                this.d = 0;
                this.e = 0;
                this.f = 0;
                this.g = 0;
                this.h = 0;
                this.i = 0;
                this.a = -1;
                return this;
            }

            public void a(com.yandex.metrica.impl.ob.b bVar) throws IOException {
                bVar.a(1, this.b);
                bVar.a(2, this.c);
                if (this.d != 0) {
                    bVar.a(3, this.d);
                }
                if (this.e != 0) {
                    bVar.b(4, this.e);
                }
                if (this.f != 0) {
                    bVar.b(5, this.f);
                }
                if (this.g != 0) {
                    bVar.b(6, this.g);
                }
                if (this.h != 0) {
                    bVar.a(7, this.h);
                }
                if (this.i != 0) {
                    bVar.a(8, this.i);
                }
                super.a(bVar);
            }

            protected int c() {
                int c = (super.c() + com.yandex.metrica.impl.ob.b.d(1)) + com.yandex.metrica.impl.ob.b.d(2);
                if (this.d != 0) {
                    c += com.yandex.metrica.impl.ob.b.c(3, this.d);
                }
                if (this.e != 0) {
                    c += com.yandex.metrica.impl.ob.b.e(4, this.e);
                }
                if (this.f != 0) {
                    c += com.yandex.metrica.impl.ob.b.e(5, this.f);
                }
                if (this.g != 0) {
                    c += com.yandex.metrica.impl.ob.b.e(6, this.g);
                }
                if (this.h != 0) {
                    c += com.yandex.metrica.impl.ob.b.d(7, this.h);
                }
                if (this.i != 0) {
                    return c + com.yandex.metrica.impl.ob.b.d(8, this.i);
                }
                return c;
            }
        }

        public static final class e extends com.yandex.metrica.impl.ob.d {
            public b[] b;
            public i[] c;
            public int d;
            public String e;

            public e() {
                d();
            }

            public e d() {
                this.b = b.d();
                this.c = i.d();
                this.d = 2;
                this.e = "";
                this.a = -1;
                return this;
            }

            public void a(com.yandex.metrica.impl.ob.b bVar) throws IOException {
                int i = 0;
                if (this.b != null && this.b.length > 0) {
                    for (com.yandex.metrica.impl.ob.d dVar : this.b) {
                        if (dVar != null) {
                            bVar.a(1, dVar);
                        }
                    }
                }
                if (this.c != null && this.c.length > 0) {
                    while (i < this.c.length) {
                        com.yandex.metrica.impl.ob.d dVar2 = this.c[i];
                        if (dVar2 != null) {
                            bVar.a(2, dVar2);
                        }
                        i++;
                    }
                }
                if (this.d != 2) {
                    bVar.a(3, this.d);
                }
                if (!this.e.equals("")) {
                    bVar.a(4, this.e);
                }
                super.a(bVar);
            }

            protected int c() {
                int i = 0;
                int c = super.c();
                if (this.b != null && this.b.length > 0) {
                    int i2 = c;
                    for (com.yandex.metrica.impl.ob.d dVar : this.b) {
                        if (dVar != null) {
                            i2 += com.yandex.metrica.impl.ob.b.b(1, dVar);
                        }
                    }
                    c = i2;
                }
                if (this.c != null && this.c.length > 0) {
                    while (i < this.c.length) {
                        com.yandex.metrica.impl.ob.d dVar2 = this.c[i];
                        if (dVar2 != null) {
                            c += com.yandex.metrica.impl.ob.b.b(2, dVar2);
                        }
                        i++;
                    }
                }
                if (this.d != 2) {
                    c += com.yandex.metrica.impl.ob.b.d(3, this.d);
                }
                if (this.e.equals("")) {
                    return c;
                }
                return c + com.yandex.metrica.impl.ob.b.b(4, this.e);
            }
        }

        public static final class f extends com.yandex.metrica.impl.ob.d {
            private static volatile f[] d;
            public String b;
            public String c;

            public static f[] d() {
                if (d == null) {
                    synchronized (com.yandex.metrica.impl.ob.c.a) {
                        if (d == null) {
                            d = new f[0];
                        }
                    }
                }
                return d;
            }

            public f() {
                e();
            }

            public f e() {
                this.b = "";
                this.c = "";
                this.a = -1;
                return this;
            }

            public void a(com.yandex.metrica.impl.ob.b bVar) throws IOException {
                bVar.a(1, this.b);
                bVar.a(2, this.c);
                super.a(bVar);
            }

            protected int c() {
                return (super.c() + com.yandex.metrica.impl.ob.b.b(1, this.b)) + com.yandex.metrica.impl.ob.b.b(2, this.c);
            }
        }

        public static final class g extends com.yandex.metrica.impl.ob.d {
            private static volatile g[] e;
            public long b;
            public b c;
            public a[] d;

            public static final class a extends com.yandex.metrica.impl.ob.d {
                private static volatile a[] m;
                public long b;
                public long c;
                public int d;
                public String e;
                public byte[] f;
                public d g;
                public e h;
                public String i;
                public a j;
                public int k;
                public int l;

                public static a[] d() {
                    if (m == null) {
                        synchronized (com.yandex.metrica.impl.ob.c.a) {
                            if (m == null) {
                                m = new a[0];
                            }
                        }
                    }
                    return m;
                }

                public a() {
                    e();
                }

                public a e() {
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = "";
                    this.f = com.yandex.metrica.impl.ob.f.b;
                    this.g = null;
                    this.h = null;
                    this.i = "";
                    this.j = null;
                    this.k = 0;
                    this.l = 0;
                    this.a = -1;
                    return this;
                }

                public void a(com.yandex.metrica.impl.ob.b bVar) throws IOException {
                    bVar.a(1, this.b);
                    bVar.a(2, this.c);
                    bVar.b(3, this.d);
                    if (!this.e.equals("")) {
                        bVar.a(4, this.e);
                    }
                    if (!Arrays.equals(this.f, com.yandex.metrica.impl.ob.f.b)) {
                        bVar.a(5, this.f);
                    }
                    if (this.g != null) {
                        bVar.a(6, this.g);
                    }
                    if (this.h != null) {
                        bVar.a(7, this.h);
                    }
                    if (!this.i.equals("")) {
                        bVar.a(8, this.i);
                    }
                    if (this.j != null) {
                        bVar.a(9, this.j);
                    }
                    if (this.k != 0) {
                        bVar.b(10, this.k);
                    }
                    if (this.l != 0) {
                        bVar.a(12, this.l);
                    }
                    super.a(bVar);
                }

                protected int c() {
                    int c = ((super.c() + com.yandex.metrica.impl.ob.b.c(1, this.b)) + com.yandex.metrica.impl.ob.b.c(2, this.c)) + com.yandex.metrica.impl.ob.b.e(3, this.d);
                    if (!this.e.equals("")) {
                        c += com.yandex.metrica.impl.ob.b.b(4, this.e);
                    }
                    if (!Arrays.equals(this.f, com.yandex.metrica.impl.ob.f.b)) {
                        c += com.yandex.metrica.impl.ob.b.b(5, this.f);
                    }
                    if (this.g != null) {
                        c += com.yandex.metrica.impl.ob.b.b(6, this.g);
                    }
                    if (this.h != null) {
                        c += com.yandex.metrica.impl.ob.b.b(7, this.h);
                    }
                    if (!this.i.equals("")) {
                        c += com.yandex.metrica.impl.ob.b.b(8, this.i);
                    }
                    if (this.j != null) {
                        c += com.yandex.metrica.impl.ob.b.b(9, this.j);
                    }
                    if (this.k != 0) {
                        c += com.yandex.metrica.impl.ob.b.e(10, this.k);
                    }
                    if (this.l != 0) {
                        return c + com.yandex.metrica.impl.ob.b.d(12, this.l);
                    }
                    return c;
                }
            }

            public static final class b extends com.yandex.metrica.impl.ob.d {
                public b b;
                public String c;
                public int d;

                public b() {
                    d();
                }

                public b d() {
                    this.b = null;
                    this.c = "";
                    this.d = 0;
                    this.a = -1;
                    return this;
                }

                public void a(com.yandex.metrica.impl.ob.b bVar) throws IOException {
                    if (this.b != null) {
                        bVar.a(1, this.b);
                    }
                    bVar.a(2, this.c);
                    if (this.d != 0) {
                        bVar.a(5, this.d);
                    }
                    super.a(bVar);
                }

                protected int c() {
                    int c = super.c();
                    if (this.b != null) {
                        c += com.yandex.metrica.impl.ob.b.b(1, this.b);
                    }
                    c += com.yandex.metrica.impl.ob.b.b(2, this.c);
                    if (this.d != 0) {
                        return c + com.yandex.metrica.impl.ob.b.d(5, this.d);
                    }
                    return c;
                }
            }

            public static g[] d() {
                if (e == null) {
                    synchronized (com.yandex.metrica.impl.ob.c.a) {
                        if (e == null) {
                            e = new g[0];
                        }
                    }
                }
                return e;
            }

            public g() {
                e();
            }

            public g e() {
                this.b = 0;
                this.c = null;
                this.d = a.d();
                this.a = -1;
                return this;
            }

            public void a(com.yandex.metrica.impl.ob.b bVar) throws IOException {
                bVar.a(1, this.b);
                if (this.c != null) {
                    bVar.a(2, this.c);
                }
                if (this.d != null && this.d.length > 0) {
                    for (com.yandex.metrica.impl.ob.d dVar : this.d) {
                        if (dVar != null) {
                            bVar.a(3, dVar);
                        }
                    }
                }
                super.a(bVar);
            }

            protected int c() {
                int c = super.c() + com.yandex.metrica.impl.ob.b.c(1, this.b);
                if (this.c != null) {
                    c += com.yandex.metrica.impl.ob.b.b(2, this.c);
                }
                if (this.d == null || this.d.length <= 0) {
                    return c;
                }
                int i = c;
                for (com.yandex.metrica.impl.ob.d dVar : this.d) {
                    if (dVar != null) {
                        i += com.yandex.metrica.impl.ob.b.b(3, dVar);
                    }
                }
                return i;
            }
        }

        public static final class h extends com.yandex.metrica.impl.ob.d {
            private static volatile h[] g;
            public int b;
            public int c;
            public String d;
            public boolean e;
            public String f;

            public static h[] d() {
                if (g == null) {
                    synchronized (com.yandex.metrica.impl.ob.c.a) {
                        if (g == null) {
                            g = new h[0];
                        }
                    }
                }
                return g;
            }

            public h() {
                e();
            }

            public h e() {
                this.b = 0;
                this.c = 0;
                this.d = "";
                this.e = false;
                this.f = "";
                this.a = -1;
                return this;
            }

            public void a(com.yandex.metrica.impl.ob.b bVar) throws IOException {
                if (this.b != 0) {
                    bVar.b(1, this.b);
                }
                if (this.c != 0) {
                    bVar.b(2, this.c);
                }
                if (!this.d.equals("")) {
                    bVar.a(3, this.d);
                }
                if (this.e) {
                    bVar.a(4, this.e);
                }
                if (!this.f.equals("")) {
                    bVar.a(5, this.f);
                }
                super.a(bVar);
            }

            protected int c() {
                int c = super.c();
                if (this.b != 0) {
                    c += com.yandex.metrica.impl.ob.b.e(1, this.b);
                }
                if (this.c != 0) {
                    c += com.yandex.metrica.impl.ob.b.e(2, this.c);
                }
                if (!this.d.equals("")) {
                    c += com.yandex.metrica.impl.ob.b.b(3, this.d);
                }
                if (this.e) {
                    c += com.yandex.metrica.impl.ob.b.e(4);
                }
                if (this.f.equals("")) {
                    return c;
                }
                return c + com.yandex.metrica.impl.ob.b.b(5, this.f);
            }
        }

        public static final class i extends com.yandex.metrica.impl.ob.d {
            private static volatile i[] f;
            public String b;
            public int c;
            public String d;
            public boolean e;

            public static i[] d() {
                if (f == null) {
                    synchronized (com.yandex.metrica.impl.ob.c.a) {
                        if (f == null) {
                            f = new i[0];
                        }
                    }
                }
                return f;
            }

            public i() {
                e();
            }

            public i e() {
                this.b = "";
                this.c = 0;
                this.d = "";
                this.e = false;
                this.a = -1;
                return this;
            }

            public void a(com.yandex.metrica.impl.ob.b bVar) throws IOException {
                bVar.a(1, this.b);
                if (this.c != 0) {
                    bVar.c(2, this.c);
                }
                if (!this.d.equals("")) {
                    bVar.a(3, this.d);
                }
                if (this.e) {
                    bVar.a(4, this.e);
                }
                super.a(bVar);
            }

            protected int c() {
                int c = super.c() + com.yandex.metrica.impl.ob.b.b(1, this.b);
                if (this.c != 0) {
                    c += com.yandex.metrica.impl.ob.b.f(2, this.c);
                }
                if (!this.d.equals("")) {
                    c += com.yandex.metrica.impl.ob.b.b(3, this.d);
                }
                if (this.e) {
                    return c + com.yandex.metrica.impl.ob.b.e(4);
                }
                return c;
            }
        }

        public a() {
            d();
        }

        public a d() {
            this.b = null;
            this.c = g.d();
            this.d = c.d();
            this.e = f.d();
            this.f = com.yandex.metrica.impl.ob.f.a;
            this.g = h.d();
            this.a = -1;
            return this;
        }

        public void a(com.yandex.metrica.impl.ob.b bVar) throws IOException {
            int i = 0;
            if (this.b != null) {
                bVar.a(1, this.b);
            }
            if (this.c != null && this.c.length > 0) {
                for (com.yandex.metrica.impl.ob.d dVar : this.c) {
                    if (dVar != null) {
                        bVar.a(3, dVar);
                    }
                }
            }
            if (this.d != null && this.d.length > 0) {
                for (com.yandex.metrica.impl.ob.d dVar2 : this.d) {
                    if (dVar2 != null) {
                        bVar.a(7, dVar2);
                    }
                }
            }
            if (this.e != null && this.e.length > 0) {
                for (com.yandex.metrica.impl.ob.d dVar22 : this.e) {
                    if (dVar22 != null) {
                        bVar.a(8, dVar22);
                    }
                }
            }
            if (this.f != null && this.f.length > 0) {
                for (String str : this.f) {
                    if (str != null) {
                        bVar.a(9, str);
                    }
                }
            }
            if (this.g != null && this.g.length > 0) {
                while (i < this.g.length) {
                    com.yandex.metrica.impl.ob.d dVar3 = this.g[i];
                    if (dVar3 != null) {
                        bVar.a(10, dVar3);
                    }
                    i++;
                }
            }
            super.a(bVar);
        }

        protected int c() {
            int i;
            int i2 = 0;
            int c = super.c();
            if (this.b != null) {
                c += com.yandex.metrica.impl.ob.b.b(1, this.b);
            }
            if (this.c != null && this.c.length > 0) {
                i = c;
                for (com.yandex.metrica.impl.ob.d dVar : this.c) {
                    if (dVar != null) {
                        i += com.yandex.metrica.impl.ob.b.b(3, dVar);
                    }
                }
                c = i;
            }
            if (this.d != null && this.d.length > 0) {
                i = c;
                for (com.yandex.metrica.impl.ob.d dVar2 : this.d) {
                    if (dVar2 != null) {
                        i += com.yandex.metrica.impl.ob.b.b(7, dVar2);
                    }
                }
                c = i;
            }
            if (this.e != null && this.e.length > 0) {
                i = c;
                for (com.yandex.metrica.impl.ob.d dVar22 : this.e) {
                    if (dVar22 != null) {
                        i += com.yandex.metrica.impl.ob.b.b(8, dVar22);
                    }
                }
                c = i;
            }
            if (this.f != null && this.f.length > 0) {
                int i3 = 0;
                int i4 = 0;
                for (String str : this.f) {
                    if (str != null) {
                        i4++;
                        i3 += com.yandex.metrica.impl.ob.b.b(str);
                    }
                }
                c = (c + i3) + (i4 * 1);
            }
            if (this.g != null && this.g.length > 0) {
                while (i2 < this.g.length) {
                    com.yandex.metrica.impl.ob.d dVar3 = this.g[i2];
                    if (dVar3 != null) {
                        c += com.yandex.metrica.impl.ob.b.b(10, dVar3);
                    }
                    i2++;
                }
            }
            return c;
        }
    }

    public static final class b extends d {
        public long b;
        public int c;
        public long d;

        public b() {
            d();
        }

        public b d() {
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.a = -1;
            return this;
        }

        public void a(com.yandex.metrica.impl.ob.b bVar) throws IOException {
            bVar.a(1, this.b);
            bVar.c(2, this.c);
            if (this.d != 0) {
                bVar.b(3, this.d);
            }
            super.a(bVar);
        }

        protected int c() {
            int c = (super.c() + com.yandex.metrica.impl.ob.b.c(1, this.b)) + com.yandex.metrica.impl.ob.b.f(2, this.c);
            if (this.d != 0) {
                return c + com.yandex.metrica.impl.ob.b.d(3, this.d);
            }
            return c;
        }
    }
}
