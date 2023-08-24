package gameData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import game.GameMode;


public class Database {
	
	public LinkedList<Match> getAllMatches(String username) {
		
		LinkedList<Match> matches=new LinkedList<Match>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pingponggame","root","");
			Statement stmt=con.createStatement();
			String sql="Select * from matchhistory where UserName1='"+username+"' OR UserName2='"+username+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				GameMode mode;
				String m=rs.getString(6);
				if(m.equals("Classic"))mode=GameMode.Classic;
				else if(m.equals("Advanced"))mode=GameMode.Advanced;
				else mode=GameMode.Powers;
				Match match=new Match(rs.getInt(1),rs.getString(2),rs.getString(3),
						rs.getInt(4),rs.getInt(5),mode);
				matches.add(match);
			}	
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(matches.isEmpty())return null;
		else return matches;
	}
	
}
