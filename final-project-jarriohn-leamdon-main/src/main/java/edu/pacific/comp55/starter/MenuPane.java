package edu.pacific.comp55.starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	public static final int BUTTON_WIDTH = 150;
	public static final int BUTTON_HEIGHT = 75;
	public static final int BUTTONX = 325;
	public static final int BUTTONY = 275;
	private MainApplication program; // you will use program to get access to
										// all of the GraphicsProgram calls
	public GImage menuBG;
	public GButton startButton;
	public GButton optionsButton;
	public GButton exitButton;

	public MenuPane(MainApplication app) {
		super();
		program = app;

		menuBG = new GImage("media/mainBG.jpg");
		
		Color lightYellow = new Color(233, 207, 139);
		Color darkYellow = new Color(231, 176, 35);
		
		startButton = new GButton("Start Game", BUTTONX, BUTTONY, BUTTON_WIDTH, BUTTON_HEIGHT);
		startButton.setFillColor(lightYellow);
		startButton.setColor(darkYellow);
		
		optionsButton = new GButton("Options", BUTTONX, BUTTONY + 100, BUTTON_WIDTH, BUTTON_HEIGHT);
		optionsButton.setFillColor(lightYellow);
		optionsButton.setColor(darkYellow);

		exitButton = new GButton("Exit Game", BUTTONX, BUTTONY + 200, BUTTON_WIDTH, BUTTON_HEIGHT);
		exitButton.setFillColor(lightYellow);
		exitButton.setColor(darkYellow);
	}

	@Override
	public void showContents() {
		program.add(menuBG);
		program.add(startButton);
		program.add(optionsButton);
		program.add(exitButton);
	}

	@Override
	public void hideContents() {
		program.remove(menuBG);
		program.remove(startButton);
		program.remove(optionsButton);
		program.remove(exitButton);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == startButton) {
			program.switchToStart();
		}
		else if(obj == optionsButton) {
			program.switchToOptions();
		}
		else if(obj == exitButton) {
			System.exit(0);
		}
	}
}
