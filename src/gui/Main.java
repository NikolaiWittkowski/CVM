package gui;

import business.TheaterModel;
import gui.TheaterControl;
import gui.TheaterView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        new TheaterControl(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}