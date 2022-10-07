package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;

public class TurnBasedBattle extends GraphicsPane implements ActionListener {
	public static final int ENEMY_X = 550;
	public static final int ENEMY_Y = 75;
	private static final int HEART = 30;
	private static final int ATTACKPIX = 50;
	private static final int RECPIX = 15;
	private static final int BUTTONX = 225;
	private static final int BUTTONY = 500;
	private static final int BUTTON_SIZE = 100;
	private static final int PLAYERX = 75;
	private static final int PLAYERY = 275;
	
	private MainApplication program; 	// you will use program to get access to
										// all of the GraphicsProgram calls
	private GImage tbbBGBoss;
	private GImage tbbBGBullet; 
	private GImage realBulletSprite;
	
	public GLine border;
	
	public GLabel actionLabel;
	
	public GParagraph itemEffectLabel;
	public GParagraph itemEffect;
	
	public GButton attackButton;
	public GButton defendButton;
	
	public Timer battleTimer;
	
	int type;
	int numTimes = 0;
	
	public boolean cooldown = false;
	
	public Player player;
	public Bullet enemy;
	public Boss boss;
	
	int playerHealth;
	public ArrayList<GImage> playerHearts;
	
	int enemyHealth;
	public ArrayList<GImage> enemyHearts;

	public TurnBasedBattle(MainApplication app, int enemyType, Bullet bulletObject, Boss argBoss) {
		super();
		program = app;
		
		type = enemyType;
		
		battleTimer = new Timer(1000, this);

		tbbBGBoss = new GImage("media/bossTBBbackground.jpg");
		tbbBGBoss.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT - 37);
		
