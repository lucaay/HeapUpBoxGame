package com.sergiu.heapupboxgame.Builder;

import com.sergiu.heapupboxgame.Controllers.LevelController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URISyntaxException;

public class BuildLevel extends LevelController {

    private void PlatformBuilder (String platformImagePath, AnchorPane anchorPane) throws URISyntaxException {
        Image platformImage = new Image(getClass().getResource(platformImagePath).toURI().toString());
        ImageView imageView = new ImageView(platformImage);
        imageView.getStyleClass().add("platform");
        imageView.setFitWidth(335);
        imageView.setFitHeight(80);
        imageView.setX(0);
        imageView.setY(520);
        anchorPane.getChildren().add(imageView);
    }
    private void BoxesBuilder (String boxImagePath, int numberOfBoxes,AnchorPane anchorPane) throws URISyntaxException {
        int localNumberOfBoxes = numberOfBoxes;
        // make sure that the number of boxes is between 2 and 5
        if (numberOfBoxes < 2){
            localNumberOfBoxes = 2;
        } else if (numberOfBoxes > 5){
            localNumberOfBoxes = 5;
        }

        for (int i = 0; i < localNumberOfBoxes; i++) {
            Image boxImage = new Image(getClass().getResource(boxImagePath).toURI().toString());
            ImageView imageView = new ImageView(boxImage);
            imageView.getStyleClass().add("box");
            imageView.setFitWidth(75);
            imageView.setFitHeight(75);
            imageView.setY(445);
            if (localNumberOfBoxes == 2) {
                imageView.setX(50 + i * 150);
            } else if (localNumberOfBoxes == 3) {
                imageView.setX(25 + i * 100);
            } else if (localNumberOfBoxes == 4) {
                imageView.setX(2.5 + i * 85);
            } else if (localNumberOfBoxes == 5) {
                imageView.setX(2.5 + i * 85);
                if (i == (localNumberOfBoxes - 1)) {
                    imageView.setY(350);
                    imageView.setX(2.5);
                }
            }
            anchorPane.getChildren().add(imageView);
        }
    }

    public void createLevel(String platformImagePath, String boxImagePath, int numberOfBoxes, AnchorPane anchorPane) throws URISyntaxException {
        PlatformBuilder(platformImagePath, anchorPane);
        BoxesBuilder(boxImagePath, numberOfBoxes, anchorPane);
    }
}
