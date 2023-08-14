package gameModeAdvanced;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import game.GamePanel;
import game.Paddle;
import gameSound.Sound;

public class GameAdvanced extends GamePanel{
	
	Random random;
	Barrier barrier;
	Barrier barrier1;
	Barrier barrier2;
	BuffEffect effect;
	boolean flag = false;
	static final int BARRIER_WIDTH = 30;
	static final int BARRIER_HEIGHT = 160;
	static final int EFFECT_SIZE=15;
	Sound claimSound = new Sound("claim.wav");
	
	public GameAdvanced(){
		super();
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
	
	@Override
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
	}
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
			hitSound.audioStart();
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
		
		claimSound.audioStart();
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
	
}
