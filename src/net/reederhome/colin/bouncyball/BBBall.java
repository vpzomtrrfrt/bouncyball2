package net.reederhome.colin.bouncyball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BBBall extends BBMovingObject implements KeyListener {

	int ballnum = 0;
	static Color[] ballcolor = new Color[]{Color.orange, Color.green};
	static int[][] ballcontrols = new int[][]{
		new int[]{KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT},
		new int[]{KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D}
	};
	public int width() {return 20;}
	public int height() {return width();}
	
	public void addToWorld() {
		worldObj.lastballnum++;
		ballnum = worldObj.lastballnum;
	}
	
	@Override
	public void draw(Graphics g) {
		if(!worldObj.isStarted()) {
			g.setColor(Color.black);
			for(int i = 0; i < 20; i++) {
				g.drawLine((int)(x+width()/2+xv*(i)), (int)(y+height()/2+yv*(i)), (int)(x+width()/2+xv*(i+0.5)), (int)(y+height()/2+yv*(i+0.5)));
			}
			//g.drawLine(x+width()/2, y+height()/2, x+width()/2+xv*width(), y+height()/2+yv*height());
		}
		g.setColor(ballcolor[ballnum]);
		g.fillOval(x, y, width(), height());
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(worldObj.isStarted()) return;
		int code = e.getKeyCode();
		if(code==ballcontrols[ballnum][1]) this.xv--;
		if(code==ballcontrols[ballnum][3])this.xv++;
		if(code==ballcontrols[ballnum][0]) this.yv--;
		if(code==ballcontrols[ballnum][2])this.yv++;
		this.xv=Math.max(Math.min(this.xv, 5),-5);
		this.yv=Math.max(Math.min(this.yv, 5),-5);
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
}