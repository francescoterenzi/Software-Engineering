/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.client;

import java.util.List;
import java.util.Map;
import soapclient.*;

/**
 *
 * @author studente
 */
public class SOAPClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Student s1 = new Student();
        s1.setName("Massimo");
        SOAPClient.helloStudent(s1);
        
        Student s2 = new Student();
        s2.setName("Monica");
        SOAPClient.helloStudent(s2);
        
        List<StudentEntry> result = SOAPClient.getStudents().getEntry();
        for (int i = 0 ; i<result.size(); i++){
            System.out.println(((StudentEntry)result.get(i)).getStudent().getName());
    }
        
    }

    private static StudentMap getStudents() {
        soapclient.WSImplService service = new soapclient.WSImplService();
        soapclient.WSInterface port = service.getWSImplPort();
        return port.getStudents();
    }

    private static String helloStudent(soapclient.Student arg0) {
        soapclient.WSImplService service = new soapclient.WSImplService();
        soapclient.WSInterface port = service.getWSImplPort();
        return port.helloStudent(arg0);
    }
    
}
