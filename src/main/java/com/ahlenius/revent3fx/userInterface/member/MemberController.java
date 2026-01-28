package com.ahlenius.revent3fx.userInterface.member;

import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.exception.InvalidMemberInfoInputException;
import com.ahlenius.revent3fx.exception.InvalidNameInputException;
import com.ahlenius.revent3fx.exception.InvalidPhoneInputException;
import com.ahlenius.revent3fx.service.MemberService;
import com.ahlenius.revent3fx.service.RentalService;

import java.io.IOException;
import java.util.ArrayList;


public class MemberController {
    private MemberService memberService;
    private RentalService rentalService;
    private MemberView view;

    public MemberController() {
    }
    public MemberController(MemberService memberService, RentalService rentalService, MemberView memberView) {
        this.memberService = memberService;
        this.rentalService = rentalService;
        this.view = memberView;
        startIU();
    }

    private void startIU(){
    view.OKBTN.setOnAction(actionEvent -> {
            try {
                memberService.newMember(view.userfName.getText(), view.userlName.getText(), view.userPhone.getText(),view.userEmail.getText(), memberService.createMemberStatus(view.statusComboBox.getValue()));
                view.confrimationText.setText("Ny medlem skapad.");
                view.userfName.clear();
                view.userlName.clear();
                view.userPhone.clear();
                view.userEmail.clear();
                view.exceptionInfo.setText(" ");
            } catch (InvalidMemberInfoInputException | InvalidNameInputException | InvalidPhoneInputException |
                     IOException e) {
                view.exceptionInfo.setText(e.getMessage());
            }
        });
       //Vanlig sök
       view.searchBtnMem.setOnAction(actionEvent -> {
                   if (view.searchMember.getText().isEmpty()) {
                       view.confirmationSearchMem.setText("För att söka fyll i fullständig mailadress.");
                   } else {
                       view.confirmationSearchMem.setText(" ");
                       try {
                           Member foundMem = memberService.searchAndReturnMemberByEmail(view.searchMember.getText());
                           view.confirmationSearchMem.setText(foundMem.toString());
                           view.searchMember.clear();
                           foundMem = null;
                       } catch (NullPointerException ex) {
                           view.confirmationSearchMem.setText(ex.getMessage());
                       }
                   }
               });

       /*
        //Historik
        searchBtnHist.setOnAction(actionEvent -> {
            searchBtnHist.setText("Söker medlem...");// Lägga en sleep och sen återställa knapp till "Sök."
            try {
                tempHistMember = memberService.searchMemberByNameOrPhoneReturnMember(memberHistory.getText());
            } catch (NullPointerException ex) {
                exceptionInfoHistory.setText(ex.getMessage());
                searchBtnHist.setText(searchBtnString);
            }
            try {
                historyTable.setItems(rentalService.memberRentalHistoryObsList(tempHistMember));
                memberPane.setCenter(memHistShow);
                searchBtnHist.setText(searchBtnString);
                memberHistory.clear();
            } catch (NoHistoryFoundException ex) {
                exceptionInfoHistory.setText(ex.getMessage());
            }
            searchBtnHist.setText(searchBtnString);
        });

        // Uppdatera medlemsinfo
        searchBtnUpd.setOnAction(actionEvent -> {
            searchBtnUpd.setText("Söker medlem...");
            try {
                Member foundMem = memberService.searchMemberByNameOrPhoneReturnMember(updateMember.getText());
                confrUpdMem.setContentText("Hittade medlem " + foundMem.getfname() + " " + foundMem.getlname() + ". Stämmer det?");
                Optional<ButtonType> userResult = confrUpdMem.showAndWait();
                if (userResult.isPresent()) {
                    if (userResult.get() == yesBtn) {
                        updateMemInfo.setText("Medlem bekräftad. Laddar sida för uppdatering av medlemsinfo.");// detta skrivs aldrig ut?
                        try {
                            TimeUnit.MILLISECONDS.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println("Fel uppstod vid sleep");
                        }
                        memberPane.setCenter(updateMemVbox);
                        tempMember = foundMem;
                        validatedMem.setText("Vald medlem : " + foundMem.getfname() + " " + foundMem.getlname());
                        updUserNameField.setText(foundMem.getfname() + " " + foundMem.getlname());
                        updUserPhoneField.setText(foundMem.getPhone());
                        updUserStatusCombo.setValue(foundMem.getMemberStatus().toString());
                    } else if (userResult.get() == noBtn) {
                        updateMember.clear();
                        searchBtnUpd.setText(searchBtnString);
                        confrUpdMem.close();
                    }
                }
            } catch (NullPointerException e) {
                updateMemInfo.setText(e.getMessage());
                searchBtnUpd.setText(searchBtnString);
            }
        });


        //Uppdatera ändringar mot register
        confBtn.setOnAction(actionEvent -> {
            if (!updUserNameField.getText().isEmpty()) {
                memberService.updateMemberName(tempMember, updUserNameField.getText());
            }
            if (!updUserPhoneField.getText().isEmpty()) {
                memberService.updateMemberPhone(tempMember, updUserPhoneField.getText());
            }
            if (!updUserStatusCombo.getValue().equals(tempMember.getMemberStatus())) {
                memberService.updateMemberStatus(tempMember, updUserStatusCombo.getValue());
            }
            try {
                jsonService.memberlistToJson();
                confrmUpdText.setText("Efter uppdatering:\n" + tempMember);
                updUserNameField.clear();
                updUserPhoneField.clear();
                tempMember = null;
                validatedMem.setText("");
            } catch (IOException e) {
                confrmUpdText.setText(e.getMessage());
            }
        });
        // Avsluta medlemskap
        removeMemBtn.setOnAction(actionEvet -> {
            confRemoveMem.setContentText("Vill du avsluta " + tempMember.getfname() + " " + tempMember.getlname() + "s medemskap?"); // tillägg senare om det påverkar uthyrning + kostnad kan man dra en chech här innan.
            Optional<ButtonType> userRemoveResult = confRemoveMem.showAndWait();
            if (userRemoveResult.isPresent()) {
                if (userRemoveResult.get() == removeBtn) {
                    try {
                        memberService.getMemberRegistry().remove(tempMember);
                        confrmUpdText.setText(tempMember.getfname() + " " + tempMember.getlname() + "s medlemskap är avslutat.");
                    } catch (IOException e) {
                        confrmUpdText.setText(e.getMessage());
                    }
                } else {
                    confrmUpdText.setText("Avbröt medlemsavslut");
                }
            }
        });*/

           // vilka views och vilka repos??
       }
}