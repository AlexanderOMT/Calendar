package model;

import SqlDatabase.HerokuCalendarPermitSqlConnection;
import SqlDatabase.HerokuCalendarSqlConnection;
import SqlDatabase.HerokuTaskSqlConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Iterator;

public class CalendarTask{
    
    private ArrayList<Task> dateTasks = new ArrayList<>();
    private final String name;
    private Integer id;
    
    public CalendarTask (String name) {
        this.name = name;
    }
    
    public CalendarTask (String name, int id) {
        this.name = name;
        this.id = id;
    }

    // recuperar todas las task BD
    public void retrieveTaskFromDB() {
        HerokuCalendarPermitSqlConnection connToTask = HerokuCalendarPermitSqlConnection.getInstance();
        dateTasks = connToTask.selectTaskByCalendarId(id);
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
        // propuesta(Base de Datos desacoplado del modelo):
        // htqc.insertTask(t.getName(), t.getDate(), t.getTag(), t.getPrior(), t.getDesc());

    }
    
    // Método para obtener todas las tareas de un calendario
    public List<Task> getAllTasks() {

        retrieveTaskFromDB();
        
        Iterator i = dateTasks.iterator();
        List<Task> dayTasks = new ArrayList<>();
        while (i.hasNext()) {
            Task t = (Task)i.next();
            dayTasks.add(t);
            
        }
        return dayTasks;
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
