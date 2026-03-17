package com.java;
import java.util.*;

class Job {
    int id;
    String title;
    String location;
    int salary;
    int recruiterId;
    String skills;  // Comma-separated skills required
    List<String> requiredSkills;
    
    Job(int id, String title, String location, int salary, int recruiterId, String skills) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.salary = salary;
        this.recruiterId = recruiterId;
        this.skills = skills;
        this.requiredSkills = Arrays.asList(skills.toLowerCase().split("\\s*,\\s*"));
    }
}