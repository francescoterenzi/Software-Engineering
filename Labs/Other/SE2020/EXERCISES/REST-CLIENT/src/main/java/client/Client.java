/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author studente
 */
public class Client {
    
    private static final String BASE_URL = "http://localhost:8080/";
    private static CloseableHttpClient client;
    
   public static void main(String[] args){
        try{
            client = HttpClients.createDefault();
            Movie m = getMovie("m0");
            System.out.println(m.toString());
           
            Director d1 = getDirector("D1");
            System.out.println(d1.toString());
            
            MovieMap map = getMovies();
            System.out.println(map.toString());
            
            postMovie();
            
            
        }catch(Exception e){
            System.out.println(e);
        }
   }
   
    private static Director getDirector(String ID) throws IOException {
        final URL url = new URL(BASE_URL + "directors/" + ID);
        final InputStream input = url.openStream();
        return JAXB.unmarshal(new InputStreamReader(input),Director.class);
    }
   
    private static void postMovie(){
        try {
            final HttpPost httpPost = new HttpPost(BASE_URL+"movies/" + "ID89");
            
            final InputStream resourceStream = Client.class.getClassLoader().getResourceAsStream("newMovie.xml");
            
            httpPost.setEntity(new InputStreamEntity(resourceStream));
            httpPost.setHeader("Content-Type", "text/xml");
            
            final HttpResponse response = client.execute(httpPost);
            System.out.println(response.toString());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    private static Movie getMovie(String ID) throws IOException {
        final URL url = new URL(BASE_URL + "movies/" + ID);
        final InputStream input = url.openStream();
        return JAXB.unmarshal(new InputStreamReader(input), Movie.class);
    }

    private static MovieMap getMovies() throws IOException {
        final URL url = new URL(BASE_URL + "movies");
        final InputStream input = url.openStream();
        return JAXB.unmarshal(new InputStreamReader(input), MovieMap.class);
    }
}
