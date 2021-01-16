package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static Utils.Constants.KEY_COUNT;

/**
 * A class for polling keyboard input.
 */
public class KeyboardInput implements KeyListener {

    private enum KeyState {
        RELEASED,   // Not down.
        PRESSED,    // Down, but not the first time.
        ONCE        // Down for the first time.
    }

    // Current state of the keyboard.
    private boolean[] currentKeys = null;

    // Polled keyboard state.
    private KeyState[] keys = null;

    /**
     * Initialize the keyboard parameters.
     */
    public KeyboardInput() {
        currentKeys = new boolean[ KEY_COUNT ];
        keys = new KeyState[ KEY_COUNT ];
        for( int i=0; i < KEY_COUNT; i++ ){
            // Not a single key has been pressed.
            keys[i] = KeyState.RELEASED;
        }
    }

    /**
     * Poll the keyboard and set the appropriate key states.
     */
    public synchronized void poll(){
        for( int i=0; i < KEY_COUNT; ++i ){
            // Set the key state.
            if (currentKeys[i]){
                if( keys[i] == KeyState.RELEASED )
                    keys[i] = KeyState.ONCE;
                else
                    keys[i] = KeyState.PRESSED;
            } else {
                keys[i] = KeyState.RELEASED;
            }
        }
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < KEY_COUNT) {
            // The key has at least been pressed once.
            currentKeys[ keyCode ] = true;
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >=0 && keyCode < KEY_COUNT){
            // The key has been released.
            currentKeys[keyCode] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Not needed.
    }

    /**
     * Is the user currently pressing this key down?
     * @param keyCode The code of the key.
     * @return true if the user is currently pressing this key down. Otherwise return false.
     */
    public boolean keyDown(int keyCode){
        return keys[keyCode] == KeyState.ONCE || keys[keyCode] == KeyState.PRESSED;
    }

    /**
     * Has the user pressed this key once?
     * @param keyCode The code of the key.
     * @return true if the user has pressed this key once. Otherwise return false.
     */
    public boolean keyDownOnce(int keyCode){
        return keys[keyCode] == KeyState.ONCE;
    }

}
