/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.List;
import javax.jws.WebService;
import repository.Repository;

/**
 *
 * @author studente
 */
@WebService(endpointInterface = "server.WSInterface")
public class WSImplementation implements WSInterface {


    public List<Movie> getMovies() {
       return Repository.getMovies();
    }
    
}
