package net.reederhome.colin.bouncyball;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JComponent;
public class BBWorld extends JComponent implements KeyListener, MouseMotionListener {

	private static final long serialVersionUID = 5328167859301614764L;
	
	boolean going = false;
	int win = -1;
	private String levelName = "";
	int time = -1;
	ArrayList<BBObject> obj = new ArrayList<BBObject>();
	Stack<BBObject> doomed = new Stack<BBObject>();

	int lastballnum = -1;

	public double gravity = 0;
	
	public BBWorld(String name, int time) {
		levelName=name;
		this.time=time;
	}
	
	public boolean isStarted() {
		return going || win>-1 || (time<=-1 && time!=-42);
	}
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		for(int i = 0; i < obj.size(); i++) {
			obj.get(i).draw(g);
		}
		if(time>=0) {
			g.setColor(Color.red);
			g.drawString("Time: "+time, 100, 10);
		}
		else if(time!=-42) {
			g.setColor(new Color(255, 255, 255, 127));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.red);
			String txt = "TIME'S UP!";
			g.setFont(g.getFont().deriveFont(96f));
			g.drawString(txt, getWidth()/2-g.getFontMetrics().stringWidth(txt)/2, getHeight()/2);
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
				for(int i2 = 0; i2 < obj.size(); i2++) {
					if(!obj.get(i).noCollision(obj.get(i2)).contains("me")) {
						if(!obj.get(i2).noCollision(obj.get(i)).contains("you")&&!(i2==i)) {
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
			if(win>=20) {
				BouncyBall.nextLevel();
			}
		}
		if((going||time<=0)&&time!=-42) {
			time--;
			if(time<=0) {
				going=false;
				if(time<=-40) {
					BouncyBall.restartLevel();
				}
			}
		}
		while(doomed.size()>0) {
			obj.remove(doomed.pop());
		}
	}

	public void addObject(BBObject no) {
		if(no!=null) {
			no.worldObj=this;
			obj.add(no);
			no.addToWorld();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(BouncyBall.world!=this) return;
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
			going = true;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_R) {
			BouncyBall.restartLevel();
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

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if(BouncyBall.world!=this) return;
		for(int i = 0; i < obj.size(); i++) {
			if(obj.get(i) instanceof MouseMotionListener) {
				((MouseMotionListener) obj.get(i)).mouseDragged(arg0);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		if(BouncyBall.world!=this) return;
		for(int i = 0; i < obj.size(); i++) {
			if(obj.get(i) instanceof MouseMotionListener) {
				((MouseMotionListener) obj.get(i)).mouseMoved(arg0);
			}
		}
	}
}