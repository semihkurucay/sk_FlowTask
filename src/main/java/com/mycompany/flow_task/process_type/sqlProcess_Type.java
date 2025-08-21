/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.process_type;
import com.mycompany.flow_task.db_Sql.sqlDbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author semih
 */
public class sqlProcess_Type {
    private sqlDbConnection sConn = new sqlDbConnection();
    
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pstt = null;
    private Statement stt = null;
    
    public void getTableList(JTable table){
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);
        
        try{
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("SELECT id,process_type FROM tbl_ProcessType");
            
            while(rs.next()){
                mTable.addRow(new Object[]{rs.getInt("id"), rs.getString("process_type")});
            }
            
            rs.close();
            stt.close();
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlProcess_Type.getTableList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlProcess_Type.getTableList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void getTableSearchList(JTable table, process_type _process_type){
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,process_type FROM tbl_ProcessType WHERE process_type LIKE = ?");
            pstt.setString(1, "%"+_process_type.getProcess_type()+"%");
            rs = pstt.executeQuery();
            
            while(rs.next()){
                mTable.addRow(new Object[]{rs.getInt("id"), rs.getString("process_type")});
            }
            
            rs.close();
            pstt.close();
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlProcess_Type.getTableSearchList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlProcess_Type.getTableSearchList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getProcessName(int ID){
        String name = "";
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,process_type FROM tbl_ProcessType WHERE id = ?");
            pstt.setInt(1, ID);
            rs = pstt.executeQuery();
            
            while(rs.next()){
                name = rs.getString("process_type");
            }
            
            rs.close();
            pstt.close();
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlProcess_Type.getProcessName()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlProcess_Type.getProcessName()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
        
        return name;
    }
    
    public boolean add(process_type _process_type){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("INSERT INTO tbl_ProcessType (process_type) values (?)");
            pstt.setString(1, _process_type.getProcess_type());
            pstt.executeUpdate();
            
            pstt.close();
            conn.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlProcess_Type.add()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlProcess_Type.add()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
    
    public boolean update(process_type _process_type){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("UPDATE tbl_ProcessType SET process_type = ? WHERE id=?");
            pstt.setString(1, _process_type.getProcess_type());
            pstt.setInt(2, _process_type.getId());
            pstt.executeUpdate();
            
            pstt.close();
            conn.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlProcess_Type.update()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlProcess_Type.update()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
    
    public boolean delete(process_type _process_type){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("DELETE FROM tbl_ProcessType WHERE id = ?");
            pstt.setInt(1, _process_type.getId());
            pstt.executeUpdate();
            
            pstt.close();
            conn.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlProcess_Type.delete()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlProcess_Type.delete()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
}
