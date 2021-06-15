package org.toby.database.idmapping.formatters;

import org.junit.*;
import org.toby.csv.deserializers.Deserializer;
import org.toby.csv.deserializers.GameDetailDeserilizer;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.Deletion;
import org.toby.database.delete.TeamDeletion;
import org.toby.database.idmapping.TeamIdMapper;
import org.toby.database.insert.Insertion;
import org.toby.database.insert.TeamInsertion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.csv.reader.CsvGameReader;
import org.toby.csv.reader.CsvReader;
import org.toby.valueobject.csvobjects.GameDetail;

public class TeamIdFormatterTester {

    private static CsvReader<GameDetail> csvReader;
    private static Deserializer<GameDetail> csvGameDetailDeserializer;
    private static TeamIdMapper teamIdMapper;
    private static Format teamIdFormatter;
    private static SQLManager sqlManager;
    private static LolDbConnector connector;
    private static Insertion insertion;
    private static Deletion deletion;

    @BeforeClass
    public static void setup() {
        readCsv();
        setupDataBaseData();
        setupTeamIdMapper();
        formatGameDetails();
    }

    private static void readCsv(){
        csvGameDetailDeserializer = new GameDetailDeserilizer();
        csvReader = new CsvGameReader<>(PropertyRetriever.getProperty(PropertyKeys.GAMES_DATA_FILE_LOCATION.toString()), csvGameDetailDeserializer);
        csvReader.read();
    }

    private static void setupDataBaseData(){
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        insertion = new TeamInsertion(connector);
        deletion = new TeamDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
        sqlManager.insert();
    }

    private static void setupTeamIdMapper(){
        teamIdMapper = new TeamIdMapper(connector);
        teamIdMapper.map();
    }

    private static void formatGameDetails(){
        teamIdFormatter = new TeamIdFormatter(csvReader.getRowDetailsMap(), teamIdMapper.getMapping());
        teamIdFormatter.format();
    }

    @Test
    public void ensureThatTheWinnerBeenCorrectlyFormatted(){
        Assert.assertEquals(2, csvReader.getRowDetailsMap().get(15901).getWinner());
    }

    @Test
    public void ensureThatTheFirstBloodBeenCorrectlyFormatted(){
        Assert.assertEquals(1, csvReader.getRowDetailsMap().get(21389).getFirstBlood());
    }

    @Test
    public void ensureThatTheFirstTowerBeenCorrectlyFormatted(){
        Assert.assertEquals(1, csvReader.getRowDetailsMap().get(18841).getFirstTower());
    }

    @Test
    public void ensureThatTheFirstInhibitorBeenCorrectlyFormatted(){
        Assert.assertEquals(3, csvReader.getRowDetailsMap().get(21377).getFirstInhibitor());
    }

    @Test
    public void ensureThatTheFirstBaronBeenCorrectlyFormatted(){
        Assert.assertEquals(3, csvReader.getRowDetailsMap().get(4348).getFirstBaron());
    }

    @Test
    public void ensureThatTheFirstDragonBeenCorrectlyFormatted(){
        Assert.assertEquals(2, csvReader.getRowDetailsMap().get(1752).getFirstDragon());
    }

    @Test
    public void ensureThatTheFirstRiftHeraldBeenCorrectlyFormatted(){
        Assert.assertEquals(1, csvReader.getRowDetailsMap().get(18835).getFirstRiftHerald());
    }

    @AfterClass
    public static void deleteData(){
        sqlManager.delete();
    }
}
