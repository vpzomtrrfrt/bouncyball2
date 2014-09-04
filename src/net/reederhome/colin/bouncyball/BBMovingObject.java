package net.reederhome.colin.bouncyball;

public abstract class BBMovingObject extends BBObject {

	int x,y,xv,yv;
	public void update(){move();}
	public void move() {x+=xv;y+=yv;};
}