package com.amazonaws.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class DateUtils {
    public static final String ALTERNATE_ISO8601_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String COMPRESSED_DATE_PATTERN = "yyyyMMdd'T'HHmmss'Z'";
    private static final TimeZone GMT_TIMEZONE = TimeZone.getTimeZone("GMT");
    public static final String ISO8601_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String RFC822_DATE_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";
    private static final Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap();

    private static ThreadLocal<SimpleDateFormat> getSimpleDateFormat(final String pattern) {
        ThreadLocal<SimpleDateFormat> sdf = (ThreadLocal) sdfMap.get(pattern);
        if (sdf == null) {
            synchronized (sdfMap) {
                try {
                    sdf = (ThreadLocal) sdfMap.get(pattern);
                    if (sdf == null) {
                        ThreadLocal<SimpleDateFormat> sdf2 = new ThreadLocal<SimpleDateFormat>() {
                            protected SimpleDateFormat initialValue() {
                                SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
                                sdf.setTimeZone(DateUtils.GMT_TIMEZONE);
                                sdf.setLenient(false);
                                return sdf;
                            }
                        };
                        try {
                            sdfMap.put(pattern, sdf2);
                            sdf = sdf2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            sdf = sdf2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return sdf;
    }

    public static Date parse(String pattern, String dateString) {
        try {
            return ((SimpleDateFormat) getSimpleDateFormat(pattern).get()).parse(dateString);
        } catch (ParseException pe) {
            throw new IllegalArgumentException(pe);
        }
    }

    public static String format(String pattern, Date date) {
        return ((SimpleDateFormat) getSimpleDateFormat(pattern).get()).format(date);
    }

    public static Date parseISO8601Date(String dateString) {
        try {
            return parse(ISO8601_DATE_PATTERN, dateString);
        } catch (IllegalArgumentException e) {
            return parse(ALTERNATE_ISO8601_DATE_PATTERN, dateString);
        }
    }

    public static String formatISO8601Date(Date date) {
        return format(ISO8601_DATE_PATTERN, date);
    }

    public static Date parseRFC822Date(String dateString) {
        return parse(RFC822_DATE_PATTERN, dateString);
    }

    public static String formatRFC822Date(Date date) {
        return format(RFC822_DATE_PATTERN, date);
    }

    public static Date parseCompressedISO8601Date(String dateString) {
        return parse(COMPRESSED_DATE_PATTERN, dateString);
    }
}
