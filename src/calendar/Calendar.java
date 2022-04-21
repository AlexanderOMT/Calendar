package calendar;

import design.Login;

import SqlDatabase.HerokuCalendarPermitSqlConnection;
import SqlDatabase.HerokuCalendarSqlConnection;
import SqlDatabase.HerokuInvitationSqlConnection;
import SqlDatabase.HerokuTaskSqlConnection;
import SqlDatabase.HerokuUsersSqlConnection;
import design.Login;
import model.Task;
import model.User;
import model.CalendarTask;

public class Calendar {

    public static void main(String[] args) {
        /*
        Login lg = new Login();
        lg.setVisible(true);
        */
        
        //HerokuUsersSqlConnection userConnection = HerokuUsersSqlConnection.getInstance();
        //HerokuTaskSqlConnection taskConnection = HerokuTaskSqlConnection.getInstance();
        //HerokuCalendarSqlConnection calConnection = HerokuCalendarSqlConnection.getInstance();
        //HerokuCalendarPermitSqlConnection calPermitConnection = HerokuCalendarPermitSqlConnection.getInstance();
        HerokuInvitationSqlConnection invConnection = HerokuInvitationSqlConnection.getInstance();
        /*
        User user = new User(1, "pwd1", "email1");
        Task task = new Task("Tarea1", "Tarea1", "01:10", 1);
        CalendarTask calendar = new CalendarTask("Calendar1");

        User user1 = new User(2, "pwd2", "email2");
        Task task1 = new Task("Tarea2", "Tarea2", "02:20", 2);
        CalendarTask calendar1 = new CalendarTask("Calendar2");
        
                
        User user2 = new User(3, "pwd3", "email3");
        Task task2 = new Task("Tarea3", "Tarea3", "03:30", 3);
        CalendarTask calendar2 = new CalendarTask("Calendar3");
        */
        
        //userConnection.insertUser(user.getName(), user.getPwd(), user.getEmail(), false);
        //taskConnection.insertTask("Tarea");
        //calConnection.insertCalendar("Calendar");
        
        // insertCalendarPermit - Integer user_id, Integer calendar_id, Integer task_id, String rol
        
        //calPermitConnection.insertCalendarPermit(8, 1, 1, "Admin");
        //calPermitConnection.insertCalendarPermit(9, 1, 1, "Editor");

        //calPermitConnection.insertCalendarPermit(10, 2, 1, "Admin");
        //calPermitConnection.insertCalendarPermit(8, 2, 1, "Lector");
  
        //calPermitConnection.insertCalendarPermit(10, 3, 3, "Admin");
        //calPermitConnection.insertCalendarPermit(10, 1, 2, "Editor");
 
        /*
        System.out.println("Seleccionar todos permnisos de calendario:");        
        calPermitConnection.selectAllCalendarsPermits();
        System.out.println("-------------------------------------------------");                
        
        System.out.println("Seleccionar permnisos de calendario por id de calendario:");
        calPermitConnection.selectCalendarPermitByCalendarId(1);       
        System.out.println("-------------------------------------------------");                
     
        System.out.println("Seleccionar id de un calendario por nombre de propietario y nombre de calendario:");
        calConnection.selectCalendarIdByNameAndSpecialId("b", "bnull");
        System.out.println("-------------------------------------------------");
        
        System.out.println("Seleccionar tareas de uno calendario por id de calendario:");
        calPermitConnection.selectTaskByCalendarId(1);
        System.out.println("-------------------------------------------------");                
        
        
        System.out.println("Seleccionar calendario(s) donde encuentre la id de una tarea:");
        calPermitConnection.selectCalendarByTaskId(2);
        System.out.println("-------------------------------------------------");                
    
        System.out.println("Seleccionar todos los permisos de los calendarios por id de usuario:");
        calPermitConnection.selectAllCalendarsPermitsByIdUser(10);
        System.out.println("-------------------------------------------------");                
        
        System.out.println("Seleccionar permisos usuarios por id de calendario:");
        calPermitConnection.selectUsersPermitsByCalendarId(1);
        System.out.println("-------------------------------------------------");           
        */
        
        /*
        System.out.println("-------------------------------------------------"); 
        System.out.println("Seleccionar todas las invitaciones:");
        invConnection.selectAllInvitations();
        System.out.println("-------------------------------------------------"); 
        System.out.println("Seleccionar invitaciones por id:");
        invConnection.selectInvitationById(1);
        System.out.println("-------------------------------------------------"); 
        
        */

        System.out.println("Seleccionar por id de usuario origen y destino:");
        invConnection.selectInvitationIdByOriginAndTarget(1, 2);
        System.out.println("-------------------------------------------------"); 

        System.out.println("Responder a invitación por id de invitación:");
        invConnection.replyInvitation(1, 1);
        System.out.println("-------------------------------------------------");    
        
        System.out.println("Selecciona todo por id de calendario:");
        invConnection.selectAllByCalendarId(1);
        System.out.println("-------------------------------------------------");           
    }
}