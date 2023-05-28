package com.example.demo2;


import com.example.demo2.entities.Computer;
import com.example.demo2.service.ComputerService;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditComputerFormController {

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
    private DatePicker anneFabri;

    @FXML
    private TextField ssdField;

    private Computer computer;
    ComputerService computerService = new ComputerService();

    public void setComputer(Computer computer) {
        this.computer = computer;

        // Set the values in the form
        idField.setText(String.valueOf(computer.getId()));
        nameField.setText(computer.getName());
        marqueField.setText(computer.getMarque());
        prixField.setText(String.valueOf(computer.getPrix()));
        generationField.setText(String.valueOf(computer.getGeneration()));
        typeField.setText(String.valueOf(computer.getTypeProcesor()));
        anneFabri.setValue(LocalDate.parse(computer.getAnneFabri().toString()));
        ssdField.setText(String.valueOf(computer.isSsd()));
    }

    @FXML
    public void updateComputer() {
        // Get the updated values from the form
        int id= Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String marque = marqueField.getText();
        int price = Integer.parseInt(prixField.getText());
        Computer.generations generation = Computer.generations.valueOf(generationField.getText());
        Computer.typePros typePros = Computer.typePros.valueOf(typeField.getText());
        boolean ssd = Boolean.parseBoolean(ssdField.getText());
        LocalDate dateFabric =anneFabri.getValue() ;


        // Convert LocalDate to java.util.Date
                java.util.Date dateCreationFormatted = java.sql.Date.valueOf(dateFabric);

        // Create a new Computer object
        Computer computer = new Computer(id,name,marque,price,ssd,dateCreationFormatted,generation,typePros);


        // Call the update method to update the computer in the database
        computerService.update(computer);

        // Close the update form
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void close() {
        // Get the reference to the main application window and close it
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }

}
