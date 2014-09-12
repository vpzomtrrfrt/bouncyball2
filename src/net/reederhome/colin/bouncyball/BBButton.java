package net.reederhome.colin.bouncyball;

import java.awt.Color;
import java.awt.Graphics;

public class BBButton extends BBObject {
	String action;
	String actionarg;
	public BBButton() {
		String[] args = BouncyBall.lastArgs;
		action = args[3];
		if(args.length>4) {
			actionarg=args[4];
		}
	}
	
	int press = 10;
	
	@Override
	public void update() {if(press<10)press++;}

	@Override
	public void draw(Graphics g) {
		for(int i = 0; i < press+1; i++) {
			g.setColor(Color.red.darker().darker());
			g.drawRoundRect(x-i, y-i, width(), height(), 20, 20);
			g.setColor(Color.red);
			g.fillRoundRect(x-i, y-i, width(), height(), 20, 20);
		}
		g.setColor(Color.gray);
		if(action.equals("gravity")) {
			g.drawLine(x-press+width()/2, y-press+height()/3, x-press+width()/2, y-press+height()*2/3);
			g.drawLine(x-press+width()/2, y-press+height()*2/3, x-press+width()/3, y-press+height()/2);
			g.drawLine(x-press+width()/2, y-press+height()*2/3, x-press+width()*2/3, y-press+height()/2);
		}
		else if(action.equals("spawn")) {
			g.fillOval(x-press+width()/3, y-press+height()/3, width()/3, height()/3);
			g.drawString("?", x-press+10, y-press+10);
		}
		else {
			g.drawString("ERROR", x, y);
		}
	}

	@Override
	public int width() {
		return 50;
	}

	@Override
	public int height() {
		return 50;
	}
	
	@Override
	public void onCollide(BBObject thing) {
		if(thing instanceof BBMovingObject && press>5) {
			press=0;
			if(action.equals("gravity")) {
				worldObj.gravity=worldObj.gravity>0?0:0.33;
			}
			else if(action.equals("spawn")) {
				worldObj.addObject(BouncyBall.loadLine(actionarg));
			}
		}
	}

}