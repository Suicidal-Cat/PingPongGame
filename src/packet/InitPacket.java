package packet;

import java.io.Serializable;

import game.GameMode;

public class InitPacket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8779667799403699093L;
	public int playerId;
	public GameMode mode;
	public InitPacket(int id){
		playerId=id;
	}
	public InitPacket(GameMode m){
		mode=m;
	}

}
