package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;
import view.RemovePlayerPage;

public class RemovePlayerListener implements ActionListener{
	private GameEngine ge;
	private GameEngineCallbackGUI gui;
	private RemovePlayerPage page;
	private String id;
	private Player player;
	private Vector<Object> playerData;
	private String status;
	
	public RemovePlayerListener(GameEngine ge, GameEngineCallbackGUI gui, RemovePlayerPage page) {
		super();
		this.ge= ge;
		this.gui = gui;
		this.page = page;
	}
	
	private String removePlayer(Vector<Object>playerData) {
		//get selected player's ID
		id = (String) playerData.get(0);
		player = ge.getPlayer(id);
		if(player.equals(null)) {
			return "player not found";
		}
		if(ge.removePlayer(player)==false) {
			return "failed removing player";
		}
		return "Player removed";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Remove")) {
			//fetch the selected player data
			playerData = gui.getPlayerData();
			if(playerData==null) {
				JOptionPane.showMessageDialog(null,
	                    "Please select a player to remove", "Error",
	                    JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//show a dialog box confirming player's removal
			int confirm = JOptionPane.showConfirmDialog(page, "Are you sure you want to delete this player?");
			
			if(confirm==JOptionPane.YES_OPTION) {
				
				status = removePlayer(playerData);
				if(status.equals("player not found")) {
					JOptionPane.showMessageDialog(null,
	                        "Selected Player not found", "Error",
	                        JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(status.equals("failed removing player")) {
					JOptionPane.showMessageDialog(null,
	                        "Failed to remove the selected player", "Error",
	                        JOptionPane.ERROR_MESSAGE);
					return;
				}
				//refresh the main table display with new data
				gui.refreshMainTable(ge);
				page.dispose();
				return;
			}	
			
		}
		else if(e.getActionCommand().equals("Cancel")) {
			page.dispose();
			return;
		}
		
	}

}
