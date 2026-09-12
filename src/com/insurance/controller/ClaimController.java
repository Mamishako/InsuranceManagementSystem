package com.insurance.controller;

import com.insurance.model.Claim;

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

public class ClaimController {

    private static ArrayList<Claim> claims = new ArrayList<>();

    public void showClaimScreen(Stage stage) {

        Label title = new Label("CLAIM MANAGEMENT");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField policyId = new TextField();
        policyId.setPromptText("Policy ID");

        TextField claimDate = new TextField();
        claimDate.setPromptText("Claim Date");

        TextField claimAmount = new TextField();
        claimAmount.setPromptText("Claim Amount");

        TextField description = new TextField();
        description.setPromptText("Description");

        GridPane form = new GridPane();

        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        form.add(new Label("Policy ID:"), 0, 0);
        form.add(policyId, 1, 0);

        form.add(new Label("Claim Date:"), 2, 0);
        form.add(claimDate, 3, 0);

        form.add(new Label("Claim Amount:"), 0, 1);
        form.add(claimAmount, 1, 1);

        form.add(new Label("Description:"), 2, 1);
        form.add(description, 3, 1);

        Button registerButton = new Button("REGISTER CLAIM");
        Button processButton = new Button("PROCESS SELECTED");
        Button clearButton = new Button("CLEAR");
        Button backButton = new Button("BACK TO DASHBOARD");

        TableView<Claim> table = new TableView<>();

        TableColumn<Claim, Integer> idCol =
                new TableColumn<>("Claim ID");
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("claimId"));

        TableColumn<Claim, Integer> policyCol =
                new TableColumn<>("Policy ID");
        policyCol.setCellValueFactory(
                new PropertyValueFactory<>("policyId"));

        TableColumn<Claim, String> dateCol =
                new TableColumn<>("Claim Date");
        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("claimDate"));

        TableColumn<Claim, Double> amountCol =
                new TableColumn<>("Amount");
        amountCol.setCellValueFactory(
                new PropertyValueFactory<>("claimAmount"));

        TableColumn<Claim, String> descCol =
                new TableColumn<>("Description");
        descCol.setCellValueFactory(
                new PropertyValueFactory<>("description"));

        TableColumn<Claim, String> statusCol =
                new TableColumn<>("Status");
        statusCol.setCellValueFactory(
                new PropertyValueFactory<>("status"));

        table.getColumns().addAll(
                idCol, policyCol, dateCol,
                amountCol, descCol, statusCol
        );

        ObservableList<Claim> claimList =
                FXCollections.observableArrayList(claims);

        table.setItems(claimList);

        registerButton.setOnAction(e -> {

            try {

                if (policyId.getText().isEmpty()
                        || claimAmount.getText().isEmpty()) {

                    showAlert("Please enter Policy ID and Claim Amount.");
                    return;
                }

                int id = claims.size() + 1;

                Claim claim = new Claim(
                        id,
                        Integer.parseInt(policyId.getText()),
                        claimDate.getText(),
                        Double.parseDouble(claimAmount.getText()),
                        description.getText(),
                        "PENDING"
                );

                claims.add(claim);
                claimList.setAll(claims);

                policyId.clear();
                claimDate.clear();
                claimAmount.clear();
                description.clear();

                showAlert("Claim registered successfully!");

            } catch (NumberFormatException ex) {
                showAlert("Please enter valid numbers.");
            }
        });

        processButton.setOnAction(e -> {

            Claim selectedClaim = table.getSelectionModel()
                    .getSelectedItem();

            if (selectedClaim == null) {
                showAlert("Please select a claim first.");
                return;
            }

            selectedClaim.setStatus("PROCESSED");

            table.refresh();

            showAlert("Claim processed successfully!");
        });

        clearButton.setOnAction(e -> {
            policyId.clear();
            claimDate.clear();
            claimAmount.clear();
            description.clear();
        });

        TextField searchField = new TextField();
        searchField.setPromptText("Search by Policy ID");

        Button searchButton = new Button("SEARCH");
        Button showAllButton = new Button("SHOW ALL");

        searchButton.setOnAction(e -> {

            try {

                int id = Integer.parseInt(searchField.getText());

                ObservableList<Claim> results =
                        FXCollections.observableArrayList();

                for (Claim claim : claims) {
                    if (claim.getPolicyId() == id) {
                        results.add(claim);
                    }
                }

                table.setItems(results);

            } catch (NumberFormatException ex) {
                showAlert("Enter a valid Policy ID.");
            }
        });

        showAllButton.setOnAction(e ->
                table.setItems(
                        FXCollections.observableArrayList(claims)
                )
        );

        HBox buttons = new HBox(
                10, registerButton, processButton, clearButton);

        buttons.setAlignment(Pos.CENTER);

        HBox search = new HBox(
                10, searchField, searchButton, showAllButton);

        search.setAlignment(Pos.CENTER);

        backButton.setOnAction(e ->
                new DashboardController().showDashboard(stage)
        );

        VBox root = new VBox(15);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        root.getChildren().addAll(
                title, form, buttons, search, table, backButton
        );

        Scene scene = new Scene(root, 1100, 700);

        stage.setTitle("Insurance Management System - Claims");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Claim");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}