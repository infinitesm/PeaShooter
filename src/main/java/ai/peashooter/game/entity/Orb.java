package ai.peashooter.game.entity;

import ai.peashooter.game.loop.GameLoop;
import ai.peashooter.game.util.Vector;

import java.awt.*;

public class Orb extends Entity {

    public static final double SPEED = 3;

    private final int creationTick = GameLoop.getInstance().getCurrentTick();

    private int health = 255;
    private boolean canRemove;

    public Orb(Vector position) {
        super(position);
    }

    @Override
    public void collideWithEntity(Entity entity) {
        if (entity instanceof Bullet) {
            health -= ((Bullet) entity).getDamage();
            if (health < 0) GameLoop.getInstance().getSentientOrb().addKill();
        }
    }

    @Override
    public int size() {
        return 7;
    }

    @Override
    public void move(double strafe, double forward) {
        Vector sentientOrb = GameLoop.getInstance().getSentientOrb().getPosition();

        double vectorX = (sentientOrb.x - position.x);
        double vectorY = (sentientOrb.y - position.y);

        double length = Math.hypot(vectorX, vectorY);

        double nvx = vectorX / length;
        double nvy = vectorY / length;

        double motionX = position.x + (nvx * SPEED);
        double motionY = position.y + (nvy * SPEED);

        position.setX(motionX);
        position.setY(motionY);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color((255 - health), (health), 0));
        graphics.fillOval((int) position.x, (int) position.y, size(), size());
    }

    @Override
    public boolean canRemove() {
        return health < 0 || canRemove;
    }
}