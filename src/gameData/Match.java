package gameData;

import game.GameMode;
import gameInterface.FirstFrame;

public class Match {
	
	public int matchId;
	public String username1;
	public String username2;
	public int score1,score2;
	public GameMode gameMode;
	
	public Match() {
		
	}
	
	public Match(int matchId, String username1, String username2, int score1, int score2, GameMode gameMode) {
		super();
		this.matchId = matchId;
		this.username1 = username1;
		this.username2 = username2;
		this.score1 = score1;
		this.score2 = score2;
		this.gameMode = gameMode;
	}
	
	public Object[] getDataMatch() {
		int win=0;
		if(FirstFrame.u.userName.equals(username1) && score1>score2)win=1;
		else if(FirstFrame.u.userName.equals(username2) && score1<score2)win=1;
		return new Object[] { gameMode,username1, score1+" : "+score2, username2,win};
	}
	
}
