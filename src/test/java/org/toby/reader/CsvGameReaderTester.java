package org.toby.reader;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.csv.deserializers.Deserializer;
import org.toby.csv.deserializers.GameDetailDeserilizer;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.valueobject.csvobjects.GameDetail;

public class CsvGameReaderTester {

    private static Deserializer<GameDetail> deserializer;
    private static CsvReader<GameDetail> csvReader;

    @BeforeClass
    public static void setup(){
        deserializer = new GameDetailDeserilizer("");
        csvReader = new CsvGameReader<>(PropertyRetriever.getProperty(PropertyKeys.GAMES_DATA_FILE_LOCATION.toString()), deserializer);
        csvReader.read();
    }

    @Test
    public void ensureAllLinesInGamesCsvAreRead(){
        Assert.assertEquals(51490, csvReader.getDetails().size());
    }

    @Test
    public void ensureGameIdValueIsCorrect(){
        Assert.assertEquals(3229532219L, csvReader.getDetails().get(18128).getOldId());
    }

    @Test
    public void ensureCreationTimeValueIsCorrect(){
        Assert.assertEquals(1504453965479L, csvReader.getDetails().get(26451).getCreationTime());
    }

    @Test
    public void ensureGameDurationValueIsCorrect(){
        Assert.assertEquals(1424, csvReader.getDetails().get(41273).getGameDuration());
    }

    @Test
    public void ensureSeasonIdValueIsCorrect(){
        Assert.assertEquals(9, csvReader.getDetails().get(33901).getSeasonId());
    }

    @Test
    public void ensureWinnerValueIsCorrect(){
        Assert.assertEquals(2, csvReader.getDetails().get(27228).getWinner());
    }

    @Test
    public void ensureFirstBloodValueIsCorrect(){
        Assert.assertEquals(2, csvReader.getDetails().get(20751).getFirstBlood());
    }

    @Test
    public void ensureFirstTowerValueIsCorrect(){
        Assert.assertEquals(2, csvReader.getDetails().get(42629).getFirstTower());
    }

    @Test
    public void ensureFirstInhibitorValueIsCorrect(){
        Assert.assertEquals(2, csvReader.getDetails().get(26084).getFirstInhibitor());
    }

    @Test
    public void ensureFirstBaronValueIsCorrect(){
        Assert.assertEquals(2, csvReader.getDetails().get(29868).getFirstBaron());
    }

    @Test
    public void ensureFirstDragonValueIsCorrect(){
        Assert.assertEquals(1, csvReader.getDetails().get(18222).getFirstDragon());
    }

    @Test
    public void ensureFirstRiftHeraldValueIsCorrect(){
        Assert.assertEquals(2, csvReader.getDetails().get(38492).getFirstRiftHerald());
    }

    @Test
    public void ensureT1_Champ1IdValueIsCorrect(){
        Assert.assertEquals(98, csvReader.getDetails().get(36954).getT1_champ1id());
    }

    @Test
    public void ensureT1_Champ1_Sum1ValueIsCorrect(){
        Assert.assertEquals(14, csvReader.getDetails().get(49005).getT1_champ1_sum1());
    }

    @Test
    public void ensureT1_Champ1_Sum2ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getDetails().get(13742).getT1_champ1_sum2());
    }

    @Test
    public void ensureT1_Champ2IdValueIsCorrect(){
        Assert.assertEquals(21, csvReader.getDetails().get(22445).getT1_champ2id());
    }

    @Test
    public void ensureT1_Champ2_Sum1ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getDetails().get(1897).getT1_champ2_sum1());
    }

    @Test
    public void ensureT1_Champ2_Sum2ValueIsCorrect(){
        Assert.assertEquals(7, csvReader.getDetails().get(8165).getT1_champ2_sum2());
    }

    @Test
    public void ensureT1_Champ3IdValueIsCorrect(){
        Assert.assertEquals(80, csvReader.getDetails().get(3307).getT1_champ3id());
    }

    @Test
    public void ensureT1_Champ3_Sum1ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getDetails().get(33243).getT1_champ3_sum1());
    }

    @Test
    public void ensureT1_Champ3_Sum2ValueIsCorrect(){
        Assert.assertEquals(12, csvReader.getDetails().get(49284).getT1_champ3_sum2());
    }

    @Test
    public void ensureT1_Champ4IdValueIsCorrect(){
        Assert.assertEquals(238, csvReader.getDetails().get(45157).getT1_champ4id());
    }

    @Test
    public void ensureT1_Champ4_Sum1ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getDetails().get(51175).getT1_champ4_sum1());
    }

    @Test
    public void ensureT1_Champ4_Sum2ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getDetails().get(31228).getT1_champ4_sum2());
    }

    @Test
    public void ensureT1_Champ5IdValueIsCorrect(){
        Assert.assertEquals(58, csvReader.getDetails().get(4534).getT1_champ5id());
    }

    @Test
    public void ensureT1_Champ5_Sum1ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getDetails().get(8966).getT1_champ5_sum1());
    }

    @Test
    public void ensureT1_Champ5_Sum2ValueIsCorrect(){
        Assert.assertEquals(12, csvReader.getDetails().get(6944).getT1_champ5_sum2());
    }

    @Test
    public void ensureT1_TowerKillsValueIsCorrect(){
        Assert.assertEquals(10, csvReader.getDetails().get(3658).getT1_towerKills());
    }

    @Test
    public void ensureT1_InhibitorKillsValueIsCorrect(){
        Assert.assertEquals(0, csvReader.getDetails().get(13943).getT1_inhibitorKills());
    }

    @Test
    public void ensureT1_BaronKillsValueIsCorrect(){
        Assert.assertEquals(0, csvReader.getDetails().get(9010).getT1_baronKills());
    }

    @Test
    public void ensureT1_DragonKillsValueIsCorrect(){
        Assert.assertEquals(1, csvReader.getDetails().get(19049).getT1_dragonKills());
    }

    @Test
    public void ensureT1_RiftHeraldValueIsCorrect(){
        Assert.assertEquals(0, csvReader.getDetails().get(28579).getT1_riftHeraldKills());
    }

    @Test
    public void ensureT1_Ban1ValueIsCorrect(){
        Assert.assertEquals(112, csvReader.getDetails().get(14311).getT1_ban1());
    }

    @Test
    public void ensureT1_Ban2ValueIsCorrect(){
        Assert.assertEquals(17, csvReader.getDetails().get(28298).getT1_ban2());
    }

    @Test
    public void ensureT1_Ban3ValueIsCorrect(){
        Assert.assertEquals(18, csvReader.getDetails().get(13342).getT1_ban3());
    }

    @Test
    public void ensureT1_Ban4ValueIsCorrect(){
        Assert.assertEquals(412, csvReader.getDetails().get(15664).getT1_ban4());
    }

    @Test
    public void ensureT1_Ban5ValueIsCorrect(){
        Assert.assertEquals(29, csvReader.getDetails().get(5070).getT1_ban5());
    }
//
    @Test
    public void ensureT2_Champ1IdValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ1_Sum1ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ1_Sum2ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ2IdValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ2_Sum1ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ2_Sum2ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ3IdValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ3_Sum1ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ3_Sum2ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ4IdValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ4_Sum1ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ4_Sum2ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ5IdValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ5_Sum1ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ5_Sum2ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_TowerKillsValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_InhibKillsValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_BaronKillsValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_DragonKillsValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_RiftHeraldValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Ban1ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Ban2ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Ban3ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Ban4ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Ban5ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getDetails().get(45278).getT2_champ5_sum1());
    }




}
