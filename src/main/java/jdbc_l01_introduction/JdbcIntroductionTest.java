/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_l01_introduction;

import java.sql.*;

/**
 *
 * @author bilsis
 */
public class JdbcIntroductionTest {

    public static void main(String[] args) {
        //MySql connection string, the useSSL parameter is optional
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false";
        try {
            //We loaded the mysql driver
            Class.forName("com.mysql.jdbc.Driver");
            //Create the connection object using try-with-resources to close the connection
            try (Connection connection = DriverManager.getConnection(url, "root", "admin")) {
            //Create a Statement object
                Statement statement = connection.createStatement();
                //Create the query string
                String sql = "SELECT id_person, name FROM person";
                //Execute the query and obtain the result
                ResultSet result = statement.executeQuery(sql);
                //Process the result for every column in the query
                while (result.next()) {
                    System.out.print("Id:" + result.getInt(1));
                    System.out.print(", Name:" + result.getString(2));
                    System.out.println("");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }

    }
}
