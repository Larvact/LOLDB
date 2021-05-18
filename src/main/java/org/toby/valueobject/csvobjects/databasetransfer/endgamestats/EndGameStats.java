package org.toby.valueobject.csvobjects.databasetransfer.endgamestats;


public abstract class EndGameStats {

    private long gameId;
    private byte teamId;
    private byte towerKills;
    private byte inhibitorKills;
    private byte baronKills;
    private byte dragonKills;
    private byte riftHeraldKills;

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

    public byte getTowerKills() {
        return towerKills;
    }

    public void setTowerKills(byte towerKills) {
        this.towerKills = towerKills;
    }

    public byte getInhibitorKills() {
        return inhibitorKills;
    }

    public void setInhibitorKills(byte inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    public byte getBaronKills() {
        return baronKills;
    }

    public void setBaronKills(byte baronKills) {
        this.baronKills = baronKills;
    }

    public byte getDragonKills() {
        return dragonKills;
    }

    public void setDragonKills(byte dragonKills) {
        this.dragonKills = dragonKills;
    }

    public byte getRiftHeraldKills() {
        return riftHeraldKills;
    }

    public void setRiftHeraldKills(byte riftHeraldKills) {
        this.riftHeraldKills = riftHeraldKills;
    }
}
