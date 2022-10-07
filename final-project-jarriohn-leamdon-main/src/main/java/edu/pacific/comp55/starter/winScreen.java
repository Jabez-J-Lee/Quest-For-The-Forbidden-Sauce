package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.event.MouseEvent;
import acm.graphics.GImage;
import acm.graphics.GObject;

public class winScreen extends GraphicsPane {
	
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	public GImage winScreenBG;
	public GButton continueButton;
	public GButton exitButton;

	public winScreen(MainApplication app) {
		super();
		program = app;

		winScreenBG = new GImage("media/WinScreenBG.jpg");
		winScreenBG.setSize(MainApplication.WINDOW_WIDTH, MainApplication.WINDOW_HEIGHT - 37);
		
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
		program.add(winScreenBG);
		program.add(continueButton);
		program.add(exitButton);
	}

	@Override
	public void hideContents() {
		program.remove(winScreenBG);
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
