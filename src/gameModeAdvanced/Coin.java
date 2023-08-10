package gameModeAdvanced;
import java.awt.*;
import java.util.Random;

public class Coin extends BuffEffect {
	
	public Coin (int x, int y, int COIN_WIDTH, int COIN_HEIGHT) {
		super(x, y, COIN_WIDTH, COIN_HEIGHT);
		
	}
	
	@Override
	public void move() {
		super.move();
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x,y,width,height);
	}
	
}
