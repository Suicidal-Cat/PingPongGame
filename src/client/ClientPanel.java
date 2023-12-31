package client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Ball;
import game.Paddle;
import game.Score;
import gameInterface.Intro;
import gameSound.Sound;
import packet.GamePacket;

public class ClientPanel extends JPanel{

	
	private static final long serialVersionUID = 4096717905217397314L;
	protected static final int GAME_WIDTH = 1100;
	protected static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
	protected static final int BALL_DIAMETER = 20;
	protected static final int PADDLE_WIDTH = 25;
	protected static final int PADDLE_HEIGHT = 100;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	Image image;
	Graphics graphics;
	public Paddle paddle1;
	public Paddle paddle2;
	public Ball ball;
	public Score score;
	Sound sound;
	protected Sound hitSound = new Sound("hit.wav");
	protected Sound errorSound = new Sound("error.wav");
	static Sound sound1;
	protected boolean gif;
	
	public ClientPanel() {

		if(!Intro.sound.isMute()) {
			sound=new Sound("gamePlay.wav");
			sound.audioStart();
			sound.audioLoop();
		}

		this.setFocusable(true);
		this.setPreferredSize(SCREEN_SIZE);
		paddle1 = new Paddle(10, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH-10, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
		ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), GAME_HEIGHT/2,
				BALL_DIAMETER, BALL_DIAMETER);
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
	}
	
	
	public void paint(Graphics g) {
		try {
		    image = ImageIO.read(new File("src/resources/images/pozadinaIgrice.jpg"));
			graphics = image.getGraphics();
			draw(graphics);
			g.drawImage(image, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	public void updateComponents(Object packet1) throws IOException {
		GamePacket packet=(GamePacket)packet1;
		paddle1.x=packet.xp1;
		paddle1.y=packet.yp1;
		paddle2.x=packet.xp2;
		paddle2.y=packet.yp2;
		ball.x=packet.xBall;
		ball.y=packet.yBall;
		score.player1=packet.p1Score;
		score.player2=packet.p2Score;
		
		gif=packet.gifFlag;
		
		checkCollision();
		repaint();
		if(packet.p1Score>=6 || packet.p2Score>=6) {
			throw new IOException("Kraj igre");
		}
	}
	public void checkCollision() {
		//odavde radi Intro.sound.isMute() tj Intro.sound nije null
		if (ball.intersects(paddle1)) {
			if(!Intro.sound.isMute()) {
//				System.out.println("hit --" + Intro.sound.isMute());
				hitSound.audioRestart();
			}
		}
		if (ball.intersects(paddle2)) {
			if(!Intro.sound.isMute()) {
//				System.out.println("hit --" + Intro.sound.isMute());
				hitSound.audioRestart();
			}
		}
		
		
		if(ball.x <= 0) {
//			System.out.println("radi error1");
			if(!Intro.sound.isMute()) errorSound.audioRestart();			
		}
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
//			System.out.println("radi error2");
			if(!Intro.sound.isMute()) errorSound.audioRestart();
		}
		
//		System.out.println("2:gif = " + gif);
		if(gif) {
			addGif();
		}
		
	}
	
	public void addGif() {
	    Icon icon = new ImageIcon("src/resources/images/minions.gif");
	    JLabel label = new JLabel(icon);
	 
	    JFrame f = new JFrame("Animation");
	    f.getContentPane().add(label);
	  	f.setUndecorated(true);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.pack();
	    f.setLocationRelativeTo(this);
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
	
	
}
