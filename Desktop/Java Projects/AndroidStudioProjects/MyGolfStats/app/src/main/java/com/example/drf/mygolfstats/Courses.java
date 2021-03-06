package com.example.drf.mygolfstats;

public class Courses {

    public String course_id;
    public String course;
    public int par;
    public int slope;
    public double rating;

    public Courses() {
    }

    public Courses (String course, int par, double rating, int slope){
        this.course_id = course_id;
        this.course = course;
        this.par = par;
        this.rating = rating;
        this.slope = slope;
    }

    public String getCourse_id(){
        return course_id;
    }

    public void setCourse_id(int courseID){
        this.course_id = course_id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(int slope) {
        this.slope = slope;
    }
}
