package org.toby.json.deserialisers.summonerspell;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.toby.valueobject.jsondeserialise.SummonerSpell;

import java.io.IOException;

public class Deserializer extends StdDeserializer<SummonerSpell> {

    public Deserializer() {
        this(null);
    }

    public Deserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public SummonerSpell deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SummonerSpell summonerSpell = new SummonerSpell();
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        JsonNode idNode = node.get("id");
        JsonNode levelRequiredNode = node.get("summonerLevel");
        JsonNode descriptionNode = node.get("description");
        JsonNode nameNode = node.get("name");

        summonerSpell.setId(idNode.asInt());
        summonerSpell.setLevelRequired((short) levelRequiredNode.asInt());
        summonerSpell.setDescription(descriptionNode.asText());
        summonerSpell.setName(nameNode.asText());

        return summonerSpell;
    }
}
