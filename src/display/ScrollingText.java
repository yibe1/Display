/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

/**
 *
 * @author yibe
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScrollingText extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;
    private static int WIDTH = 800;
    private static int HEIGHT = 600;
    private static final int SPEED = 2;
    private static final String TEXT = "የሚታዘዝልዎትን መድሃኒት በአግባቡ በመጠቀም የመልካም ስነ-ምግባር ባለቤት መሆንዎን ያስመስክሩ። …………Qooricha isniif ajajame sirnaan waan fudhatannif, namuusa gaarii qabaach keessaniif isin galateeffana!!.....By using your medication responsibly, show you are a moral person. ";
    private static final Font FONT = new Font("Geez able", Font.BOLD, 35);
    private int x;

    public ScrollingText(int xx, int yy) {
        HEIGHT = yy;
        WIDTH = xx;
        x = WIDTH;
        this.setBackground(new Color(128, 0, 128));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            x -= SPEED;
            if (x < -getFontMetrics(FONT).stringWidth(TEXT)) {
                x = WIDTH+1000;
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.orange);
        g2d.setFont(FONT);
        g2d.drawString(TEXT, x, HEIGHT / 2);
        g2d.dispose();
    }

//  public static void main(String[] args) {
//    JFrame frame = new JFrame("Scrolling Text");
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    frame.add(new ScrollingText());
//    frame.pack();
//    frame.setLocationRelativeTo(null);
//    frame.setVisible(true);
//  }
}
