/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.soap.webservice.professors;

import javax.jws.WebService;

/**
 *
 * @author Alegi
 */
@WebService
public interface Exam {
    public Professor getDetails(String id);
}