package edu.pacific.comp55.starter;
import acm.graphics.GImage;

import java.util.Random;

public class chest {
	private static final int REWARDLABELX = 600;
	private static final int REWARDLABELY = 40;
	private static final int REWARDEFFECTY = 525;
	
//	private MainApplication program;
	private int itemValue = 0;
	private boolean opened;
	public GImage chestSprite;
	public Position chestPosition;
	public GParagraph rewardLabel;
	public GParagraph item;
	
	public Player player;
	public Bullet bullet;
	
	public chest() {
		itemValue = generateRandomItem();
		opened = false;
		chestPosition = new Position(8, 2);
	}

	public int generateRandomItem () {
		Random rnad = new Random();
		int upperBound = 11;
		itemValue = rnad.nextInt(upperBound);
		return itemValue;
	}
	
	public int getItemValue() {
		return itemValue;
	}

	public boolean getOpenCloseStatus () {
		return opened;
	}

	public void setOpenCloseStatus (int value) {
		if (value == 0) {
			opened = true;
		}
		else {
			opened = false;
		}
	}
	
	public void applyItem(int itemValue, MainApplication program) {
		switch(itemValue) {
		case 10:
			player.setPlayerHealth(15);
			rewardLabel = new GParagraph("Your Health Has\n"
					+ "Been Increased to\n"
					+ "15 due to having a\n"
					+ "5 layer super\n"
					+ "burrito", REWARDLABELX, REWARDLABELY);
			rewardLabel.setFont("Arial-20");
			program.add(rewardLabel);
			
			program.rewardEffect = new GParagraph("Health Increased\n"
					+ "by 5", REWARDLABELX, REWARDEFFECTY);
			program.rewardEffect.setFont("Arial-15");
			break;
		case 9:
			player.setMoveSpeed(10);
			rewardLabel = new GParagraph("The Chest consisted\n"
					+ "of a Delicious Taco\n"
					+ "Slightly lacking in Sauce.\n"
					+ "Your Movement Speed\n"
					+ "has increased by 5", REWARDLABELX, REWARDLABELY);
			rewardLabel.setFont("Arial-13");
			program.add(rewardLabel);
			
			program.rewardEffect = new GParagraph("Move Speed Increased\n"
					+ "by 5", REWARDLABELX, REWARDEFFECTY);
			program.rewardEffect.setFont("Arial-15");
			break;
		case 8:
			player.setPlayerDamage(3);
			rewardLabel = new GParagraph("The Chest contained\n"
					+ "a sharpened hard\n"
					+ "taco shell sword.\n"				
					+ "3X Damage Multiplier", REWARDLABELX, REWARDLABELY);
			rewardLabel.setFont("Arial-18");
			program.add(rewardLabel);
			
			program.rewardEffect = new GParagraph("3x Damage\n"
					+ "Multiplier", REWARDLABELX, REWARDEFFECTY);
			program.rewardEffect.setFont("Arial-15");
			break;
		case 7:
			bullet.setBulletSpeed(3);
			rewardLabel = new GParagraph("The Chest contained\n"
					+ "a bullet mask.\n"
					+ "Bullets will need\n"
					+ "more time to\n"
					+ "recognize you.\n"
					+ "Bullet Speed Reduced by 1", REWARDLABELX, REWARDLABELY);
			program.add(rewardLabel);
			
			program.rewardEffect = new GParagraph("Bullet Speed Reduced\n"
					+ "by 1", REWARDLABELX, REWARDEFFECTY);
			program.rewardEffect.setFont("Arial-15");
			break;
		case 6:
			bullet.setHealth(2);
			rewardLabel = new GParagraph("The Chest contained\n"
					+ "a mirror to "
					+ "practice "
					+ "intimidating\n"
					+ "faces for battle. "
					+ "You will have the\n"
					+ "bullets shaking in "
					+ "their shells.\n"
					+ "Bullet Health Reduced by 1", REWARDLABELX, REWARDLABELY);
			program.add(rewardLabel); 
			
			program.rewardEffect = new GParagraph("Bullet Health Decreased\n"
					+ "by 1", REWARDLABELX, REWARDEFFECTY);
			program.rewardEffect.setFont("Arial-15");
			break;
		case 5:
			player.setPlayerHealth(7);
			rewardLabel = new GParagraph("The Chest contained\n"
					+ "what you thought was\n"
					+ "a fresh soft taco.\n"
					+ "It was actually "
					+ "there for years.\n"
					+ "You were too hungry to check.\n"
					+ "Your Health has\n"
					+ "decreased to 7", REWARDLABELX, REWARDLABELY);
			program.add(rewardLabel);
			
			program.rewardEffect = new GParagraph("Health decreased\n"
					+ "by 3", REWARDLABELX, REWARDEFFECTY);
			program.rewardEffect.setFont("Arial-15");
			break;
		case 4:
			player.setMoveSpeed(3);
			rewardLabel = new GParagraph("The Chest burst open\n"
					+ "and got sticky expired\n"
					+ "taco sauce on your fresh\n"
					+ "kicks.\n"
					+ "Your Movement Speed\nhas decreased to 3", REWARDLABELX, REWARDLABELY);
			program.add(rewardLabel);
			
			program.rewardEffect = new GParagraph("Player Speed Decreased\n"
					+ "by 3", REWARDLABELX, REWARDEFFECTY);
			program.rewardEffect.setFont("Arial-15");
			break;
		case 3:
			player.setPlayerDamage(0);
			rewardLabel = new GParagraph("You received a Soggy\n"
					+ "Lettuce Slice as a sword.\n"
					+ "Might As well Give up Now", REWARDLABELX, REWARDLABELY);
			rewardLabel.setFont("Arial-15");
			program.add(rewardLabel);
			
			program.rewardEffect = new GParagraph("Damage Dealt Decreased\n"
					+ "to 0", REWARDLABELX, REWARDEFFECTY);
			program.rewardEffect.setFont("Arial-15");
			break;
		case 2:
			bullet.setDamage(3);
			rewardLabel = new GParagraph("The Despised Taco\n"
					+ "has taken the\n"
					+ "contents of the "
					+ "chest.\n"
					+ "Bullet Damage\n"
					+ "increased by 3", REWARDLABELX, REWARDLABELY);
			program.add(rewardLabel);
			
			program.rewardEffect = new GParagraph("Bullet Damage taken\n"
					+ "X3", REWARDLABELX, REWARDEFFECTY);
			program.rewardEffect.setFont("Arial-15");
			break;
		case 1:
			bullet.setFireRate(REWARDLABELY);
			rewardLabel = new GParagraph("The Despised Taco\n"
					+ "Opened the chest\n"
					+ "and is in a suprisingly\n"
					+ "good mood.\n"
					+ "Enemy Fire Rate\n"
					+ "Increased", REWARDLABELX, REWARDLABELY);
			rewardLabel.setFont("Arial-13");
			program.add(rewardLabel);
			
			program.rewardEffect = new GParagraph("Enemy Fire Rate\n"
					+ "Increased by 10", REWARDLABELX, REWARDEFFECTY);
			program.rewardEffect.setFont("Arial-15");
			break;
		default:
			rewardLabel = new GParagraph("Someone put a ton\n"
					+ "of trash in the chest.\n"
					+ "You are morbidly\n"
					+ "upset", REWARDLABELX, REWARDLABELY);
			rewardLabel.setFont("Arial-18");
			program.add(rewardLabel);
			
			program.rewardEffect = new GParagraph("You Have Trash\n"
					+ "in your Pockets", REWARDLABELX, REWARDEFFECTY);
			program.rewardEffect.setFont("Arial-15");
			break;
		}
	}
}