package org.toby.database.idmapping.formatters;

import java.util.List;

public class GlobalFormatter implements Format{

    private List<Format> formatterList;

    public GlobalFormatter(List<Format> formatterList) {
        this.formatterList = formatterList;
    }

    @Override
    public void format() {
        for(Format formatter : this.formatterList){
            formatter.format();
        }
    }
}
