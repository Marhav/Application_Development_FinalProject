package com.sample.components;

import com.sample.exceptions.Regex;

/**
 * Class made to make sure all the component attributtes have correct values.
 */

public class ComponentValidation {

    public static boolean checkComponentId(String productNr) {
        boolean IDok;
        if (new ComponentRegister().checkID(productNr)){
            IDok = false;
        }
        else {
            IDok = true;
        }

        return IDok;
    }

    public static boolean checkType(String type) {
        boolean typeOk = false;
        ComponentList componentList = new ComponentList();
        for (int i = 0; i < componentList.listOfComponents.size();i++){
            if(componentList.listOfComponents.get(i).equals(type) && !type.equals("Choose component..")) {
                typeOk = true;
                break;
            }
        }

        return typeOk;
    }

    public static boolean checkComponentName(String name) {
/*
        boolean nameOk = Regex.TestRegex(("[0-9A-Za-zÆØÅæøå \\.'"+"-]{5,60}"),name);
        if(name.isEmpty() || name.isBlank()){
            return false;
        }
        else if(!nameOk){
            return false;
        }

 */

        return true;
    }

    public static boolean checkComponentDescription(String description) {
        return true;
    }

    public static boolean checkComponentPrice(double price) {
        return (price > 0);
    }






}
