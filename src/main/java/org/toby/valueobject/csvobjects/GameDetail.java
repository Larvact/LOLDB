package org.toby.valueobject.csvobjects;

import java.util.List;
import java.util.Objects;

public class GameDetail {

    private long newId;
    private long oldId;
    private long creationTime;
    private short gameDuration;
    private byte seasonId;
    private byte winner;
    private byte firstBlood;
    private byte firstTower;
    private byte firstInhibitor;
    private byte firstBaron;
    private byte firstDragon;
    private byte firstRiftHerald;
    private int t1_champ1id;
    private int t1_champ1_sum1;
    private int t1_champ1_sum2;
    private int t1_champ2id;
    private int t1_champ2_sum1;
    private int t1_champ2_sum2;
    private int t1_champ3id;
    private int t1_champ3_sum1;
    private int t1_champ3_sum2;
    private int t1_champ4id;
    private int t1_champ4_sum1;
    private int t1_champ4_sum2;
    private int t1_champ5id;
    private int t1_champ5_sum1;
    private int t1_champ5_sum2;
    private byte t1_towerKills;
    private byte t1_inhibitorKills;
    private byte t1_baronKills;
    private byte t1_dragonKills;
    private byte t1_riftHeraldKills;
    private int t1_ban1;
    private int t1_ban2;
    private int t1_ban3;
    private int t1_ban4;
    private int t1_ban5;
    private int t2_champ1id;
    private int t2_champ1_sum1;
    private int t2_champ1_sum2;
    private int t2_champ2id;
    private int t2_champ2_sum1;
    private int t2_champ2_sum2;
    private int t2_champ3id;
    private int t2_champ3_sum1;
    private int t2_champ3_sum2;
    private int t2_champ4id;
    private int t2_champ4_sum1;
    private int t2_champ4_sum2;
    private int t2_champ5id;
    private int t2_champ5_sum1;
    private int t2_champ5_sum2;
    private byte t2_towerKills;
    private byte t2_inhibitorKills;
    private byte t2_baronKills;
    private byte t2_dragonKills;
    private byte t2_riftHeraldKills;
    private int t2_ban1;
    private int t2_ban2;
    private int t2_ban3;
    private int t2_ban4;
    private int t2_ban5;

