package com.sergiu.heapupboxgame.Command;

import com.sergiu.heapupboxgame.AudioController;
import com.sergiu.heapupboxgame.Controllers.LevelController;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class WonRequirement {

    private boolean alreadyExecuted = false;
    private  Label timerLabel;
    private Pane gameWonPane;
    private Timeline timeline;
    private AudioController gameWonSound;

    public WonRequirement(Label timerLabel, Pane gameWonPane, Timeline timeline, AudioController gameWonSound) {
        super();
        this.timerLabel = timerLabel;
        this.gameWonPane = gameWonPane;
        this.timeline = timeline;
        this.gameWonSound = gameWonSound;
    }

    public void GameWon(ImageView box, ImageView wonLine) {
        boolean wonBoolean = false;
        if (wonLine.getBoundsInParent().intersects(box.getBoundsInParent())) {
            wonBoolean = true;
        }
        if (wonBoolean) {
            if (!alreadyExecuted) {
                alreadyExecuted = true;
                System.out.println("You won!");
                timeline.stop();
                timerLabel.setTextFill(Color.GREEN);
                gameWonPane.setVisible(true);
                gameWonSound.play();

            }
        }
    }
}
