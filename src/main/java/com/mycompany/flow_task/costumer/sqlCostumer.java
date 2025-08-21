/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.costumer;

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
public class sqlCostumer {

    private sqlDbConnection sConn = new sqlDbConnection();

    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pstt = null;
    private Statement stt = null;

    public void getTableEditList(JTable table) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("SELECT id,compName,taxOffice,compNo,compPhone,compMail FROM tbl_Costumer");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getString("id"), rs.getString("compName"), rs.getString("taxOffice"), rs.getString("compNo"), rs.getString("compPhone"), rs.getString("compMail")});
            }

            rs.close();
            stt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlCostumer.getTableEditList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlCostumer.getTableEditList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableEditSearchList(JTable table, String name) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,compName,taxOffice,compNo,compPhone,compMail FROM tbl_Costumer WHERE compName LIKE ?");
            pstt.setString(1, "%"+name+"%");
            rs = pstt.executeQuery();

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getString("id"), rs.getString("compNo"), rs.getString("taxOffice"), rs.getString("compPhone"), rs.getBoolean("compMail")});
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlCostumer.getTableEditSearchList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlCostumer.getTableEditSearchList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableList(JTable table) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("SELECT id,compName,taxOffice,compNo,compPhone,compMail,compFax,adminName,adminTitle,adminDepartment,adminPhone,adminMail,adminFax,country,city,district,nightbrood,address FROM tbl_Costumer");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getString("id"),rs.getString("compName"), rs.getString("taxOffice"), rs.getString("compNo"), rs.getString("compPhone"), rs.getString("compMail"), rs.getString("compFax"), rs.getString("adminName"), rs.getString("adminTitle"), rs.getString("adminDepartment"), rs.getString("adminPhone"), rs.getString("adminFax"), rs.getString("country"), rs.getString("city"), rs.getString("district"), rs.getString("nightbrood"), rs.getString("address")});
            }

            rs.close();
            stt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlCostumer.getTableList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlCostumer.getTableList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableSearchList(JTable table, String name) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,compName,taxOffice,compNo,compPhone,compMail,compFax,adminName,adminTitle,adminDepartment,adminPhone,adminMail,adminFax,country,city,district,nightbrood,address FROM tbl_Costumer WHERE compName LIKE ?");
            pstt.setString(1, "%"+name+"%");
            rs = pstt.executeQuery();

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getString("id"),rs.getString("compName"), rs.getString("taxOffice"), rs.getString("compNo"), rs.getString("compPhone"), rs.getString("compMail"), rs.getString("compFax"), rs.getString("adminName"), rs.getString("adminTitle"), rs.getString("adminDepartment"), rs.getString("adminPhone"), rs.getString("adminFax"), rs.getString("country"), rs.getString("city"), rs.getString("district"), rs.getString("nightbrood"), rs.getString("address")});
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlCostumer.getTableSearchList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlCostumer.getTableSearchList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public costumer getCostumerInfo(String id) {
        costumer _costumer = new costumer();

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,taxOffice,compNo,compName,compPhone,compMail,compFax,adminName,adminTitle,adminDepartment,adminPhone,adminMail,adminFax,country,city,district,nightbrood,address FROM tbl_Costumer WHERE id = ?");
            pstt.setString(1, id);
            rs = pstt.executeQuery();

            while (rs.next()) {
                _costumer.setId(rs.getString("id"));
                _costumer.setTaxOffice(rs.getString("taxOffice"));
                _costumer.setCompNo(rs.getString("compNo"));
                _costumer.setCompName(rs.getString("compName"));
                _costumer.setCompPhone(rs.getString("compPhone"));
                _costumer.setCompMail(rs.getString("compMail"));
                _costumer.setCompFax(rs.getString("compFax"));
                
                _costumer.setAdminName(rs.getString("adminName"));
                _costumer.setAdminTitle(rs.getString("adminTitle"));
                _costumer.setAdminDepartment(rs.getString("adminDepartment"));
                _costumer.setAdminPhone(rs.getString("adminPhone"));
                _costumer.setAdminFax(rs.getString("adminFax"));
                
                _costumer.setCountry(rs.getString("country"));
                _costumer.setCity(rs.getString("city"));
                _costumer.setDistrict(rs.getString("district"));
                _costumer.setNightbrood(rs.getString("nightbrood"));
                _costumer.setAddress(rs.getString("address"));
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlCostumer.getCostumerInfo()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlCostumer.getCostumerInfo()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return _costumer;
    }
    
    public String getCostumerName(String id) {
        String name = "";

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,compName FROM tbl_Costumer WHERE id = ?");
            pstt.setString(1, id);
            rs = pstt.executeQuery();

            while (rs.next()) {
                name = rs.getString("compName");
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlCostumer.getCostumerName()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlCostumer.getCostumerName()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return name;
    }

    public boolean isThereCompID(String id) {
        boolean isThere = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT COUNT(id) AS 'count' FROM tbl_Costumer WHERE id = ?");
            pstt.setString(1, id);
            rs = pstt.executeQuery();

            while (rs.next()) {
                if (rs.getInt("count") > 0) {
                    isThere = true;
                }
            }

            rs.close();
            stt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlCostumer.isThereCompID()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlCostumer.isThereCompID()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isThere;
    }

    public boolean add(costumer _costumer) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("INSERT INTO tbl_Costumer (id,taxOffice,compNo,compName,compPhone,compMail,compFax,adminName,adminTitle,adminDepartment,adminPhone,adminMail,adminFax,country,city,district,nightbrood,address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstt.setString(1, _costumer.getId());
            pstt.setString(2, _costumer.getTaxOffice());
            pstt.setString(3, _costumer.getCompNo());
            pstt.setString(4, _costumer.getCompName());
            pstt.setString(5, _costumer.getCompPhone());
            pstt.setString(6, _costumer.getCompMail());
            pstt.setString(7, _costumer.getCompFax());
            pstt.setString(8, _costumer.getAdminName());
            pstt.setString(9, _costumer.getAdminTitle());
            pstt.setString(10, _costumer.getAdminDepartment());
            pstt.setString(11, _costumer.getAdminPhone());
            pstt.setString(12, _costumer.getAdminMail());
            pstt.setString(13, _costumer.getAdminFax());
            pstt.setString(14, _costumer.getCountry());
            pstt.setString(15, _costumer.getCity());
            pstt.setString(16, _costumer.getDistrict());
            pstt.setString(17, _costumer.getNightbrood());
            pstt.setString(18, _costumer.getAddress());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlCostumer.add()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlCostumer.add()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean update(costumer _costumer) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("UPDATE tbl_Costumer SET taxOffice = ?,compNo = ?,compName = ?,compPhone = ?,compMail = ?,compFax = ?,adminName = ?,adminTitle = ?,adminDepartment = ?,adminPhone = ?,adminMail = ?,adminFax = ?,country = ?,city = ?,district = ?,nightbrood = ?,address = ? WHERE id = ?");
            pstt.setString(1, _costumer.getTaxOffice());
            pstt.setString(2, _costumer.getCompNo());
            pstt.setString(3, _costumer.getCompName());
            pstt.setString(4, _costumer.getCompPhone());
            pstt.setString(5, _costumer.getCompMail());
            pstt.setString(6, _costumer.getCompFax());
            pstt.setString(7, _costumer.getAdminName());
            pstt.setString(8, _costumer.getAdminTitle());
            pstt.setString(9, _costumer.getAdminDepartment());
            pstt.setString(10, _costumer.getAdminPhone());
            pstt.setString(11, _costumer.getAdminMail());
            pstt.setString(12, _costumer.getAdminFax());
            pstt.setString(13, _costumer.getCountry());
            pstt.setString(14, _costumer.getCity());
            pstt.setString(15, _costumer.getDistrict());
            pstt.setString(16, _costumer.getNightbrood());
            pstt.setString(17, _costumer.getAddress());
            pstt.setString(18, _costumer.getId());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlCostumer.update()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlCostumer.update()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean delete(costumer _costumer) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("DELETE FROM tbl_Costumer WHERE id = ?");
            pstt.setString(1, _costumer.getId());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlCostumer.delete()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlCostumer.delete()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }
}
