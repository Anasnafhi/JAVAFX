package com.example.demo2;

import com.example.demo2.entities.Computer;
import com.example.demo2.service.ComputerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;


public class ComputerController {
    @FXML
    private TableView<Computer> computerTable;

    @FXML
    private TableColumn<Computer, Integer> idColumn;

    @FXML
    private TableColumn<Computer, String> nameColumn;

    @FXML
    private TableColumn<Computer, String> marqueColumn;
    @FXML
    private TableColumn<Computer, String> prixColumn;

    @FXML
    private TableColumn<Computer, String> generationColumn;
    @FXML
    private TableColumn<Computer, String> typeProsColumn;
    @FXML
    private TableColumn<Computer, String> ssdColumn;
    @FXML
    private TableColumn<Computer, String> anneFabriColumn;


    private ComputerService computerService;



    public ComputerController() {
        // Initialize the computerService
        computerService = new ComputerService();
    }

    @FXML
    private void initialize() {
        // Configure table columns to display Computer properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        marqueColumn.setCellValueFactory(new PropertyValueFactory<>("marque"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ssdColumn.setCellValueFactory(new PropertyValueFactory<>("ssd"));

        anneFabriColumn.setCellValueFactory(new PropertyValueFactory<>("Annee Fabrication"));
        generationColumn.setCellValueFactory(new PropertyValueFactory<>("generation"));
        typeProsColumn.setCellValueFactory(new PropertyValueFactory<>("type"));



        List<Computer> computerList = computerService.findAll();
        ObservableList<Computer> computerObservableList = FXCollections.observableArrayList(computerList);
        computerTable.setItems(computerObservableList);
    }




}
