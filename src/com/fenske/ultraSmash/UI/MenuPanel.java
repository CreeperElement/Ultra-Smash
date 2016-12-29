package com.fenske.ultraSmash.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * The main menu
 * 
 * @author CreeperElement
 *
 */
public class MenuPanel extends JPanel{
	
	private JButton quit, play, edit;
	private String 
			quitText = "Quit",
			playText = "Play",
			editText = "Edit";
	
	/** Default constructor for the menu. Takes no parameters*/
	public MenuPanel(){
		super();
		quit = new JButton(quitText);
			quit.addActionListener(new QuitListener());
		play = new JButton(playText);
			play.addActionListener(new PlayListener());
		edit = new JButton(editText);
			edit.addActionListener(new EditListener());
			
		add(quit);
		add(play);
		add(edit);
	}
	
	/** Private action listener for quit button. Will call <code>GameFrame.sutDownGame()</code>*/
	private class QuitListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			GameFrame.shutDownGame();
		}
		
	}
	
	/** Private action listener for play button. Will call <code>GameFrame()</code> and tell it to run the game*/
	private class PlayListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			GameFrame.runGame();
		}
		
	}
	
	/** Private action listener for edit button. Will launch edit mode.*/
	private class EditListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		}
		
	}
}
