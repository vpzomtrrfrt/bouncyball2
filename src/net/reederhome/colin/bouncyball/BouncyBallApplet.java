package net.reederhome.colin.bouncyball;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class BouncyBallApplet extends JApplet implements ActionListener {

	private static final long serialVersionUID = -3578311308946183860L;

	JButton b;
	public void init() {
		b = new JButton("Play");
		b.addActionListener(this);
		add(b);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		BouncyBall.main(new String[]{});
	}
}