package net.reederhome.colin.bouncyball;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComponent;
public class BBWorld extends JComponent implements KeyListener {

	private static final long serialVersionUID = 5328167859301614764L;
	
	boolean going = false;
	ArrayList<BBObject> obj = new ArrayList<BBObject>();
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		for(int i = 0; i < obj.size(); i++) {
			obj.get(i).draw(g);
		}
	}
	
	public void updateObjects() {
		for(int i = 0; i < obj.size(); i++) {
			obj.get(i).update();
		}
	}

	public void addObject(BBObject no) {
		no.worldObj=this;
		obj.add(no);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(BouncyBall.world!=this) return;
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
			going = true;
		}
		for(int i = 0; i < obj.size(); i++) {
			if(obj.get(i) instanceof KeyListener) {
				((KeyListener) obj.get(i)).keyPressed(arg0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(BouncyBall.world!=this) return;
		for(int i = 0; i < obj.size(); i++) {
			if(obj.get(i) instanceof KeyListener) {
				((KeyListener) obj.get(i)).keyReleased(arg0);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if(BouncyBall.world!=this) return;
		for(int i = 0; i < obj.size(); i++) {
			if(obj.get(i) instanceof KeyListener) {
				((KeyListener) obj.get(i)).keyTyped(arg0);
			}
		}
	}
}