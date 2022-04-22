package model;

import SqlDatabase.HerokuInvitationSqlConnection;

public class User {
    
    private int id;
    private String name;
    private String pwd;
    private String email;
    private boolean login;
    
    public User(){
        
    }
    
    public User(int id, String name, String pwd, String email, boolean login) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.login = login;
    }
    
    public User (Integer id, String email, String pwd) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
    }       
    public User (String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
    
    public void replyInvitation(int calendar_id, boolean reply){
        HerokuInvitationSqlConnection invConnection = HerokuInvitationSqlConnection.getInstance();
        invConnection.replyInvitation(calendar_id, reply ? 1 : 0);
    }
    
    public void inviteUsertoCalendar(User user, ButtonCalendar calendar){
        HerokuInvitationSqlConnection invConnection = HerokuInvitationSqlConnection.getInstance();
        // TODO asegurar que el id de calendario en la BD está en la clase ButtonCalendar
        //invConnection.insertInvitation(this.getId(), user.getId(), calendar.getId());
    }
    
    
    public User getUser(){
        User user = new User();
        user.setEmail(email);
        user.setPwd(pwd);
        return user;
    }
    
    
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    @Override
    public String toString(){
        return "ID: " + id + ", Nombre: " + name + ", Email: " + email + ", Contraseña: " + pwd + ", Login: " + login;
    }
}
