package com.mypackage.bookloop;

public class BLUserAccount {
    private String userName, userEmail, userAge;

    public BLUserAccount(String userName, String userEmail, String userAge) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userAge = userAge;
        //this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserAge() {
        return userAge;
    }

//    public String getUserPassword() {
//        return userPassword;
//    }
}
