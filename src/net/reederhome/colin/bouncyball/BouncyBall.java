package net.reederhome.colin.bouncyball;

import java.io.*;
import java.util.HashMap;

import javax.swing.*;
@SuppressWarnings("rawtypes")
public class BouncyBall {

	public static JFrame frame;
	public static BBWorld world;
	public static String[] lastArgs;
	private static HashMap<String,Class> classMapping;
	public static HashMap<String,Class> getHashMap() {
		if(classMapping==null) {
			classMapping = new HashMap<String,Class>();
			classMapping.put("ball", BBBall.class);
		}
		return classMapping;
	}
	public static BBObject loadLine(String l) {
		String[] args = l.split(" ");
		HashMap<String,Class> map = getHashMap();
		if(map.containsKey(args[0])) {
			lastArgs=args;
			try {
				return (BBObject) map.get(args[0]).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	public static BBWorld loadLevel(InputStream is) {
		BBWorld tr = new BBWorld();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			String line = null;
			do {
				if(line!=null) tr.addObject(loadLine(line));
				line = br.readLine();
			} while(line != null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tr;
	}
	public static void main(String[] args) {
		try {
			frame = new JFrame("Bouncy Ball");
			world = loadLevel(new FileInputStream(args[0]));
			frame.addKeyListener(world);
			frame.add(world);
			frame.setSize(600, 400);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Thread t = new Thread(new RepaintMachine());
			t.start();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}