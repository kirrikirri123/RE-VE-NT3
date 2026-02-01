package com.ahlenius.revent3fx.userInterface.rental;

import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.entity.Rental;
import com.ahlenius.revent3fx.entity.RentalType;
import com.ahlenius.revent3fx.exception.InvalidAmountRentingDaysException;
import com.ahlenius.revent3fx.exception.InvalidDateChoiceException;
import com.ahlenius.revent3fx.exception.InvalidRentalInfoInputException;
import com.ahlenius.revent3fx.service.RentalService;
import javafx.scene.control.Button;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.time.LocalDate;

public class RentalController {
private RentalService rentalService;
private RentalView view;


    public RentalController(RentalService rentalService, RentalView rentalView) {
        this.rentalService = rentalService;
        this.view = rentalView;

    startUi();

    }

    public void startUi(){
/*
        //Knappar i sidomeny
        view.updItemList.setOnAction( actionEvent ->{
                            }
        );


        view.OKBTN.setOnAction(actionEvent -> {
            try {
                view.days = Integer.parseInt(view.daysOfRentField.getText());
            } catch (NumberFormatException e) {
                view.exceptionInfo.setText("Missat antal dagar. Skriv ett ungefärligt antal dar.");
            }
            LocalDate dateStart = view.datePicker.valueProperty().getValue();
/*
            Member member, long productId,RentalType rentalType, int rentDays, LocalDate startOfRent, boolean returned
             try {
                 Rental newestRental = rentalService.newRental(view.memberComboBox.getValue(),produktID, RentalType,view.days,String.valueOf(dateStart),false);
                 view.confrimationText.setText("Ny uthyrning skapad.\n" + newestRental);
                    view.daysOfRentField.clear();
                    view.exceptionInfo.setText("");

                } catch (InvalidAmountRentingDaysException | InvalidDateChoiceException |
                         InvalidRentalInfoInputException e) {view.exceptionInfo.setText(e.getMessage());
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
        });




    }*/




    }}

