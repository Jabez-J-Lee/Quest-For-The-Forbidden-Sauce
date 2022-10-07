package edu.pacific.comp55.starter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GLine;

public class sauceRoom extends GraphicsPane{
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GImage bg;
	
	public Player player;
	public Sauce sauce;
	
	private GParagraph interactMessage;
	
	private ArrayList<GLine> gridLine;
	
	int numRows = 10; //Set board temporarily
	int numCols = 10; //Set it to retrieve size from text file

	public sauceRoom(MainApplication app) {
		this.program = app;
		
		// Set Background
		bg = new GImage("media/noDoorRoom.jpg");
		bg.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT - 37);
		
		sauce = new Sauce();
		sauce.sauceSprite.setSize(50, 75);
		sauce.sauceSprite.setLocation(375, 50);
		
		player = Player.INSTANCE;
		player.setLocation(185, 490);
		
		interactMessage = new GParagraph("           Press 'e' to\n"
				+ "obtain what you so desire", 280, 25);
		interactMessage.setFont("Arial-20");
		
		// Draw Grid
		gridLine = new ArrayList<GLine>();
		drawGrid();
	}
	
	public void drawGrid() {
		for(int i = 1; i < numRows; i++) {
			GLine vertLine = new GLine(i * cellWidth(), 0, i * cellWidth(), program.getHeight());
			GLine horizLine = new GLine(0, i * cellHeight(), program.getWidth(), i * cellHeight());
			gridLine.add(vertLine);
			gridLine.add(horizLine);
		}
	}
	

	@Override
	public void showContents() {
		for(GLine line:gridLine) {
			program.add(line);
		} 
		program.add(bg);
		program.add(player);
		program.add(sauce.sauceSprite);
	}

	@Override
	public void hideContents() {
		for(GLine line:gridLine) {
			program.remove(line);
		}
		program.remove(bg);
		program.remove(player);
		program.remove(sauce.sauceSprite);
		program.remove(interactMessage);
	}
	
	public boolean canInteract() {
		double playerX = player.getX();
		double playerY = player.getY();
		
		return (playerX >= 325 && playerX <= 425) && playerY <= 130;
	}
	
	public boolean canMove(KeyEvent e) {
		int code = e.getKeyCode();
		
		double playerX = player.getX();
		double playerY = player.getY();

		if (code == KeyEvent.VK_W) {
			return playerY > 130;
		}
		else if (code == KeyEvent.VK_S) {
			return playerY < 510;
		}
		else if (code == KeyEvent.VK_A) {
			return playerX > 60;
		}
		else if (code == KeyEvent.VK_D) {
			return playerX < 705;
		}
		return false;
	}
	
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(canInteract()) {
			program.add(interactMessage);
		}
		else {
			program.remove(interactMessage);
		}
		
		if (code == KeyEvent.VK_W && canMove(e)) {
			player.move(0, player.getMoveSpeed() * -1);
		}
		else if (code == KeyEvent.VK_S && canMove(e)) {
			player.move(0, player.getMoveSpeed());
		}
		else if (code == KeyEvent.VK_A && canMove(e)) {
			player.move(player.getMoveSpeed() * -1, 0);
		}
		else if (code == KeyEvent.VK_D && canMove(e)) {
			player.move(player.getMoveSpeed(), 0);
		}
		else if (code == KeyEvent.VK_E && canInteract()) {
			program.switchToWinScreen();
		}
	}
	
	public double cellWidth() {
		return (double)program.getWidth()/numRows;
	}
	
	public double cellHeight() {
		return (double)program.getHeight()/numCols;
	}
}

