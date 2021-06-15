package org.toby.csv.validators.duplicate;

import java.util.HashMap;
import java.util.Map;

public class BaseDuplicateFilter<K, V> implements DuplicateFilter<K, V> {

    private Map<K, V> duplicates;
    private Map<K, V> distinctiveMap;

    public BaseDuplicateFilter(Map<K, V> fullMap) {
        this.duplicates = new HashMap<>();
        this.distinctiveMap = new HashMap<>();
        validate(fullMap);
    }

    private void validate(Map<K, V> map) {
        for(Map.Entry<K, V> entry : map.entrySet()){
            if(this.distinctiveMap.containsValue(entry.getValue())){
                this.duplicates.put(entry.getKey(), entry.getValue());
            }
            else{
                this.distinctiveMap.put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public Map<K, V> getDuplicates() {
        return this.duplicates;
    }

    @Override
    public Map<K, V> getDistinctiveMap() {
        return this.distinctiveMap;
    }


}
