package com.example.cv;

public class cv_data {


    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String skills;
    private String projects;
    private String education;
    private String workexperience;
//constructor
    public cv_data(String fullname, String email,String phone,String address, String skills,
                   String projects,String education,String workexperience ){
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.skills = skills;
        this.projects = projects;
        this.education = education;
        this.workexperience = workexperience;


    }
    //getters
    public String getFullname() {
        return fullname;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public String getSkills() {
        return skills;
    }
    public String getProjects() {
        return projects;
    }
    public String getEducation() {
        return education;
    }
    public String getWorkexperience() {
        return  workexperience;
    }


}
