package net.reederhome.colin.bouncyball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BBBall extends BBMovingObject implements KeyListener {

	public int width() {return 20;}
	public int height() {return width();}
	
	@Override
	public void draw(Graphics g) {
		if(!worldObj.isStarted()) {
			g.setColor(Color.black);
			g.drawLine(x+width()/2, y+height()/2, x+width()/2+xv*width(), y+height()/2+yv*height());
		}
		g.setColor(Color.orange);
		g.fillOval(x, y, width(), height());
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code==KeyEvent.VK_LEFT) this.xv--;
		if(code==KeyEvent.VK_RIGHT)this.xv++;
		if(code==KeyEvent.VK_UP) this.yv--;
		if(code==KeyEvent.VK_DOWN)this.yv++;
		this.xv=Math.max(Math.min(this.xv, 5),-5);
		this.yv=Math.max(Math.min(this.yv, 5),-5);
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
}