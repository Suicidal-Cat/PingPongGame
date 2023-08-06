package gameModeAdvanced;
import java.awt.*;
import java.util.Random;

public class Coin extends Rectangle{
	
	Random random;
	int xVelocity;
	int speed = 2;
	
	public Coin(int x, int y, int COIN_WIDTH, int COIN_HEIGHT) {
		super(x, y, COIN_WIDTH, COIN_HEIGHT);
		random = new Random();
		
		int randomXDirection = random.nextInt();
		if(randomXDirection%2==0)
			xVelocity=-speed;
		else
			xVelocity=speed;
	}
	
	public void move() {
		x = x + xVelocity;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x,y,width,height);
	}

}
