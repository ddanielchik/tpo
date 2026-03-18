package org.example.task3.domain;

public class Engine {
    private boolean running;
    private Sound sound;

    public Engine() {
        this.running = false;
        this.sound = new Sound(SoundType.BUZZ, 0.0);
    }

    public boolean isRunning() {
        return running;
    }

    public Sound getSound() {
        return sound;
    }

    public void start() {
        running = true;
        sound.transformTo(SoundType.BUZZ);
        sound.setIntensity(1.0);
    }

    public void stop() {
        running = false;
        sound.setIntensity(0.0);
    }

    public void makeWhistle() {
        ensureRunning();
        sound.transformTo(SoundType.WHISTLE);
        sound.setIntensity(2.0);
    }

    public void makeRoar() {
        ensureRunning();
        sound.transformTo(SoundType.ROAR);
        sound.setIntensity(5.0);
    }

    private void ensureRunning() {
        if (!running) {
            throw new IllegalStateException("Engine is not running");
        }
    }

    @Override
    public String toString() {
        return "Engine{" +
                "running=" + running +
                ", sound=" + sound +
                '}';
    }
}