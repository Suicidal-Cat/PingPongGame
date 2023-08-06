package Game;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import javax.swing.*;


public class PongGame{
	

	public static void main(String[] args) {
		GameMode mode=GameMode.Classic;
		GameFrame frame=new GameFrame(mode);
		//new Thread(new Client()).start();		
		
	}
}

