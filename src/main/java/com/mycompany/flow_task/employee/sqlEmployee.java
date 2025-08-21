/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.employee;

import com.mycompany.flow_task.db_Sql.sqlDbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author semih
 */
public class sqlEmployee {

    private sqlDbConnection sConn = new sqlDbConnection();

    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pstt = null;
    private Statement stt = null;

    public void getTableList(JTable table) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("SELECT tbl_Employee.id,name,department,title,admin,ISNULL(active,0) AS 'active' FROM tbl_Employee inner join tbl_Department ON tbl_Employee.departmentID=tbl_Department.id INNER JOIN tbl_Title ON tbl_Employee.titleID=tbl_Title.id LEFT JOIN tbl_EmployeeWork ON tbl_Employee.id=tbl_EmployeeWork.employeeID");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("department"), rs.getString("title"), rs.getBoolean("admin"), rs.getBoolean("active")});
            }

            rs.close();
            stt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.getTableList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.getTableList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableNameSearchList(JTable table, employee _employee) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT tbl_Employee.id,name,department,title,admin,ISNULL(active,0) AS 'active' FROM tbl_Employee inner join tbl_Department ON tbl_Employee.departmentID=tbl_Department.id INNER JOIN tbl_Title ON tbl_Employee.titleID=tbl_Title.id LEFT JOIN tbl_EmployeeWork ON tbl_Employee.id=tbl_EmployeeWork.employeeID WHERE name LIKE ?");
            pstt.setString(1, "%" + _employee.getName() + "%");
            rs = pstt.executeQuery();

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("department"), rs.getString("title"), rs.getBoolean("admin"), rs.getBoolean("active")});
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.getTableNameSearchList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.getTableNameSearchList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableSearchList(JTable table, String name, String department, String mail, boolean active) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT tbl_Employee.id,name,department,title,admin,ISNULL(active,0) AS 'active' FROM tbl_Employee inner join tbl_Department ON tbl_Employee.departmentID=tbl_Department.id INNER JOIN tbl_Title ON tbl_Employee.titleID=tbl_Title.id LEFT JOIN tbl_EmployeeWork ON tbl_Employee.id=tbl_EmployeeWork.employeeID WHERE name LIKE ? AND department LIKE ? AND mail LIKE ? AND (? IS NULL OR active = ?)");
            pstt.setString(1, "%" + name + "%");
            pstt.setString(2, "%" + department + "%");
            pstt.setString(3, "%" + mail + "%");
            if (active) {
                pstt.setBoolean(4, active);
                pstt.setBoolean(5, active);
            } else {
                pstt.setNull(4, java.sql.Types.NULL);
                pstt.setNull(5, java.sql.Types.NULL);
            }
            rs = pstt.executeQuery();

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("department"), rs.getString("title"), rs.getBoolean("admin"), rs.getBoolean("active")});
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.getTableSearchList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.getTableSearchList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableWorkList(JTable table, employee _employee) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT employeeID,date_time,status FROM tbl_EmployeeHistoryWork WHERE employeeID = ? ORDER BY date_time DESC");
            pstt.setInt(1, _employee.getId());
            rs = pstt.executeQuery();

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getInt("employeeID"), rs.getTimestamp("date_time"), rs.getString("status")});
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.getTableWorkList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.getTableWorkList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableWorkSearchList(JTable table, employee _employee, LocalDateTime start, LocalDateTime end) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT employeeID,date_time,status FROM tbl_EmployeeHistoryWork WHERE employeeID = ? AND date_time >= ? AND date_time <= ? ORDER BY date_time DESC");
            pstt.setInt(1, _employee.getId());
            pstt.setTimestamp(2, Timestamp.valueOf(start));
            pstt.setTimestamp(3, Timestamp.valueOf(end));
            rs = pstt.executeQuery();

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getInt("employeeID"), rs.getTimestamp("date_time"), rs.getString("status")});
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.getTableWorkList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.getTableWorkList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public employee getEmployeeInfo(int id) {
        employee getEmployee = new employee();

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,name,phone1,phone2,mail,fax,username,password,admin,titleID,departmentID,country,city,district,nightbrood,address FROM tbl_Employee WHERE id=?");
            pstt.setInt(1, id);
            rs = pstt.executeQuery();

            while (rs.next()) {
                getEmployee = new employee(rs.getInt("id"), rs.getString("name"), rs.getString("phone1"), rs.getString("phone2"), rs.getString("mail"), rs.getString("fax"), rs.getString("username"), rs.getString("password"), rs.getBoolean("admin"), rs.getInt("titleID"), rs.getInt("departmentID"), rs.getString("country"), rs.getString("city"), rs.getString("district"), rs.getString("nightbrood"), rs.getString("address"));
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.getEmployeeInfo()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.getEmployeeInfo()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return getEmployee;
    }

    public String getEmployeeName(int id) {
        String name = "";

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,name FROM tbl_Employee WHERE id=?");
            pstt.setInt(1, id);
            rs = pstt.executeQuery();

            while (rs.next()) {
                name = rs.getString("name");
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.getEmployeeName()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.getEmployeeName()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return name;
    }

    public boolean isThereUsername(String username) {
        boolean isThere = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT COUNT(id) AS 'count' FROM tbl_Employee WHERE username = ?");
            pstt.setString(1, username);
            rs = pstt.executeQuery();

            while (rs.next()) {
                if (rs.getInt("count") > 0) {
                    isThere = true;
                }
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.isThereUsername()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.isThereUsername()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isThere;
    }

    public boolean isAdmin(employee _employee) {
        boolean admin = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT admin FROM tbl_Employee WHERE id = ?");
            pstt.setInt(1, _employee.getId());
            rs = pstt.executeQuery();

            while (rs.next()) {
                admin = rs.getBoolean("admin");
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.isAdmin()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.isAdmin()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return admin;
    }

    public boolean isActive(employee _employee) {
        boolean active = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT active FROM tbl_EmployeeWork WHERE employeeID = ?");
            pstt.setInt(1, _employee.getId());
            rs = pstt.executeQuery();

            while (rs.next()) {
                active = rs.getBoolean("active");
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.isActive()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.isActive()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return active;
    }

    public int login(String username, String password) {
        int id = -1;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id FROM tbl_Employee WHERE username = ? COLLATE SQL_Latin1_General_CP1_CS_AS AND password = ? COLLATE SQL_Latin1_General_CP1_CS_AS");
            pstt.setString(1, username);
            pstt.setString(2, password);
            rs = pstt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.login()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.login()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return id;
    }

    public boolean setPassword(employee _emplyee) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("UPDATE tbl_Employee SET password = ? WHERE id = ?");
            pstt.setString(1, _emplyee.getPassword());
            pstt.setInt(2, _emplyee.getId());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.setPassword()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.setPassword()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean setContact(employee _emplyee) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("UPDATE tbl_Employee SET phone1 = ?, phone2 = ? WHERE id = ?");
            pstt.setString(1, _emplyee.getPhone1());
            pstt.setString(2, _emplyee.getPhone2());
            pstt.setInt(3, _emplyee.getId());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.setContact()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.setContact()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean add(employee _employee) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("INSERT INTO tbl_Employee (name,phone1,phone2,mail,fax,username,password,admin,titleID,departmentID,country,city,district,nightbrood,address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstt.setString(1, _employee.getName());
            pstt.setString(2, _employee.getPhone1());
            pstt.setString(3, _employee.getPhone2());
            pstt.setString(4, _employee.getMail());
            pstt.setString(5, _employee.getFax());
            pstt.setString(6, _employee.getUsername());
            pstt.setString(7, _employee.getPassword());
            pstt.setBoolean(8, _employee.isAdmin());
            pstt.setInt(9, _employee.getTitleID());
            pstt.setInt(10, _employee.getDepartmentID());
            pstt.setString(11, _employee.getCountry());
            pstt.setString(12, _employee.getCity());
            pstt.setString(13, _employee.getDistrict());
            pstt.setString(14, _employee.getNightbrood());
            pstt.setString(15, _employee.getAddress());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showMessageDialog(null, "Ünvan ya da Departman bilgisi yanlış, kontrol edip tekrar deneyin.\n(Ünvan ID/Departman ID girdiğiniz yerlere 'ENTER' basıp kontrol edebilirsiniz", "Ünvan/Departman Bilgileri Yanlış", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.add()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.add()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
            }

        }

        return isComplate;
    }

    public boolean addWork(employee _employee) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("INSERT INTO tbl_EmployeeWork (employeeID) values (?)");
            pstt.setInt(1, _employee.getId());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.addWork()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.addWork()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean addWorkHistoryStart(employee _employee) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("INSERT INTO tbl_EmployeeHistoryWork (employeeID,date_time,status) values (?,?,?)");
            pstt.setInt(1, _employee.getId());
            pstt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            pstt.setString(3, "MESAİ BAŞLADI");
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.addWorkHistoryStart()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.addWorkHistoryStart()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean addWorkHistoryEnd(employee _employee) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("INSERT INTO tbl_EmployeeHistoryWork (employeeID,date_time,status) values (?,?,?)");
            pstt.setInt(1, _employee.getId());
            pstt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            pstt.setString(3, "MESAİ BİTTİ");
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.addWorkHistoryEnd()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.addWorkHistoryEnd()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean update(employee _employee) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("UPDATE tbl_Employee SET name=?, phone1=?, phone2=?, mail=?, fax=?, username=?, password=?, admin=?, titleID=?, departmentID=?, country=?, city=?, district=?, nightbrood=?, address=? WHERE id=?");
            pstt.setString(1, _employee.getName());
            pstt.setString(2, _employee.getPhone1());
            pstt.setString(3, _employee.getPhone2());
            pstt.setString(4, _employee.getMail());
            pstt.setString(5, _employee.getFax());
            pstt.setString(6, _employee.getUsername());
            pstt.setString(7, _employee.getPassword());
            pstt.setBoolean(8, _employee.isAdmin());
            pstt.setInt(9, _employee.getTitleID());
            pstt.setInt(10, _employee.getDepartmentID());
            pstt.setString(11, _employee.getCountry());
            pstt.setString(12, _employee.getCity());
            pstt.setString(13, _employee.getDistrict());
            pstt.setString(14, _employee.getNightbrood());
            pstt.setString(15, _employee.getAddress());
            pstt.setInt(16, _employee.getId());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showMessageDialog(null, "Ünvan ya da Departman bilgisi yanlış, kontrol edip tekrar deneyin.\n(Ünvan ID/Departman ID girdiğiniz yerlere 'ENTER' basıp kontrol edebilirsiniz", "Ünvan/Departman Bilgileri Yanlış", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.update()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.update()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
            }
        }

        return isComplate;
    }

    public boolean delete(employee _employee) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("DELETE FROM tbl_Employee WHERE id = ?");
            pstt.setInt(1, _employee.getId());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showMessageDialog(null, "Çalışanın hala bilgileri bulunuyor.\n* Not defteri olabilir,\n* İş açmış olabilir\n* Mesai bilgisi olabilir", "[sqlEmployee.delete()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.delete()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.delete()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
            }
        }

        return isComplate;
    }

    public boolean deleteWork(employee _employee) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("DELETE FROM tbl_EmployeeWork WHERE employeeID = ?");
            pstt.setInt(1, _employee.getId());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.deleteWork()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.deleteWork()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }
    
    public boolean deleteHistoryWork(employee _employee) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("DELETE FROM tbl_EmployeeHistoryWork WHERE employeeID = ?");
            pstt.setInt(1, _employee.getId());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlEmployee.deleteHistoryWork()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlEmployee.deleteHistoryWork()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }
}
