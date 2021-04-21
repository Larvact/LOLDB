package org.toby.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LolDbConnector {

    private Connection connection;
    private String dbConnectionString; //"jdbc:sqlserver://localhost:1434;"  + "databaseName=loldb;" + "integratedSecurity=true;";

    public LolDbConnector(String dbConnectionString) {
        this.dbConnectionString = dbConnectionString;
    }

    public void connect(){
        try {
            connection = DriverManager.getConnection(dbConnectionString);
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to connect to the Lol database with connection string: " + dbConnectionString);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String getDbConnectionString() {
        return dbConnectionString;
    }
}

