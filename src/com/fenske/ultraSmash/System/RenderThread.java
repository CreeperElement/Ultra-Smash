package com.fenske.ultraSmash.System;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RenderThread extends TimedThread{
	
	//Send draw commands to this panel
	JPanel panel;
	
	public RenderThread(int updatesPerSecond, String name) {
		super(updatesPerSecond, name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(){
		Graphics g = panel.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
		for(int i =0; i < processors.size(); i++)
			processors.get(i).render(g);
	}
	
	@Override
	public void endThread(){
		for(int i =0; i < processors.size(); i++)
			processors.get(i).endProcessor();
	}
	
	/**
	 * Sets the panel upon which we will draw.
	 * 
	 * @param renderPanel
	 */
	public void setRenderablePanel(JPanel renderPanel){
		panel = renderPanel;
	}
}
