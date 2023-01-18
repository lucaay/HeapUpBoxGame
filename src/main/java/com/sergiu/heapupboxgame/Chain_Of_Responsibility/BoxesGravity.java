package com.sergiu.heapupboxgame.Chain_Of_Responsibility;

import com.sergiu.heapupboxgame.Mediator.CollisionWithPlatform;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class BoxesGravity {
    public ImageView[] boxes;
    private int numberOfBoxes;
    Timeline[] timelines;
    ImageViewWithTimelineIndex[] box;
    private static final double GRAVITY = 0.5; // adjust this value to change the speed of the falling boxes
    private static final double MIN_VELOCITY = 0.1; // adjust this value to change the minimum velocity of the falling boxes
    double velocity[];

    public BoxesGravity(ImageView[] boxes, int numberOfBoxes) {
        this.boxes = boxes;
        this.numberOfBoxes = numberOfBoxes;
        timelines = new Timeline[numberOfBoxes];
        box = new ImageViewWithTimelineIndex[numberOfBoxes];
        velocity = new double[numberOfBoxes];
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
                boxes[i].setY(boxes[i].getY() + velocity[i]);
            }
        }
    }

    private void stop(ImageView clickedBox) {
        for (int i = 0; i < numberOfBoxes; i++) {
            if (clickedBox == boxes[i]) {
                velocity[i] = 0;
                break;
            }
        }
    }

    private void moveBoxes() {

        for (int i = 0; i < numberOfBoxes; i++) {
            CollisionWithPlatform collisionWithPlatform = new CollisionWithPlatform();
            startGravity(boxes[i]);
            if (collisionWithPlatform.checkCollisionWithPlatform(boxes[i]) && boxes[i].getY() < 450) {
                velocity[i] = -GRAVITY;
            } else if (collisionWithPlatform.checkCollisionWithPlatform(boxes[i])) {
                stop(boxes[i]);
            }
        }
        for (int i = 0; i < numberOfBoxes; i++) {
            int finalI = i;
            boxes[i].onMousePressedProperty().set((MouseEvent event) -> {
                stop(boxes[finalI]);
            });
            boxes[i].onMouseReleasedProperty().set((MouseEvent event) -> {
                startGravity(boxes[finalI]);
            });
        }
    }
}
