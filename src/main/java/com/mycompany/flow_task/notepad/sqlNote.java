/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.notepad;

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
public class sqlNote {

    private sqlDbConnection sConn = new sqlDbConnection();

    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pstt = null;
    private Statement stt = null;

    public void getTableList(JTable table, note _note) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,title FROM tbl_Note WHERE employeeID = ?");
            pstt.setInt(1, _note.getEmployeeID());
            rs = pstt.executeQuery();

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getInt("id"), rs.getString("title")});
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlNote.getTableList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlNote.getTableList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public note getNoteInfo(note _note) {
        note getNote = new note();

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,title,note,employeeID FROM tbl_Note WHERE id = ?");
            pstt.setInt(1, _note.getId());
            rs = pstt.executeQuery();

            while (rs.next()) {
                getNote.setId(rs.getInt("id"));
                getNote.setTitle(rs.getString("title"));
                getNote.setMessage(rs.getString("note"));
                getNote.setEmployeeID(rs.getInt("employeeID"));
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlNote.getNoteInfo()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlNote.getNoteInfo()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return getNote;
    }

    public boolean add(note _note) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("INSERT INTO tbl_Note (title,note,employeeID) values (?,?,?)");
            pstt.setString(1, _note.getTitle());
            pstt.setString(2, _note.getMessage());
            pstt.setInt(3, _note.getEmployeeID());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            if(e.getErrorCode() == 547){
                JOptionPane.showMessageDialog(null, "Çalışan ID sistemde kayıtlı olmadığı için işlem gerçekleşmiyor.", "Hatalı İşlem", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlNote.add()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlNote.add()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
            }
        }

        return isComplate;
    }

    public boolean update(note _note) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("UPDATE tbl_Note SET title = ?,note = ? WHERE id = ?");
            pstt.setString(1, _note.getTitle());
            pstt.setString(2, _note.getMessage());
            pstt.setInt(3, _note.getId());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlNote.update()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlNote.update()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean delete(note _note) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("DELETE FROM tbl_Note WHERE id = ?");
            pstt.setInt(1, _note.getId());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlNote.delete()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlNote.delete()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }
}
