/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spaceshooter;
import java.awt.*;

public class Bullet {
    private int x, y;
    private final int speed = 8;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        y -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 5, 10);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
