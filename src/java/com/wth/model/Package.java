package com.wth.model;

public class Package {
    private int packageID;
    private String userName;
    private String packName;
    private String description;
    
    public int getPackID(){
        return packageID;
    }
    
    public void setPackID(int packageID){
        this.packageID = packageID;
    }
    
    public String getUsername(){
        return userName;
    }
    
    public void setUsername(String userName){
        this.userName = userName;
    }
    
    public String getPackName(){
        return packName;
    }
    
    public void setPackName(String packName){
        this.packName = packName;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
}
