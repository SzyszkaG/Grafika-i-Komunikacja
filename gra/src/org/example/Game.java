package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

class Position{
    int x,y;
    Rectangle bounds;

    public Position(int x,int y){
        setPosition(x,y);
    }

    public void setPosition(int x, int y) {
        this.x=x;
        this.y=y;
        if(bounds!=null){
            bounds.setLocation(x,y);
        }
    }

    public void setBoundsSize(int SZEROKOSC,int WYSOKOSC){
        bounds = new Rectangle(x,y,SZEROKOSC,WYSOKOSC);
    }
}

public class Game extends JPanel implements Runnable, KeyListener {
    final int SZEROKOSC = 520;
    final int WYSOKOSC = 450;

    boolean isRunning;
    Thread thread;

    BufferedImage view;
    Graphics g;

    BufferedImage background, paddle, ball, block, gameOver;
    int n = 0;
    Position[] blocksPosition;
    Position paddlePosition;


    boolean right, left;
    int paddleX, paddleY;
    int ballX, ballY;
    int ball0x, ball0y;

    public Game() {
        setPreferredSize(new Dimension(SZEROKOSC, WYSOKOSC));
        addKeyListener(this);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            isRunning = true;
            thread.start();
        }
    }

    public void start() {
        try {
            view = new BufferedImage(SZEROKOSC, WYSOKOSC, BufferedImage.TYPE_INT_RGB);
            g = (Graphics2D) view.getGraphics();
            background = ImageIO.read(getClass().getResource("/images/background.jpg"));
            paddle = ImageIO.read(getClass().getResource("/images/paddle.png"));
            ball = ImageIO.read(getClass().getResource("/images/ball.png"));
            block = ImageIO.read(getClass().getResource("/images/block04.png"));
            gameOver = ImageIO.read(getClass().getResource("/images/gameover.png"));


            blocksPosition = new Position[100];
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    blocksPosition[n] = new Position(i * 43, j * 20);
                    blocksPosition[n].setBoundsSize(block.getWidth(), block.getHeight());
                    n++;
                }

            }

            paddleX = (SZEROKOSC / 2) - (paddle.getWidth() / 2);
            paddleY = (WYSOKOSC - paddle.getHeight());
            paddlePosition = new Position(paddleX, paddleY);
            paddlePosition.setBoundsSize(paddle.getWidth(), paddle.getHeight());

            ballX = (SZEROKOSC / 2 - ball.getWidth() / 2);
            ballY = (WYSOKOSC - ball.getHeight());

            ball0x = -(new Random().nextInt(4) % 4 + 3);
            ball0y = -5;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        ballX += ball0x;
        for (int i = 0; i < n; i++) {
            if (new Rectangle((ballX + 3), (ballY + 3), 6, 6).intersects(blocksPosition[i].bounds)) {
                blocksPosition[i].setPosition(-100, 0);
                ball0x = -ball0x;
            }
        }
        ballY += ball0y;
        for (int i = 0; i < n; i++) {
            if (new Rectangle((ballX + 3), (ballY + 3), 6, 6).intersects(blocksPosition[i].bounds)) {
                blocksPosition[i].setPosition(-100, 0);
                ball0y = -ball0y;
            }
        }

        if (ballX < 0 || ballX > SZEROKOSC - ball.getWidth()) {
            ball0x = -ball0x;
        }
        if (ballY < 0 || ballY > WYSOKOSC - ball.getHeight()) {
            ball0y=-ball0y;
        }

        if (ballY > WYSOKOSC - ball.getHeight()) {
            isRunning = false;
        }

        if (right) {
            paddleX += 6;
        }
        if (left) {
            paddleX -= 6;
        }
        if (paddleX >= SZEROKOSC - paddle.getWidth()) {
            paddleX = SZEROKOSC - paddle.getWidth();
        }
        if (paddleX <= 0) {
            paddleX = 0;
        }

        if (new Rectangle(ballX, ballY, 12, 12).intersects(paddlePosition.bounds)) {
            ball0y = -(new Random().nextInt(4) % 4 + 3);
        }
        paddlePosition.setPosition(paddleX, paddleY);

    }

    public void draw() {
        g.drawImage(background, 0, 0, SZEROKOSC, WYSOKOSC, null);
        g.drawImage(ball, ballX, ballY, ball.getWidth(), ball.getHeight(), null);
        g.drawImage(paddle, paddleX, paddleY, paddle.getWidth(), paddle.getHeight(), null);

        for (int i = 0; i < n; i++) {
            g.drawImage(block, blocksPosition[i].x, blocksPosition[i].y, block.getWidth(), block.getHeight(), null);
        }

        if (!isRunning) {
            g.drawImage(gameOver,
                    (SZEROKOSC / 2) - (gameOver.getWidth() / 2),
                    (WYSOKOSC / 2) - (gameOver.getHeight() / 2),
                    gameOver.getWidth(),
                    gameOver.getHeight(),
                    null
            );
        }
        Graphics g2 = getGraphics();
        g2.drawImage(view, 0, 0, SZEROKOSC, WYSOKOSC, null);
        g2.dispose();
    }


    @Override
    public void run() {
        requestFocus();
        start();
        while (isRunning) {
            update();
            draw();
            try {
                Thread.sleep(1000 / 70);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_LEFT:
                left = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
        }
    }
}

