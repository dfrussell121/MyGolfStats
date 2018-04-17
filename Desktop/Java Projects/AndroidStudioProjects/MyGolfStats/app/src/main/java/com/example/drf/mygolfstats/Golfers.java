package com.example.drf.mygolfstats;

public class Golfers {
    private int golferId;
    private String firstName;
    private String surname;

    public Golfers(){
    }

    public Golfers(int golferId, String firstName, String surname) {
        this.golferId = golferId;
        this.firstName = firstName;
        this.surname = surname;
    }

    public int getGolferId() {
        return golferId;
    }

    public void setGolferId(int golferId) {
        this.golferId = golferId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
