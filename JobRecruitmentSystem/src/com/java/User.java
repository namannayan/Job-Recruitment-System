package com.java;
import java.util.*;

class User {
    int id;
    String name;
    String email;
    String password;
    String role;
    String resume;
    
    User(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.resume = "";
    }
}