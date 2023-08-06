package game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import gameModeAdvanced.GameAdvanced;
import gameModeClassic.GameClassic;
import gameModePowers.GamePowers;

public class GameFrame extends JFrame {

	public GamePanel panel;

	public GameFrame(GameMode mode) {
		//u zavisnosti od izabrane opcije menjacemo instancu panela
		panel = getMode(mode);
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}
	private GamePanel getMode(GameMode mode) {
		if(mode==GameMode.Classic)return new GameClassic();
		else if(mode==GameMode.Powers)return new GamePowers();
		else return new GameAdvanced();
	}
}
