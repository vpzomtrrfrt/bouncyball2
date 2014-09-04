package net.reederhome.colin.bouncyball;

import java.awt.Color;
import java.awt.Graphics;

public class BBBox extends BBObject {

	int boxWidth,boxHeight;
	
	public BBBox() {
		String[] args = BouncyBall.lastArgs;
		boxWidth=Integer.parseInt(args[3]);
		boxHeight=Integer.parseInt(args[4]);
	}
	
	@Override
	public void update() {}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fill3DRect(x, y, width(), height(), true);
	}

	@Override
	public int width() {
		return boxWidth;
	}

	@Override
	public int height() {
		return boxHeight;
	}

}