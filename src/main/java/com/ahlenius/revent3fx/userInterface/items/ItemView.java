package com.ahlenius.revent3fx.userInterface.items;

import com.ahlenius.revent3fx.entity.BouncyCastle;
import com.ahlenius.revent3fx.entity.Costume;
import com.ahlenius.revent3fx.entity.DiscoMachine;
import com.ahlenius.revent3fx.entity.RentalType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ItemView {
    // Här läggs allt som har med produkterna att göra. Foto-info, boka osv.
    final BorderPane productPane = new BorderPane();
    final FlowPane itemView = new FlowPane();
    final VBox newProdBox = new VBox();
    final VBox updateProdPane = new VBox();
    final VBox updateProdVbox;
    final Button products;
    final Button newProd;
    final Button editProd;
    final Button viewAccesibleProdBtn = new Button("Aktuella produkter");
    final Button OKBTN = new Button("OK");
    final Button searchBtnUpd;
    final ButtonType noBtn;
    final ButtonType yesBtn;
    final ButtonType removeBtn;
    final ButtonType noRemoveBtn;
    Label confrimationText= new Label();
    Label exceptionInfo= new Label();
    Label updProdInfo = new Label();
    Label validatedProd;
    Label confrmUpdText;
    Label updMemExceptionInfo;
    TextField prodNameField;
    TextField prodDescriptField;
    TextField updateProdField;
    TextField dayPriceField;
    TextField updProdNameField;
    TextField updProdDescripField;
    TextField updDayPriceField;
    ComboBox<RentalType> itemTypeCombo;
    ComboBox<RentalType> updateComboBox;
    Alert confrUpdProd;
    Alert confRemoveProd;
    Button confBtn;
    Button removeProdBtn;
    BouncyCastle bouncyItem;
    Costume costumeItem;
    DiscoMachine discoItem;
    String pNameHolder;
    String pDescrHolder;
    String pDayPriceHolder;


    public ItemView(){
        // GalleriVY
        products = new Button("Galleri");
        Label headerGallery = new Label("Ett urval av produkter");
        headerGallery.setAlignment(Pos.CENTER);
        headerGallery.setPrefSize(750,55);

        VBox item1 = new VBox();
        item1.setAlignment(Pos.BASELINE_LEFT);
        item1.setSpacing(10);
        Image image1 = new Image(getClass().getResourceAsStream("/com/ahlenius/revent3fx/gurk_costume.png"));
        ImageView imageView1 = new ImageView(image1);
        imageView1.setPreserveRatio(true);
        imageView1.setFitWidth(235);
        Label nameItem1 = new Label("Gruk-Man");
        Label descripItem1 = new Label("Grön och ståtlig dräkt.Garanterar skratt!");
        Label dayPriceItem1 = new Label("275kr");
        item1.setSpacing(5);
        item1.getChildren().addAll(imageView1,nameItem1,descripItem1,dayPriceItem1);

        VBox item2 = new VBox();
        item2.setAlignment(Pos.CENTER);
        Image image2 = new Image(getClass().getResourceAsStream("/com/ahlenius/revent3fx/dino_costume.png"));
        ImageView imageView2 = new ImageView(image2);
        imageView2.setPreserveRatio(true);
        imageView2.setFitWidth(235);
        Label item2name = new Label("Dino-dräkt");
        Label item2desc = new Label("Skräckinjagande. Springvänlig modell");
        Label item2price = new Label("575kr");
        item1.setSpacing(5);
        item2.getChildren().addAll(imageView2,item2name,item2desc,item2price);

        VBox item4 = new VBox();
        item4.setAlignment(Pos.BASELINE_RIGHT);
        Image image4 = new Image(getClass().getResourceAsStream("/com/ahlenius/revent3fx/trampoline.png"));
        ImageView imageView4 = new ImageView(image4);
        imageView4.setPreserveRatio(true);
        imageView4.setFitWidth(235);
        Label item4name = new Label("Studsmatta - Större ");
        Label item4desc = new Label("Klassisk trampolin. Max vikt 350 kg");
        Label item4Price = new Label("1500kr");
        item4.getChildren().addAll(imageView4,item4name,item4desc,item4Price);
        itemView.getChildren().addAll(headerGallery,item1,item2,item4);

        // Ny produktVY
        Label headerNewProd = new Label(" Skapa ny produkt");
        newProdBox.setSpacing(10);
        newProdBox.setAlignment(Pos.CENTER);
        newProd = new Button("Ny produkt");
        Label prodName = new Label("Produktnamn ");
        prodNameField = new TextField();
        prodNameField.setPromptText("Stora stygga vargen");
        prodNameField.setMaxWidth(250);
        itemTypeCombo = new ComboBox<>();
        Label itemTypeL = new Label("Vilken typ av produkt?");
        itemTypeCombo.getItems().addAll(RentalType.values());
        Label prodDescript = new Label("Beskrivning ");
        prodDescriptField = new TextField();
        prodDescriptField.setPromptText("tex. Lurvig svart varg med löstagbar svans");
        Label dayPrice = new Label("Dagspris i sek ");
        dayPriceField = new TextField();
        dayPriceField.setPromptText("tex. 750");
        dayPriceField.setMaxWidth(250);
        GridPane newProdPane =new GridPane();
        newProdPane.add(itemTypeL,0,0);
        newProdPane.add(itemTypeCombo,1,0);
        newProdPane.add(prodName,0,1);
        newProdPane.add(prodNameField,1,1);
        newProdPane.add(prodDescript,0,2);
        newProdPane.add(prodDescriptField,1,2);
        newProdPane.add(dayPrice,0,3);
        newProdPane.add(dayPriceField,1,3);
        newProdPane.add(OKBTN,3,4);
        newProdPane.add(confrimationText,0,5);
        newProdPane.add(exceptionInfo,0,6);
        newProdPane.setVgap(8);
        newProdPane.setHgap(5);
        newProdPane.setAlignment(Pos.CENTER);
        newProdBox.getChildren().addAll(headerNewProd,newProdPane);

        // Redigera ProduktVy
        editProd = new Button("Redigera produkt");
        Label headerUpd = new Label("Redigera produkt");
        Label updateItemType = new Label("Välj produktkategori");
        Label updateItemSearch = new Label("Sök på fullständigt produktnamn för redigering");
        updateComboBox = new ComboBox<>();
        updateComboBox.getItems().addAll(RentalType.values());
        updateProdField = new TextField();
        updateProdField.setPromptText("tex. Tomten");
        updateProdField.setMaxWidth(250);
        updateProdField.setPromptText("Produktnamn");
        searchBtnUpd = new Button("Sök och redigera");
        GridPane updItemPane =new GridPane();
        updItemPane.setHgap(5);
        updItemPane.setVgap(8);
        updItemPane.setAlignment(Pos.CENTER);
        updItemPane.add(updateItemType,0,0);
        updItemPane.add(updateComboBox,1,0);
        updItemPane.add(updateItemSearch,0,1);
        updItemPane.add(updateProdField,1,1);
        updItemPane.add(updProdInfo,1,2);
        updItemPane.add(searchBtnUpd,3,4);
        confrUpdProd = new Alert(Alert.AlertType.CONFIRMATION);
        yesBtn = new ButtonType("Ja");
        noBtn = new ButtonType("Avbryt");
        confrUpdProd.getButtonTypes().setAll(yesBtn,noBtn);
        confrUpdProd.setTitle("Redigera produkt - Validering");
        confrUpdProd.setHeaderText("Vill du redigera en produkt?");

        updateProdPane.setSpacing(5);
        updateProdPane.setAlignment(Pos.CENTER);
        updateProdPane.getChildren().addAll(headerUpd,updItemPane);

        // Steg 2 uppdatera produkt.
        updateProdVbox = new VBox();
        Label update2ndView = new Label("Redigering av produktinformation");
        Label updName = new Label(" Ändra produktnamn: ");
        Label updDescript = new Label("Uppdatera beskrivning: ");
        Label updDayPrice = new Label("Uppdatera dagshyra: ");
        validatedProd = new Label();
        updProdNameField = new TextField();
        updProdNameField.maxWidth(225);
        updProdDescripField = new TextField();
        updProdDescripField.maxWidth(225);
        updDayPriceField = new TextField();
        confBtn = new Button(" Bekräfta ändring ");
        removeProdBtn = new Button("Ta bort produkt");
        confrmUpdText = new Label();
        updMemExceptionInfo = new Label();
        GridPane updProdPane = new GridPane();
        updProdPane.setHgap(5);
        updProdPane.setVgap(8);
        updProdPane.setAlignment(Pos.CENTER);
        updProdPane.add(updName,0,0);
        updProdPane.add(updProdNameField,1,0);
        updProdPane.add(updDescript,0,2);
        updProdPane.add(updProdDescripField,1,2);
        updProdPane.add(updDayPrice,0,3);
        updProdPane.add(updDayPriceField,1,3);
        updProdPane.add(confBtn,2,4);
        updProdPane.add(confrmUpdText,0,4);
        updProdPane.add(updMemExceptionInfo,1,6);
        updProdPane.add (removeProdBtn,1,7);
        updateProdVbox.setSpacing(15);
        updateProdVbox.setAlignment(Pos.CENTER);
        updateProdVbox.getChildren().addAll(update2ndView,validatedProd,updProdPane);
        confRemoveProd = new Alert(Alert.AlertType.CONFIRMATION);
        removeBtn = new ButtonType("Radera");
        noRemoveBtn = new ButtonType("Avbryt");
        confRemoveProd.getButtonTypes().setAll(removeBtn,noRemoveBtn);
        confRemoveProd.setTitle("Radera produkt");
        confRemoveProd.setHeaderText("Vill radera produkten?");

        //Knappar Layout
        products.setOnAction(actionEvent -> {
            productPane.setCenter(itemView);
        });
        newProd.setOnAction( actionEvent -> {
            productPane.setCenter(newProdBox);
        });
        editProd.setOnAction(actionEvent -> {
            productPane.setCenter(updateProdPane);
            searchBtnUpd.setText("Sök"); updateProdField.clear();confrmUpdText.setText("");
        });

        // Vänsterfält
        VBox leftField = new VBox();
        leftField.setPadding(new Insets(15,15,5,10));
        leftField.setSpacing(10);
        leftField.getChildren().addAll(products,newProd,editProd,viewAccesibleProdBtn);

        // Layout ProductView
        productPane.setCenter(itemView);
        productPane.setLeft(leftField);
    }

    public VBox getUpdateProdPane() {
        return updateProdPane;
    }
    public VBox getNewProdBox() {
        return newProdBox;
    }
    public FlowPane getItemView() {
        return itemView;
    }
    public BorderPane getProductPane(){
     return productPane;}


}
