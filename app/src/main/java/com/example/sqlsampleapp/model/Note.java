package com.example.sqlsampleapp.model;

/**
 * Created by frank on 14-12-2015.
 */
public class Note {
    public int id;
    public String message;
    public String created;
    public User user;

    public Note(int id, String message, String created, User user) {
        this.id = id;
        this.message = message;
        this.created = created;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", created='" + created + '\'' +
                ", user=" + user +
                '}';
    }
}