package gameModeAdvanced;

import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

import java.awt.*;

public class BallSpeedCoin extends BuffEffect {

	Image bscoinImage;
	
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
		try {
			bscoinImage = ImageIO.read(new File("src/resources/images/speed.png"));
			g.drawImage(bscoinImage, x, y, width, height, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
