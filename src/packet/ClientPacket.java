package packet;

import java.io.Serializable;

import client.ClientControl;

public class ClientPacket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8952157283339148839L;
	public ClientControl control;
	public int playerNumber;
	public ClientPacket(ClientControl c,int number){
		control=c;
		playerNumber=number;
	}
}
