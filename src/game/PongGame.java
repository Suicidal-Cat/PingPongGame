package game;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

import javax.sound.sampled.Clip;
import javax.swing.*;

import client.Client;
import client.GameResultFrame;
import gameInterface.FirstFrame;
import gameInterface.Intro;
import gameInterface.MatchHistory.MatchHistory;
import gameServer.Server;


public class PongGame{
	
	
	public static void main(String[] args) throws InterruptedException {
		
		//first start server
	//	new Thread(new Server()).start();
		
		
		//start players
		//to move paddles use W and S
		//(controls for abilities in PowerMode are Z X C)

		Intro intro=new Intro();
	}
}

