package com.projet.gnotes.dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class DbConnection{
    private static final String URL = "jdbc:mysql://localhost:3306/universite";
    private static final String USER = "root";
    private static final String PASSWORD = "root237";

    public static Connection connect() {
        try {

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}