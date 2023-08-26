package client;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameInterface.Intro;
import gameModePowers.PaddlePower;
import gameSound.Sound;
import packet.GamePowersPacket;

public class ClientPowerPanel extends ClientPanel{


	boolean sound1;
	boolean sound2;
	Sound soundP=new Sound("powerOn.wav");
	
	private static final long serialVersionUID = 2246181633594674603L;
	public ClientPowerPanel() {
		super();
		paddle1 = new PaddlePower(65, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new PaddlePower(GAME_WIDTH - PADDLE_WIDTH-65, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
	}
	
	public void paint(Graphics g) {
		try {
		    image = ImageIO.read(new File("src/resources/images/pozadinaPowers5.jpg"));
			graphics = image.getGraphics();
			draw(graphics);
			g.drawImage(image, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateComponents(Object packet1) throws IOException {
		GamePowersPacket packet=(GamePowersPacket)packet1;
		updatePaddles(packet.paddles);
		paddle1.setFlags(packet.flagPaddle1);
		paddle2.setFlags(packet.flagPaddle2);
		ball.x=packet.ballScore[0];
		ball.y=packet.ballScore[1];
		score.player1=packet.ballScore[2];
		score.player2=packet.ballScore[3];
		gif=packet.gifFlag;
		
		sound1=packet.soundPower1;
		sound2=packet.soundPower2;
		
		checkCollision();
		repaint();
		if(score.player1>=6 || score.player2>=6)throw new IOException("Kraj igre");
	}
	
	public void updatePaddles(int[]paddles) {
		paddle1.x=paddles[0];
		paddle1.y=paddles[1];
		paddle1.width=paddles[2];
		paddle1.height=paddles[3];
		paddle2.x=paddles[4];
		paddle2.y=paddles[5];
		paddle2.width=paddles[6];
		paddle2.height=paddles[7];
	}
	
	public void checkCollision() {
		if(sound1 || sound2)
			if(!Intro.sound.isMute()) soundP.audioRestart();
		
		if(ball.x <= 65) {
//			System.out.println("radi error1");
			if(!Intro.sound.isMute()) errorSound.audioRestart();			
		}
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER - 65) {
//			System.out.println("radi error2");
			if(!Intro.sound.isMute()) errorSound.audioRestart();
		}
		
		super.checkCollision();
	}
	
}
