/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.spaceshooter;
import javax.swing.JFrame;

/**
 *
 * @author Mohamed Galal
*/
public class SpaceShooter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Shooter");
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
