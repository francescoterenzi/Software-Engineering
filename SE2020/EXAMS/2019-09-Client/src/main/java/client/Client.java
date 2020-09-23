/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author studente
 */
public class Client {
    public static void main(String[] args){
       FlightListner listner = new FlightListner();
       listner.start();
       
    }
}
