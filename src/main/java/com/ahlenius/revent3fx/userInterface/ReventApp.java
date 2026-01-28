package com.ahlenius.revent3fx.userInterface;

import com.ahlenius.revent3fx.repository.*;
import com.ahlenius.revent3fx.service.ItemService;
import com.ahlenius.revent3fx.service.MemberService;
import com.ahlenius.revent3fx.service.RentalService;
import com.ahlenius.revent3fx.userInterface.view.*;
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
   /* MemberView memberView = new MemberView(memberService,jsonService,rentalService);
    ProductView productView = new ProductView(rentalService,jsonService);
    RentalView rentalView = new RentalView(rentalService,memberService, jsonService);
    HistoryView historyView = new HistoryView(rentalService);
    EconomyView economyView = new EconomyView(rentalService);
  */
    Scene start,main;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("R-EV-ENT - Re-Invent your event - Just rent!");
        start = new Scene(startView.getStartView(),500,450);
        main = new Scene(mainView.getMainView(),925,550);
        stage.setScene(start);
        stage.show();

        startView.getImageStart().setOnMouseClicked(mouseEvent -> {
            changeScene(stage,main);});

    }
    public void changeScene(Stage stage,Scene scene){
        stage.setScene(scene);
    }
}
