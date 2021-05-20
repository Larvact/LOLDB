package org.toby.json.deserialisers.champion;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.toby.valueobject.jsondeserialise.Champion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deserializer extends StdDeserializer<Champion> {

    public Deserializer() {
        this(null);
    }

    public Deserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Champion deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Champion champion = new Champion();
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        JsonNode idNode = node.get("id");
        JsonNode nameNode = node.get("name");
        JsonNode titleNode = node.get("title");
        JsonNode rolesNode = node.get("tags");

        champion.setId(idNode.asInt());
        champion.setName(nameNode.asText());
        champion.setTitle(titleNode.asText());
        champion.setRoles(serparateString(",", rolesNode.asText()));

        return champion;
    }

    private List<String> serparateString(String delimiter, String string){
        String[] strings = string.split(delimiter);

        if(strings[0].length() == 0 || strings[0].equals("[]")){
            return new ArrayList<>();
        }

        String firstString = strings[0];
        if(firstString.charAt(0) == '['){
            strings[0] = firstString.substring(1);
        }

        String lastString = strings[strings.length - 1];
        if(lastString.charAt(lastString.length() - 1) == ']'){
            strings[strings.length - 1] = lastString.substring(0, lastString.length() - 1);
        }

        List<String> stringList = Arrays.asList(strings);
        List<String> trimmedStringList = new ArrayList<>();
        for(String untrimmedString : stringList){
            trimmedStringList.add(untrimmedString.trim());
        }
        return trimmedStringList;
    }
}
