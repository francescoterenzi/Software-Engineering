/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import listner.ProfessorListner;

/**
 *
 * @author studente
 */
public class Client {
    public static void main(String[] args){
        
        // start the listner for getting the message from the broker and the information of the professor
        ProfessorListner pl = new ProfessorListner();
        pl.start();
        
    }
}
