/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.xml.ws.Endpoint;
import repository.Repository;


/**
 *
 * @author studente
 */
public class Server {
    
    public static void main (String[] args){
        WSImplementation implementor = new WSImplementation();
        String address = "http://localhost:8080/WSInterface";
        Endpoint.publish(address, implementor);
        System.out.println("Server started...");
        while(true) {}
    }
}
