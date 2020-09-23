/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.List;
import server.Director;
import server.Movie;
import server.WSImplementationService;
import server.WSInterface;

/**
 *
 * @author studente
 */
public class Client {

    public static void main(String[] args) {

        try {
            WSImplementationService service = new WSImplementationService();
            WSInterface port = service.getWSImplementationPort();

            List<Movie> movies = port.getMovies();
            for (Movie mov : movies) {
                System.out.println("Movie " + mov.getID() + " title " + mov.getTitle() + " " + mov.getYear() + "");
                Director d = mov.getDirector();
                if (d != null) {
                    System.out.println("Director " + d.getID() + " name " + d.getName() + " " + d.getYearOfBirth() + "");
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
