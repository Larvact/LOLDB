package org.toby.database.insert;

import org.junit.*;
import org.toby.csv.deserializers.Deserializer;
import org.toby.csv.deserializers.GameDetailDeserilizer;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.*;
import org.toby.database.idmapping.IdMapper;
import org.toby.database.idmapping.SeasonIdMapper;
import org.toby.database.idmapping.TeamIdMapper;
import org.toby.database.idmapping.formatters.Format;
import org.toby.database.idmapping.formatters.GlobalFormatter;
import org.toby.database.idmapping.formatters.SeasonIdFormatter;
import org.toby.database.idmapping.formatters.TeamIdFormatter;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.database.testtable.TestTableDataRetriever;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.reader.CsvGameReader;
import org.toby.reader.CsvReader;
import org.toby.valueobject.csvobjects.GameDetail;
import org.toby.valueobject.csvobjects.databasetransfer.game.GameCollection;
import org.toby.valueobject.csvobjects.databasetransfer.season.SeasonCollection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameInsertionTester {

    private static CsvReader<GameDetail> csvReader;
    private static Deserializer<GameDetail> csvGameDetailDeserializer;
    private static List<Format> formatters;
    private static Format globalFormatter;

    private static SQLManager sqlManager;
    private static LolDbConnector connector;
    private static Insertion teamInsertion;
    private static Deletion teamDeletion;
    private static Insertion seasonInsertion;
    private static Deletion seasonDeletion;
    private static Insertion gameInsertion;
    private static Deletion gameDeletion;


    private TestTableDataRetriever testOutcomeRetrieval;
    private LocalDateTime testInitialiserTime;


    @BeforeClass
    public static void setUpData(){
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        readCsv();
        insertTeamData();
        insertSeasonData();
        formatData();
        insertGameData();
    }

    private static void readCsv(){
        csvGameDetailDeserializer = new GameDetailDeserilizer();
        csvReader = new CsvGameReader<>(PropertyRetriever.getProperty(PropertyKeys.GAMES_DATA_FILE_LOCATION.toString()), csvGameDetailDeserializer);
        csvReader.read();
    }

    private static void insertTeamData(){
        teamInsertion = new TeamInsertion(connector);
        teamDeletion = new TeamDeletion(connector);
        sqlManager = new SQLTableManager(teamInsertion, teamDeletion);
        sqlManager.insert();
    }

    private static void insertSeasonData(){
        seasonInsertion = new SeasonInsertion(connector, new SeasonCollection(csvReader.getRowDetailsMap().values()));
        sqlManager.setInsertion(seasonInsertion);
        sqlManager.insert();
    }

    private static void insertGameData(){
        gameInsertion = new GameInsertion(connector, new GameCollection(new ArrayList<>(csvReader.getRowDetailsMap().values())));
        sqlManager.setInsertion(gameInsertion);
        sqlManager.insert();
    }

    private static void formatData(){
        IdMapper<Byte> seasonIdMapper = new SeasonIdMapper(new SeasonCollection(csvReader.getRowDetailsMap().values()), connector);
        IdMapper<Byte> teamIdMapper = new TeamIdMapper(connector);
        seasonIdMapper.map();
        teamIdMapper.map();

        Format seasonIdFormatter = new SeasonIdFormatter(csvReader.getRowDetailsMap(), seasonIdMapper.getMapping());
        Format teamIdFormatter = new TeamIdFormatter(csvReader.getRowDetailsMap(), teamIdMapper.getMapping());

        formatters = new ArrayList<>();
        formatters.add(seasonIdFormatter);
        formatters.add(teamIdFormatter);
        globalFormatter = new GlobalFormatter(formatters);
        globalFormatter.format();
    }

    @Before
    public void setupTestInitialiserTime(){
        this.testInitialiserTime = LocalDateTime.now();
        this.testOutcomeRetrieval = new TestTableDataRetriever(connector, this.testInitialiserTime);
        connector.connect();
    }

    @Test
    public void ensureThatOneSeasonHasBeenInsertedIntoTheSeasonTable() {
        System.out.println("test");
    }

    @After
    public void teardown(){
        connector.closeConnection();
    }

    private static void deleteGameData(){
        gameDeletion = new GameDeletion(connector);
        sqlManager.setDeletion(gameDeletion);
        sqlManager.delete();
    }

    private static void deleteTeamData(){
        sqlManager.setDeletion(teamDeletion);
        sqlManager.delete();
    }

    private static void deleteSeasonData(){
        seasonDeletion = new SeasonDeletion(connector);
        sqlManager.setDeletion(seasonDeletion);
        sqlManager.delete();
    }

    @AfterClass
    public static void deleteData(){
        deleteGameData();
        deleteTeamData();
        deleteSeasonData();
    }

}