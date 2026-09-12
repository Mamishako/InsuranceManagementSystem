package com.insurance.controller;

import com.insurance.model.Payment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PaymentController {

    private static ArrayList<Payment> payments = new ArrayList<>();

    public void showPaymentScreen(Stage stage) {

        Label title = new Label("PREMIUM PAYMENT MANAGEMENT");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField policyId = new TextField();
        policyId.setPromptText("Policy ID");

        TextField amount = new TextField();
        amount.setPromptText("Amount");

        TextField paymentDate = new TextField();
        paymentDate.setPromptText("Payment Date");

        TextField paymentMethod = new TextField();
        paymentMethod.setPromptText("Payment Method");

        Button addButton = new Button("RECORD PAYMENT");
        Button clearButton = new Button("CLEAR");
        Button backButton = new Button("BACK TO DASHBOARD");

        TableView<Payment> table = new TableView<>();

        TableColumn<Payment, Integer> idCol =
                new TableColumn<>("Payment ID");
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("paymentId"));

        TableColumn<Payment, Integer> policyCol =
                new TableColumn<>("Policy ID");
        policyCol.setCellValueFactory(
                new PropertyValueFactory<>("policyId"));

        TableColumn<Payment, Double> amountCol =
                new TableColumn<>("Amount");
        amountCol.setCellValueFactory(
                new PropertyValueFactory<>("amount"));

        TableColumn<Payment, String> dateCol =
                new TableColumn<>("Date");
        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("paymentDate"));

        TableColumn<Payment, String> methodCol =
                new TableColumn<>("Method");
        methodCol.setCellValueFactory(
                new PropertyValueFactory<>("paymentMethod"));

        TableColumn<Payment, String> statusCol =
                new TableColumn<>("Status");
        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("status"));

        table.getColumns().addAll(
                idCol, policyCol, amountCol,
                dateCol, methodCol, statusCol
        );

        ObservableList<Payment> paymentList =
                FXCollections.observableArrayList(payments);

        table.setItems(paymentList);

        addButton.setOnAction(e -> {

            try {

                if (policyId.getText().isEmpty()
                        || amount.getText().isEmpty()) {

                    showAlert("Please enter Policy ID and Amount.");
                    return;
                }

                int id = payments.size() + 1;

                Payment payment = new Payment(
                        id,
                        Integer.parseInt(policyId.getText()),
                        Double.parseDouble(amount.getText()),
                        paymentDate.getText(),
                        paymentMethod.getText(),
                        "PAID"
                );

                payments.add(payment);
                paymentList.setAll(payments);

                policyId.clear();
                amount.clear();
                paymentDate.clear();
                paymentMethod.clear();

                showAlert("Payment recorded successfully!");

            } catch (NumberFormatException ex) {
                showAlert("Please enter valid numbers.");
            }
        });

        clearButton.setOnAction(e -> {
            policyId.clear();
            amount.clear();
            paymentDate.clear();
            paymentMethod.clear();
        });

        TextField searchField = new TextField();
        searchField.setPromptText("Search by Policy ID");

        Button searchButton = new Button("SEARCH");
        Button showAllButton = new Button("SHOW ALL");

        searchButton.setOnAction(e -> {

            try {

                int id = Integer.parseInt(searchField.getText());

                ObservableList<Payment> results =
                        FXCollections.observableArrayList();

                for (Payment p : payments) {
                    if (p.getPolicyId() == id) {
                        results.add(p);
                    }
                }

                table.setItems(results);

            } catch (NumberFormatException ex) {
                showAlert("Enter a valid Policy ID.");
            }
        });

        showAllButton.setOnAction(e ->
                table.setItems(
                        FXCollections.observableArrayList(payments)
                )
        );

        HBox form = new HBox(10,
                policyId, amount, paymentDate, paymentMethod);

        form.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(10, addButton, clearButton);
        buttons.setAlignment(Pos.CENTER);

        HBox search = new HBox(
                10, searchField, searchButton, showAllButton);
        search.setAlignment(Pos.CENTER);

        backButton.setOnAction(e ->
                new DashboardController().showDashboard(stage)
        );

        VBox root = new VBox(20);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        root.getChildren().addAll(
                title, form, buttons, search, table, backButton
        );

        Scene scene = new Scene(root, 1000, 650);

        stage.setTitle("Insurance Management System - Payments");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}