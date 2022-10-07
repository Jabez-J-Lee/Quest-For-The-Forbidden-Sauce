package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class BossRoom extends GraphicsPane implements ActionListener{
	private MainApplication program; // you will use program to get access to
	// all of the GraphicsProgram calls
	private GImage bg;
	private Player player;
	private Bullet newBullet;
	public Boss boss;
	
	private ArrayList<GLine> gridLine;
	
	private ArrayList<Bullet> projectilesDown;
	private ArrayList<Bullet> projectilesLeft;
	private ArrayList<Bullet> projectilesRight;
	private ArrayList<Bullet> projectilesUp;
	public ArrayList<Bullet> projectilesAll;
	
	public Timer bossTimer;
	
	private int numTimes = 0;

	int numRows = 10;
	int numCols = 10;

	public BossRoom(MainApplication app, Bullet updatedBullet) {
		this.program = app;
		
		player = Player.INSTANCE;
		player.setLocation(185, 490);
		
		newBullet = updatedBullet;
		
		boss = new Boss();
		boss.bossSprite.setSize(100,  100);
		boss.bossSprite.setLocation(330, 24);

		// Set Background
		bg = new GImage("media/roomBG.jpg");
		
		// Draw Grid
		gridLine = new ArrayList<GLine>();
		drawGrid(); 
		
		projectilesDown = new ArrayList<Bullet>();
		projectilesLeft = new ArrayList<Bullet>();
		projectilesRight = new ArrayList<Bullet>();
		projectilesUp = new ArrayList<Bullet>();
		projectilesAll = new ArrayList<Bullet>();
		
		bossTimer = new Timer(100, this);
		bossTimer.start();
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
		if(boss.getStatus()) {
			program.add(boss.bossSprite);
		}
	}

	@Override
	public void hideContents() {
		for(GLine line:gridLine) {
			program.remove(line);
		}
		program.remove(bg);
		program.remove(player);
		for(Bullet bullet:projectilesAll) {
			program.remove(bullet.bulletSprite);
		}
		if(!boss.getStatus()) {
			program.remove(boss.bossSprite);
		}
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
		
		double playerX = player.getX();
		double playerY = player.getY();
		
		//Room Enter
		if(((playerX >= 320 && playerX <= 400) && playerY <= 130) && !boss.getStatus()) {
			program.switchToSauceRoom();
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
	}

	public double cellWidth() {
		return (double)program.getWidth()/numRows;
	}

	public double cellHeight() {
		return (double)program.getHeight()/numCols;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int pattern = 0;
		numTimes++;
		moveAllProjectilesOnceDown(); //works with pattern1
		moveAllProjectilesOnceRight(); //works with pattern2
		moveAllProjectilesOnceLeft(); //works with pattern3
		moveAllProjectilesOnceUp(); //works with pattern4
		
		bulletDetect();
		
		if (numTimes % newBullet.getFireRate() == 0) {
			pattern = generateRandomPattern();
			switch(pattern) {
			case 0:
				pattern1();
			case 1:
				pattern2();
			case 2:
				pattern3();
			case 3:
				pattern4();
			}
		}
		
		if(numTimes % 600 == 0) { // At the 1 minute mark of being in bullet hell ONLY, start boss TBB
			bossTimer.stop();
			program.switchToTBB(2, null, boss);
		}
	}

	public void pattern1() {
		for(int i = 0; i < 7; i++) {
			Bullet temp = makeBullet(100 * (i + 1), 100);
			projectilesDown.add(temp);
			projectilesAll.add(temp);
			program.add(temp.bulletSprite);
		}
	}
	
	public void pattern2() {
		for(int i = 0; i < 7; i++) {
			Bullet temp = makeBullet(0, 130 + (i * 75));
			projectilesLeft.add(temp);
			projectilesAll.add(temp);
			program.add(temp.bulletSprite);
		}
	}
	
	public void pattern3() {
		for(int i = 0; i < 7; i++) {
			Bullet temp = makeBullet(800, 130 + (i * 75));
			projectilesRight.add(temp);
			projectilesAll.add(temp);
			program.add(temp.bulletSprite);
		}
	}
	
	public void pattern4() {
		for(int i = 0; i < 7; i++) {
			Bullet temp = makeBullet(100 * (i + 1), 600);
			projectilesUp.add(temp);
			projectilesAll.add(temp);
			program.add(temp.bulletSprite);
		}
	}
	
	private void moveAllProjectilesOnceRight() {
		for (Bullet enemy:projectilesLeft) {
			enemy.bulletSprite.move(enemy.getBulletSpeed(), 0);
		}
	}
	
	private void moveAllProjectilesOnceLeft() {
		for (Bullet enemy:projectilesRight) {
			enemy.bulletSprite.move((-1 * enemy.getBulletSpeed()) , 0);
		}
	}
	
	private void moveAllProjectilesOnceDown() {
		for (Bullet enemy:projectilesDown) {
			enemy.bulletSprite.move(0, enemy.getBulletSpeed());
		}
	}
	
	private void moveAllProjectilesOnceUp() {
		for (Bullet enemy:projectilesUp) {
			enemy.bulletSprite.move(0, -1 * enemy.getBulletSpeed());
		}
	}
	
	private void bulletDetect() {
		for(Bullet enemy:projectilesAll) {
			if(program.getElementAt(enemy.bulletSprite.getX() + enemy.bulletSprite.getWidth() + 1, enemy.bulletSprite.getY() + enemy.bulletSprite.getHeight()/2) instanceof GOval) {
				bossTimer.stop();
				program.switchToTBB(1, enemy, null);
			}
		}
	}
	
	public Bullet makeBullet(double x, double y) {
		Bullet bullet = new Bullet();
		bullet.bulletSprite = new GRect(x,y,10,10);
		bullet.bulletSprite.setColor(Color.red);
		bullet.bulletSprite.setFilled(true);
		
		bullet.setBulletSpeed(newBullet.getBulletSpeed());
		bullet.setDamage(newBullet.getDamage());
		bullet.setFireRate(newBullet.getFireRate());
		bullet.setHealth(newBullet.getHealth());
		
		return bullet;
	}
	
	public int generateRandomPattern() {
		Random rnad = new Random();
		int upperBound = 4;
		int randomPattern = rnad.nextInt(upperBound);

		return randomPattern;
	}
	
}