    public GameDetail(long oldId, long creationTime, short gameDuration, byte seasonId, byte winner, byte firstBlood, byte firstTower, byte firstInhibitor, byte firstBaron, byte firstDragon, byte firstRiftHerald, short t1_champ1id, byte t1_champ1_sum1, byte t1_champ1_sum2, short t1_champ2id, byte t1_champ2_sum1, byte t1_champ2_sum2, short t1_champ3id, byte t1_champ3_sum1, byte t1_champ3_sum2, short t1_champ4id, byte t1_champ4_sum1, byte t1_champ4_sum2, short t1_champ5id, byte t1_champ5_sum1, byte t1_champ5_sum2, byte t1_towerKills, byte t1_inhibitorKills, byte t1_baronKills, byte t1_dragonKills, byte t1_riftHeraldKills, short t1_ban1, short t1_ban2, short t1_ban3, short t1_ban4, short t1_ban5, short t2_champ1id, byte t2_champ1_sum1, byte t2_champ1_sum2, short t2_champ2id, byte t2_champ2_sum1, byte t2_champ2_sum2, short t2_champ3id, byte t2_champ3_sum1, byte t2_champ3_sum2, short t2_champ4id, byte t2_champ4_sum1, byte t2_champ4_sum2, short t2_champ5id, byte t2_champ5_sum1, byte t2_champ5_sum2, byte t2_towerKills, byte t2_inhibitorKills, byte t2_baronKills, byte t2_dragonKills, byte t2_riftHeraldKills, short t2_ban1, short t2_ban2, short t2_ban3, short t2_ban4, short t2_ban5) {
        this.oldId = oldId;
        this.creationTime = creationTime;
        this.gameDuration = gameDuration;
        this.seasonId = seasonId;
        this.winner = winner;
        this.firstBlood = firstBlood;
        this.firstTower = firstTower;
        this.firstInhibitor = firstInhibitor;
        this.firstBaron = firstBaron;
        this.firstDragon = firstDragon;
        this.firstRiftHerald = firstRiftHerald;
        this.t1_champ1id = t1_champ1id;
        this.t1_champ1_sum1 = t1_champ1_sum1;
        this.t1_champ1_sum2 = t1_champ1_sum2;
        this.t1_champ2id = t1_champ2id;
        this.t1_champ2_sum1 = t1_champ2_sum1;
        this.t1_champ2_sum2 = t1_champ2_sum2;
        this.t1_champ3id = t1_champ3id;
        this.t1_champ3_sum1 = t1_champ3_sum1;
        this.t1_champ3_sum2 = t1_champ3_sum2;
        this.t1_champ4id = t1_champ4id;
        this.t1_champ4_sum1 = t1_champ4_sum1;
        this.t1_champ4_sum2 = t1_champ4_sum2;
        this.t1_champ5id = t1_champ5id;
        this.t1_champ5_sum1 = t1_champ5_sum1;
        this.t1_champ5_sum2 = t1_champ5_sum2;
        this.t1_towerKills = t1_towerKills;
        this.t1_inhibitorKills = t1_inhibitorKills;
        this.t1_baronKills = t1_baronKills;
        this.t1_dragonKills = t1_dragonKills;
        this.t1_riftHeraldKills = t1_riftHeraldKills;
        this.t1_ban1 = t1_ban1;
        this.t1_ban2 = t1_ban2;
        this.t1_ban3 = t1_ban3;
        this.t1_ban4 = t1_ban4;
        this.t1_ban5 = t1_ban5;
        this.t2_champ1id = t2_champ1id;
        this.t2_champ1_sum1 = t2_champ1_sum1;
        this.t2_champ1_sum2 = t2_champ1_sum2;
        this.t2_champ2id = t2_champ2id;
        this.t2_champ2_sum1 = t2_champ2_sum1;
        this.t2_champ2_sum2 = t2_champ2_sum2;
        this.t2_champ3id = t2_champ3id;
        this.t2_champ3_sum1 = t2_champ3_sum1;
        this.t2_champ3_sum2 = t2_champ3_sum2;
        this.t2_champ4id = t2_champ4id;
        this.t2_champ4_sum1 = t2_champ4_sum1;
        this.t2_champ4_sum2 = t2_champ4_sum2;
        this.t2_champ5id = t2_champ5id;
        this.t2_champ5_sum1 = t2_champ5_sum1;
        this.t2_champ5_sum2 = t2_champ5_sum2;
        this.t2_towerKills = t2_towerKills;
        this.t2_inhibitorKills = t2_inhibitorKills;
        this.t2_baronKills = t2_baronKills;
        this.t2_dragonKills = t2_dragonKills;
        this.t2_riftHeraldKills = t2_riftHeraldKills;
        this.t2_ban1 = t2_ban1;
        this.t2_ban2 = t2_ban2;
        this.t2_ban3 = t2_ban3;
        this.t2_ban4 = t2_ban4;
        this.t2_ban5 = t2_ban5;
    }

