package org.toby.database.tablemanagers;

import org.toby.database.delete.Deletion;
import org.toby.database.insert.Insertion;

public class SQLChampionTableManager extends SQLTableManager {

    private Insertion insertion;
    private Deletion deletion;

    public SQLChampionTableManager(Insertion insertion, Deletion deletion) {
        this.insertion = insertion;
        this.deletion = deletion;
    }

    @Override
    public void insert(){
        this.insertion.insertData();
    }

    @Override
    public void delete() {
        this.deletion.delete();
    }

}
