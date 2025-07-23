package ai.peashooter.game.entity;

import ai.peashooter.game.loop.GameLoop;
import ai.peashooter.game.util.Vector;
import lombok.Getter;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public abstract class Entity {

    public Entity(Vector position) {
        this.position = position;

        int generated = ThreadLocalRandom.current().nextInt();

        boolean exists = false;

        for (Entity e : GameLoop.getInstance().getEntities()) {
            exists = false;

            if (e.getId() == generated) {
                exists = true;
                break;
            }
        }

        while (exists) {
            for (Entity e : GameLoop.getInstance().getEntities()) {
                exists = false;

                if (e.getId() == generated) {
                    exists = true;
                    break;
                }
            }
            generated = ThreadLocalRandom.current().nextInt();
        }

        this.id = generated;
    }

    public final Vector position;
    public final int id;
    public double motionX, motionY;

    public void move(double strafe, double forward) {}
    public void collideWithEntity(Entity entity) {}

    public Vector getCenter() {
        return position.add(size() / 2.0);
    }
    public int size() {
        return 20;
    }

    public abstract void render(Graphics graphics);
    public abstract boolean canRemove();
}
