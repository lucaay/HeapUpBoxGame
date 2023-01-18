package com.sergiu.heapupboxgame.Mediator;


import javafx.scene.image.ImageView;

public class CollisionWithPlatform {

    public boolean checkCollisionWithPlatform(ImageView box) {
        boolean collisionWithPlatform = box.getY() == 450;
        return collisionWithPlatform;
    }
}
