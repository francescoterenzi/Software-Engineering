/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author studente
 */
public class Quotazione {
    public String nome;
    public String ID;
    public float valore;
    
    public Quotazione(String nome,float valore){
        this.nome = nome;
        this.valore = valore;
    }
    
    @Override
    public String toString(){
        return this.nome + " " + this.valore;
    }
}
