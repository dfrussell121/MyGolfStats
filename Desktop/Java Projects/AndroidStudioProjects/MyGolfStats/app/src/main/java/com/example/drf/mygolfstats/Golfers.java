package com.example.drf.mygolfstats;

public class Golfers {

    public int golfer_id;
    public String first_name;
    public String surname;

    public Golfers(){
    }

    public Golfers(int golferId, String firstName, String surname) {
        this.golfer_id = golferId;
        this.first_name = firstName;
        this.surname = surname;
    }

    public int getGolfer_id() {
        return golfer_id;
    }

    public void setGolfer_id(int golfer_id) {
        this.golfer_id = golfer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
