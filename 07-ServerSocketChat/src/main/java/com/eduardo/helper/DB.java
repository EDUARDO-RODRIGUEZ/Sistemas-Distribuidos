package com.eduardo.helper;

import java.sql.*;

public class DB {

    public static String LOG = DB.class.getName();

    public static String HOST = "localhost";
    public static int PORT = 5432;
    public static String DBNAME = "dbgame";
    public static String USER = "postgres";
    public static String PASSWORD = "edcr";

    private Connection connection;

    public void open() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + DBNAME, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(LOG + " : " + e.getMessage());
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(LOG + " : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setAutoCommit(boolean commit) {
        try {
            connection.setAutoCommit(commit);
        } catch (SQLException ex) {
            System.out.println(LOG + " : " + ex.getMessage());
        }
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            System.out.println(LOG + " : " + ex.getMessage());
        }
    }

}
