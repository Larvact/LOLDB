package org.toby.valueobject.csvobjects.databasetransfer.player;

public abstract class Player {

    private long gameId;
    private byte teamId;
    private byte positionId;
    private int championId;
    private int bannedChampionId;

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public byte getTeamId() {
        return teamId;
    }

    public void setTeamId(byte teamId) {
        this.teamId = teamId;
    }

    public byte getPositionId() {
        return positionId;
    }

    public void setPositionId(byte positionId) {
        this.positionId = positionId;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public int getBannedChampionId() {
        return bannedChampionId;
    }

    public void setBannedChampionId(int bannedChampionId) {
        this.bannedChampionId = bannedChampionId;
    }
}
