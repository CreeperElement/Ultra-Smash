package com.fenske.ultraSmash.UI;

import java.awt.event.WindowEvent;

/**
 * WindowListener was created to handle all window events triggerable by GameFrame.
 * 
 * @author CreeperElement
 *
 */

public class WindowListener implements java.awt.event.WindowListener {

	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	/** This is called when the X icon is clicked in the upper righthand corner.
	*	This class calls the appropriate methods to properly shut down the game.	
	*
	*	@param arg0 Window event
	*/
	@Override
	public void windowClosing(WindowEvent arg0) {
		GameFrame.shutDownGame();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
