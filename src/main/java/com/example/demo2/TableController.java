package com.example.demo2;

import com.example.demo2.dao.ComputerDao;
import com.example.demo2.dao.impl.ComputerDaoImp;
import com.example.demo2.entities.Computer;

import com.example.demo2.service.ComputerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public class TableController {
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
     private DatePicker anneFabri ;
    @FXML
    private TextField ssdField;
    @FXML
    private GridPane newComputerDataPane;

    @FXML
    private TableView<Computer> table;

    private ObservableList<Computer> computerList;
    private ComputerDao computerDao = new ComputerDaoImp();
    private ComputerService computerService;

    public TableController() {
        // Initialize the ComputerService
        computerService = new ComputerService();
    }

    public void initialize() {
        // Add columns to the TableView
        table.getColumns().addAll(
                ComputerTableUtil.getIdColumn(),
                ComputerTableUtil.getNomColumn(),
                ComputerTableUtil.getDateCreationColumn(),
                ComputerTableUtil.getMarqueColumn(),
                ComputerTableUtil.getGenerationColumn(),
                ComputerTableUtil.getTypeColumn(),
                ComputerTableUtil.getPrixColumn(),
                ComputerTableUtil.getSsdColumn()

                );
        table.getColumns().add(ComputerTableUtil.getActionsColumn(this));

        // Initialize the ObservableList
        computerList = FXCollections.observableArrayList();

        // Load data into the table
        System.out.println(computerService.findAll());
        computerList.addAll(computerService.findAll());
        table.setItems(computerList);

        // Start monitoring for database changes
        startDatabaseMonitoring();
    }

    private void startDatabaseMonitoring() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Adjust the time interval as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Retrieve updated data from the database
                List<Computer> updatedComputerList = computerService.findAll();

                // Update the ObservableList on the JavaFX Application Thread
                table.getItems().clear();
                table.getItems().addAll(updatedComputerList);
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    public void Importer() {
        try {
            computerService.importerDepuisExcel("C:\\Users\\Yassine\\Desktop\\TpJavaFX\\src\\main\\resources\\dataimporte.xls");
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Exporter() {
        computerService.exporterVersExcel("C:\\Users\\Yassine\\Desktop\\TpJavaFX\\src\\main\\resources\\dataexporte.xls");
        System.out.println("Done");
    }

    @FXML
    public void addComputer() throws ParseException {
        String name = nameField.getText();
        String marque = marqueField.getText();
        Integer price = Integer.parseInt(prixField.getText());
        Computer.generations generation = Computer.generations.valueOf(generationField.getText());
        Computer.typePros typePros = Computer.typePros.valueOf(typeField.getText());
        boolean ssd = Boolean.parseBoolean(ssdField.getText());
        LocalDate date = anneFabri.getValue();


        // Convert LocalDate to java.util.Date
        java.util.Date dateCreationFormatted = java.sql.Date.valueOf(date);

        // Create a new Computer object
        Computer computer = new Computer( name, marque, price, ssd, dateCreationFormatted, generation, typePros);

        // Save the Computer to the database
        computerService.save(computer);

        // Add the Computer to the ObservableList
        computerList.add(computer);

        // Clear the fields
        clearFields();
    }

    private void clearFields() {
        nameField.clear();
        ssdField.clear();
        prixField.clear();
        generationField.clear();
        typeField.clear();

        marqueField.clear();



    }

    public void deleteComputer(Computer computer) {
        // Remove the computer from the ObservableList
        computerList.remove(computer);

        // Delete the corresponding record from the database
        computerService.remove(computer);
    }

    public void updateComputer(Computer computer) {
        // Handle update logic here
        // ...
    }

    public void showUpdateForm(Computer computer) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_computer_form.fxml"));
        Parent root;
        try {
            root = loader.load();
            EditComputerFormController editController = loader.getController();
            editController.setComputer(computer);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
