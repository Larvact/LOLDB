package org.toby.database.tablemanagers;

import org.toby.database.delete.Deletion;
import org.toby.database.insert.Insertion;

public interface SQLManager {

    void insert();

    void delete();

    void setInsertion(Insertion insertion);

    void setDeletion(Deletion deletion);

}
