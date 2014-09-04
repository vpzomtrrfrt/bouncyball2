package net.reederhome.colin.bouncyball;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;
public class BBWorld extends JComponent {

	private static final long serialVersionUID = 5328167859301614764L;
	
	boolean going = true;
	ArrayList<BBObject> obj = new ArrayList<BBObject>();
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		for(int i = 0; i < obj.size(); i++) {
			obj.get(i).draw(g);
		}
	}
}