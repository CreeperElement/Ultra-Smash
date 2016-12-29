package com.fenske.ultraSmash.System;

import java.util.ArrayList;

import com.fenske.ultraSmash.Processors.MicroProcessor;

public class GameThread extends TimedThread{
	
	public GameThread(int updatesPerSecond, String name) {
		super(updatesPerSecond, name);
	}
	
	@Override
	public void update(){
		for(int i =0; i < processors.size(); i++)
			processors.get(i).update();
	}
	
	@Override
	public void endThread(){
		for(int i =0; i < processors.size(); i++)
			processors.get(i).endProcessor();
	}
	
	public MicroProcessor getMicroProcessorByName(String name){
		MicroProcessor mp = null;
		for(int i = 0; i < processors.size(); i++){
			if(processors.get(i).getName().equals(name)) mp = processors.get(i);
		}
		return mp;
	}
}
