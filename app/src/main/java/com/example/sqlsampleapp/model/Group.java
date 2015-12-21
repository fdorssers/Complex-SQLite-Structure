package com.example.sqlsampleapp.model;

import java.util.List;

/**
 * Created by frank on 14-12-2015.
 */
public class Group {
    public int id;
    public String name;
    public double balance;
    public List<User> users;
    public List<Bill> bills;

    public Group(int id, String name, double balance, List<User> users, List<Bill> bills) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.users = users;
        this.bills = bills;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name=" + name +
                ", balance=" + balance +
                ", users=" + users +
                ", bills=" + bills +
                '}';
    }
}
