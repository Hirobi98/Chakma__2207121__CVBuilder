package com.example.cv;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
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
                connection= DriverManager.getConnection("jdbc:sqlite:cv2.db");
                logger.info("Connected to database successfully");
                create_table();

            }

        } catch (SQLException e){
            logger.info(e.toString());
        }
    }

    public void create_table(){
        get_connection();
        String entry="CREATE TABLE if not exists cv_entries ("
                + "fullName TEXT NOT NULL,"
                + "email TEXT NOT NULL,"
                + "phone TEXT,"
                + "address TEXT,"
                + "skills TEXT,"
                + "projects TEXT,"
                + "education TEXT,"
                + "workExperience TEXT,"

                + "PRIMARY KEY (fullName, email)"
                + ");";
        try(PreparedStatement statement = connection.prepareStatement(entry)){
            statement.execute();
            logger.info("table created successfully");

        }catch(SQLException e){
            logger.info(e.toString());
        }
    }

    public void insert_data(cv_data data){
        String entry="Insert into cv_entries(fullName,email,phone,address,skills,projects,education,workExperience) values(?,?,?,?,?,?,?,?)";
        get_connection();
        try(PreparedStatement statement= connection.prepareStatement(entry)){
            statement.setString(1,data.getFullname());
            statement.setString(2,data.getEmail());
            statement.setString(3,data.getPhone());
            statement.setString(4,data.getAddress());
            statement.setString(5,data.getSkills());
            statement.setString(6,data.getProjects());
            statement.setString(7,data.getEducation());
            statement.setString(8, data.getWorkexperience());
            statement.executeUpdate();
            logger.info("inserted data successfully");


        }catch(SQLException e){
            logger.severe("Error inserting data: ");
        }





    }

    public void update_data(cv_data data){
        String entry="Update cv_entries set "+
        "fullName=?,email=?,phone=?,address=?,skills=?,projects=?,education=?,workExperience=?)"
                + " where fullName=? and email=? ";
        get_connection();
        try(PreparedStatement statement= connection.prepareStatement(entry)){
            statement.setString(1,data.getFullname());
            statement.setString(2,data.getEmail());
            statement.setString(3,data.getPhone());
            statement.setString(4,data.getAddress());
            statement.setString(5,data.getSkills());
            statement.setString(6,data.getProjects());
            statement.setString(7,data.getEducation());
            statement.setString(8, data.getWorkexperience());
            statement.executeUpdate();
            logger.info("updated data successfully");


        }catch(SQLException e){
            logger.severe("Error inserting data: ");
        }





    }

    public void delete_data(String fullname, String email){
        String entry="delete from cv_entries where fullName=? and email=?";
        get_connection();
        try(PreparedStatement statement= connection.prepareStatement(entry)){
            statement.setString(1,fullname);
            statement.setString(2,email);

            statement.executeUpdate();
            logger.info("deleted data successfully");


        }catch(SQLException e){
            logger.severe("Error inserting data: ");
        }





    }
    public ObservableList<cv_data>get_all_data(){
        ObservableList<cv_data> list = FXCollections.observableArrayList();
        String sql="select * from cv_entries;";

        try(Statement st=connection.createStatement()){
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                list.add(new cv_data(
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("skills"),
                        rs.getString("projects"),
                        rs.getString("education"),
                        rs.getString("workExperience")


                ));
            }

        }
        catch(SQLException e){
            logger.severe("Error inserting data: ");
        }
        return list;
    }






}
