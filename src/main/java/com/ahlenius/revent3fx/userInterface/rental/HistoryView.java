package com.ahlenius.revent3fx.userInterface.rental;

import com.ahlenius.revent3fx.entity.Rental;
import com.ahlenius.revent3fx.service.RentalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class HistoryView {

    private RentalService rentalService;
    private BorderPane historyPane = new BorderPane();
    private VBox historyViewBox = new VBox();
    private VBox memberHistoryBox = new VBox();
    private Button viewHistBtn = new Button();
    private Button memberHistBtn = new Button();

    public HistoryView() {
    }

    public HistoryView(RentalService rentalService) {
        this.rentalService = rentalService;

        // Vänstrafältet
        VBox leftBox = new VBox();
        viewHistBtn.setText("Uthyrningshistorik");
        memberHistBtn.setText("Historik - Medlemsspecifik");
        leftBox.setPadding(new Insets(15, 15, 5, 10));
        leftBox.setSpacing(10);
        leftBox.getChildren().addAll(viewHistBtn, memberHistBtn);

        // Genrell historik
        Label headerHistory = new Label("Uthyrningshistorik");
        historyViewBox.setAlignment(Pos.CENTER);
        historyViewBox.setSpacing(10);
        ObservableList<Rental> rentalHistoryObsList = FXCollections.observableList(rentalService.getReturnedRentalList());
        TableView<Rental> allHistoryView = new TableView<>(rentalHistoryObsList);
        TableColumn<Rental, String> rentalNameCol = new TableColumn<>("Medlem");
        rentalNameCol.setCellValueFactory(new PropertyValueFactory<>("member"));
        TableColumn<Rental, String> rentalItemCol = new TableColumn<>("Varukategori");
        rentalItemCol.setCellValueFactory(new PropertyValueFactory<>("rentalType"));
        TableColumn<Rental, String> startRentCol = new TableColumn<>("Uthyrd from. datum");
        startRentCol.setCellValueFactory(new PropertyValueFactory<>("startOfRent"));
        TableColumn<Rental, String> daysRentedCol = new TableColumn<>("Hyresdagar");
        daysRentedCol.setCellValueFactory(new PropertyValueFactory<>("rentDays"));
        allHistoryView.getColumns().setAll(rentalNameCol,rentalItemCol,startRentCol,daysRentedCol);

        historyViewBox.getChildren().addAll(headerHistory,allHistoryView);


        // Knappar Layout

        viewHistBtn.setOnAction(actionEvent -> {
            historyPane.setCenter(historyViewBox);
        });

        // Layout HistoryPane
        historyPane.setLeft(leftBox);
        historyPane.setCenter(historyViewBox);
    }

    public Button getMemberHistBtn() {
        return memberHistBtn;
    }
    public Button getViewHistBtn() {
        return viewHistBtn;
    }
    public VBox getMemberHistoryBox() {
        return memberHistoryBox;
    }
    public VBox getHistoryViewBox() {
        return historyViewBox;
    }
    public BorderPane getHistoryPane() {
        return historyPane;
    }
}