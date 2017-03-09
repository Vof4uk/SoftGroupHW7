package ua.mykytenko.util;

public class CalcUtil {
    private CalcUtil(){}

    public static boolean phoneNumberIsValid(String phoneNumber){
        return phoneNumber.matches("^\\+(?:[0-9]●?){6,14}[0-9]$");
    }
}
