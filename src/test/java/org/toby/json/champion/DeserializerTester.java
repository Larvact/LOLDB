package org.toby.json.champion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.toby.lolobject.Champion;

import java.util.ArrayList;
import java.util.List;

public class DeserializerTester {

    private static String json = "{\"id\": \"24\"," + "\"name\": \"Jax\"," + "\"title\": \"Grandmaster at Arms\"," + "\"tags\": \"[Fight, Assassin]\"}";
    private static List<String> expectedRoles = new ArrayList<String>();

    @BeforeClass
    public static void setup(){
        expectedRoles.add("Fight");
        expectedRoles.add("Assassin");
    }

    @Test
    public void ensureChampionDeserializeMappingIsCorrect(){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ChampionDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Champion.class, new Deserializer());
        mapper.registerModule(module);
        Champion champion = null;
        try {
            champion = mapper.readValue(json, Champion.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(24, champion.getId());
        Assert.assertEquals("Jax", champion.getName());
        Assert.assertEquals("Grandmaster at Arms", champion.getTitle());
        Assert.assertEquals(expectedRoles, champion.getRoles());
    }
}
