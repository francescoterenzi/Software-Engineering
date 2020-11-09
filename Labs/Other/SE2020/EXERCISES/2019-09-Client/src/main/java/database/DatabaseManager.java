/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
   
    //Print the content of the db
    public static void printContent(){
      
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:/home/studente/se-2019_09.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs1 = statement.executeQuery("SELECT * FROM flights");
            while (rs1.next()) {
                System.out.println(String.format("%s : %s", rs1.getString("flight"), rs1.getString("status")));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static void addFlight(String flight,String landed){
        
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:/home/studente/se-2019_09.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            PreparedStatement prep = connection.prepareStatement(
                    "insert into flights values (?, ?);"); // sintax for putting record in database
            prep.setString(1, flight);
            System.out.println(landed);
          
            
            if(landed.equals("true")){
                 System.out.println(landed + "So I write landed");
                 prep.setString(2, "landed");
            }else{
                System.out.println(landed + "So I write not landed");
                 prep.setString(2, "not landed");
            }
           
            
            prep.addBatch();
            connection.setAutoCommit(false);
            prep.executeBatch();
            connection.setAutoCommit(true);
            connection.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static void main(String[] args) throws ClassNotFoundException {
        
        if (args.length < 1) {
            System.out.println("Pass 'create' to initialize the database, 'run' to print the content of the database");
            System.exit(1);
        }

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/studente/se-2019_09.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            if (args[0].equals("create")) {
                statement.executeUpdate("DROP TABLE IF EXISTS flights;");
                statement.executeUpdate("CREATE TABLE flights (flight STRING, status STRING);");
                statement.executeUpdate("INSERT INTO flights VALUES('AA123', 'landed');");
            } else{
                ResultSet rs1 = statement.executeQuery("SELECT * FROM flights");
                while (rs1.next()) {
                    System.out.println(String.format("%s : %s", rs1.getString("flight"), rs1.getString("status")));
                }
            }
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
}