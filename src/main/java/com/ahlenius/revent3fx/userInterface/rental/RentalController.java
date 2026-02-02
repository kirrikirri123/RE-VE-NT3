package com.ahlenius.revent3fx.userInterface.rental;

import com.ahlenius.revent3fx.exception.InvalidAmountRentingDaysException;
import com.ahlenius.revent3fx.exception.InvalidDateChoiceException;
import com.ahlenius.revent3fx.service.ItemService;
import com.ahlenius.revent3fx.service.RentalService;
import javafx.scene.control.ButtonType;
import java.time.LocalDate;
import java.util.Optional;

import static com.ahlenius.revent3fx.entity.RentalType.*;


public class RentalController {
private RentalService rentalService;
private ItemService itemService;
private RentalView view;


    public RentalController(RentalService rentalService, RentalView rentalView,ItemService itemService) {
        this.rentalService = rentalService;
        this.view = rentalView;
        this.itemService = itemService;

    startUi();

    }

    public void startUi(){
        view.OKBTN.setOnAction(actionEvent -> {
            LocalDate dateStart = view.datePicker.valueProperty().getValue();
            try {
                view.days = Integer.parseInt(view.daysOfRentField.getText());
            } catch (NumberFormatException e) {
                view.exceptionInfo.setText("Missat antal dagar. Skriv ett ungefärligt antal dar.");} //
                try{
                    switch(view.rentalTypeComboBox.getValue()) {
                     case BOUNCYCASTLE  ->  view.rental = rentalService.newRental(view.memberComboBox.getValue(), 2L,BOUNCYCASTLE, view.days, dateStart, false);
                     case MASCOTECOSTUME ->  view.rental = rentalService.newRental(view.memberComboBox.getValue(), view.availableMCItem.getValue().getProductId(),MASCOTECOSTUME, view.days, dateStart, false);
                     case DISCOMACHINE-> view.rental = rentalService.newRental(view.memberComboBox.getValue(), view.availableDMItem.getValue().getProductId(),DISCOMACHINE, view.days, dateStart, false);
                 }
                 view.confrimationText.setText("Ny uthyrning skapad.\n" + view.rental.getProductId()+ " - "+ " "+ view.rental.getMember().getEmail());
                    view.daysOfRentField.clear();
                    view.exceptionInfo.setText("");
                    view.rental = null;

                } catch (InvalidAmountRentingDaysException | InvalidDateChoiceException e) {view.exceptionInfo.setText(e.getMessage());}
            });

        // Avsluta uthyrning
        view.confirmRentMem.setOnAction(actionEvent -> {
            view.tempRental = view.rentingMemberComboBox.getValue();

            view.confEndRent.setContentText("Vill du avsluta uthyrningen av "+ view.tempRental.getProductId()+ " till " + view.tempRental.getMember().getfname() +" " +view.tempRental.getMember().getlname()  + " ?");
            Optional<ButtonType> userEndingRentResult = view.confEndRent.showAndWait();
            if (userEndingRentResult.isPresent()) {
                if (userEndingRentResult.get() == view.endRentBtn) {
                    view.rentalPane.setCenter(view.finalEndRentBox);}
                if (userEndingRentResult.get() == view.closeConfAlertBtn) {
                    view.exceptionEndRent.setText("Avbryter återlämning. Produkt fortfarande uthyrd.");
                    view.tempRental = null;
                }
            }
        });
        view.confEndRentBtn.setOnAction(actionE -> {
            view.tempRental.setReturned(true);
            rentalService.countActualDays(view.endDatePicker.getValue(),view.tempRental);
            view.rentalPane.setCenter(view.finnishedRentingBox);
            String days = String.valueOf(rentalService.rentalCountDays(view.tempRental));
            //String price = rentalService.pricePolicyCalc(view.tempRental);
            view.rentalDays.setText(days);
           // view.rentalCostSum.setText(price);
            view.tempRental = null;
        });

    }


    }

