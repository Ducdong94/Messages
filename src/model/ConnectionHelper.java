/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dongvu
 */
public class ConnectionHelper {

    private final String DATABASE_PREFIX = "jdbc:mysql://192.168.64.2:3306/";
    private final String DATABASE_NAME = "chatapp";
    private final String USERNAME = "ducdong94";
    private final String PASSWORD = "vJGvpTYPYb3I2oc8";
    private final String ENDCODING = "?characterEncoding=utf8";
    private static ConnectionHelper connectionHelper;
    private Connection con;

    public static ConnectionHelper getInstance() {
        if (null == connectionHelper) {
            connectionHelper = new ConnectionHelper();
        }
        return connectionHelper;
    }

    public Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(DATABASE_PREFIX + DATABASE_NAME + ENDCODING, USERNAME, PASSWORD);
            }
        } catch (SQLException ex) {
            System.err.println("> [CONNECT FAILED] - Can not connect to database " + DATABASE_NAME);
        }
        return con;
    }

    public void closeConnection() {
        try {
            if (con != null || !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            System.err.println("[Close Connection Failse]");
        }
    }

}
