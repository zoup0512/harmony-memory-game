package com.cube.memorygames.pushes.event;

import com.cube.memorygames.MemoryApplicationModel;
import java.util.HashMap;

public class IntermediateResultsEvent extends OnlineEvent {
    private static final String FIELD_GAME_ID = "FIELD_GAME_ID";
    private static final String FIELD_RESULTS = "FIELD_RESULTS";
    private String gameId;
    private String resultsUser2;

    public IntermediateResultsEvent(String resultsUser2, String gameId) {
        this.gameId = gameId;
        this.resultsUser2 = resultsUser2;
    }

    public String getResultsUser2() {
        return this.resultsUser2;
    }

    public String getGameId() {
        return this.gameId;
    }

    public static IntermediateResultsEvent create(String s) {
        if (s == null) {
            return null;
        }
        HashMap hashMap = (HashMap) MemoryApplicationModel.getInstance().getGsonParser().fromJson(s, HashMap.class);
        return new IntermediateResultsEvent((String) hashMap.get(FIELD_RESULTS), (String) hashMap.get(FIELD_GAME_ID));
    }
}
