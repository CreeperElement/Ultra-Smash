package com.fenske.ultraSmash.characters;

import java.awt.Color;
import java.awt.Graphics;

public class SquareMan {
	
	int x, y;
	
	public SquareMan(){
		x = 0;
		y = 0;
	}
	
	public void move(int x, int y){
		this.x += x;
		this.y += y;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.red);
		g.drawRect(x, y, 10, 10);
	}
	
}
