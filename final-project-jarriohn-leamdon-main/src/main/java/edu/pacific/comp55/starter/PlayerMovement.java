package edu.pacific.comp55.starter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class PlayerMovement extends JPanel implements ActionListener, KeyListener{
	Timer t = new Timer(5, this);
	double x = 500, y = 500, velx = 0, vely = 0;

	public PlayerMovement() {
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(new Ellipse2D.Double(x, y, 40, 40));
	}
	public void actionPerformed(ActionEvent e) {
		repaint();
		x += velx;
		y += vely;
	}
	
	
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			System.out.println("Player Moves Up");
		}
		if (code == KeyEvent.VK_S) {
			System.out.println("Player Moves Down");
		}
		if (code == KeyEvent.VK_A) {
			System.out.println("Player Moves Left");
		}
		if (code == KeyEvent.VK_D) {
			System.out.println("Player Moves Right");
		}
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}
