/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import server.Director;
import server.Movie;

/**
 *
 * @author studente
 */
public class Repository {
    
    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Pass 'create' to initialize the database, 'run' to print the content of the database");
            System.exit(1);
        }

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/studente/se-2019_07.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            if (args[0].equals("create")) {
                statement.executeUpdate("DROP TABLE IF EXISTS movie;");
                statement.executeUpdate("DROP TABLE IF EXISTS director;");
                statement.executeUpdate("CREATE TABLE movie (ID STRING, title STRING, year STRING, directorID STRING);");
                statement.executeUpdate("CREATE TABLE director (ID STRING, name STRING, yearOfBirth STRING);");
                 
                Director first = new Director();
                    first.ID = "D1";
                    first.name="Mario Rossi";
                    first.yearOfBirth="22/12/1995";
                    
                Director second = new Director();
                    second.ID ="D2";
                    second.name = "Enrico Neri";
                    second.yearOfBirth="23/02/1990";
                    
                 PreparedStatement first_s = connection.prepareStatement(
                    "insert into director values (?, ?, ?);"); // sintax for putting record in database
                first_s.setString(1, first.ID);
                first_s.setString(2, first.name);
                first_s.setString(3, first.yearOfBirth);

                first_s.addBatch();
                connection.setAutoCommit(false);
                first_s.executeBatch();
                connection.setAutoCommit(true);
                
                PreparedStatement second_s = connection.prepareStatement(
                    "insert into director values (?, ?, ?);"); // sintax for putting record in database
                
                second_s.setString(1, second.ID);
                second_s.setString(2, second.name);
                second_s.setString(3, second.yearOfBirth);

                second_s.addBatch();
                connection.setAutoCommit(false);
                second_s.executeBatch();
                connection.setAutoCommit(true);
                    
                for(int i=0; i<15; i++){
                    
                    Movie m = new Movie();
                    m.ID = "m" + i;
                    
                    if(i%2==0){
                        m.directorID = "D1";
                    }else{
                        m.directorID = "D2";
                    }
                    
                    m.title = "Movie" + i;
                    m.year="2011";
                    
                    PreparedStatement m_statement = connection.prepareStatement(
                    "insert into movie values (?, ?, ?, ?);"); // sintax for putting record in database
                
                    m_statement.setString(1, m.ID);
                    m_statement.setString(2, m.title);
                    m_statement.setString(3, m.year);
                    m_statement.setString(4, m.directorID);
                          
                    m_statement.addBatch();
                    connection.setAutoCommit(false);
                    m_statement.executeBatch();
                    connection.setAutoCommit(true);
                    
                }
                
                connection.close();
                 
            } else{
                ResultSet rs1 = statement.executeQuery("SELECT * FROM movie");
                while (rs1.next()) {
                    System.out.println(String.format("%s : %s", rs1.getString("ID"), rs1.getString("title")));
                }
                System.out.println(" ");
                ResultSet rs2 = statement.executeQuery("SELECT * FROM director");
                 while (rs2.next()) {
                    System.out.println(String.format("%s : %s", rs1.getString("ID"), rs1.getString("name")));
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
    
    public static List<Movie> getMovies(){
        
        //be sure to initialize the databse first
        List<Movie> movies = new ArrayList();
        
        try{
            
            Class.forName("org.sqlite.JDBC");
            Connection connection = null;

            connection = DriverManager.getConnection("jdbc:sqlite:/home/studente/se-2019_07.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            ResultSet rs2 = statement.executeQuery("SELECT * FROM director");
            Map<String,Director> directors = new HashMap<String,Director>();
            
            while (rs2.next()) {
                Director dir = new Director();
                dir.ID = rs2.getString("ID");
                dir.name = rs2.getString("name");
                dir.yearOfBirth = rs2.getString("yearOfBirth");
               directors.put(dir.ID, dir);
            }
            
            ResultSet rs1 = statement.executeQuery("SELECT * FROM movie");
            while (rs1.next()) {
                Movie mov = new Movie();
                mov.ID = rs1.getString("ID");
                mov.title = rs1.getString("Title");
                mov.year = rs1.getString("year");
                String directorID = rs1.getString("directorID");
                mov.directorID = directorID;
                
                if(directorID != null){
                     
                    Director d = directors.get(directorID);
                    mov.director = d;
                }
          
                movies.add(mov);
            }
          
           
        }catch(Exception e){
        System.out.println(e);
        }
        
        return movies;
    }
}
