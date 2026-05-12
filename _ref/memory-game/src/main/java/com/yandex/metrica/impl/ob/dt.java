package com.yandex.metrica.impl.ob;

import java.io.IOException;
import java.security.GeneralSecurityException;

class dt {
    private static ea a;
    private static dr b;
    private static el c;

    private static class a implements dr {
        private static final String[] a = new String[]{"LNFe+yc4/NZbJVynpxAeAd+brU3EPwGbtwF6VeUjI/Y=", "PL1/TTDEe9Cm2lb2X0tixyQC7zaPREm/V0IHJscTCmw=", "+B0DgmKB5hWEuHib00m2jvCJWBlOYI0NGTMmVjaVrJA=", "dy/Myn0WRtYGKBNP8ubn9boJWJi+WWmLzp0V+W9pqfM=", "OB84k4abNNzWpMVBdhI+TSgQmCqTKdPPQrwq6j4YdMU=", "AZQG1XXPKFo8LYu/gTPgz65IOcmcwYFb3yREhyWefNI=", "iZEDYF5LpvyxpOX9+x3+qDBXhdByZOUFatBA3JgW7sY=", "IQBnNBEiFuhj+8x6X8XLgh01V9Ic5/V3IRQLNFFc7v4=", "LvRiGEjRqfzurezaWuj8Wie2gyHMrW5Q06LspMnox7A="};
        private final du b;
        private final du c;

        private a(dx dxVar) throws IOException {
            do dgVar = new dg(dxVar.b(), "lib");
            this.b = new du(dgVar, "LIB-BLACK");
            this.c = new du(dgVar, "LIB-TRUST", a);
        }

        public du a() {
            return this.b;
        }

        public du b() {
            throw new UnsupportedOperationException("white list isn't supported in shared container");
        }

        public du c() {
            return this.c;
        }
    }

    static synchronized ea a(dx dxVar) {
        ea eaVar;
        synchronized (dt.class) {
            if (a == null) {
                a = new ea(dxVar, b(dxVar), c(dxVar), new dw());
            }
            eaVar = a;
        }
        return eaVar;
    }

    static synchronized dr b(dx dxVar) {
        dr drVar;
        synchronized (dt.class) {
            if (b == null) {
                try {
                    b = new a(dxVar);
                } catch (IOException e) {
                    b = new dm();
                }
            }
            drVar = b;
        }
        return drVar;
    }

    static synchronized el c(dx dxVar) {
        el elVar;
        synchronized (dt.class) {
            if (c == null) {
                try {
                    c = dxVar.d();
                } catch (GeneralSecurityException e) {
                } catch (IOException e2) {
                }
            }
            elVar = c;
        }
        return elVar;
    }
}