		tbbBGBullet = new GImage("media/bulletTBBbackground.jpg");
		tbbBGBullet.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT - 37);
		
		realBulletSprite = new GImage("media/bulletGuy.jpg");
		realBulletSprite.setSize(Player.TBB, Player.TBB);
		realBulletSprite.setLocation(ENEMY_X, ENEMY_Y);
		
		border = new GLine(0, 435, 800, 435);
		
		actionLabel = new GLabel("Actions: ", 40, 475);
		actionLabel.setFont("Arial-20");
		
		itemEffectLabel = new GParagraph("Item\nEffect: ", Room.REWARDX, 475);
		itemEffectLabel.setFont("Arial-20");

		attackButton = new GButton("Attack", BUTTONX, BUTTONY, BUTTON_SIZE, BUTTON_SIZE/2);
		attackButton.setFillColor(Color.white);
		attackButton.setColor(Color.black);

		defendButton = new GButton("Defend", BUTTONX + 175, BUTTONY, BUTTON_SIZE, BUTTON_SIZE/2);
		defendButton.setFillColor(Color.white);
		defendButton.setColor(Color.black);
		
		playerHearts = new ArrayList<GImage>();
		enemyHearts = new ArrayList<GImage>();
		
		player = Player.INSTANCE;
		player.setLocation(75, 275);
		player.setSize(Player.TBB, Player.TBB);
		
		if(enemyType == 1) {
			enemy = bulletObject;
			enemy.bulletSprite.setColor(Color.red);
			enemy.bulletSprite.setFilled(true);
			enemy.bulletSprite.setSize(Player.TBB, Player.TBB);
			enemy.bulletSprite.setLocation(ENEMY_X, ENEMY_Y) ;
			
			createHealth(player.getPlayerHealth(), enemy.getHealth());
		}
		else {
			boss = argBoss;
			boss.bossSprite.setImage("media/BossTBB.png");
			boss.bossSprite.setSize(Player.TBB, Player.TBB);
			boss.bossSprite.setLocation(ENEMY_X, ENEMY_Y);
			
			createHealth(player.getPlayerHealth(), boss.getHealth());
		}
		
		itemEffect = program.rewardEffect;
	}

	@Override
	public void showContents() {
		if (type == 1) {
			program.add(tbbBGBullet);
		}
		else {
			program.add(tbbBGBoss);
		}
		program.add(player);
		program.add(border);
		program.add(actionLabel);
		program.add(itemEffectLabel);
		program.add(attackButton);
		program.add(defendButton);
		if(type == 1) {
			program.add(realBulletSprite);
		}
		else {
			program.add(boss.bossSprite);
		}
		program.add(itemEffect);
		for(GImage heart:playerHearts) {
			program.add(heart);
		}
		for(GImage heart:enemyHearts) {
			program.add(heart);
		}
	}

	@Override
	public void hideContents() {
		if (type == 1) {
			program.remove(tbbBGBullet);
		}
		else {
			program.remove(tbbBGBoss);
		}
		program.remove(player);
		program.remove(border);
		program.remove(actionLabel);
		program.remove(itemEffectLabel);
		program.remove(attackButton);
		program.remove(defendButton);
		if(type == 1) {
			program.remove(realBulletSprite);
		}
		else {
			program.remove(boss.bossSprite);
		}
		program.remove(itemEffect);
		for(GImage heart:playerHearts) {
			program.remove(heart);
		}
		for(GImage heart:enemyHearts) {
			program.remove(heart);
		}
	}
	
	public void attackAnimation() {
		player.move(ATTACKPIX, ATTACKPIX * -1);
		
		if (type == 1) {
			realBulletSprite.move(RECPIX, -1 * RECPIX);
		}
		else {
			boss.bossSprite.move(RECPIX, -1 * RECPIX);
		}
	}
	
	public void enemyAnimation() {
		if (type == 1) {
			realBulletSprite.move(-1 * ATTACKPIX, ATTACKPIX);
		}
		else {
			boss.bossSprite.move(-1 * ATTACKPIX, ATTACKPIX);
		}
		player.move(-1 * RECPIX, RECPIX);
	}
	
	public void createHealth(int playerHealth, int enemyHealth) {
		int playerStart = 780;
		int enemyStart = 0;
		
		// Player Hearts
		for(int i = 0; i < playerHealth; i++) {
			GImage heart = new GImage("media/heart.png");
			heart.setSize(HEART, HEART);
			playerStart -= HEART;
			heart.setLocation(playerStart, HEART * 10);
			playerHearts.add(heart);
		}
		
		// Enemy Hearts
		for(int i = 0; i < enemyHealth; i++) {
			GImage heart = new GImage("media/heart.png");
			heart.setSize(HEART, HEART);
			enemyStart += HEART;
			heart.setLocation(enemyStart, ENEMY_Y);
			enemyHearts.add(heart);
		}
	}
	
	public void updateHealth(ArrayList<GImage> playerHearts, ArrayList<GImage> enemyHearts) {
		if(type == 1) {
			for(int i = 0; i < enemy.getDamage(); i++) {
				int playerCurrentHealth = playerHearts.size() - player.getPlayerHealth();
				int playerIndex = playerHearts.size() - playerCurrentHealth;
				program.remove(playerHearts.get(playerIndex));
				playerHearts.remove(playerIndex);
			}
		}
		else {
			for(int i = 0; i < boss.getDamage(); i++) {
				int playerCurrentHealth = playerHearts.size() - player.getPlayerHealth();
				int playerIndex = playerHearts.size() - playerCurrentHealth;
				program.remove(playerHearts.get(playerIndex));
				playerHearts.remove(playerIndex);
			}
		}
		for(int i = 0; i < player.getPlayerDamage(); i++) {
			if(type == 1) {
				int enemyCurrentHealth = enemyHearts.size() - enemy.getHealth();
				int enemyIndex = enemyHearts.size() - enemyCurrentHealth;
				program.remove(enemyHearts.get(enemyIndex));
				enemyHearts.remove(enemyIndex);
			}
			else {
				int bossCurrentHealth = enemyHearts.size() - boss.getHealth();
				int bossIndex = enemyHearts.size() - bossCurrentHealth;
				program.remove(enemyHearts.get(bossIndex));
				enemyHearts.remove(bossIndex);
			}
		}
	}
	
	public boolean checkHealth(int playerHealth, int enemyHealth) {
		int predictedPlayerHealth = playerHealth - 1;
		int predictedEnemyHealth = enemyHealth - player.getPlayerDamage();
		
		if(predictedEnemyHealth <= 0) {
			player.setSize(Player.SIZE, Player.SIZE);
			if(type == 1) {
				program.bossRoom.bossTimer.start(); 		// Starting the bossTimer upon pane switch
				program.bossRoom.projectilesAll.clear(); 	// Clearing the boss room of all previous bullets
			}
			else {
				program.bossRoom.boss.setStatus(false);
				program.bossRoom.bossTimer.stop(); 		
				program.bossRoom.projectilesAll.clear(); 
			}
			battleTimer.stop();
			program.continueToBossRoom();
			player.setLocation(375, 450);
			return false;
		}
		else if(predictedPlayerHealth <= 0) {
			battleTimer.stop();
			program.bossRoom.bossTimer.stop();
			program.switchToGameOver();
			return false;
		}
		return true;
	}
	
	public void enemyAttacksPlayer() {
		if(type == 1) {
			enemy.setHealth(enemy.getHealth() - player.getPlayerDamage());
			enemy.dealDamage(player);
			updateHealth(playerHearts, enemyHearts);
		}
		else {
			boss.setHealth(boss.getHealth() - player.getPlayerDamage());
			boss.dealDamage(player);
			updateHealth(playerHearts, enemyHearts);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == attackButton && !cooldown) {
			cooldown = true;
			battleTimer.start();
			
			attackAnimation();
			
			if(type == 1 && checkHealth(player.getPlayerHealth(), enemy.getHealth())) {
				enemyAttacksPlayer();
			}
			else if(type != 1 && checkHealth(player.getPlayerHealth(), boss.getHealth())){
				enemyAttacksPlayer();
			}
		}
		else if(obj == defendButton && !cooldown) {
			cooldown = true;
			battleTimer.start();
			enemyAnimation();
		}
	}
	
	public void resetEnemyPosition(int numTimes) {
		if (numTimes % 2 == 0) {
			player.setLocation(PLAYERX, PLAYERY);
			if (type == 1) {
				realBulletSprite.setLocation(ENEMY_X, ENEMY_Y);
				battleTimer.stop();
			}
			else {
				boss.bossSprite.setLocation(ENEMY_X, ENEMY_Y);
				battleTimer.stop();
			}
			cooldown = false;
		}
	}
	
	public void resetPlayerPosition(int numTimes) {
		player.setLocation(PLAYERX, PLAYERY);
		if (type == 1) {
			realBulletSprite.setLocation(ENEMY_X, ENEMY_Y);
		}
		else {
			boss.bossSprite.setLocation(ENEMY_X, ENEMY_Y);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		numTimes++;
		if (numTimes % 1 == 0) {
			resetPlayerPosition(numTimes);
			enemyAnimation();
			resetEnemyPosition(numTimes);
		}
	}
	
}
