package com.sample.users;

import com.sample.exceptions.user.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {

    private transient SimpleStringProperty userMail, password, firstName, lastName, phone;
    private transient SimpleBooleanProperty adminRights;

    public User(String userMail, String password, String firstName, String lastName, String phone, boolean adminRights){
        setUserMail(userMail);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAdminRights(adminRights);
    }

    public void setUserMail(String userMail){
        if (UserValidation.checkUserMail(userMail)){
            this.userMail = new SimpleStringProperty(userMail);
        }
        else {
            throw new InvalidUserEmailException("Invalid email");
        }
    }

    public String getUserMail(){
        return this.userMail.getValue();
    }

    public void setPassword(String password){
        if(UserValidation.checkPassword(password)){
            this.password = new SimpleStringProperty(password);
        }
        else {
            throw new InvalidUserPasswordException("The password must be between 5 and 14 digits and include at least one number between 0 and 9.");
        }
    }

    public String getPassword(){
        return this.password.getValue();
    }

    public void setFirstName(String firstName){
        if(UserValidation.checkFirstName(firstName)){
            this.firstName = new SimpleStringProperty(firstName);
        }
        else {
            throw new InvalidUserFirstNameException("Invalid first name.");
        }
    }

    public String getFirstName(){
        return this.firstName.getValue();
    }

    public void setLastName(String lastName){
        if(UserValidation.checkLastName(lastName)){
            this.lastName = new SimpleStringProperty(lastName);
        }
        else {
            throw new InvalidUserLastNameException("Invalid last name.");
        }
    }

    public String getLastName(){
        return this.lastName.getValue();
    }

    public void setPhone(String phone){
        if(UserValidation.checkPhone(phone)){
            this.phone = new SimpleStringProperty(phone);
        }
        else {
            throw new InvalidUserPhoneException("Invalid phone number.");
        }
    }

    public String getPhone(){
        return this.phone.getValue();
    }

    public void setAdminRights(boolean adminRights){
        if (UserValidation.checkAdminRights(adminRights)){
            this.adminRights = new SimpleBooleanProperty(adminRights);
        }
        else {
            throw new InvalidUserRightException("Invalid user right.");
        }
    }

    public boolean getAdminRights(){
        return this.adminRights.getValue();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(userMail.getValue());
        s.writeUTF(password.getValue());
        s.writeUTF(firstName.getValue());
        s.writeUTF(lastName.getValue());
        s.writeUTF(phone.getValue());
        s.writeBoolean(adminRights.getValue());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String userMail = s.readUTF();
        String password = s.readUTF();
        String firstName = s.readUTF();
        String lastName = s.readUTF();
        String phone = s.readUTF();
        boolean adminRights = s.readBoolean(); //Mulig lesefeil her.(På grunn av String.valueOf i inje 25)

        this.userMail = new SimpleStringProperty(userMail);
        this.password = new SimpleStringProperty(password);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phone = new SimpleStringProperty(phone);
        this.adminRights = new SimpleBooleanProperty(adminRights);
    }

}
