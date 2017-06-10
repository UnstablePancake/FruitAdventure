import java.awt.*;

public class HUD {

    public static int health = 200;
    public static int score = 0;

    public static void drawHUD(Graphics g) {
        if (!Panel.aiActive) {
            g.setColor(Color.WHITE);
            g.fillRect(15, 450, 40, 210);

            g.setColor(Color.BLACK);
            g.fillRect(20, 455, 30, 200);

            g.setColor(Color.GREEN);
            g.fillRect(20, 455 + (200 - health), 30, health);

            g.setColor(Color.WHITE);
            g.drawString("Score: " + String.valueOf(HUD.score), 10, 20);
        }
    }
}
