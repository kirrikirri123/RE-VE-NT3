package com.ahlenius.revent3fx.userInterface;

import com.ahlenius.revent3fx.repository.*;
import com.ahlenius.revent3fx.service.ItemService;
import com.ahlenius.revent3fx.service.MemberService;
import com.ahlenius.revent3fx.service.RentalService;
import com.ahlenius.revent3fx.userInterface.items.ItemView;
import com.ahlenius.revent3fx.userInterface.member.MemberController;
import com.ahlenius.revent3fx.userInterface.member.MemberView;
import com.ahlenius.revent3fx.userInterface.items.ItemController;
import com.ahlenius.revent3fx.userInterface.rental.RentalView;
import com.ahlenius.revent3fx.userInterface.start.*;
import com.ahlenius.revent3fx.util.HibernateUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

public class ReventApp extends Application {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    MemberRepoImpl memberRepo = new MemberRepoImpl(sessionFactory);
    BouncyCastleRepoImpl bouncyRepo = new BouncyCastleRepoImpl(sessionFactory);
    DiscoMachineRepoImpl discoRepo = new DiscoMachineRepoImpl(sessionFactory);
    MascoteCostumeRepoImpl costumeRepo = new MascoteCostumeRepoImpl(sessionFactory);
    RentalRepoImpl rentalRepo = new RentalRepoImpl(sessionFactory);
    ItemService itemService = new ItemService(bouncyRepo,discoRepo,costumeRepo);
    MemberService memberService = new MemberService(memberRepo);
    RentalService rentalService = new RentalService(rentalRepo);

    StartView startView = new StartView();
    MainView mainView = new MainView();
    MemberView memberView = new MemberView();
    ItemView itemView = new ItemView();
    RentalView rentalView = new RentalView(rentalService,memberService);
    /* HistoryView historyView = new HistoryView(rentalService);
     EconomyView economyView = new EconomyView(rentalService);
   */
    MemberController memberController = new MemberController(memberService,rentalService, memberView);
    ItemController itemController = new ItemController(itemService,itemView);
    Scene start,main;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("R-EV-ENT - Re-Invent your event - Just rent!");
        start = new Scene(startView.getStartView(),500,450);
        main = new Scene(mainView.getMainView(),925,550);
        stage.setScene(start);
        stage.show();

        // knappar
        startView.getImageStart().setOnMouseClicked(mouseEvent -> {
            changeScene(stage,main);});

        // Medlemsknappar i meny.
        mainView.getNewMem().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(memberView.getMemberPane());
            memberView.getMemberPane().setCenter(memberView.getNewMemBox());
        });
        mainView.getHistoryMem().setOnAction(actionEvent -> {
            mainView.getMainView().setCenter(memberView.getMemberPane());
            memberView.getMemberPane().setCenter(memberView.getMemHistoryPane());
        });
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

        /*// - ProduktView funktioner
        itemView.getViewAccesibleProdBtn().setOnAction(actionEvent ->{
            mainView.getMainView().setCenter(rentalView.getRentalPane());
            rentalView.getRentalPane().setCenter(rentalView.getProdViewBox());
        });*/
    }
    public void changeScene(Stage stage,Scene scene){
        stage.setScene(scene);
    }
}
