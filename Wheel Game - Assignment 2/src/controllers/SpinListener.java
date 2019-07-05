package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;

public class SpinListener implements ActionListener{
	private GameEngine ge;
	private GameEngineCallbackGUI gui;
	private JButton spinButton;
	
	public SpinListener(GameEngine ge, GameEngineCallbackGUI gui, JButton spinButton) {
		super();
		this.ge = ge;
		this.gui = gui;
		this.spinButton = spinButton;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("spin")) {
			spinButton.setEnabled(false);
			//check whether all players are added
			String[] status = new String[2];
			status = gui.checkPlayerBet(ge);
			if(status[0].equals("no players found")) {
				JOptionPane.showMessageDialog(null,
                        "No players have been added", "Error",
                        JOptionPane.ERROR_MESSAGE);
				spinButton.setEnabled(true);
				return;
			}

			//create a new background thread to call the spin function
			new Thread() {
		    	  @Override
		    	  public void run() {
		    		  ge.spin(1, 500, 25);
		    		  spinButton.setEnabled(true);
		    	  }
		      }.start();
		      
		}
		
	}

}
