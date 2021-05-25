package org.toby.database.insertion;

import org.junit.*;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.ChampionDeletion;
import org.toby.database.delete.Deletion;
import org.toby.database.insert.ChampionInsertion;
import org.toby.database.insert.Insertion;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.testtable.TestTableDataRetriever;
import org.toby.json.mappers.ChampionCollectionMapper;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;
import org.toby.reader.LolJsonReader;
import org.toby.reader.Reader;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ChampionInsertionTester {

    private static SQLManager sqlManager;
    private static Reader reader;
    private static LolDbConnector connector;
    private static ChampionCollectionMapper mapper;
    private static Insertion insertion;
    private static Deletion deletion;
    private LocalDateTime testInitialiserTime;
    private final int expectedNumberOfChampions = 139;
    private List<String> championList;
    private TestTableDataRetriever testOutcomeRetrieval;

    @BeforeClass
    public static void setup(){
        reader = new LolJsonReader(PropertyRetriever.getProperty(PropertyKeys.CHAMPION_DATA_FILE_LOCATION.toString()));
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        mapper = new ChampionCollectionMapper(reader);
        insertion = new ChampionInsertion(connector, mapper.getCollection());
        deletion = new ChampionDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
        sqlManager.insert();
    }

    @Before
    public void setupTestInitialiserTime(){
        this.testInitialiserTime = LocalDateTime.now();
        testOutcomeRetrieval = new TestTableDataRetriever(connector, this.testInitialiserTime);
    }

    @Test
    public void ensureTheNumberOfChampionsInsideTheChampionTableIsCorrect(){
        connector.connect();
        try(PreparedStatement executeSpChampionTableTest = connector.getConnection().prepareStatement("EXECUTE [test].[spChampionTableTest] ?")){
            executeSpChampionTableTest.setInt(1, this.expectedNumberOfChampions);
            executeSpChampionTableTest.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                connector.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        Assert.assertEquals(1, testOutcomeRetrieval.getLastestTestResult());
    }

    @Test
    public void ensureThatAllSpecificChampionsHaveBeenInsertedIntoTheChampionTable(){
        setupChampionlist();
        for(String champion : championList) {
            testOutcomeRetrieval.setTestInitialiserTime(LocalDateTime.now());
            connector.connect();
            try (PreparedStatement executeSpSpecificChampionTest = connector.getConnection().prepareStatement("EXECUTE [test].[spSpecificChampionTest] ?")) {
                executeSpSpecificChampionTest.setString(1, champion);
                executeSpSpecificChampionTest.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connector.getConnection().close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            Assert.assertEquals(1, testOutcomeRetrieval.getLastestTestResult());
        }
    }

    @AfterClass
    public static void deleteChampionData(){
        sqlManager.delete();
    }

    private void setupChampionlist(){
        this.championList = new ArrayList<>();
        championList.add("Aatrox");
        championList.add("Ahri");
        championList.add("Akali");
        championList.add("Alistar");
        championList.add("Amumu");
        championList.add("Anivia");
        championList.add("Annie");
        championList.add("Ashe");
        championList.add("Aurelion Sol");
        championList.add("Azir");
        championList.add("Bard");
        championList.add("Blitzcrank");
        championList.add("Brand");
        championList.add("Braum");
        championList.add("Caitlyn");
        championList.add("Camille");
        championList.add("Cassiopeia");
        championList.add("Chogath");
        championList.add("Corki");
        championList.add("Darius");
        championList.add("Diana");
        championList.add("Dr. Mundo");
        championList.add("Draven");
        /*
        Ekko
                Elise
        Evelynn
                Ezreal
        Fiddlesticks
                Fiora
        Fizz
                Galio
        Gangplank
                Garen
        Gnar
                Gragas
        Graves
                Hecarim
        Heimerdinger
                Illaoi
        Irelia
                Ivern
        Janna
        Jarvan IV
        Jax
                Jayce
        Jhin
                Jinx
        Kalista
                Karma
        Karthus
                Kassadin
        Katarina
                Kayle
        Kayn
                Kennen
        Khazix
                Kindred
        Kled
                Kogmaw
        LeBlanc
        Lee Sin
        Leona
                Lissandra
        Lucian
                Lulu
        Lux
                Malphite
        Malzahar
                Maokai
        Master Yi
        Miss Fortune
        Mordekaiser
                Morgana
        Nami
                Nasus
        Nautilus
                Nidalee
        Nocturne
                None
        Nunu
                Olaf
        Orianna
                Ornn
        Pantheon
                Poppy
        Quinn
                Rakan
        Rammus
                Reksai
        Renekton
                Rengar
        Riven
                Rumble
        Ryze
                Sejuani
        Shaco
                Shen
        Shyvana
                Singed
        Sion
                Sivir
        Skarner
                Sona
        Soraka
                Swain
        Syndra
        Tahm Kench
        Taliyah
                Talon
        Taric
                Teemo
        Thresh
                Tristana
        Trundle
                Tryndamere
        Twisted Fate
        Twitch
                Udyr
        Urgot
                Varus
        Vayne
                Veigar
        Velkoz
                Vi
        Viktor
                Vladimir
        Volibear
                Warwick
        Wukong
                Xayah
        Xerath
        Xin Zhao
        Yasuo
                Yorick
        Zac
                Zed
        Ziggs
                Zilean
        Zyra

         */
    }
}
