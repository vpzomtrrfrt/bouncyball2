package net.reederhome.colin.bouncyball;

import java.io.*;
import java.util.HashMap;

import javax.swing.*;
@SuppressWarnings("rawtypes")
public class BouncyBall {

	public static String lastLevel;
	public static JFrame frame;
	public static BBWorld world = null;
	public static String[] lastArgs;
	private static HashMap<String,Class> classMapping;
	public static HashMap<String,Class> getClassMap() {
		if(classMapping==null) {
			classMapping = new HashMap<String,Class>();
			classMapping.put("ball", BBBall.class);
			classMapping.put("box", BBBox.class);
			classMapping.put("woodblock", BBBox.class);
			classMapping.put("end", BBFinish.class);
			classMapping.put("done", BBFinish.class);
		}
		return classMapping;
	}
	public static BBObject loadLine(String l) {
		String[] args = l.split(" ");
		HashMap<String,Class> map = getClassMap();
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
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		BBWorld tr = null;
		lastLevel="";
		try {
			tr = new BBWorld(br.readLine(), Integer.parseInt(br.readLine()));
			String n = "\n";
			lastLevel+=tr.getName();
			lastLevel+=n+tr.time;
			String line = null;
			do {
				if(line!=null) {
					lastLevel+=n+line;
					tr.addObject(loadLine(line));
				}
				line = br.readLine();
			} while(line != null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(lastLevel);
		return tr;
	}
	
	public static void setupLevel(BBWorld w) {
		frame.getContentPane().removeAll();
		world = w;
		frame.setTitle(world.getName(true));
		frame.addKeyListener(world);
		frame.getContentPane().add(world);
		frame.revalidate();
	}
	
	public static void restartLevel() {
		setupLevel(loadLevel(new ByteArrayInputStream(lastLevel.getBytes())));
	}
	
	public static void main(String[] args) {
		try {
			frame = new JFrame("Bouncy Ball");
			setupLevel(loadLevel(new FileInputStream(args[0])));
			frame.setSize(600, 400);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Thread t = new Thread(new RepaintMachine());
			t.start();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void nextLevel() {
		System.exit(0);
	}
}