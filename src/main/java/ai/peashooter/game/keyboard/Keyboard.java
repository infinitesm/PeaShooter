package ai.peashooter.game.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    public double strafe;
    public double forward;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_LEFT:
                strafe = -0.98;
                break;
            case KeyEvent.VK_RIGHT:
                strafe = 0.98;
                break;
            case KeyEvent.VK_UP:
                forward = -0.98;
                break;
            case KeyEvent.VK_DOWN:
                forward = 0.98;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                strafe = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                forward = 0;
                break;
        }
    }

    public double getStrafe() {
        return strafe;
    }

    public double getForward() {
        return forward;
    }

}
