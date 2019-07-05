package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import model.interfaces.GameEngine;

import view.AddPlayersPage;
import view.GameEngineCallbackGUI;
import view.MainPage;
import view.RemovePlayerPage;

public class MenuBarListener implements ActionListener{
	private GameEngine ge;
	private GameEngineCallbackGUI gui;
	
	public MenuBarListener(GameEngine ge,GameEngineCallbackGUI gui) {
		super();
        this.ge = ge;
        this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//if the Add Player button is pressed, open the Add Players panel
		if(e.getActionCommand().equals("Add Player")) {
			gui.initAddPlayersPage(ge);
			
		}
		else if(e.getActionCommand().equals("Remove Player")) {
			gui.initRemovePlayerPage(ge);
		}
	}

}
