package gameModeAdvanced;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Barrier extends Rectangle {

	Random random;
	int yVelocity; // brzina pomeranja pravougaonika
	int speed = 4;
	
	public Barrier(int x, int y, int BARRIER_WIDTH, int BARRIER_HEIGHT){
		super(x,y,BARRIER_WIDTH,BARRIER_HEIGHT);
		random = new Random();
		
		int randomYDirection = random.nextInt();
		if(randomYDirection%2==0)
			yVelocity=-speed;
		else
			yVelocity=speed;
	}
	
	
	
	public void move() {
		y = y + yVelocity;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x,y,width,height);
	}


	
	
}
