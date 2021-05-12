package org.toby.csv.deserializers;

import org.toby.valueobject.csvobjects.GameDetail;
import java.util.Arrays;
import java.util.List;

public class GameDetailDeserilizer extends Deserializer<GameDetail> {

    public GameDetailDeserilizer(String csvString) {
        super(csvString);
    }

    @Override
    public GameDetail deserialize() {
        List<String> gameDetails = Arrays.asList(this.csvString.split(","));
        return new GameDetail(gameDetails);
    }

}
