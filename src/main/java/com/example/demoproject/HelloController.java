package com.example.demoproject;

import com.example.demoproject.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
        //this.readCSV();
        this.readJSON();
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

    private void readCSV(){
        try{
            String filename = "src/main/resources/com/example/demoproject/filename.txt";
            Scanner input = new Scanner(new File(filename));
            if (input.hasNextLine()) {
                input.nextLine(); // Skip the first line (header)
            }

            while (input.hasNextLine()) {
                String line = input.nextLine();
                // Split by comma (CSV format)
                String[] data = line.split(",");

                // Extract student information
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String surname = data[2];
                String email = data[3];
                String group = data[4];

                Student student = new Student(id, name, surname, email, group);
                students.add(student);

            }





            }
        catch(Exception e){
            System.out.println(e.getMessage());
        }


    }

    private void saveCSV(){
        try {
            String filename = "src/main/resources/com/example/demoproject/filename.txt";

            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("id,name,surname,email,group\n");

            for(Student s: this.students){
                myWriter.write(s + "\n");
            }


            myWriter.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void saveJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        String filename = "src/main/resources/com/example/demoproject/data.json";

        try {
            // Convert Java object to JSON and write to a file
            objectMapper.writeValue(new File(filename)
                    , students);
            System.out.println("JSON file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        String filename = "src/main/resources/com/example/demoproject/data.json";
        try {
            // Read JSON file into Student array
            Student[] studentsArray = objectMapper.readValue(new File(filename), Student[].class);

            // Convert array to List
            Collections.addAll(students, studentsArray);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onSave(ActionEvent event) {

        this.saveCSV();
        this.saveJSON();

    }

}
