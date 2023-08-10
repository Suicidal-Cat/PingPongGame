package gameModeAdvanced;

import java.awt.Graphics;
import java.awt.*;

public class BallSpeedCoin extends BuffEffect {

	public BallSpeedCoin(int x, int y, int EFFECT_WIDTH, int EFFECT_HEIGHT) {
		super(x, y, EFFECT_WIDTH, EFFECT_HEIGHT);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.move();
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(x,y,width,height);
	}

}
