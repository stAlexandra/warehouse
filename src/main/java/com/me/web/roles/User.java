package com.me.web.roles;

import static com.me.web.AccessHolder.*;

public class User {
    private long id;
    private String name;
    private String email;
    int access;

    public User(){
        access = ACCESS_SEARCH | ACCESS_READ; // User может искать И читать информацию
    }

    public User(String name, String email) {
        this();
        this.name = name;
        this.email = email;
    }

    public void setId(){
        ++this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAccess() {
        return access;
    }
}
