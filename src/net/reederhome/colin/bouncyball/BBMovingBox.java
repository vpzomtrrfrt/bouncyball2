package net.reederhome.colin.bouncyball;

import java.awt.Color;
import java.awt.Graphics;

public class BBMovingBox extends BBMovingObject {

	int boxWidth,boxHeight;
	
	public BBMovingBox() {
		String[] args = BouncyBall.lastArgs;
		boxWidth=Integer.parseInt(args[3]);
		boxHeight=Integer.parseInt(args[4]);
		xv=Integer.parseInt(args[5]);
		yv=Integer.parseInt(args[6]);
	}

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