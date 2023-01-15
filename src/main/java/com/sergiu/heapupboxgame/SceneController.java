package com.sergiu.heapupboxgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private void handleSwitchToLevelSelectorAction(ActionEvent event) throws IOException {
        switchToLevelSelector(event, "level-selector-screen.fxml");
    }
    public void switchToLevelSelector(ActionEvent event, String pathToLevelSelector) throws IOException {
        Object toLoad = getClass().getResource(pathToLevelSelector);
        if ( toLoad == null ) {
            toLoad = getClass().getResource("level-selector-screen.fxml");
        }
        root = FXMLLoader.load((URL) toLoad);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleSwitchToHomeScreenAction(ActionEvent event) throws IOException {
        switchToHomeScreen(event, "home-screen.fxml");
    }
    public void switchToHomeScreen(ActionEvent event, String pathToHomeScreen) throws IOException {
        Object toLoad = getClass().getResource(pathToHomeScreen);
        if (toLoad == null) {
            toLoad = getClass().getResource("home-screen.fxml");
        }
        root = FXMLLoader.load((URL) toLoad);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public int getCurrentLevel(ActionEvent event){
        Object node = event.getSource();
        Button b = (Button)node;
        return Integer.parseInt(b.getText());
    }
    public void switchToLevel(ActionEvent event) throws IOException, URISyntaxException {
        int level  = getCurrentLevel(event);
        Object selectedLevel = getClass().getResource("levels/level-" + level+ "/level-" + level + ".fxml");
        String defaultLevel = "levels/level-0/level-0.fxml";
        if (selectedLevel == null) {
            root = FXMLLoader.load(getClass().getResource(defaultLevel));
        } else {
            root = FXMLLoader.load((URL) selectedLevel);
        }
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void reloadScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent root = null;
        Object selectedLevel = getClass().getResource(fxmlFile);
        if (selectedLevel == null) {
            root = FXMLLoader.load(getClass().getResource("level-selector-screen.fxml"));
        } else {
            root = FXMLLoader.load((URL) selectedLevel);
        }
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
