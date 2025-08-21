/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.db_Sql;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
/**
 *
 * @author semih
 */
public class sqlDbConnection {
    private String username = "user";
    private String password = "user1";
    private String url = "jdbc:sqlserver://A\\SQLEXPRESS:49699;databaseName=db_FlowTask;encrypt=true;trustServerCertificate=true";
    
    public  Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }
}
