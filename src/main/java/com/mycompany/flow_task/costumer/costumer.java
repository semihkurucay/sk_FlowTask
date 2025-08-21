/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flow_task.costumer;

/**
 *
 * @author semih
 */
public class costumer {
    private String id = "";
    private String taxOffice = "";
    private String compNo = "";
    private String compName = "";
    
    private String compPhone = "";
    private String compMail = "";
    private String compFax = "";
    
    private String adminName = "";
    private String adminTitle = "";
    private String adminDepartment = "";
    
    private String adminPhone = "";
    private String adminMail = "";
    private String adminFax = "";
    
    private String country = "";
    private String city = "";
    private String district = "";
    private String nightbrood = "";
    private String address = "";

    //CONSTRUCTOR
    public costumer() {
    }
    
    public costumer(String id, String taxOffice, String compNo, String compName, String compPhone, String compMail, String compFax, String adminName, String adminTitle, String adminDepartment, String adminPhone, String adminMail, String adminFax, String country, String city, String district, String nightbrood, String address) {
        this.id = id;
        this.taxOffice = taxOffice;
        this.compNo = compNo;
        this.compName = compName;
        
        this.compPhone = compPhone;
        this.compMail = compMail;
        this.compFax = compFax;
        
        this.adminName = adminName;
        this.adminTitle = adminTitle;
        this.adminDepartment = adminDepartment;
        
        this.adminPhone = adminPhone;
        this.adminMail = adminMail;
        this.adminFax = adminFax;
        
        this.country = country;
        this.city = city;
        this.district = district;
        this.nightbrood = nightbrood;
        this.address = address;
    }

    //GET
    public String getId() {
        return id;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public String getCompNo() {
        return compNo;
    }

    public String getCompName() {
        return compName;
    }

    public String getCompPhone() {
        return compPhone;
    }

    public String getCompMail() {
        return compMail;
    }

    public String getCompFax() {
        return compFax;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminTitle() {
        return adminTitle;
    }

    public String getAdminDepartment() {
        return adminDepartment;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public String getAdminMail() {
        return adminMail;
    }

    public String getAdminFax() {
        return adminFax;
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
    public void setId(String id) {
        this.id = id;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public void setCompNo(String compNo) {
        this.compNo = compNo;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public void setCompPhone(String compPhone) {
        this.compPhone = compPhone;
    }

    public void setCompMail(String compMail) {
        this.compMail = compMail;
    }

    public void setCompFax(String compFax) {
        this.compFax = compFax;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAdminTitle(String adminTitle) {
        this.adminTitle = adminTitle;
    }

    public void setAdminDepartment(String adminDepartment) {
        this.adminDepartment = adminDepartment;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public void setAdminMail(String adminMail) {
        this.adminMail = adminMail;
    }

    public void setAdminFax(String adminFax) {
        this.adminFax = adminFax;
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
        if(id.matches("^[0-9]{10,11}$")){
            this.id = id;
            return true;
        }
        return false;
    }

    public boolean chkSetTaxOffice(String taxOffice) {
        if(taxOffice.matches("^[0-9a-zA-ZüğşçöıÜĞİŞÇÖ +-./,;:?!'%()&<>]{0,50}$")){
            this.taxOffice = taxOffice;
            return true;
        }
        return false;
    }

    public boolean chkSetCompNo(String compNo) {
        if(compNo.matches("^[0-9/-]{0,20}$")){
            this.compNo = compNo;
            return true;
        }
        return false;
    }

    public boolean chkSetCompName(String compName) {
        if(compName.matches("^[0-9a-zA-ZüğşçöıÜĞİŞÇÖ +-./,;:?!'%()&<>]{2,250}$")){
            this.compName = compName;
            return true;
        }
        return false;
    }

    public boolean chkSetCompPhone(String compPhone) {
        if(compPhone.matches("^[0-9]{0,15}$")){
            this.compPhone = compPhone;
            return true;
        }
        return false;
    }

    public boolean chkSetCompMail(String compMail) {
        if(compMail.matches("^$|^(?=.{1,150}$)[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            this.compMail = compMail;
            return true;
        }
        return false;
    }

    public boolean chkSetCompFax(String compFax) {
        if(compFax.matches("^[0-9]{0,15}$")){
            this.compFax = compFax;
            return true;
        }
        return false;
    }

    public boolean chkSetAdminName(String adminName) {
        if(adminName.matches("^[a-zA-ZüğışçöÜĞİŞÇÖ ]{0,150}$")){
            this.adminName = adminName;
            return true;
        }
        return false;
    }

    public boolean chkSetAdminTitle(String adminTitle) {
        if(adminTitle.matches("^[0-9a-zA-ZüğşçöıÜĞİŞÇÖ +-./,;:?!'%()&<>]{0,50}$")){
            this.adminTitle = adminTitle;
            return true;
        }
        return false;
    }

    public boolean chkSetAdminDepartment(String adminDepartment) {
        if(adminDepartment.matches("^[0-9a-zA-ZüğşçöıÜĞİŞÇÖ +-./,;:?!'%()&<>]{0,50}$")){
            this.adminDepartment = adminDepartment;
            return true;
        }
        return false;
    }

    public boolean chkSetAdminPhone(String adminPhone) {
        if(adminPhone.matches("^[0-9]{0,15}$")){
            this.adminPhone = adminPhone;
            return true;
        }
        return false;
    }

    public boolean chkSetAdminMail(String adminMail) {
        if(adminMail.matches("^$|^(?=.{1,150}$)[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            this.adminMail = adminMail;
            return true;
        }
        return false;
    }

    public boolean chkSetAdminFax(String adminFax) {
        if(adminFax.matches("^[0-9]{0,15}$")){
            this.adminFax = adminFax;
            return true;
        }
        return false;
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
        if(nightbrood.trim().toUpperCase().matches("^[a-zA-ZçğıöşüÇĞİÖŞÜ ]{1,50}$")){
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
