import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ConnectTheDotsGame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private ArrayList<Point> dots;
    private boolean isDrawing;

    public ConnectTheDotsGame() {
        setTitle("Connect the Dots");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dots = new ArrayList<>();
        isDrawing = false;

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                dots.add(e.getPoint());
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (dots.size() > 0) {
                    int lastIndex = dots.size() - 1;
                    Point lastDot = dots.get(lastIndex);
                    Point currentDot = e.getPoint();
                    if (!currentDot.equals(lastDot)) {
                        dots.add(currentDot);
                        repaint();
                    }
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);

        for (int i = 0; i < dots.size(); i++) {
            Point dot = dots.get(i);
            g.fillOval(dot.x - 5, dot.y - 5, 10, 10);

            if (i > 0) {
                Point prevDot = dots.get(i - 1);
                g.drawLine(prevDot.x, prevDot.y, dot.x, dot.y);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ConnectTheDotsGame game = new ConnectTheDotsGame();
                game.setVisible(true);
            }
        });
    }
}
