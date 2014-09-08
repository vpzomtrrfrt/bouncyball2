package net.reederhome.colin.bouncyball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class BBMouseBox extends BBObject implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(worldObj.isStarted()) return;
		this.x=e.getX();
		this.y=e.getY();
	}

	@Override
	public void update() {}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.gray);
		g.fill3DRect(x, y, width(), height(), true);
	}

	@Override
	public int width() {
		return 20;
	}

	@Override
	public int height() {
		return width();
	}

	
}