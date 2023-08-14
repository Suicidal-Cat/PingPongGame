package gameModeAdvanced;
import java.awt.*;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Coin extends BuffEffect {
	
	Image coinIcon;
	
	public Coin (int x, int y, int COIN_WIDTH, int COIN_HEIGHT) {
		super(x, y, COIN_WIDTH, COIN_HEIGHT);
		
	}
	
	@Override
	public void move() {
		super.move();
	}
	
	@Override
	public void draw(Graphics g) {
		try {
			coinIcon = ImageIO.read(new File("src/resources/images/wall.png"));
			g.drawImage(coinIcon, x, y, width, height, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
