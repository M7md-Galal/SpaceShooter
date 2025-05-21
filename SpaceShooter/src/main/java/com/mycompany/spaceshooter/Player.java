/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spaceshooter;
import java.awt.*;

/**
 *
 * @author Mohamed Galal
*/

public class Player {
    private int x, y;
    private boolean left, right;
    private final int speed = 10;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        if(left && x > 0)
        {
             x -= speed;
        }
        if(right && x < 750)
        {
             x += speed;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 40, 40);
    }

    public void setLeft(boolean b) 
    {
        left = b;
    }
    public void setRight(boolean b) 
    {
        right = b; 
    }
    public int getX() 
    {
        return x;
    }
    public int getY() 
    {
        return y; 
    }
}
