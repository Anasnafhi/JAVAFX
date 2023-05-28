package com.example.demo2;

import com.example.demo2.entities.Computer;
import com.example.demo2.service.ComputerService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.Date;


public class HelloController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;
    @FXML
    private TextField marqueField;
    @FXML
    private TextField prixField;
    @FXML
    private TextField generationField;
    @FXML
    private TextField typeField;
    @FXML
    private Date anneFabri;

    @FXML
    private TextField ssdField;

    @FXML
    private Label welcomeText;

    private ComputerService computerService;

    public HelloController() {
        // Initialize the Service
        computerService = new ComputerService();
    }
    @FXML
    protected void onHelloButtonClick() {
        //welcomeText.setText("Welcome to JavaFX Application!");
        // Retrieve values from the UI components
        String name = nameField.getText();
        Date dateCreation = anneFabri;
        String marque = marqueField.getText();
        int price = Integer.parseInt(prixField.getText());
        Computer.generations generation = Computer.generations.valueOf(generationField.getText());
        Computer.typePros typePros = Computer.typePros.valueOf(typeField.getText());
        boolean ssd = Boolean.parseBoolean(ssdField.getText());

        // Convert LocalDate to java.util.Date

        // Create a new Equipe object
        Computer computer = new Computer(1,name,marque,price,ssd,dateCreation,generation,typePros);

        // Save the Equipe to the database
        computerService.save(computer);

        // Show a success message
        welcomeText.setText("computer added successfully!");
        // Clear the fields
        clearFields();
    }
    @FXML
    private void onAnnulerButtonClick() {
        // Clear the fields
        clearFields();
    }

    private void clearFields() {
        nameField.clear();
        anneFabri=null;
        ssdField.clear();
        prixField.clear();
        generationField.clear();
        typeField.clear();

        marqueField.clear();



    }
}