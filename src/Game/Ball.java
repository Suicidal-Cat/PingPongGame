package Game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle {

	Random random;
	int xVelocity;
	int yVelocity;
	int initialSpeed = 2;

	Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		random = new Random();
		int randomXDiretion = random.nextInt(2);
		if (randomXDiretion == 0)// left
			randomXDiretion--;
		setXDirection(randomXDiretion * initialSpeed);
		int randomYDiretion = random.nextInt(2);
		if (randomYDiretion == 0)// left
			randomYDiretion--;
		setYDirection(randomYDiretion * initialSpeed);
	}

	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
	}

	public void setYDirection(int randomYDirection) {
		yVelocity =randomYDirection;
	}

	public void move() {
		x += xVelocity;
		y += yVelocity;
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
}
