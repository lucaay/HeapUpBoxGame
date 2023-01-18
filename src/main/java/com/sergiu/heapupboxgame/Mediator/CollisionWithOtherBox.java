package com.sergiu.heapupboxgame.Mediator;


import javafx.scene.image.ImageView;

public class CollisionWithOtherBox {

    public boolean checkCollisionWithOtherBox(ImageView[] boxes, int index) {
        boolean collisionWithOtherBox = false;
        for (int i = 0; i < boxes.length; i++) {
            if (i != index && boxes[i].getBoundsInParent().intersects(boxes[index].getBoundsInParent())) {
                collisionWithOtherBox = true;
            }
        }
        return collisionWithOtherBox;
    }

}
