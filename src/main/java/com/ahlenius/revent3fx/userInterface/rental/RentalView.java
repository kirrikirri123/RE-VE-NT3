package com.ahlenius.revent3fx.userInterface.rental;

import com.ahlenius.revent3fx.entity.*;
import com.ahlenius.revent3fx.service.ItemService;
import com.ahlenius.revent3fx.service.MemberService;
import com.ahlenius.revent3fx.service.RentalService;
import com.ahlenius.revent3fx.util.TypeList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


import java.time.LocalDate;


public class RentalView {

    //Här hanteras bokning och återlämning
    private RentalService rentalService;
    private MemberService memberService;
    private ItemService itemService;
    private final TypeList typeList = new TypeList();
    final BorderPane rentalPane = new BorderPane();
    VBox prodViewBox;
    VBox newRentalBox = new VBox();
    VBox endRentalBox = new VBox();
    VBox finalEndRentBox;
    VBox finnishedRentingBox;
    GridPane newRentalPane;
    Button viewProd = new Button();
    Button newRental = new Button();
    Button endRental = new Button();
    Button updItemList = new Button();
    Button cateChoiceBtn;
    Button OKBTN = new Button("OK");
    Button confirmRentMem;
    Button confEndRentBtn;
    ButtonType endRentBtn;
    ButtonType closeConfAlertBtn;
    Label exceptionInfo = new Label();
    Label rentalProd;
    Label confrimationText;
    Label exceptionEndRent;
    Label rentalCostSum;
    Label rentalDays;
    Rental tempRental;
    int days;
    RadioButton discobtn;
    RadioButton costumebtn;
    RadioButton bouncybtn;
    Alert confEndRent;
    ComboBox<RentalType>rentalTypeComboBox;
    ComboBox<BouncyCastle> availableBCItem;
    ComboBox<Costume> availableMCItem;
    ComboBox<DiscoMachine> availableDMItem;
    ComboBox<Member> memberComboBox;
    ComboBox<Rental> rentingMemberComboBox;
    TextField daysOfRentField;
    DatePicker datePicker;
    DatePicker endDatePicker;
    Rental rental;

    public RentalView(){}

