package org.example.task3.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpaceEnvironment {
    private final boolean blackVoid;
    private final List<LightPoint> lightPoints;

    public SpaceEnvironment(boolean blackVoid) {
        this.blackVoid = blackVoid;
        this.lightPoints = new ArrayList<>();
    }

    public boolean isBlackVoid() {
        return blackVoid;
    }

    public void addLightPoint(LightPoint point) {
        if (point == null) {
            throw new IllegalArgumentException("Light point cannot be null");
        }
        lightPoints.add(point);
    }

    public int getLightPointsCount() {
        return lightPoints.size();
    }

    public List<LightPoint> getLightPoints() {
        return Collections.unmodifiableList(lightPoints);
    }

    @Override
    public String toString() {
        return "SpaceEnvironment{" +
                "blackVoid=" + blackVoid +
                ", lightPoints=" + lightPoints +
                '}';
    }
}
