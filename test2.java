package projectgui;

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
import java.util.HashMap;
import java.util.Scanner;
import javafx.scene.Group;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class test2 extends Application {
TextField addTitle, addPrice;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BookStoreApp");
        GridPane g = new GridPane();
        g.setAlignment(Pos.TOP_LEFT);
        g.setHgap(10);//horizontal gap between grid cells i think
        g.setVgap(10);//vertical gap
        g.setPadding(new Insets(20, 20, 20, 20));//margin around the grid (top/right/bottom/left)

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
                if (user.equals("admin") && pass.equals("admin")) {
                    ownerWindow(primaryStage);
                    System.out.println("Owner logged in.");
                } else if (!(user.equals("admin"))) {
                    boolean valid = false;
                    File f = new File("customers.txt");
                    try {
                        Scanner reader = new Scanner(f);
                        while (reader.hasNextLine()) {
                            String in = reader.nextLine();
                            String[] data = in.split(", ");
                            if (user.equals(data[0]) && pass.equals(data[1])) {
                                valid = true;
                            }
                        }
                        if (valid == true) {
                            customerWindow(primaryStage);

                        } else {
                            System.out.println("This customer does not exist");
                            g.add(new Label("Invalid login."), 2, 4, 2, 1);
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File does not exist!");
                    }
                }
            }
        });

        Scene login = new Scene(g, 300, 225);
        primaryStage.setScene(login);
        primaryStage.show();
    }

    public void ownerWindow(Stage primaryStage) {
        primaryStage.setTitle("BookStoreApp");
        GridPane g = new GridPane();
        g.setAlignment(Pos.TOP_LEFT);
        g.setHgap(10);
        g.setVgap(10);
        g.setPadding(new Insets(20, 20, 20, 20));

        Text title = new Text("Welcome, Owner!");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        g.add(title, 1, 0, 2, 1);

        Button books = new Button("Books");
        Button customers = new Button("Customers");
        Button logout = new Button("Logout");
        g.add(books, 1, 1, 2, 1);
        g.add(customers, 1, 2, 2, 1);
        g.add(logout, 1, 3, 2, 1);

        books.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked book button");
                managerBooks(primaryStage);
            }
        });
        customers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clciked customer buton");
                customerWindow(primaryStage);
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

    public void ownerBooks(Stage primaryStage) {
        TableView tableView = new TableView();
        TableColumn<Book, Double> column1 = new TableColumn<>("Book Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Book, Double> column2 = new TableColumn<>("Price");
        column2.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.setPlaceholder(new Label("No rows to display"));

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void customerWindow(Stage primaryStage) {

        TableView tableView = new TableView();
        TableColumn<Book, Double> column1 = new TableColumn<>("Book Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Book, Double> column2 = new TableColumn<>("Price");
        column2.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.setPlaceholder(new Label("No rows to display"));
        
        Text scenetitle = new Text("Welcome");
        
        final Button buy = new Button("Delete");
        final Button rbuy = new Button("Add");
        final Button logout = new Button("Logout");
        
        buy.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            }
        });

        rbuy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start(primaryStage);
            }
        });
    
        
        VBox vbox = new VBox();
       
        
        vbox.getChildren().addAll(scenetitle,tableView,buy,rbuy, logout);
        vbox.setPadding(new Insets(35, 35, 35, 35));
        vbox.setSpacing(10);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void managerBooks(Stage primaryStage) {

        TableView tableView = new TableView();
        TableColumn<Book, Double> column1 = new TableColumn<>("Book Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Book, Double> column2 = new TableColumn<>("Price");
        column2.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.setPlaceholder(new Label("No rows to display"));

        final Button removeButton = new Button("Delete");
        final Button addButton = new Button("Add");
        final Button logoutButton = new Button("Logout");
        removeButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
        
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start(primaryStage);
            }
        });
        addTitle = new TextField();
        addTitle.setPromptText("Book Name");
        
        addPrice = new TextField();
        addPrice.setPromptText("Book Price");
   
        
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableView,addTitle, addPrice,addButton, removeButton, logoutButton);
        Scene scene = new Scene(vbox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void managerCustomers(Stage primaryStage) {

    }

    public void customerCostScreen(Stage primaryStage) {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
