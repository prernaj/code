package spring.project.logger;

import spring.project.logger.logger.Timer;

public class TestTimer extends Timer {

    private long currentTime = System.currentTimeMillis();

    @Override
    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long time) {
        currentTime = time;
    }
}
