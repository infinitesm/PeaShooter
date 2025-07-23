package ai.peashooter.game.util;

import lombok.Getter;

@Getter
public class Vector implements Cloneable {
    public double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Vector other) {
        return Math.hypot(Math.abs(x - other.x), Math.abs(y - other.y));
    }

    public Vector add(double increment) {
        return new Vector(x + increment, y + increment);
    }

    public Vector subtract(double decrement) {
        return new Vector(x - decrement, y - decrement);
    }

    public Vector multiply(double scalar) {
        return new Vector(x * scalar, y * scalar);
    }

    public Vector divide(double scalar) {
        if (scalar == 0) throw new IllegalArgumentException("Cannot divide by zero");
        return new Vector(x / scalar, y / scalar);
    }

    public Vector normalize(Vector end) {
        return divide(distance(end));
    }

    public int getX() {
        return (int) x;
    }

    public Vector setX(double x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return (int) y;
    }

    public Vector setY(double y) {
        this.y = y;
        return this;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }
}
