package org.toby.database.tablemanagers;

public abstract class SQLManager {

    protected String lolDbConnectionString = "jdbc:sqlserver://localhost:1434;"  + "databaseName=loldb;" + "integratedSecurity=true;";

    public abstract void insert();

    public abstract void delete();




}