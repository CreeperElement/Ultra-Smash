package com.fenske.ultraSmash.characters;

import java.awt.Graphics;

import com.fenske.ultraSmash.Processors.Input;
import com.fenske.ultraSmash.Processors.MicroProcessor;
import com.fenske.ultraSmash.System.GameThread;
import com.fenske.ultraSmash.System.RenderThread;

public class CharacterController implements MicroProcessor{

	String name;
	int order;
	Input input;
	SquareMan square;
	
	@Override
	public void instantiate(String name, int order, GameThread gameThread, RenderThread renderThread) {
		this.name = name;
		this.order = order;
		input = (Input)gameThread.getMicroProcessorByName(Input.processorName);
		square = new SquareMan();
	}

	@Override
	public void update() {
		int x = 0;
		int y = 0;
		
		if(input.down()) y++;
		if(input.right()) x++;
		
		System.out.println(x + " " + y);
		square.move(x, y);
	}

	@Override
	public void render(Graphics g) {
		square.draw(g);
	}

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean replaces(String name, int order) {
		// TODO Auto-generated method stub
		return this.name.equals(name) && this.order == order;
	}

	@Override
	public void endProcessor() {
		
	}

}
