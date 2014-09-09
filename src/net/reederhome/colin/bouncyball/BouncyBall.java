package net.reederhome.colin.bouncyball;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;
@SuppressWarnings("rawtypes")
public class BouncyBall {

	public static String lastLevel;
	public static JFrame frame;
	public static BBWorld world = null;
	public static String[] lastArgs;
	public static URL[] levelFiles = null;
	public static int currentLevel = -1;
	private static HashMap<String,Class> classMapping;
	public static HashMap<String,Class> getClassMap() {
		if(classMapping==null) {
			classMapping = new HashMap<String,Class>();
			classMapping.put("ball", BBBall.class);
			classMapping.put("box", BBBox.class);
			classMapping.put("woodblock", BBBox.class);
			classMapping.put("end", BBFinish.class);
			classMapping.put("done", BBFinish.class);
			classMapping.put("mouse", BBMouseBox.class);
			classMapping.put("<--", BBPowerMover.class);
			classMapping.put("<box>", BBMovingBox.class);
			classMapping.put("bzzt", BBElectricFence.class);
			classMapping.put("key", BBKey.class);
		}
		return classMapping;
	}
	public static BBObject loadLine(String l) {
		ArrayList<String> ala = new ArrayList<String>();
		String cura = "";
		boolean quote = false;
		for(int i = 0; i < l.length(); i++) {
			char c = l.charAt(i);
			if(c=='"') {
				quote=!quote;
			}
			else if(c==' '&&!quote) {
				if(cura.length()>0) {
					ala.add(cura);
					cura="";
				}
			}
			else {
				cura+=c;
			}
		}
		if(cura.length()>0) {
			ala.add(cura);
		}
		String[] args = new String[]{};
		args=ala.toArray(args);
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
		return tr;
	}
	
	public static void setupLevel(BBWorld w) {
		frame.getContentPane().removeAll();
		world = w;
		frame.setTitle(world.getName(true));
		frame.addKeyListener(world);
		frame.addMouseMotionListener(world);
		frame.getContentPane().add(world);
		frame.revalidate();
	}
	
	public static void restartLevel() {
		setupLevel(loadLevel(new ByteArrayInputStream(lastLevel.getBytes())));
	}
	
	public static void main(String[] args) {
		System.setProperty("http.agent", "net.reederhome.colin.bouncyball");
		try {
			frame = new JFrame("Bouncy Ball");
			if(args[0].indexOf(".bbl")>-1) {
				URL url;
				if(args[0].indexOf("://")>-1) {
					url = new URL(args[0]);
				}
				else {
					url = new File(args[0]).toURI().toURL();
				}
				setupLevel(loadLevel(url.openStream()));
			}
			else {
				URL infourl;
				if(args[0].indexOf("://")==-1) {
					File infofile = new File(args[0]);
					if(infofile.isDirectory()) {
						infofile = new File(infofile.getAbsoluteFile()+"/info");
					}
					infourl = infofile.toURI().toURL();
				}
				else {
					infourl = new URL(args[0]);
				}
				ArrayList<URL> fal = new ArrayList<URL>();
				BufferedReader br = new BufferedReader(new InputStreamReader(infourl.openStream()));
				String line;
				String rootpath = infourl.toString();
				int i = rootpath.lastIndexOf("/");
				System.out.println(rootpath);
				if(i>-1) {
					rootpath=rootpath.substring(0, i);
				}
				else {
					rootpath=".";
				}
				while((line=br.readLine()) != null) {
					fal.add(new URL(rootpath+"/"+line+(line.indexOf(".bbl")>-1?"":".bbl")));
				}
				levelFiles = new URL[fal.size()];
				levelFiles = fal.toArray(levelFiles);
				br.close();
				nextLevel();
			}
			frame.setSize(600, 400);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Thread t = new Thread(new RepaintMachine());
			t.start();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void nextLevel() {
		currentLevel++;
		if(levelFiles!=null&&currentLevel<levelFiles.length) {
			try {
				setupLevel(loadLevel(levelFiles[currentLevel].openStream()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				nextLevel();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(levelFiles!=null&&currentLevel==levelFiles.length) {
			BBWorld winLevel = new BBWorld("", -42);
			for(int i=0;i<24;i++) {
				lastArgs=new String[]{"<box>", Integer.toString(new Random().nextInt(580)), Integer.toString(new Random().nextInt(380)), "20", "20", Integer.toString(new Random().nextInt(5)), Integer.toString(new Random().nextInt(5))};
				BBMovingObject box = new BBMovingBox();
				winLevel.addObject(box);
			}
			lastArgs=new String[]{"box", "200", "160", "200", "80", " You Won!", "32"};
			BBObject winBox = new BBBox();
			winLevel.addObject(winBox);
			setupLevel(winLevel);
			winLevel.going=true;
		}
		else {
			System.exit(0);
		}
	}
}