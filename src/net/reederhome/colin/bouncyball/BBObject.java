package net.reederhome.colin.bouncyball;

import java.awt.Graphics;

public abstract class BBObject {

	BBWorld worldObj;
	int x,y;
	public BBObject() {
		String[] args = BouncyBall.lastArgs;
		if(args.length>2) {
			x=Integer.parseInt(args[1]);
			y=Integer.parseInt(args[2]);
		}
		else {
			x=0;y=0;
		}
	}
	public abstract void update();
	public abstract void draw(Graphics g);
	public abstract int width();
	public abstract int height();
	public String noCollision() {return "";}
	public void onCollide(BBObject thing) {}
	public void addToWorld() {}
}