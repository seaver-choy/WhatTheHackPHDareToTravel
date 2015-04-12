package com.wth.model;

import java.time.LocalDateTime;

public class AmazingRace {
    private int raceID;
    private String userName;
    private String raceName;
    private String description;
    private LocalDateTime datePosted;
    
    public int getRaceID(){
        return raceID;
    }
    
    public void setRaceID(int raceID){
        this.raceID = raceID;
    }
    
    public String getUsername(){
        return userName;
    }
    
    public void setUsername(String userName){
        this.userName = userName;
    }
    
    public String getRaceName(){
        return raceName;
    }
    
    public void setRaceName(String raceName){
        this.raceName = raceName;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public LocalDateTime getPostDate(){
        return datePosted;
    }
    
    public void setPostDate(LocalDateTime datePosted){
        this.datePosted = datePosted;
    }
}

