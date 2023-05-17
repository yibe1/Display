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
import java.io.*;
import javax.swing.*;

public class RandomImage implements Runnable {
    private Timer timer;
    private JLabel label;
    private File[] files;
    private int index;

    public RandomImage(JPanel panel, JLabel label) {
        this.label = label;
        panel.add(label);
        timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (files != null && files.length > 0) {
                    index = (index + 1) % files.length;
                    showImage();
                }
            }
        });
        timer.start();
        File dir = new File("Images");
        files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".jpg");
            }
        });
        if (files != null && files.length > 0) {
            index = (int) (Math.random() * files.length);
            showImage();
        }
    }

    private void showImage() {
        ImageIcon icon = new ImageIcon(files[index].getPath());
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        label.setIcon(icon);
    }

    @Override
    public void run() {
//        JPanel panel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                if (label.getIcon() != null) {
//                    int x = (getWidth() - label.getIcon().getIconWidth()) / 2;
//                    int y = (getHeight() - label.getIcon().getIconHeight()) / 2;
//                    g.drawImage(((ImageIcon) label.getIcon()).getImage(), x, y, this);
//                }
//            }
//        };
//        frame.add(panel);
//        RandomImage randomImage = new RandomImage(panel);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        Thread thread = new Thread(new RandomImage(null));
//        thread.start();
//    }
}