package model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Task {
    
    private int id;
    private String name;
    private String desc;
    private Timestamp date;
    private int prior;
    // private ArrayList<Tags> tag;
    private String tag;
    
    public Task () {
    }
   
    /* public Task (int id, String name, String desc, Timestamp date, int prior, ArrayList<Tags> tag) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.prior = prior;
        this.tag = tag;
    } */
    
    public Task (int id, String name, String desc, Timestamp date, int prior, String tag) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.prior = prior;
        this.tag = tag;
    }

    public int getId() {
        return id;
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
    
    /* public ArrayList<Tags> getTag() {        
        return tag;
    } */
    
    public String getTag() {        
        return tag;
    }
}
