package ai.peashooter.game.entity;

import ai.peashooter.game.util.Vector;

import java.awt.*;

public class PowerOrb extends Entity {

    private boolean canRemove;

    public PowerOrb(Vector position) {
        super(position);
    }

    @Override
    public void collideWithEntity(Entity entity) {
        if (entity instanceof SentientOrb) {
            ((SentientOrb) entity).setFireTicks(1);
            ((SentientOrb) entity).setInaccuracy(0.01);
            ((SentientOrb) entity).setShootPassThrough(true);
            ((SentientOrb) entity).setPowerUpTicks(1000);
            ((SentientOrb) entity).setDamage(36);
            canRemove = true;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(23, 153, 213, 129));
        graphics.fillOval(position.getX(), position.getY(), 10, 10);
    }

    @Override
    public boolean canRemove() {
        return canRemove;
    }
}
