package gameModeAdvanced;

import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;

public class QuestionMarkCoin extends BuffEffect {

	Image qmcoinImage;
	
	public QuestionMarkCoin(int x, int y, int COIN_WIDTH, int COIN_HEIGHT) {
		super(x, y, COIN_WIDTH, COIN_HEIGHT);
	}
	
	@Override
	public void move() {
		super.move();
	}
	
	@Override
	public void draw(Graphics g) {
		try {
			qmcoinImage = ImageIO.read(new File("src/resources/images/question-mark.png"));
			g.drawImage(qmcoinImage, x, y, width, height, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
