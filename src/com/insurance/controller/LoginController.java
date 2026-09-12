package com.insurance.controller;

import com.insurance.model.User;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {

    public void showLoginScreen(Stage stage) {

        // Title
        Label titleLabel = new Label("INSURANCE MANAGEMENT SYSTEM");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label loginLabel = new Label("Login");
        loginLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Username
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        // Password
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Login button
        Button loginButton = new Button("LOGIN");

        // Message
        Label messageLabel = new Label();

        // Login event
        loginButton.setOnAction(e -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            User user = new User(1, "admin", "admin123", "ADMIN");

            if (username.equals(user.getUsername())
                    && password.equals(user.getPassword())) {

                DashboardController dashboardController =
                        new DashboardController();

                dashboardController.showDashboard(stage);

                // We will open Dashboard here later

            } else {

                messageLabel.setText("Invalid username or password.");
            }
        });

        // Layout
        VBox root = new VBox(15);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        root.getChildren().addAll(
                titleLabel,
                loginLabel,
                usernameField,
                passwordField,
                loginButton,
                messageLabel
        );

        // Scene
        Scene scene = new Scene(root, 500, 400);

        stage.setTitle("Insurance Management System - Login");
        stage.setScene(scene);
        stage.show();
    }
}
