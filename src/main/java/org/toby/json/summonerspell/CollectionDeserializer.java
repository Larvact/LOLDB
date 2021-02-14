package org.toby.json.summonerspell;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.toby.content.summonerspell.SummonerSpellCollection;
import org.toby.lolobject.SummonerSpell;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CollectionDeserializer extends StdDeserializer<SummonerSpellCollection> {

    public CollectionDeserializer() {
        this(null);
    }

    public CollectionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SummonerSpellCollection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

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

    private Set<SummonerSpell> parseSummonerSpells(JsonNode summonerSpellsNode) {

        ObjectMapper mapper = createSummonerSpellObjectmapper();
        Set<SummonerSpell> summonerSpellSet = new HashSet<>();
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
            summonerSpellSet.add(summonerSpell);
        }
        return summonerSpellSet;
    }

    private ObjectMapper createSummonerSpellObjectmapper(){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("SummonerSpellDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(SummonerSpell.class, new Deserializer());
        mapper.registerModule(module);
        return mapper;
    }


}
