package com.unity3d.ads.misc;

import android.os.Handler;
import android.os.Looper;
import com.facebook.appevents.AppEventsConstants;
import com.unity3d.ads.log.DeviceLog;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.security.MessageDigest;

public class Utilities {
    public static void runOnUiThread(Runnable runnable) {
        runOnUiThread(runnable, 0);
    }

    public static void runOnUiThread(Runnable runnable, long j) {
        Handler handler = new Handler(Looper.getMainLooper());
        if (j > 0) {
            handler.postDelayed(runnable, j);
        } else {
            handler.post(runnable);
        }
    }

    public static String Sha256(String str) {
        return Sha256(str.getBytes());
    }

    public static String Sha256(byte[] bArr) {
        String str = null;
        if (bArr == null) {
            return str;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(bArr, 0, bArr.length);
            return toHexString(instance.digest());
        } catch (Exception e) {
            DeviceLog.exception("SHA-256 algorithm not found", e);
            return str;
        }
    }

    public static String toHexString(byte[] bArr) {
        int length = bArr.length;
        String str = "";
        int i = 0;
        while (i < length) {
            int i2 = bArr[i] & 255;
            if (i2 <= 15) {
                str = str + AppEventsConstants.EVENT_PARAM_VALUE_NO;
            }
            i++;
            str = str + Integer.toHexString(i2);
        }
        return str;
    }

    public static boolean writeFile(File file, String str) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        boolean z = false;
        if (file != null) {
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(str.getBytes());
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e2) {
                            DeviceLog.exception("Error closing FileOutputStream", e2);
                            z = true;
                        }
                    }
                    z = true;
                } catch (Exception e3) {
                    e = e3;
                    try {
                        DeviceLog.exception("Could not write file", e);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e4) {
                                DeviceLog.exception("Error closing FileOutputStream", e4);
                            }
                        }
                        if (z) {
                            DeviceLog.debug("Wrote file: " + file.getAbsolutePath());
                        }
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e42) {
                                DeviceLog.exception("Error closing FileOutputStream", e42);
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e42 = e5;
                fileOutputStream = null;
                DeviceLog.exception("Could not write file", e42);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (z) {
                    DeviceLog.debug("Wrote file: " + file.getAbsolutePath());
                }
                return z;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
            if (z) {
                DeviceLog.debug("Wrote file: " + file.getAbsolutePath());
            }
        }
        return z;
    }

    public static String readFile(File file) {
        FileReader fileReader;
        BufferedReader bufferedReader;
        Exception e;
        String str = null;
        if (file != null) {
            String str2 = "";
            if (file.exists() && file.canRead()) {
                try {
                    fileReader = new FileReader(file);
                    try {
                        bufferedReader = new BufferedReader(fileReader);
                        while (true) {
                            try {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                str2 = str2.concat(readLine);
                            } catch (Exception e2) {
                                e = e2;
                            }
                        }
                        str = str2;
                    } catch (Exception e3) {
                        e = e3;
                        bufferedReader = null;
                        DeviceLog.exception("Problem reading file", e);
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e4) {
                                DeviceLog.exception("Couldn't close BufferedReader", e4);
                            }
                        }
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Exception e42) {
                                DeviceLog.exception("Couldn't close FileReader", e42);
                            }
                        }
                        return str;
                    }
                } catch (Exception e5) {
                    e42 = e5;
                    fileReader = null;
                    bufferedReader = null;
                    DeviceLog.exception("Problem reading file", e42);
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    return str;
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } else {
                DeviceLog.error("File did not exist or couldn't be read");
            }
        }
        return str;
    }

    public static byte[] readFileBytes(File file) {
        if (file == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.close();
                fileInputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
