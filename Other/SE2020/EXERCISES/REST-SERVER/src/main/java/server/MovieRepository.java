/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import reposititory.Database;
import server.MovieMap.MovieEntry;

/**
 *
 * @author studente
 */
@Path("/")
@Produces("text/xml")
public class MovieRepository {
  

    
    @GET
    @Path("/movies")
    public MovieMap getMovies(@PathParam("movieID") String movieID) {   

        MovieMap map = new MovieMap();
        for(String key : Database.getMovies().keySet()){
            Movie m = Database.getMovies().get(key);
            MovieEntry entry = new MovieEntry();
            entry.setId(key);
            entry.setMovie(m);
            map.getEntries().add(entry);
         }
    
        return map;
    }

    
    @GET
    @Path("movies/{movieID}")
    public Movie getMovie(@PathParam("movieID") String movieID) {
        return findById(movieID);
    }
    
    @GET
    @Path("directors/{directorID}")
    public Director getDirector(@PathParam("directorID") String directorID) {
        return this.findDirectorById(directorID);
    }
    
    @DELETE
    @Path("movies/{movieID}")
    public Response deleteMovie(@PathParam("movieID") String movieID) {
        Database.deleteMovie(movieID);
        return Response.ok().build();
    }

    /*@PUT
    @Path("movies/{movieID}")
    public Response postMovie(@PathParam("movieID") String movieID, Movie movie) {
        Movie existingCourse = findById(movieID);
        if (existingCourse == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (existingCourse.equals(movieID)) {
            return Response.notModified().build();
        }
        Database.addMovie(movie);
        return Response.ok().build();
    }*/
    
    @POST
    @Path("movies/{movieID}")
    public Response postMovie(@PathParam("movieID") String movieID, Movie movie) {
        Database.addMovie(movie);
        return Response.ok().build();
    }
    
    private Director findDirectorById(String id){
        for(String key : Database.getMovies().keySet()){
             Movie mov =  Database.getMovies().get(key);
             if(mov.directorID.equals(id)){
                 return mov.director;
             }
        }
        return null;
    }


    private Movie findById(String id) {
        for(String key : Database.getMovies().keySet()){
            if(key.equals(id)){
                return Database.getMovies().get(id);
            }
        }
        return null;
    }
}
