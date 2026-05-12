package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.zzo;
import com.google.android.gms.internal.zzin;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;

@zzin
public final class LargeParcelTeleporter extends AbstractSafeParcelable {
    public static final Creator<LargeParcelTeleporter> CREATOR = new zzm();
    final int mVersionCode;
    ParcelFileDescriptor zzccz;
    private Parcelable zzcda;
    private boolean zzcdb;

    LargeParcelTeleporter(int i, ParcelFileDescriptor parcelFileDescriptor) {
        this.mVersionCode = i;
        this.zzccz = parcelFileDescriptor;
        this.zzcda = null;
        this.zzcdb = true;
    }

    public LargeParcelTeleporter(SafeParcelable safeParcelable) {
        this.mVersionCode = 1;
        this.zzccz = null;
        this.zzcda = safeParcelable;
        this.zzcdb = false;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzccz == null) {
            Parcel obtain = Parcel.obtain();
            try {
                this.zzcda.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                this.zzccz = zzi(marshall);
            } finally {
                obtain.recycle();
            }
        }
        zzm.zza(this, parcel, i);
    }

    public <T extends SafeParcelable> T zza(Creator<T> creator) {
        if (this.zzcdb) {
            if (this.zzccz == null) {
                zzb.e("File descriptor is empty, returning null.");
                return null;
            }
            Closeable dataInputStream = new DataInputStream(new AutoCloseInputStream(this.zzccz));
            try {
                byte[] bArr = new byte[dataInputStream.readInt()];
                dataInputStream.readFully(bArr, 0, bArr.length);
                zzo.zzb(dataInputStream);
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.unmarshall(bArr, 0, bArr.length);
                    obtain.setDataPosition(0);
                    this.zzcda = (SafeParcelable) creator.createFromParcel(obtain);
                    this.zzcdb = false;
                } finally {
                    obtain.recycle();
                }
            } catch (Throwable e) {
                throw new IllegalStateException("Could not read from parcel file descriptor", e);
            } catch (Throwable th) {
                zzo.zzb(dataInputStream);
            }
        }
        return (SafeParcelable) this.zzcda;
    }

    protected <T> ParcelFileDescriptor zzi(byte[] bArr) {
        Throwable e;
        ParcelFileDescriptor parcelFileDescriptor = null;
        Closeable autoCloseOutputStream;
        try {
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            autoCloseOutputStream = new AutoCloseOutputStream(createPipe[1]);
            try {
                new Thread(new 1(this, autoCloseOutputStream, bArr)).start();
                return createPipe[0];
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            autoCloseOutputStream = parcelFileDescriptor;
            zzb.zzb("Error transporting the ad response", e);
            zzu.zzft().zzb(e, true);
            zzo.zzb(autoCloseOutputStream);
            return parcelFileDescriptor;
        }
    }
}
