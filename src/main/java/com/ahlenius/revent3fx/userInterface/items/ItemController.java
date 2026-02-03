package com.ahlenius.revent3fx.userInterface.items;

import com.ahlenius.revent3fx.entity.BouncyCastle;
import com.ahlenius.revent3fx.entity.Costume;
import com.ahlenius.revent3fx.entity.DiscoMachine;
import com.ahlenius.revent3fx.entity.RentalType;
import com.ahlenius.revent3fx.exception.NoItemFoundException;
import com.ahlenius.revent3fx.service.ItemService;
import javafx.scene.control.ButtonType;

import java.util.Optional;


public class ItemController {
    private ItemService itemService;
    private ItemView view;


    public ItemController(ItemService itemService, ItemView itemview) {
        this.itemService = itemService;
        this.view = itemview;

        startUi();
    }
     private void startUi() {
         view.OKBTN.setOnAction(actionEvent -> {
             double dayPrice = Double.parseDouble(view.dayPriceField.getText());
             if (view.itemTypeCombo.getValue().equals(RentalType.MASCOTECOSTUME)) {
                 itemService.newCostumeItem(view.prodNameField.getText(), view.prodDescriptField.getText(), dayPrice, "Året runt"); // Borde man fixa ett fält fär säsong kanske så det har nån betydelse?
                 view.confrimationText.setText("Ny produkt tillagd");
             }
             if (view.itemTypeCombo.getValue().equals(RentalType.BOUNCYCASTLE)) {
                 itemService.newBouncyItem(view.prodNameField.getText(), view.prodDescriptField.getText(), dayPrice, false);
                 view.confrimationText.setText("Ny produkt tillagd");
             }
             if (view.itemTypeCombo.getValue().equals(RentalType.DISCOMACHINE)) {
                 itemService.newDiscoItem(view.prodNameField.getText(), view.prodDescriptField.getText(), dayPrice);
                 view.confrimationText.setText("Ny produkt tillagd");
             }
             view.prodNameField.clear();
             view.prodDescriptField.clear();
             view.dayPriceField.clear();
             view.exceptionInfo.setText("");
         });
        //Uppdatera produkt
         view.searchBtnUpd.setOnAction(actionEvent -> {
             try {
                 var item = itemService.sortByRentalType(view.updateComboBox.getValue(), view.updateProdField.getText());
                 if (item instanceof BouncyCastle bc) {
                     view.confrUpdProd.setContentText("Hittade produkten - " + bc.getProductName() + ".\n Stämmer det?");
                     view.bouncyItem = bc;
                     view.pNameHolder = bc.getProductName();
                     view.pDescrHolder = bc.getDescription();
                     view.pDayPriceHolder = String.valueOf(bc.getDayPrice());
                 } else if (item instanceof Costume c) {
                     view.confrUpdProd.setContentText("Hittade produkten - " + c.getProductName() + ".\n Stämmer det?");
                     view.costumeItem = c;
                     view.pNameHolder = c.getProductName();
                     view.pDescrHolder = c.getDescription();
                     view.pDayPriceHolder = String.valueOf(c.getDayPrice());
                 } else if (item instanceof DiscoMachine dM) {
                     view.confrUpdProd.setContentText("Hittade produkten - " + dM.getProductName() + ".\n Stämmer det?");
                     view.discoItem = dM;
                     view.pNameHolder = dM.getProductName();
                     view.pDescrHolder = dM.getDescription();
                     view.pDayPriceHolder = String.valueOf(dM.getDayPrice());
                 }
             }catch (NoItemFoundException e){view.updProdInfo.setText(e.getMessage());}

                 Optional<ButtonType> userResult = view.confrUpdProd.showAndWait();
                 if (userResult.isPresent()) {
                     if (userResult.get() == view.yesBtn) {
                         view.productPane.setCenter(view.updateProdVbox);
                         view.validatedProd.setText("Vald produkt: " + view.pNameHolder);
                         view.updProdNameField.setText(view.pNameHolder);
                         view.updProdDescripField.setText(view.pDescrHolder);
                         view.updDayPriceField.setText(view.pDayPriceHolder);
                            itemService.checkItem(view.pNameHolder);
                     } else if (userResult.get() == view.noBtn) {
                         view.updateProdField.clear();
                         view.searchBtnUpd.setText("Sök");
                     }
                 }
         });/*
         view.confBtn.setOnAction(actionEvent -> {
             if (!view.updProdNameField.getText().isEmpty()) {
                 itemService.updateItemName(tempItem, view.updProdNameField.getText());
                 if (!view.updDayPriceField.getText().isEmpty()) {
                     itemService.updateItemPrice(tempItem, view.updDayPriceField.getText());
                     if (!view.updProdDescripField.getText().isEmpty()) {
                         itemService.updateItemDesc(tempItem, view.updProdDescripField.getText());
                         try {
                             view.confrmUpdText.setText("Efter uppdatering:\n" + tempItem);
                             view.updProdNameField.clear();
                             view.updDayPriceField.clear();
                             view.updProdDescripField.clear();
                             tempItem = null;
                             view.validatedProd.setText("");
                         } catch (IOException e) {
                             view.confrmUpdText.setText(e.getMessage());
                         }
                     }
                 }
             }
         });

         view.removeProdBtn.setOnAction(actionEvent -> {
             view.confRemoveProd.setContentText("Vill du radera " + tempItem.getName() + " ?"); // tillägg senare om det påverkar uthyrning + kostnad kan man dra en chech här innan.
             Optional<ButtonType> userRemoveResult = view.confRemoveProd.showAndWait();
             if (userRemoveResult.isPresent()) {
                 if (userRemoveResult.get() == view.removeBtn) {
                     try {
                         itemService.deleteItem(tempItem);
                         System.out.println(tempItem + "Raderad");
                         view.confrmUpdText.setText(tempItem.getName() + " är raderad.");
                     } catch (IOException e) {
                         view.confrmUpdText.setText(e.getMessage());
                     }
                 } else {
                     view.confrmUpdText.setText("Avbröt radering av produkt.");
                 }
             }
         });*/

     }



     }


