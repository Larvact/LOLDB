package org.toby.csv.validators.duplicate;

import java.util.Map;

public interface DuplicateFilter<K, V> {

    Map<K, V> getDuplicates();

    Map<K, V> getDistinctiveMap();

}
