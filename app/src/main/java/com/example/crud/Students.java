package com.example.crud;

public class Students {

    String id;
    String name;
    String course;
    String year;
    String age;

    public Students() {
    }

    public Students(String id, String name, String course, String year, String age) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.year = year;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}