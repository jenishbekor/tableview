package com.example.demoproject;

import com.example.demoproject.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private TextField emailInput;

    @FXML
    private TextField groupInput;

    @FXML
    private TextField idInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField surnameInput;


    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Integer> idColumn;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> surnameColumn;
    @FXML private TableColumn<Student, String> emailColumn;
    @FXML private TableColumn<Student, String> groupColumn;

    private ObservableList<Student> students = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Link columns to Student properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));

        // Set data list to the table
        studentTable.setItems(students);
    }


    @FXML
    void handleSave(ActionEvent event) {

        try {

            int id = Integer.parseInt(idInput.getText());
            String name = nameInput.getText();
            String surname = surnameInput.getText();
            String email = emailInput.getText();
            String group = groupInput.getText();

            Student student = new Student(id, name, surname);
            student.setEmail(email);
            student.setGroup(group);


            students.add(student);  // when we add students list, tableview is updated automatically


            System.out.println("Student successfully added to the list.");
            idInput.setText("");
            nameInput.setText("");
            surnameInput.setText("");
            emailInput.setText("");
            groupInput.setText("");

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void onPrintClick(){

        for(Student s: students){
            System.out.println(s);
        }

    }

}
