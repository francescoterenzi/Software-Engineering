/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Quotazione;

/**
 *
 * @author studente
 */
public class Database {
    
     private static String db_path = "jdbc:sqlite:/home/studente/JMS-2020_07.db";
     
     public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Pass 'create' to initialize the database, 'run' to print the content of the database");
            System.exit(1);
        }

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(db_path);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            if (args[0].equals("create")) {
                statement.executeUpdate("DROP TABLE IF EXISTS quotazione;");
                statement.executeUpdate("CREATE TABLE quotazione (ID STRING, name STRING, valore STRING);");
            }else{
             ResultSet rs = statement.executeQuery("SELECT * FROM quotazione");
                while (rs.next()) {
                    System.out.println(rs.getString("ID") + " " + rs.getString("name") +  " " + rs.getString("valore"));
                }
                System.out.println(" ");
            }
            
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     }
     
     public static void aggiungiQuotazione(Quotazione quot){
         try{
         
                Class.forName("org.sqlite.JDBC");
                Connection  connection = DriverManager.getConnection(db_path);
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
                PreparedStatement first_s = connection.prepareStatement(
                        "insert into quotazione values (?, ?, ?);"); // sintax for putting record in database
                first_s.setString(1, quot.ID);
                first_s.setString(2, quot.nome);
                first_s.setString(3, String.valueOf(quot.valore));

                first_s.addBatch();
                connection.setAutoCommit(false);
                first_s.executeBatch();
                connection.setAutoCommit(true);
                
         }catch(Exception e){
             e.printStackTrace();
         }
     }
}
