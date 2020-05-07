package com.sample.components;

import com.sample.exceptions.component.InvalidComponentFormatException;

/**
 * Convert from input file to a component.
 */

public class ComponentParser {
   public static Component parseComponent(String componentList) throws InvalidComponentFormatException{
       String[] split = componentList.split(ComponentFormatter.DELIMITER);
       if(split.length != 5) {
           throw new InvalidComponentFormatException();
       }

       String componentID = split[0];
       String componentType = split[1];
       String componentName = split[2];
       String componentDescription = split[3];
       String componentPriceIn = split[4];

       double componentPrice = Double.parseDouble(componentPriceIn);
       Component newComponent = new Component(componentID,componentType,componentName,componentDescription,componentPrice);

       return newComponent;
   }
}
