package edu.pacific.comp55.starter;

import acm.graphics.GRect;

public class Bullet {
	public GRect bulletSprite;
	private int bulletHealth;
	private int bulletDamage;
	public int fireRate;
	public int bulletSpeed;
	
	public Bullet () {
		setDamage(1);
		setHealth(3);
		setFireRate(50);
		setBulletSpeed(4);
	}
	
	public int getHealth() {
		return bulletHealth;
	}
	
	public int getDamage() {
		return bulletDamage;
	}
	
	public int getFireRate() {
		return fireRate;
	}
	
	public int getBulletSpeed() {
		return bulletSpeed;
	}
	
	public void setHealth (int health) {
		bulletHealth = health;
	}
	
	public void setDamage (int damage) {
		bulletDamage = damage;
	}
	
	public void setFireRate(int rate) {
		fireRate = rate;
	}
	
	public void setBulletSpeed(int speed) {
		bulletSpeed = speed;
	}
	
	public void dealDamage(Player player) {
		player.setPlayerHealth(player.getPlayerHealth() - getDamage());
	}
	
	public void takeDamage(Player player) {
		setHealth(getHealth() - player.getPlayerDamage());
	}
}
