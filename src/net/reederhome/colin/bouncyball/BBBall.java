package net.reederhome.colin.bouncyball;

import java.awt.Color;
import java.awt.Graphics;

public class BBBall extends BBMovingObject {

	public BBBall() {
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.orange);
		g.fillOval(x, y, 30, 30);
	}
}