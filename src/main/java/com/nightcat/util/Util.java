package com.nightcat.util;

import com.nightcat.model.User;

public class Util {

    public static boolean equalsPassword(User correct, String password) {
        return correct.getPassword().equals(password);
    }

}
