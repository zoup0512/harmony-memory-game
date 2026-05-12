package com.cube.memorygames.pushes.event;

import com.cube.memorygames.MemoryApplicationModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StartGameEvent extends OnlineEvent {
    private static final String FIELD_GAMES_LIST = "FIELD_GAMES_LIST";
    private static final String FIELD_GAME_ID = "FIELD_GAME_ID";
    private static final String FIELD_GAME_SEED = "FIELD_GAME_SEED";
    private String gameId;
    private long gameSeed;
    private List<String> gamesList;

    private StartGameEvent(long gameSeed, String gameId, List<String> gamesList) {
        this.gameSeed = gameSeed;
        this.gameId = gameId;
        this.gamesList = gamesList;
    }

    public long getGameSeed() {
        return this.gameSeed;
    }

    public String getGameId() {
        return this.gameId;
    }

    public List<String> getGamesList() {
        return this.gamesList;
    }

    public static StartGameEvent create(String s) {
        if (s == null) {
            return null;
        }
        HashMap hashMap = (HashMap) MemoryApplicationModel.getInstance().getGsonParser().fromJson(s, HashMap.class);
        return new StartGameEvent(((Double) hashMap.get(FIELD_GAME_SEED)).longValue(), (String) hashMap.get(FIELD_GAME_ID), new ArrayList(Arrays.asList(((String) hashMap.get(FIELD_GAMES_LIST)).split(","))));
    }
}
