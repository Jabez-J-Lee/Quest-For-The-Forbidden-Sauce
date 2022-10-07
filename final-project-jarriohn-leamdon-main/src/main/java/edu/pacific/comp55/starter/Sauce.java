package edu.pacific.comp55.starter;
import acm.graphics.GImage;

public class Sauce {
	private boolean opened;
	public GImage sauceSprite;
	public Position saucePosition;
	
	public Sauce() {
		sauceSprite = new GImage("media/sauce.png");
		opened = false;
		saucePosition = new Position(4, 4);
		
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
}