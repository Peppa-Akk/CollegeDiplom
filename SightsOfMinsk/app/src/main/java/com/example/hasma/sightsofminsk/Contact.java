package com.example.hasma.sightsofminsk;

public class Contact {
    int id, min_rating;
    String name, pass;

    public void setId(int id) {
        this.id = id;
    }
    public int getId()
    {
        return this.id;
    }
    public void setMin_rating(int min_rating) {
        this.min_rating = min_rating;
    }
    public int getMin_rating()
    {
        return this.min_rating;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setPass(String pass)
    {
        this.pass = pass;
    }
    public String getPass()
    {
        return this.pass;
    }
}