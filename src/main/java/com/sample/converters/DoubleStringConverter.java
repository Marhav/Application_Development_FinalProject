package com.sample.converters;

public class DoubleStringConverter extends javafx.util.converter.DoubleStringConverter {
    public static boolean doubleConversionSuccesful = false;

    public Double fromString(String string){
        try {
            Double doubleValue = super.fromString(string);
            doubleConversionSuccesful = true;
            return doubleValue;
        }
        catch(NumberFormatException e){
            doubleConversionSuccesful = false;
            return 0.0;
        }
    }
}
