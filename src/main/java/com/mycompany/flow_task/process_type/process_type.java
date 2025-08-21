/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.process_type;

/**
 *
 * @author semih
 */
public class process_type {
    private int id = -1;
    private String process_type = "";

    //CONSTRUCTOR
    public process_type() {
    }
    
    public process_type(int id, String process_type) {
        this.id = id;
        this.process_type = process_type;
    }

    //GET
    public int getId() {
        return id;
    }

    public String getProcess_type() {
        return process_type;
    }

    //SET
    public void setId(int id) {
        this.id = id;
    }

    public void setProcess_type(String process_type) {
        this.process_type = process_type;
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

    public boolean chkSetProcess_type(String process_type) {
        if(process_type.length() < 50 && !process_type.isEmpty()){
            this.process_type = process_type;
            return true;
        }
        
        return false;
    }
}
