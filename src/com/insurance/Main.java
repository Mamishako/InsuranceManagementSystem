package com.insurance;

import com.insurance.controller.LoginController;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        LoginController loginController = new LoginController();
        loginController.showLoginScreen(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}