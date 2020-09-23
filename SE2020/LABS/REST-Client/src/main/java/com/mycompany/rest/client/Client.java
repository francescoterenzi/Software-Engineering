/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rest.client;

import java.io.*;
import java.net.URL;
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
            Student s = getStudent(1,2);
            System.out.println(s.toString());
            Course c = getCourse(1);
            System.out.println(c.toString());
            createValidStudent();
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private static Student getStudent(int courseOrder, int id) throws IOException {
        final URL url = new URL(BASE_URL+"courses/" + courseOrder + "/students/" + id);
        final InputStream input = url.openStream();
        return JAXB.unmarshal(new InputStreamReader(input), Student.class);
    }
    
    private static Course getCourse(int courseOrder)throws IOException{
        final URL url = new URL(BASE_URL+"courses/" + courseOrder);
        final InputStream input = url.openStream();
        return JAXB.unmarshal(new InputStreamReader(input), Course.class);
    }
    
    private static void createValidStudent() throws IOException {
        final HttpPost httpPost = new HttpPost(BASE_URL+"courses/"+ "2/students");
        
        final InputStream resourceStream = Client.class.getClassLoader().getResourceAsStream("newStudent.xml");
        
        httpPost.setEntity(new InputStreamEntity(resourceStream));
        httpPost.setHeader("Content-Type", "text/xml");

        final HttpResponse response = client.execute(httpPost);
        System.out.println(response.toString());
    }
    
}
