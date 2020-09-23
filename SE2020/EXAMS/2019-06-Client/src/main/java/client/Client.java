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
        System.out.println("Client start");
        ProfessorListner pl = new ProfessorListner();
        pl.start();
    }
}
