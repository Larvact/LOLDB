package org.toby.database.idmapping;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.database.LolDbConnector;
import org.toby.database.delete.Deletion;
import org.toby.database.delete.TeamDeletion;
import org.toby.database.insert.Insertion;
import org.toby.database.insert.TeamInsertion;
import org.toby.database.tablemanagers.SQLManager;
import org.toby.database.tablemanagers.SQLTableManager;
import org.toby.properties.PropertyKeys;
import org.toby.properties.PropertyRetriever;

public class TeamIdMapperTester {

    private static TeamIdMapper teamIdMapper;
    private static SQLManager sqlManager;
    private static LolDbConnector connector;
    private static Insertion insertion;
    private static Deletion deletion;

    private final int expectedNumberOfTeams = 3;
    private final byte legacyBlueTeamId = 1;
    private final byte legacyPurpleTeamId = 2;
    private final byte legacyNeitherTeamId = 0;
    private final byte expectedBlueTeamNewId = 1;
    private final byte expectedPurpleTeamNewId = 2;
    private final byte expectedNeitherTeamNewId = 3;

    @BeforeClass
    public static void setUpData(){
        setupDataBaseData();
        setupSeasonIdMapper();
    }

    private static void setupDataBaseData(){
        connector = new LolDbConnector(PropertyRetriever.getProperty(PropertyKeys.DATABASE_CONNECTION_STRING.toString()));
        insertion = new TeamInsertion(connector);
        deletion = new TeamDeletion(connector);
        sqlManager = new SQLTableManager(insertion, deletion);
        sqlManager.insert();
    }

    private static void setupSeasonIdMapper(){
        teamIdMapper = new TeamIdMapper(connector);
        teamIdMapper.map();
    }

    @Test
    public void ensureBlueTeamNewIdMappedViaTheirOldId(){
        byte newId = teamIdMapper.getMapping().get(legacyBlueTeamId);
        Assert.assertEquals(expectedBlueTeamNewId, newId);
    }

    @Test
    public void ensurePurpleTeamNewIdMappedViaTheirOldId(){
        byte newId = teamIdMapper.getMapping().get(legacyPurpleTeamId);
        Assert.assertEquals(expectedPurpleTeamNewId, newId);
    }

    @Test
    public void ensureNeitherTeamNewIdMappedViaTheirOldId(){
        byte newId = teamIdMapper.getMapping().get(legacyNeitherTeamId);
        Assert.assertEquals(expectedNeitherTeamNewId, newId);
    }

    @Test
    public void ensureCorrectNumberOfTeamsArePresentInTheMap(){
        Assert.assertEquals(expectedNumberOfTeams, teamIdMapper.getMapping().size());
    }

    @AfterClass
    public static void deleteData(){
        sqlManager.delete();
    }
}
