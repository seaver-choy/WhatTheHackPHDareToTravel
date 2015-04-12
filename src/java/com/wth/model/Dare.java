package com.wth.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Dare {
    private int dareID;
    private String userName;
    private int locationID;
    private String dareName;
    private String description;
    private String instruction;
    private LocalDateTime datePosted;
    
    public int getDareID(){
        return dareID;
    }
    
    public void setDareID(int dareID){
        this.dareID = dareID;
    }
    
    public String getUsername(){
        return userName;
    }
    
    public void setUsername(String userName){
        this.userName = userName;
    }
    
    public int getLocID(){
        return locationID;
    }
    
    public void setLocID(int locationID){
        this.locationID = locationID;
    }
    
    public String getDareName(){
        return dareName;
    }
    
    public void setDareName(String dareName){
        this.dareName = dareName;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getInstruction(){
        return this.instruction;
    }
    
    public void setInstruction(String instruction){
        this.instruction = instruction;
    }
    
    public LocalDateTime getPostDate(){
        return datePosted;
    }
    
    public void setPostDate(LocalDateTime datePosted){
        this.datePosted = datePosted;
    }
    
    
    @Override
    public boolean equals(Object o) {
    	Dare d = (Dare) o;
    	return this.dareID == d.getDareID();
    }
}
