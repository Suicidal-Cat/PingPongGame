package gameModeAdvanced;

import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;

public class PaddleResizeCoin extends BuffEffect {
	Image prcoinImage;
	
	public PaddleResizeCoin(int x, int y, int COIN_WIDTH, int COIN_HEIGHT) {
		super(x, y, COIN_WIDTH, COIN_HEIGHT);
	}
	
	@Override
	public void move() {
		super.move();
	}
	
	@Override
	public void draw(Graphics g) {
		try {
			prcoinImage = ImageIO.read(new File("src/resources/images/double-arrow.png"));
			g.drawImage(prcoinImage, x, y, width, height, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
