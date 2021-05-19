package org.toby.database.idmapping;

import java.util.Map;

public interface IdMapper<T> {

    void map();

    Map<T, T> getMapping();
}
