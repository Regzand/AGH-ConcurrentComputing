package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Display extends JFrame {

    private BufferedImage image;

    public Display(String title, BufferedImage image) {
        super(title);

        this.image = image;

        setBounds(100, 100, image.getWidth(), image.getHeight());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.image, 0, 0, this);
    }

}
