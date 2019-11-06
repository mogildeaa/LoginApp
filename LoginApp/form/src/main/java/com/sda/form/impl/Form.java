package com.sda.form.impl;

import com.sda.database.impl.Database;
import com.sda.database.impl.User;
import com.sda.form.api.IGreetingService;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sda.database.api.IDatabaseAccess;
import com.sda.database.api.IUser;
import com.sda.form.api.IForm;

import java.util.Arrays;

public class Form implements IForm {

    private static final Logger LOGGER = LoggerFactory.getLogger(Form.class);
    private IDatabaseAccess databaseConnection;
    private IGreetingService greetingService = user -> LOGGER.info("Hello, {}", user.getName());

    public Form() {
        databaseConnection = new Database();
    }

    @Override
    public void registerUser(IUser newUser) {
        LOGGER.debug("Registering a new user, with id '{}'", newUser.getId());

        for (IUser u : databaseConnection.getAll()) {
            if (u.getId().equals(newUser.getId())) {
                try {
                    throw new IllegalAccessException("User already signed.");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                boolean valid = EmailValidator.getInstance().isValid(newUser.getId());
                String str = new String(newUser.getPassword());
                if (!valid) {
                    throw new IllegalArgumentException("Please enter a valid email.");
                    //Not the best password validation but, for this project, it does the job :D
                } else if (str == null || str.trim().isEmpty() || str.trim().length() < 6) {
                    throw new IllegalArgumentException("Your password doesn't match the criteria.");
                } else {
                    databaseConnection.save(newUser);
                    break;
                }
            }
        }
    }

    @Override
    public boolean login(String email, String password) {
        LOGGER.debug("Attempting login for user '{}'", email);
        boolean result = false;
        IUser foundUser = databaseConnection.getById(email);

        boolean passwordsMatch = Arrays.equals(foundUser.getPassword(), password.toCharArray());

        if (passwordsMatch) {
            result = true;
            greetingService.greet(foundUser);
        }

        return result;
    }
}
