package com.me.web.roles;

import static com.me.web.AccessHolder.*;

public class Admin extends User {
    public Admin(){
        access = ACCESS_EDIT | ACCESS_SEARCH | ACCESS_READ;
    }
    public Admin(String name, String email) {
        super(name, email);
        access = ACCESS_EDIT | ACCESS_SEARCH | ACCESS_READ;
    }
}
