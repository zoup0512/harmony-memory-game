package com.cube.memorygames.api.network.body;

public class BodyRoundResults {
    public String gameId;
    public int roundNumber;
    public String userId;
    public int value;

    public BodyRoundResults(String userId, String gameId, int roundNumber, int value) {
        this.userId = userId;
        this.gameId = gameId;
        this.roundNumber = roundNumber;
        this.value = value;
    }
}
