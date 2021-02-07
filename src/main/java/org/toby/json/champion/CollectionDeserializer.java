package org.toby.json.champion;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.toby.content.champion.ChampionCollection;
import org.toby.lolobject.Champion;

import java.io.IOException;
import java.util.*;

public class CollectionDeserializer extends StdDeserializer<ChampionCollection> {

    public CollectionDeserializer() {
        this(null);
    }

    public CollectionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ChampionCollection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        ChampionCollection championCollection = new ChampionCollection();
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        JsonNode type = node.get("type");
        JsonNode version = node.get("version");
        JsonNode championData = node.get("data");

        championCollection.setType(type.asText());
        championCollection.setVersion(version.asDouble());
        championCollection.setChampions(parseChampions(championData));

        return championCollection;
    }

    private Set<Champion> parseChampions(JsonNode championNodeData){
        ObjectMapper championMapper = createChampionObjectmapper();
        Set<Champion> championList = new HashSet<>();
        Iterator<Map.Entry<String, JsonNode>> championNodeIterator = championNodeData.fields();
        while(championNodeIterator.hasNext()){
            Map.Entry<String, JsonNode> championJson = championNodeIterator.next();
            JsonNode championNode = championJson.getValue();
            String json = championNode.toString();
            System.out.println(json);
            Champion champion = null;
            try {
                champion= championMapper.readValue(json, Champion.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            championList.add(champion);
        }
        return championList;
    }

    private ObjectMapper createChampionObjectmapper(){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ChampionDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Champion.class, new Deserializer());
        mapper.registerModule(module);
        return mapper;
    }
}
