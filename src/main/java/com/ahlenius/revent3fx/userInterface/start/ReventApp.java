package com.ahlenius.revent3fx.userInterface.start;

import com.ahlenius.revent3fx.repository.*;
import com.ahlenius.revent3fx.service.ItemService;
import com.ahlenius.revent3fx.service.MemberService;
import com.ahlenius.revent3fx.service.PricingService;
import com.ahlenius.revent3fx.service.RentalService;
import com.ahlenius.revent3fx.userInterface.items.ItemView;
import com.ahlenius.revent3fx.userInterface.member.MemberController;
import com.ahlenius.revent3fx.userInterface.member.MemberView;
import com.ahlenius.revent3fx.userInterface.items.ItemController;
import com.ahlenius.revent3fx.userInterface.rental.EconomyView;
import com.ahlenius.revent3fx.userInterface.rental.HistoryView;
import com.ahlenius.revent3fx.userInterface.rental.RentalController;
import com.ahlenius.revent3fx.userInterface.rental.RentalView;
import com.ahlenius.revent3fx.util.HibernateUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class ReventApp extends Application {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    MemberRepoImpl memberRepo = new MemberRepoImpl(sessionFactory);
    BouncyCastleRepoImpl bouncyRepo = new BouncyCastleRepoImpl(sessionFactory);
    DiscoMachineRepoImpl discoRepo = new DiscoMachineRepoImpl(sessionFactory);
    MascoteCostumeRepoImpl costumeRepo = new MascoteCostumeRepoImpl(sessionFactory);
    RentalRepoImpl rentalRepo = new RentalRepoImpl(sessionFactory);
    ItemService itemService = new ItemService(bouncyRepo, discoRepo, costumeRepo);
    MemberService memberService = new MemberService(memberRepo);
    RentalService rentalService = new RentalService(rentalRepo);
    PricingService pricingService = new PricingService(bouncyRepo, discoRepo, costumeRepo);

    StartView startView = new StartView();
    MainView mainView = new MainView();
    MemberView memberView = new MemberView();
    ItemView itemView = new ItemView(itemService);
    RentalView rentalView = new RentalView(itemService,memberService,rentalService);
    HistoryView historyView = new HistoryView(rentalService);
    EconomyView economyView = new EconomyView();

    MemberController memberController = new MemberController(memberService, rentalService, memberView);
    ItemController itemController = new ItemController(itemService, itemView);
    RentalController rentalController = new RentalController(rentalService, rentalView, pricingService,itemService,economyView);
    Scene start, main;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("R-EV-ENT - Re-Invent your event - Just rent!");
        start = new Scene(startView.getStartView(), 500, 450);
        main = new Scene(mainView.getMainView(), 925, 800);
        start.getStylesheets().add("/com/ahlenius/revent3fx/revent_style.css");
        main.getStylesheets().add("/com/ahlenius/revent3fx/revent_style.css");
        stage.setScene(start);
        stage.show();


        startView.getImageStart().setOnMouseClicked(mouseEvent -> {
            changeScene(stage, main);
        });

        mainView.home.setOnAction(actionEvent -> {
            mainView.mainPane.setCenter(mainView.centerBox);
        });

        // Medlemsknappar i meny.
        mainView.getNewMem().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(memberView.getMemberPane());
            memberView.getMemberPane().setCenter(memberView.getNewMemBox());
        });
      /*  mainView.getHistoryMem().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(memberView.getMemberPane());
            memberView.getMemberPane().setCenter(memberView.getMemHistoryPane());
        });*/
        mainView.getSearchMem().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(memberView.getMemberPane());
            memberView.getMemberPane().setCenter(memberView.getSearchMemPane());
        });
        mainView.getUpdateMem().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(memberView.getMemberPane());
            memberView.getMemberPane().setCenter(memberView.getUpdateMemPane());
        });

        // Produktknappar i meny
        mainView.getProducts().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(itemView.getProductPane());
            itemView.getProductPane().setCenter(itemView.getItemView());
        });

        mainView.getEditProduct().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(itemView.getProductPane());
            itemView.getProductPane().setCenter(itemView.getUpdateProdPane());
        });

        mainView.getNewProducts().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(itemView.getProductPane());
            itemView.getProductPane().setCenter(itemView.getNewProdBox());
        });

        mainView.getViewAccesibleProd().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(rentalView.getRentalPane());
            rentalView.getRentalPane().setCenter(rentalView.getProdViewBox());
        });

        // - ProduktView funktioner
        itemView.getViewAccesibleProdBtn().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(rentalView.getRentalPane());
            rentalView.getRentalPane().setCenter(rentalView.getProdViewBox());
        });
        // Uthyrningsknappar i meny

        mainView.getAccesibleProd().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(rentalView.getRentalPane());
            rentalView.getRentalPane().setCenter(rentalView.getProdViewBox());
        });

        mainView.getNewRental().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(rentalView.getRentalPane());
            rentalView.getRentalPane().setCenter(rentalView.getNewRentalBox());
            rentalView.getExceptionInfo().setText("");
            rentalView.getConfrimationText().setText("");
        });

        mainView.getEndRental().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(rentalView.getRentalPane());
            rentalView.getRentalPane().setCenter(rentalView.getEndRentalBox());
            rentalView.getRentalsObsList().setAll(rentalService.getNotReturnedRentalList());
        });
        // Historyknappar i meny
        mainView.getRentalHistory().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(historyView.getHistoryPane());
            historyView.getHistoryPane().setCenter(historyView.getHistoryViewBox());
        });

       /*  mainView.getMemberhistory().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(memberView.getMemberPane());
            memberView.getMemberPane().setCenter(memberView.getMemHistoryPane());
        });
       historyView.getMemberHistBtn().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(memberView.getMemberPane());
            memberView.getMemberPane().setCenter(memberView.getMemHistoryPane());
        });*/
        mainView.revenue.setOnAction(actionEvent -> {
                    mainView.getMainView().setCenter(economyView.getEconomyPane());});


        // Avsluta
        Alert saveBeforeQuit = new Alert(Alert.AlertType.CONFIRMATION);
        saveBeforeQuit.setHeaderText("Avsluta");
        saveBeforeQuit.setTitle("Stäng ner program");
        saveBeforeQuit.setContentText("Är du säker du vill avsluta programmet?");
        ButtonType yesBtn = new ButtonType("Ja då");
        ButtonType noBtn = new ButtonType("Nej, låt bli");
        saveBeforeQuit.getButtonTypes().setAll(yesBtn,noBtn);


        mainView.getQuitBtn().setOnAction(actionEvent -> {
            Optional<ButtonType> userChoice = saveBeforeQuit.showAndWait();
            if (userChoice.isPresent()) {
                if (userChoice.get() == yesBtn) {
                    stage.close();
                }
            }
        });
    }
        public void changeScene (Stage stage, Scene scene){
            stage.setScene(scene);
        }

}
