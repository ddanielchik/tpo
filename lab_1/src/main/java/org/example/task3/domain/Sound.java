package org.example.task3.domain;

import java.util.Objects;

public class Sound {
    private SoundType type;
    private double intensity;

    public Sound(SoundType type, double intensity) {
        this.type = Objects.requireNonNull(type, "Sound type cannot be null");
        setIntensity(intensity);
    }

    public SoundType getType() {
        return type;
    }

    public double getIntensity() {
        return intensity;
    }

    public void transformTo(SoundType newType) {
        this.type = Objects.requireNonNull(newType, "New sound type cannot be null");
    }

    public void increaseIntensity(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Intensity increment cannot be negative");
        }
        this.intensity += value;
    }

    public void setIntensity(double intensity) {
        if (intensity < 0) {
            throw new IllegalArgumentException("Intensity cannot be negative");
        }
        this.intensity = intensity;
    }

    @Override
    public String toString() {
        return "Sound{" +
                "type=" + type +
                ", intensity=" + intensity +
                '}';
    }
}