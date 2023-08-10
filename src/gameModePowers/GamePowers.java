package gameModePowers;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.GamePanel;
import game.Paddle;
public class GamePowers extends GamePanel{
	
	Image pushP1;
	Image speedP1;
	Image blockP1;
	Image pushP2;
	Image speedP2;
	Image blockP2;
	static final int POWER_SIZE=45;
	static final int POWER_POSITIONX=5;
	
	public GamePowers(){
		initImages();
	}
	@Override
	public void newPaddles() {
		paddle1 = new PaddlePower(60, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new PaddlePower(GAME_WIDTH - PADDLE_WIDTH-60, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);

	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		drawImages(g);
	}
	@Override
	public void move() {
		super.move();
	}
	@Override
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
			if(paddle1.powerPush) {
				ball.setXDirection(ball.getXVelocity()*(-1)+4);
				ball.setYDirection(0);
				paddle1.powerPush=false;
			}else {
				ball.setXVelocity(Math.abs(ball.getXVelocity())+1);
				if (ball.getYVelocity() > 0) {
					ball.setYVelocity(ball.getYVelocity() + 1);// ubrzanje nakon udarca
				} 
				else ball.setYVelocity(ball.getYVelocity() - 1);

				ball.setXDirection(ball.getXVelocity());
				ball.setYDirection(ball.getYVelocity());
			}

			}
			
		if (ball.intersects(paddle2)) {
			if(paddle2.powerPush) {
				ball.setXDirection(ball.getXVelocity()*(-1)-4);
				ball.setYDirection(0);
				paddle2.powerPush=false;
			}else {
				ball.setXVelocity(Math.abs(ball.getXVelocity())+1);
				if (ball.getYVelocity() > 0) {
					ball.setYVelocity(ball.getYVelocity() + 1);// ubrzanje nakon udarca
				} 
				else ball.setYVelocity(ball.getYVelocity() - 1);

				ball.setXDirection(-ball.getXVelocity());
				ball.setYDirection(ball.getYVelocity());
			}
		}

		// paddle in the edges
		if (paddle1.y <= 0)
			paddle1.y = 0;
		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		if (paddle2.y <= 0)
			paddle2.y = 0;
		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

		// scoring system
		
		if(ball.x <= 0) {
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
	public void initImages() {
		 try {
			 pushP1=ImageIO.read(new File("src/resources/images/4329547.png"));
			 speedP1=ImageIO.read(new File("src/resources/images/4329547.png"));
			 blockP1=ImageIO.read(new File("src/resources/images/4329547.png"));
			 pushP2=ImageIO.read(new File("src/resources/images/4329547.png"));
			 speedP2=ImageIO.read(new File("src/resources/images/4329547.png"));
			 blockP2=ImageIO.read(new File("src/resources/images/4329547.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
	public void drawImages(Graphics g) {
		g.drawImage(pushP1,POWER_POSITIONX,40,POWER_SIZE,POWER_SIZE,null);
		g.drawImage(speedP1,POWER_POSITIONX,120,POWER_SIZE,POWER_SIZE,null);
		g.drawImage(blockP1,POWER_POSITIONX,200,POWER_SIZE,POWER_SIZE,null);
		g.drawImage(pushP2,GAME_WIDTH-POWER_POSITIONX-50,40,POWER_SIZE,POWER_SIZE,null);
		g.drawImage(speedP2,GAME_WIDTH-POWER_POSITIONX-50,120,POWER_SIZE,POWER_SIZE,null);
		g.drawImage(blockP2,GAME_WIDTH-POWER_POSITIONX-50,200,POWER_SIZE,POWER_SIZE,null);
	}
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
