/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.department;

/**
 *
 * @author semih
 */
public class department {
    private int id = -1;
    private String department = "";

    //CONSTRUCTOR
    public department() {
    }
    
    public department(int id, String department) {
        this.id = id;
        this.department = department;
    }

    //GET
    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    //SET
    public void setId(int id) {
        this.id = id;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    //CHK
    public boolean chkSetId(String id) {
        try{
            this.id = Integer.valueOf(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean chkSetDepartment(String department) {
        if(department.length() < 50 && !department.isEmpty()){
            this.department = department;
            return true;
        }else{
            return false;
        }
    }
}
