package game;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import client.ClientPanel;
import gameInterface.Intro;
import gameServer.Client_handler;
import gameSound.Sound;
import packet.GamePacket;

import javax.sound.sampled.*;

public class GamePanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8477109583716358379L;
	protected static final int GAME_WIDTH = 1100;
	protected static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	protected static final int BALL_DIAMETER = 20;
	protected static final int PADDLE_WIDTH = 25;
	protected static final int PADDLE_HEIGHT = 100;
	Thread gameThread;
	protected Image image;
	protected Graphics graphics;
	Random random;
	public Paddle paddle1;
	public Paddle paddle2;
	protected Ball ball;
	protected Score score;
	protected int brojac1,brojac2;
	protected Client_handler player1,player2;
	
	public GamePanel(Client_handler p1,Client_handler p2) {

		player1=p1;
		player2=p2;
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
	//	this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);		
	}
	
	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();

	};

	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER),
				BALL_DIAMETER, BALL_DIAMETER);

	}

	public void newPaddles() {
		paddle1 = new Paddle(10, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH-10, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);

	}

/*	public void paint(Graphics g) {
		try {
			image = ImageIO.read(new File("src/resources/images/pozadinaIgrice.jpg"));
			graphics = image.getGraphics();
			draw(graphics);
			g.drawImage(image, 0, 0, this);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}*/

	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	public void addGif() {
	    Icon icon = new ImageIcon("src/resources/images/minions.gif");
	    JLabel label = new JLabel(icon);
	 
	    JFrame f = new JFrame("Animation");
	    f.getContentPane().add(label);
	  	f.setUndecorated(true);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.pack();
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);
	    //f.setTitle("Ostvarili ste bonus +1");
	    ImageIcon image=new ImageIcon("src/resources/images/arcade1.png");
	  	//f.setIconImage(image.getImage());
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    f.dispose();
	}
	public void checkCollision() {
		// ball bouncing of edges
		if (ball.y <= 0) {
			ball.setYDirection(-ball.getYVelocity());
		}
		if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
			ball.setYDirection(-ball.getYVelocity());
		}
		// bounces ball off paddles ovaj deo moze bolje
		if (ball.intersects(paddle1)) {
			
			ball.setXVelocity(Math.abs(ball.getXVelocity()));
			ball.setXVelocity(ball.getXVelocity() + 1);// ubrzanje nakon udarca
			if (ball.getYVelocity() > 0) {
				ball.setYVelocity(ball.getYVelocity() + 1);// ubrzanje nakon udarca
			} 
			else ball.setYVelocity(ball.getYVelocity() - 1);

			ball.setXDirection(ball.getXVelocity());
			ball.setYDirection(ball.getYVelocity());
		}
		if (ball.intersects(paddle2)) {
			
			ball.setXVelocity(Math.abs(ball.getXVelocity()));
			ball.setXVelocity(ball.getXVelocity() + 1);// ubrzanje nakon udarca
			if (ball.getYVelocity() > 0) {
				ball.setYVelocity(ball.getYVelocity() + 1);// ubrzanje nakon udarca
			} 
			else ball.setYVelocity(ball.getYVelocity() - 1);

			ball.setXDirection(-ball.getXVelocity());
			ball.setYDirection(ball.getYVelocity());
		}

		// paddle in the edges
		if (paddle1.y <= 0)
			paddle1.y = 0;
		if (paddle1.y >= (GAME_HEIGHT - paddle1.height))
			paddle1.y = GAME_HEIGHT - paddle1.height;
		if (paddle2.y <= 0)
			paddle2.y = 0;
		if (paddle2.y >= (GAME_HEIGHT - paddle2.height))
			paddle2.y = GAME_HEIGHT - paddle2.height;

		// scoring system
		
		if(ball.x <= 0) {
//			if(!Intro.sound.isMute()) {
//				ClientPanel.playSound("error.wav");
//				System.out.println("error 1 zvuk");
//			}//u ifu nece a ovako ispod hoce.....
//			ClientPanel.playSound("error.wav");
//			System.out.println("error 1 zvuk");
			
			if (brojac2<2) {
				score.player2++;
				brojac2++;
				newPaddles();
				newBall();
		
			}else {
				score.player2=score.player2+2;
				newPaddles();
				newBall();
				brojac2=0;
				addGif();
				//kad da treci za redom(bez da ga drugi prekine dobice +2 umesto +1 
			
			}
		}else if (brojac1>0)brojac2=0;
		
		
		
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
//			if(!Intro.sound.isMute()) {
//				ClientPanel.playSound("error.wav");
//				System.out.println("error 2 zvuk");
//			}//u ifu nece a ovako ispod hoce.....
//			ClientPanel.playSound("error.wav");
//			System.out.println("error 1 zvuk");
			
			
		
			if(brojac1<2) {
				score.player1++;
				newPaddles();
				newBall();
				brojac1++;

			}else {
				score.player1=score.player1+2;
				newPaddles();
				newBall();
				brojac1=0;
				addGif();

			}
		}else if (brojac2>0)brojac1=0;

	}

	public void run() {
		// game loop
		long lastTime = System.nanoTime();
		double amountofTicks = 60.0;
		double ns = 1000000000 / amountofTicks;
		double delta = 0;
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				move();
//				checkCollision();
			//	repaint();// poziva paint metodu
				GamePacket packet=new GamePacket(paddle1.x,paddle1.y, paddle2.x,paddle2.y,
						ball.x,ball.y, score.player1,score.player2);
				try {
					player1.updatePlayer(packet);
					player2.updatePlayer(packet);
				}catch(IOException e) {
					break;
				}
				checkCollision();
				if(score.player1>=6 || score.player2>=6)break;
				delta--;
			}
		}
	}

/*	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}*/

}
