/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.l02.jdbcHandling.domain;

/**
 *
 * @author bilsis
 */
public class Person {
    private int idPerson;
    private String name;

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString(){
        return "Person {" + "idPerson = " + idPerson + ", name = "+name + "}";
    }
}
