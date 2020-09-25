/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rest.client;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author studente
 */
@XmlRootElement(name = "Student")
public class Student {
    
    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + '}';
    }

    @Override
    public boolean equals(Object o) {
        Student that = (Student)o;
        return this.name.equals(that.name) && this.id == that.id;
    }

    @Override
    public int hashCode() {
        return this.id + this.name.hashCode();
    }
    
    
    
}
