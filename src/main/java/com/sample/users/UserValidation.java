package com.sample.users;

import com.sample.exceptions.Regex;

/**
 * Class made to make sure all the user attributtes have correct values.
 */

public class UserValidation {

    public static boolean checkUserMail(String userMail){
        // Har hentet regex på epost fra https://stackoverflow.com/questions/8204680/java-regex-email
        boolean isRight = Regex.TestRegex("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", userMail);
        if(userMail.isEmpty() || userMail.isBlank()){
            return false;
        }

        if(!isRight){
            return false;
        }

        return true;
    }

    public static boolean checkPassword(String password){
        boolean isRight = Regex.TestRegex("^(?=.*\\d).{5,14}$", password);
        if(password.isEmpty() || password.isBlank()){
            return false;
        }

        if(!isRight){
            return false;
        }

        return true;
    }

    public static boolean checkFirstName(String firstName){
        boolean isRight = Regex.TestRegex("[A-ZÆØÅ][a-zæøåA-ZÆØÅ -_]+", firstName);
        if(firstName.isEmpty() || firstName.isBlank()){
            return false;
        }
        if(!isRight){
            return false;
        }

        return true;
    }

    public static boolean checkLastName(String lastName){
        boolean isRight = Regex.TestRegex("[A-ZÆØÅ][a-zæøåA-ZÆØÅ -_]+", lastName);
        if(lastName.isEmpty() || lastName.isBlank()){
            return false;
        }
        if(!isRight){
            return false;
        }
        return true;
    }

    public static boolean checkPhone(String phone){
        boolean isRight = Regex.TestRegex("[^-]([(])?([0-9]{1,5})?([)])?( )?(([(])?[+]*[0-9]{1,4}([)])?)?( )?([-]*[0-9]{1,10}( )?[-]?[0-9]{2,10}?)?( )?([-])?([0-9]{1,4})( )?[0-9]{2}", phone);
        if(phone.isEmpty() || phone.isBlank()){
            return false;
        }

        if(!isRight){
            return false;
        }

        return true;
    }

    public static boolean checkAdminRights(boolean adminRights){
        if(adminRights == true || adminRights == false) {
            return true;
        }
        return false;
    }
}
