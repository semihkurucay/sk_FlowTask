/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.task;

import com.mycompany.flow_task.db_Sql.sqlDbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author semih
 */
public class sqlTask {

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
            rs = stt.executeQuery("SELECT t.id, t.costumerID, c.compName, t.costumerReport, pt.process_type, tp.title, tp.toEmployeeID, e.name, tp.number, t.costumerDate FROM tbl_TaskInfo t INNER JOIN tbl_Costumer c ON t.costumerID = c.id INNER JOIN (SELECT taskID, MAX(number) AS MaxNumber FROM tbl_TaskProcess GROUP BY taskID) tpMax ON t.id = tpMax.taskID INNER JOIN tbl_TaskProcess tp ON t.id = tp.taskID AND tp.number = tpMax.MaxNumber INNER JOIN tbl_Employee e ON tp.toEmployeeID = e.id INNER JOIN tbl_ProcessType pt ON tp.process_typeID = pt.id");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getInt("id"), rs.getString("costumerID"), rs.getString("compName"), rs.getString("costumerReport"), rs.getString("process_type"), rs.getString("title"), rs.getInt("toEmployeeID"), rs.getString("name"), rs.getInt("number"), rs.getDate("costumerDate")});
            }

            rs.close();
            stt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.getTableList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.getTableList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableSearchList(JTable table, String employeeID, String processID, String costumerID, LocalDateTime start, LocalDateTime end) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT t.id, t.costumerID, c.compName, t.costumerReport, pt.process_type, tp.title, tp.toEmployeeID, e.name, tp.number, t.costumerDate FROM tbl_TaskInfo t INNER JOIN tbl_Costumer c ON t.costumerID = c.id INNER JOIN (SELECT taskID, MAX(number) AS MaxNumber FROM tbl_TaskProcess GROUP BY taskID) tpMax ON t.id = tpMax.taskID INNER JOIN tbl_TaskProcess tp ON t.id = tp.taskID AND tp.number = tpMax.MaxNumber INNER JOIN tbl_Employee e ON tp.toEmployeeID = e.id INNER JOIN tbl_ProcessType pt ON tp.process_typeID = pt.id WHERE (? IS NULL OR tp.toEmployeeID = ?) AND (? IS NULL OR t.costumerID = ?) AND (? IS NULL OR tp.process_typeID = ?) AND costumerDate >= ? AND costumerDate <= ?");

            if (employeeID == null) {
                pstt.setNull(1, java.sql.Types.NULL);
                pstt.setNull(2, java.sql.Types.NULL);
            } else {
                pstt.setInt(1, Integer.valueOf(employeeID));
                pstt.setInt(2, Integer.valueOf(employeeID));
            }

            if (costumerID == null) {
                pstt.setNull(3, java.sql.Types.NULL);
                pstt.setNull(4, java.sql.Types.NULL);
            } else {
                pstt.setString(3, costumerID);
                pstt.setString(4, costumerID);
            }

            if (processID == null) {
                pstt.setNull(5, java.sql.Types.NULL);
                pstt.setNull(6, java.sql.Types.NULL);
            } else {
                pstt.setInt(5, Integer.valueOf(processID));
                pstt.setInt(6, Integer.valueOf(processID));
            }
            
            pstt.setTimestamp(7, Timestamp.valueOf(start));
            pstt.setTimestamp(8, Timestamp.valueOf(end));

            rs = pstt.executeQuery();
            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getInt("id"), rs.getString("costumerID"), rs.getString("compName"), rs.getString("costumerReport"), rs.getString("process_type"), rs.getString("title"), rs.getInt("toEmployeeID"), rs.getString("name"), rs.getInt("number"), rs.getDate("costumerDate")});
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.getTableSearchList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.getTableSearchList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableProcessList(JTable table, taskProcess _taskProcess) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT tp.taskID,tp.number,tp.title,tp.process_typeID,pt.process_type,tp.comment,tp.toEmployeeID,toEmp.name AS 'toEmployeeName',tp.fromEmployeeID,fromEmp.name AS 'fromEmployeeName' FROM tbl_TaskProcess tp INNER JOIN tbl_Employee toEmp ON tp.toEmployeeID=toEmp.id INNER JOIN tbl_Employee fromEmp ON tp.fromEmployeeID=fromEmp.id INNER JOIN tbl_ProcessType pt ON tp.process_typeID=pt.id WHERE tp.taskID = ? ORDER BY number ASC");
            pstt.setInt(1, _taskProcess.getTaskNo());
            rs = pstt.executeQuery();

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getInt("taskID"), rs.getInt("number"), rs.getString("title"), rs.getInt("process_typeID"), rs.getString("process_type"), rs.getString("comment"), rs.getInt("toEmployeeID"), rs.getString("toEmployeeName"), rs.getInt("fromEmployeeID"), rs.getString("fromEmployeeName")});
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.getTableProcessList()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.getTableProcessList()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getCreatTaskID() {
        int id = -1;

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("SELECT ISNULL(MAX(id), 0) AS id FROM tbl_TaskInfo");

            while (rs.next()) {
                id = rs.getInt("id") + 1;
            }

            rs.close();
            stt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.getCreatTaskID()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.getCreatTaskID()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return id;
    }

    public int getProcessNumber(int taskNo) {
        int id = -1;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT ISNULL(MAX(number), 0) AS count FROM tbl_TaskProcess WHERE taskID = ?");
            pstt.setInt(1, taskNo);
            rs = pstt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("count");
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.getProcessNumber()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.getProcessNumber()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return id;
    }

    public task getTaskInfo(task _task) {
        task rTask = new task();

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT id,costumerID,costumerContact,costumerReport,costumerDate FROM tbl_TaskInfo WHERE id = ?");
            pstt.setInt(1, _task.getTaskNo());
            rs = pstt.executeQuery();

            while (rs.next()) {
                rTask.setTaskNo(rs.getInt("id"));
                rTask.setCostumerID(rs.getString("costumerID"));
                rTask.setCostumerContact(rs.getString("costumerContact"));
                rTask.setCostumerReport(rs.getString("costumerReport"));
                rTask.setDate(LocalDate.parse(rs.getDate("costumerDate").toString()));
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.getTaskInfo()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.getTaskInfo()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return rTask;
    }

    public taskProcess getTaskProcessInfo(taskProcess _taskProcess) {
        taskProcess rTaskProcess = new taskProcess();

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT taskID,title,process_typeID,comment,toEmployeeID,fromEmployeeID,number FROM tbl_TaskProcess WHERE taskID = ? AND number = ?");
            pstt.setInt(1, _taskProcess.getTaskNo());
            pstt.setInt(2, _taskProcess.getNumber());
            rs = pstt.executeQuery();

            while (rs.next()) {
                rTaskProcess.setTaskNo(rs.getInt("taskID"));
                rTaskProcess.setTitle(rs.getString("title"));
                rTaskProcess.setProcessTypeID(rs.getInt("process_typeID"));
                rTaskProcess.setComment(rs.getString("comment"));
                rTaskProcess.setToEmployeeID(rs.getInt("toEmployeeID"));
                rTaskProcess.setFromEmployeeID(rs.getInt("fromEmployeeID"));
                rTaskProcess.setNumber(rs.getInt("number"));
            }

            rs.close();
            pstt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.getTaskProcessInfo()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.getTaskProcessInfo()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return rTaskProcess;
    }

    public boolean isThereTask(int taskNo) {
        boolean isThere = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("SELECT COUNT(id) AS count FROM tbl_TaskInfo WHERE id = ?");
            pstt.setInt(1, taskNo);
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
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.isThereTask()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.isThereTask()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isThere;
    }

    public boolean addTask(task _task) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("INSERT INTO tbl_TaskInfo (id, costumerDate) values (?,?)");
            pstt.setInt(1, _task.getTaskNo());
            pstt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.addTask()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.addTask()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean addProcess(taskProcess _taskProcess) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("INSERT INTO tbl_TaskProcess (taskID,title,process_typeID,comment,toEmployeeID,fromEmployeeID,number) values (?,?,?,?,?,?,?)");
            pstt.setInt(1, _taskProcess.getTaskNo());
            pstt.setString(2, _taskProcess.getTitle());
            pstt.setInt(3, _taskProcess.getProcessTypeID());
            pstt.setString(4, _taskProcess.getComment());
            pstt.setInt(5, _taskProcess.getToEmployeeID());
            pstt.setInt(6, _taskProcess.getFromEmployeeID());
            pstt.setInt(7, _taskProcess.getNumber());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showMessageDialog(null, "İşlem ID veya Çalışan ID bulunamadı.\nTekrar kontrol edip tekar deneyin.", "ID Bulunamadı", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.addProcess()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.addProcess()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
            }
        }

        return isComplate;
    }

    public boolean updateTask(task _task) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("UPDATE tbl_TaskInfo SET costumerID = ?, costumerContact = ?, costumerReport = ? WHERE id = ?");
            pstt.setString(1, _task.getCostumerID());
            pstt.setString(2, _task.getCostumerContact());
            pstt.setString(3, _task.getCostumerReport());
            pstt.setInt(4, _task.getTaskNo());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showMessageDialog(null, "Müşteri ID bulunamadı.\nTekrar kontrol edip tekar deneyin.", "ID Bulunamadı", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.updateTask()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.updateTask()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
            }
        }

        return isComplate;
    }

    public boolean updateProcess(taskProcess _taskProcess) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("UPDATE tbl_TaskProcess SET title = ?,process_typeID = ?,comment = ?,toEmployeeID = ?,fromEmployeeID = ? WHERE number = ? AND taskID = ?");

            pstt.setString(1, _taskProcess.getTitle());
            pstt.setInt(2, _taskProcess.getProcessTypeID());
            pstt.setString(3, _taskProcess.getComment());
            pstt.setInt(4, _taskProcess.getToEmployeeID());
            pstt.setInt(5, _taskProcess.getFromEmployeeID());
            pstt.setInt(6, _taskProcess.getNumber());
            pstt.setInt(7, _taskProcess.getTaskNo());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showMessageDialog(null, "İşlem ID veya Çalışan ID bulunamadı.\nTekrar kontrol edip tekar deneyin.", "ID Bulunamadı", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.updateProcess()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.updateProcess()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
            }
        }

        return isComplate;
    }

    public boolean deleteTask(task _task) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("DELETE FROM tbl_TaskInfo WHERE id = ?");
            pstt.setInt(1, _task.getTaskNo());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showMessageDialog(null, "Silmek istediğiniz ünvan kullanılmaktadır.\nKullanılan ünvanı başka bir şey ile değiştirin ve sonra tekrar silmeyi deneyin.", "Ünvan Kullanımda", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.deleteTask()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.deleteTask()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
            }
        }

        return isComplate;
    }

    public boolean deleteProcess(taskProcess _taskProcess) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("DELETE FROM tbl_TaskProcess WHERE taskID = ?");
            pstt.setInt(1, _taskProcess.getTaskNo());
            pstt.executeUpdate();

            pstt.close();
            conn.close();

            isComplate = true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showMessageDialog(null, "Silmek istediğiniz ünvan kullanılmaktadır.\nKullanılan ünvanı başka bir şey ile değiştirin ve sonra tekrar silmeyi deneyin.", "Ünvan Kullanımda", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "SQL'de bir hata ile karşılaşıldı!\nHata sınıfı : sqlTask.deleteProcess()\nHata Kodu : " + e.getErrorCode() + "\nHata Mesajı : " + e.getMessage(), "[sqlTask.deleteProcess()] SQL Hatası", JOptionPane.ERROR_MESSAGE);
            }
        }

        return isComplate;
    }
}
