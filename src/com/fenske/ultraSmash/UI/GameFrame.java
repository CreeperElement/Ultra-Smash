package com.fenske.ultraSmash.UI;

import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.fenske.ultraSmash.Processors.Input;
import com.fenske.ultraSmash.Processors.MicroProcessor;
import com.fenske.ultraSmash.System.GameThread;
import com.fenske.ultraSmash.System.RenderThread;
import com.fenske.ultraSmash.System.TimedThread;

/**
 * <h1>Ultra Smash</h1>
 * Utra Smash is inspired by Nintendo's Super Smash Bros. This game was a hobby project of Seth Fenske
 * as achallenge to develop his own 2D game engine.
 * 
 * @author CreeperElement
 * @version 0.0.1
 */
public class GameFrame extends JFrame{
	
	private static String gameName = "Ultra Smash";
	private static boolean running; //Should the main loop keep executing
	private static MenuPanel menuPanel;
	private static int SECOND_IN_MILLISECONDS = 1000;
	private static JFrame frame;
	private static JPanel oldFrame, renderPanel;
	//Threads
	private static GameThread gameThread;
	private static RenderThread renderThread;
	
	public GameFrame(){
		super(getGameName());
		menuPanel = new MenuPanel();//Main Menu For The Game
		oldFrame = menuPanel;
		running = true;
		add(menuPanel);
		renderPanel = new JPanel();
		
		
		//Visual elements of the frame
		setSize(800, 600);
		setVisible(true);
		this.addWindowListener(new WindowListener());
	}
	
	/** Main method, calls constructor
	 * @param args no planned use for this default entry variable
	 * @throws InterruptedException */
	public static void main(String[] args) throws InterruptedException{
		frame = new GameFrame();
	}
	
	public static void runGame(){
		gameThread = new GameThread(60, "GameThread");
		gameThread.start();
		
		renderThread = new RenderThread(60, "RenderThread");
		renderThread.setRenderablePanel(renderPanel);
		
		//Load Micro-Processors
				try{
					int lineCount = 0;
					FileReader fr = new FileReader("D:\\Dropbox\\Ultra Smash\\mods\\mods_list.txt");
					BufferedReader br = new BufferedReader(fr);
					String modInfo = br.readLine();
					while(modInfo != null){
						String[] info = modInfo.split(" ");
						String modName = info[0];
						String modLocation = info[1];
						int processOrder = Integer.parseInt(info[2]);

						ClassLoader cl = ClassLoader.getSystemClassLoader();
						Class<MicroProcessor> c = (Class<MicroProcessor>) cl.loadClass(modLocation);
						MicroProcessor mp = c.newInstance();
						mp.instantiate(modName, processOrder, gameThread, renderThread);
						TimedThread.add(mp);
						modInfo = br.readLine();
					}
				}catch(FileNotFoundException e){
					e.printStackTrace();
				}catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		frame.addKeyListener((KeyListener)gameThread.getMicroProcessorByName(Input.processorName));
		setPanel(renderPanel);
		frame.setVisible(true);
		frame.requestFocus();
		renderThread.start();
	}
	
	/**
	 * Removes the last Jpanel and replaces it with a new one. Removes <code>oldFrame</code>
	 * 
	 * @param newFrame The Panel to be added to the main window
	 */
	public static void setPanel(JPanel newFrame){
		frame.remove(oldFrame);
		frame.add(newFrame);
		oldFrame = newFrame;
	}
	
	/** Sets the running variable for the main loop of the game to either <code>true</code> or 
	 * <code>false</code>. If running is false the game will quit on the next itterance of the loop.
	 * @param isRunning value to set to running variable*/
	public static void gameIsRunning(boolean isRunning){
		running = isRunning;
	}
	
	/** 
	 * Properly shuts down the game. Responsible for any last minute saving and storage of data.
	 */
	public static void shutDownGame(){
		if(gameThread!=null) gameThread.endThread();
		if(renderThread!=null) renderThread.endThread();
		System.exit(0);
	}
	
	/** A getter for the name of the game
	 * @return gameName String variable which reads Ultra Smash*/
	public static String getGameName(){
		return gameName;
	}
}