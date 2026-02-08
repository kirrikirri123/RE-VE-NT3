package com.ahlenius.revent3fx.userInterface.items;

import com.ahlenius.revent3fx.entity.*;
import com.ahlenius.revent3fx.exception.NoItemFoundException;
import com.ahlenius.revent3fx.service.ItemService;
import javafx.scene.control.ButtonType;

import java.math.BigDecimal;
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
              if(view.updateComboBox.getValue() == RentalType.DISCOMACHINE){
                  view.discoItem = view.discoChoice.getValue();
                  view.confrUpdProd.setContentText("Hittade produkten - " + view.discoItem.getProductName() + ".\n Stämmer det?");
              }else if(view.updateComboBox.getValue() == RentalType.BOUNCYCASTLE){
                  view.bouncyItem = view.bouncyChoice.getValue();
                  view.confrUpdProd.setContentText("Hittade produkten - " + view.bouncyItem.getProductName() + ".\n Stämmer det?");
              }else if(view.updateComboBox.getValue() == RentalType.MASCOTECOSTUME){
                  view.costumeItem = view.costumeChoice.getValue();
                  view.confrUpdProd.setContentText("Hittade produkten - " + view.costumeItem.getProductName() + ".\n Stämmer det?");

              }
                 Optional<ButtonType> userResult = view.confrUpdProd.showAndWait();
                 if (userResult.isPresent()) {
                     if (userResult.get() == view.yesBtn) {
                         view.productPane.setCenter(view.updateProdVbox);
                         switch(view.updateComboBox.getValue()){
                         case DISCOMACHINE :{
                            view.validatedProd.setText("Vald produkt: " + view.discoItem.getProductName());
                            view.updProdNameField.setText(view.discoItem.getProductName());
                            view.updProdDescripField.setText(view.discoItem.getDescription());
                            view.updDayPriceField.setText(String.valueOf(view.discoItem.getDayPrice()));}
                         case MASCOTECOSTUME : {view.validatedProd.setText("Vald produkt: " + view.costumeItem.getProductName());
                            view.updProdNameField.setText(view.costumeItem.getProductName());
                            view.updProdDescripField.setText(view.costumeItem.getDescription());
                            view.updDayPriceField.setText(String.valueOf(view.costumeItem.getDayPrice()));}
                         case BOUNCYCASTLE : view.validatedProd.setText("Vald produkt: " + view.bouncyItem.getProductName());
                            view.updProdNameField.setText(view.bouncyItem.getProductName());
                            view.updProdDescripField.setText(view.bouncyItem.getDescription());
                            view.updDayPriceField.setText(String.valueOf(view.bouncyItem.getDayPrice()));}
                     }
                     } else if (userResult.get() == view.noBtn) {
                        view.updateProdField.clear();
                        view.bouncyItem = null;
                        view.discoItem = null;
                        view.costumeItem = null;
                        view.updateProdField.clear();
                        view.updProdNameField.clear();
                        view.updDayPriceField.clear();
                        view.updProdDescripField.clear();
                     }

         });
         view.confBtn.setOnAction(actionEvent -> {
             switch(view.updateComboBox.getValue()){
                 case DISCOMACHINE :{
                     if (!view.updProdNameField.getText().isEmpty()) {
                         itemService.updateItemName(view.discoItem, view.updProdNameField.getText());
                         if (!view.updDayPriceField.getText().isEmpty()) {
                             itemService.updateItemPrice(view.costumeItem, new BigDecimal(view.updDayPriceField.getText()));
                             if (!view.updProdDescripField.getText().isEmpty()) {
                                 itemService.updateItemDesc(view.discoItem, view.updProdDescripField.getText());}}}
                     view.confrmUpdText.setText("Uppdaterat!");
                 }
                 case MASCOTECOSTUME : {if (!view.updProdNameField.getText().isEmpty()) {
                     itemService.updateItemName(view.costumeItem, view.updProdNameField.getText());
                     if (!view.updDayPriceField.getText().isEmpty()) {
                         itemService.updateItemPrice(view.costumeItem, new BigDecimal(view.updDayPriceField.getText()));
                         if (!view.updProdDescripField.getText().isEmpty()) {
                             itemService.updateItemDesc(view.costumeItem, view.updProdDescripField.getText());}}}
                     view.confrmUpdText.setText("Uppdaterat!");
                 }
                 case BOUNCYCASTLE :{ if (!view.updProdNameField.getText().isEmpty()) {
                 itemService.updateItemName(view.bouncyItem, view.updProdNameField.getText());
                 if (!view.updDayPriceField.getText().isEmpty()) {
                     itemService.updateItemPrice(view.bouncyItem, new BigDecimal(view.updDayPriceField.getText()));
                     if (!view.updProdDescripField.getText().isEmpty()) {
                         itemService.updateItemDesc(view.bouncyItem, view.updProdDescripField.getText());}}}
                     view.confrmUpdText.setText("Uppdaterat!");
                 }
             }
                view.updProdNameField.clear();
                view.updDayPriceField.clear();
                view.updProdDescripField.clear();
                view.bouncyItem = null;
                view.discoItem = null;
                view.costumeItem = null;
                view.validatedProd.setText("");
                                      });

         view.removeProdBtn.setOnAction(actionEvent -> {
             try{
             switch(view.updateComboBox.getValue()){
                 case DISCOMACHINE  -> itemService.deleteItem(view.discoItem);
                 case MASCOTECOSTUME-> itemService.deleteItem(view.costumeItem);
                 case BOUNCYCASTLE  -> itemService.deleteItem(view.bouncyItem);
             }
             view.confrmUpdText.setStyle("-fx-font-color: RED");
             view.confrmUpdText.setText("Produkt raderad!");

             } catch (RuntimeException e){ view.confrmUpdText.setText(e.getMessage()+ "Något gick fel vid radering av produkt. Raderingen avbröts.");}
             view.updProdNameField.clear();
             view.updDayPriceField.clear();
             view.updProdDescripField.clear();
             view.bouncyItem = null;
             view.discoItem = null;
             view.costumeItem = null;
         });


     }
}


