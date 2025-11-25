package com.example.cv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class db {
    private Connection connection;
    private Logger logger= Logger.getLogger(this.getClass().getName());
    public db() {
        this.connection = connection;
    }

    void get_connection(){

        try{
            if(connection==null || connection.isClosed()){
                connection= DriverManager.getConnection("jdbc:sqlite:cv.db");
                logger.info("Connected to database successfully");

            }

        } catch (SQLException e){
            logger.info(e.toString());
        }
    }
}
