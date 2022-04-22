package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
    
    private String name;
    private String desc;
    private String hour;
    private int prior;
    
    public Task () {
    }
   
    public Task (String name, String desc, String hour, int prior) {
        this.name = name;
        this.desc = desc;
        this.hour = hour;
        this.prior = prior;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public String getHour() {
        return this.hour;
    }
    
    public int getPrior() {
        return this.prior;
    }
}
