package com.sergiu.heapupboxgame.Adapter;

import com.sergiu.heapupboxgame.Chain_Of_Responsibility.BoxesGravity;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MouseInput implements EventHandler<MouseEvent> {
    private double x, y;
    private ImageView box;
    private BoxesGravity boxesGravity = null;
    private Timeline timeline;

    public MouseInput(ImageView box, BoxesGravity boxesGravity) {
        this.box = box;
        this.boxesGravity = boxesGravity;
    }

    public MouseInput() {
        this.boxesGravity = null;
    }


    @Override
    public void handle(MouseEvent event) {

        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            x = event.getSceneX() - box.getTranslateX();
            y = event.getSceneY() - box.getTranslateY();
            box.setCursor(Cursor.CLOSED_HAND);
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            double newTranslateX = event.getSceneX() - x;
            double newTranslateY = event.getSceneY() - y;

            double leftLimit = -box.getX();
            double rightLimit = 335 - box.getX() - 75;
            double topLimit = -box.getY() + 90; // 90 is the height of the status bar
            double bottomLimit = 600 - 80 - 70 - box.getY(); // 80 is the height of the platform

            // Check if the new X and Y positions are within the limits
            if (newTranslateX > leftLimit && newTranslateX < rightLimit && newTranslateY > topLimit && newTranslateY < bottomLimit) {
                box.setTranslateX(newTranslateX);
                box.setTranslateY(newTranslateY);
            }
        } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            box.setCursor(Cursor.DEFAULT);
        } else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            box.setCursor(Cursor.HAND);
        } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            box.setCursor(Cursor.DEFAULT);
        }
    }

    public void setMouseInput(ImageView box) {
        box.setOnMousePressed(this);
        box.setOnMouseDragged(this);
        box.setOnMouseReleased(this);
        box.setOnMouseEntered(this);
        box.setOnMouseExited(this);
    }
}
