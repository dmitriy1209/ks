package com.mycompany;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    
    private TableView<Product> table;
    private TextField nameInput, priceInput, quantityInput;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stagex) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stagex.setScene(scene);
        
        Stage stage = stagex;
        stage.setTitle("thenewboston - JavaFX");
        
        //Name column
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //Price column
        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price")); 
        
        //Quantity column
        TableColumn<Product, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        //Name input
        nameInput = new TextField();
        nameInput.setPromptText ("Name");
        nameInput.setMinWidth(100);
        
        //Price input 
        priceInput = new TextField();
        priceInput.setPromptText("Price");
        
       //Quantity input
       quantityInput = new TextField();
       quantityInput.setPromptText("Quantity");
       
       //Button 
       Button addButton = new Button("Add");
       addButton.setOnAction(e -> addButtonClicked());
       Button deleteButton = new Button("Delete");
       deleteButton.setOnAction(e -> deleteButtonClicked());
       
       HBox hBox = new HBox();
       hBox.setPadding (new Insets(10,10,10,10));
       hBox.setSpacing(10);
       hBox.getChildren().addAll(nameInput, priceInput, quantityInput, addButton, deleteButton);
       
       table = new TableView<>(); 
       table.setItems(getProduct());
       table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);
       
       VBox vBox = new VBox();
       vBox.getChildren().addAll(table, hBox);
               
       Scene scene1 = new Scene(vBox) ;
       stage.setScene(scene1); 
       stage.show();
       }
        
       //Add button clicked
       public void addButtonClicked(){
           Product product = new Product();
           product.setName(nameInput.getText());
           product.setPrice(Double.parseDouble(priceInput.getText()));
           product.setQuantity(Integer.parseInt(quantityInput.getText())); 
           table.getItems().add(product);
           nameInput.clear();
           priceInput.clear(); 
           quantityInput.clear();
        }
       
       //Delete button clicked
       public void deleteButtonClicked(){
            ObservableList<Product> productSelected, allProducts; 
            allProducts = table.getItems();
            productSelected = table.getSelectionModel().getSelectedItems();
            
            productSelected.forEach(allProducts::remove);
        }
       
       //Get all of the products
       public ObservableList<Product> getProduct(){
           ObservableList<Product> products = FXCollections.observableArrayList();
           products.add(new Product("Laptop", 3859.00, 47));
           products.add(new Product("TV", 799.49, 21));
           products.add(new Product("Fridge", 4599.00, 18));
           products.add(new Product("Microwave oven", 147.99, 35));
           products.add(new Product("Vacuum cleaner", 230.00, 26));
           return products;
       }      

}
