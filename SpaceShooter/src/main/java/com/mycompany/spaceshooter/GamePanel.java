/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spaceshooter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Player player;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private boolean inGame = false;
    private boolean gameOver = false;
    private int score = 0;
    private int level = 1;
    private int spawnTimer = 0;
    private int spawnInterval = 100;
    private int health = 4;

    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);
        setBackground(Color.BLACK);
        timer = new Timer(15, this);
        timer.start();
    }

    private void initGame() {
        player = new Player(375, 500);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        spawnTimer = 0;
        spawnInterval = 100;
        score = 0;
        level = 1;
        health = 4;
        gameOver = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!inGame) {
            drawStartScreen(g);
        } else if (gameOver) {
            drawGameOver(g);
        } else {
            drawGame(g);
        }
    }

    private void drawStartScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Space Shooter", 270, 200);
        g.setFont(new Font("Arial", Font.PLAIN, 35));
        g.drawString("Start Game", 300, 300);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Game Over", 290, 200);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + score, 350, 250);
        g.drawString("Replay the game", 280, 300);
    }

    private void drawGame(Graphics g) {
        player.draw(g);

        for (Bullet b : bullets)
            b.draw(g);
        for (Enemy e : enemies)
            e.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
        g.drawString("Level: " + level, 10, 40);
        g.drawString("Health: " + health, 10, 60);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!inGame || gameOver) 
            return;

        player.update();

        Iterator<Bullet> bIter = bullets.iterator();
        while (bIter.hasNext()) {
            Bullet b = bIter.next();
            b.update();
            if (b.getY() < 0)
                bIter.remove();
        }

        spawnTimer++;
        if (spawnTimer >= spawnInterval) {
            enemies.add(new Enemy(new Random().nextInt(750), 0));
            spawnTimer = 0;
        }

        Iterator<Enemy> eIter = enemies.iterator();
        while (eIter.hasNext()) {
            Enemy enemy = eIter.next();
            enemy.update();

            if (enemy.getY() > 600) {
                health--;
                eIter.remove();
                if (health <= 0) {
                    gameOver = true;
                    inGame = false;
                }
            }

            Rectangle er = new Rectangle(enemy.getX(), enemy.getY(), 40, 40);
            for (Bullet b : bullets) {
                Rectangle br = new Rectangle(b.getX(), b.getY(), 5, 10);
                if (er.intersects(br)) {
                    eIter.remove();
                    bullets.remove(b);
                    score += 10;
                    if (score % 100 == 0) {
                        level++;
                        spawnInterval = Math.max(20, spawnInterval - 10);
                    }
                    break;
                }
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();

        if (!inGame) {
            if (k == KeyEvent.VK_ENTER) {
                inGame = true;
                gameOver = false;
                initGame();
            }
            return;
        }

        if (k == KeyEvent.VK_LEFT) player.setLeft(true);
        if (k == KeyEvent.VK_RIGHT) player.setRight(true);
        if (k == KeyEvent.VK_SPACE) bullets.add(new Bullet(player.getX() + 18, player.getY()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_LEFT) player.setLeft(false);
        if (k == KeyEvent.VK_RIGHT) player.setRight(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
