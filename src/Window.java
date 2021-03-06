import javax.swing.*;

public class Window {

    public static final int FRAME_WIDTH = 750;
    public static final int FRAME_HEIGHT = 700;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mr. Brunner's Space Adventure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Panel panel = new Panel();
        frame.add(panel);
        panel.init();
        frame.setVisible(true);
    }
}