    public RentalView(ItemService itemService,MemberService memberService, RentalService rentalservice) {
        this.itemService = itemService;
        this.memberService = memberService;
        this.rentalService = rentalservice;


    // Vänstrafältet
        VBox leftBox = new VBox();
        viewProd.setText("Akutella produkter");
        newRental.setText("Ny uthyrning");
        endRental.setText("Avsluta uthyrning");
        updItemList.setText("Uppdatera produktlistor");
        leftBox.setPadding(new Insets(15, 15, 5, 10));
        leftBox.setSpacing(10);
        leftBox.getChildren().addAll(viewProd, newRental, endRental,updItemList);

        // Aktuella produkter. TabelPane
        prodViewBox = new VBox();
        Label headerViewProd = new Label("Aktuella produkter för uthyrning: ");
        //hoppb
        ObservableList<BouncyCastle> obsListBouncy = FXCollections.observableArrayList(itemService.returnListBouncyItem());
        TableView<BouncyCastle> bouncyCastleTableView = new TableView<>();
        bouncyCastleTableView.setItems(obsListBouncy);
        TableColumn<BouncyCastle, String> bouncyNameCol = new TableColumn<>("Hoppborgar");
        bouncyNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        TableColumn<BouncyCastle, String> bouncyDescriptCol = new TableColumn<>("Info");
        bouncyDescriptCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<BouncyCastle, String> bouncyDayPriceCol = new TableColumn<>("Dagspris i SEK. ex.moms");
        bouncyDayPriceCol.setCellValueFactory(new PropertyValueFactory<>("dayPrice"));
        bouncyCastleTableView.getColumns().setAll(bouncyNameCol, bouncyDescriptCol, bouncyDayPriceCol);
        //dräkter
        ObservableList<Costume> obsListCostume = FXCollections.observableArrayList(itemService.returnListCostumeItem());
        TableView<Costume> costumeTableView = new TableView<>();
        costumeTableView.setItems(obsListCostume);
        TableColumn<Costume,String> costNameCol = new TableColumn<>("Dräkter");
        costNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        TableColumn<Costume, String> costDescriptCol = new TableColumn<>("Info");
        costDescriptCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<Costume,String > costDayPriceCol = new TableColumn<>("Dagspris i SEK. ex.moms");
        costDayPriceCol.setCellValueFactory(new PropertyValueFactory<>("dayPrice"));
        costumeTableView.getColumns().setAll(costNameCol, costDescriptCol, costDayPriceCol);
        //disco
        ObservableList<DiscoMachine> obsListDisco = FXCollections.observableArrayList(itemService.returnListDiscoItem());
        TableView<DiscoMachine> discoTableView = new TableView<>();
        discoTableView.setItems(obsListDisco);
        TableColumn<DiscoMachine, String> discoNameCol = new TableColumn<>("Disco-disco");
        discoNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        TableColumn<DiscoMachine, String> discoDescriptCol = new TableColumn<>("Info");
        discoDescriptCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<DiscoMachine, String> discoDayPriceCol = new TableColumn<>("Dagspris i SEK. ex.moms");
        discoDayPriceCol.setCellValueFactory(new PropertyValueFactory<>("dayPrice"));
        discoTableView.getColumns().setAll(discoNameCol, discoDescriptCol, discoDayPriceCol);

        prodViewBox.getChildren().addAll(headerViewProd,bouncyCastleTableView,costumeTableView,discoTableView);
        prodViewBox.setSpacing(15);
        prodViewBox.setPadding(new Insets(25, 10, 10, 10));

        // Ny uthyrning
        typeList.listOfRentalType();
        Label headerNewRental = new Label("Ny uthyrning");
        newRentalBox.setAlignment(Pos.CENTER);
        newRentalBox.setSpacing(10);
        confrimationText = new Label();
        newRentalPane = new GridPane();
        Label memName = new Label("Namn på hyrande medlem: ");
        Label categoryLabel= new Label("Kategori: ");
        cateChoiceBtn = new Button("Lås vald kategori");
        rentalProd = new Label("Välj produkt: ");
        Label rentFromDate = new Label("Startdatum: ");
        Label daysOfRent = new Label("Hur många dagar önskas hyra?");
        daysOfRentField = new TextField();
        daysOfRentField.setPromptText("tex. 5");
        daysOfRentField.setMaxWidth(250);
        ObservableList<Member> memberObsList = FXCollections.observableArrayList(memberService.findAllMembers());
        memberComboBox = new ComboBox<>(memberObsList);
        ObservableList<RentalType> rentalTypeObsList = FXCollections.observableArrayList(typeList.getRentalInString());
        rentalTypeComboBox = new ComboBox<>(rentalTypeObsList);
        availableBCItem = new ComboBox<>(obsListBouncy);
        availableMCItem = new ComboBox<>(obsListCostume);
        availableDMItem = new ComboBox<>(obsListDisco);
        availableBCItem.setMaxWidth(250);
        datePicker = new DatePicker(LocalDate.of(2026,2,9));
        newRentalPane.add(memName, 0, 0);
        newRentalPane.add(memberComboBox, 1, 0);
        newRentalPane.add(categoryLabel, 0, 1);
        newRentalPane.add(rentalTypeComboBox, 1, 1);
        newRentalPane.add(cateChoiceBtn,2,1);
        newRentalPane.add(rentFromDate, 0, 3);
        newRentalPane.add(datePicker, 1, 3);
        newRentalPane.add(daysOfRent, 0, 4);
        newRentalPane.add(daysOfRentField, 1, 4);
        newRentalPane.add(OKBTN, 2, 5);
        newRentalPane.add(confrimationText, 0, 6);
        newRentalPane.add(exceptionInfo, 0, 7);
        newRentalPane.setVgap(8);
        newRentalPane.setHgap(5);
        newRentalPane.setAlignment(Pos.CENTER);
        newRentalPane.setAlignment(Pos.CENTER);
        newRentalBox.getChildren().addAll(headerNewRental, newRentalPane);

        // Avsluta uthyrning
        Label headerCloseRental = new Label("Avsluta uthyrning");
        endRentalBox.setAlignment(Pos.TOP_CENTER);
        endRentalBox.setSpacing(10);
        endRentalBox.setPadding(new Insets(35, 15, 15, 15));
        Label rentalChoice = new Label("Välj bland aktuella uthyrningar: ");
        ObservableList<Rental> rentalsObsList = FXCollections.observableArrayList(rentalService.getNotReturnedRentalList());
        rentingMemberComboBox = new ComboBox<>(rentalsObsList);
        memberComboBox.getItems().addAll();
        confirmRentMem = new Button("Välj uthyrning");
        endRentalBox.getChildren().addAll(headerCloseRental, rentalChoice, rentingMemberComboBox, confirmRentMem);

        confEndRent = new Alert(Alert.AlertType.CONFIRMATION);
        endRentBtn = new ButtonType("Ja, avsluta");
        closeConfAlertBtn = new ButtonType("Avbryt");
        confEndRent.getButtonTypes().setAll(endRentBtn, closeConfAlertBtn);
        confEndRent.setTitle("Avsluta Uthyrning");
        confEndRent.setHeaderText("Säker på att du vill avsluta uthyrning?");

        // Steg 2 - Avsluta uthyrning
        // Ta in vald rental - sett ett slut datum ändra returned till true
        finalEndRentBox = new VBox();
        finalEndRentBox.setAlignment(Pos.CENTER);
        finalEndRentBox.setSpacing(5);
        Label validateEndRent = new Label();
        Label endDateOfRent = new Label("Återlämningsdatum: ");
        endDatePicker = new DatePicker(LocalDate.of(2026,3,1));
        confEndRentBtn = new Button("Bekräfta avslut");
        exceptionEndRent = new Label();
        finalEndRentBox.getChildren().addAll(headerCloseRental, validateEndRent, endDateOfRent, endDatePicker, confEndRentBtn, exceptionEndRent);

         // Steg 3 - Räkna ihop uthyrning. Dagar och kostnad.
        finnishedRentingBox = new VBox();
        finnishedRentingBox.setSpacing(10);
        finnishedRentingBox.setAlignment(Pos.CENTER);
        Label headerRentingInfo = new Label("Uthyrning avslutad.");
        GridPane rentingSumPane= new GridPane();
        rentingSumPane.setHgap(7);
        rentingSumPane.setVgap(7);
        rentingSumPane.setAlignment(Pos.CENTER);
        Label rentingDays = new Label("Dagar uthyrd: ");
        Label rentingCost = new Label("Totalkostnad: ");
        rentalDays = new Label("0");
        rentalCostSum = new Label("kr");
        rentingSumPane.add(rentingDays, 0,0);
        rentingSumPane.add(rentingCost,0,1);
        rentingSumPane.add(rentalDays,1,0);
        rentingSumPane.add(rentalCostSum,1,1);

        finnishedRentingBox.getChildren().addAll(headerRentingInfo,rentingSumPane);

        // Knappar i sidoMeny
        viewProd.setOnAction(actionEvent -> {
            rentalPane.setCenter(prodViewBox);
        });
        newRental.setOnAction(actionEvent -> {
            rentalPane.setCenter(newRentalBox);
            daysOfRentField.clear();
            exceptionInfo.setText("");
            exceptionEndRent.setText("");
        });
        endRental.setOnAction(actionEvent -> {
            rentalPane.setCenter(endRentalBox);
            rentalsObsList.setAll(rentalService.getNotReturnedRentalList());
            exceptionEndRent.setText("");
        });
        updItemList.setOnAction( actionEvent ->{
            obsListDisco.setAll(itemService.returnListDiscoItem());
                    obsListBouncy.setAll(itemService.returnListBouncyItem());
                        obsListCostume.setAll(itemService.returnListCostumeItem());
                            }

        );
        cateChoiceBtn.setOnAction(actionEvent -> {
            cateChoiceBtn.setDisable(true);
            switch(rentalTypeComboBox.getValue()){
                case BOUNCYCASTLE -> newRentalPane.add(availableBCItem, 1, 2);
                case MASCOTECOSTUME -> newRentalPane.add(availableMCItem, 1, 2);
                case DISCOMACHINE -> newRentalPane.add(availableDMItem, 1, 2);}

        });

        // Layout RentalPane
        rentalPane.setLeft(leftBox);
        rentalPane.setCenter(prodViewBox);
    }

    public VBox getEndRentalBox () {
        return endRentalBox;
    }
                public VBox getNewRentalBox () {
                    return newRentalBox;
                }
                public VBox getProdViewBox () {
                    return prodViewBox;
                }
                public Button getEndRental () {
                    return endRental;
                }
                public Button getNewRental () {
                    return newRental;
                }
                public Button getViewProd () {
                    return viewProd;
                }
                public BorderPane getRentalPane () {
                    return rentalPane;
                }
            }

