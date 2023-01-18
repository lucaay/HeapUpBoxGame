package com.sergiu.heapupboxgame.Chain_Of_Responsibility;

import com.sergiu.heapupboxgame.Mediator.CollisionWithPlatform;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BoxesGravity {
    public ImageView[] boxes;
    private int numberOfBoxes;
    Timeline[] timelines;
    ImageViewWithTimelineIndex[] box;
    private static final double GRAVITY = 0.5; // adjust this value to change the speed of the falling boxes
    private static final double MIN_VELOCITY = 0.1; // adjust this value to change the minimum velocity of the falling boxes
    double velocity[];
    Pane noEventsPane;
    AnchorPane mainLevelPane;

    public BoxesGravity(ImageView[] boxes, int numberOfBoxes, AnchorPane mainLevelPane) {
        this.boxes = boxes;
        this.numberOfBoxes = numberOfBoxes;
        timelines = new Timeline[numberOfBoxes];
        box = new ImageViewWithTimelineIndex[numberOfBoxes];
        velocity = new double[numberOfBoxes];
        this.mainLevelPane = mainLevelPane;
        noEventsPane = new Pane();
        noEventsPane.setPrefSize(335, 600);
        noEventsPane.setLayoutX(0);
        noEventsPane.setLayoutY(0);
        mainLevelPane.getChildren().add(noEventsPane);
    }


    public ImageViewWithTimelineIndex[] getImageViewWithTimelineIndexData (){
        return box;
    }

    public void start() {
        for (int i = 0; i < numberOfBoxes; i++) {
            box[i] = new ImageViewWithTimelineIndex(i);
            box[i].setImage(boxes[i].getImage());
            timelines[i] = new Timeline(new KeyFrame(Duration.millis(10), e -> moveBoxes()));
            timelines[i].setCycleCount(Timeline.INDEFINITE);
            timelines[i].play();
            velocity[i] = GRAVITY;
        }
    }

    private void startGravity(ImageView clickedBox) {
        for (int i = 0; i < numberOfBoxes; i++) {
            if (clickedBox == boxes[i]) {
                int finalI = i;
                boxes[i].setY(boxes[i].getY() + velocity[i]);
                CollisionWithPlatform collisionWithPlatform = new CollisionWithPlatform();
                if (collisionWithPlatform.checkCollisionWithPlatform(boxes[i]) && boxes[i].getY() < 450) {
                    velocity[i] = -GRAVITY;
                } else if (collisionWithPlatform.checkCollisionWithPlatform(boxes[i])) {
                    velocity[finalI] = 0;
                }
            }
        }
    }

    private void moveBoxes() {
        for (int i = 0; i < numberOfBoxes; i++) {
            startGravity(boxes[i]);
            CollisionWithPlatform collisionWithPlatform = new CollisionWithPlatform();
            if (collisionWithPlatform.checkCollisionWithPlatform(boxes[i])) {
                noEventsPane.setVisible(false);
            }
        }
        for (int i = 0; i < numberOfBoxes; i++) {
            int finalI = i;
            boxes[i].onMousePressedProperty().set((MouseEvent event) -> {
                velocity[finalI] = 0;
            }); // stop the falling of the box when the user clicks on it
            boxes[i].onMouseReleasedProperty().set((MouseEvent event) -> {
                velocity[finalI] = GRAVITY;
                startGravity(boxes[finalI]);
            }); // start the falling of the box when the user releases the click on it
        }
    }
}
