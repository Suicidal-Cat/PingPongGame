package gameModePowers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Console;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import client.ClientControl;
import game.Paddle;
import gameInterface.Intro;
import gameSound.Sound;

public class PaddlePower extends Paddle{
	
	private static final long serialVersionUID = 1604143271542518196L;
	boolean cdSpeed;//ability is ready to use (cooldown)
	boolean cdPush;
	boolean cdBlock;
	Image pushShow;
	Image speedShow;
	Image blockShow;
	Image pushC;
	Image speedC;
	Image blockC;
	static final int POWER_SIZE=45;
	static final int POWER_POSITIONX=5;
	static final int POWER_POSITIONY=40;
	private static final int GAME_WIDTH=1100;
	static Sound powerSound=new Sound("powerOn.wav");
	
	public PaddlePower(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT, id);
		initImages();
		speed=10;
	}
	public void updatePaddle() {
		super.updatePaddle(null);
	}
	
/*	public void keyPressed(KeyEvent e) {
		
		switch (id) {
		case 1:
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-speed);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(speed);
			}
			if(!cdPush && e.getKeyCode() == KeyEvent.VK_J) {
				powerPush();
			}
			if(!cdSpeed && !powerBlock && e.getKeyCode() == KeyEvent.VK_K) {
				powerSpeed(speed);
			}
			if(!cdBlock && e.getKeyCode() == KeyEvent.VK_L) {
				powerBlock();
			}
			break;
		case 2:
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed);
			}
			if(!cdPush && e.getKeyCode() == KeyEvent.VK_Z) {
				powerPush();
			}
			if(!cdSpeed && !powerBlock && e.getKeyCode() == KeyEvent.VK_X) {
				powerSpeed(speed);
			}
			if(!cdBlock && e.getKeyCode() == KeyEvent.VK_C) {
				powerBlock();
			}
			break;
		}

	}*/
	
	public void updatePaddle(ClientControl control) {
		if(control==ClientControl.UP_PRESSED)setYDirection(-speed);
		else if(control==ClientControl.DOWN_PRESSED)setYDirection(speed);
		else if(control==ClientControl.UP_REALISED)setYDirection(0);
		else if(control==ClientControl.DOWN_REALISED)setYDirection(0);
		else if(!cdPush && control==ClientControl.Z_PRESSED)powerPush();
		else if(!cdSpeed && !powerBlock && control==ClientControl.X_PRESSED)powerSpeed(speed);
		else if(!cdBlock && control==ClientControl.C_PRESSED)powerBlock();
	}
	
	public void move() {
		y = y + yVelocity;
	}
	public void draw(Graphics g) {
		if (id == 1) {
			g.setColor(new Color(122, 183, 255));
		} else
			g.setColor(new Color(148, 87, 235));
		g.fillRect(x, y, width, height);
		if(powerPush) {
			g.setColor(Color.RED);
			if(id==1)g.drawLine(x+width,y,x+width,y+height);
			else g.drawLine(x,y,x,y+height);
		}
		drawImages(g);

	}
	private void powerPush() {
	//	if(!Intro.sound.isMute()) powerSound.audioStart();
		powerPush=true;
		cdPush=true;
		Timer timer = new Timer(1000, null);
		ActionListener taskPerformer = new ActionListener() {
			int counter=0;
            public void actionPerformed(ActionEvent evt) {
            	counter++;
            	if(counter==10) {
                	cdPush=false;
            		timer.stop();
            	}
            }
        };
        timer.addActionListener(taskPerformer);
        timer.start();
	}
	private void powerSpeed(int ogSpeed) {
	//	if(!Intro.sound.isMute()) powerSound.audioStart();
		speed=15;
		height=150;
		cdSpeed=true;
        Timer timer = new Timer(1000, null);
		ActionListener taskPerformer = new ActionListener() {
			int counter=0;
            public void actionPerformed(ActionEvent evt) {
            	counter++;
            	if(counter==4) {
                	speed=ogSpeed;
                	if(powerBlock==false)height=100;
            	}
            	else if(counter==10) {
                	cdSpeed=false;
            		timer.stop();
            	}
            }
        };
        timer.addActionListener(taskPerformer);
        timer.start();
	}
	private void powerBlock() {
	//	if(!Intro.sound.isMute()) powerSound.audioStart();
		powerBlock=true;
		y=0;
		height=667;
		cdBlock=true;
        Timer timer = new Timer(1000, null);
		ActionListener taskPerformer = new ActionListener() {
			int counter=0;
            public void actionPerformed(ActionEvent evt) {
            	counter++;
            	if(counter==10) {
                	cdBlock=false;
            		timer.stop();
            	}
            }
        };
        timer.addActionListener(taskPerformer);
        timer.start();
	}
	
	public void resetPowers() {
		powerPush=false;
		powerBlock=false;
		cdPush=false;
		cdBlock=false;
		cdSpeed=false;
	}	
	
	private void initImages() {
		 try {
		//	 if(id==1) {
				 pushShow=ImageIO.read(new File("src/resources/images/90.png"));
				 speedShow=ImageIO.read(new File("src/resources/images/shield.png"));
				 blockShow=ImageIO.read(new File("src/resources/images/wall.png"));
				 pushC=ImageIO.read(new File("src/resources/images/90On.png"));
				 speedC=ImageIO.read(new File("src/resources/images/shieldOn.png"));
				 blockC=ImageIO.read(new File("src/resources/images/wallOn.png"));
			/* }else {
				 pushShow=ImageIO.read(new File("src/resources/images/90.png"));
				 speedShow=ImageIO.read(new File("src/resources/images/shield.png"));
				 blockShow=ImageIO.read(new File("src/resources/images/wall.png"));
				 pushC=ImageIO.read(new File("src/resources/images/90On.png"));
				 speedC=ImageIO.read(new File("src/resources/images/shieldOn.png"));
				 blockC=ImageIO.read(new File("src/resources/images/wallOn.png"));
			 }*/

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void drawImages(Graphics g) {
		if(id==1) {
			if(!cdPush)g.drawImage(pushShow,POWER_POSITIONX,POWER_POSITIONY,POWER_SIZE,POWER_SIZE,null);
			else g.drawImage(pushC,POWER_POSITIONX,POWER_POSITIONY,POWER_SIZE,POWER_SIZE,null);
			if(!cdSpeed)g.drawImage(speedShow,POWER_POSITIONX,POWER_POSITIONY+80,POWER_SIZE,POWER_SIZE,null);
			else g.drawImage(speedC,POWER_POSITIONX,POWER_POSITIONY+80,POWER_SIZE,POWER_SIZE,null);	
			if(!cdBlock)g.drawImage(blockShow,POWER_POSITIONX,POWER_POSITIONY+160,POWER_SIZE,POWER_SIZE,null);
			else g.drawImage(blockC,POWER_POSITIONX,POWER_POSITIONY+160,POWER_SIZE,POWER_SIZE,null);
		}
		else {
			if(!cdPush)g.drawImage(pushShow,GAME_WIDTH-POWER_POSITIONX-POWER_SIZE,POWER_POSITIONY,POWER_SIZE,POWER_SIZE,null);
			else g.drawImage(pushC,GAME_WIDTH-POWER_POSITIONX-POWER_SIZE,POWER_POSITIONY,POWER_SIZE,POWER_SIZE,null);
			if(!cdSpeed)g.drawImage(speedShow,GAME_WIDTH-POWER_POSITIONX-POWER_SIZE,POWER_POSITIONY+80,POWER_SIZE,POWER_SIZE,null);
			else g.drawImage(speedC,GAME_WIDTH-POWER_POSITIONX-POWER_SIZE,POWER_POSITIONY+80,POWER_SIZE,POWER_SIZE,null);	
			if(!cdBlock)g.drawImage(blockShow,GAME_WIDTH-POWER_POSITIONX-POWER_SIZE,POWER_POSITIONY+160,POWER_SIZE,POWER_SIZE,null);
			else g.drawImage(blockC,GAME_WIDTH-POWER_POSITIONX-POWER_SIZE,POWER_POSITIONY+160,POWER_SIZE,POWER_SIZE,null);
		}
		
	}
	public boolean[] getFlags(){
		return new boolean[] {powerPush,powerBlock,cdPush,cdSpeed,cdBlock};
	}
	public void setFlags(boolean[]flags){
		powerPush=flags[0];
		powerBlock=flags[1];
		cdPush=flags[2];
		cdSpeed=flags[3];
		cdBlock=flags[4];
		};
}
