/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.l03.jdbcHandling.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jdbc.l03.jdbcHandling.domain.Person;

/**
 ** Class that contains the methods of SELECT, INSERT, UPDATE and DELETE for
 * the Person table in MYSQL
 *
 * @author bilsis
 */
public class JdbcPerson {
// We rely on MySql's autoincrementable primary key
// so the id_person field is omitted
// A prepareStatement is used, so we can
// use parameters (signs of ?)
// which will later be replaced by the respective parameter

    private java.sql.Connection userConn;
    private final String SQL_INSERT = "INSERT INTO person(name) VALUES(?)";
    private final String SQL_UPDATE = "UPDATE person SET name=? WHERE id_person=?";
    private final String SQL_DELETE = "DELETE FROM person WHERE id_person = ?";
    private final String SQL_SELECT = "SELECT id_person, name FROM person ORDER BY id_person";

    /*
    * Add the empty constructor
     */
    public JdbcPerson() {
    }

    /**
     * Constructor that assigns an existing connection to be used in the queries
     * of this class
     *
     * @param conn Connection to the DB previously created
     */
    public JdbcPerson(Connection conn) {
        this.userConn = conn;
    }

    /**
     * Method that inserts a record in the Person table
     *
     * @param name
     * @return int the number of modified rows
     * @throws java.sql.SQLException
     */
    public int insert(String name) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0; //affected rows
        try {
//If the connection to reuse is different from null, it is used, if not
//create a new connection
            conn = (this.userConn != null) ? this.userConn : JavaConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, name);//param 1 => ? name
            System.out.println("Executing query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Affected records:" + rows);
        } finally {
            if (this.userConn == null) {
                JavaConnection.close(conn);
            }
        }
        return rows;
    }

    /**
     * Method that updates an existing record
     *
     * @param idPerson Primary key
     * @param name Name value
     * @return int modified rows
     * @throws java.sql.SQLException
     */
    public int update(int idPerson, String name) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : JavaConnection.getConnection();
            System.out.println("Executing query:" + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, name);//param 1 => ? name
            stmt.setInt(2, idPerson);//param 2 => ? id_person
            rows = stmt.executeUpdate();
            System.out.println("Updated records:" + rows);
        } finally {
            if (this.userConn == null) {
                JavaConnection.close(conn);
            }
        }
        return rows;
    }

    /**
     * Method that deletes an existing record
     *
     * @param idPerson Primary key
     * @return int rows affected
     * @throws java.sql.SQLException
     */
    public int delete(int idPerson) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : JavaConnection.getConnection();
            System.out.println("Executing query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idPerson);//param 1 => ? id_person
            rows = stmt.executeUpdate();
            System.out.println("Deleted records:" + rows);
        } finally {
            if (this.userConn == null) {
                JavaConnection.close(conn);
            }
        }
        return rows;
    }

    /**
     * Method that returns the contents of the Person table
     *
     * @return list of person objects
     * @throws java.sql.SQLException
     */
    public List<Person> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Person persona = null;
        List<Person> personas = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : JavaConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_persona = rs.getInt(1);
                String nombre = rs.getString(2);
                persona = new Person();
                persona.setIdPerson(id_persona);
                persona.setName(nombre);
                personas.add(persona);
            }
        } finally {
            if (this.userConn == null) {
                JavaConnection.close(conn);
            }
        }
        return personas;
    }
}
