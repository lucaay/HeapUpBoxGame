package com.sergiu.heapupboxgame.Builder;

import com.sergiu.heapupboxgame.Adapter.MouseInput;
import com.sergiu.heapupboxgame.Controllers.LevelController;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
            ImageView box = new ImageView(boxImage);
            box.getStyleClass().add("box");
            box.setFitWidth(75);
            box.setFitHeight(75);
            box.setY(445);
            if (localNumberOfBoxes == 2) {
                box.setX(50 + i * 150);
            } else if (localNumberOfBoxes == 3) {
                box.setX(25 + i * 100);
            } else if (localNumberOfBoxes == 4) {
                box.setX(2.5 + i * 85);
            } else if (localNumberOfBoxes == 5) {
                box.setX(2.5 + i * 85);
                if (i == (localNumberOfBoxes - 1)) {
                    box.setY(350);
                    box.setX(2.5);
                }
            }
            anchorPane.getChildren().add(box);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    MouseInput mi = new MouseInput(box);
                    box.addEventHandler(MouseEvent.MOUSE_PRESSED, mi);
                    box.addEventHandler(MouseEvent.MOUSE_DRAGGED, mi);
                    box.addEventHandler(MouseEvent.MOUSE_RELEASED, mi);
                    box.addEventHandler(MouseEvent.MOUSE_ENTERED, mi);
                    box.addEventHandler(MouseEvent.MOUSE_EXITED, mi);
                }
            });

        }
    }

    private void dottedLineBuilder (int numberOfBoxes, AnchorPane anchorPane) throws URISyntaxException {
        ImageView dottedLine = new ImageView(getClass().getResource("/com/sergiu/heapupboxgame/level_items/dotted_line.png").toURI().toString());
        dottedLine.getStyleClass().add("dottedLine");
        dottedLine.setFitWidth(325);
        dottedLine.setFitHeight(3);
        dottedLine.setX(5);

        final int[] anchorPaneHeight = {0};
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                anchorPaneHeight[0] = (int) anchorPane.getHeight();
                dottedLine.setY(anchorPaneHeight[0] - 75 * numberOfBoxes - 80 - 20); // 75 is the height of the box and -20 is the offset of the dotted line and 80 is the height of the platform
                anchorPane.getChildren().add(dottedLine);
            }
        });
    }


    public void createLevel(String platformImagePath, String boxImagePath, int numberOfBoxes, AnchorPane anchorPane) throws URISyntaxException {
        PlatformBuilder(platformImagePath, anchorPane);
        BoxesBuilder(boxImagePath, numberOfBoxes, anchorPane);
        dottedLineBuilder(numberOfBoxes, anchorPane);

    }
}
