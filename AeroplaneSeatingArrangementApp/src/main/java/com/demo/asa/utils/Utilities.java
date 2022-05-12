package com.demo.asa.utils;

import com.demo.asa.exceptions.AppServiceException;

public class Utilities {

    public static boolean checkInputs(int inputValue) throws AppServiceException {
        if (inputValue <= 0) {
            return true;
        }
        return false;
    }
}
