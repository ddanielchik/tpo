package org.example.task3.domain;

import java.util.Objects;

public class SpaceCharacter {
    private final String name;
    private boolean inOpenSpace;

    public SpaceCharacter(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Character name cannot be null or blank");
        }
        this.name = name;
        this.inOpenSpace = false;
    }

    public String getName() {
        return name;
    }

    public boolean isInOpenSpace() {
        return inOpenSpace;
    }

    public void ejectToOpenSpace() {
        this.inOpenSpace = true;
    }

    @Override
    public String toString() {
        return "SpaceCharacter{" +
                "name='" + name + '\'' +
                ", inOpenSpace=" + inOpenSpace +
                '}';
    }
}