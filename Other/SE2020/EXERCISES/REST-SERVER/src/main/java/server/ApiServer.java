/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
/**
 *
 * @author studente
 */
public class ApiServer {
     public static void main(String[] args){
       JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean(); 
        factoryBean.setResourceClasses(MovieRepository.class); // repository per i servizi rest
        factoryBean.setResourceProvider(new SingletonResourceProvider(new MovieRepository())); // repository effettiva
        factoryBean.setAddress("http://localhost:8080/"); // set the address
        Server server = factoryBean.create();

        System.out.println("Server ready...");
        //Thread.sleep(60 * 1000);
        //System.out.println("Server exiting");
        //server.destroy();
        //System.exit(0);
        while (true) {
        }
    }
}
