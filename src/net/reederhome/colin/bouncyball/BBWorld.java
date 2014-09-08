package net.reederhome.colin.bouncyball;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComponent;
public class BBWorld extends JComponent implements KeyListener {

	private static final long serialVersionUID = 5328167859301614764L;
	
	boolean going = false;
	int win = -1;
	private String levelName = "";
	int time = -1;
	ArrayList<BBObject> obj = new ArrayList<BBObject>();
	
	public BBWorld(String name, int time) {
		levelName=name;
		this.time=time;
	}
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		for(int i = 0; i < obj.size(); i++) {
			obj.get(i).draw(g);
		}
	}
	
	public String getName(boolean start) {
		if(start) {
			if(levelName=="") {
				return "Bouncy Ball";
			}
			else {
				return "Bouncy Ball: "+levelName;
			}
		}
		else {
			return levelName;
		}
	}
	
	public String getName() {
		return getName(false);
	}
	
	public void updateObjects() {
		for(int i = 0; i < obj.size(); i++) {
			obj.get(i).update();
			if(going) {
				if(!obj.get(i).noCollision().contains("me")) {
					for(int i2 = 0; i2 < obj.size(); i2++) {
						if(!obj.get(i2).noCollision().contains("you")&&!(i2==i)) {
							if(obj.get(i).x<obj.get(i2).x+obj.get(i2).width()&&obj.get(i).x+obj.get(i).width()>obj.get(i2).x&&obj.get(i).y<obj.get(i2).y+obj.get(i2).height()&&obj.get(i).y+obj.get(i).height()>obj.get(i2).y) {
								obj.get(i).onCollide(obj.get(i2));
							}
						}
					}
				}
			}
		}
		if(win>-1) {
			win++;
			if(win>=10) {
				BouncyBall.nextLevel();
			}
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