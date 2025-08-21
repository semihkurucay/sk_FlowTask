/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.notepad;

/**
 *
 * @author semih
 */
public class note {

    private int employeeID;
    private int id;
    private String title;
    private String message;

    public note() {
    }
    
    public note(int employeeID, int id, String title, String message) {
        this.employeeID = employeeID;
        this.id = id;
        this.title = title;
        this.message = message;
    }

    //GET
    public int getEmployeeID() {
        return employeeID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    //SET
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //CHK
    public boolean chkSetEmployeeID(String employeeID) {
        if(employeeID.matches("^[0-9]{1,}$")){
            this.employeeID = Integer.valueOf(employeeID);
            return true;
        }
        
        return false;
    }

    public boolean chkSetId(String id) {
        if(id.matches("^[0-9]{1,}$")){
            this.id = Integer.valueOf(id);
            return true;
        }
        
        return false;
    }

    public boolean chkSetTitle(String title) {
        if(title.matches("^[0-9a-zA-ZüğşçöıÜĞİŞÇÖ +-./,;:?!'%()&<>]{1,50}$")){
            this.title = title;
            return true;
        }
        
        return false;
    }

    public boolean chkSetMessage(String message) {
        if(message.matches("^[0-9a-zA-ZüğşçöıÜĞİŞÇÖ +-./,;:?!'%()&<>\\n]{0,1000}$")){
            this.message = message;
            return true;
        }
        
        return false;
    }
}
