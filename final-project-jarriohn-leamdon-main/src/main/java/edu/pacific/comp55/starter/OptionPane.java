package edu.pacific.comp55.starter;
import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class OptionPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
									 // all of the GraphicsProgram calls
	private GImage optionsBG;
	private GButton muteButton;
	private GButton backButton;
	
	public Boolean mute = false;
	AudioPlayer audio;
	int count = 0;

	public OptionPane(MainApplication app) {
		this.program = app;
		optionsBG = new GImage("media/optionsBG.jpg");
		
		audio = AudioPlayer.getInstance();
		
		Color lightYellow = new Color(233, 207, 139);
		Color darkYellow = new Color(231, 176, 35);
		
		muteButton = new GButton("Sounds", MenuPane.BUTTONX, MenuPane.BUTTONY, MenuPane.BUTTON_WIDTH, MenuPane.BUTTON_HEIGHT);
		muteButton.setFillColor(lightYellow);
		muteButton.setColor(darkYellow);
		
		backButton = new GButton("Back", MenuPane.BUTTONX, MenuPane.BUTTONY + 100, MenuPane.BUTTON_WIDTH, MenuPane.BUTTON_HEIGHT);
		backButton.setFillColor(lightYellow);
		backButton.setColor(darkYellow);
	}

	@Override
	public void showContents() {
		program.add(optionsBG);
		program.add(muteButton);
		program.add(backButton);
	}

	@Override
	public void hideContents() {
		program.remove(optionsBG);
		program.remove(muteButton);
		program.remove(backButton);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == backButton) {
			program.switchToMenu();
		}
		else if(obj == muteButton) {
			count++;
			if(count % 2 == 0) {
				System.out.println("UnMuted Sounds");
				audio.playSound(MainApplication.MUSIC_FOLDER, MainApplication.SOUND_FILES[0], true);
				mute = false;
			}
			else {
				System.out.println("Muted Sounds");
				audio.stopSound(MainApplication.MUSIC_FOLDER, MainApplication.SOUND_FILES[0]);
				mute = true;
			}
		}
	}
}
