package io.fabric.sdk.android.services.common;

import com.cube.memorygames.games.Game1MemoryGridActivity;

public class ResponseParser {
    public static final int ResponseActionDiscard = 0;
    public static final int ResponseActionRetry = 1;

    public static int parse(int statusCode) {
        if (statusCode >= 200 && statusCode <= 299) {
            return 0;
        }
        if (statusCode >= 300 && statusCode <= 399) {
            return 1;
        }
        if (statusCode >= Game1MemoryGridActivity.START_ANIMATION_DURATION && statusCode <= 499) {
            return 0;
        }
        if (statusCode >= 500) {
            return 1;
        }
        return 1;
    }
}
