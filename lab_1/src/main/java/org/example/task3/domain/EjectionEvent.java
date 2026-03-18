package org.example.task3.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EjectionEvent {
    private final List<SpaceCharacter> characters;
    private final SpaceEnvironment environment;
    private boolean completed;

    public EjectionEvent(List<SpaceCharacter> characters, SpaceEnvironment environment) {
        if (characters == null || characters.isEmpty()) {
            throw new IllegalArgumentException("Characters list cannot be null or empty");
        }

        for (SpaceCharacter character : characters) {
            if (character == null) {
                throw new IllegalArgumentException("Characters list cannot contain null");
            }
        }

        this.characters = new ArrayList<>(characters);
        this.environment = Objects.requireNonNull(environment, "Environment cannot be null");
        this.completed = false;
    }

    public List<SpaceCharacter> getCharacters() {
        return Collections.unmodifiableList(characters);
    }

    public SpaceEnvironment getEnvironment() {
        return environment;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void execute() {
        if (completed) {
            throw new IllegalStateException("Ejection event has already been executed");
        }

        for (SpaceCharacter character : characters) {
            character.ejectToOpenSpace();
        }

        completed = true;
    }

    @Override
    public String toString() {
        return "EjectionEvent{" +
                "characters=" + characters +
                ", environment=" + environment +
                ", completed=" + completed +
                '}';
    }
}