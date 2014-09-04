package net.reederhome.colin.bouncyball;

import java.awt.Graphics;

public abstract class BBObject {

	BBWorld worldObj;
	int x,y;
	public BBObject() {
		String[] args = BouncyBall.lastArgs;
		x=Integer.parseInt(args[1]);
		y=Integer.parseInt(args[2]);
	}
	public abstract void update();
	public abstract void draw(Graphics g);
	public abstract int width();
	public abstract int height();
	public String noCollision() {return "";}
	public void onCollision(BBObject thing) {}
}