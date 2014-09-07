package net.reederhome.colin.bouncyball;

import java.awt.Color;
import java.awt.Graphics;

public class BBFinish extends BBObject {

	@Override
	public void update() {}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fill3DRect(x, y, width(), height(), true);
	}

	@Override
	public int width() {
		return 50;
	}

	@Override
	public int height() {
		return 40;
	}
	
	public String noCollision() {return "you";}
	
	public void onCollide(BBObject thing) {
		if(thing instanceof BBBall) {
			worldObj.going=false;
			worldObj.win=0;
			thing.x=x+width()/2-thing.width()/2;
			thing.y=y+height()/2-thing.height()/2;
		}
	}
}