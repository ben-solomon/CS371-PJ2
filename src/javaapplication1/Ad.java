/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Ben
 */
public class Ad {
    //categories
    String ELECTRONICS = "ELC";
    String CARTRUCKS ="CAT";
    String CHILDCARE = "CCA";
    String HOUSING = "HOU";
    int adID;
    private String adTitle, adDetails, adDate, adPrice, adUser,adMod,adCategory,adStatus;
    Ad(){
        this.adID = -1;this.adTitle="";this.adDetails="null";this.adDate = "";this.adPrice="";this.adUser="";this.adMod="";this.adCategory="";adStatus="";
    }
    // Setters //
    public void setAll(int id, String title, String detail, String date, String price, String user, String mod, String category,String status){
        this.adID = id;this.adTitle=title;this.adDetails=detail;this.adDate = date;this.adPrice=price;this.adUser=user;this.adMod=mod;this.adCategory=category;adStatus=status;
    }
    public void setTitle(String title){
        this.adTitle = title;
    }
    public void setDetails(String details){
        this.adDetails = details;
    }
    public void setDate (String date){
        this.adDate = date;
    }
    public void setPrice(String price){
        this.adPrice=price;
    }
    
    public void setUser( String user){
        this.adUser=user;
    }
    public void setModerator (String mod){
        this.adMod=mod;
    }
    public void setCategory(String category){
        this.adCategory=category;
    }
    public void setStatus(String status){
        this.adStatus = status;
    }
    // Getters //
    public int getID(){
        return this.adID;
    }
    public String getTitle(){
        return this.adTitle;
    }
    public String getDetails(){
        return this.adDetails;
    }
    public String getDate(){
        return this.adDate;
    }
    public String getPrice(){
        return this.adPrice;
    }
    public String getUser(){
        return this.adUser;
    }
    public String getModerator(){
        return this.adMod;
    }
    public String getStatus(){
        
        return this.adStatus;
    }
    public String getCategory(){
       if (this.adCategory == null ? ELECTRONICS == null : this.adCategory.equals(ELECTRONICS)){
           return "Electronics";
       }else if (this.adCategory == null ? CHILDCARE == null : this.adCategory.equals(CHILDCARE)){
           return "Child Care";
       }
       else if(this.adCategory == null ? HOUSING == null : this.adCategory.equals(HOUSING)){
           return"Housing";
       }else if (this.adCategory == null ? CARTRUCKS == null : this.adCategory.equals(CARTRUCKS)){
           return "Cars and Trucks";
       }
       else{return "Error";}   
}
}
