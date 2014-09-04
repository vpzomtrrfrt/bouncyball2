package net.reederhome.colin.bouncyball;

public class RepaintMachine implements Runnable {

	public void run() {
		while(true) {
			BouncyBall.world.updateObjects();
			BouncyBall.frame.repaint();
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}