package com.Database;

//import java.sql.DriverManager;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;


        /**
        * DBHelper.java modeled from BookStore.
        */

public class DBHelper
{
            public static Connection getConnection() {

            System.out.println("DBHelper: -------- PostgreSQL " + "JDBC Connection  ------------");

            try {

                Class.forName("org.postgresql.Driver");

            } catch (ClassNotFoundException e) {

                System.out.println("DBHelper: Check Where  your PostgreSQL JDBC Driver exist and " + "Include in your library path!");
                e.printStackTrace();
                return null;

            }

            System.out.println("DBHelper: PostgreSQL JDBC Driver Registered!");

            Connection connection = null;

            try {

                /**
                 * Database hosted on Heroku using PostgreSQL add-on.
                 * NOTE: We were able to deploy our code in our Heroku app with the PostgreSQL add-on, however, we ran into trouble getting the credentials for the database to work here.
                 * The credentials are not pointing to the relational database correctly, causing DBHelper exceptions to be thrown when running a client interface.
                 */
                connection = DriverManager.getConnection("postgresql://ec2-54-83-55-115.compute-1.amazonaws.com:5432/dbnlucraumcoi5", "mivqlcuchuseyk", "64ca752c314f7ea94b5926bb9c3b12dc47c5146a40b7801062fec4a202391989");
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT VERSION()");

                if (rs.next()) {
                    System.out.println("DBHelper: The Database Version is " + rs.getString(1));
                }

            } catch (SQLException e) {

                System.out.println("DBHelper: Connection Failed. Check output console.");
                e.printStackTrace();
                return null;

            }

            if (connection != null) {
                System.out.println("DBHelper: You have a database connection.");
            } else {
                System.out.println("DBHelper: Failed to make connection.");
            }

            return connection;
        }
    }
