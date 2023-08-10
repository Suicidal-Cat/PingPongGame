package gameModeAdvanced;

import java.awt.Graphics;
import java.util.Random;
import java.awt.*;

public class BuffEffect extends Rectangle {

	Random random;
	int xVelocity;
	int speed = 2;
	
	public BuffEffect (int x,int y,int EFFECT_WIDTH,int EFFECT_HEIGHT){
		
		super(x,y,EFFECT_WIDTH,EFFECT_HEIGHT);
		
		random=new Random();
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
		
	}
}
