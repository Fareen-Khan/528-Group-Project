package bookstoreapp;

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
/**
 *
 * @author Ryan Chowdhury
 */
public class BookStoreApp extends Application {
    
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
        
        Scene login = new Scene(g, 300, 225);
        primaryStage.setScene(login);
        primaryStage.show();
    }
    
    public void ownerWindow(Stage primaryStage){
        primaryStage.setTitle("BookStoreApp");
        GridPane g = new GridPane();
        g.setAlignment(Pos.TOP_LEFT);
        g.setHgap(10);
        g.setVgap(10);
        g.setPadding(new Insets(20,20,20,20));
        
        Text title = new Text("Welcome, Owner!");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        g.add(title, 1, 0, 2, 1);
        
        Button books = new Button("Books");
        Button customers = new Button("Customers");
        Button logout = new Button("Logout");
        g.add(books, 1, 1, 2, 1);
        g.add(customers, 1, 2, 2, 1);
        g.add(logout, 1,3,2,1);
        Scene owner = new Scene(g, 300, 210);
        primaryStage.setScene(owner);
        primaryStage.show();
    }
    
    public void customerWindow(Stage primaryStage){
        System.out.println("In the customer screm");
    }
    
    public static void main(String[] args) {
        launch(args);
    } 
}
