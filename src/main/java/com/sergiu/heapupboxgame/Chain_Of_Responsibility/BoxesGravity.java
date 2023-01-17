package com.sergiu.heapupboxgame.Chain_Of_Responsibility;

import com.sergiu.heapupboxgame.Adapter.MouseInput;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URISyntaxException;

public class BoxesGravity {
    public ImageView[] boxes;
    private int numberOfBoxes;
    private boolean mousePressed[];
    Timeline[] timelines;
    ImageViewWithTimelineIndex[] box;
    private static final double GRAVITY = 0.5; // adjust this value to change the speed of the falling boxes
    private static final double MIN_VELOCITY = 0.1; // adjust this value to change the minimum velocity of the falling boxes

    public BoxesGravity(ImageView[] boxes, int numberOfBoxes) {
        this.boxes = boxes;
        this.numberOfBoxes = numberOfBoxes;
        mousePressed = new boolean[numberOfBoxes];
        timelines = new Timeline[numberOfBoxes];
        box = new ImageViewWithTimelineIndex[numberOfBoxes];
    }


    public ImageViewWithTimelineIndex[] getImageViewWithTimelineIndexData (){
        return box;
    }
    public void start() {
        for (int i =0; i < numberOfBoxes; i++) {
            mousePressed[i] = false;
            box[i] = new ImageViewWithTimelineIndex(i);
            box[i].setImage(boxes[i].getImage());
            timelines[i] = new Timeline(new KeyFrame(Duration.millis(10), e -> moveBoxes()));
            timelines[i].setCycleCount(Timeline.INDEFINITE);
            timelines[i].play();
        }
    }
    public void resume(ImageView clickedBox) {
        if(clickedBox instanceof ImageViewWithTimelineIndex){
            int timelineIndex = ((ImageViewWithTimelineIndex) clickedBox).getTimelineIndex();
            timelines[timelineIndex].play();
        }
    }
    public void stop(ImageView clickedBox) {
        if(clickedBox instanceof ImageViewWithTimelineIndex){
            int timelineIndex = ((ImageViewWithTimelineIndex) clickedBox).getTimelineIndex();
            timelines[timelineIndex].stop();
        }
    }


    public void getMousePressed (boolean mousePressedBool, final int i) {
        for (int j = 0; j < mousePressed.length; j++) {
            if (j == i) {
                mousePressed[j] = mousePressedBool;
            }
        }
    }


    private void moveBoxes() {
        for (int i = 0; i < numberOfBoxes; i++) {
            double velocity = GRAVITY;
            if (boxes[i].getY() < 350) {
                velocity = -GRAVITY;
            }
            if (mousePressed[i]){
                System.out.println(mousePressed[i]);
                stop(boxes[i]);
                break;
            }else{
                boxes[i].setY(boxes[i].getY() + velocity);

            }
        }
    }
}
