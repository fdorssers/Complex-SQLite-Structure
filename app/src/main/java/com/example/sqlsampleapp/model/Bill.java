package com.example.sqlsampleapp.model;

import java.util.List;

/**
 * Created by frank on 14-12-2015.
 */
public class Bill {
    public int id;
    public String title;
    public double amount;
    public String date;
    public int createdBy;
    public List<User> users;
    public List<Note> notes;

    public Bill(int id, String title, double amount, String date, int createdBy, List<User> users, List<Note> notes) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.createdBy = createdBy;
        this.users = users;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", createdBy=" + createdBy +
                ", users=" + users +
                ", notes=" + notes +
                '}';
    }
}
