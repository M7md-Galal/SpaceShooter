/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spaceshooter;
import java.awt.*;

public class Enemy {
    private int x, y;
    private final int speed = 2;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        y += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 40, 40);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
