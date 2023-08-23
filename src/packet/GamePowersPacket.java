package packet;

import java.io.Serializable;

public class GamePowersPacket implements Serializable{

	private static final long serialVersionUID = -8682408153404937287L;
	public int []paddles;
	public int []ballScore;
	public boolean []flagPaddle1;
	public boolean []flagPaddle2;
	public boolean gifFlag;
	
	public GamePowersPacket(int[]pads,int[]ballSc,boolean[]fl,boolean[]f2,boolean gif) {
		paddles=pads;
		ballScore=ballSc;
		flagPaddle1=fl;
		flagPaddle2=f2;
		gifFlag=gif;
	}
}
