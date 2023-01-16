package com.sergiu.heapupboxgame.Chain_Of_Responsibility;

public class ColisionDetection {
    private static final double BOX_WIDTH = 75;
    private static final double BOX_HEIGHT = 73;
    private static final double PLATFORM_WIDTH = 335;
    private static final double PLATFORM_HEIGHT = 80;
    private static final double STATUS_BAR_HEIGHT = 90;

    public boolean checkColision(double boxX, double boxY, double platformX, double platformY) {
        if (boxX + BOX_WIDTH >= platformX && boxX <= platformX + PLATFORM_WIDTH && boxY + BOX_HEIGHT >= platformY && boxY <= platformY + PLATFORM_HEIGHT) {
            return true;
        }
        return false;
    }
}
