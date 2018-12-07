/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.l02.jdbcHandling.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jdbc.l02.jdbcHandling.domain.Person;

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

    private final String SQL_INSERT = "INSERT INTO person(name) VALUES(?)";
    private final String SQL_UPDATE = "UPDATE person SET name=? WHERE id_person=?";
    private final String SQL_DELETE = "DELETE FROM person WHERE id_person = ?";
    private final String SQL_SELECT = "SELECT id_person, name FROM person ORDER BY id_person";

    /**
     * Method that inserts a record in the Person table
     *
     * @param name
     */
    public int insert(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0; //affected rows
        try {
            conn = JavaConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, name);//param 1 => ? name
            System.out.println("Executing query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Affected records:" + rows);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            JavaConnection.close(stmt);
            JavaConnection.close(conn);
        }
        return rows;
    }

    /**
     * Method that updates an existing record
     *
     * @param idPerson Primary key
     * @param name Name value
     * @return int modified rows
     */
    public int update(int idPerson, String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = JavaConnection.getConnection();
            System.out.println("Executing query:" + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, name);//param 1 => ? name
            stmt.setInt(2, idPerson);//param 2 => ? id_person
            rows = stmt.executeUpdate();
            System.out.println("Updated records:" + rows);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            JavaConnection.close(stmt);
            JavaConnection.close(conn);
        }
        return rows;
    }

    /**
     * Method that deletes an existing record
     *
     * @param idPerson Primary key
     * @return int rows affected
     */
    public int delete(int idPerson) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = JavaConnection.getConnection();
            System.out.println("Executing query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idPerson);//param 1 => ? id_person
            rows = stmt.executeUpdate();
            System.out.println("Deleted records:" + rows);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            JavaConnection.close(stmt);
            JavaConnection.close(conn);
        }
        return rows;
    }

    /**
     * Method that returns the contents of the Person table
     *
     * @return list of person objects
     */
    public List<Person> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Person persona = null;
        List<Person> personas = new ArrayList<>();
        try {
            conn = JavaConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_persona = rs.getInt(1);
                String name = rs.getString(2);
                persona = new Person();
                persona.setIdPerson(id_persona);
                persona.setName(name);
                personas.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            JavaConnection.close(rs);
            JavaConnection.close(stmt);
            JavaConnection.close(conn);
        }
        return personas;
    }
}
