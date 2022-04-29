package model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class CalendarTask{
    
    private final ArrayList<Task> dateTasks = new ArrayList<>();
    private String name;
    private int id;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CalendarTask (){}
    
    public CalendarTask (String name) {
        this.name = name;
    }

    public CalendarTask (String name, int id){
        this.name=name;
        this.id=id;
    }
    // Method to add a task
    public void addTask(Task t) {
        dateTasks.add(t);
        //HerokuTaskSqlConnection htqc = new HerokuTaskSqlConnection();
        //htqc.insertTaskByTask(t);
    }
    
    // Method to modify a task
    public void setTask(Integer position, Task newTask) {
        if(dateTasks.size() > position){
            dateTasks.set(position, newTask);
        }
    }
    
    //Method to delete a task
    public void deleteTask(Integer id) {
        Iterator i = dateTasks.iterator();
        Task tDel = new Task();
        while(i.hasNext()) {
            Task t = (Task)i.next();
            if (t.getId() == id) {
                tDel = t;
            }
        }
        dateTasks.remove(tDel);
    }
    
    // Method to get all tasks
    public List<Task> getTasks(Timestamp date) {
        if (dateTasks == null) {
            return null;
        }
        Iterator i = dateTasks.iterator();
        List<Task> dayTasks = new ArrayList<>();
        while (i.hasNext()) {
            Task t = (Task)i.next();
            System.out.println("a: " + t.getDate().getDate());
            System.out.println("b: " +date.getDate());
            if (t.getDate().getYear() == date.getYear() 
                    && t.getDate().getMonth() == date.getMonth() 
                    && t.getDate().getDate() == date.getDate()) {
                dayTasks.add(t);
            }
        }
        return dayTasks;
    }
    
    // Method to get a task
    public Task getTask(int id) {
        Task t = dateTasks.get(id);
        return t;
    }
    
        
    public static CalendarTask getCalendarFromJson(JSONObject jsonObject, String name) throws ParseException, JsonProcessingException{
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JSONObject getJson = (JSONObject) jsonObject.get(name);
        
        CalendarTask mapped = (CalendarTask) objectMapper.readValue(getJson.toJSONString(), CalendarTask.class);
        return mapped;
    }

    public Integer getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
