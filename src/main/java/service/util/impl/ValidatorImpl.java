package service.util.impl;

import service.exception.ServiceException;
import service.util.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static controller.ControllerStringsStorage.*;

public class ValidatorImpl implements Validator {

    private static final Validator INSTANCE = new ValidatorImpl();
    private final List<String> permissibleCarTypes = Arrays.asList(SEDAN, HATCHBACK, COUPE, CROSSOVER, SUV, MINIVAN);

    private void Validator() {

    }

    public static Validator getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public boolean validateEmail(final String email) {
        Pattern pattern = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean validatePassword(final String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,16}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public void validateInputData(String... strings) throws ServiceException {
        for (String s : strings) {
            if (Objects.isNull(s) || s.isEmpty()) {
                throw new ServiceException("Invalid input data");
            }
        }
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("(\\+)?\\d{12}");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    @Override
    public boolean validateDate(String date) throws ServiceException {
        Date currentDate = new Date();
        Date orderDate;
        try {
            orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new ServiceException("Invalid date!", e);
        }
        return !currentDate.after(orderDate);
    }

    @Override
    public boolean validatePrice(String price) {
        Pattern pattern = Pattern.compile("\\d{5,6}(\\$)?");
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }

    @Override
    public boolean validateImagePath(String imagePath) {
        Pattern pattern = Pattern.compile("([a-zA-Z0-9_]+/)+[a-zA-Z0-9_-]+\\.(png|jpg)");
        Matcher matcher = pattern.matcher(imagePath);
        return matcher.matches();
    }

    @Override
    public boolean isNonNegativeDigit(String... strings) {
        boolean isCorrect = true;
        Pattern pattern = Pattern.compile("^[0-9]+([.,]?[0-9]+)?$");
        Matcher matcher;
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                isCorrect = false;
                break;
            }
        }
        return isCorrect;
    }

    @Override
    public boolean isPermissibleCarType(String carType) {
        return permissibleCarTypes.contains(carType);
    }

}