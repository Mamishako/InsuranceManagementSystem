package com.insurance.controller;

import com.insurance.model.Customer;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CustomerController {

    // Store customers in memory
    private static ArrayList<Customer> customers = new ArrayList<>();

    public void showCustomerScreen(Stage stage) {

        // =========================
        // TITLE
        // =========================

        Label titleLabel = new Label("CUSTOMER MANAGEMENT");
        titleLabel.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold;"
        );

        // =========================
        // INPUT FIELDS
        // =========================

        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");

        TextField genderField = new TextField();
        genderField.setPromptText("Gender");

        TextField dateOfBirthField = new TextField();
        dateOfBirthField.setPromptText("Date of Birth");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField addressField = new TextField();
        addressField.setPromptText("Address");

        // =========================
        // FORM LAYOUT
        // =========================

        GridPane form = new GridPane();

        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        form.add(new Label("Full Name:"), 0, 0);
        form.add(nameField, 1, 0);

        form.add(new Label("Gender:"), 0, 1);
        form.add(genderField, 1, 1);

        form.add(new Label("Date of Birth:"), 0, 2);
        form.add(dateOfBirthField, 1, 2);

        form.add(new Label("Phone:"), 2, 0);
        form.add(phoneField, 3, 0);

        form.add(new Label("Email:"), 2, 1);
        form.add(emailField, 3, 1);

        form.add(new Label("Address:"), 2, 2);
        form.add(addressField, 3, 2);

        // =========================
        // BUTTONS
        // =========================

        Button addButton = new Button("ADD CUSTOMER");
        Button updateButton = new Button("UPDATE CUSTOMER");
        Button deleteButton = new Button("DELETE CUSTOMER");
        Button clearButton = new Button("CLEAR");
        Button backButton = new Button("BACK TO DASHBOARD");

        // =========================
        // TABLE
        // =========================

        TableView<Customer> table = new TableView<>();

        TableColumn<Customer, Integer> idColumn =
                new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("customerId")
        );

        TableColumn<Customer, String> nameColumn =
                new TableColumn<>("Full Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("fullName")
        );

        TableColumn<Customer, String> genderColumn =
                new TableColumn<>("Gender");

        genderColumn.setCellValueFactory(
                new PropertyValueFactory<>("gender")
        );

        TableColumn<Customer, String> phoneColumn =
                new TableColumn<>("Phone");

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );

        TableColumn<Customer, String> emailColumn =
                new TableColumn<>("Email");

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        table.getColumns().addAll(
                idColumn,
                nameColumn,
                genderColumn,
                phoneColumn,
                emailColumn
        );

        // Display existing customers
        ObservableList<Customer> customerList =
                FXCollections.observableArrayList(customers);

        table.setItems(customerList);

        // =========================
        // SELECT CUSTOMER FROM TABLE
        // =========================

        table.setOnMouseClicked(e -> {

            Customer selectedCustomer =
                    table.getSelectionModel().getSelectedItem();

            if (selectedCustomer != null) {

                nameField.setText(
                        selectedCustomer.getFullName()
                );

                genderField.setText(
                        selectedCustomer.getGender()
                );

                dateOfBirthField.setText(
                        selectedCustomer.getDateOfBirth()
                );

                phoneField.setText(
                        selectedCustomer.getPhone()
                );

                emailField.setText(
                        selectedCustomer.getEmail()
                );

                addressField.setText(
                        selectedCustomer.getAddress()
                );
            }
        });

        // =========================
        // ADD CUSTOMER
        // =========================

        addButton.setOnAction(e -> {

            String name = nameField.getText();
            String gender = genderField.getText();
            String dateOfBirth = dateOfBirthField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String address = addressField.getText();

            if (name.isEmpty() || phone.isEmpty()) {

                showMessage(
                        "Please enter at least the name and phone."
                );

                return;
            }

            int customerId = customers.size() + 1;

            Customer customer = new Customer(
                    customerId,
                    name,
                    gender,
                    dateOfBirth,
                    phone,
                    email,
                    address
            );

            customers.add(customer);

            customerList.setAll(customers);

            clearFields(
                    nameField,
                    genderField,
                    dateOfBirthField,
                    phoneField,
                    emailField,
                    addressField
            );

            showMessage("Customer added successfully!");
        });

        // =========================
        // UPDATE CUSTOMER
        // =========================

        updateButton.setOnAction(e -> {

            Customer selectedCustomer =
                    table.getSelectionModel().getSelectedItem();

            if (selectedCustomer == null) {

                showMessage(
                        "Please select a customer to update."
                );

                return;
            }

            if (nameField.getText().isEmpty()
                    || phoneField.getText().isEmpty()) {

                showMessage(
                        "Name and phone are required."
                );

                return;
            }

            selectedCustomer.setFullName(
                    nameField.getText()
            );

            selectedCustomer.setGender(
                    genderField.getText()
            );

            selectedCustomer.setDateOfBirth(
                    dateOfBirthField.getText()
            );

            selectedCustomer.setPhone(
                    phoneField.getText()
            );

            selectedCustomer.setEmail(
                    emailField.getText()
            );

            selectedCustomer.setAddress(
                    addressField.getText()
            );

            table.refresh();

            clearFields(
                    nameField,
                    genderField,
                    dateOfBirthField,
                    phoneField,
                    emailField,
                    addressField
            );

            showMessage(
                    "Customer updated successfully!"
            );
        });

        // =========================
        // DELETE CUSTOMER
        // =========================

        deleteButton.setOnAction(e -> {

            Customer selectedCustomer =
                    table.getSelectionModel().getSelectedItem();

            if (selectedCustomer == null) {

                showMessage(
                        "Please select a customer to delete."
                );

                return;
            }

            Alert confirmation =
                    new Alert(Alert.AlertType.CONFIRMATION);

            confirmation.setTitle("Delete Customer");
            confirmation.setHeaderText(null);
            confirmation.setContentText(
                    "Are you sure you want to delete this customer?"
            );

            confirmation.showAndWait().ifPresent(response -> {

                if (response ==
                        javafx.scene.control.ButtonType.OK) {

                    customers.remove(selectedCustomer);

                    customerList.setAll(customers);

                    clearFields(
                            nameField,
                            genderField,
                            dateOfBirthField,
                            phoneField,
                            emailField,
                            addressField
                    );

                    showMessage(
                            "Customer deleted successfully!"
                    );
                }
            });
        });

        // =========================
        // CLEAR BUTTON
        // =========================

        clearButton.setOnAction(e -> {

            clearFields(
                    nameField,
                    genderField,
                    dateOfBirthField,
                    phoneField,
                    emailField,
                    addressField
            );

            table.getSelectionModel().clearSelection();
        });

        // =========================
        // SEARCH
        // =========================

        TextField searchField = new TextField();
        searchField.setPromptText(
                "Search customer by name"
        );

        Button searchButton = new Button("SEARCH");
        Button showAllButton = new Button("SHOW ALL");

        searchButton.setOnAction(e -> {

            String searchText =
                    searchField.getText().toLowerCase();

            ObservableList<Customer> results =
                    FXCollections.observableArrayList();

            for (Customer customer : customers) {

                if (customer.getFullName()
                        .toLowerCase()
                        .contains(searchText)) {

                    results.add(customer);
                }
            }

            table.setItems(results);
        });

        showAllButton.setOnAction(e -> {

            table.setItems(
                    FXCollections.observableArrayList(customers)
            );
        });

        HBox searchBox = new HBox(10);

        searchBox.setAlignment(Pos.CENTER);

        searchBox.getChildren().addAll(
                searchField,
                searchButton,
                showAllButton
        );

        // =========================
        // BUTTON LAYOUT
        // =========================

        HBox buttonBox = new HBox(10);

        buttonBox.setAlignment(Pos.CENTER);

        buttonBox.getChildren().addAll(
                addButton,
                updateButton,
                deleteButton,
                clearButton
        );

        // =========================
        // BACK TO DASHBOARD
        // =========================

        backButton.setOnAction(e -> {

            DashboardController dashboardController =
                    new DashboardController();

            dashboardController.showDashboard(stage);
        });

        // =========================
        // MAIN LAYOUT
        // =========================

        VBox root = new VBox(15);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        root.getChildren().addAll(
                titleLabel,
                form,
                buttonBox,
                searchBox,
                table,
                backButton
        );

        // =========================
        // SCENE
        // =========================

        Scene scene = new Scene(root, 1100, 650);

        stage.setTitle(
                "Insurance Management System - Customers"
        );

        stage.setScene(scene);
        stage.show();
    }

    // =========================
    // CLEAR FIELDS METHOD
    // =========================

    private void clearFields(
            TextField nameField,
            TextField genderField,
            TextField dateOfBirthField,
            TextField phoneField,
            TextField emailField,
            TextField addressField) {

        nameField.clear();
        genderField.clear();
        dateOfBirthField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
    }

    // =========================
    // MESSAGE METHOD
    // =========================

    private void showMessage(String message) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Customer");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}