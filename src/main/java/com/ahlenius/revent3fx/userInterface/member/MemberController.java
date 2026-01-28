package com.ahlenius.revent3fx.userInterface.member;

import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.exception.InvalidMemberInfoInputException;
import com.ahlenius.revent3fx.exception.InvalidNameInputException;
import com.ahlenius.revent3fx.exception.InvalidPhoneInputException;
import com.ahlenius.revent3fx.service.MemberService;
import com.ahlenius.revent3fx.service.RentalService;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


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
*/
        // Uppdatera medlemsinfo
        view.searchBtnUpd.setOnAction(actionEvent -> {
            view.searchBtnUpd.setText("Söker medlem...");
            try {
                Member foundMem = memberService.searchAndReturnMemberByEmail(view.updateMember.getText());
                view.confrUpdMem.setContentText("Hittade medlem " + foundMem.getfname() + " " + foundMem.getlname() + ". Stämmer det?");
                Optional<ButtonType> userResult = view.confrUpdMem.showAndWait();
                if (userResult.isPresent()) {
                    if (userResult.get() == view.yesBtn) {
                        view.getMemberPane().setCenter(view.updateMemVbox);
                        view.tempMember = foundMem;
                        view.validatedMem.setText("Vald medlem : " + foundMem.getfname() + " " + foundMem.getlname());
                        view.updUserNameField.setText(foundMem.getfname() + " " + foundMem.getlname());
                        view.updUserPhoneField.setText(foundMem.getPhone());
                        view.updUserStatusCombo.setValue(foundMem.getMemberStatus().toString());
                    } else if (userResult.get() == view.noBtn) {
                        view.updateMember.clear();
                        view.searchBtnUpd.setText("Sök");
                        view.confrUpdMem.close();
                    }
                }
            } catch (NullPointerException e) {
                view.updateMemInfo.setText(e.getMessage());
                view.searchBtnUpd.setText(view.searchEmail);
            }
        });

        //Uppdatera ändringar mot register
        view.confBtn.setOnAction(actionEvent -> {
            if (!updUserNameField.getText().isEmpty()) {
                memberService.updateMemberName(view.tempMember, view.updUserNameField.getText());
            }
            if (!updUserPhoneField.getText().isEmpty()) {
                memberService.updateMemberPhone(view.tempMember, view.updUserPhoneField.getText());
            }
            if (!updUserStatusCombo.getValue().equals(view.tempMember.getMemberStatus())) {
                memberService.updateMemberStatus(view.tempMember, view.updUserStatusCombo.getValue());
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
        view.removeMemBtn.setOnAction(actionEvet -> {
            view.confRemoveMem.setContentText("Vill du avsluta " + tempMember.getfname() + " " + tempMember.getlname() + "s medemskap?");
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