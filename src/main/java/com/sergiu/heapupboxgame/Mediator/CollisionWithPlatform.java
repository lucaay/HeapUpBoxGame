package com.sergiu.heapupboxgame.Mediator;


import javafx.scene.image.ImageView;

public class CollisionWithPlatform {

    public boolean checkCollisionWithPlatform(ImageView box) {
        boolean collisionWithPlatform = box.getY() == 450; // 450 is the top coordinate of the platform
        return collisionWithPlatform;
    }
}
