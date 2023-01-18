package com.sergiu.heapupboxgame.Mediator;


import javafx.scene.image.ImageView;

public class CollisionWithPlatform {

    public boolean checkCollisionWithPlatform(ImageView box, ImageView platform) {
        boolean collisionWithPlatform = false;
        if (platform.getBoundsInParent().intersects(box.getBoundsInParent())) {
            collisionWithPlatform = true;
        }
        return collisionWithPlatform;
    }
}
