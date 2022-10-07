package edu.pacific.comp55.starter;

import acm.graphics.GOval;

public class Player extends GOval{
	public static final double TBB = 150;
	public static final double SIZE = 50;
	
	public GOval playerSprite;
	private int playerHealth;
	private int playerDamage;
	private int moveSpeed;
	
	public static Player INSTANCE; // Creating a singleton for player so that Passing player to every pane is unnecessary
	
	public Player() {
		super(375, 450, SIZE, SIZE);
		super.setFilled(true);
		playerHealth = 10;
		playerDamage = 1;
		setMoveSpeed(5);
		INSTANCE = this;
	}
	
	public int getPlayerHealth() {
		return playerHealth;
	}
	
	public void setPlayerHealth(int health) {
		this.playerHealth = health;
	}

	public int getPlayerDamage() {
		return playerDamage;
	}
	
	public void setPlayerDamage(int damage) {
		this.playerDamage = damage;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
}