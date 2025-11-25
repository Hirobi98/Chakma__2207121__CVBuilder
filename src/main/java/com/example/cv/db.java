package com.example.cv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
                create_table();

            }

        } catch (SQLException e){
            logger.info(e.toString());
        }
    }

    public void create_table(){
        get_connection();
        String entry="CREATE TABLE IF NOT EXISTS cv_entries ("
                + "id INTEGER PRIMARY KEY,"
                + "fullName TEXT NOT NULL,"
                + "email TEXT,"
                + "phone TEXT,"
                + "address TEXT,"
                + "skills TEXT,"
                + "projects TEXT,"
                + "education TEXT,"
                + "workExperience TEXT"
                + ");";
        try(PreparedStatement statement = connection.prepareStatement(entry)){
            statement.execute();
            logger.info("table created successfully");

        }catch(SQLException e){
            logger.info(e.toString());
        }
    }
}
