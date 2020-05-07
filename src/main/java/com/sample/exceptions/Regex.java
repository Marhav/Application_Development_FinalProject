package com.sample.exceptions;

public class Regex {
    public static boolean TestRegex(String regex, String... strings) {

        for (String str : strings) {
            if (!str.matches(regex)) {
                return false;
            }
        }

        return true;
    }
}
