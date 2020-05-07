package com.sample.components;

import com.sample.fileManagement.ReadFromFileJobj;
import com.sample.fileManagement.WriteToFileJobj;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a register of components.
 */

public class ComponentRegister extends Task<Void> implements Serializable {

    private static final long serialVersionUID = 2L;

    private transient ObservableList<Component> componentRegister = FXCollections.observableArrayList();

    public void attachToTableView(TableView tableview){
        tableview.setEditable(true);
        tableview.setItems(componentRegister);
    }

    public void addComponentToList(Component newComponent){
        componentRegister.add(newComponent);
    }

    public void deleteComponentFromList(Component component){
        componentRegister.remove(component);
    }

    public boolean checkID(String ID){
        boolean existingID = false;
        for (Component IDs : componentRegister){
            if (IDs.getComponentID().equals(ID)){
                existingID = true;
                break;
            }
        }

        return existingID;
    }

    public List<Component> separateRegisterByType(String componentType){
        ObservableList separatedComponentList = FXCollections.observableArrayList();
        for(Component component : componentRegister){
            if(component.getComponentType().equals(componentType)){
                separatedComponentList.add(component);
            }
        }
        return separatedComponentList;
    }

    public FilteredList<Component> makeFilteredRegisterForCB(ChoiceBox filterChoice){
        FilteredList<Component> filteredList = new FilteredList(componentRegister, x -> true);
        filterChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(component -> {
                if (newValue.equals("Choose component..")){
                    return true;
                }
                else {
                    if (component.getComponentType().equals(newValue)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        });


        return filteredList;
    }

    public SortedList<Component> makeFilteredRegister(TextField txtField, TableView tv, ChoiceBox filterChoice){
        FilteredList<Component> filteredList = new FilteredList(componentRegister, x -> true);
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(component -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String searchToLowerCase = newValue.toLowerCase();
                String chosenFilter = filterChoice.getValue().toString();

                switch(chosenFilter){
                    case "Item ID":
                        if(component.getComponentID().toLowerCase().contains(searchToLowerCase)){
                            return true;
                        }
                        else {
                            return false;
                        }
                    case "Item type":
                        if (component.getComponentType().toLowerCase().contains(searchToLowerCase)){
                            return true;
                        }
                        else {
                            return false;
                        }
                    case "Item name":
                        if (component.getComponentName().toLowerCase().contains(searchToLowerCase)){
                            return true;
                        }
                        else {
                            return false;
                        }
                    case "Item description":
                        if (component.getComponentDescription().toLowerCase().contains(searchToLowerCase)){
                            return true;
                        }
                        else {
                            return false;
                        }
                    case "Item price":
                        if(Double.toString(component.getComponentPrice()).toLowerCase().contains(searchToLowerCase)){
                            return true;
                        }
                        else {
                            return false;
                        }
                    default:
                        if(component.getComponentID().toLowerCase().contains(searchToLowerCase) ||
                                component.getComponentType().toLowerCase().contains(searchToLowerCase) ||
                                component.getComponentName().toLowerCase().contains(searchToLowerCase) ||
                                component.getComponentDescription().toLowerCase().contains(searchToLowerCase) ||
                                Double.toString(component.getComponentPrice()).toLowerCase().contains(searchToLowerCase)){
                            return true;
                        }
                        else {
                            return false;
                        }

                }
            });
        });

        SortedList<Component> sortedComponentList = new SortedList<>(filteredList);
        sortedComponentList.comparatorProperty().bind(tv.comparatorProperty());
        return sortedComponentList;
    }

    public void saveComponentRegisterToProgramFile() throws IOException {
        WriteToFileJobj<Component> fileSaver = new WriteToFileJobj();
        String stringPath = "./src/main/resources/ProgramFiles/componentRegister.jobj";
        Path path = Paths.get(stringPath);
        fileSaver.writeFile(componentRegister,path);
    }

    public void saveComponentRegisterToBackupFile() throws IOException {
        WriteToFileJobj<Component> fileSaver = new WriteToFileJobj();
        String stringPath = "./src/main/resources/ProgramFiles/componentRegisterBackup.jobj";
        Path path = Paths.get(stringPath);
        fileSaver.writeFile(componentRegister,path);
    }

    public void saveComponentRegisterToNewFile() throws IOException {
        WriteToFileJobj<Component> fileSaver = new WriteToFileJobj();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Jobj files","*.jobj"));
        File selectedFile = fileChooser.showSaveDialog(new Stage());
        fileSaver.writeFile(componentRegister,selectedFile.toPath());
    }

    public void openRegisterFromProgramFile() throws IOException, ClassNotFoundException {
        ReadFromFileJobj fileOpener = new ReadFromFileJobj();
        String stringPath = "./src/main/resources/ProgramFiles/componentRegisterWithErrors2.jobj";
        Path path = Paths.get(stringPath);
        componentRegister = (ObservableList<Component>) fileOpener.read(path);
    }

    public void openRegisterFromBackupFile() throws IOException, ClassNotFoundException {
        ReadFromFileJobj fileOpener = new ReadFromFileJobj();
        String stringPath = "./src/main/resources/ProgramFiles/componentRegisterBackup.jobj";
        Path path = Paths.get(stringPath);
        componentRegister = (ObservableList<Component>) fileOpener.read(path);
    }

    public void openRegisterFromFile() throws IOException, ClassNotFoundException {
        ReadFromFileJobj fileOpener = new ReadFromFileJobj();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Jobj files","*.jobj"));
        File selectedFile = fileChooser.showSaveDialog(new Stage());
       componentRegister = (ObservableList<Component>) fileOpener.read(selectedFile.toPath());
    }

    @Override
    public String toString(){
        String ut = "";
        for (int i = 0; i < componentRegister.size(); i++){
            ut += componentRegister.get(i).getComponentID() + " ";
        }

        return ut;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(componentRegister));
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        List<Component> componentList = (List<Component>) s.readObject();
        this.componentRegister = FXCollections.observableArrayList();
        this.componentRegister.addAll(componentList);
    }

    @Override
    protected Void call() throws Exception {
        Thread.sleep(3000);
        ReadFromFileJobj fileOpener = new ReadFromFileJobj();
        String stringPath = "./src/main/resources/ProgramFiles/componentRegister.jobj";
        Path path = Paths.get(stringPath);
        componentRegister = (ObservableList<Component>) fileOpener.read(path);
        return null;
    }

}
