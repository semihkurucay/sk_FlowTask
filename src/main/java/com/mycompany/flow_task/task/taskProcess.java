/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.task;

/**
 *
 * @author semih
 */
public class taskProcess {

    private int taskNo = -1;
    private String title = "";
    private int processTypeID = -1;
    private String comment = "";
    private int toEmployeeID = -1;
    private int fromEmployeeID = -1;
    private int number = -1;

    //CONSTRUCTOR
    public taskProcess() {
    }

    public taskProcess(int taskNo, String title, int processTypeID, String comment, int toEmployeeID, int fromEmployeeID, int number) {
        this.taskNo = taskNo;
        this.title = title;
        this.processTypeID = processTypeID;
        this.comment = comment;
        this.toEmployeeID = toEmployeeID;
        this.fromEmployeeID = fromEmployeeID;
        this.number = number;
    }

    //GET
    public int getTaskNo() {
        return taskNo;
    }

    public String getTitle() {
        return title;
    }

    public int getProcessTypeID() {
        return processTypeID;
    }

    public String getComment() {
        return comment;
    }

    public int getToEmployeeID() {
        return toEmployeeID;
    }

    public int getFromEmployeeID() {
        return fromEmployeeID;
    }

    public int getNumber() {
        return number;
    }

    //SET
    public void setTaskNo(int taskNo) {
        this.taskNo = taskNo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProcessTypeID(int processTypeID) {
        this.processTypeID = processTypeID;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setToEmployeeID(int toEmployeeID) {
        this.toEmployeeID = toEmployeeID;
    }

    public void setFromEmployeeID(int fromEmployeeID) {
        this.fromEmployeeID = fromEmployeeID;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    //CHK
    public boolean chkSetTaskNo(String taskNo) {
        if (taskNo.matches("^[0-9]{1,}$")) {
            this.taskNo = Integer.valueOf(taskNo);
            return true;
        }
        return false;
    }

    public boolean chkSetTitle(String title) {
        if (title.matches("^[0-9a-zA-ZüğşçöıÜĞİŞÇÖ +-./,;:?!'%()&<>]{1,50}$")) {
            this.title = title;
            return true;
        }
        return false;
    }

    public boolean chkSetProcessTypeID(String processTypeID) {
        if (processTypeID.matches("^[0-9]{1,}$")) {
            this.processTypeID = Integer.valueOf(processTypeID);
            return true;
        }
        return false;
    }

    public boolean chkSetComment(String comment) {
        if (comment.matches("^[0-9a-zA-ZüğşçöıÜĞİŞÇÖ +-./,;:?!'%()&<>\\n]{1,1000}$")) {
            this.comment = comment;
            return true;
        }
        return false;
    }

    public boolean chkSetToEmployeeID(String toEmployeeID) {
        if (toEmployeeID.matches("^[0-9]{1,}$")) {
            this.toEmployeeID = Integer.valueOf(toEmployeeID);
            return true;
        }
        return false;
    }

    public boolean chkSetFromEmployeeID(String fromEmployeeID) {
        if (fromEmployeeID.matches("^[0-9]{1,}$")) {
            this.fromEmployeeID = Integer.valueOf(fromEmployeeID);
            return true;
        }
        return false;
    }

    public boolean chkSetNumber(String number) {
        if (number.matches("^[0-9]{1,}$")) {
            this.number = Integer.valueOf(number);
            return true;
        }
        return false;
    }

    //SEARCH
    public boolean search(String id) {
        if (id.matches("^[0-9]{0,}$")) {
            return true;
        }
        return false;
    }
}
