package org.toby.database.tablemanagers;

import org.toby.database.delete.Deletion;
import org.toby.database.insert.Insertion;

public class SQLTableManager implements SQLManager {

    private Insertion insertion;
    private Deletion deletion;

    public SQLTableManager(Insertion insertion, Deletion deletion) {
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

    @Override
    public void setInsertion(Insertion insertion) {
        this.insertion = insertion;
    }

    @Override
    public void setDeletion(Deletion deletion) {
        this.deletion = deletion;
    }
}
