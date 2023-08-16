package gameModePowers;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

import game.GamePanel;
import game.Paddle;
import gameInterface.Intro;
public class GamePowers extends GamePanel{
		
	public GamePowers(){
		super();
	}
	@Override
	public void newPaddles() {
		paddle1 = new PaddlePower(65, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new PaddlePower(GAME_WIDTH - PADDLE_WIDTH-65, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);

	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}
	@Override
	public void paint(Graphics g) {
		try {
			image = ImageIO.read(new File("src/resources/images/pozadinaPowers5.jpg"));
			graphics = image.getGraphics();
			draw(graphics);
			g.drawImage(image, 0, 0, this);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if (ball.intersects(paddle1)) {
			if(!Intro.sound.isMute()) hitSound.audioStart();
			if(paddle1.powerPush) {
				ball.setXDirection(ball.getXVelocity()*(-1)+4);
				ball.setYDirection(0);
				paddle1.powerPush=false;
		        
			}else {
				ball.setXVelocity(Math.abs(ball.getXVelocity()));
				ball.setXVelocity(ball.getXVelocity() + 1);// ubrzanje nakon udarca
				if (ball.getYVelocity() > 0) {
					ball.setYVelocity(ball.getYVelocity() + 1);// ubrzanje nakon udarca
				} 
				else ball.setYVelocity(ball.getYVelocity() - 1);

				ball.setXDirection(ball.getXVelocity());
				ball.setYDirection(ball.getYVelocity());
			}
			if(paddle1.powerBlock) {
				paddle1.powerBlock=false;
				paddle1.y=(GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2);
				paddle1.height=PADDLE_HEIGHT;
			}

			}
			
		if (ball.intersects(paddle2)) {
			if(!Intro.sound.isMute()) hitSound.audioStart();
			if(paddle2.powerPush) {
				ball.setXDirection(ball.getXVelocity()*(-1)-4);
				ball.setYDirection(0);
				paddle2.powerPush=false;
			    
			}else {
				ball.setXVelocity(Math.abs(ball.getXVelocity()));
				ball.setXVelocity(ball.getXVelocity() + 1);// ubrzanje nakon udarca
				if (ball.getYVelocity() > 0) {
					ball.setYVelocity(ball.getYVelocity() + 1);// ubrzanje nakon udarca
				} 
				else ball.setYVelocity(ball.getYVelocity() - 1);

				ball.setXDirection(-ball.getXVelocity());
				ball.setYDirection(ball.getYVelocity());
			}
			if(paddle2.powerBlock) {
				paddle2.powerBlock=false;
				paddle2.y=(GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2);
				paddle2.height=PADDLE_HEIGHT;
			}
		}

		// paddle in the edges
		if (paddle1.y <= 0)
			paddle1.y = 0;
		if (paddle1.y >= (GAME_HEIGHT - paddle1.height))
			paddle1.y = GAME_HEIGHT -  paddle1.height;
		if (paddle2.y <= 0)
			paddle2.y = 0;
		if (paddle2.y >= (GAME_HEIGHT -  paddle2.height))
			paddle2.y = GAME_HEIGHT -  paddle2.height;

		// scoring system
		
		if(ball.x<=65) {
			if(!Intro.sound.isMute()) errorSound.audioStart();
		if (brojac2<2) {
			score.player2++;
			brojac2++;
			newPaddles();
			newBall();
			paddle1.resetPowers();
			paddle2.resetPowers();
		
		}else {
			score.player2=score.player2+2;
			newPaddles();
			newBall();
			paddle1.resetPowers();
			paddle2.resetPowers();
			brojac2=0;
			addGif();
			//kad da treci za redom(bez da ga drugi prekine dobice +2 umesto +1 
			
		}
		}else if (brojac1>0)brojac2=0;
		
		
		
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER-65) {
			if(!Intro.sound.isMute()) errorSound.audioStart();
			
			if(brojac1<2) {
			score.player1++;
			newPaddles();
			newBall();
			paddle1.resetPowers();
			paddle2.resetPowers();
			brojac1++;

			}else {
				score.player1=score.player1+2;
				newPaddles();
				newBall();
				paddle1.resetPowers();
				paddle2.resetPowers();
				brojac1=0;
				addGif();

			}
		}else if (brojac2>0)brojac1=0;
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
