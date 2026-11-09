package com.university;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainController {
    @FXML private StackPane centerPane;

    @FXML
    public void initialize() {
        showDashboard();
    }

    @FXML
    private void showDashboard() {
        VBox dashboardBox = new VBox(20);
        dashboardBox.setStyle("-fx-padding: 30;");

        Label titleLabel = new Label("University Dashboard");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Summary Card for Students
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0); -fx-max-width: 250px;");
        
        Label cardTitle = new Label("Total Students");
        cardTitle.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");
        
        Label countLabel = new Label("Loading...");
        countLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2980b9;");

        // Fetch student count from MySQL
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM students");
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                countLabel.setText(String.valueOf(count));
            }
        } catch (Exception e) {
            countLabel.setText("Error");
            e.printStackTrace();
        }

        card.getChildren().addAll(cardTitle, countLabel);
        dashboardBox.getChildren().addAll(titleLabel, card);
        
        centerPane.getChildren().setAll(dashboardBox);
    }

    @FXML
    private void showStudents() {
        try {
            Parent studentView = FXMLLoader.load(getClass().getResource("/com/university/StudentView.fxml"));
            centerPane.getChildren().setAll(studentView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}