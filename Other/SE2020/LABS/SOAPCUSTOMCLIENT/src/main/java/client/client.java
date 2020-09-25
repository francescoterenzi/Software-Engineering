/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import it.sapienza.softeng.soapws.Student;
import it.sapienza.softeng.soapws.StudentMap;
import it.sapienza.softeng.soapws.WSImplService;
import it.sapienza.softeng.soapws.WSInterface;

/**
 *
 * @author studente
 */
public class client {
    
    public static void main (String[] args){
        System.out.print("The service contains ");
        System.out.print(getStudents().getEntry().size());
        System.out.println(" students ");
        
        Student s = new Student();
        s.setName("Marco");
        helloStudent(s);
        
        System.out.print("The service contains ");
        System.out.print(getStudents().getEntry().size());
        System.out.println(" students ");
        
    }
    
     private static StudentMap getStudents() {
        WSImplService service = new WSImplService();
        WSInterface port = service.getWSImplPort();
        return port.getStudents();
    }

    private static String helloStudent(Student arg0) {
        WSImplService service = new WSImplService();
        WSInterface port = service.getWSImplPort();
        return port.helloStudent(arg0);
    }
    
    
    
}
