package com.sample.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComponentValidationTest {
    @Test
    public void testValidComponentPrice() {
        assertTrue(ComponentValidation.checkComponentPrice(1234));
        assertTrue(ComponentValidation.checkComponentPrice(500));
        assertTrue(ComponentValidation.checkComponentPrice(699));
        assertTrue(ComponentValidation.checkComponentPrice(560.0));
    }

    @Test
    public void testInvalidComponentPrice() {
        assertFalse(ComponentValidation.checkComponentPrice(-345));
        assertFalse(ComponentValidation.checkComponentPrice(0));
    }

    @Test
    public void testValidComponentType(){
        assertTrue(ComponentValidation.checkType("Keyboard"));
        assertTrue(ComponentValidation.checkType("RAM"));
        assertTrue(ComponentValidation.checkType("Mouse"));
        assertTrue(ComponentValidation.checkType("Processor"));
    }

    @Test
    public void testInvalidComponentType(){
        assertFalse(ComponentValidation.checkType("Hei"));
        assertFalse(ComponentValidation.checkType("3424234"));
        assertFalse(ComponentValidation.checkType("Memor"));
        assertFalse(ComponentValidation.checkType("Pro"));
        assertFalse(ComponentValidation.checkType("Choose component.."));
    }

    @Test
    public void testValidComponentName(){
        assertTrue(ComponentValidation.checkComponentName(""));
    }

}