    public GameDetail(List<String> gameDetails) {
        this.oldId = Long.parseLong(gameDetails.get(0));
        this.creationTime = Long.parseLong(gameDetails.get(1));
        this.gameDuration = Short.parseShort(gameDetails.get(2));
        this.seasonId = Byte.parseByte(gameDetails.get(3));
        this.winner = Byte.parseByte(gameDetails.get(4));
        this.firstBlood = Byte.parseByte(gameDetails.get(5));
        this.firstTower = Byte.parseByte(gameDetails.get(6));
        this.firstInhibitor = Byte.parseByte(gameDetails.get(7));
        this.firstBaron = Byte.parseByte(gameDetails.get(8));
        this.firstDragon = Byte.parseByte(gameDetails.get(9));
        this.firstRiftHerald = Byte.parseByte(gameDetails.get(10));
        this.t1_champ1id = Short.parseShort(gameDetails.get(11));
        this.t1_champ1_sum1 = Byte.parseByte(gameDetails.get(12));
        this.t1_champ1_sum2 = Byte.parseByte(gameDetails.get(13));
        this.t1_champ2id = Short.parseShort(gameDetails.get(14));
        this.t1_champ2_sum1 = Byte.parseByte(gameDetails.get(15));
        this.t1_champ2_sum2 = Byte.parseByte(gameDetails.get(16));
        this.t1_champ3id = Short.parseShort(gameDetails.get(17));
        this.t1_champ3_sum1 = Byte.parseByte(gameDetails.get(18));
        this.t1_champ3_sum2 = Byte.parseByte(gameDetails.get(19));
        this.t1_champ4id = Short.parseShort(gameDetails.get(20));
        this.t1_champ4_sum1 = Byte.parseByte(gameDetails.get(21));
        this.t1_champ4_sum2 = Byte.parseByte(gameDetails.get(22));
        this.t1_champ5id = Short.parseShort(gameDetails.get(23));
        this.t1_champ5_sum1 = Byte.parseByte(gameDetails.get(24));
        this.t1_champ5_sum2 = Byte.parseByte(gameDetails.get(25));
        this.t1_towerKills = Byte.parseByte(gameDetails.get(26));
        this.t1_inhibitorKills = Byte.parseByte(gameDetails.get(27));
        this.t1_baronKills = Byte.parseByte(gameDetails.get(28));
        this.t1_dragonKills = Byte.parseByte(gameDetails.get(29));
        this.t1_riftHeraldKills = Byte.parseByte(gameDetails.get(30));
        this.t1_ban1 = Short.parseShort(gameDetails.get(31));
        this.t1_ban2 = Short.parseShort(gameDetails.get(32));
        this.t1_ban3 = Short.parseShort(gameDetails.get(33));
        this.t1_ban4 = Short.parseShort(gameDetails.get(34));
        this.t1_ban5 = Short.parseShort(gameDetails.get(35));
        this.t2_champ1id = Short.parseShort(gameDetails.get(36));
        this.t2_champ1_sum1 = Byte.parseByte(gameDetails.get(37));
        this.t2_champ1_sum2 = Byte.parseByte(gameDetails.get(38));
        this.t2_champ2id = Short.parseShort(gameDetails.get(39));
        this.t2_champ2_sum1 = Byte.parseByte(gameDetails.get(40));
        this.t2_champ2_sum2 = Byte.parseByte(gameDetails.get(41));
        this.t2_champ3id = Short.parseShort(gameDetails.get(42));
        this.t2_champ3_sum1 = Byte.parseByte(gameDetails.get(43));
        this.t2_champ3_sum2 = Byte.parseByte(gameDetails.get(44));
        this.t2_champ4id = Short.parseShort(gameDetails.get(45));
        this.t2_champ4_sum1 = Byte.parseByte(gameDetails.get(46));
        this.t2_champ4_sum2 = Byte.parseByte(gameDetails.get(47));
        this.t2_champ5id = Short.parseShort(gameDetails.get(48));
        this.t2_champ5_sum1 = Byte.parseByte(gameDetails.get(49));
        this.t2_champ5_sum2 = Byte.parseByte(gameDetails.get(50));
        this.t2_towerKills = Byte.parseByte(gameDetails.get(51));
        this.t2_inhibitorKills = Byte.parseByte(gameDetails.get(52));
        this.t2_baronKills = Byte.parseByte(gameDetails.get(53));
        this.t2_dragonKills = Byte.parseByte(gameDetails.get(54));
        this.t2_riftHeraldKills = Byte.parseByte(gameDetails.get(55));
        this.t2_ban1 = Short.parseShort(gameDetails.get(56));
        this.t2_ban2 = Short.parseShort(gameDetails.get(57));
        this.t2_ban3 = Short.parseShort(gameDetails.get(58));
        this.t2_ban4 = Short.parseShort(gameDetails.get(59));;
        this.t2_ban5 = Short.parseShort(gameDetails.get(60));;
    }

    public long getNewId() {
        return newId;
    }

    public void setSeasonId(byte seasonId) {
        this.seasonId = seasonId;
    }

    public void setNewId(long newId) {
        this.newId = newId;
    }

    public void setWinner(byte winner) {
        this.winner = winner;
    }

    public void setFirstBlood(byte firstBlood) {
        this.firstBlood = firstBlood;
    }

    public void setFirstTower(byte firstTower) {
        this.firstTower = firstTower;
    }

    public void setFirstInhibitor(byte firstInhibitor) {
        this.firstInhibitor = firstInhibitor;
    }

    public void setFirstBaron(byte firstBaron) {
        this.firstBaron = firstBaron;
    }

    public void setFirstDragon(byte firstDragon) {
        this.firstDragon = firstDragon;
    }

    public void setFirstRiftHerald(byte firstRiftHerald) {
        this.firstRiftHerald = firstRiftHerald;
    }

    public void setT1_champ1id(int t1_champ1id) {
        this.t1_champ1id = t1_champ1id;
    }

    public void setT1_champ2id(int t1_champ2id) {
        this.t1_champ2id = t1_champ2id;
    }

    public void setT1_champ3id(int t1_champ3id) {
        this.t1_champ3id = t1_champ3id;
    }

    public void setT1_champ4id(int t1_champ4id) {
        this.t1_champ4id = t1_champ4id;
    }

    public void setT1_champ5id(int t1_champ5id) {
        this.t1_champ5id = t1_champ5id;
    }

    public void setT1_ban1(int t1_ban1) {
        this.t1_ban1 = t1_ban1;
    }

