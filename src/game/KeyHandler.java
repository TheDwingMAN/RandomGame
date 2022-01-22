package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyHandler implements KeyListener {

    private Set<Integer> keysPressed;

    public KeyHandler() {
        this.keysPressed = new HashSet<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        keysPressed.add(code);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        keysPressed.remove(code);
    }

    public boolean isKeyPressed(Integer e) {
        return keysPressed.contains(e);
    }

    public boolean isKeyPressed(String key) {
        return isKeyPressed(KeyEvent.getExtendedKeyCodeForChar(key.charAt(0)));
    }

    public boolean isNotMoving() {
        return !isKeyPressed("W") && !isKeyPressed("S") && !isKeyPressed("D") && !isKeyPressed("A");
    }

    public boolean isMovingDiagonally() {
        return isKeyPressed("W") && isKeyPressed("A") || isKeyPressed("W") && isKeyPressed("D")
                || isKeyPressed("S") && isKeyPressed("A") || isKeyPressed("S") && isKeyPressed("D");
    }
}
