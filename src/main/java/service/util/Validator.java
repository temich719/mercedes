package service.util;

import controller.exception.ControllerException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validateEmail(final String email){
        Pattern pattern = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePassword(final String password){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,16}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static void validateInputData(String...strings) throws ControllerException{
        for (String s:strings) {
            if (Objects.isNull(s) || s.isEmpty()){
                throw new ControllerException("Invalid input data");
            }
        }
    }
}