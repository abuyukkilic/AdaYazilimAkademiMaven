/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.l03.jdbcHandling.test;

import data.*;
import java.sql.*;
import java.util.List;
import jdbc.l03.jdbcHandling.data.JdbcPerson;
import jdbc.l03.jdbcHandling.domain.Person;

/**
 *
 * @author bilsis
 */
public class JdbcHandlingTest {

    public static void main(String[] args) throws SQLException {
//We create a connection object, it will be shared
//for all the queries that we run
        Connection conn = null;
        try {
            conn = JavaConnection.getConnection();
//We check if the connection is in autocommit mode
//default is autocommit == true
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
//We create the object JdbcPerson
//we provide the connection created
            JdbcPerson jdbcPerson = new JdbcPerson(conn);
// we started to execute sentences
// remember that a transaction groups several SQL statements
// if something goes wrong changes are not made in the DB
            jdbcPerson.update(2, "KattyChange");
//We caused an error exceeding 45 characters
//of the name field
            jdbcPerson.insert("Miguel21234123123123123123123123123123123123123123");
//save the changes if no error is found
            conn.commit();
        } catch (SQLException e) {
//if any error is found, we execute the rollback
            try {
                System.out.println("Execute the rollback");
//We print the exception to the console
                e.printStackTrace(System.out);
//executhe the rollback
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace(System.out);
            }
        }
//List people to see any change
        listPeople();
    }

    private static void listPeople() throws SQLException {
        JdbcPerson jdbcPerson = new JdbcPerson();
        List<Person> people = jdbcPerson.select();
        for (Person person : people) {
            System.out.print(person);
            System.out.println("");
        }
    }

}
