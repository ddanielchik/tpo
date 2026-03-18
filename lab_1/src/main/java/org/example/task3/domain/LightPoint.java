package org.example.task3.domain;

public class LightPoint {
    private final int brightness;
    private final boolean glowing;

    public LightPoint(int brightness, boolean glowing) {
        if (brightness < 0) {
            throw new IllegalArgumentException("Brightness cannot be negative");
        }
        this.brightness = brightness;
        this.glowing = glowing;
    }

    public int getBrightness() {
        return brightness;
    }

    public boolean isGlowing() {
        return glowing;
    }

    @Override
    public String toString() {
        return "LightPoint{" +
                "brightness=" + brightness +
                ", glowing=" + glowing +
                '}';
    }
}