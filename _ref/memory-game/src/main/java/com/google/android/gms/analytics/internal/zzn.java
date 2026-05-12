package com.google.android.gms.analytics.internal;

import android.content.Context;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.zzab;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class zzn extends zzd {
    private volatile String zzcvi;
    private Future<String> zzcyu;

    protected zzn(zzf com_google_android_gms_analytics_internal_zzf) {
        super(com_google_android_gms_analytics_internal_zzf);
    }

    private String zzaay() {
        String zzaaz = zzaaz();
        try {
            return !zzi(zzyz().getContext(), zzaaz) ? AppEventsConstants.EVENT_PARAM_VALUE_NO : zzaaz;
        } catch (Exception e) {
            zze("Error saving clientId file", e);
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
    }

    private boolean zzi(Context context, String str) {
        zzab.zzhr(str);
        zzab.zzhj("ClientId should be saved from worker thread");
        FileOutputStream fileOutputStream = null;
        try {
            zza("Storing clientId", str);
            fileOutputStream = context.openFileOutput("gaClientId", 0);
            fileOutputStream.write(str.getBytes());
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    zze("Failed to close clientId writing stream", e);
                }
            }
            return true;
        } catch (FileNotFoundException e2) {
            zze("Error creating clientId file", e2);
            if (fileOutputStream == null) {
                return false;
            }
            try {
                fileOutputStream.close();
                return false;
            } catch (IOException e3) {
                zze("Failed to close clientId writing stream", e3);
                return false;
            }
        } catch (IOException e32) {
            zze("Error writing to clientId file", e32);
            if (fileOutputStream == null) {
                return false;
            }
            try {
                fileOutputStream.close();
                return false;
            } catch (IOException e322) {
                zze("Failed to close clientId writing stream", e322);
                return false;
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e3222) {
                    zze("Failed to close clientId writing stream", e3222);
                }
            }
        }
    }

    public String zzaav() {
        String str;
        zzzg();
        synchronized (this) {
            if (this.zzcvi == null) {
                this.zzcyu = zzyz().zzc(new Callable<String>(this) {
                    final /* synthetic */ zzn zzcyv;

                    {
                        this.zzcyv = r1;
                    }

                    public /* synthetic */ Object call() throws Exception {
                        return zzaba();
                    }

                    public String zzaba() throws Exception {
                        return this.zzcyv.zzaax();
                    }
                });
            }
            if (this.zzcyu != null) {
                try {
                    this.zzcvi = (String) this.zzcyu.get();
                } catch (InterruptedException e) {
                    zzd("ClientId loading or generation was interrupted", e);
                    this.zzcvi = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                } catch (ExecutionException e2) {
                    zze("Failed to load or generate client id", e2);
                    this.zzcvi = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                }
                if (this.zzcvi == null) {
                    this.zzcvi = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                }
                zza("Loaded clientId", this.zzcvi);
                this.zzcyu = null;
            }
            str = this.zzcvi;
        }
        return str;
    }

    String zzaaw() {
        synchronized (this) {
            this.zzcvi = null;
            this.zzcyu = zzyz().zzc(new Callable<String>(this) {
                final /* synthetic */ zzn zzcyv;

                {
                    this.zzcyv = r1;
                }

                public /* synthetic */ Object call() throws Exception {
                    return zzaba();
                }

                public String zzaba() throws Exception {
                    return this.zzcyv.zzaay();
                }
            });
        }
        return zzaav();
    }

    String zzaax() {
        String zzba = zzba(zzyz().getContext());
        return zzba == null ? zzaay() : zzba;
    }

    protected String zzaaz() {
        return UUID.randomUUID().toString().toLowerCase();
    }

    protected String zzba(Context context) {
        FileInputStream openFileInput;
        FileInputStream fileInputStream;
        Object e;
        Throwable th;
        zzab.zzhj("ClientId should be loaded from worker thread");
        try {
            openFileInput = context.openFileInput("gaClientId");
            try {
                byte[] bArr = new byte[36];
                int read = openFileInput.read(bArr, 0, 36);
                if (openFileInput.available() > 0) {
                    zzek("clientId file seems corrupted, deleting it.");
                    openFileInput.close();
                    context.deleteFile("gaClientId");
                    if (openFileInput == null) {
                        return null;
                    }
                    try {
                        openFileInput.close();
                        return null;
                    } catch (IOException e2) {
                        zze("Failed to close client id reading stream", e2);
                        return null;
                    }
                } else if (read < 14) {
                    zzek("clientId file is empty, deleting it.");
                    openFileInput.close();
                    context.deleteFile("gaClientId");
                    if (openFileInput == null) {
                        return null;
                    }
                    try {
                        openFileInput.close();
                        return null;
                    } catch (IOException e22) {
                        zze("Failed to close client id reading stream", e22);
                        return null;
                    }
                } else {
                    openFileInput.close();
                    String str = new String(bArr, 0, read);
                    zza("Read client id from disk", str);
                    if (openFileInput != null) {
                        try {
                            openFileInput.close();
                        } catch (IOException e3) {
                            zze("Failed to close client id reading stream", e3);
                        }
                    }
                    return str;
                }
            } catch (FileNotFoundException e4) {
                fileInputStream = openFileInput;
                if (fileInputStream != null) {
                    return null;
                }
                try {
                    fileInputStream.close();
                    return null;
                } catch (IOException e222) {
                    zze("Failed to close client id reading stream", e222);
                    return null;
                }
            } catch (IOException e5) {
                e = e5;
                try {
                    zze("Error reading client id file, deleting it", e);
                    context.deleteFile("gaClientId");
                    if (openFileInput != null) {
                        return null;
                    }
                    try {
                        openFileInput.close();
                        return null;
                    } catch (IOException e2222) {
                        zze("Failed to close client id reading stream", e2222);
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (openFileInput != null) {
                        try {
                            openFileInput.close();
                        } catch (IOException e22222) {
                            zze("Failed to close client id reading stream", e22222);
                        }
                    }
                    throw th;
                }
            }
        } catch (FileNotFoundException e6) {
            fileInputStream = null;
            if (fileInputStream != null) {
                return null;
            }
            fileInputStream.close();
            return null;
        } catch (IOException e7) {
            e = e7;
            openFileInput = null;
            zze("Error reading client id file, deleting it", e);
            context.deleteFile("gaClientId");
            if (openFileInput != null) {
                return null;
            }
            openFileInput.close();
            return null;
        } catch (Throwable th3) {
            openFileInput = null;
            th = th3;
            if (openFileInput != null) {
                openFileInput.close();
            }
            throw th;
        }
    }

    protected void zzwv() {
    }
}
