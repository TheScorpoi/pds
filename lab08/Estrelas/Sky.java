package Estrelas;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Sky extends JFrame {
    private List<Star> stars = new ArrayList<>();

    public void placeStar(Star star) {
        
        stars.add(star);
    }

    @Override
    public void paint(Graphics graphics) {
        for (Star star : stars) {
            star.draw(graphics);
        }
    }
}