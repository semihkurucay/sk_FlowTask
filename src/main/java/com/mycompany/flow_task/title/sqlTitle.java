/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.title;
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
public class sqlTitle {
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
            rs = stt.executeQuery("SELECT id,title FROM tbl_Title");
            
            while(rs.next()){
                mTable.addRow(new Object[]{rs.getInt("id"), rs.getString("title")});
            }
            
            rs.close();
            stt.close();
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTitle.getTableList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTitle.getTableList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void getTableSearchList(JTable table, title _title){
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,title FROM tbl_Title where title LIKE ?");
            pstt.setString(1, "%"+_title.getTitle()+"%");
            rs = pstt.executeQuery();
            
            while(rs.next()){
                mTable.addRow(new Object[]{rs.getInt("id"), rs.getString("title")});
            }
            
            rs.close();
            pstt.close();
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTitle.getTableSearchList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTitle.getTableSearchList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     public String getTitleName(int id){
        String name = "";
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,title FROM tbl_Title WHERE id = ?");
            pstt.setInt(1, id);
            rs = pstt.executeQuery();
            
            while(rs.next()){
                name = rs.getString("title");
            }
            
            rs.close();
            pstt.close();
            conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlDepartment.getTitleName()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlDepratment.getTitleName()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
        
        return name;
    }
    
    public boolean add(title _title){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("INSERT INTO tbl_Title (title) values (?)");
            pstt.setString(1, _title.getTitle());
            pstt.executeUpdate();
            
            pstt.close();
            conn.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTitle.add()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTitle.add()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
    
    public boolean update(title _title){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("UPDATE tbl_Title SET title = ? WHERE id=?");
            pstt.setString(1, _title.getTitle());
            pstt.setInt(2, _title.getId());
            pstt.executeUpdate();
            
            pstt.close();
            conn.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTitle.update()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTitle.update()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
    
    public boolean delete(title _title){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("DELETE FROM tbl_Title WHERE id = ?");
            pstt.setInt(1, _title.getId());
            pstt.executeUpdate();
            
            pstt.close();
            conn.close();
            
            isComplate = true;
        }catch(SQLException e){
            if(e.getErrorCode() == 547){
                JOptionPane.showMessageDialog(null, "Silmek istediğiniz ünvan kullanılmaktadır.\nKullanılan ünvanı başka bir şey ile değiştirin ve sonra tekrar silmeyi deneyin.", "Ünvan Kullanımda", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTitle.delete()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTitle.delete()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return isComplate;
    }
}
