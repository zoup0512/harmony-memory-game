package com.cube.memorygames.api.network.model;

import java.util.Date;

public class RetrofitOnlineMatch {
    private Date created;
    public String id;
    private String player1Sessions;
    private String player2Sessions;
    private String playerId1;
    private String playerId2;
    private String playerName1;
    private String playerName2;
    private String playerPhotoUrl1;
    private String playerPhotoUrl2;
    private String status;
    private int totalScorePlayer1;
    private int totalScorePlayer2;
    private String winnerUser;

    public String getId() {
        return this.id;
    }

    public String getPlayerId1() {
        return this.playerId1;
    }

    public String getPlayerId2() {
        return this.playerId2;
    }

    public String getPlayer1Sessions() {
        return this.player1Sessions;
    }

    public String getPlayer2Sessions() {
        return this.player2Sessions;
    }

    public Date getCreated() {
        return this.created;
    }

    public String getWinnerUser() {
        return this.winnerUser;
    }

    public String getStatus() {
        return this.status;
    }

    public String getPlayerName1() {
        return this.playerName1;
    }

    public String getPlayerName2() {
        return this.playerName2;
    }

    public String getPlayerPhotoUrl1() {
        return this.playerPhotoUrl1;
    }

    public String getPlayerPhotoUrl2() {
        return this.playerPhotoUrl2;
    }

    public int getTotalScorePlayer1() {
        return this.totalScorePlayer1;
    }

    public int getTotalScorePlayer2() {
        return this.totalScorePlayer2;
    }
}
