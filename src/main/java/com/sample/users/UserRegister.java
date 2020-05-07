package com.sample.users;

import com.sample.components.Component;
import com.sample.fileManagement.ReadFromFileJobj;
import com.sample.fileManagement.WriteToFileJobj;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserRegister implements Serializable {

    private static final long serialVersionUID = 4L;

    private transient ObservableList<User> userRegister = FXCollections.observableArrayList();

    public void addUser(User user) {
        userRegister.add(user);
    }

    public void deleteUser(User user) {
        userRegister.remove(user);
    }

    public boolean userNameExists(TextField loginName){
        boolean userNameExists = false;
        for (int i = 0; i < userRegister.size(); i++){
            if (loginName.getText().equals(userRegister.get(i).getUserMail())){
                userNameExists = true;
                break;
            }
            else {
                userNameExists = false;
            }
        }

        return userNameExists;
    }

    public boolean isAdminUser(TextField loginName, PasswordField password){
        boolean isAdminUser = false;
        for(int i = 0; i < userRegister.size(); i++){
            if(loginName.getText().equals(userRegister.get(i).getUserMail()) && password.getText().equals(userRegister.get(i).getPassword())
                    && userRegister.get(i).getAdminRights() == true){
                isAdminUser = true;
                break;
            }
            else {
                isAdminUser = false;
            }
        }

        return isAdminUser;
    }

    public boolean isUser(TextField loginName, PasswordField password){
        boolean isUser = false;
        for(int i = 0; i < userRegister.size(); i++){
            if(loginName.getText().equals(userRegister.get(i).getUserMail()) && password.getText().equals(userRegister.get(i).getPassword())
                    && userRegister.get(i).getAdminRights() == false){
                isUser = true;
                break;
            }
            else {
                isUser = false;
            }
        }

        return isUser;
    }

    public void openUserRegisterFromProgramFile() throws IOException, ClassNotFoundException {
        ReadFromFileJobj fileOpener = new ReadFromFileJobj();
        String stringPath = "./src/main/resources/ProgramFiles/userRegister.jobj";
        Path path = Paths.get(stringPath);
        userRegister = (ObservableList<User>) fileOpener.read(path);
    }

    public void saveUserRegisterToProgramFile() throws IOException {
        WriteToFileJobj<Component> fileSaver = new WriteToFileJobj();
        String stringPath = "./src/main/resources/ProgramFiles/userRegister.jobj";
        Path path = Paths.get(stringPath);
        fileSaver.writeFile(userRegister, path);
    }

    public void attachToTableView(TableView tableview) {
        tableview.setEditable(true);
        tableview.setItems(userRegister);
    }

    public SortedList<User> makeFilteredRegister2(TextField txtField, TableView tv, ChoiceBox filterChoice) {
        FilteredList<User> filteredList = new FilteredList(userRegister, x -> true);
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchToLowerCase = newValue.toLowerCase();
                String chosenFilter = filterChoice.getValue().toString();

                switch (chosenFilter) {
                    case "Mail":
                        if (user.getUserMail().toLowerCase().contains(searchToLowerCase)) {
                            return true;
                        } else {
                            return false;
                        }
                    case "First name":
                        if (user.getFirstName().toLowerCase().contains(searchToLowerCase)) {
                            return true;
                        } else {
                            return false;
                        }
                    case "Last name":
                        if (user.getLastName().toLowerCase().contains(searchToLowerCase)) {
                            return true;
                        } else {
                            return false;
                        }
                    case "Phone number":
                        if (user.getPhone().toLowerCase().contains(searchToLowerCase)) {
                            return true;
                        } else {
                            return false;
                        }
                    default:
                        if (user.getUserMail().toLowerCase().contains(searchToLowerCase) ||
                                user.getFirstName().toLowerCase().contains(searchToLowerCase) ||
                                user.getLastName().toLowerCase().contains(searchToLowerCase) ||
                                user.getPhone().toLowerCase().contains(searchToLowerCase)) {
                            return true;
                        } else {
                            return false;
                        }

                }
            });
        });

        SortedList<User> sortedUserList = new SortedList<>(filteredList);
        sortedUserList.comparatorProperty().bind(tv.comparatorProperty());
        return sortedUserList;
    }

    public SortedList<User> makeFilteredRegister(TextField txtField, TableView tv, ChoiceBox filterChoice) {
        SortedList<User> sortedlistUser = makeFilteredRegister2(txtField, tv, filterChoice);
        FilteredList<User> filteredList = new FilteredList(sortedlistUser, x -> true);

        filterChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(user -> {

                String chosenFilter = filterChoice.getValue().toString();

                switch (chosenFilter) {
                    case "Admin":
                        if(user.getAdminRights()){
                            return true;
                        }
                        else {
                            return false;
                        }

                    case "User":
                        if(!user.getAdminRights()){
                            return true;
                        }
                        else {
                            return false;
                        }

                    default:
                        return true;
                }
            });
        });


        SortedList<User> sortedComponentList = new SortedList<>(filteredList);
        sortedComponentList.comparatorProperty().bind(tv.comparatorProperty());
        return sortedComponentList;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(userRegister));
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        List<User> list = (List<User>) s.readObject();
        userRegister = FXCollections.observableArrayList();
        userRegister.addAll(list);
    }

    @Override
    public String toString(){
        String ut = "";
        for (int i = 0; i < userRegister.size(); i++){
            ut += userRegister.get(i).getUserMail() + " ";
        }

        return ut;
    }

}
