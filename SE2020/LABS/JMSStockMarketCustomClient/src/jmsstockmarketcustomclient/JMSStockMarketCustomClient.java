/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmsstockmarketcustomclient;

import java.util.Observer;
import listner.AzioniListner;


/**
 *
 * @author studente
 */
public class JMSStockMarketCustomClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AzioniListner al = new AzioniListner();
        al.start();
    }
    
}
