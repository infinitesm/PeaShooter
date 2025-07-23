package ai.peashooter.game.entity;

import ai.peashooter.game.GameWindow;
import ai.peashooter.game.util.Vector;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class SentientOrb extends Entity {

    public SentientOrb(Vector position) {
        super(position);
    }

    public static final int SPEED = 4;
    public static final int INERTIA = 1;

    @Setter public int fireTicks = 5;
    @Setter public double inaccuracy = 0.05;
    @Setter public int damage = 12;

    private int size = 20;
    @Setter private int powerUpTicks = 0;
    @Setter @Getter private int kills;
    @Setter @Getter private boolean shootPassThrough;

    @Getter @Setter private double health = 100;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void collideWithEntity(Entity entity) {
        if (entity instanceof Orb) {
            health = Math.max(0, health - 0.1);
        }
    }

    public void addKill() {
        kills++;
    }

    @Override
    public void move(double strafe, double forward) {
        double totalInput = strafe * strafe + forward * forward;

        if (--powerUpTicks <= 0) {
            fireTicks = 5;
            inaccuracy = 0.05;
            shootPassThrough = false;
        }

        if (totalInput > 0) {
            motionX += strafe * INERTIA;
            motionY += forward * INERTIA;

            motionX = Math.max(-SPEED, Math.min(SPEED, motionX));
            motionY = Math.max(-SPEED, Math.min(SPEED, motionY));
        }

        int x = (int) position.x;
        int y = (int) position.y;

        x += motionX;
        y += motionY;

        x = Math.max(0, Math.min(GameWindow.WIDTH - size(), x));
        y = Math.max(0, Math.min(GameWindow.HEIGHT - size(), y));

        position.x = x;
        position.y = y;

        motionX *= 0.9;
        motionY *= 0.9;

        if (Math.abs(motionX) < 0.25) {
            motionX = 0.0;
        }

        if (Math.abs(motionY) < 0.25) {
            motionY = 0.0;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval((int) position.x, (int) position.y, size(), size());
    }

    @Override
    public boolean canRemove() {
        return health <= 0;
    }
}
