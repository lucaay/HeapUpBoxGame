package com.sergiu.heapupboxgame.Chain_Of_Responsibility;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

public class CollisionDetection {

    private ImageView box;
    private ImageView platform;

    public CollisionDetection(ImageView box, ImageView platform) {
        this.box = box;
        this.platform = platform;
    }

    public boolean checkCollision() { // check if the box collides with the platform
        Bounds boxBounds = box.getBoundsInLocal();
        Bounds platformBounds = platform.getBoundsInLocal();
        return boxBounds.intersects(platformBounds);
    }
}
