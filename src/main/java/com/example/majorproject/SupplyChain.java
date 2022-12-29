package com.example.majorproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SupplyChain extends Application {

    public static final int width = 700,height = 600, headerBar =50;
    Pane bodyPane = new Pane();


    Login login = new Login();

    productDetails productDetails = new productDetails();
    Button globalLogin;
    Label customerEmail = null;

    String customer_Email = null;

    private GridPane headerBar() {
        TextField searchText = new TextField();
        Button searchButton = new Button("search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               String productName = searchText.getText();


               bodyPane.getChildren().clear();

               bodyPane.getChildren().add(productDetails.getProductBYName(productName));
            }
        });

        globalLogin = new Button("Log In");
        globalLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());

            }
        });

        customerEmail = new Label("Welcome User");



        GridPane gridPane = new GridPane();
//        gridPane.setMinSize();
        gridPane.setMinSize(bodyPane.getMinWidth(), headerBar -10);
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setStyle("-fx-background-color : blue");
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(searchText,0,0);
        gridPane.add(searchButton,1,0);
        gridPane.add(globalLogin,2,0);
        gridPane.add(customerEmail,3,0);

        return gridPane;
    }

    private GridPane loginPage() {

        Label emailLabel = new Label("Email");
        Label passwordLabel = new Label("Password");
        Label messageLabel = new Label("I am message");


        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    String email = emailTextField.getText();
                    String password = passwordField.getText();
//                    messageLabel.setText(email + "$$" + password);

                if(login.customerLogin(email,password))
                {
                    messageLabel.setText("login Succesfull");
                    customer_Email = email;
                    globalLogin.setDisable(true);
                    customerEmail.setText("Welcome : "+ customer_Email);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProduct());
                }
                else {
                    messageLabel.setText("Login fail");
                }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        gridPane.setVgap(5);
        gridPane.setHgap(5);
//        gridPane.setStyle("-fx-background-color : #C0C0C0");

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(emailLabel, 0,0);
        gridPane.add(emailTextField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(loginButton, 0,2);
        gridPane.add(messageLabel,1,2);

        return gridPane;
  }

    private GridPane footerBar() {

        Button addToCartButton = new Button("Add to Cart");
        Button buyNowButton = new Button("Buy Now");

        Label messageLabel = new Label("");
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    product selectedProduct = productDetails.getSelectedProduct();
                    if(Order.placeOrder(customer_Email,selectedProduct))
                    {
                        messageLabel.setText("Ordered");
                    }
                    else {
                        messageLabel.setText("order fail");
                    }
            }
        });

        GridPane gridPane = new GridPane();
//        gridPane.setMinSize();
        gridPane.setMinSize(bodyPane.getMinWidth(), headerBar -10);
        gridPane.setVgap(5);
        gridPane.setHgap(50);


        gridPane.setAlignment(Pos.CENTER);
        gridPane.setTranslateY(headerBar+height+5);

        gridPane.add(addToCartButton,0,0);
        gridPane.add(buyNowButton,1,0);
        gridPane.add(messageLabel,2,0);

        return gridPane;
    }

    private  Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width,height+2*headerBar);

        bodyPane.setMinSize(width,height);
        bodyPane.setTranslateY(headerBar);

        bodyPane.getChildren().addAll(productDetails.getAllProduct());
        root.getChildren().addAll(headerBar(), bodyPane,footerBar());

        return root;

    }
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Supply_chain");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}





