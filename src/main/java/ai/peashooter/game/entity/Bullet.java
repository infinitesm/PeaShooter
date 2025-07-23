package ai.peashooter.game.entity;

import ai.peashooter.game.GameWindow;
import ai.peashooter.game.loop.GameLoop;
import ai.peashooter.game.util.Vector;
import lombok.Getter;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class Bullet extends Entity {

    private static final int SPEED = 25;
    public final Vector start, end;

    private final double nvx, nvy;

    private boolean canRemove;
    private final boolean passthrough = GameLoop.getInstance().getSentientOrb().isShootPassThrough();
    private double random;
    private final int damage;

    public Bullet(Vector point, double random, int damage) {
        super(point);

        this.random = random;
        this.damage = damage;

        this.start = point;
        this.end = new Vector(GameLoop.getInstance().getMouseListener().getMouseX(), GameLoop.getInstance().getMouseListener().getMouseY());

        double vectorX = (end.x - start.x);
        double vectorY = (end.y - start.y);

        double length = Math.hypot(vectorX, vectorY);

        double dir = ThreadLocalRandom.current().nextBoolean() ? 1 : -1;

        double accurate = ThreadLocalRandom.current().nextDouble(random) * dir;
        nvx = vectorX / length + accurate;
        nvy = vectorY / length + accurate;
    }

    @Override
    public void collideWithEntity(Entity entity) {
        if (entity instanceof Orb && !passthrough) {
            canRemove = true;
        }
    }

    @Override
    public void move(double strafe, double forward) {
        int dir = ThreadLocalRandom.current().nextBoolean() ? -1 : 1;

        double motionX = position.x + (nvx * SPEED);
        double motionY = position.y + (nvy * SPEED);

        position.setX(motionX);
        position.setY(motionY);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawOval((int) position.x, (int) position.y, size(), size());
    }

    @Override
    public int size() {
        return 5;
    }

    @Override
    public boolean canRemove() {
        int x = (int) position.x;
        int y = (int) position.y;

        return x < 0 || x > GameWindow.WIDTH || y < 0 || y > GameWindow.HEIGHT || canRemove;
    }
}