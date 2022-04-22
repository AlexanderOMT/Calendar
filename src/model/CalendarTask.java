package model;

import SqlDatabase.HerokuTaskSqlConnection;
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
    private Integer id;

    public CalendarTask (){}
    
    public CalendarTask (String name) {
        this.name = name;
    }

    
    // Método para modificar una tarea
    public void setTask(Integer position, Task newTask) {
        if(dateTasks.size() > position){
            dateTasks.set(position, newTask);
        }
    }
    
    // Método para añadir una tarea
    public void addTask(Task t) {
        dateTasks.add(t);
        HerokuTaskSqlConnection htqc = new HerokuTaskSqlConnection();
        htqc.insertTaskByTask(t);
    }
    
    // Método para obtener todas las tareas de una fecha
    public List<Task> getTasks(Timestamp date) {
        Iterator i = dateTasks.iterator();
        List<Task> dayTasks = new ArrayList<>();
        while (i.hasNext()) {
            Task t = (Task)i.next();
            if (t.getDate().equals(date)) {
                dayTasks.add(t);
            }
        }
        return dayTasks;
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
