/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapwebservice;

import java.util.Objects;

/**
 *
 * @author studente
 */
public class Student {
    
    private String nome;
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    @Override
    public boolean equals(Object o){
       Student that = (Student)o;
       return this.nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return this.nome.hashCode();
    }
}
