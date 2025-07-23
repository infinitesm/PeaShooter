package com.gladurbad.game.entity;

import com.gladurbad.game.GameWindow;
import com.gladurbad.game.loop.GameLoop;
import com.gladurbad.game.util.Vector;

import java.awt.*;

@Getter
public class Bullet extends com.gladurbad.game.entity.Entity {

    private static final int SPEED = 15;
    public final Vector start, end, object;

    public Bullet(Vector point) {
        super(point);

        this.start = point;
        this.end = new Vector(GameLoop.getInstance().getMouseListener().getMouseX(), GameLoop.getInstance().getMouseListener().getMouseY());
        this.object = (Vector) start.clone();
    }

    @Override
    public void move(double strafe, double forward) {
        double vectorX = end.x - start.x;
        double vectorY = end.y - start.y;

        double length = Math.hypot(vectorX, vectorY);

        double normalizedVectorX = vectorX / length;
        double normalizedVectorY = vectorY / length;

        double motionX = object.x + (normalizedVectorX * SPEED);
        double motionY = object.y + (normalizedVectorY * SPEED);

        object.setLocation(motionX, motionY);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.drawOval(object.x, object.y, 5, 5);
    }

    @Override
    public boolean canRemove() {
        int x = getObject().x;
        int y = getObject().y;

        return x < 0 || x > GameWindow.WIDTH || y < 0 || y > GameWindow.HEIGHT;
    }
}
