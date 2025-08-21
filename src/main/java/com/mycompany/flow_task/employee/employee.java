/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.employee;

/**
 *
 * @author semih
 */
public class employee {
    private int id = -1;
    private String name = "";
    private String phone1 = "";
    private String phone2 = "";
    private String mail = "";
    private String fax = "";
    private String username = "";
    private String password = "";
    
    private boolean isAdmin = false;
    private int title = -1;
    private int department = -1;
    
    private String country = "";
    private String city = "";
    private String district = "";
    private String nightbrood = "";
    private String address = "";

    public employee() {
    }
    
    public employee(int id, String name, String phone1, String phone2, String mail, String fax, String username, String password, boolean isAdmin, int title, int department, String country, String city, String district, String nightbrood, String address) {
        this.id = id;
        this.name = name;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.mail = mail;
        this.fax = fax;
        this.username = username;
        this.password = password;
        
        this.isAdmin = isAdmin;
        this.title = title;
        this.department = department;
        
        this.country = country;
        this.city = city;
        this.district = district;
        this.nightbrood = nightbrood;
        this.address = address;
    }

    //GET
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getMail() {
        return mail;
    }

    public String getFax() {
        return fax;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public int getTitleID() {
        return title;
    }

    public int getDepartmentID() {
        return department;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getNightbrood() {
        return nightbrood;
    }

    public String getAddress() {
        return address;
    }

    //SET
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setTitleID(int title) {
        this.title = title;
    }

    public void setDepartmentID(int department) {
        this.department = department;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setNightbrood(String nightbrood) {
        this.nightbrood = nightbrood;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public boolean chkSetName(String name) {
        if(name.matches("^[a-zA-ZçğıöşüÇĞİÖŞÜ ]{1,99}$")){
            this.name = name;
            return true;
        }
        
        return false;
    }

    public boolean chkSetPhone1(String phone1) {
        if(phone1.matches("^[0-9]{1,15}$")){
            this.phone1 = phone1;
            return true;
        }
        
        return false;
    }

    public boolean chkSetPhone2(String phone2) {
        if(phone2.matches("^[0-9]{0,15}$")){
            this.phone2 = phone2;
            return true;
        }
        
        return false;
    }

    public boolean chkSetMail(String mail) {
        if(mail.matches("^$|^(?=.{1,150}$)[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            this.mail = mail;
            return true;
        }
        
        return false;
    }

    public boolean chkSetFax(String fax) {
        if(fax.matches("^[0-9]{0,15}$")){
            this.fax = fax;
            return true;
        }
        
        return false;
    }

    public boolean chkSetUsername(String username) {
        if(username.length() < 50 && !username.isEmpty()){
            this.username = username;
            return true;
        }
        
        return false;
    }

    public boolean chkSetPassword(String password) {
        if(password.length() < 50 && !password.isEmpty()){
            this.password = password;
            return true;
        }
        
        return false;
    }

    public boolean chkSetTitleID(String title) {
        try{
            this.title = Integer.valueOf(title);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean chkSetDepartmentID(String department) {
        try{
            this.department = Integer.valueOf(department);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean chkSetCountry(String country) {
        if(country.matches("^[a-zA-ZçğıöşüÇĞİÖŞÜ ]{1,50}$")){
            this.country = country;
            return true;
        }
        
        return false;
    }

    public boolean chkSetCity(String city) {
        if(city.matches("^[a-zA-ZçğıöşüÇĞİÖŞÜ ]{1,50}$")){
            this.city = city;
            return true;
        }
        
        return false;
    }

    public boolean chkSetDistrict(String district) {
        if(district.matches("^[a-zA-ZçğıöşüÇĞİÖŞÜ ]{1,50}$")){
            this.district = district;
            return true;
        }
        
        return false;
    }

    public boolean chkSetNightbrood(String nightbrood) {
        if(nightbrood.matches("^[a-zA-ZçğıöşüÇĞİÖŞÜ ]{1,50}$")){
            this.nightbrood = nightbrood;
            return true;
        }
        
        return false;
    }

    public boolean chkSetAddress(String address) {
        if(address.matches("^[0-9a-zA-ZüğşçöıÜĞİŞÇÖ +-./,;:?!'%()&<>\\n]{0,1000}$")){
            this.address = address;
            return true;
        }
        
        return false;
    }
}
