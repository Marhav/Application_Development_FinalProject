package com.sample.components;

import java.util.List;

/**
 * Class to format component and component List coming from a file.
 */

public class ComponentFormatter {

    public static final String DELIMITER = "\t";

    public static String formatComponent(Component component){
        String formattedComponent = component.getComponentID() + DELIMITER + component.getComponentType()
                + DELIMITER + component.getComponentName() + DELIMITER + component.getComponentDescription() +
                DELIMITER + component.getComponentPrice();
        return formattedComponent;
    }

    public static String formatComponentList(List<Component> componentList){
        StringBuffer formattedComponentList = new StringBuffer();
        formattedComponentList.append("Component_ID" + DELIMITER + "Component_Type" + DELIMITER + "Component_Name" +
                DELIMITER + "Component_Description" + DELIMITER + "Component_Price" + "\n");
        for (int i = 0; i < componentList.size(); i++){
            formattedComponentList.append(formatComponent(componentList.get(i)));
            formattedComponentList.append("\n");
        }
        return formattedComponentList.toString();
    }

}

