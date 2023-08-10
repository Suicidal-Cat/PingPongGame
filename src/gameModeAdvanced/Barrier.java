package gameModeAdvanced;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Barrier extends Rectangle {

	Random random;
	int yVelocity; // brzina pomeranja pravougaonika
	int speed = 4;
	
	public Barrier(int x, int y, int BARRIER_WIDTH, int BARRIER_HEIGHT, int yVelocity1){
		//yVelocity1 = 0 -> jedna barijera na sredini, biramo random smer
		//yVelocity1 = 1 -> barijera ide na dole
		//yVelocity1 = -1 -> barijera ide na gore
		
		super(x,y,BARRIER_WIDTH,BARRIER_HEIGHT);
		
		if(yVelocity1==0) {
			random = new Random();
		
			int randomYDirection = random.nextInt();
			if(randomYDirection%2==0)
				yVelocity=-speed; //up
			else
				yVelocity=speed; //down
		}
		if(yVelocity1==1) {
			//down
			yVelocity=speed;
		}
		if(yVelocity1==-1) {
			//up
			yVelocity=-speed;
		}
	}
	
	
	public void move() {
		y = y + yVelocity;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x,y,width,height);
	}


	
	
}
