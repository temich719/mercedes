package service.util;

import service.exception.ServiceException;

public interface Validator {
    /**
     * checks email format(example123@mail.ru)
     *
     * @param email is the user email
     * @return true if email is correct and false if not
     */
    boolean validateEmail(final String email);

    /**
     * checks reliability of password
     *
     * @param password is the user password
     * @return true if password is reliable and false if vulnerable
     */
    boolean validatePassword(final String password);

    /**
     * checks all data that comes from UI for emptiness
     *
     * @param strings - data from UI
     * @throws ServiceException is a module exception
     */
    void validateInputData(String... strings) throws ServiceException;

    /**
     * checks phone number format
     *
     * @param phoneNumber is a phone number
     * @return true if phone number consists of 12 digits(also can be plus at the beginning of line) and false if not
     */
    boolean validatePhoneNumber(String phoneNumber);

    /**
     * checks if date is in future
     *
     * @param date is a date
     * @return true if the date is in future and false if not
     * @throws ServiceException is a module exception
     */
    boolean validateDate(String date) throws ServiceException;

    /**
     * checks price format
     *
     * @param price is a price
     * @return true if price is non-negative and consists of 5 or 6 digits(can be '$' at the end of line) and false if not
     */
    boolean validatePrice(String price);

    /**
     * checks image path format
     *
     * @param imagePath is the image path
     * @return true if image path corresponds to expected format and false if not
     */
    boolean validateImagePath(String imagePath);

    /**
     * checks if the input strings are a non-negative number
     *
     * @param strings strings to check
     * @return true if all strings are non-negative numbers and false if not
     */
    boolean isNonNegativeDigit(String... strings);

    /**
     * checks if the car type is allowed to be added
     *
     * @param carType is the car type
     * @return true if car type is allowed to be added and false if not
     */
    boolean isPermissibleCarType(String carType);
}
