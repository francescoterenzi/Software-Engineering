/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import it.sapienza.softeng.exam.Interface;
import it.sapienza.softeng.exam.InterfaceImplService;
import it.sapienza.softeng.exam.Professor;

/**
 *
 * @author studente
 */
public class SoapService {
    
    /**
     * Get The details of a professor from the SOAP web service from and ID
     * @author Giulio Serra 1904089
     */
    public static Professor getDetails(String ID){
        InterfaceImplService is = new InterfaceImplService();
        Interface port = is.getInterfaceImplPort();
        return port.getDetails(ID);
    }
    
}
