package gameModeAdvanced;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import client.ClientPanel;
import game.GamePanel;
import game.Paddle;
import gameInterface.Intro;
import gameServer.Client_handler;
import gameSound.Sound;
import packet.GameAdvancePacket;

public class GameAdvanced extends GamePanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 931036142327098826L;
	Random random;
	Barrier barrier;
	Barrier barrier1;
	Barrier barrier2;
	BuffEffect effect;
	boolean flag = false;
	static final int BARRIER_WIDTH = 30;
	static final int BARRIER_HEIGHT = 160;
	static final int EFFECT_SIZE=30;
	
	public GameAdvanced(Client_handler p1,Client_handler p2){
		super(p1,p2);
		newBarrier();
		
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	effect = newEffect();
            }
        };
        javax.swing.Timer timer = new javax.swing.Timer(5000, taskPerformer);
        timer.setRepeats(true);
        timer.start();
        timer.setDelay(10000);

	}
	
	public void newBarrier() {
		random = new Random();
		barrier = new Barrier(GAME_WIDTH/2 - BARRIER_WIDTH/2, random.nextInt(GAME_HEIGHT - BARRIER_HEIGHT), BARRIER_WIDTH, BARRIER_HEIGHT);

	}

	//generate random effect
	public BuffEffect newEffect() {
		random=new Random();
		int effectType;
		
		if(barrier==null)
			effectType=random.nextInt(3);
		else
			effectType=random.nextInt(4);
			
		switch(effectType) {
		case 0:
			return new QuestionMarkCoin(GAME_WIDTH/2 - EFFECT_SIZE/2, random.nextInt(GAME_HEIGHT-EFFECT_SIZE), EFFECT_SIZE, EFFECT_SIZE);
		case 1:
			return new PaddleResizeCoin(GAME_WIDTH/2 - EFFECT_SIZE/2, random.nextInt(GAME_HEIGHT-EFFECT_SIZE), EFFECT_SIZE , EFFECT_SIZE);
		case 2:
			return new BallSpeedCoin(GAME_WIDTH/2 - EFFECT_SIZE/2, random.nextInt(GAME_HEIGHT-EFFECT_SIZE), EFFECT_SIZE, EFFECT_SIZE);
		case 3:
			return new Coin(GAME_WIDTH/2 - EFFECT_SIZE/2, random.nextInt(GAME_HEIGHT-EFFECT_SIZE), EFFECT_SIZE, EFFECT_SIZE);
		default: 
			return null;
		}
	}
	
	public void switchTerrain() {
		
		barrier=null;
		flag=true;
		
		barrier1 = new Barrier(GAME_WIDTH/2 - BARRIER_WIDTH/2 - BARRIER_WIDTH, 0, BARRIER_WIDTH, BARRIER_HEIGHT);
		barrier2 = new Barrier(GAME_WIDTH/2 - BARRIER_WIDTH/2 + BARRIER_WIDTH, GAME_HEIGHT-BARRIER_HEIGHT, BARRIER_WIDTH, BARRIER_HEIGHT);
		
	}
	
