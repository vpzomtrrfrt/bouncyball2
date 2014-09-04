package net.reederhome.colin.bouncyball;

public abstract class BBMovingObject extends BBObject {

	int xv,yv;
	public void update(){if(worldObj.going)move();}
	public void move() {
		x+=xv;y+=yv;
		if(x+width()>=worldObj.getWidth()) {
			x=worldObj.getWidth()-width();
			xv=-Math.abs(xv);
		}
		if(x<=0) {
			x=0;
			xv=Math.abs(xv);
		}
		if(y+height()>=worldObj.getHeight()) {
			y=worldObj.getHeight()-height();
			yv=-Math.abs(yv);
		}
		if(y<=0) {
			y=0;
			yv=Math.abs(yv);
		}
	};
	public void onCollision(BBObject t) {
		if(x<t.x) {
			xv=-Math.abs(xv);
		}
		if(x+width()>t.x+t.width()) {
			xv=Math.abs(xv);
		}
		if(y<t.y) {
			yv=-Math.abs(yv);
		}
		if(y+height()>t.y+t.height()) {
			yv=Math.abs(yv);
		}
	}
}