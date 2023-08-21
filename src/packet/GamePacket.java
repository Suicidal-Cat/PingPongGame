package packet;

import java.io.Serializable;

import game.Ball;
import game.Paddle;
import game.Score;

import java.awt.Image;
import java.awt.Rectangle;

public class GamePacket implements Serializable{

	private static final long serialVersionUID = 1912877229737730879L;
	public int xp1,yp1,xp2,yp2,xBall,yBall,p1Score,p2Score;
	public boolean gifFlag1, gifFlag2;
	
	public GamePacket(int x1,int y1,int x2,int y2,int xb,int yb,int p1s,int p2s, boolean gif1, boolean gif2) {
		xp1=x1;
		yp1=y1;
		xp2=x2;
		yp2=y2;
		xBall=xb;
		yBall=yb;
		p1Score=p1s;
		p2Score=p2s;
		gifFlag1=gif1;
		gifFlag2=gif2;
	}
/*	public byte [] data;
	public GamePacket(byte[]img) {
		data=img;
	}*/
}
