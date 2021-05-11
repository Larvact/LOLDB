package org.toby.json.deserialisers.summonerspell;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.toby.valueobject.jsondeserialise.databasetransfer.summonerspell.SummonerSpellCollection;
import org.toby.valueobject.jsondeserialise.SummonerSpell;

import java.io.IOException;
import java.util.*;

public class CollectionDeserializer extends StdDeserializer<SummonerSpellCollection> {

    public CollectionDeserializer() {
        this(null);
    }

    public CollectionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SummonerSpellCollection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        SummonerSpellCollection summonerSpellCollection = new SummonerSpellCollection();
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode root = codec.readTree(jsonParser);

        JsonNode typeNode = root.get("type");
        JsonNode versionNode = root.get("version");
        JsonNode summonerSpellsNode = root.get("data");

        summonerSpellCollection.setType(typeNode.asText());
        summonerSpellCollection.setVersion(versionNode.asDouble());
        summonerSpellCollection.setSummonerSpells( parseSummonerSpells(summonerSpellsNode));

        return summonerSpellCollection;
    }

    private List<SummonerSpell> parseSummonerSpells(JsonNode summonerSpellsNode) {

        ObjectMapper mapper = createSummonerSpellObjectmapper();
        List<SummonerSpell> summonerSpellList = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> summonerSpellsNodeIterator = summonerSpellsNode.fields();
        while (summonerSpellsNodeIterator.hasNext()){
            JsonNode summonerSpellNode = summonerSpellsNodeIterator.next().getValue();
            String json = summonerSpellNode.toString();
            SummonerSpell summonerSpell = null;
            try {
                summonerSpell = mapper.readValue(json, SummonerSpell.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            summonerSpellList.add(summonerSpell);
        }
        return summonerSpellList;
    }

    private ObjectMapper createSummonerSpellObjectmapper(){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("SummonerSpellDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(SummonerSpell.class, new Deserializer());
        mapper.registerModule(module);
        return mapper;
    }


}
