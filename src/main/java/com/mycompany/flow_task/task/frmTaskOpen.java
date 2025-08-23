/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.flow_task.task;

import com.mycompany.flow_task.employee.employee;
import com.mycompany.flow_task.employee.sqlEmployee;
import com.mycompany.flow_task.process_type.sqlProcess_Type;
import com.mycompany.flow_task.costumer.sqlCostumer;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author semih
 */
public class frmTaskOpen extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmTaskOpen
     */
    public frmTaskOpen(employee _employee) {
        initComponents();

        this._employee = _employee;
        setTaskIcon();
        setCloseTask();
    }

    public frmTaskOpen(employee _employee, int taskID) {
        initComponents();

        this._employee = _employee;
        setTaskIcon();
        setCloseTask();

        txtTaskNo.setText(taskID + "");
    }

    boolean isOpenTask = false;
    DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"İş No", "İşlem No", "Başlık", "İşlem ID", "İşlem Adı", "Açıklama", "Gönderilen Çalışan ID", "Gönderilen Çalışan Adı", "Gönderen Çalışan ID", "Gönderen Çalışan Adı"});
    employee _employee;
    task _task = new task();
    taskProcess _taskProcess = new taskProcess();
    sqlEmployee sEmployee = new sqlEmployee();
    sqlProcess_Type sProcess_Type = new sqlProcess_Type();
    sqlCostumer sCostumer = new sqlCostumer();
    sqlTask sTask = new sqlTask();

    private void setTaskIcon() {
        tblList.setModel(model);

        btnOpen.setIcon(new ImageIcon(getClass().getResource("/resources/open.png")));
        btnRead.setIcon(new ImageIcon(getClass().getResource("/resources/read.png")));
        btnAdd.setIcon(new ImageIcon(getClass().getResource("/resources/add.png")));
        btnUpdate.setIcon(new ImageIcon(getClass().getResource("/resources/update.png")));
        btnHistoryUpdate.setIcon(new ImageIcon(getClass().getResource("/resources/update.png")));
        btnHistoryRefresh.setIcon(new ImageIcon(getClass().getResource("/resources/refresh.png")));
        btnDelete.setIcon(new ImageIcon(getClass().getResource("/resources/remove.png")));
        btnLeave.setIcon(new ImageIcon(getClass().getResource("/resources/leave.png")));
    }

    private void setOpenTask() {
        jTabbedPane1.setEnabled(true);
        txtTaskNo.setEnabled(false);

        jPanel4.setEnabled(true);
        txtCustomerID.setEnabled(true);
        txtCustomerName.setEnabled(true);
        txtCustomerContact.setEnabled(true);
        txtCustomerReport.setEnabled(true);

        jPanel3.setEnabled(true);
        txtProcessTitle.setEnabled(true);
        txtProcessTypeID.setEnabled(true);
        txtProcessTypeName.setEnabled(true);
        txtProcessComment.setEnabled(true);
        txtToEmployeeID.setEnabled(true);
        txtToEmployeeName.setEnabled(true);

        jPanel7.setEnabled(true);
        txtHistoryFromEmployeeID.setEnabled(true);
        txtHistoryFromEmployeeName.setEnabled(true);
        txtHistoryToEmployeeID.setEnabled(true);
        txtHistoryToEmployeeName.setEnabled(true);
        txtHistoryProcessTitle.setEnabled(true);
        txtHistoryProcessTypeID.setEnabled(true);
        txtHistoryProcessTypeName.setEnabled(true);
        txtHistoryProcessComment.setEnabled(true);

        refresh();
        if (_employee.isAdmin()) {
            setAdminOpen();
        } else {
            setAdminClose();
        }

        btnOpen.setEnabled(false);
        btnRead.setEnabled(false);
        btnDelete.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnLeave.setEnabled(true);
        btnAdd.setEnabled(true);
        btnHistoryRefresh.setEnabled(true);
    }

    private void setCloseTask() {
        jTabbedPane1.setEnabled(false);
        txtTaskNo.setEnabled(true);

        jPanel4.setEnabled(false);
        txtCustomerID.setEnabled(false);
        txtCustomerName.setEnabled(false);
        txtCustomerContact.setEnabled(false);
        txtCustomerReport.setEnabled(false);

        jPanel3.setEnabled(false);
        txtProcessTitle.setEnabled(false);
        txtProcessTypeID.setEnabled(false);
        txtProcessTypeName.setEnabled(false);
        txtProcessComment.setEnabled(false);
        txtToEmployeeID.setEnabled(false);
        txtToEmployeeName.setEnabled(false);

        jPanel7.setEnabled(false);
        btnHistoryUpdate.setEnabled(false);
        txtHistoryFromEmployeeID.setEnabled(false);
        txtHistoryFromEmployeeName.setEnabled(false);
        txtHistoryToEmployeeID.setEnabled(false);
        txtHistoryToEmployeeName.setEnabled(false);
        txtHistoryProcessTitle.setEnabled(false);
        txtHistoryProcessTypeID.setEnabled(false);
        txtHistoryProcessTypeName.setEnabled(false);
        txtHistoryProcessComment.setEnabled(false);

        btnOpen.setEnabled(true);
        btnRead.setEnabled(true);
        btnDelete.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnLeave.setEnabled(false);
        btnAdd.setEnabled(false);
        btnHistoryRefresh.setEnabled(false);
    }

    private void setTaskRemove() {
        txtTaskNo.setText("");
        model.setRowCount(0);
        tblList.setModel(model);

        txtCustomerID.setText("");
        txtCustomerName.setText("");
        txtCustomerContact.setText("");
        txtCustomerReport.setText("");

        txtProcessTitle.setText("");
        txtProcessTypeID.setText("");
        txtProcessTypeName.setText("");
        txtProcessComment.setText("");
        txtToEmployeeID.setText("");
        txtToEmployeeName.setText("");

        txtHistoryFromEmployeeID.setText("");
        txtHistoryFromEmployeeName.setText("");
        txtHistoryToEmployeeID.setText("");
        txtHistoryToEmployeeName.setText("");
        txtHistoryProcessTitle.setText("");
        txtHistoryProcessTypeID.setText("");
        txtHistoryProcessTypeName.setText("");
        txtHistoryProcessComment.setText("");
    }

    private void setAdminOpen() {
        btnHistoryUpdate.setEnabled(true);
        txtHistoryFromEmployeeID.setEditable(true);
        txtHistoryToEmployeeID.setEditable(true);
        txtHistoryProcessTitle.setEditable(true);
        txtHistoryProcessTypeID.setEditable(true);
        txtHistoryProcessComment.setEditable(true);
    }

    private void setAdminClose() {
        btnHistoryUpdate.setEnabled(false);
        txtHistoryFromEmployeeID.setEditable(false);
        txtHistoryToEmployeeID.setEditable(false);
        txtHistoryProcessTitle.setEditable(false);
        txtHistoryProcessTypeID.setEditable(false);
        txtHistoryProcessComment.setEditable(false);
    }

    private void refresh() {
        _employee = sEmployee.getEmployeeInfo(_employee.getId());
    }

    private void entryData() {
        _task = sTask.getTaskInfo(_task);

        txtTaskNo.setText(_task.getTaskNo() + "");
        txtCustomerID.setText(_task.getCostumerID());
        txtCustomerName.setText(sCostumer.getCostumerName(_task.getCostumerID()));
        txtCustomerContact.setText(_task.getCostumerContact());
        txtCustomerReport.setText(_task.getCostumerReport());
    }

    private void entryDataHistoryProcess(String taskNo, String number) {
        if (_taskProcess.chkSetTaskNo(taskNo) && _taskProcess.chkSetNumber(number)) {
            _taskProcess = sTask.getTaskProcessInfo(_taskProcess);

            txtHistoryFromEmployeeID.setText(_taskProcess.getFromEmployeeID() + "");
            txtHistoryFromEmployeeName.setText(sEmployee.getEmployeeName(_taskProcess.getFromEmployeeID()));
            txtHistoryProcessTypeID.setText(_taskProcess.getProcessTypeID() + "");
            txtHistoryProcessTypeName.setText(sProcess_Type.getProcessName(_taskProcess.getProcessTypeID()));
            txtHistoryProcessTitle.setText(_taskProcess.getTitle());
            txtHistoryProcessComment.setText(_taskProcess.getComment());
            txtHistoryToEmployeeID.setText(_taskProcess.getToEmployeeID() + "");
            txtHistoryToEmployeeName.setText(sEmployee.getEmployeeName(_taskProcess.getToEmployeeID()));
        } else {
            JOptionPane.showMessageDialog(null, "Hatalı İş No veya İş Numarası nedeniyle işlem gerçekleşemiyor.", "Hatalı İş Bilgileri", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void historyProcessList() {
        sTask.getTableProcessList(tblList, _taskProcess);
    }

    private boolean leave(String taskNo, String costumerID, String costumerContact, String costumerReport) {
        boolean isLeave = false;

        if (updateComp(taskNo, costumerID, costumerContact, costumerReport)) {
            if (sTask.getProcessNumber(_task.getTaskNo()) > 0) {
                isLeave = true;
            } else {
                JOptionPane.showMessageDialog(null, "Yapılanlar İşlemi hiç değer girilmeden çıkılamaz.\nYapılanlara giriş yapın.", "Başarısız Çıkış", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Müşteri bilgileri girilmediği için çıkış yapılamıyor.\nMüşteri bilgilerini giriniz.", "Başarısız Çıkış", JOptionPane.ERROR_MESSAGE);
        }

        return isLeave;
    }

    private boolean setTaskID() {
        if (_taskProcess.chkSetTaskNo(txtTaskNo.getText().trim())) {
            historyProcessList();
            return true;
        }

        JOptionPane.showMessageDialog(null, "İş No hatalı olduğu için gerçekleşemiyor.", "Hatalı İş No", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    private void openTask() {
        _task.setTaskNo(sTask.getCreatTaskID());
        sTask.addTask(_task);
        txtTaskNo.setText(_task.getTaskNo() + "");
    }

    private void addProcess(String taskNo, String title, String processID, String comment, String employeeID) {
        if (_taskProcess.chkSetTitle(title.trim().toUpperCase()) && _taskProcess.chkSetProcessTypeID(processID.trim()) && _taskProcess.chkSetComment(comment.trim()) && _taskProcess.chkSetToEmployeeID(employeeID.trim()) && _taskProcess.chkSetTaskNo(taskNo.trim())) {
            _taskProcess.setFromEmployeeID(_employee.getId());
            _taskProcess.setNumber(sTask.getProcessNumber(_taskProcess.getTaskNo()) + 1);

            if (sTask.addProcess(_taskProcess)) {
                JOptionPane.showMessageDialog(null, "Yapılanlar başarıyla kaydedildi.", "Başarılı Kaydetme", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Yapılanlar kaydedilirken hata ile karşılaşıldı.\nKaydetme gerçekleşmedi!", "Başarısız Kaydetme", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hatalı giriş yaptınız!\n* Zorunlu alanları doldurunuz.\n* Girdiğiniz formata dikkan edin.", "Hatalı Veri Girişi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean updateComp(String taskNo, String costumerID, String costumerContact, String costumerReport) {
        boolean isComplate = false;

        if (_task.chkSetTaskNo(taskNo.trim()) && _task.chkSetCostumerID(costumerID.trim()) && _task.chkSetCostumerContact(costumerContact.trim().toUpperCase()) && _task.chkSetCostumerReport(costumerReport.trim())) {
            if (sTask.updateTask(_task)) {
                JOptionPane.showMessageDialog(null, "Müşteri bilgileri başarıyla kaydedildi.", "Başarılı Kaydetme", JOptionPane.INFORMATION_MESSAGE);
                isComplate = true;
            } else {
                JOptionPane.showMessageDialog(null, "Müşteri bilgileri kaydedilirken hata ile karşılaşıldı.\nKaydetme gerçekleşmedi!", "Başarısız Kaydetme", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hatalı giriş yaptınız!\n* Zorunlu alanları doldurunuz.\n* Girdiğiniz formata dikkan edin.", "Hatalı Veri Girişi", JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    private void updateHistoryProcess(int number, String taskNo, String title, String processID, String comment, String toEmployeeID, String fromEmployeeID) {
        if (_taskProcess.chkSetTitle(title.trim().toUpperCase()) && _taskProcess.chkSetProcessTypeID(processID.trim()) && _taskProcess.chkSetComment(comment.trim()) && _taskProcess.chkSetToEmployeeID(toEmployeeID.trim()) && _taskProcess.chkSetFromEmployeeID(fromEmployeeID.trim()) && _taskProcess.chkSetTaskNo(taskNo.trim()) && number > 0) {
            if (sTask.updateProcess(_taskProcess)) {
                JOptionPane.showMessageDialog(null, "Geçmiş Yapılanlar başarıyla güncellendi.", "Başarılı Güncelleme", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Geçmiş Yapılanlar güncellenirken hata ile karşılaşıldı.\nGüncelleme gerçekleşmedi!", "Başarısız Kaydetme", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hatalı giriş yaptınız!\n* Zorunlu alanları doldurunuz.\n* Girdiğiniz formata dikkan edin.", "Hatalı Veri Girişi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void delete(String taskNo) {
        if (_task.chkSetTaskNo(taskNo.trim()) && _taskProcess.chkSetTaskNo(taskNo.trim())) {
            if (sTask.deleteProcess(_taskProcess) && sTask.deleteTask(_task)) {
                JOptionPane.showMessageDialog(null, "İş başarıyla silindi.", "Başarılı Silme", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "İş silinirken hata ile karşılaşıldı.\nSilme gerçekleşmedi!", "Başarısız Silme", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hatalı giriş yaptınız!\n* Zorunlu alanları doldurunuz.\n* Girdiğiniz formata dikkan edin.", "Hatalı Veri Girişi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTaskNo = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCustomerID = new javax.swing.JTextField();
        txtCustomerName = new javax.swing.JTextField();
        txtCustomerContact = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCustomerReport = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtProcessTitle = new javax.swing.JTextField();
        txtToEmployeeID = new javax.swing.JTextField();
        txtToEmployeeName = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtProcessComment = new javax.swing.JTextArea();
        txtProcessTypeID = new javax.swing.JTextField();
        txtProcessTypeName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        txtHistoryFromEmployeeID = new javax.swing.JTextField();
        txtHistoryFromEmployeeName = new javax.swing.JTextField();
        txtHistoryToEmployeeName = new javax.swing.JTextField();
        txtHistoryToEmployeeID = new javax.swing.JTextField();
        txtHistoryProcessTitle = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtHistoryProcessComment = new javax.swing.JTextArea();
        txtHistoryProcessTypeID = new javax.swing.JTextField();
        txtHistoryProcessTypeName = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        btnHistoryUpdate = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        btnHistoryRefresh = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        btnOpen = new javax.swing.JButton();
        btnRead = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        btnLeave = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("İş İşlemleri");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formİnternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("İş Numarası : ");

        txtTaskNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTaskNo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTaskNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 51));
        jLabel2.setText("Müşteri Numarası : ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Müşteri Adı : ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 0, 51));
        jLabel4.setText("Müşteri Şikayeti : ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 51));
        jLabel5.setText("Müşterinin Ulaştığı Yer : ");

        txtCustomerID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCustomerID.setToolTipText("Müşteri adını getirmek için 'ENTER' basın");
        txtCustomerID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerIDActionPerformed(evt);
            }
        });

        txtCustomerName.setEditable(false);
        txtCustomerName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtCustomerContact.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCustomerContact.setToolTipText("En fazla 50 karakter girin");

        txtCustomerReport.setColumns(20);
        txtCustomerReport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCustomerReport.setLineWrap(true);
        txtCustomerReport.setRows(5);
        txtCustomerReport.setToolTipText("En fazla 1000 karakter girin");
        txtCustomerReport.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtCustomerReport);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnUpdate.setToolTipText("Müşteri bilgilerini kaydet");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCustomerName)
                    .addComponent(txtCustomerContact)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCustomerID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCustomerContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bilgi", jPanel4);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 0, 51));
        jLabel6.setText("Başlık : ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 0, 51));
        jLabel7.setText("Açıklama : ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Aktarılan Kişi Adı : ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 0, 51));
        jLabel9.setText("Aktarılan Kişi ID : ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 0, 51));
        jLabel10.setText("İşlem ID : ");

        txtProcessTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtProcessTitle.setToolTipText("En fazla 50 karakter girin");

        txtToEmployeeID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtToEmployeeID.setToolTipText("Akatarılan kişinin adını getirmek için 'ENTER' basın");
        txtToEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtToEmployeeIDActionPerformed(evt);
            }
        });

        txtToEmployeeName.setEditable(false);
        txtToEmployeeName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtProcessComment.setColumns(20);
        txtProcessComment.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtProcessComment.setLineWrap(true);
        txtProcessComment.setRows(5);
        txtProcessComment.setToolTipText("En fazla 1000 karakter girin");
        txtProcessComment.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtProcessComment);

        txtProcessTypeID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtProcessTypeID.setToolTipText("İşlem adını getirmek için 'ENTER' basın");
        txtProcessTypeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProcessTypeIDActionPerformed(evt);
            }
        });

        txtProcessTypeName.setEditable(false);
        txtProcessTypeName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("İşlem Adı : ");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAdd.setToolTipText("Yapılanı ekle");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtProcessTitle)
                    .addComponent(txtToEmployeeID)
                    .addComponent(txtToEmployeeName)
                    .addComponent(jScrollPane4)
                    .addComponent(txtProcessTypeID)
                    .addComponent(txtProcessTypeName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtProcessTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProcessTypeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProcessTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtToEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtToEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Yapılanları Ekle", jPanel3);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 0, 51));
        jLabel11.setText("Başlık : ");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 0, 51));
        jLabel14.setText("Aktaran Kişi ID : ");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Aktaran Kişi Adı : ");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 0, 51));
        jLabel16.setText("Aktarılan Kişi ID : ");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Aktarılan Kişi Adı : ");

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tblList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblList);

        txtHistoryFromEmployeeID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHistoryFromEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHistoryFromEmployeeIDActionPerformed(evt);
            }
        });

        txtHistoryFromEmployeeName.setEditable(false);
        txtHistoryFromEmployeeName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtHistoryToEmployeeName.setEditable(false);
        txtHistoryToEmployeeName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtHistoryToEmployeeID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHistoryToEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHistoryToEmployeeIDActionPerformed(evt);
            }
        });

        txtHistoryProcessTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHistoryProcessTitle.setToolTipText("En fazla 50 karakter girin");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 0, 51));
        jLabel18.setText("İşlem ID : ");

        txtHistoryProcessComment.setColumns(20);
        txtHistoryProcessComment.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHistoryProcessComment.setLineWrap(true);
        txtHistoryProcessComment.setRows(5);
        txtHistoryProcessComment.setToolTipText("En fazla 1000 karakter girin");
        txtHistoryProcessComment.setWrapStyleWord(true);
        jScrollPane5.setViewportView(txtHistoryProcessComment);

        txtHistoryProcessTypeID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHistoryProcessTypeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHistoryProcessTypeIDActionPerformed(evt);
            }
        });

        txtHistoryProcessTypeName.setEditable(false);
        txtHistoryProcessTypeName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("İşlem Adı : ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 0, 51));
        jLabel13.setText("Açıklama : ");

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnHistoryUpdate.setToolTipText("Geçmiş düzenlemeyi kaydet");
        btnHistoryUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHistoryUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHistoryUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHistoryUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnHistoryRefresh.setToolTipText("İşlem geçmişini yenile");
        btnHistoryRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHistoryRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHistoryRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHistoryRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHistoryFromEmployeeID, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                    .addComponent(txtHistoryFromEmployeeName)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHistoryProcessTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(txtHistoryProcessTypeID))))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtHistoryToEmployeeID)
                                    .addComponent(txtHistoryToEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHistoryProcessTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtHistoryFromEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtHistoryToEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtHistoryFromEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtHistoryToEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHistoryProcessTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtHistoryProcessTypeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtHistoryProcessTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(86, 86, 86))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Geçmiş", jPanel7);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnOpen.setToolTipText("İş emri aç");
        btnOpen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnRead.setToolTipText("İş emrinin bilgilerini getir");
        btnRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRead, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnDelete.setToolTipText("İş emrini sil");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnLeave.setToolTipText("İş emrinden çık");
        btnLeave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLeave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLeave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        setOpenTask();
        openTask();
        isOpenTask = true;
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadActionPerformed
        // TODO add your handling code here:
        if (_task.chkSetTaskNo(txtTaskNo.getText())) {
            if (sTask.isThereTask(_task.getTaskNo())) {
                _taskProcess.setTaskNo(_task.getTaskNo());

                setOpenTask();
                historyProcessList();
                entryData();
                isOpenTask = true;
            } else {
                JOptionPane.showMessageDialog(null, "Böyle bir iş bulunmamaktadır.", "Bulunamayan İş", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hatalı giriş yaptınız!\n* İşlem No yanlış girdiniz.", "Hatalı Veri Girişi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnReadActionPerformed

    private void formİnternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formİnternalFrameClosing
        // TODO add your handling code here:
        if (isOpenTask) {
            if (leave(txtTaskNo.getText(), txtCustomerID.getText(), txtCustomerContact.getText(), txtCustomerReport.getText())) {
                setCloseTask();
                setTaskRemove();
                setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
            } else {
                setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
            }
        } else {
            setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_formİnternalFrameClosing

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "İş No silmek istediğinize emin misiniz?", "İş No Silme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
            delete(txtTaskNo.getText());
            setCloseTask();
            isOpenTask = false;
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeaveActionPerformed
        // TODO add your handling code here:
        if (isOpenTask) {
            if (leave(txtTaskNo.getText(), txtCustomerID.getText(), txtCustomerContact.getText(), txtCustomerReport.getText())) {
                setCloseTask();
                setTaskRemove();
                isOpenTask = false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "İş emri kapalı olduğu için çıkılmıyor.", "Kapalı İş", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnLeaveActionPerformed

    private void btnHistoryRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryRefreshActionPerformed
        // TODO add your handling code here:
        if (setTaskID()) {
            historyProcessList();
        }
    }//GEN-LAST:event_btnHistoryRefreshActionPerformed

    private void btnHistoryUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryUpdateActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Geçmişi işlemi güncellemek üzeresiniz!\nGüncellemeyi onaylıyor musunuz?", "Geçmiş İşlem Güncelleme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
            if (setTaskID()) {
                updateHistoryProcess(_taskProcess.getNumber(), txtTaskNo.getText(), txtHistoryProcessTitle.getText(), txtHistoryProcessTypeID.getText(), txtHistoryProcessComment.getText(), txtHistoryToEmployeeID.getText(), txtHistoryFromEmployeeID.getText());
                historyProcessList();
            }
        }
    }//GEN-LAST:event_btnHistoryUpdateActionPerformed

    private void txtHistoryProcessTypeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHistoryProcessTypeIDActionPerformed
        // TODO add your handling code here:
        if (_taskProcess.chkSetProcessTypeID(txtHistoryProcessTypeID.getText())) {
            txtHistoryProcessTypeName.setText(sProcess_Type.getProcessName(_taskProcess.getProcessTypeID()));
        }
    }//GEN-LAST:event_txtHistoryProcessTypeIDActionPerformed

    private void txtHistoryToEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHistoryToEmployeeIDActionPerformed
        // TODO add your handling code here:
        if (_taskProcess.chkSetToEmployeeID(txtHistoryToEmployeeID.getText())) {
            txtHistoryToEmployeeName.setText(sEmployee.getEmployeeName(_taskProcess.getToEmployeeID()));
        }
    }//GEN-LAST:event_txtHistoryToEmployeeIDActionPerformed

    private void txtHistoryFromEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHistoryFromEmployeeIDActionPerformed
        // TODO add your handling code here:
        if (_taskProcess.chkSetFromEmployeeID(txtHistoryFromEmployeeID.getText())) {
            txtHistoryFromEmployeeName.setText(sEmployee.getEmployeeName(_taskProcess.getFromEmployeeID()));
        }
    }//GEN-LAST:event_txtHistoryFromEmployeeIDActionPerformed

    private void tblListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListMouseClicked
        // TODO add your handling code here:
        if (txtTaskNo.getText().trim().equals(tblList.getValueAt(tblList.getSelectedRow(), 0).toString())) {
            entryDataHistoryProcess(txtTaskNo.getText().trim(), tblList.getValueAt(tblList.getSelectedRow(), 1).toString());
        } else {
            JOptionPane.showMessageDialog(null, "Çalıştığınız İş No ile seçtiğiniz İş No aynı olmadığı için işleminiz gerçekleşmiyor.", "Uyuşmayan İş Nolar", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_tblListMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        addProcess(txtTaskNo.getText(), txtProcessTitle.getText(), txtProcessTypeID.getText(), txtProcessComment.getText(), txtToEmployeeID.getText());
        historyProcessList();
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtProcessTypeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProcessTypeIDActionPerformed
        // TODO add your handling code here:
        if (_taskProcess.chkSetProcessTypeID(txtProcessTypeID.getText().trim())) {
            txtProcessTypeName.setText(sProcess_Type.getProcessName(_taskProcess.getProcessTypeID()));
        } else {
            JOptionPane.showMessageDialog(null, "Hatalı giriş yaptınız!\n* İşlem ID yanlış girdiniz.", "Hatalı Veri Girişi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtProcessTypeIDActionPerformed

    private void txtToEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtToEmployeeIDActionPerformed
        // TODO add your handling code here:
        if (_taskProcess.chkSetToEmployeeID(txtToEmployeeID.getText().trim())) {
            txtToEmployeeName.setText(sEmployee.getEmployeeName(_taskProcess.getToEmployeeID()));
        } else {
            JOptionPane.showMessageDialog(null, "Hatalı giriş yaptınız!\n* Çalışan ID yanlış girdiniz.", "Hatalı Veri Girişi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtToEmployeeIDActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        updateComp(txtTaskNo.getText(), txtCustomerID.getText(), txtCustomerContact.getText(), txtCustomerReport.getText());
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtCustomerIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerIDActionPerformed
        // TODO add your handling code here:
        if (_task.chkSetCostumerID(txtCustomerID.getText().trim())) {
            txtCustomerName.setText(sCostumer.getCostumerName(_task.getCostumerID()));
        } else {
            JOptionPane.showMessageDialog(null, "Hatalı giriş yaptınız!\n* Müşteri ID yanlış girdiniz.", "Hatalı Veri Girişi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtCustomerIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnHistoryRefresh;
    private javax.swing.JButton btnHistoryUpdate;
    private javax.swing.JButton btnLeave;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnRead;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtCustomerContact;
    private javax.swing.JTextField txtCustomerID;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextArea txtCustomerReport;
    private javax.swing.JTextField txtHistoryFromEmployeeID;
    private javax.swing.JTextField txtHistoryFromEmployeeName;
    private javax.swing.JTextArea txtHistoryProcessComment;
    private javax.swing.JTextField txtHistoryProcessTitle;
    private javax.swing.JTextField txtHistoryProcessTypeID;
    private javax.swing.JTextField txtHistoryProcessTypeName;
    private javax.swing.JTextField txtHistoryToEmployeeID;
    private javax.swing.JTextField txtHistoryToEmployeeName;
    private javax.swing.JTextArea txtProcessComment;
    private javax.swing.JTextField txtProcessTitle;
    private javax.swing.JTextField txtProcessTypeID;
    private javax.swing.JTextField txtProcessTypeName;
    private javax.swing.JTextField txtTaskNo;
    private javax.swing.JTextField txtToEmployeeID;
    private javax.swing.JTextField txtToEmployeeName;
    // End of variables declaration//GEN-END:variables
}
