import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements Runnable, KeyListener {

    private Thread thread;
    private Player p;

    public void init() {
        setFocusable(true);
        this.addKeyListener(this);
        p = new Player();
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.FRAME_WIDTH, Window.FRAME_HEIGHT);

        p.draw(g);
    }

    @Override
    public void run() {
        while (true) {
            p.update();

            repaint();

            try {
                thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            p.setLeftAccel(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            p.setRightAccel(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            p.setLeftAccel(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            p.setRightAccel(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
