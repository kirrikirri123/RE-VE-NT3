package com.ahlenius.revent3fx.userInterface.member;

import com.ahlenius.revent3fx.entity.Rental;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MemberView {
    // Här kommer under meny men olika alternativ till medlemskapshantering.
    private final BorderPane memberPane = new BorderPane();
    private final VBox newMemBox = new VBox();
    private final VBox memHistoryPane = new VBox();
    private final VBox updateMemPane = new VBox();
    private final VBox searchMemPane = new VBox();
    final Button newMem;
    final Button searchMem;
    final Button updateMem;
    final Button historyMem;
    final String searchString = "Sök på emailadress: ";
    final Button OKBTN = new Button("OK");
    final Button searchBtnUpd = new Button("Sök");
    final Button searchBtnMem = new Button("Sök");
    final Button searchBtnHist = new Button("Sök");
    Label exceptionInfo = new Label();
    Label headerText;
    Label confrimationText= new Label();
    Label confirmationSearchMem = new Label();
    TextField userEmail = new TextField();
    TextField userfName = new TextField();
    TextField userlName = new TextField();
    TextField userPhone = new TextField();
    TextField searchMember = new TextField();
    ComboBox<String> statusComboBox;

    //  private Member tempMember;
   // private Member tempHistMember;


    public MemberView(){
         //NY medlemsVy
        headerText = new Label("Skapa ny medlem");
        GridPane newMemPane= new GridPane();
        newMem = new Button("Ny medlem");
        Label fName = new Label("Förnamn / Föreningsnamn ");
        Label lName = new Label("Efternamn.  - Vid föreningskund ange: Förening ");
        Label phone = new Label("Telefonnummer ");
        Label email = new Label("Emailadress ") ;
        Label status = new Label("Välj status: ");
        userlName.maxWidth(250);
        userfName.setPromptText("Kickan / Kickans kexförening");
        userlName.maxWidth(250);
        userlName.setPromptText("Kristersson \nalt. Förening (vid föreningskund)");
        userPhone.maxWidth(250);
        userPhone.setPromptText(" 070 302 48 10");
        userEmail.maxWidth(250);
        userEmail.setPromptText("Bosse.Bengtsson@live.se");
        String statusPi= "Privatperson";
        String statusSo ="Förening";
        String statusEmp = "Kollega";
        ObservableList<String> statusComboList = FXCollections.observableArrayList();
        statusComboList.addAll(statusPi,statusSo,statusEmp);
        statusComboBox = new ComboBox<>(statusComboList);
        newMemPane.add(fName,0,0);
        newMemPane.add(userfName,1,0);
        newMemPane.add(lName,0,1);
        newMemPane.add(userlName,1,1);
        newMemPane.add(phone,0,2);
        newMemPane.add(userPhone,1,2);
        newMemPane.add(email,0,3);
        newMemPane.add(userEmail,1,3);
        newMemPane.add(status,0,4);
        newMemPane.add(statusComboBox,1, 4);
        newMemPane.add(OKBTN,2,4);
        newMemPane.add(confrimationText,1,5);
        newMemPane.add(exceptionInfo,1,6);
        newMemPane.setVgap(5);
        newMemPane.setHgap(5);
        newMemPane.setAlignment(Pos.CENTER);
        newMemBox.setSpacing(10);
        newMemBox.setAlignment(Pos.CENTER);
        newMemBox.getChildren().addAll(headerText,newMemPane);

        //Sök medlemsVy
        searchMem = new Button("Sök medlem");
        Label headerSearch = new Label("Sök befintlig medlem");
        Label searchMemLabel = new Label(searchString);
        searchMember.setMaxWidth(250);
        searchMember.setPromptText("Tex. Bosse.Bengtsson@yahoo.se");
        searchMemPane.setSpacing(10);
        searchMemPane.setAlignment(Pos.CENTER);
        searchMemPane.getChildren().addAll(headerSearch,searchMemLabel,searchMember,searchBtnMem,confirmationSearchMem);

        //HistorikVy
        historyMem = new Button("Medlemshistorik");
        Label headerHistoryMem = new Label("Medlemshistorik");
        Label memberHistLab = new Label(searchString);
        Label exceptionInfoHistory = new Label();
        TextField memberHistory = new TextField();
        memberHistory.setMaxWidth(250);
        memberHistory.setPromptText("Tex. Bosse.Bengtsson@outlook.com");
        Button searchBtnHist = new Button("Sök");
        memHistoryPane.setSpacing(10);
        memHistoryPane.setAlignment(Pos.CENTER);
        memHistoryPane.getChildren().addAll(headerHistoryMem,memberHistLab,memberHistory,searchBtnHist,exceptionInfoHistory);

       /// Steg 2 Visa Historik
        VBox memHistShow = new VBox();
        memHistShow.setSpacing(10);
        memHistShow.setAlignment(Pos.CENTER);
        Label headerShowHist = new Label("Medlemshistorik");
        TableView<Rental> historyTable = new TableView<>();
        TableColumn<Rental, String> rentalNameCol = new TableColumn<Rental, String>("Medlem");
        rentalNameCol.setCellValueFactory(new PropertyValueFactory<>("rentingMember"));
        TableColumn<Rental, String> rentalItemCol = new TableColumn<>("Hyrd vara");
        rentalItemCol.setCellValueFactory(new PropertyValueFactory<>("rentalItem"));
        TableColumn<Rental, String> startRentCol = new TableColumn<>("Uthyrd from. datum");
        startRentCol.setCellValueFactory(new PropertyValueFactory<>("startOfRent"));
        TableColumn<Rental, String> daysRentedCol = new TableColumn<>("Hyresdagar");
        daysRentedCol.setCellValueFactory(new PropertyValueFactory<>("rentDays"));
        historyTable.getColumns().setAll(rentalNameCol,rentalItemCol,startRentCol,daysRentedCol);

        memHistShow.getChildren().addAll(headerShowHist,historyTable);

        //Uppdatera medlemVy
        updateMem = new Button("Uppdatera medlem");
        Label headerUpdate = new Label("Uppdatera medlem");
        Label updateMemLabel = new Label(searchString);
        Label updateMemInfo = new Label();
        TextField updateMember = new TextField();
        updateMember.setMaxWidth(250);
        updateMember.setPromptText("Tex. Bosse.Bengtsson@telia.se");
        updateMemPane.setSpacing(10);
        updateMemPane.setAlignment(Pos.CENTER);
        updateMemPane.getChildren().addAll(headerUpdate,updateMemLabel,updateMember,searchBtnUpd,updateMemInfo);
          Alert confrUpdMem = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType yesBtn = new ButtonType("Ja");
            ButtonType noBtn = new ButtonType("Avbryt");
            confrUpdMem.getButtonTypes().setAll(yesBtn,noBtn);
            confrUpdMem.setTitle("Uppdatera medlem - Validering");
            confrUpdMem.setHeaderText("Vill du uppdatera medlem?");

            // Steg 2 uppdatera medlem.
        VBox updateMemVbox= new VBox();
        Label update2ndView = new Label("Redigering av medlemsinformation");
        Label validatedMem = new Label();
        Label updName = new Label(" Ändra stavning i namn : ");
        Label updPhone = new Label("Ändra telefonnummer : ");
        Label updStatus = new Label("Välj korrekt medlemsstatus ");
        TextField updUserNameField = new TextField();
        userfName.maxWidth(225);
        TextField updUserPhoneField = new TextField();
        userPhone.maxWidth(225);
        ComboBox<String>updUserStatusCombo = new ComboBox<>();
        String privateIndividual = "Privatperson";
        String society = "Förening";
        updUserStatusCombo.getItems().addAll(privateIndividual,society);
        updUserStatusCombo.maxWidth(225);
        Button confBtn = new Button(" Bekräfta ändring ");
        Button removeMemBtn = new Button("Eliminera medlem");
        Label confrmUpdText = new Label();
        Label updMemExceptionInfo = new Label();
        GridPane updMemPane = new GridPane();
        updMemPane.setHgap(5);
        updMemPane.setVgap(5);
        updMemPane.setAlignment(Pos.CENTER);
        updMemPane.add(updName,0,0);
        updMemPane.add(updUserNameField,1,0);
        updMemPane.add(updPhone,0,2);
        updMemPane.add(updUserPhoneField,1,2);
        updMemPane.add(updStatus,0,3);
        updMemPane.add(updUserStatusCombo,1,3);
        updMemPane.add(confBtn,2,4);
        updMemPane.add(confrmUpdText,0,5);
        updMemPane.add(updMemExceptionInfo,0,6);
        updMemPane.add (removeMemBtn,1,7);
        updateMemVbox.setSpacing(15);
        updateMemVbox.setAlignment(Pos.CENTER);
        updateMemVbox.getChildren().addAll(update2ndView,validatedMem,updMemPane);

        Alert confRemoveMem = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType removeBtn = new ButtonType("Avsluta");
        ButtonType noRemoveBtn = new ButtonType("Avbryt");
        confRemoveMem.getButtonTypes().setAll(removeBtn,noRemoveBtn);
        confRemoveMem.setTitle("Avsluta Medlemskap");
        confRemoveMem.setHeaderText("Vill du ta bort medlem ur register?");

        // Vänsterfält
        VBox leftField = new VBox();
        leftField.setPadding(new Insets(15,15,5,10));
        leftField.setSpacing(10);
        leftField.getChildren().addAll(newMem,searchMem,updateMem,historyMem);

        // Knappar Layout
        newMem.setOnAction(actionEvent -> {
            memberPane.setCenter(newMemBox);
        });
        searchMem.setOnAction( actionEvent -> {
            memberPane.setCenter(searchMemPane);
            searchMember.clear();confirmationSearchMem.setText("");
        });
        updateMem.setOnAction(actionEvent -> {
            memberPane.setCenter(updateMemPane);
            updateMember.clear(); updateMemInfo.setText("");confrmUpdText.setText("");
        });
        historyMem.setOnAction(actionEvent -> {
            memberPane.setCenter(memHistoryPane);
            memberHistory.clear();exceptionInfoHistory.setText("");
        });
         // Layout MembershipView
        memberPane.setCenter(newMemBox);
        memberPane.setLeft(leftField);
    }
    public VBox getNewMemBox() {
        return newMemBox;
    }
    public VBox getMemHistoryPane() {
        return memHistoryPane;
    }
    public VBox getUpdateMemPane() {
        return updateMemPane;
    }
    public VBox getSearchMemPane() {
        return searchMemPane;
    }
    public BorderPane getMemberPane(){
        return memberPane;
    }

}
