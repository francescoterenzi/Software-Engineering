/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import edu.uniroma1.msecs.soapserver.Exam;
import edu.uniroma1.msecs.soapserver.ExamImplService;
import edu.uniroma1.msecs.soapserver.Professor;

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
        ExamImplService is = new ExamImplService();
        Exam port = is.getExamImplPort();
        return port.getDetails(ID);
    }
    
}