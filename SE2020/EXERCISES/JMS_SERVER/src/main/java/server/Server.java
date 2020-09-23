/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author studente
 */
public class Server {
    public static void main(String[] args){
        try {
            ProduttoreQuotazioni pq = new ProduttoreQuotazioni(); // produce le quotazioni
            pq.start();
            
            NotificatoreAcquisto na = new NotificatoreAcquisto();
            na.start();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
