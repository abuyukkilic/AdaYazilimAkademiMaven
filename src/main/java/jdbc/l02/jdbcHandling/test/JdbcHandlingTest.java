/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.l02.jdbcHandling.test;

import java.util.List;
import jdbc.l02.jdbcHandling.data.JdbcPerson;
import jdbc.l02.jdbcHandling.domain.Person;

/**
 *
 * @author bilsis
 */
public class JdbcHandlingTest {

    public static void main(String[] args) {
        JdbcPerson jdbcPerson = new JdbcPerson();
        //Test of the insert method
        //jdbcPerson.insert("Fehime");
        //Test of the update method
        //jdbcPerson.update(1, "John");
        //Test the delete method, the id_person must exist in the database
        //jdbcPerson.delete(4);
        //Select method test
        //Use of a person object to encapsulate the information
        //of a database record
        List<Person> people = jdbcPerson.select();
        for (Person person : people) {
            System.out.print(person);
            System.out.println("");
        }
    }
    
    
    /*It is requested to create a Java project with the following characteristics:
Create a table called user in mysql in the schema: test
id_user (int) (Primary Key)
user (varchar)
password (varchar)
Create the classes similar to the Handling JDBC with the Person table, but
in this case for handling the operations SELECT, INSERT, UPDATE, DELETE
for the user table. */
}
