/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Minerva
 */
public class Invitation {
    private int invitation_id;
    private int origin_user;
    private int target_user;
    private int calendar_id;
    private String reply;

    public Invitation(int invitation_id, int origin_user, int target_user, int calendar_id, String reply) {
        this.invitation_id = invitation_id;
        this.origin_user = origin_user;
        this.target_user = target_user;
        this.calendar_id = calendar_id;
        this.reply = reply;
    }

    public int getInvitation_id() {
        return invitation_id;
    }

    public void setInvitation_id(int invitation_id) {
        this.invitation_id = invitation_id;
    }

    public int getOrigin_user() {
        return origin_user;
    }

    public void setOrigin_user(int origin_user) {
        this.origin_user = origin_user;
    }

    public int getTarget_user() {
        return target_user;
    }

    public void setTarget_user(int target_user) {
        this.target_user = target_user;
    }

    public int getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(int calendar_id) {
        this.calendar_id = calendar_id;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
            
}
