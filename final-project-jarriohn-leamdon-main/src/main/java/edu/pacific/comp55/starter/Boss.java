package edu.pacific.comp55.starter;

import acm.graphics.GImage;

public class Boss {
	public GImage bossSprite;
	private int bossHealth;
	private int bossDamage;
	private boolean alive = true;
	
	public Boss () {
		bossSprite = new GImage("media/bossBulletHell.png", 0, 0);
		setDamage(1);
		setHealth(6);
		setStatus(true);
	}
	
	public int getHealth() {
		return bossHealth;
	}
	
	public int getDamage() {
		return bossDamage;
	}
	
	public boolean getStatus() {
		return alive;
	}
	
	public void setStatus(boolean status) {
		alive = status;
	}
	
	public void setHealth (int health) {
		bossHealth = health;
	}
	
	public void setDamage (int damage) {
		bossDamage = damage;
	}
	
	public void dealDamage(Player player) {
		player.setPlayerHealth(player.getPlayerHealth() - getDamage());
	}
	
	public void takeDamage(Player player) {
		setHealth(getHealth() - player.getPlayerDamage());
	}
}
