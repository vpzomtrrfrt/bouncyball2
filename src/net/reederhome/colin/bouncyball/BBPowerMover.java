package net.reederhome.colin.bouncyball;

import java.awt.Color;
import java.awt.Graphics;

public class BBPowerMover extends BBObject {

	@Override
	public void update() {}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillPolygon(new int[]{x,x+width()/4,x+width(),x+width(),x+width()/4}, new int[]{y+height()/2,y,y,y+height(),y+height()}, 5);
	}

	@Override
	public int width() {
		return 60;
	}

	@Override
	public int height() {
		return 20;
	}
	
	@Override
	public void onCollide(BBObject thing) {
		if(thing instanceof BBMovingObject) {
			BBMovingObject mo = (BBMovingObject) thing;
			mo.xv=-Math.min(Math.sqrt(mo.xv*mo.xv+mo.yv*mo.yv), 5.0);
			mo.yv=0;
		}
	}

}