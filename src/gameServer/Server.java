package gameServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import game.GameFrame;
import game.GameMode;
import game.GamePanel;
import gameInterface.Intro;
import gameSound.Sound;
import packet.GameAdvancePacket;
import packet.GamePacket;


public class Server implements Runnable{
	
	int port = 9521;
	ServerSocket serverSoket;
	Socket player;
	
	public static LinkedList<Client_handler>players=new LinkedList<>();
		
		
	@Override
	public void run() {
		try {
			serverSoket = new ServerSocket(port);
			while (true) {
				System.out.println("Cekam na igrace...");
				player=serverSoket.accept();
				System.out.println("Povezan igrac!");
				new Client_handler(player);
			}
		} catch (IOException e) {
			System.out.println("Greska pri pokretanju servera!");
		}
		
	}
}
