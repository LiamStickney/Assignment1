package com.example.liamstickney.assignment1.model;

/**
 * Created by liamstickney on 2018-09-11.
 */
    /*
    the student class that defines the students created and added to the arraylist
    every student has its own name and number as variables
    the resource string is also a variable that is passed into the constructor and is defined when the object is made
    also includes getters and setters for the name and number
    */
public class Student {

    private String resource;
    private String name;
    private int number;

    public Student(String resource, String name, int number) {
        this.resource = resource;
        this.name = name;
        this.number = number;
    }

    /*
    toString method that returns the student's info as a string
    the resource variable is always the string in the strings.xml that displays Name: and Number:
    */
    @Override
    public String toString() {
        return String.format(resource, name, number);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {

        return name;
    }

    public int getNumber() {
        return number;
    }
}
