package com.sergiu.heapupboxgame.Builder;

import com.sergiu.heapupboxgame.Adapter.MouseInput;
import com.sergiu.heapupboxgame.Chain_Of_Responsibility.BoxesGravity;
import com.sergiu.heapupboxgame.Controllers.LevelController;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URISyntaxException;

public class BuildLevel extends LevelController {

    private final String platformImagePath;
    private final String boxImagePath;
    private final int numberOfBoxes;
    private ImageView platformImageView;

    private ImageView getPath(String path) throws URISyntaxException {
        Image image = new Image(getClass().getResource(path).toURI().toString());
        ImageView imageView = new ImageView(image);
        return imageView;
    }
    public BuildLevel(String platformImagePath, int numberOfBoxes, String boxImagePath) throws URISyntaxException {
        this.platformImagePath = platformImagePath;
        this.numberOfBoxes = numberOfBoxes;
        this.boxImagePath = boxImagePath;
        this.platformImageView = getPath(platformImagePath);
    }


    private void PlatformBuilder(AnchorPane mainLevelPane) throws URISyntaxException {
        platformImageView.getStyleClass().add("platform");
        platformImageView.setFitWidth(335);
        platformImageView.setFitHeight(80);
        platformImageView.setX(0);
        platformImageView.setY(520);
        mainLevelPane.getChildren().add(platformImageView);
    }

    private void BoxesBuilder(AnchorPane mainLevelPane) throws URISyntaxException {
        int localNumberOfBoxes = numberOfBoxes;
        // make sure that the number of boxes is between 2 and 5
        if (numberOfBoxes < 2) {
            localNumberOfBoxes = 2;
        } else if (numberOfBoxes > 5) {
            localNumberOfBoxes = 5;
        }

        ImageView[] boxes = new ImageView[localNumberOfBoxes];
        for (int i = 0; i < localNumberOfBoxes; i++) {
            ImageView box = getPath(boxImagePath);
            box.getStyleClass().add("box");
            box.setFitWidth(75);
            box.setFitHeight(75);
            box.setY(350);
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
            boxes[i] = box;
        }
        BoxesGravity boxesGravity = new BoxesGravity(boxes, localNumberOfBoxes, mainLevelPane, platformImageView);

        for (ImageView box: boxes) {
            mainLevelPane.getChildren().add(box);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    MouseInput mi = new MouseInput(box, boxesGravity);
                    box.addEventHandler(MouseEvent.MOUSE_PRESSED, mi);
                    box.addEventHandler(MouseEvent.MOUSE_DRAGGED, mi);
                    box.addEventHandler(MouseEvent.MOUSE_RELEASED, mi);
                    box.addEventHandler(MouseEvent.MOUSE_ENTERED, mi);
                    box.addEventHandler(MouseEvent.MOUSE_EXITED, mi);
                }
            });

        }
        boxesGravity.start();
    }

    private void dottedLineBuilder(AnchorPane mainLevelPane) throws URISyntaxException {
        ImageView dottedLine = getPath("/com/sergiu/heapupboxgame/level_items/dotted_line.png");
        dottedLine.getStyleClass().add("dottedLine");
        dottedLine.setFitWidth(325);
        dottedLine.setFitHeight(3);
        dottedLine.setX(5);

        final int[] mainLevelPaneHeight = {0};
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                mainLevelPaneHeight[0] = (int) mainLevelPane.getHeight();
                dottedLine.setY(mainLevelPaneHeight[0] - 75 * numberOfBoxes - 80 + 20); // 75 is the height of the box and 20 is the offset of the dotted line and 80 is the height of the platform
                mainLevelPane.getChildren().add(dottedLine);
            }
        });
    }


    public void createLevel(AnchorPane mainLevelPane) throws URISyntaxException {
        try {
            PlatformBuilder(mainLevelPane);
            BoxesBuilder(mainLevelPane);
            dottedLineBuilder(mainLevelPane);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
