package com.cube.memorygames.api.network.body;

public class BodyNoResults {
    public String gameId;
    public String userId;

    public BodyNoResults(String userId, String gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }
}
