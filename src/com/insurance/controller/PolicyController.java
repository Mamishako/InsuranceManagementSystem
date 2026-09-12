package com.insurance.controller;

import com.insurance.model.MotorPolicy;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PolicyController {

    private static ArrayList<MotorPolicy> policies = new ArrayList<>();

    public void showPolicyScreen(Stage stage) {

        Label title = new Label("MOTOR POLICY MANAGEMENT");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField policyNumber = new TextField();
        policyNumber.setPromptText("Policy Number");

        TextField customerId = new TextField();
        customerId.setPromptText("Customer ID");

        TextField premium = new TextField();
        premium.setPromptText("Premium");

        // Make premium field read-only because it will be calculated
        premium.setEditable(false);

        TextField coverage = new TextField();
        coverage.setPromptText("Sum Insured / Coverage Amount");

        TextField rate = new TextField();
        rate.setPromptText("Rate (%)");

        TextField startDate = new TextField();
        startDate.setPromptText("Start Date");

        TextField endDate = new TextField();
        endDate.setPromptText("End Date");

        TextField vehicleMake = new TextField();
        vehicleMake.setPromptText("Vehicle Make");

        TextField vehicleModel = new TextField();
        vehicleModel.setPromptText("Vehicle Model");

        TextField plateNumber = new TextField();
        plateNumber.setPromptText("Plate Number");

        TextField vehicleValue = new TextField();
        vehicleValue.setPromptText("Vehicle Value");

        GridPane form = new GridPane();

        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        form.add(new Label("Policy Number:"), 0, 0);
        form.add(policyNumber, 1, 0);

        form.add(new Label("Customer ID:"), 2, 0);
        form.add(customerId, 3, 0);

        form.add(new Label("Premium:"), 0, 1);
        form.add(premium, 1, 1);

        form.add(new Label("Sum Insured:"), 2, 1);
        form.add(coverage, 3, 1);

        form.add(new Label("Rate (%):"), 0, 2);
        form.add(rate, 1, 2);

        form.add(new Label("Start Date:"), 2, 2);
        form.add(startDate, 3, 2);

        form.add(new Label("End Date:"), 0, 3);
        form.add(endDate, 1, 3);

        form.add(new Label("Vehicle Make:"), 2, 3);
        form.add(vehicleMake, 3, 3);

        form.add(new Label("Vehicle Model:"), 0, 4);
        form.add(vehicleModel, 1, 4);

        form.add(new Label("Plate Number:"), 2, 4);
        form.add(plateNumber, 3, 4);

        form.add(new Label("Vehicle Value:"), 0, 5);
        form.add(vehicleValue, 1, 5);

        // ==============================
        // BUTTONS
        // ==============================

        Button calculateButton = new Button("CALCULATE PREMIUM");
        Button addButton = new Button("ADD POLICY");
        Button clearButton = new Button("CLEAR");
        Button backButton = new Button("BACK TO DASHBOARD");

        // ==============================
        // CALCULATE PREMIUM
        // ==============================

        calculateButton.setOnAction(e -> {

            try {

                if (coverage.getText().isEmpty()
                        || rate.getText().isEmpty()) {

                    showAlert("Please enter Sum Insured and Rate.");

                    return;
                }

                double sumInsured =
                        Double.parseDouble(coverage.getText());

                double rateValue =
                        Double.parseDouble(rate.getText());

                if (sumInsured <= 0) {

                    showAlert("Sum Insured must be greater than zero.");

                    return;
                }

                if (rateValue <= 0) {

                    showAlert("Rate must be greater than zero.");

                    return;
                }

                double calculatedPremium =
                        sumInsured * rateValue / 100;

                premium.setText(
                        String.format("%.2f", calculatedPremium)
                );

                showAlert(
                        "Premium calculated successfully!\n\n"
                        + "Sum Insured: " + sumInsured
                        + "\nRate: " + rateValue + "%"
                        + "\nPremium: "
                        + String.format("%.2f", calculatedPremium)
                );

            } catch (NumberFormatException ex) {

                showAlert(
                        "Please enter valid numbers for "
                        + "Sum Insured and Rate."
                );
            }
        });

        // ==============================
        // TABLE
        // ==============================

        TableView<MotorPolicy> table = new TableView<>();

        TableColumn<MotorPolicy, Integer> idCol =
                new TableColumn<>("ID");

        idCol.setCellValueFactory(
                new PropertyValueFactory<>("policyId")
        );

        TableColumn<MotorPolicy, String> numberCol =
                new TableColumn<>("Policy Number");

        numberCol.setCellValueFactory(
                new PropertyValueFactory<>("policyNumber")
        );

        TableColumn<MotorPolicy, Integer> customerCol =
                new TableColumn<>("Customer ID");

        customerCol.setCellValueFactory(
                new PropertyValueFactory<>("customerId")
        );

        TableColumn<MotorPolicy, Double> premiumCol =
                new TableColumn<>("Premium");

        premiumCol.setCellValueFactory(
                new PropertyValueFactory<>("premium")
        );

        TableColumn<MotorPolicy, String> makeCol =
                new TableColumn<>("Vehicle Make");

        makeCol.setCellValueFactory(
                new PropertyValueFactory<>("vehicleMake")
        );

        TableColumn<MotorPolicy, String> modelCol =
                new TableColumn<>("Vehicle Model");

        modelCol.setCellValueFactory(
                new PropertyValueFactory<>("vehicleModel")
        );

        TableColumn<MotorPolicy, String> plateCol =
                new TableColumn<>("Plate Number");

        plateCol.setCellValueFactory(
                new PropertyValueFactory<>("plateNumber")
        );

        table.getColumns().addAll(
                idCol,
                numberCol,
                customerCol,
                premiumCol,
                makeCol,
                modelCol,
                plateCol
        );

        //To creates an ObservableList from the ArrayList
        
        ObservableList<MotorPolicy> policyList =
                FXCollections.observableArrayList(policies);

        table.setItems(policyList);

        // ==============================
        // ADD POLICY
        // ==============================

        addButton.setOnAction(e -> {

            try {

                if (policyNumber.getText().isEmpty()
                        || customerId.getText().isEmpty()
                        || coverage.getText().isEmpty()
                        || rate.getText().isEmpty()) {

                    showAlert(
                            "Please enter Policy Number, "
                            + "Customer ID, Sum Insured and Rate."
                    );

                    return;
                }

                // Make sure premium has been calculated
                if (premium.getText().isEmpty()) {

                    showAlert(
                            "Please calculate the premium "
                            + "before adding the policy."
                    );

                    return;
                }

                int id = policies.size() + 1;

                int cId =
                        Integer.parseInt(customerId.getText());

                double prem =
                        Double.parseDouble(premium.getText());

                double cov =
                        Double.parseDouble(coverage.getText());

                double value =
                        Double.parseDouble(vehicleValue.getText());

                MotorPolicy policy =
                        new MotorPolicy(
                                id,
                                policyNumber.getText(),
                                cId,
                                "MOTOR",
                                prem,
                                cov,
                                startDate.getText(),
                                endDate.getText(),
                                "ACTIVE",
                                vehicleMake.getText(),
                                vehicleModel.getText(),
                                plateNumber.getText(),
                                value
                        );

                policies.add(policy);

                policyList.setAll(policies);

                clearFields(
                        policyNumber,
                        customerId,
                        premium,
                        coverage,
                        rate,
                        startDate,
                        endDate,
                        vehicleMake,
                        vehicleModel,
                        plateNumber,
                        vehicleValue
                );

                showAlert(
                        "Motor policy added successfully!"
                );

            } catch (NumberFormatException ex) {

                showAlert(
                        "Please enter valid numbers."
                );
            }
        });

        // ==============================
        // CLEAR
        // ==============================

        clearButton.setOnAction(e ->
                clearFields(
                        policyNumber,
                        customerId,
                        premium,
                        coverage,
                        rate,
                        startDate,
                        endDate,
                        vehicleMake,
                        vehicleModel,
                        plateNumber,
                        vehicleValue
                )
        );

        // ==============================
        // SEARCH
        // ==============================

        TextField searchField = new TextField();

        searchField.setPromptText(
                "Search Policy Number"
        );

        Button searchButton =
                new Button("SEARCH");

        Button showAllButton =
                new Button("SHOW ALL");

        searchButton.setOnAction(e -> {

            String text =
                    searchField.getText().toLowerCase();

            ObservableList<MotorPolicy> results =
                    FXCollections.observableArrayList();

            for (MotorPolicy p : policies) {

                if (p.getPolicyNumber()
                        .toLowerCase()
                        .contains(text)) {

                    results.add(p);
                }
            }

            table.setItems(results);
        });

        showAllButton.setOnAction(e ->
                table.setItems(
                        FXCollections.observableArrayList(
                                policies
                        )
                )
        );

        // ==============================
        // BACK TO DASHBOARD
        // ==============================

        backButton.setOnAction(e -> {

            new DashboardController()
                    .showDashboard(stage);
        });

        // ==============================
        // BUTTON LAYOUT
        // ==============================

        HBox buttons =
                new HBox(
                        10,
                        calculateButton,
                        addButton,
                        clearButton
                );

        buttons.setAlignment(Pos.CENTER);

        HBox searchBox =
                new HBox(
                        10,
                        searchField,
                        searchButton,
                        showAllButton
                );

        searchBox.setAlignment(Pos.CENTER);

        // ==============================
        // MAIN LAYOUT
        // ==============================

        VBox root = new VBox(15);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        root.getChildren().addAll(
                title,
                form,
                buttons,
                searchBox,
                table,
                backButton
        );

        Scene scene =
                new Scene(root, 1100, 700);

        stage.setTitle(
                "Insurance Management System - Policies"
        );

        stage.setScene(scene);
        stage.show();
    }

    // ==============================
    // CLEAR FIELDS METHOD
    // ==============================

    private void clearFields(TextField... fields) {

        for (TextField field : fields) {

            field.clear();
        }
    }

    // ==============================
    // ALERT METHOD
    // ==============================

    private void showAlert(String message) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Policy");

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}