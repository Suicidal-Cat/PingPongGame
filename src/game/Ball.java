package game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4764021735703576751L;
	Random random;
	private int xVelocity;
	private int yVelocity;
	int initialSpeed = 2;

	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if (randomXDirection == 0)// left
			randomXDirection--;
		setXDirection(randomXDirection * initialSpeed);
		int randomYDirection = random.nextInt(2);
		if (randomYDirection == 0)// up
			randomYDirection--;
		setYDirection(randomYDirection * initialSpeed);
	}

	public void setXDirection(int randomXDirection) {
		setXVelocity(randomXDirection);
	}

	public void setYDirection(int randomYDirection) {
		setYVelocity(randomYDirection);
	}

	public void move() {
		x += getXVelocity();
		y += getYVelocity();
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}

	public int getXVelocity() {
		return xVelocity;
	}

	public void setXVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}

	public int getYVelocity() {
		return yVelocity;
	}

	public void setYVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}
}
