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
import gameServer.Client_handler;
import packet.GamePowersPacket;
@SuppressWarnings("serial")
public class GamePowers extends GamePanel{
		
	
//	public boolean soundPower = false;
	
	public GamePowers(Client_handler p1,Client_handler p2){
		super(p1,p2);
		paddle1 = new PaddlePower(65, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new PaddlePower(GAME_WIDTH - PADDLE_WIDTH-65, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
	}
	@Override
	public void newPaddles() {
		paddle1 = new PaddlePower(65, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new PaddlePower(GAME_WIDTH - PADDLE_WIDTH-65, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);

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

		//	if(!Intro.sound.isMute()) hitSound.audioRestart();  !!!!!!!!

			if(paddle1.powerPush) {
//				soundPower=true;
				paddle1.soundPower=true;
				
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

		//	if(!Intro.sound.isMute()) hitSound.audioRestart();  !!!!!

			if(paddle2.powerPush) {
				paddle2.soundPower=true;
				
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

		//	if(!Intro.sound.isMute()) errorSound.audioRestart();   !!!!

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
			
			gifFlag=true;
			
		}
		}else if (brojac1>0)brojac2=0;
		
		
		
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER-65) {

		//	if(!Intro.sound.isMute()) errorSound.audioRestart();   !!!! 

			
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
				
				gifFlag=true;

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
				try {
					updateClient();
					////////
					gifFlag=false;
					paddle1.soundPower=false;
					paddle2.soundPower=false;
				}catch(IOException e) {
					break;
				}
				if(score.player1>=6 || score.player2>=6)break;
				checkCollision();
				delta--;
			}
		}
	
	}
	private void updateClient() throws IOException{
		int []paddles= {paddle1.x,paddle1.y,paddle1.width,paddle1.height,
				paddle2.x,paddle2.y,paddle2.width,paddle2.height};
		int []ballscore= {ball.x,ball.y,score.player1,score.player2};
		boolean []flags1= paddle1.getFlags();
		boolean []flags2= paddle2.getFlags();
		GamePowersPacket packet=new GamePowersPacket(paddles, ballscore, flags1, flags2, gifFlag, paddle1.soundPower, paddle2.soundPower);
		player1.updatePlayer(packet);
		player2.updatePlayer(packet);
	}
}
