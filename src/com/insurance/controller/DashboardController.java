package com.insurance.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardController {

    public void showDashboard(Stage stage) {

        // Title
        Label titleLabel = new Label("INSURANCE MANAGEMENT SYSTEM");
        titleLabel.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold;"
        );

        Label welcomeLabel = new Label("Welcome, Administrator");
        welcomeLabel.setStyle("-fx-font-size: 16px;");

        // Buttons
        Button customerButton = new Button("CUSTOMERS");
        Button policyButton = new Button("POLICIES");
        Button paymentButton = new Button("PAYMENTS");
        Button claimButton = new Button("CLAIMS");
        Button logoutButton = new Button("LOGOUT");

        // Button sizes
        customerButton.setPrefSize(180, 70);
        policyButton.setPrefSize(180, 70);
        paymentButton.setPrefSize(180, 70);
        claimButton.setPrefSize(180, 70);
        logoutButton.setPrefSize(120, 40);

        // Customer button
        customerButton.setOnAction(e -> {

            CustomerController customerController =
                    new CustomerController();

            customerController.showCustomerScreen(stage);
        });

        // Policy button
        policyButton.setOnAction(e -> {

            PolicyController controller =
                    new PolicyController();

            controller.showPolicyScreen(stage);
        });

        // Payment button
        paymentButton.setOnAction(e -> {

            PaymentController controller =
                    new PaymentController();

            controller.showPaymentScreen(stage);
        });

        // Claim button
        claimButton.setOnAction(e -> {

            ClaimController controller =
                    new ClaimController();

            controller.showClaimScreen(stage);
        });
        // Logout button
        logoutButton.setOnAction(e -> {
            LoginController loginController = new LoginController();
            loginController.showLoginScreen(stage);
        });

        // Grid for main buttons
        GridPane grid = new GridPane();

        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

        grid.add(customerButton, 0, 0);
        grid.add(policyButton, 1, 0);
        grid.add(paymentButton, 0, 1);
        grid.add(claimButton, 1, 1);

        // Main layout
        VBox root = new VBox(20);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        root.getChildren().addAll(
                titleLabel,
                welcomeLabel,
                grid,
                logoutButton
        );

        // Scene
        Scene scene = new Scene(root, 650, 500);

        stage.setTitle("Insurance Management System - Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}