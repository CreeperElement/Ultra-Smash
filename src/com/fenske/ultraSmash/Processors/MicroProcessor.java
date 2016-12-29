package com.fenske.ultraSmash.Processors;

import java.awt.Graphics;

import com.fenske.ultraSmash.System.GameThread;
import com.fenske.ultraSmash.System.RenderThread;

public interface MicroProcessor {
	
	/**
	 * 
	 * <h1>Instantiate</h1>
	 * This acts as a constructor, because these classes are never instantiated because they are <i>dynamically</i> 
	 * loaded in <code>GameFrame</code>.
	 * 
	 * @param name Handler name
	 * @param order Thread order execution
	 */
	public void instantiate(String name, int order, GameThread gameThread, RenderThread renderThread);
	
	/**
	 * Called every time GameThread updates
	 */
	public void update();
	
	/**
	 * Called every time RenderTherad updates
	 */
	public void render(Graphics g);
	
	/**
	 * Returns the update order of this MP
	 * 
	 * @return an integer which represents the priority in which it is updated
	 */
	public int getOrder();
	
	/**
	 * Returns the unique name of this MP
	 * 
	 * @return The name of the MP
	 */
	public String getName();
	
	/**
	 * Used to determine if a mod is to replace this mod. The best way to replace code is by changing the path name of the desired MP to 
	 * the package path name of the replacement MP.
	 * 
	 * @return <code>true</code> if the name and order match
	 */
	public boolean replaces(String name, int order);
	
	/**
	 * Call to properly shut down the thread.
	 */
	public void endProcessor();
}
