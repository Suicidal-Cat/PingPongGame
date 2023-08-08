package gameModeAdvanced;

import java.awt.Color;
import java.awt.Graphics;

public class QuestionMarkCoin extends Coin{

	public QuestionMarkCoin(int x, int y, int COIN_WIDTH, int COIN_HEIGHT) {
		super(x, y, COIN_WIDTH, COIN_HEIGHT);
	}
	
	@Override
	public void move() {
		super.move();
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval(x,y,width,height);
	}

}
