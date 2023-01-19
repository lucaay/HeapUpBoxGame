package com.sergiu.heapupboxgame.Chain_Of_Responsibility;

import javafx.scene.image.ImageView;

public class ImageViewWithTimelineIndex extends ImageView {
    private final int timelineIndex;

    public ImageViewWithTimelineIndex(int timelineIndex) {
        this.timelineIndex = timelineIndex;
    }

    public int getTimelineIndex() {
        return timelineIndex;
    }
}

