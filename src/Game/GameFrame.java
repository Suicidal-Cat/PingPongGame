package Game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import GameModeAdvance.GameAdvance;
import GameModeClassic.GameClassic;
import GameModePowers.GamePowers;

public class GameFrame extends JFrame {

	public GamePanel panel;

	GameFrame(GameMode mode) {
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
		else return new GameAdvance();
	}
}
