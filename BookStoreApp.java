package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.*;
import java.util.Scanner;
import javafx.scene.Group;
/**
 *
 * @author Ryan Chowdhury
 */
public class BookStoreApp extends Application {
    TableView table = new TableView();
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BookStoreApp");
        GridPane g = new GridPane();
        g.setAlignment(Pos.TOP_LEFT);
        g.setHgap(10);//horizontal gap between grid cells i think
        g.setVgap(10);//vertical gap
        g.setPadding(new Insets(20,20,20,20));//margin around the grid (top/right/bottom/left)
        
        Text title = new Text("Book Store Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        g.add(title, 1, 0, 2, 1);
        g.add(new Label("Username: "), 1, 1);
        TextField username = new TextField();
        g.add(username, 2, 1, 2, 1);
        g.add(new Label("Password: "), 1, 2);
        PasswordField password = new PasswordField();
        g.add(password, 2, 2, 2, 1);
        Button btn = new Button("Log in");
        g.add(btn, 3, 3, 2, 1);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String user = username.getText();
                String pass = password.getText();
                if(user.equals("admin") && pass.equals("admin")){
                    ownerWindow(primaryStage);
                    System.out.println("Owner logged in.");
                }else if(!(user.equals("admin"))){
                    boolean valid = false;
                    File f = new File("customers.txt");
                        try{
                        Scanner reader = new Scanner(f);
                        while (reader.hasNextLine()){
                            String in = reader.nextLine();
                            String[] data = in.split(", ");
                            if(user.equals(data[0]) && pass.equals(data[1])){valid = true;}
                        }
                        if(valid == true){
                            customerWindow(primaryStage);
                            System.out.println("Customer logged in.");
                        }else{
                            System.out.println("This customer does not exist");
                            g.add(new Label("Invalid login."), 2, 4, 2, 1);
                        }
                    }catch(FileNotFoundException e){
                        System.out.println("File does not exist!");
                    }
                }
            }
        });
        
        Button buyPageTemp = new Button("Cart");
        g.add(buyPageTemp, 4, 3, 2, 1);
        buyPageTemp.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                checkoutCart(primaryStage,0);
            }
        });
                
        
        Scene login = new Scene(g, 300, 225);
        primaryStage.setScene(login);
        primaryStage.show();
    }
    
    public void ownerWindow(Stage primaryStage){
        System.out.println("In the owner scren");
    }
    
    public void customerWindow(Stage primaryStage){
        System.out.println("In the customer screm");
    }
    
    public void checkoutCart (Stage primaryStage, double total){
        System.out.println("In the Cart");
        TableView table = new TableView();
        Scene scene = new Scene(new Group());
        Label label = new Label("Cart");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        table.setEditable(true);
        
       TableColumn bookNameCol = new TableColumn("Book");
       TableColumn price = new TableColumn("Price");
    }
    
    public static void main(String[] args) {
        launch(args);
    } 
}
