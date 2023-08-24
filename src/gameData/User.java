package gameData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import game.Score;

public class User {
	
	public String userName;
	public String password;
	
	public User() {
		
	}
	public User(String userName, String password) {
		
		this.userName = userName;
		this.password = password;
		//System.out.println(userName);
		
	}
	
	public void saveScore() {
	try {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pingponggame","root","");
		PreparedStatement ps=conn.prepareStatement("insert into score(UserName,Score) values (?,?)");
		ps.setString(1, userName);
		//ps.setString(2, getPlayer1()));
		
		int x=ps.executeUpdate();

	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	
	
	
	

}
