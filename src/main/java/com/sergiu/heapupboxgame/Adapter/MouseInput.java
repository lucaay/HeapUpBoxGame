package com.sergiu.heapupboxgame.Adapter;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MouseInput implements EventHandler<MouseEvent> {
    private double x, y;
    private ImageView box;

    public MouseInput(ImageView box) {
        this.box = box;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            x = event.getSceneX() - box.getTranslateX();
            y = event.getSceneY() - box.getTranslateY();
            box.setCursor(Cursor.CLOSED_HAND);
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            box.setTranslateX(event.getSceneX() - x);
            box.setTranslateY(event.getSceneY() - y);
        } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            box.setCursor(Cursor.DEFAULT);
        } else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            box.setCursor(Cursor.HAND);
        } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            box.setCursor(Cursor.DEFAULT);
        }
    }
}
