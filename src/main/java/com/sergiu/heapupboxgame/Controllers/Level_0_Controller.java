package com.sergiu.heapupboxgame.Controllers;

public class Level_0_Controller extends LevelController {
    //build level here and specific methods for this level

    private int timerMaxSeconds = 30; //default value, adjust the value for each level depending on the number of boxes

    public void initialize() {
        super.initialize(30);
    }
}
