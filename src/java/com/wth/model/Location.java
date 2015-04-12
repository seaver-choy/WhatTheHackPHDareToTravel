package com.wth.model;

public class Location {
    private int locationID;
    private String locName;
    private String address;
    private String description;
    
    public int getLocID(){
        return locationID;
    }
    
    public void setLocID(int locationID){
        this.locationID = locationID;
    }
    
    public String getLocName(){
        return locName;
    }
    
    public void setLocName(String locName){
        this.locName = locName;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
}
