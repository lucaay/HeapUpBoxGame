package com.sergiu.heapupboxgame.Chain_Of_Responsibility;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BoxesGravity {
    private ImageView box;
    private static final double GRAVITY = 4; // adjust this value to change the speed of the falling boxes
    private static final double MIN_VELOCITY = 0.1; // adjust this value to change the minimum velocity of the falling boxes

    public BoxesGravity(ImageView box) {
        this.box = box;
    }
    public void startBox() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> moveBox()));
        timeline.stop();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void moveBox() {
            double velocity = box.getTranslateY() + GRAVITY;
            if (velocity > MIN_VELOCITY) {
                box.setTranslateY(velocity);
            }
    }
}
