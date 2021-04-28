package org.toby.database.managers;

import org.toby.database.delete.Deletion;
import org.toby.database.insert.Insertion;

public class SQLChampionManager extends SQLManager{

    private Insertion insertion;
    private Deletion deletion;

    public SQLChampionManager(Insertion insertion, Deletion deletion) {
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
