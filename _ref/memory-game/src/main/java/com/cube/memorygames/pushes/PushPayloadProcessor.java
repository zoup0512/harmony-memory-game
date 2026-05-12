package com.cube.memorygames.pushes;

import com.cube.memorygames.pushes.event.ErrorEvent;
import com.cube.memorygames.pushes.event.GameEndedEvent;
import com.cube.memorygames.pushes.event.IntermediateResultsEvent;
import com.cube.memorygames.pushes.event.MatchFoundEvent;
import com.cube.memorygames.pushes.event.OnlineEvent;
import com.cube.memorygames.pushes.event.StartGameEvent;
import com.cube.memorygames.pushes.event.UserConfirmedGameEvent;
import java.util.Map;

public class PushPayloadProcessor {
    public static final String PROPERTY_MESSAGE_TYPE = "type";
    public static final String PROPERTY_MESSAGE_VALUES = "values";
    public static final String TYPE_ERROR = "TYPE_ERROR";
    public static final String TYPE_GAME_ENDED = "TYPE_GAME_ENDED";
    public static final String TYPE_INTERMEDIATE_RESULTS = "TYPE_INTERMEDIATE_RESULTS";
    public static final String TYPE_MATCH_FOUND = "TYPE_MATCH_FOUND";
    public static final String TYPE_START_GAME = "TYPE_START_GAME";
    public static final String TYPE_USER_CONFIRMED = "TYPE_USER_CONFIRMED";

    public static OnlineEvent processPayload(Map<String, String> data) {
        String type = (String) data.get("type");
        Object obj = -1;
        switch (type.hashCode()) {
            case -1615958028:
                if (type.equals(TYPE_START_GAME)) {
                    obj = 3;
                    break;
                }
                break;
            case -1446950283:
                if (type.equals(TYPE_INTERMEDIATE_RESULTS)) {
                    obj = 5;
                    break;
                }
                break;
            case 308211875:
                if (type.equals(TYPE_ERROR)) {
                    obj = 1;
                    break;
                }
                break;
            case 660615875:
                if (type.equals(TYPE_MATCH_FOUND)) {
                    obj = null;
                    break;
                }
                break;
            case 1629453168:
                if (type.equals(TYPE_USER_CONFIRMED)) {
                    obj = 2;
                    break;
                }
                break;
            case 1801462322:
                if (type.equals(TYPE_GAME_ENDED)) {
                    obj = 4;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return MatchFoundEvent.create((String) data.get(PROPERTY_MESSAGE_VALUES));
            case 1:
                return ErrorEvent.create((String) data.get(PROPERTY_MESSAGE_VALUES));
            case 2:
                return UserConfirmedGameEvent.create((String) data.get(PROPERTY_MESSAGE_VALUES));
            case 3:
                return StartGameEvent.create((String) data.get(PROPERTY_MESSAGE_VALUES));
            case 4:
                return GameEndedEvent.create((String) data.get(PROPERTY_MESSAGE_VALUES));
            case 5:
                return IntermediateResultsEvent.create((String) data.get(PROPERTY_MESSAGE_VALUES));
            default:
                return null;
        }
    }
}
