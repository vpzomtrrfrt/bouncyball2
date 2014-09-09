package net.reederhome.colin.bouncyball;

import java.awt.Color;
import java.awt.Graphics;

public class BBKey extends BBObject {

	BBMovingObject collected = null;
	
	@Override
	public void update() {
		if(collected!=null) {
			x = collected.x+collected.width()/2;
			y = collected.y+collected.height()/2;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.pink);
        g.fillRect(x+width()-2, y+(height()/2), 2, height());
        g.fillRect(x+width()-7, y+(height()/2), 2, height());
        g.fillRect(x+(width()/2), y+(height()/2), (width()/2), (height()/3));
        for(int i = 0; i < 10; i++) {
                g.setColor(g.getColor().brighter().brighter());
                g.drawOval(x+i, y+i, (width()/2)-i, height()-i);
        }
	}

	@Override
	public int width() {
		return height()*2;
	}

	@Override
	public int height() {
		return 15;
	}
	
	public String noCollision(BBObject thing) {
		return thing instanceof BBMovingObject?"you":"";
	}
	
	public void onCollide(BBObject thing) {
		if(thing instanceof BBMovingObject) {
			collected=(BBMovingObject) thing;
		}
	}

}