package bookstoreapp;

import java.io.IOException;
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
        
        books.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked book button");
                ownerBooks(primaryStage);
            }
        });
        customers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clciked customer buton");
                //ownerCustomer(primaryStage);
            }
        });
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start(primaryStage);
            }
        });
        
        Scene owner = new Scene(g, 300, 210);
        primaryStage.setScene(owner);
        primaryStage.show();
    }
    public void ownerBooks(Stage primaryStage){
        
        TableView tableView = new TableView();
       
        TableColumn<Book, Double> column1 = new TableColumn<>("Book Name");
        TableColumn<Book, Double> column2 = new TableColumn<>("Price");
       
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.setCellValueFactory(new PropertyValueFactory<>("price"));
      
        tableView.getColumns().addAll(column1, column2);
        tableView.setItems();
       
        final Button removeButton = new Button("Delete");
        final Button addButton = new Button("Add");
        final Button backButton = new Button("Back");
        
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String bookN = addTitle.getText();
                Double bookP = Double.parseDouble(addPrice.getText()); 
                
            }
        });
        
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ownerWindow(primaryStage);
            }
        });
        addTitle = new TextField();
        addPrice = new TextField();
        addTitle.setPromptText("Book Name");
        addPrice.setPromptText("Book Price");
   
        HBox hbox = new HBox(8);
        HBox buttons = new HBox(8);
        buttons.getChildren().addAll(removeButton, backButton);
        hbox.getChildren().addAll(addTitle, addPrice, addButton);
        VBox vbox = new VBox(tableView, hbox,buttons);
        vbox.setPadding(new Insets(20,20,20,20));
        vbox.setSpacing(10);
        Scene scene = new Scene(vbox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void customerWindow (Stage primaryStage, double total, Customer a){
        //Only displays the total amount, points, and status of the customer
        // I can just pass a customer and use the getPrice method but I will need to 
        // import the book selection into a txt file and read it to use as a parameter in the 
        // method
        //need to add list of books they are buying
        TableView<Book> table = new TableView<>();
        File myFile = new File("books.txt");
        
        TableColumn<Book, Double> column1 = new TableColumn<>("Book Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column1.setMinWidth(250);
        
        TableColumn<Book, Double> column2 = new TableColumn<>("Price");
        column2.setCellValueFactory(new PropertyValueFactory<>("price"));
        column2.setMinWidth(250);
        
        GridPane buyPane = new GridPane();
        //Oberservable Arraylist of all books
        //read text file of books and put them into an arraylist
        //read the arraylist for the checkout system
        ObservableList<Book> books = FXCollections.observableArrayList();
            try{
                Scanner readBooks = new Scanner(myFile);
                while(readBooks.hasNextLine()){
                    String s = readBooks.nextLine();
                    String[] data = s.split(", ");
                    books.add(new Book(data[0], Double.parseDouble(data[1])));
                }
            }catch(FileNotFoundException e){
                System.out.println("File not found.");
            }
        table.setItems(books);
        table.getColumns().addAll(column1, column2);

        table.setPlaceholder(new Label("No rows to display"));
        Button logout= new Button("Logout");
        Button buyButton = new Button("Buy");
        Button rBuyButton = new Button("Redeem and Buy");
        
        VBox vbox = new VBox(table);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(logout, buyButton, rBuyButton);
        hBox.setPadding(new Insets(35));
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(hBox);
        
        buyPane.setAlignment(Pos.BOTTOM_CENTER);
        buyPane.setHgap(10);
        buyPane.setVgap(10);
        buyPane.setPadding(new Insets(25, 25, 25, 25));
       
        logout.setOnAction((ActionEvent e) -> {
            start(primaryStage);
        });
        
        buyButton.setOnAction((ActionEvent e)->{
            // add points and bring to a new screen or display message saying buying was a success
            //for every $1 CAD you earn 10 points
            System.out.println("Bought the book");// Should go to a new screen
            int pointsEarned=0;
            ObservableList<Book> chosen = table.getSelectionModel().getSelectedItems();
            double chosenPrice = chosen.get(0).getPrice();
            String chosenName = chosen.get(0).getName();
            pointsEarned = (int)chosenPrice*10;
            System.out.println("Bought " + chosenName + " Earned: " + pointsEarned + " points!");
            Book.removeBook(chosen.get(0), myFile);
            table.getItems().remove(chosen.get(0));           
        });
        
        rBuyButton.setOnAction((ActionEvent e)->{
            // deduct points and bring to a new screen or display message saying buying was a success
            //100 points per dollar
            System.out.println("Reddem and Bought the book");
            int pointsLost=0;
            ObservableList<Book> chosen = table.getSelectionModel().getSelectedItems();
            double chosenPrice = chosen.get(0).getPrice();
            String chosenName = chosen.get(0).getName();
            pointsLost = (int)chosenPrice*100;
            System.out.println("Redeem & Bought " + chosenName + " Lost: " + pointsLost + " points!");
            Book.removeBook(chosen.get(0), myFile);
            table.getItems().remove(chosen.get(0));
        });

        Scene scene1 = new Scene(vbox,500,500);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    } 
}