    public void setT1_ban2(int t1_ban2) {
        this.t1_ban2 = t1_ban2;
    }

    public void setT1_ban3(int t1_ban3) {
        this.t1_ban3 = t1_ban3;
    }

    public void setT1_ban4(int t1_ban4) {
        this.t1_ban4 = t1_ban4;
    }

    public void setT1_ban5(int t1_ban5) {
        this.t1_ban5 = t1_ban5;
    }

    public void setT2_champ1id(int t2_champ1id) {
        this.t2_champ1id = t2_champ1id;
    }

    public void setT2_champ2id(int t2_champ2id) {
        this.t2_champ2id = t2_champ2id;
    }

    public void setT2_champ3id(int t2_champ3id) {
        this.t2_champ3id = t2_champ3id;
    }

    public void setT2_champ4id(int t2_champ4id) {
        this.t2_champ4id = t2_champ4id;
    }

    public void setT2_champ5id(int t2_champ5id) {
        this.t2_champ5id = t2_champ5id;
    }

    public void setT2_ban1(int t2_ban1) {
        this.t2_ban1 = t2_ban1;
    }

    public void setT2_ban2(int t2_ban2) {
        this.t2_ban2 = t2_ban2;
    }

    public void setT2_ban3(int t2_ban3) {
        this.t2_ban3 = t2_ban3;
    }

    public void setT2_ban4(int t2_ban4) {
        this.t2_ban4 = t2_ban4;
    }

    public void setT2_ban5(int t2_ban5) {
        this.t2_ban5 = t2_ban5;
    }

    public void setT1_champ1_sum1(int t1_champ1_sum1) {
        this.t1_champ1_sum1 = t1_champ1_sum1;
    }

    public void setT1_champ1_sum2(int t1_champ1_sum2) {
        this.t1_champ1_sum2 = t1_champ1_sum2;
    }

    public void setT1_champ2_sum1(int t1_champ2_sum1) {
        this.t1_champ2_sum1 = t1_champ2_sum1;
    }

    public void setT1_champ2_sum2(int t1_champ2_sum2) {
        this.t1_champ2_sum2 = t1_champ2_sum2;
    }

    public void setT1_champ3_sum1(int t1_champ3_sum1) {
        this.t1_champ3_sum1 = t1_champ3_sum1;
    }

    public void setT1_champ3_sum2(int t1_champ3_sum2) {
        this.t1_champ3_sum2 = t1_champ3_sum2;
    }

    public void setT1_champ4_sum1(int t1_champ4_sum1) {
        this.t1_champ4_sum1 = t1_champ4_sum1;
    }

    public void setT1_champ4_sum2(int t1_champ4_sum2) {
        this.t1_champ4_sum2 = t1_champ4_sum2;
    }

    public void setT1_champ5_sum1(int t1_champ5_sum1) {
        this.t1_champ5_sum1 = t1_champ5_sum1;
    }

    public void setT1_champ5_sum2(int t1_champ5_sum2) {
        this.t1_champ5_sum2 = t1_champ5_sum2;
    }

    public void setT2_champ1_sum1(int t2_champ1_sum1) {
        this.t2_champ1_sum1 = t2_champ1_sum1;
    }

    public void setT2_champ1_sum2(int t2_champ1_sum2) {
        this.t2_champ1_sum2 = t2_champ1_sum2;
    }

    public void setT2_champ2_sum1(int t2_champ2_sum1) {
        this.t2_champ2_sum1 = t2_champ2_sum1;
    }

    public void setT2_champ2_sum2(int t2_champ2_sum2) {
        this.t2_champ2_sum2 = t2_champ2_sum2;
    }

    public void setT2_champ3_sum1(int t2_champ3_sum1) {
        this.t2_champ3_sum1 = t2_champ3_sum1;
    }

    public void setT2_champ3_sum2(int t2_champ3_sum2) {
        this.t2_champ3_sum2 = t2_champ3_sum2;
    }

    public void setT2_champ4_sum1(int t2_champ4_sum1) {
        this.t2_champ4_sum1 = t2_champ4_sum1;
    }

    public void setT2_champ4_sum2(int t2_champ4_sum2) {
        this.t2_champ4_sum2 = t2_champ4_sum2;
    }

    public void setT2_champ5_sum1(int t2_champ5_sum1) {
        this.t2_champ5_sum1 = t2_champ5_sum1;
    }

    public void setT2_champ5_sum2(int t2_champ5_sum2) {
        this.t2_champ5_sum2 = t2_champ5_sum2;
    }

