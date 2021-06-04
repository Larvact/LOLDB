package org.toby.database.deletion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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

public class ChampionDeletionTester {

    private static SQLManager sqlManager;
    private static Reader reader;
    private static LolDbConnector connector;
    private static ChampionCollectionMapper mapper;
    private static Insertion insertion;
    private static Deletion deletion;
    private TestTableDataRetriever testOutcomeRetrieval;
    private LocalDateTime testInitialiserTime;

    @BeforeClass
    public static void setData(){
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
    public void ensureDataHasBeenDeletedFromTheChampionTable(){
        sqlManager.delete();
        connector.connect();
        try(PreparedStatement executeSpEnsureEmptyTable = connector.getConnection().prepareStatement("EXECUTE [test].[spEnsureEmptyTable] @SchemaName = ?, @SelectedTable = ?")){
            executeSpEnsureEmptyTable.setString(1, "dbo");
            executeSpEnsureEmptyTable.setString(2, "Champion");
            executeSpEnsureEmptyTable.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertEquals(1, testOutcomeRetrieval.getLastestTestResult());
    }
}
