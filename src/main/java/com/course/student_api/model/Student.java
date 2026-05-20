package com.course.student_api.model;

public class Student {

    private Long id;
    private String name;
    private String email;
    private String branch;
    private int year;

    public Student() {
    }

    public Student(Long id, String name, String email, String branch, int year) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.branch = branch;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}