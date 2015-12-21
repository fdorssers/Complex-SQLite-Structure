package com.example.sqlsampleapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by frank on 14-12-2015.
 */
public class User {
    public int id;
    public String name;
    public String email;
    @SerializedName("member_since")
    public String memberSince;
    public String avatar;
    public double balance;
    public double paid;
    public double owes;

    public User(int id, String name, String email, String memberSince, String avatar, double balance, double paid, double owes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.memberSince = memberSince;
        this.avatar = avatar;
        this.balance = balance;
        this.paid = paid;
        this.owes = owes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", memberSince='" + memberSince + '\'' +
                ", avatar='" + avatar + '\'' +
                ", balance=" + balance +
                ", paid=" + paid +
                ", owes=" + owes +
                '}';
    }
}
