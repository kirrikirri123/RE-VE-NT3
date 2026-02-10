package com.ahlenius.revent3fx.userInterface.rental;

import com.ahlenius.revent3fx.service.RentalService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class EconomyView {
    final BorderPane economyPane = new BorderPane();
    final Label sum = new Label();
    Button incomBtn;

    public EconomyView(){

            VBox economyBox = new VBox();
            economyBox.setSpacing(10);
            economyBox.setAlignment(Pos.CENTER);
            economyBox.setPadding(new Insets(25,35,25,35));
            Label headerEconomy = new Label("Ekonomi");
            headerEconomy.getStyleClass().add("title");
            Label businessToDay = new Label("Totala intäkter på avslutade affärer är: ");
            incomBtn = new Button("Beräkna");
            GridPane economyGPane = new GridPane();
            economyGPane.add(businessToDay,0,0);
            economyGPane.add(sum,1,0);
            economyGPane.setAlignment(Pos.CENTER);
            economyGPane.setVgap(5);
            economyBox.getChildren().addAll(headerEconomy,incomBtn,economyGPane);
            economyPane.setCenter(economyBox);
        }

    public BorderPane getEconomyPane() {
        return economyPane;
    }
}
