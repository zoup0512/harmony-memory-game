package com.cube.memorygames.api.network.body;

import java.util.List;

public class BodyOnlineResults {
    public String gameId;
    public List<Integer> results;
    public String userId;

    public BodyOnlineResults(String userId, String gameId, List<Integer> results) {
        this.userId = userId;
        this.gameId = gameId;
        this.results = results;
    }
}
