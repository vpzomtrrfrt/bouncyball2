package net.reederhome.colin.bouncyball;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class BBElectricFence extends BBBox {

	public int width() {
		return boxWidth+20;
	}
	
	public int height() {
		return boxHeight+20;
	}
	
	public void draw(Graphics g) {
		g.translate((this.width()-boxWidth)/2, (this.height()-boxHeight)/2);
		super.draw(g);
		g.translate(-(this.width()-boxWidth)/2, -(this.height()-boxHeight)/2);
		g.setColor(new Color(200,200,200));
		for(int i=0;i<20;i++) {
			g.drawLine(x+new Random().nextInt(this.width()), y+new Random().nextInt(this.height()), x+new Random().nextInt(this.width()), y+new Random().nextInt(this.height()));
		}
	}
	
	public void onCollide(BBObject thing) {
		if(thing instanceof BBKey) {
			worldObj.doomed.push(this);
			worldObj.doomed.push(thing);
		}
	}
}