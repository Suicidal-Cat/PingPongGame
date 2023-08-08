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
	PaddleResizeCoin prcoin;
	QuestionMarkCoin qmcoin;
	boolean flag = false;
	boolean prflag = false;
	boolean qmflag = false;
	static final int BARRIER_WIDTH = 30;
	static final int BARRIER_HEIGHT = 160;
	static final int COIN_WIDTH = 15;
	static final int COIN_HEIGHT = 15;
	
	public GameAdvanced(){
		super();
		newBarrier();
		releaseCoin();
		releasePaddleResizeCoin();
		releaseQuestionMarkCoin();
		
		//treba nekako uskladiti pojavljivanje coina, ili prorediti, jer ovako nailaze maltene stalno
		
	}
	
	public void newBarrier() {
		random = new Random();
		barrier = new Barrier(GAME_WIDTH/2 - BARRIER_WIDTH/2, random.nextInt(GAME_HEIGHT - BARRIER_HEIGHT), BARRIER_WIDTH, BARRIER_HEIGHT);
	}
	
	public void newCoin() {
		random = new Random();
		coin = new Coin(GAME_WIDTH/2 - COIN_WIDTH/2, random.nextInt(GAME_HEIGHT-COIN_HEIGHT), COIN_WIDTH, COIN_HEIGHT);
	}
	
	public void releaseCoin() {
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
            	newCoin();
            }
        };
        javax.swing.Timer timer = new javax.swing.Timer(7000, taskPerformer);
        timer.setRepeats(true);
        timer.start();
        timer.setDelay(15000);
        
	}
	
	public void newPaddleResizeCoin() {
		random = new Random();
		prcoin = new PaddleResizeCoin(GAME_WIDTH/2 - COIN_WIDTH/2, random.nextInt(GAME_HEIGHT-COIN_HEIGHT), COIN_WIDTH, COIN_HEIGHT);
	}
	
	public void releasePaddleResizeCoin() {
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
            	newPaddleResizeCoin();
                prflag=false;
            }
        };
        javax.swing.Timer timer = new javax.swing.Timer(8000, taskPerformer);
        timer.setRepeats(true);
        timer.start();
        timer.setDelay(17000);
        
	}
	
	public void newQuestionMarkCoin() {
		random = new Random();
		qmcoin = new QuestionMarkCoin(GAME_WIDTH/2 - COIN_WIDTH/2, random.nextInt(GAME_HEIGHT-COIN_HEIGHT), COIN_WIDTH, COIN_HEIGHT);
	}
	
	public void releaseQuestionMarkCoin() {
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
            	newQuestionMarkCoin();
            	qmflag = false;
            }
        };
        javax.swing.Timer timer = new javax.swing.Timer(9000, taskPerformer);
        timer.setRepeats(true);
        timer.start();
        timer.setDelay(13000);
        
	}
	
	public void resetPaddles() {
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
            	paddle1.setBounds(paddle1.x, paddle1.y, PADDLE_WIDTH, PADDLE_HEIGHT);
            	paddle2.setBounds(paddle2.x, paddle2.y, PADDLE_WIDTH, PADDLE_HEIGHT);
            }
        };
        javax.swing.Timer timer = new javax.swing.Timer(8000, taskPerformer);
        timer.setRepeats(false);
        timer.start();
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		barrier.draw(g);
		if(coin!=null)
			coin.draw(g);
		if(prcoin!=null)
			prcoin.draw(g);
		if(qmcoin!=null)
			qmcoin.draw(g);
	}
	@Override
	public void move() {
		super.move();
		if(!flag)
			barrier.move();
		if(coin!=null)
			coin.move();
		if(prcoin!=null)
			prcoin.move();
		if(qmcoin!=null)
			qmcoin.move();
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
		if(coin!=null && paddle1.intersects(coin)) {
			//barijera treba da se poveca na celu visinu
			
			barrier = new Barrier(GAME_WIDTH/2 - BARRIER_WIDTH/2, 0, BARRIER_WIDTH, GAME_HEIGHT);
			flag = true;
			//sa ovim flagom radi, ali tranzicija kad se vraca na staro nije bas najsrecnija
			//seljacki al ok
			
			new Timer().schedule( 
			        new TimerTask() {
			            @Override
			            public void run() {
			            	barrier = new Barrier(GAME_WIDTH/2 - BARRIER_WIDTH/2, random.nextInt(GAME_HEIGHT - BARRIER_HEIGHT), BARRIER_WIDTH, BARRIER_HEIGHT);
			            	//vracamo na staro
			            	flag = false;
			            }
			        }, 
			        5000 
			);
			
			
		}
		if(coin!=null && paddle2.intersects(coin)) {
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
			
		}
		
		//collect PaddleResizeCoin
		
		if(prcoin!=null && paddle1.intersects(prcoin)) {
			
			Random r = new Random();
			int pom = r.nextInt(32);
			
			if(!prflag) {
				if(pom%2==0) {
					//slucaj 1 - njemu se prosiri paddle - POBOLJSAJ TRANZICIJU
					paddle1.setBounds(paddle1.x, paddle1.y-PADDLE_HEIGHT/2, PADDLE_WIDTH, PADDLE_HEIGHT*2);
					prflag=true;
					resetPaddles();
				}
				else {
					//slucaj 2 - protivniku se suzi paddle
					paddle2.setBounds(paddle2.x, paddle2.y+PADDLE_HEIGHT/4, PADDLE_WIDTH, PADDLE_HEIGHT/2);
					prflag=true;
					resetPaddles();
				}
			}
			
		}
		
		if(prcoin!=null && paddle2.intersects(prcoin)) {
			
			Random r = new Random();
			int pom = r.nextInt(32);
			
			if(!prflag) {
				if(pom%2==0) {
					//slucaj 1 - njemu se prosiri paddle - POBOLJSAJ TRANZICIJU
					paddle2.setBounds(paddle2.x, paddle2.y-PADDLE_HEIGHT/2, PADDLE_WIDTH, PADDLE_HEIGHT*2);
					prflag=true;
					resetPaddles();
				}
				else {
					//slucaj 2 - protivniku se suzi paddle
					paddle1.setBounds(paddle1.x, paddle1.y+PADDLE_HEIGHT/4, PADDLE_WIDTH, PADDLE_HEIGHT/2);
					prflag=true;
					resetPaddles();
				}
			}
			
		}
		
		// collect QuestionMarkCoin
		
		if(qmcoin!=null && (paddle1.intersects(qmcoin) || paddle2.intersects(qmcoin))) {
			//loptica se ubrzava ili usporava
			
			Random r = new Random();
			int pom = r.nextInt(32);
			
			int ballXVelocity = ball.getXVelocity();
			int ballYVelocity = ball.getYVelocity();
			
			//treba ovo malo popraviti da bude vise osetno i efikasnije
			if(!qmflag) {
				if(pom%2==0) {
					//slucaj 1 - loptica se uspori
					if(ballXVelocity>1) {
						ball.setXVelocity(ballXVelocity--);
					}
					if(ballXVelocity<1) {
						ball.setXVelocity(ballXVelocity++);
					}
					if(ballYVelocity>1) {
						ball.setYVelocity(ballYVelocity--);
					}
					if(ballYVelocity<1) {
						ball.setYVelocity(ballYVelocity++);
					}
					
					ball.setXDirection(ball.getXVelocity());
					ball.setYDirection(ball.getYVelocity());
					
					qmflag=true;
				}
				else {
					//slucaj 2 - loptica se ubrza
					ball.setXVelocity(Math.abs(ball.getXVelocity()));
					ball.setXVelocity(ball.getXVelocity() + 1);
					if (ball.getYVelocity() > 0) {
						ball.setYVelocity(ball.getYVelocity() + 1);
					} 
					else ball.setYVelocity(ball.getYVelocity() - 1);

					ball.setXDirection(ball.getXVelocity());
					ball.setYDirection(ball.getYVelocity());
					
					qmflag=true;
				}
			}
			
			
		}
		
		//ball is out of bounds, reset coins
		if (ball.x <= 0 || ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			
			releaseCoin();
			releasePaddleResizeCoin();
			releaseQuestionMarkCoin();
			
			//poenta je da ako je coin krenuo dok lopta jos nije pala, kad se igrica resetuje na pocetak i score se promeni, onaj coin koji je krenuo i dalje ide,
			//treba nekako da se obrise ali ovo ne radi, ono samo resetuje ponovo onaj tajmer i to, treba taj pocetni coin nekako da se obrise (ne radi sa repaint();)
		}
		
		
	}
	
}
