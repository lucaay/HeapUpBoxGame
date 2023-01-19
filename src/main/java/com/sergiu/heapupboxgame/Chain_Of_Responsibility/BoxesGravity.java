package com.sergiu.heapupboxgame.Chain_Of_Responsibility;

import com.sergiu.heapupboxgame.AudioController;
import com.sergiu.heapupboxgame.Command.WonRequirement;
import com.sergiu.heapupboxgame.Mediator.CollisionWithOtherBox;
import com.sergiu.heapupboxgame.Mediator.CollisionWithPlatform;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BoxesGravity {
    private static final double GRAVITY = 3;
    public ImageView[] boxes;
    private int numberOfBoxes;
    private Timeline timeline;
    private double[] velocity;
    private Pane noEventsPane;
    private AnchorPane mainLevelPane;
    private ImageView platform;
    private ImageView wonLine;
    private CollisionWithPlatform collisionWithPlatform;
    private CollisionWithOtherBox collisionWithOtherBox;
    private WonRequirement wonRequirement;


    public BoxesGravity(ImageView[] boxes, int numberOfBoxes, AnchorPane mainLevelPane, ImageView platform, ImageView wonLine, Label timerLabel, Pane gameWonPane, Timeline timeline, AudioController gameWonSound) {
        this.boxes = boxes;
        this.numberOfBoxes = numberOfBoxes;
        this.velocity = new double[numberOfBoxes];
        this.mainLevelPane = mainLevelPane;
        noEventsPane = new Pane();
        noEventsPane.setPrefSize(335, 600);
        noEventsPane.setLayoutX(0);
        noEventsPane.setLayoutY(0);
        mainLevelPane.getChildren().add(noEventsPane);
        this.platform = platform;
        this.collisionWithPlatform = new CollisionWithPlatform();
        this.collisionWithOtherBox = new CollisionWithOtherBox();
        this.wonRequirement = new WonRequirement(timerLabel, gameWonPane, timeline, gameWonSound);
        this.wonLine = wonLine;
    }

    public void start() {
        for (int i = 0; i < numberOfBoxes; i++) {
            velocity[i] = GRAVITY;
        }
        timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> moveBoxes()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void moveBoxes() {
        for (int i = 0; i < numberOfBoxes; i++) {
            boxes[i].setY(boxes[i].getY() + velocity[i]);
            if (collisionWithPlatform.checkCollisionWithPlatform(boxes[i], platform) || collisionWithOtherBox.checkCollisionWithOtherBox(boxes, i)) {
                velocity[i] = 0;
                wonRequirement.GameWon(boxes[i], wonLine);
            }
            if (collisionWithPlatform.checkCollisionWithPlatform(boxes[i], platform)) {
                noEventsPane.setVisible(false);
            }
            int finalI = i;
            boxes[i].onMousePressedProperty().set((MouseEvent event) -> {
                velocity[finalI] = 0;
            }); // stop the falling of the box when the user clicks on it
            boxes[i].onMouseReleasedProperty().set((MouseEvent event) -> {
                velocity[finalI] = GRAVITY;
            }); // start the falling of the box when the user releases the click on it
        }
    }
}
