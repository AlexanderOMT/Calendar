package model;

import java.sql.Timestamp;

public class Task {
    
    private int id;
    private String name;
    private String desc;
    private Timestamp date;
    private int prior;
    private String tag;
    private String specialId;
    
    public Task () {
    }
    
    public Task (int id, String name, String desc, Timestamp date, int prior, String tag) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.prior = prior;
        this.tag = tag;
    }
    
    public Task (String name, String desc, Timestamp date, int prior, String tag, String specialId) {
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.prior = prior;
        this.tag = tag;
        this.specialId = specialId;
    }
    

    public void setId(int id) {
        this.id = id;
    }
    public void setId(String specialId) {
        this.specialId = specialId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setPrior(int prior) {
        this.prior = prior;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }
    
    public String getSpecialId() {
        return specialId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public Timestamp getDate() {
        return this.date;
    }
    
    public int getPrior() {
        return this.prior;
    }
    
    public String getTag() {        
        return tag;
    }
}
