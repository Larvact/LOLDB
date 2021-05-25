package org.toby.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LolDbConnector {

    private Connection connection;
    private String dbConnectionString;
    private static final Logger logger = LogManager.getLogger(LolDbConnector.class.getName());

    public LolDbConnector(String dbConnectionString) {
        this.dbConnectionString = dbConnectionString;
    }

    public void connect(){
        try {
            if(connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbConnectionString);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeConnection(){
        if(this.connection != null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

}

