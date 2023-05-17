/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

/**
 *
 * @author Jimma University
 */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;
import javax.swing.*;

public class ImagePanel extends JPanel {
    private Image image;
    private Timer timer;
    private String[] images;
    private Random rand;

    public ImagePanel(String folderPath) {
        File folder = new File(folderPath);
        images = folder.list();
        rand = new Random();
        loadImage(folderPath + File.separator + images[rand.nextInt(images.length)]);
        timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadImage(folderPath + File.separator + images[rand.nextInt(images.length)]);
                repaint();
            }
        });
        timer.start();
    }

    private void loadImage(String imagePath) {
        image = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int width = getWidth();
            int height = getHeight();
            g.drawImage(image, 0, 0, width, height, this);
        }
    }
}
