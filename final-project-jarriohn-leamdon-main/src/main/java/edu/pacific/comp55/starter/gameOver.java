package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.event.MouseEvent;
import acm.graphics.GImage;
import acm.graphics.GObject;

public class gameOver extends GraphicsPane {
	
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	public GImage gameOverBG;
	public GButton continueButton;
	public GButton exitButton;

	public gameOver(MainApplication app) {
		super();
		program = app;

		gameOverBG = new GImage("media/GameOverBG.jpg");
		gameOverBG.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT - 37);
		
		Color white = new Color(255, 255, 255);
		Color black = new Color(0, 0, 0);
		
		continueButton = new GButton("Continue", MenuPane.BUTTONX, MenuPane.BUTTONY, MenuPane.BUTTON_WIDTH, MenuPane.BUTTON_HEIGHT);
		continueButton.setFillColor(white);
		continueButton.setColor(black);

		exitButton = new GButton("Exit", MenuPane.BUTTONX, MenuPane.BUTTONY + 200, MenuPane.BUTTON_WIDTH, MenuPane.BUTTON_HEIGHT);
		exitButton.setFillColor(white);
		exitButton.setColor(black);
	}

	@Override
	public void showContents() {
		program.add(gameOverBG);
		program.add(continueButton);
		program.add(exitButton);
	}

	@Override
	public void hideContents() {
		program.remove(gameOverBG);
		program.remove(continueButton);
		program.remove(exitButton);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == continueButton) {
			program.reset();
		}
		else if(obj == exitButton) {
			System.exit(0);
		}
	}
}
