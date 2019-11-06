package com.sda.application;

import com.sda.database.api.IUser;
import com.sda.database.impl.Database;
import com.sda.database.impl.User;
import com.sda.form.api.IForm;
import com.sda.form.impl.Form;


public class Main {

    public static void main(String[] args) {
        IForm form = new Form();
        Database myDB = new Database();

        //todo:Change the parameters in the constructor to test the functionality
        IUser someNewUser = new User("Damian", "damian@yahoo.com", "damian12");

        //Shows the Database before register
        System.out.println(myDB.getAll());
        //Register and validate user
        form.registerUser(someNewUser);
        //Save user in Database if validated
        myDB.save(someNewUser);
        //Show the new database with the user validated
        System.out.println(myDB.getAll());
    }
}
