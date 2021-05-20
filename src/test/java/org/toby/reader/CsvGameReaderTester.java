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
        deserializer = new GameDetailDeserilizer();
        csvReader = new CsvGameReader<>(PropertyRetriever.getProperty(PropertyKeys.GAMES_DATA_FILE_LOCATION.toString()), deserializer);
        csvReader.read();
    }

    @Test
    public void ensureAllLinesInGamesCsvAreRead(){
        Assert.assertEquals(51490, csvReader.getRowDetailsMap().size());
    }

    @Test
    public void ensureGameIdValueIsCorrect(){
        Assert.assertEquals(3229532219L, csvReader.getRowDetailsMap().get(18130).getOldId());
    }

    @Test
    public void ensureCreationTimeValueIsCorrect(){
        Assert.assertEquals(1504453965479L, csvReader.getRowDetailsMap().get(26453).getCreationTime());
    }

    @Test
    public void ensureGameDurationValueIsCorrect(){
        Assert.assertEquals(1424, csvReader.getRowDetailsMap().get(41275).getGameDuration());
    }

    @Test
    public void ensureSeasonIdValueIsCorrect(){
        Assert.assertEquals(9, csvReader.getRowDetailsMap().get(33903).getSeasonId());
    }

    @Test
    public void ensureWinnerValueIsCorrect(){
        Assert.assertEquals(2, csvReader.getRowDetailsMap().get(27230).getWinner());
    }

    @Test
    public void ensureFirstBloodValueIsCorrect(){
        Assert.assertEquals(2, csvReader.getRowDetailsMap().get(20753).getFirstBlood());
    }

    @Test
    public void ensureFirstTowerValueIsCorrect(){
        Assert.assertEquals(2, csvReader.getRowDetailsMap().get(42631).getFirstTower());
    }

    @Test
    public void ensureFirstInhibitorValueIsCorrect(){
        Assert.assertEquals(2, csvReader.getRowDetailsMap().get(26086).getFirstInhibitor());
    }

    @Test
    public void ensureFirstBaronValueIsCorrect(){
        Assert.assertEquals(2, csvReader.getRowDetailsMap().get(29870).getFirstBaron());
    }

    @Test
    public void ensureFirstDragonValueIsCorrect(){
        Assert.assertEquals(1, csvReader.getRowDetailsMap().get(18224).getFirstDragon());
    }

    @Test
    public void ensureFirstRiftHeraldValueIsCorrect(){
        Assert.assertEquals(2, csvReader.getRowDetailsMap().get(38494).getFirstRiftHerald());
    }

    @Test
    public void ensureT1_Champ1IdValueIsCorrect(){
        Assert.assertEquals(98, csvReader.getRowDetailsMap().get(36956).getT1_champ1id());
    }

    @Test
    public void ensureT1_Champ1_Sum1ValueIsCorrect(){
        Assert.assertEquals(14, csvReader.getRowDetailsMap().get(49007).getT1_champ1_sum1());
    }

    @Test
    public void ensureT1_Champ1_Sum2ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(13744).getT1_champ1_sum2());
    }

    @Test
    public void ensureT1_Champ2IdValueIsCorrect(){
        Assert.assertEquals(21, csvReader.getRowDetailsMap().get(22447).getT1_champ2id());
    }

    @Test
    public void ensureT1_Champ2_Sum1ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(1899).getT1_champ2_sum1());
    }

    @Test
    public void ensureT1_Champ2_Sum2ValueIsCorrect(){
        Assert.assertEquals(7, csvReader.getRowDetailsMap().get(8167).getT1_champ2_sum2());
    }

    @Test
    public void ensureT1_Champ3IdValueIsCorrect(){
        Assert.assertEquals(80, csvReader.getRowDetailsMap().get(3309).getT1_champ3id());
    }

    @Test
    public void ensureT1_Champ3_Sum1ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(33245).getT1_champ3_sum1());
    }

    @Test
    public void ensureT1_Champ3_Sum2ValueIsCorrect(){
        Assert.assertEquals(12, csvReader.getRowDetailsMap().get(49286).getT1_champ3_sum2());
    }

    @Test
    public void ensureT1_Champ4IdValueIsCorrect(){
        Assert.assertEquals(238, csvReader.getRowDetailsMap().get(45159).getT1_champ4id());
    }

    @Test
    public void ensureT1_Champ4_Sum1ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(51177).getT1_champ4_sum1());
    }

    @Test
    public void ensureT1_Champ4_Sum2ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(31230).getT1_champ4_sum2());
    }

    @Test
    public void ensureT1_Champ5IdValueIsCorrect(){
        Assert.assertEquals(58, csvReader.getRowDetailsMap().get(4536).getT1_champ5id());
    }

    @Test
    public void ensureT1_Champ5_Sum1ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(8968).getT1_champ5_sum1());
    }

    @Test
    public void ensureT1_Champ5_Sum2ValueIsCorrect(){
        Assert.assertEquals(12, csvReader.getRowDetailsMap().get(6946).getT1_champ5_sum2());
    }

    @Test
    public void ensureT1_TowerKillsValueIsCorrect(){
        Assert.assertEquals(10, csvReader.getRowDetailsMap().get(3660).getT1_towerKills());
    }

    @Test
    public void ensureT1_InhibitorKillsValueIsCorrect(){
        Assert.assertEquals(0, csvReader.getRowDetailsMap().get(13945).getT1_inhibitorKills());
    }

    @Test
    public void ensureT1_BaronKillsValueIsCorrect(){
        Assert.assertEquals(0, csvReader.getRowDetailsMap().get(9012).getT1_baronKills());
    }

    @Test
    public void ensureT1_DragonKillsValueIsCorrect(){
        Assert.assertEquals(1, csvReader.getRowDetailsMap().get(19051).getT1_dragonKills());
    }

    @Test
    public void ensureT1_RiftHeraldValueIsCorrect(){
        Assert.assertEquals(0, csvReader.getRowDetailsMap().get(28581).getT1_riftHeraldKills());
    }

    @Test
    public void ensureT1_Ban1ValueIsCorrect(){
        Assert.assertEquals(112, csvReader.getRowDetailsMap().get(14313).getT1_ban1());
    }

    @Test
    public void ensureT1_Ban2ValueIsCorrect(){
        Assert.assertEquals(17, csvReader.getRowDetailsMap().get(28300).getT1_ban2());
    }

    @Test
    public void ensureT1_Ban3ValueIsCorrect(){
        Assert.assertEquals(18, csvReader.getRowDetailsMap().get(13344).getT1_ban3());
    }

    @Test
    public void ensureT1_Ban4ValueIsCorrect(){
        Assert.assertEquals(412, csvReader.getRowDetailsMap().get(15666).getT1_ban4());
    }

    @Test
    public void ensureT1_Ban5ValueIsCorrect(){
        Assert.assertEquals(29, csvReader.getRowDetailsMap().get(5072).getT1_ban5());
    }

    @Test
    public void ensureT2_Champ1IdValueIsCorrect(){
        Assert.assertEquals(21, csvReader.getRowDetailsMap().get(37719).getT2_champ1id());
    }

    @Test
    public void ensureT2_Champ1_Sum1ValueIsCorrect(){
        Assert.assertEquals(11, csvReader.getRowDetailsMap().get(17928).getT2_champ1_sum1());
    }

    @Test
    public void ensureT2_Champ1_Sum2ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(31964).getT2_champ1_sum2());
    }

    @Test
    public void ensureT2_Champ2IdValueIsCorrect(){
        Assert.assertEquals(105, csvReader.getRowDetailsMap().get(8229).getT2_champ2id());
    }

    @Test
    public void ensureT2_Champ2_Sum1ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(46515).getT2_champ2_sum1());
    }

    @Test
    public void ensureT2_Champ2_Sum2ValueIsCorrect(){
        Assert.assertEquals(7, csvReader.getRowDetailsMap().get(8221).getT2_champ2_sum2());
    }

    @Test
    public void ensureT2_Champ3IdValueIsCorrect(){
        Assert.assertEquals(22, csvReader.getRowDetailsMap().get(7172).getT2_champ3id());
    }

    @Test
    public void ensureT2_Champ3_Sum1ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(2101).getT2_champ3_sum1());
    }

    @Test
    public void ensureT2_Champ3_Sum2ValueIsCorrect(){
        Assert.assertEquals(6, csvReader.getRowDetailsMap().get(34133).getT2_champ3_sum2());
    }

    @Test
    public void ensureT2_Champ4IdValueIsCorrect(){
        Assert.assertEquals(267, csvReader.getRowDetailsMap().get(12909).getT2_champ4id());
    }

    @Test
    public void ensureT2_Champ4_Sum1ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(30373).getT2_champ4_sum1());
    }

    @Test
    public void ensureT2_Champ4_Sum2ValueIsCorrect(){
        Assert.assertEquals(7, csvReader.getRowDetailsMap().get(7896).getT2_champ4_sum2());
    }

    @Test
    public void ensureT2_Champ5IdValueIsCorrect(){
        Assert.assertEquals(24, csvReader.getRowDetailsMap().get(2253).getT2_champ5id());
    }

    @Test
    public void ensureT2_Champ5_Sum1ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(31054).getT2_champ5_sum1());
    }

    @Test
    public void ensureT2_Champ5_Sum2ValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(45981).getT2_champ5_sum2());
    }

    @Test
    public void ensureT2_TowerKillsValueIsCorrect(){
        Assert.assertEquals(4, csvReader.getRowDetailsMap().get(46194).getT2_towerKills());
    }

    @Test
    public void ensureT2_InhibitorKillsValueIsCorrect(){
        Assert.assertEquals(0, csvReader.getRowDetailsMap().get(32864).getT2_inhibitorKills());
    }

    @Test
    public void ensureT2_BaronKillsValueIsCorrect(){
        Assert.assertEquals(0, csvReader.getRowDetailsMap().get(12744).getT2_baronKills());
    }

    @Test
    public void ensureT2_DragonKillsValueIsCorrect(){
        Assert.assertEquals(0, csvReader.getRowDetailsMap().get(14410).getT2_dragonKills());
    }

    @Test
    public void ensureT2_RiftHeraldValueIsCorrect(){
        Assert.assertEquals(0, csvReader.getRowDetailsMap().get(17240).getT2_riftHeraldKills());
    }

    @Test
    public void ensureT2_Ban1ValueIsCorrect(){
        Assert.assertEquals(134, csvReader.getRowDetailsMap().get(39189).getT2_ban1());
    }

    @Test
    public void ensureT2_Ban2ValueIsCorrect(){
        Assert.assertEquals(150, csvReader.getRowDetailsMap().get(18123).getT2_ban2());
    }

    @Test
    public void ensureT2_Ban3ValueIsCorrect(){
        Assert.assertEquals(11, csvReader.getRowDetailsMap().get(45682).getT2_ban3());
    }

    @Test
    public void ensureT2_Ban4ValueIsCorrect(){
        Assert.assertEquals(141, csvReader.getRowDetailsMap().get(18035).getT2_ban4());
    }

    @Test
    public void ensureT2_Ban5ValueIsCorrect(){
        Assert.assertEquals(38, csvReader.getRowDetailsMap().get(5242).getT2_ban5());
    }
}
