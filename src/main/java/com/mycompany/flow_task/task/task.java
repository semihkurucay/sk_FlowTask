/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author semih
 */
public class task {
    private int taskNo = -1;
    private String costumerID = "";
    private String costumerContact = "";
    private String costumerReport = "";
    private LocalDate date;
    
    //CONSTRUCTOR
    public task() {
    }
    
    public task(int taskNo, String costumerID, String costumerContact, String costumerReport, LocalDate date) {
        this.taskNo = taskNo;
        this.costumerID = costumerID;
        this.costumerContact = costumerContact;
        this.costumerReport = costumerReport;
        this.date = date;
    }

    //GET
    public int getTaskNo() {
        return taskNo;
    }

    public String getCostumerID() {
        return costumerID;
    }

    public String getCostumerContact() {
        return costumerContact;
    }

    public String getCostumerReport() {
        return costumerReport;
    }
    
    public LocalDate getDate() {
        return date;
    }

    //SET
    public void setTaskNo(int taskNo) {
        this.taskNo = taskNo;
    }

    public void setCostumerID(String costumerID) {
        this.costumerID = costumerID;
    }

    public void setCostumerContact(String costumerContact) {
        this.costumerContact = costumerContact;
    }

    public void setCostumerReport(String costumerReport) {
        this.costumerReport = costumerReport;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    //CHK
    public boolean chkSetTaskNo(String taskNo) {
        if(taskNo.matches("^[0-9]{1,}$")){
            this.taskNo = Integer.valueOf(taskNo);
            return true;
        }
        return false;
    }

    public boolean chkSetCostumerID(String costumerID) {
        if(costumerID.matches("^[0-9]{1,}$")){
            this.costumerID = costumerID;
            return true;
        }
        return false;
    }

    public boolean chkSetCostumerContact(String costumerContact) {
        if(costumerContact.matches("^[0-9a-zA-ZüğşçöıÜĞİŞÇÖ +-./,;:?!'%()&<>]{1,50}$")){
            this.costumerContact = costumerContact;
            return true;
        }
        return false;
    }

    public boolean chkSetCostumerReport(String costumerReport) {
        if(costumerReport.matches("^[0-9a-zA-ZüğşçöıÜĞİŞÇÖ +-./,;:?!'%()&<>\\n]{1,1000}$")){
            this.costumerReport = costumerReport;
            return true;
        }
        return false;
    }
    
    public boolean chkSetDate(String date) {
        try{
            this.date = LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
