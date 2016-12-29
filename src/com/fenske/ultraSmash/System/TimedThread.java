package com.fenske.ultraSmash.System;

import java.util.ArrayList;

import com.fenske.ultraSmash.Processors.MicroProcessor;

/** 
 * 
 * <h1>Timed Thread</h1>
 * 
 * This class creats a thread that will execute an update method at a chosen timed interval.
 * Accuracy ranges from one update per (1000 - 10010) milliseconds
 * 
 * @author CreeperElement
 * 
 * */

public class TimedThread extends Thread{
	
	public TimedThread(int updatesPerSecond, String name){
		updates = updatesPerSecond;
		ticks = 0;
		msPerUpdate = SECOND_IN_MILLISECONDS / updates;
		threadName = name;
		processors = new ArrayList<MicroProcessor>();
	}
	
	long SECOND_IN_MILLISECONDS = 1000;
	int updates;
	int ticks;
	double msPerUpdate;
	long elapsedCycleTime;
	long lastTime;
	boolean running = true;
	String threadName;
	
	public static ArrayList<MicroProcessor> processors;
	
	/**
	 * <p>This method is called when another class call the <code>run</code> method from the superclass
	 * <code>Thread</code>.</p>
	 * <p>This method handles all the timing problems to execute the update method <b>X</b> times per second.</p>
	 */
	@Override
	public void run() {
		elapsedCycleTime = 0;
		lastTime = System.currentTimeMillis();
		
		while(running){
			long thisTime = System.currentTimeMillis();
			long elapsedUpdateTime = thisTime - lastTime;
			lastTime = thisTime;
			elapsedCycleTime += elapsedUpdateTime;
				update();
			ticks++;
			double currentTimePerUpdate = elapsedCycleTime /ticks;
			if(currentTimePerUpdate < msPerUpdate)
				try {
					Thread.sleep((long) (msPerUpdate - currentTimePerUpdate));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(elapsedCycleTime >= SECOND_IN_MILLISECONDS)
			{
				elapsedCycleTime = 0;
				ticks = 0;
			}
		}
	}
	
	/**
	 * Adds the <code>MicroProcessor</code> to the list of items to update. If the MP matches another, it deletes the former and replaces
	 * it with the new one
	 * @param mp
	 */
	public static void add(MicroProcessor mp){
		String name = mp.getName();
		int order = mp.getOrder();
		
		for(int i = 0; i < processors.size(); i++){
			if(processors.get(i).replaces(mp.getName(), mp.getOrder())){//If there's a MP to replace
				processors.remove(i);
				processors.add(i, mp);
				return;//Skip the normal adding procedure
			}
		}
		processors.add(mp);//Only executed if the MP doesn't exist already
	}
	
	/**
	 * Called <b>X</b> times per second
	 */
	public void update(){System.out.println(threadName);}
	
	
	/**
	 * Called when the main loop is broken. Use it to finish up whatever the thread needs to do to properly
	 * shut down.
	 */
	public void endThread(){
		System.out.println("All done");
	}
	
	/**
	 * Turns the <code>running</code> boolean to false. This breaks the update loop and calls the 
	 * endThread method.
	 */
	public void kill(){
		running = false;
	}
	
	/**
	 * Reurns the <code>running</code> boolean.
	 * @return running The loop continue variable
	 */
	public boolean getRunningCondition(){
		return running;
	}
}