/*	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if(barrier!=null)
			barrier.draw(g);
		if(effect!=null)
			effect.draw(g);
		if(barrier1!=null && barrier2!=null) {
			barrier1.draw(g);
			barrier2.draw(g);
		}
	}*/
	@Override
	public void move() {
		super.move();
		if(!flag)
			barrier.move();
		if(effect!=null)
			effect.move();
		if(barrier1!=null && barrier2!=null) {
			barrier1.move();
			barrier2.move();
		}
	}
	@Override
	public void checkCollision() {
		
		if(barrier!=null) {
			// ball bouncing off barrier
			barrierCollision(barrier, GAME_WIDTH/2 - BARRIER_WIDTH/2);
			//barrier changing direction
			if(barrier.y <= 0 || barrier.y >= GAME_HEIGHT - BARRIER_HEIGHT)
				barrier.yVelocity=-barrier.yVelocity;
		}
		else {
			// ball bouncing off barrier
			barrierCollision(barrier1,barrier1.x);
			//barrier1 changing direction
			if(barrier1.y < 0 || barrier1.y >= GAME_HEIGHT - BARRIER_HEIGHT)
				barrier1.yVelocity=-barrier1.yVelocity;
			
			// ball bouncing off barrier
			barrierCollision(barrier2,barrier2.x);
			//barrier2 changing direction
			if(barrier2.y <= 0 || barrier2.y > GAME_HEIGHT - BARRIER_HEIGHT)
				barrier2.yVelocity=-barrier2.yVelocity;
		}
		
		//collect coin
		if(effect!=null && paddle1.intersects(effect))
			effectCollision(paddle1, paddle2);
		if(effect!=null && paddle2.intersects(effect))
			effectCollision(paddle2, paddle1);
		
		if (ball.x <= 0 || ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            effect=null;
        }
		
		if(score.getPlayer1()>=3 || score.getPlayer2()>=3) {
			if(barrier!=null)
				switchTerrain();
		}
        
		super.checkCollision();
		
	}
	
	public void barrierCollision(Barrier b, int limit) {

		// ball bouncing of barrier
		if(ball.intersects(b)){

			if(ball.x>=limit) {
				ball.setXVelocity(Math.abs(ball.getXVelocity()));
				ball.setXDirection(ball.getXVelocity());
				ball.setYDirection(ball.getYVelocity());
			}
			else {
				ball.setXVelocity(Math.abs(ball.getXVelocity()));
				ball.setXDirection(-ball.getXVelocity());
				ball.setYDirection(ball.getYVelocity());
			}
		}
		
	}
	
	//Apply effects
	public void effectCollision(Paddle p1, Paddle p2) {
		
//		if(!Intro.sound.isMute()) {
//			ClientPanel.playSound("claim.wav");
//			System.out.println("claim zvuk");
//		} //NE RADI >
	
//		ClientPanel.playSound("claim.wav");
//		System.out.println("claim zvuk ---");
		
		int randomEffect=-1;
		
		while(effect!=null) {
			
			
			if(effect instanceof PaddleResizeCoin || randomEffect==0) {
				//mogu da se dodaju neke animacije za tranzicije
				
				random=new Random();
				int paddleUpOrDown=random.nextInt(2);
				
				if(paddleUpOrDown==0) {
					//onome ko je pokupio coin se prosiri paddle
					p1.height=PADDLE_HEIGHT*2;
					p1.y-=PADDLE_HEIGHT/2;
					
				}
				else {
					//protivniku se smanji paddle
					p2.height=PADDLE_HEIGHT/2;
					p2.y+=PADDLE_HEIGHT/4;
				}
				
				//posle 5 sekundi se vracamo na staro
				ActionListener taskPerformer = new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	p1.height=PADDLE_HEIGHT;
		            	p2.height=PADDLE_HEIGHT;
		            	
		            	if(paddleUpOrDown==0)
		            		p1.y+=PADDLE_HEIGHT/2;
		            	else
		            		p2.y-=PADDLE_HEIGHT/4;
		            }
		        };
		        javax.swing.Timer timer = new javax.swing.Timer(5000, taskPerformer);
		        timer.setRepeats(false);
		        timer.start();
				

				effect=null;
			}
			
			if(effect instanceof BallSpeedCoin || randomEffect==1) {
				
				int xVelocity=ball.getXVelocity();
				int yVelocity=ball.getYVelocity();
				
				random=new Random();
				int speedUpOrDown = random.nextInt(2);
				
				if(speedUpOrDown==0) {
					//loptica se ubrzava
					if(xVelocity>0)
						ball.setXVelocity(xVelocity+1);
					else 
						ball.setXVelocity(xVelocity-1);
					
					if(yVelocity>0)
						ball.setYVelocity(yVelocity+1);
					else 
						ball.setYVelocity(yVelocity-1);

				}
				else {
					//loptica se usporava
					if(xVelocity>0)
						ball.setXVelocity(xVelocity-1);
					else 
						ball.setXVelocity(xVelocity+1);
					
					if(yVelocity>0)
						ball.setYVelocity(yVelocity-1);
					else 
						ball.setYVelocity(yVelocity+1);
				}
				
				
				effect=null;
			}
			
			if(effect instanceof Coin || randomEffect==2) {
				
				barrier.y=0;
				barrier.height=GAME_HEIGHT;
				flag = true;
				
				//posle 5 sekundi se vracamo na staro
				ActionListener taskPerformer = new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	barrier.y=GAME_HEIGHT/2-BARRIER_HEIGHT/2;
						barrier.height=BARRIER_HEIGHT;
						flag = false;
		            }
		        };
		        javax.swing.Timer timer = new javax.swing.Timer(5000, taskPerformer);
		        timer.setRepeats(false);
		        timer.start();
				
				effect=null;
			}
			
			//QuestionMarkCoin can represent any of these coins
			if(effect instanceof QuestionMarkCoin) {
				random=new Random();
				
				if(barrier==null) 
					randomEffect = random.nextInt(2);
				else
					randomEffect = random.nextInt(3);
				
				continue;
			}
			
		}
		
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
				try {
					updateClient();
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
	//	Rectangle barr=null,barr1=null,barr2=null;
	//	if(effect!=null)eff=new Rectangle(effect.x,effect.y,effect.width,effect.height);
	//	if(barrier!=null)barr=new Rectangle(barrier.x,barrier.y,barrier.width,barrier.height);
	//	if(barrier1!=null)barr=new Rectangle(barrier1.x,barrier1.y,barrier1.width,barrier1.height);
	//	if(barrier2!=null)barr=new Rectangle(barrier2.x,barrier2.y,barrier2.width,barrier2.height);
		int []XY= {0,0,0,0,0,0,0,0,0,0,0};
		if(effect!=null) {
			XY[0]=effect.x;
			XY[1]=effect.y;
		}
		if(barrier!=null) {
			XY[2]=barrier.x;
			XY[3]=barrier.y;
			XY[8]=barrier.width;
			XY[9]=barrier.height;
		}else {
			XY[4]=barrier1.x;
			XY[5]=barrier1.y;
			XY[6]=barrier2.x;
			XY[7]=barrier2.y;
		}
		GameAdvancePacket packet= new GameAdvancePacket(
				new Rectangle(paddle1.x,paddle1.y,paddle1.width,paddle1.height),
				new Rectangle(paddle2.x,paddle2.y,paddle2.width,paddle2.height),
				ball.x,ball.y,
				effect,barrier,barrier1,barrier2,XY,score.player1,score.player2);
			player1.updatePlayer(packet);
			player2.updatePlayer(packet);
	}
	
}
