import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements Runnable, KeyListener {

    private Thread thread;
    private AI ai;
    private Player p;
    private Enemy[] enemies = new Enemy[5];
    private Bullet[] bullets = new Bullet[10];
    private boolean fireStatus;
    private int cooldown;
    private boolean inProgress = true;
    public static boolean aiActive = true;
    public boolean gameEnd = true;

    public void init() {
        setFocusable(true);
        this.addKeyListener(this);

        //create ai
        ai = new AI(enemies);

        //create player
        p = new Player();

        //create enemies
        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy();
        }

        // create bullets
        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = new Bullet();
        }

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paintComponent(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.FRAME_WIDTH, Window.FRAME_HEIGHT);

        HUD.drawHUD(g);

        // draw enemies
        for (Enemy e : enemies) {
            e.draw(g);
        }

        // draw bullets
        for (Bullet b : bullets) {
            if (b.isActive())
                b.draw(g);
        }

        if (HUD.health == 0) {
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER", (Window.FRAME_WIDTH / 2) - 25, Window.FRAME_HEIGHT / 2);
            g.drawString("Press ENTER to play again", (Window.FRAME_WIDTH / 2) - 70, Window.FRAME_HEIGHT - 300);
            inProgress = false;
            gameEnd = true;
        }

        if (aiActive) {
            g.setColor(Color.WHITE);
            g.drawString("Mr. Brunner's Space Adventure", (Window.FRAME_WIDTH / 2) - 75, Window.FRAME_HEIGHT / 2);
            g.drawString("Press ENTER to play", (Window.FRAME_WIDTH / 2) - 50, Window.FRAME_HEIGHT - 300);
        }

        // draw player/ai
        if (!aiActive)
            p.draw(g);
        else
            ai.draw(g);

    }

    @Override
    public void run() {
        while (true) {
            if (inProgress) {
                // player
                p.update();

                // ai
                ai.update();

                // enemies
                for (Enemy e : enemies) {
                    e.update();
                }

                // bullets
                for (Bullet b : bullets) {
                    b.update();

                    if (b.getY() <= 0)
                        b.setActive(false);

                    if (!aiActive) {
                        if (!b.isActive() && cooldown == 0 && fireStatus) {
                            shoot(b);
                            resetCooldown();
                            break;
                        }
                    } else {
                        if (!b.isActive() && cooldown == 0 && ai.getShoot()) {
                            shoot(b);
                            resetCooldown();
                            break;
                        }
                    }
                }

                // check for bullet and enemy collision
                for (Enemy e : enemies) {
                    for (Bullet b : bullets) {
                        if (b.isActive() && collision(b, e)) {
                            HUD.score++;
                            b.setActive(false);
                            e.reset();
                        }
                    }
                }

                // lower cooldown
                if (cooldown > 0)
                    cooldown--;

                repaint();
            }
            try {
                thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // key events
    @Override
    public void keyPressed(KeyEvent e) {
        if (!aiActive) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                p.setLeftAccel(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                p.setRightAccel(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                fireStatus = true;
            }
        }
        if (gameEnd && e.getKeyCode() == KeyEvent.VK_ENTER) {
            startGame();
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
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            fireStatus = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private void shoot(Bullet b) {
        b.setActive(true);
        b.setyVel(5);
        if (!aiActive) {
            b.setX(p.getX() + (p.getWidth() / 2));
            b.setY(p.getY());
        } else {
            b.setX(ai.getX() + (ai.getWidth() / 2));
            b.setY(ai.getY());
        }
    }

    private void resetCooldown() {
        cooldown = 25;
    }

    public boolean collision(Bullet b, Enemy e) {
        return b.getBounds().intersects(e.getBounds());
    }

    public void startGame() {
        inProgress = true;
        if (aiActive) {
            aiActive = !aiActive;
        }

        for (Enemy e : enemies) {
            e.reset();
        }

        for (Bullet b : bullets) {
            b.reset();
        }

        HUD.score = 0;
        HUD.health = 200;

        gameEnd = false;
    }
}
