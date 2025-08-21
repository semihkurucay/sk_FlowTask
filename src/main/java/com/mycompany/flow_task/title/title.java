/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.title;

/**
 *
 * @author semih
 */
public class title {
    private int id = -1;
    private String title = "";

    //CONSTRUCTOR
    public title() {
    }
    
    public title(int id, String title) {
        this.id = id;
        this.title = title;
    }

    //GET
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    //SET
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean chkSetTitle(String title) {
        if(title.length() < 50 && !title.isEmpty()){
            this.title = title;
            return true;
        }else{
            return false;
        }
    }
}
