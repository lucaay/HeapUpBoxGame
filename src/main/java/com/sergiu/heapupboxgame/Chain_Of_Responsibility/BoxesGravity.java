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
    private final int numberOfBoxes;
    private Timeline timeline;
    private final double[] velocity;
    private final Pane noEventsPane;
    private final AnchorPane mainLevelPane;
    private final ImageView platform;
    private final ImageView wonLine;
    private final CollisionWithPlatform collisionWithPlatform;
    private final CollisionWithOtherBox collisionWithOtherBox;
    private final WonRequirement wonRequirement;
    private final boolean[] isDragged;


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
        isDragged = new boolean[numberOfBoxes];
    }


    private void setIsDragged(int index, boolean value) {
        isDragged[index] = value;
    }

    private boolean getIsDragged(int index) {
        return isDragged[index];
    }

    public void start() {
        for (int i = 0; i < numberOfBoxes; i++) {
            velocity[i] = GRAVITY;
            setIsDragged(i, false);
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
            }
            if (collisionWithPlatform.checkCollisionWithPlatform(boxes[i], platform)) {
                noEventsPane.setVisible(false);
            }
            int finalI = i;
            boxes[i].onMousePressedProperty().set((MouseEvent event) -> {
                setIsDragged(finalI, true);
                velocity[finalI] = 0;
            }); // stop the falling of the box when the user clicks on it
            boxes[i].onMouseReleasedProperty().set((MouseEvent event) -> {
                setIsDragged(finalI, false);
                velocity[finalI] = GRAVITY;
            }); // start the falling of the box when the user releases the click on it
            if (collisionWithOtherBox.checkCollisionWithOtherBox(boxes, finalI) && !getIsDragged(finalI)) {
                wonRequirement.GameWon(boxes[finalI], wonLine);
            }
        }
    }
}
