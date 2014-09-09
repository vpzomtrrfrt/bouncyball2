package net.reederhome.colin.bouncyball;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class BBBox extends BBObject {

	int boxWidth,boxHeight;
	JLabel l = new JLabel();
	
	public BBBox() {
		String[] args = BouncyBall.lastArgs;
		boxWidth=Integer.parseInt(args[3]);
		boxHeight=Integer.parseInt(args[4]);
		if(args.length>5) {
			l.setText(args[5]);
			l.setSize(boxWidth, boxHeight);
			l.setForeground(Color.red);
			if(args.length>6) {
				l.setFont(l.getFont().deriveFont(Float.parseFloat(args[6])));
			}
		}
	}
	
	@Override
	public void update() {}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fill3DRect(x, y, width(), height(), true);
		g.translate(x, y);
		l.paint(g);
		g.translate(-x, -y);
	}

	@Override
	public int width() {
		return boxWidth;
	}

	@Override
	public int height() {
		return boxHeight;
	}

}