package com.ahlenius.revent3fx.userInterface.items;

import com.ahlenius.revent3fx.service.ItemService;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ItemController {
    private ItemService itemService;
    private ItemView view;

    public ItemController(ItemService itemService, ItemView itemview){
        this.itemService = itemService;
        this.view = itemview;

        startUi();
    }
     public void startUi(){
         view.OKBTN.setOnAction(actionEvent -> {
             double dayPrice = Double.parseDouble(view.dayPriceField.getText());
             if(view.itemTypeCombo.getValue().equals(view.costume)){
                 try {
                     itemService.newCostumeItem(view.prodNameField.getText(),view.prodDescriptField.getText(),dayPrice,true,"Året runt");***************
                     view.confrimationText.setText("Ny produkt tillagd");
                 } catch (IOException e) {view.exceptionInfo.setText(e.getMessage()+"Dräktproblem");}
             }
             if(view.itemTypeCombo.getValue().equals(view.bouncyC)){
                 try {
                     itemService.newBouncyItem(view.prodNameField.getText(),view.prodDescriptField.getText(),dayPrice,true,false);******§********
                     view.confrimationText.setText("Ny produkt tillagd");
                 } catch (IOException e) {view.exceptionInfo.setText((e.getMessage()+"Hoppborgssfail"));
                 }
             } view.prodNameField.clear();view.prodDescriptField.clear();view.dayPriceField.clear();view.exceptionInfo.setText("");
         });
         /*//Uppdatera produkt
         view.searchBtnUpd.setOnAction(actionEvent -> {
             try {
                 Optional <T> foundItem = itemService.searchItemByNameReturnItem(view.updateProdField.getText());
                 view.confrUpdProd.setContentText("Hittade produkten - " + foundItem.getName() + ".\n Stämmer det?");
                 Optional<ButtonType> userResult = view.confrUpdProd.showAndWait();
                 if(userResult.isPresent()) {
                     if (userResult.get() == yesBtn) {
                         tempItem = foundItem;
                         //  updProdInfo.setText("Produkt bekräftad. Laddar sida för uppdatering."); - Syns aldrig?
                         productPane.setCenter(updateProdVbox);
                         validatedProd.setText("Vald produkt: "+ tempItem.getName());
                         updProdNameField.setText(tempItem.getName());
                         updProdDescripField.setText(tempItem.getDescription());
                         updDayPriceField.setText(String.valueOf(tempItem.getDayPrice()));

                     }else if(userResult.get() == noBtn) {  updateProdField.clear();
                         searchBtnUpd.setText("Sök"); }}
             } catch (NullPointerException e) {updProdInfo.setText(e.getMessage());}});

         confBtn.setOnAction(actionEvent -> {
             if(!updProdNameField.getText().isEmpty()) {
                 itemservice.updateItemName(tempItem, updProdNameField.getText());
                 if(!updDayPriceField.getText().isEmpty()){
                     itemservice.updateItemPrice(tempItem, updDayPriceField.getText());
                     if(!updProdDescripField.getText().isEmpty()) {
                         itemservice.updateItemDesc(tempItem, updProdDescripField.getText());
                         try {
                             confrmUpdText.setText("Efter uppdatering:\n"+ tempItem);
                             updProdNameField.clear();updDayPriceField.clear(); updProdDescripField.clear(); tempItem= null; validatedProd.setText("");
                         } catch (IOException e) {confrmUpdText.setText(e.getMessage());}
                     }}}});

         removeProdBtn.setOnAction(actionEvent -> {
             confRemoveProd.setContentText("Vill du radera "+ tempItem.getName() +" ?"); // tillägg senare om det påverkar uthyrning + kostnad kan man dra en chech här innan.
             Optional<ButtonType> userRemoveResult = confRemoveProd.showAndWait();
             if(userRemoveResult.isPresent()){
                 if(userRemoveResult.get() == removeBtn){
                     try {
                         itemService.deleteItem(tempItem); System.out.println(tempItem + "Raderad");
                         confrmUpdText.setText(tempItem.getName() + " är raderad.");
                     } catch (IOException e) { confrmUpdText.setText(e.getMessage());}
                 }else{
                     confrmUpdText.setText("Avbröt radering av produkt.");
                 }
             }
         });*/




     }

}
