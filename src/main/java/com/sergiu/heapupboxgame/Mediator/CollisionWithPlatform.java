package com.sergiu.heapupboxgame.Mediator;


import javafx.scene.image.ImageView;

public class CollisionWithPlatform {

    public boolean checkCollisionWithPlatform(ImageView box, ImageView platform) {
        boolean collisionWithPlatform = platform.getBoundsInParent().intersects(box.getBoundsInParent());
        return collisionWithPlatform;
    }
}
