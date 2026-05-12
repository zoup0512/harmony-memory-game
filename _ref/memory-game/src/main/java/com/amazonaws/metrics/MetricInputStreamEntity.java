package com.amazonaws.metrics;

import android.support.v4.media.session.PlaybackStateCompat;
import com.amazonaws.internal.MetricAware;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.entity.InputStreamEntity;

public class MetricInputStreamEntity extends InputStreamEntity {
    private static final int BUFFER_SIZE = 2048;
    private final ByteThroughputHelper helper;

    public MetricInputStreamEntity(ThroughputMetricType metricType, InputStream instream, long length) {
        super(instream, length);
        this.helper = new ByteThroughputHelper(metricType);
    }

    public void writeTo(OutputStream outstream) throws IOException {
        if ((outstream instanceof MetricAware) && ((MetricAware) outstream).isMetricActivated()) {
            super.writeTo(outstream);
        } else {
            writeToWithMetrics(outstream);
        }
    }

    private void writeToWithMetrics(OutputStream outstream) throws IOException {
        if (outstream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        InputStream content = getContent();
        long length = getContentLength();
        InputStream instream = content;
        byte[] buffer = new byte[2048];
        int l;
        long startNano;
        if (length < 0) {
            while (true) {
                l = instream.read(buffer);
                if (l == -1) {
                    break;
                }
                startNano = this.helper.startTiming();
                outstream.write(buffer, 0, l);
                this.helper.increment(l, startNano);
            }
        } else {
            long remaining = length;
            while (remaining > 0) {
                try {
                    l = instream.read(buffer, 0, (int) Math.min(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, remaining));
                    if (l == -1) {
                        break;
                    }
                    startNano = this.helper.startTiming();
                    outstream.write(buffer, 0, l);
                    this.helper.increment(l, startNano);
                    remaining -= (long) l;
                } catch (Throwable th) {
                    this.helper.reportMetrics();
                    instream.close();
                }
            }
        }
        this.helper.reportMetrics();
        instream.close();
    }
}
