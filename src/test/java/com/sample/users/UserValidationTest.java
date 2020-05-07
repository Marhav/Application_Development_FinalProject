package com.sample.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserValidationTest {
    @Test
    public void testValidUserMail(){
        assertTrue(UserValidation.checkUserMail("anine@anine.no"));
        assertTrue(UserValidation.checkUserMail("heihei@example.com"));
    }

    @Test
    public void testInvalidUserMail(){
        assertFalse(UserValidation.checkUserMail("anine"));
        assertFalse(UserValidation.checkUserMail("123@"));
    }

    @Test
    public void testValidUserPassword(){
        assertTrue(UserValidation.checkPassword("Hei1234"));
    }

    @Test
    public void testInvalidUserPassword(){
        assertFalse(UserValidation.checkPassword("hei"));
        assertFalse(UserValidation.checkPassword("--"));
    }

    @Test
    public void testValidUserFirstName(){
        assertTrue(UserValidation.checkFirstName("Mari"));
        assertTrue(UserValidation.checkFirstName("Mari-Anne"));
        assertTrue(UserValidation.checkFirstName("Trond Magnus"));
    }

    @Test
    public void testInvalidUserFirstName(){
        assertFalse(UserValidation.checkFirstName("¨¨0+"));
        assertFalse(UserValidation.checkFirstName("1234"));
    }

    @Test
    public void testValidUserLastName(){
        assertTrue(UserValidation.checkLastName("Nilsen"));
        assertTrue(UserValidation.checkLastName("Jakobsen Hansen"));
        assertTrue(UserValidation.checkLastName("Jakobsen-Hansen"));
    }

    @Test
    public void testInvalidUserLastName(){
        assertFalse(UserValidation.checkLastName("000"));
    }

    @Test
    public void testValidUserPhone(){
        assertTrue(UserValidation.checkPhone("95722066"));
        assertTrue(UserValidation.checkPhone("+4795722066"));
        assertTrue(UserValidation.checkPhone("+47 95722066"));
        assertTrue(UserValidation.checkPhone("(+47)95722066"));
        assertTrue(UserValidation.checkPhone("(+47) 95722066"));
        assertTrue(UserValidation.checkPhone("(+47) 95 72 20 66"));
        assertTrue(UserValidation.checkPhone("07911 123456"));
        assertTrue(UserValidation.checkPhone("+44 7911 123456"));
        assertTrue(UserValidation.checkPhone("754-3010"));
        assertTrue(UserValidation.checkPhone("+1-541-754-3010"));
        assertTrue(UserValidation.checkPhone("1-541-754-3010"));
        assertTrue(UserValidation.checkPhone("(541) 754-3010"));
        assertTrue(UserValidation.checkPhone("001-541-754-3010"));

    }

    @Test
    public void testInvalidUserPhone(){
        assertFalse(UserValidation.checkPhone("+++"));
    }

    @Test
    public void testValidUserRight(){
        assertTrue(UserValidation.checkAdminRights(true));
        assertTrue(UserValidation.checkAdminRights(false));
    }
}