    public long getOldId() {
        return oldId;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public short getGameDuration() {
        return gameDuration;
    }

    public byte getSeasonId() {
        return seasonId;
    }

    public byte getWinner() {
        return winner;
    }

    public byte getFirstBlood() {
        return firstBlood;
    }

    public byte getFirstTower() {
        return firstTower;
    }

    public byte getFirstInhibitor() {
        return firstInhibitor;
    }

    public byte getFirstBaron() {
        return firstBaron;
    }

    public byte getFirstDragon() {
        return firstDragon;
    }

    public byte getFirstRiftHerald() {
        return firstRiftHerald;
    }

    public int getT1_champ1id() {
        return t1_champ1id;
    }

    public int getT1_champ1_sum1() {
        return t1_champ1_sum1;
    }

    public int getT1_champ1_sum2() {
        return t1_champ1_sum2;
    }

    public int getT1_champ2id() {
        return t1_champ2id;
    }

    public int getT1_champ2_sum1() {
        return t1_champ2_sum1;
    }

    public int getT1_champ2_sum2() {
        return t1_champ2_sum2;
    }

    public int getT1_champ3id() {
        return t1_champ3id;
    }

    public int getT1_champ3_sum1() {
        return t1_champ3_sum1;
    }

    public int getT1_champ3_sum2() {
        return t1_champ3_sum2;
    }

    public int getT1_champ4id() {
        return t1_champ4id;
    }

    public int getT1_champ4_sum1() {
        return t1_champ4_sum1;
    }

    public int getT1_champ4_sum2() {
        return t1_champ4_sum2;
    }

    public int getT1_champ5id() {
        return t1_champ5id;
    }

    public int getT1_champ5_sum1() {
        return t1_champ5_sum1;
    }

    public int getT1_champ5_sum2() {
        return t1_champ5_sum2;
    }

    public byte getT1_towerKills() {
        return t1_towerKills;
    }

    public byte getT1_inhibitorKills() {
        return t1_inhibitorKills;
    }

    public byte getT1_baronKills() {
        return t1_baronKills;
    }

    public byte getT1_dragonKills() {
        return t1_dragonKills;
    }

    public byte getT1_riftHeraldKills() {
        return t1_riftHeraldKills;
    }

    public int getT1_ban1() {
        return t1_ban1;
    }

    public int getT1_ban2() {
        return t1_ban2;
    }

    public int getT1_ban3() {
        return t1_ban3;
    }

    public int getT1_ban4() {
        return t1_ban4;
    }

    public int getT1_ban5() {
        return t1_ban5;
    }

    public int getT2_champ1id() {
        return t2_champ1id;
    }

    public int getT2_champ1_sum1() {
        return t2_champ1_sum1;
    }

    public int getT2_champ1_sum2() {
        return t2_champ1_sum2;
    }

    public int getT2_champ2id() {
        return t2_champ2id;
    }

    public int getT2_champ2_sum1() {
        return t2_champ2_sum1;
    }

    public int getT2_champ2_sum2() {
        return t2_champ2_sum2;
    }

    public int getT2_champ3id() {
        return t2_champ3id;
    }

    public int getT2_champ3_sum1() {
        return t2_champ3_sum1;
    }

    public int getT2_champ3_sum2() {
        return t2_champ3_sum2;
    }

    public int getT2_champ4id() {
        return t2_champ4id;
    }

    public int getT2_champ4_sum1() {
        return t2_champ4_sum1;
    }

    public int getT2_champ4_sum2() {
        return t2_champ4_sum2;
    }

    public int getT2_champ5id() {
        return t2_champ5id;
    }

    public int getT2_champ5_sum1() {
        return t2_champ5_sum1;
    }

    public int getT2_champ5_sum2() {
        return t2_champ5_sum2;
    }

    public byte getT2_towerKills() {
        return t2_towerKills;
    }

    public byte getT2_inhibitorKills() {
        return t2_inhibitorKills;
    }

    public byte getT2_baronKills() {
        return t2_baronKills;
    }

    public byte getT2_dragonKills() {
        return t2_dragonKills;
    }

    public byte getT2_riftHeraldKills() {
        return t2_riftHeraldKills;
    }

    public int getT2_ban1() {
        return t2_ban1;
    }

    public int getT2_ban2() {
        return t2_ban2;
    }

    public int getT2_ban3() {
        return t2_ban3;
    }

    public int getT2_ban4() {
        return t2_ban4;
    }

    public int getT2_ban5() {
        return t2_ban5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDetail that = (GameDetail) o;
        return oldId == that.oldId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldId);
    }
}
