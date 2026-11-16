package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Person person = new Person();
        person.setName("Alice");
        person.setAge(25);

        TextField nameField = new TextField();
        Label nameDisplayLabel = new Label();
        
        TextField ageField = new TextField();
        Label ageDisplayLabel = new Label();

        // 1. Bind name fields
        nameField.textProperty().bindBidirectional(person.nameProperty());
        nameDisplayLabel.textProperty().bind(
            javafx.beans.binding.Bindings.concat("Current Name: ", person.nameProperty())
        );

        // 2. Bind age fields (IntegerProperty to String)
        javafx.beans.binding.Bindings.bindBidirectional(
            ageField.textProperty(), 
            person.ageProperty(), 
            new NumberStringConverter()
        );
        ageDisplayLabel.textProperty().bind(
            person.ageProperty().asString("Current Age: %d")
        );

        // 3. Layout setup
        VBox root = new VBox(15, 
            new Label("Enter Name:"), nameField, nameDisplayLabel,
            new Label("Enter Age:"), ageField, ageDisplayLabel
        );
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 350, 250);
        primaryStage.setTitle("Person Property Binding Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}