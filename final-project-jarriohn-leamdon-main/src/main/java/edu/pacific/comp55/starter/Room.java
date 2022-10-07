package edu.pacific.comp55.starter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GLine;

public class Room extends GraphicsPane{
	public static final int REWARDX = 600;
	public static final int REWARDY = 40;
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GImage bg;
	private GParagraph moveTutorial;
	private GParagraph interactTutorial;
	private chest newChest;
	
	public Player player;
	public Bullet updatedBullet;
	
	private ArrayList<GLine> gridLine;
	
	int numRows = 10; //Set board temporarily
	int numCols = 10; //Set it to retrieve size from text file

	public Room(MainApplication app) {
		this.program = app;
		
		// Set Background
		bg = new GImage("media/roomBG.jpg");
		
		// Set Tutorial Messages
		moveTutorial = new GParagraph("        Press\nWASD to move \nthe character", 35, 25);
		interactTutorial = new GParagraph("   Press E\nTo Interact", REWARDX, REWARDY);
		moveTutorial.setFont("Arial-24");
		interactTutorial.setFont("Arial-24");
		
		// Draw Grid
		gridLine = new ArrayList<GLine>();
		drawGrid(); 
		
		// Passing Singleton Player
		player = Player.INSTANCE;
		
		// Creates bullet to be buffed/nerfed if chest is opened
		updatedBullet = new Bullet();

		// Creates Chest and Generates Reward
		drawChest();
		newChest.player = player;
		newChest.bullet = updatedBullet;
	}
	
	public void drawGrid() {
		for(int i = 1; i < numRows; i++) {
			GLine vertLine = new GLine(i * cellWidth(), 0, i * cellWidth(), program.getHeight());
			GLine horizLine = new GLine(0, i * cellHeight(), program.getWidth(), i * cellHeight());
			gridLine.add(vertLine);
			gridLine.add(horizLine);
		}
	}
	
	public void drawChest() {
		newChest = new chest();
		double chestRow = newChest.chestPosition.getRow() * cellWidth();
		double chestCol = newChest.chestPosition.getCol() * cellHeight();
		
		newChest.chestSprite = new GImage("media/chest_sprite.jpeg", chestRow, chestCol);
		newChest.chestSprite.setSize(cellWidth(), cellHeight());
	}

	@Override
	public void showContents() {
		for(GLine line:gridLine) {
			program.add(line);
		} 
		program.add(bg);
		program.add(player);
		program.add(moveTutorial);
		program.add(newChest.chestSprite);
	}

	@Override
	public void hideContents() {
		for(GLine line:gridLine) {
			program.remove(line);
		}
		program.remove(bg);
		program.remove(player);
		program.remove(moveTutorial);
		program.remove(interactTutorial);
		program.remove(newChest.chestSprite);
		program.remove(newChest.rewardLabel);
	}
	
	public boolean canInteract() {
		double playerX = player.getX();
		double playerY = player.getY();

		return playerX >= 575 && playerY <= 200;
	}
	
	public boolean canMove(KeyEvent e) {
		int code = e.getKeyCode();
		
		double playerX = player.getX();
		double playerY = player.getY();

		if (code == KeyEvent.VK_W) {
			if(playerY > 130 && !(playerX >= 595 && playerY <= 195)) {
				return true;
			}
			return false;
		}
		else if (code == KeyEvent.VK_S) {
			return playerY < 510;
		}
		else if (code == KeyEvent.VK_A) {
			return playerX > 60;
		}
		else if (code == KeyEvent.VK_D) {
			if(playerX < 705 && !(playerY <= 175 && playerX >= 590)) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		double playerX = player.getX();
		double playerY = player.getY();
		
		// Room Enter
		if((playerX >= 320 && playerX <= 430) && playerY <= 130) {
			if(!newChest.getOpenCloseStatus()) { // If the player doesn't open the chest set reward message to empty
				newChest.rewardLabel = new GParagraph(" ", REWARDX, REWARDY);
				program.rewardEffect = new GParagraph(" ", REWARDX, REWARDY * 12.5);
			}
			program.switchToBoss(updatedBullet);
		}
		
		// Chest Interact Display
		if(canInteract()) {
			program.add(interactTutorial);
		}
		else {
			program.remove(interactTutorial);
		}

		// Player Input
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
		else if (code == KeyEvent.VK_E && canInteract() && newChest.getOpenCloseStatus() == false) {
			newChest.setOpenCloseStatus(0);
			program.remove(interactTutorial);
			interactTutorial = new GParagraph(" ", REWARDX, REWARDY);
			newChest.applyItem(newChest.getItemValue(), program);
		}
	}
	
	public double cellWidth() {
		return (double)program.getWidth()/numRows;
	}
	
	public double cellHeight() {
		return (double)program.getHeight()/numCols;
	}
}
