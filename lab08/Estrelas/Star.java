package Estrelas;

import java.awt.Graphics;

import Estrelas.startypes.StarType;

public class Star {
    
    private int x, y;
    private StarType starType;

    public Star(int x, int y, StarType starType) {
        this.x = x;
        this.y = y;
        this.starType = starType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(this.starType.getColor());
        g.fillOval(this.x, this.y, this.starType.getSize(), this.starType.getSize());
    }
}
