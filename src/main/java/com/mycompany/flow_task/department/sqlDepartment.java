/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.department;

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
public class sqlDepartment {
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
            rs = stt.executeQuery("SELECT id,department FROM tbl_Department");
            
            while(rs.next()){
                mTable.addRow(new Object[]{rs.getInt("id"), rs.getString("department")});
            }
            
            rs.close();
            stt.close();
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlDepartment.getTableList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlDepratment.getTableList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void getTableSearchList(JTable table, department _department){
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,department FROM tbl_Department where department LIKE ?");
            pstt.setString(1, "%"+_department.getDepartment()+"%");
            rs = pstt.executeQuery();
            
            while(rs.next()){
                mTable.addRow(new Object[]{rs.getInt("id"), rs.getString("department")});
            }
            
            rs.close();
            pstt.close();
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlDepartment.getTableSearchList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlDepratment.getTableSearchList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getDepartmentName(int id){
        String name = "";
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,department FROM tbl_Department WHERE id = ?");
            pstt.setInt(1, id);
            rs = pstt.executeQuery();
            
            while(rs.next()){
                name = rs.getString("department");
            }
            
            rs.close();
            pstt.close();
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlDepartment.getDepartmentName()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlDepratment.getDepartmentName()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
        
        return name;
    }
    
    public boolean add(department _department){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("INSERT INTO tbl_Department (department) values (?)");
            pstt.setString(1, _department.getDepartment());
            pstt.executeUpdate();
            
            pstt.close();
            conn.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlDepartment.add()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlDepratment.add()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
    
    public boolean update(department _department){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("UPDATE tbl_Department SET department = ? WHERE id=?");
            pstt.setString(1, _department.getDepartment());
            pstt.setInt(2, _department.getId());
            pstt.executeUpdate();
            
            pstt.close();
            conn.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlDepartment.update()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlDepratment.update()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
    
    public boolean delete(department _department){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("DELETE FROM tbl_Department WHERE id = ?");
            pstt.setInt(1, _department.getId());
            pstt.executeUpdate();
            
            pstt.close();
            conn.close();
            
            isComplate = true;
        }catch(SQLException e){
             if(e.getErrorCode() == 547){
                JOptionPane.showMessageDialog(null, "Silmek istediğiniz departman kullanılmaktadır.\nKullanılan departmanı başka bir şey ile değiştirin ve sonra tekrar silmeyi deneyin.", "Departman Kullanımda", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlDepartment.delete()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlDepratment.delete()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return isComplate;
    }
}
