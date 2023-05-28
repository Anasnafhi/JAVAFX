package com.example.demo2;


import com.example.demo2.entities.Computer;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ComputerTableUtil {

    public static TableColumn<Computer, Integer> getIdColumn() {
        TableColumn<Computer, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        return idColumn;
    }

    public static TableColumn<Computer, String> getNomColumn() {
        TableColumn<Computer, String> nomColumn = new TableColumn<>("name");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        return nomColumn;
    }

    public static TableColumn<Computer, Date> getDateCreationColumn() {
        TableColumn<Computer, Date> dateCreationColumn = new TableColumn<>("anneFabri");
        dateCreationColumn.setCellValueFactory(new PropertyValueFactory<>("anneFabri"));
        dateCreationColumn.setCellFactory(column -> new TableCell<Computer, Date>() {
            private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(dateFormat.format(date));
                }
            }
        });
        return dateCreationColumn;
    }

    public static TableColumn<Computer, String> getMarqueColumn() {
        TableColumn<Computer, String> marqueColumn = new TableColumn<>("marque");
        marqueColumn.setCellValueFactory(new PropertyValueFactory<>("marque"));
        return marqueColumn; }


        public static TableColumn<Computer, String> getPrixColumn() {
            TableColumn<Computer, String> prixColumn = new TableColumn<>("prix");
            prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
            return prixColumn;
        }

            public static TableColumn<Computer, String> getGenerationColumn(){
                TableColumn<Computer, String> generationColumn = new TableColumn<>("generation");
                generationColumn.setCellValueFactory(new PropertyValueFactory<>("generation"));
                return generationColumn;
            }

                public static TableColumn<Computer, String> getTypeColumn() {
                    TableColumn<Computer, String> typeColumn = new TableColumn<>("typeProcesor");
                    typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeProcesor"));
                    return typeColumn;
    }
                public static TableColumn<Computer, String> getSsdColumn() {
                    TableColumn<Computer, String> ssdColumn = new TableColumn<>("ssd");
                    ssdColumn.setCellValueFactory(new PropertyValueFactory<>("ssd"));
                    return ssdColumn;
                }

    public static TableColumn<Computer, Void> getActionsColumn(TableController controller) {
        TableColumn<Computer, Void> actionsColumn = new TableColumn<>("Actions");

        actionsColumn.setCellFactory(column -> {
            return new TableCell<>() {
                private final Button deleteButton = new Button("Delete");
                private final Button updateButton = new Button("Update");

                {
                    deleteButton.setOnAction(event -> {
                        Computer computer = getTableRow().getItem();
                        if (computer != null) {
                            controller.deleteComputer(computer);
                            getTableView().getItems().remove(computer);
                        }
                    });


                    updateButton.setOnAction(event -> {
                        Computer computer = getTableRow().getItem();
                        if (computer != null) {
                            controller.showUpdateForm(computer);
                        }
                    });

                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(new HBox(deleteButton, updateButton));
                    }
                }
            };
        });

        return actionsColumn;
    }

}
