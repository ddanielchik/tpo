package org.example.task3;

import org.example.task3.domain.*;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.start();
        engine.makeWhistle();
        engine.makeRoar();

        SpaceCharacter ford = new SpaceCharacter("Ford");
        SpaceCharacter arthur = new SpaceCharacter("Arthur");

        SpaceEnvironment environment = new SpaceEnvironment(true);
        environment.addLightPoint(new LightPoint(100, true));
        environment.addLightPoint(new LightPoint(120, true));
        environment.addLightPoint(new LightPoint(150, true));

        EjectionEvent event = new EjectionEvent(List.of(ford, arthur), environment);
        event.execute();

        System.out.println(engine);
        System.out.println(ford);
        System.out.println(arthur);
        System.out.println(environment);
        System.out.println(event);
    }
}