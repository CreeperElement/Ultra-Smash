package com.fenske.ultraSmash.Processors;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.fenske.ultraSmash.System.GameThread;
import com.fenske.ultraSmash.System.RenderThread;

public class Input implements MicroProcessor, KeyListener{
	
	private String name;
	private int order;
	public static String processorName;
	public boolean down, right;
	
	@Override
	public void instantiate(String name, int order, GameThread gameThread, RenderThread renderThread) {
		this.name = name;
		this.order = order;
		processorName = name;
		
	}

	@Override
	public void update() {
		clearKeys();
		for(int i = 0; i < keyEvents.size(); i++)
		switch((int)keyEvents.get(i).getKeyCode()){
		case KeyEvent.VK_DOWN: down = true; break;
		case KeyEvent.VK_RIGHT: right = true; break;
		}
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean replaces(String name, int order) {
		return this.name == name && this.order == order;
	}
	
	public void endProcessor(){
		
	}
	
	public void clearKeys(){
		down = false;
		right = false;
	}
	
	public boolean down(){
		return down;
	}
	public boolean right(){
		return right;
	}

	public static ArrayList<KeyEvent> keyEvents = new ArrayList<KeyEvent>();
	
	@Override
	public void keyPressed(KeyEvent e) {
		for(int i = 0; i < keyEvents.size(); i++){
			if(keyEvents.get(i).getKeyCode() == e.getKeyCode()) return;
		}
		keyEvents.add(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for(int i = 0; i < keyEvents.size(); i++)
			if(keyEvents.get(i).getKeyCode() == e.getKeyCode()) keyEvents.remove(i);
		System.out.println(keyEvents.size());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("KEYS3");
	}
}
