package com.ahlenius.revent3fx.userInterface.rental;

import com.ahlenius.revent3fx.entity.*;
import com.ahlenius.revent3fx.service.ItemService;
import com.ahlenius.revent3fx.service.MemberService;
import com.ahlenius.revent3fx.service.RentalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.math.BigDecimal;


public class RentalView {

    //Här hanteras bokning och återlämning
    private RentalService rentalService;
    private MemberService memberService;
    private ItemService itemService;

    private BorderPane rentalPane = new BorderPane();
    VBox prodViewBox;
    private VBox newRentalBox = new VBox();
    private VBox endRentalBox = new VBox();
    private Button viewProd = new Button();
    private Button newRental = new Button();
    private Button endRental = new Button();
    private Button OKBTN = new Button("OK");
    private Label exceptionInfo = new Label();
    private Member foundRentingMem;
    private Rental tempRental;
    private int days;
    ToggleGroup radioGroup;

    public RentalView(){}

    public RentalView (RentalService rentalService, MemberService memberService, ItemService itemService) {
        this.rentalService = rentalService;
        this.memberService = memberService;
        this.itemService = itemService;

        // Vänstrafältet
        VBox leftBox = new VBox();
        viewProd.setText("Akutella produkter");
        newRental.setText("Ny uthyrning");
        endRental.setText("Avsluta uthyrning");
        leftBox.setPadding(new Insets(15, 15, 5, 10));
        leftBox.setSpacing(10);
        leftBox.getChildren().addAll(viewProd, newRental, endRental);

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
        Label headerNewRental = new Label("Ny uthyrning");
        newRentalBox.setAlignment(Pos.CENTER);
        newRentalBox.setSpacing(10);
        Label confrimationText = new Label();
        GridPane newRentalPane = new GridPane();
        Label memName = new Label("Namn på hyrande medlem: ");
        Label categoryLabel= new Label("Välj kategori: ");
        Label rentalProd = new Label("Välj produkt: ");
        Label rentFromDate = new Label("Startdatum: ");
        Label daysOfRent = new Label("Hur många dagar önskas hyra?");
        TextField rentalMemField = new TextField();
        TextField daysOfRentField = new TextField();
        daysOfRentField.setPromptText("tex. 5");
        daysOfRentField.setMaxWidth(250);
        rentalMemField.setMaxWidth(250);
        rentalMemField.setPromptText("tex. Kickan Kristersson");
        ObservableList<Member> memberObsList = FXCollections.observableArrayList(memberService.findAllMembers());
        ComboBox<Member> memberComboBox = new ComboBox<>(memberObsList);
        VBox radioBtnBox = new VBox();
        radioGroup = new ToggleGroup();
        RadioButton discobtn = new RadioButton("Disco");
        RadioButton costumebtn = new RadioButton("Dräkt");
        RadioButton bouncybtn = new RadioButton("Hoppborg");
        discobtn.setToggleGroup(radioGroup);
        costumebtn.setToggleGroup(radioGroup);
        bouncybtn.setToggleGroup(radioGroup);
        radioBtnBox.setPadding(new Insets(5,5,5,5));
        radioBtnBox.getChildren().setAll(bouncybtn,costumebtn,discobtn);
        // sedan uppdateras listan för produkter?
        ComboBox<BouncyCastle> availableBCItem = new ComboBox<>();
        availableBCItem.setMaxWidth(250);
        TextField fromDateField = new TextField();
        fromDateField.setPromptText("Använd format: ÅÅÅÅ-MM-DD");
        fromDateField.setMaxWidth(250);
        newRentalPane.add(memName, 0, 0);
        newRentalPane.add(rentalMemField, 1, 0);
        newRentalPane.add(categoryLabel, 0, 1);
        newRentalPane.add(radioBtnBox, 1, 1);
        //newRentalPane.add(rentalProd, 0, 1);
        //newRentalPane.add(availableItem, 1, 1);
        newRentalPane.add(daysOfRent, 0, 2);
        newRentalPane.add(daysOfRentField, 1, 2);
        newRentalPane.add(rentFromDate, 0, 3);
        newRentalPane.add(fromDateField, 1, 3);
        newRentalPane.add(OKBTN, 2, 4);
        newRentalPane.add(confrimationText, 0, 5);
        newRentalPane.add(exceptionInfo, 0, 6);
        newRentalPane.setVgap(5);
        newRentalPane.setHgap(5);
        newRentalPane.setAlignment(Pos.CENTER);
        newRentalPane.setAlignment(Pos.CENTER);
        newRentalBox.getChildren().addAll(headerNewRental, newRentalPane);
/*
        // Avsluta uthyrning
        Label headerCloseRental = new Label("Avsluta uthyrning");
        endRentalBox.setAlignment(Pos.TOP_CENTER);
        endRentalBox.setSpacing(10);
        endRentalBox.setPadding(new Insets(35, 15, 15, 15));
        Label rentalChoice = new Label("Välj bland aktuella uthyrningar: ");
        ComboBox<Rental> rentingMemberComboBox = new ComboBox<>(rentalService.rentalsObsListNotReturned(rentalService.getRentalRegistry().getRentalObsList()));
        memberComboBox.getItems().addAll();
        Button confirmRentMem = new Button("Välj uthyrning");
        endRentalBox.getChildren().addAll(headerCloseRental, rentalChoice, rentingMemberComboBox, confirmRentMem);

        Alert confEndRent = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType endRentBtn = new ButtonType("Ja, avsluta");
        ButtonType closeConfAlertBtn = new ButtonType("Avbryt");
        confEndRent.getButtonTypes().setAll(endRentBtn, closeConfAlertBtn);
        confEndRent.setTitle("Avsluta Uthyrning");
        confEndRent.setHeaderText("Säker på att du vill avsluta uthyrning?");



        // Steg 2 - Avsluta uthyrning
        // Ta in vald rental - sett ett slut datum ändra returned till true
        VBox finalEndRentBox = new VBox();
        finalEndRentBox.setAlignment(Pos.CENTER);
        finalEndRentBox.setSpacing(5);
        Label validateEndRent = new Label();
        Label endDateOfRent = new Label("Återlämningsdatum: ");
        TextField endDateField = new TextField("2025-12-31");
        endDateField.setMaxWidth(250);
        Button confEndRentBtn = new Button("Bekräfta avslut");
        Label exceptionEndRent = new Label();
        finalEndRentBox.getChildren().addAll(headerCloseRental, validateEndRent, endDateOfRent, endDateField, confEndRentBtn, exceptionEndRent);

         // Steg 3 - Räkna ihop uthyrning. Dagar och kostnad.
        VBox finnishedRentingBox = new VBox();
        finnishedRentingBox.setSpacing(10);
        finnishedRentingBox.setAlignment(Pos.CENTER);
        Label headerRentingInfo = new Label("Uthyrning avslutad.");
        GridPane rentingSumPane= new GridPane();
        rentingSumPane.setHgap(7);
        rentingSumPane.setVgap(7);
        rentingSumPane.setAlignment(Pos.CENTER);
        Label rentingDays = new Label("Dagar uthyrd: ");
        Label rentingCost = new Label("Totalkostnad: ");
        Label rentalDays = new Label("0");
        Label rentalCostSum = new Label("kr");
        rentingSumPane.add(rentingDays, 0,0);
        rentingSumPane.add(rentingCost,0,1);
        rentingSumPane.add(rentalDays,1,0);
        rentingSumPane.add(rentalCostSum,1,1);

        finnishedRentingBox.getChildren().addAll(headerRentingInfo,rentingSumPane);
*/
        // Knappar Layout
        viewProd.setOnAction(actionEvent -> {
  //          itemListTableView.refresh();
            rentalPane.setCenter(prodViewBox);
        });
        newRental.setOnAction(actionEvent -> {
            rentalPane.setCenter(newRentalBox);
            rentalMemField.clear();
            daysOfRentField.clear();
            fromDateField.clear();
            exceptionInfo.setText("");
    //        exceptionEndRent.setText("");
        });
        endRental.setOnAction(actionEvent -> {
            rentalPane.setCenter(endRentalBox);
      //      exceptionEndRent.setText("");
        });
        // Knappar funktioner
/*
        // Ny uthyrning
        OKBTN.setOnAction(actionEvent -> {
            try {
                days = Integer.parseInt(daysOfRentField.getText());
            } catch (NumberFormatException e) {
                exceptionInfo.setText("Missat antal dagar. Skriv ett ungefärligt antal dar.");
            }
           LocalDate dateStart = rentalService.createDateOfRent(fromDateField.getText());
            try {
                foundRentingMem = memberService.searchMemberByNameOrPhoneReturnMember(rentalMemField.getText());
            } catch (NullPointerException e) {
                exceptionInfo.setText(e.getMessage() + ".\n Namnet behöver ha den exakta stavningen.");}
            if(foundRentingMem != null){
            try {
                Rental newestRental = rentalService.newRental(foundRentingMem, availableItem.getValue(),days,String.valueOf(dateStart));
                Item rentedItem = newestRental.getRentalItem();
                rentalService.getInventory().getItemList().stream().filter(item -> item.equals(rentedItem))
                        .forEach(item -> item.setAvailable(false));
                confrimationText.setText("Ny uthyrning skapad.\n" + newestRental);
                foundRentingMem = null;
                rentalMemField.clear();
                daysOfRentField.clear();
                fromDateField.clear();
                exceptionInfo.setText("");

            } catch (IOException | InvalidAmountRentingDaysException | InvalidDateChoiceException |
                     InvalidRentalInfoInputException e) {exceptionInfo.setText(e.getMessage());
            }
        }});

        // Avsluta uthyrning
        confirmRentMem.setOnAction(actionEvent -> {
            tempRental = rentingMemberComboBox.getValue();

            confEndRent.setContentText("Vill du avsluta uthyrningen av " + tempRental.getRentalItem().getName() + " till " + tempRental.getRentingMember().getName() + " ?");
            Optional<ButtonType> userEndingRentResult = confEndRent.showAndWait();
            if (userEndingRentResult.isPresent()) {
                if (userEndingRentResult.get() == endRentBtn) {
                    rentalPane.setCenter(finalEndRentBox);}
                if (userEndingRentResult.get() == closeConfAlertBtn) {
                    exceptionEndRent.setText("Avbryter återlämning. Produkt fortfarande uthyrd.");
                }
            }
        });
        confEndRentBtn.setOnAction(actionE -> {
            tempRental.setReturned(true);
            tempRental.getRentalItem().setAvailable(true);
                LocalDate dateStopRent = rentalService.userChooseDate(endDateField.getText()); // Nån exception här så att det stoppar ett felaktigt datum intryck?
                rentalService.countActualDays(dateStopRent,tempRental);
                rentalPane.setCenter(finnishedRentingBox);
                String days = String.valueOf(rentalService.rentalCountDays(tempRental));
                String price = rentalService.pricePolicyCalc(tempRental);
               rentalDays.setText(days);
               rentalCostSum.setText(price);
            try{
                                }catch(IOException e){System.out.println("Feluppstod vid sparande till uthyrningsfil.");}
            tempRental = null;
        }); */

        // Layout RentalPane
        rentalPane.setLeft(leftBox);
        rentalPane.setCenter(prodViewBox);
    }//Konstruktor slut

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

