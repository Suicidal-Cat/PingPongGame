package packet;

import java.io.Serializable;

import gameModeAdvanced.Barrier;
import gameModeAdvanced.BuffEffect;

import java.awt.Rectangle;

public class GameAdvancePacket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3754524121131453722L;
	public Rectangle paddle1,paddle2;
	public Barrier barrier,barrier1,barrier2;
	public BuffEffect effect;
	public int ballx,bally,p1Score,p2Score;
	public int []XYPostions;
	public boolean gifFlag1, gifFlag2;
	
	public GameAdvancePacket(Rectangle p1,Rectangle p2,int bx,int by,
			BuffEffect eff,Barrier barr,Barrier barr1,Barrier barr2,int[]xy,int s1,int s2,boolean gif1,boolean gif2) {
		paddle1=p1;
		paddle2=p2;
		ballx=bx;
		bally=by;
		effect=eff;
		barrier=barr;
		barrier1=barr1;
		barrier2=barr2;
		XYPostions=xy;
		p1Score=s1;
		p2Score=s2;
		gifFlag1=gif1;
		gifFlag2=gif2;
	}
}
