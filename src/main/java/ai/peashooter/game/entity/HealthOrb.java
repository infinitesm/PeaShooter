package ai.peashooter.game.entity;

import ai.peashooter.game.util.Vector;

import java.awt.*;

public class HealthOrb extends Entity {

    public HealthOrb(Vector position) {
        super(position);
    }

    private boolean canRemove;

    @Override
    public void collideWithEntity(Entity entity) {
        if (entity instanceof SentientOrb) {
            double health = ((SentientOrb) entity).getHealth();

            double newHealth = Math.min(health + 20, 100);

            ((SentientOrb) entity).setHealth(newHealth);
            canRemove = true;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(213, 23, 204, 129));
        graphics.fillOval(position.getX(), position.getY(), 10, 10);
    }
    @Override
    public boolean canRemove() {
        return canRemove;
    }
}
