package com.sergiu.heapupboxgame.Command;

import com.sergiu.heapupboxgame.Controllers.LevelController;
import javafx.scene.image.ImageView;

public class WonRequirement extends LevelController {

    private boolean alreadyExecuted = false;

    public WonRequirement() {
        super();
    }

    @Override
    public void GameWon(ImageView box, ImageView wonLine) {
        boolean wonBoolean = false;
        if (wonLine.getBoundsInParent().intersects(box.getBoundsInParent())) {
            wonBoolean = true;
        }
        if (wonBoolean) {
            if (!alreadyExecuted) {
                alreadyExecuted = true;
                System.out.println("You won!");
                GameWon();
            }
        }
    }
}
