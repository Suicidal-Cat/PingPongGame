package gameModeAdvanced;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import game.GamePanel;
public class GameAdvanced extends GamePanel{
	
	Random random;
	Barrier barrier;
	Coin coin;
	boolean flag= false;
	static final int BARRIER_WIDTH = 30;
	static final int BARRIER_HEIGHT = 160;
	static final int COIN_WIDTH = 15;
	static final int COIN_HEIGHT = 15;
	
	public GameAdvanced(){
		super();
		newBarrier();
		
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
		    @Override
		    public void run() {
		    	newCoin();
		    }
		}, 0, 10000); //na svakih 10 sekundi se pojavljuje coin
		//videti kako da ne ide odmah u startu coin, nego posle nekog vremena, ako dodam ovde umesto 0 neko delay vreme, stopira se cela igrica
		
	}
	
	public void newBarrier() {
		random = new Random();
		barrier = new Barrier(GAME_WIDTH/2 - BARRIER_WIDTH/2, random.nextInt(GAME_HEIGHT - BARRIER_HEIGHT), BARRIER_WIDTH, BARRIER_HEIGHT);
	}
	
	public void newCoin() {
		random = new Random();
		coin = new Coin(GAME_WIDTH/2 - COIN_WIDTH/2, random.nextInt(GAME_HEIGHT-COIN_HEIGHT), COIN_WIDTH, COIN_HEIGHT);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		barrier.draw(g);
		if(coin!=null)
			coin.draw(g);
	}
	@Override
	public void move() {
		super.move();
		if(!flag)
			barrier.move();
		if(coin!=null)
			coin.move();
	}
	@Override
	public void checkCollision() {
		super.checkCollision();
		
		if (ball.intersects(barrier) && ball.x >GAME_WIDTH/2 - BARRIER_WIDTH/2) {
			ball.setXVelocity(Math.abs(ball.getXVelocity()));
			ball.setXDirection(ball.getXVelocity());
			ball.setYDirection(ball.getYVelocity());
		}
		if (ball.intersects(barrier) && ball.x <GAME_WIDTH/2 - BARRIER_WIDTH/2) {
			ball.setXVelocity(Math.abs(ball.getXVelocity()));
			ball.setXDirection(-ball.getXVelocity());
			ball.setYDirection(ball.getYVelocity());
		}
		
		// barrier changing direction
		if (barrier.y <= 0 || barrier.y >= GAME_HEIGHT - BARRIER_HEIGHT) {
			barrier.yVelocity=-barrier.yVelocity;
		}
		
		// collect coin
		if(paddle1.intersects(coin)) {
			//barijera treba da se poveca na celu visinu
			
			barrier = new Barrier(GAME_WIDTH/2 - BARRIER_WIDTH/2, 0, BARRIER_WIDTH, GAME_HEIGHT);
			flag = true;
			//sa ovim flagom radi, ali tranzicija kad se vraca na staro nije bas najsrecnija
			//seljacki al ok
			
//			long t= System.currentTimeMillis();
//			long end = t+5000;
//			while(System.currentTimeMillis() < end) {
//				//nista,cekamo da prodje vreme
//			}
			//ne moze ovako, blokira celu igricu isto kao i Thread.sleep()
			
			new Timer().schedule( 
			        new TimerTask() {
			            @Override
			            public void run() {
			            	barrier = new Barrier(GAME_WIDTH/2 - BARRIER_WIDTH/2, random.nextInt(GAME_HEIGHT - BARRIER_HEIGHT), BARRIER_WIDTH, BARRIER_HEIGHT);
			            	flag = false;
			            }
			        }, 
			        5000 
			);
			
			
//			barrier = new Barrier(GAME_WIDTH/2 - BARRIER_WIDTH/2, random.nextInt(GAME_HEIGHT - BARRIER_HEIGHT), BARRIER_WIDTH, BARRIER_HEIGHT);
			//vracamo na staro
			
		}
		if(paddle2.intersects(coin)) {
			//barijera treba da se poveca na celu visinu
			barrier = new Barrier(GAME_WIDTH/2 - BARRIER_WIDTH/2, 0, BARRIER_WIDTH, GAME_HEIGHT);
			flag = true;

			new Timer().schedule( 
			        new TimerTask() {
			            @Override
			            public void run() {
			            	barrier = new Barrier(GAME_WIDTH/2 - BARRIER_WIDTH/2, random.nextInt(GAME_HEIGHT - BARRIER_HEIGHT), BARRIER_WIDTH, BARRIER_HEIGHT);
			            	flag=false;
			            }
			        }, 
			        5000 
			);
			
//			barrier = new Barrier(GAME_WIDTH/2 - BARRIER_WIDTH/2, random.nextInt(GAME_HEIGHT - BARRIER_HEIGHT), BARRIER_WIDTH, BARRIER_HEIGHT);
			//vracamo na staro
		}
		
	}
	
}
