package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.SimplePlayer;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.BetPage;
import view.GameEngineCallbackGUI;

public class SetBetListener implements ActionListener{
	private GameEngine ge;
	private JTextField bet;
	private BetPage page;
	private GameEngineCallbackGUI gui;
	
	public SetBetListener(GameEngine ge, JTextField bet, BetPage page, GameEngineCallbackGUI gui) {
		super();
		this.ge = ge;
		this.bet = bet;
		this.page = page;
		this.gui = gui;
	}
	
	private String processUserInput(Vector<Object>playerData, String betType, int bet) {
		BetType type;
		Player player;
		//first, check the inputted bet type with our enumerated bet types
		if(betType.equals("BLACK")) {
			type = BetType.BLACK;
		}
		else if(betType.equals("RED")) {
			type = BetType.RED;
		}
		else if(betType.equals("ZEROS")) {
			type = BetType.ZEROS;
		}
		else {
			return "error bet type";
		}
		if(bet<0) {
			return "invalid bet amount";
		}
		
		//retrieve the player ID from the playerData vector
		String id = (String) playerData.get(0);
		player = ge.getPlayer(id);
		//set the bet amount of the player
		boolean status = ge.placeBet(player, bet, type);
		if(status==false) {
			return "invalid bet amount";
		}
		
		return"OK";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Save")) {
			//fetch the selected player data
			Vector<Object> playerData = gui.getPlayerData();
			
			//fetch the bet type
			String betType = gui.getBetType();

			//check whether the inputted bet is actually numeric, or not an empty field
			try {//check if the player is already selected or not
				if(playerData == null) {
					JOptionPane.showMessageDialog(null,
	                        "Player not selected. Please select a player from the table", "Error",
	                        JOptionPane.ERROR_MESSAGE);
					return;
				}
				//fetch the bet amount inputted
				int betAmount = Integer.parseInt(bet.getText());
				//process the user inputs
				String status = processUserInput(playerData,betType,betAmount);
				if(status.equals("error bet type")) {
					JOptionPane.showMessageDialog(null,
	                        "Invalid Bet Type", "Error",
	                        JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(status.equals("invalid bet amount")) {
					JOptionPane.showMessageDialog(null,
	                        "Invalid Bet Amount. Please insert a valid amount", "Error",
	                        JOptionPane.ERROR_MESSAGE);
					return;
				}
				//refresh the main table display with new data
				gui.refreshMainTable(ge);
				page.dispose();
				//call a function to check the bet status of all players
				String[]checkBet = gui.checkPlayerBet(ge);
				if(checkBet[0].equals("OK")) {
					//if all players have set their bet, automatically spin the wheel
					//create a new background thread to call the spin function
					new Thread() {
				    	  @Override
				    	  public void run() {
				    		  ge.spin(1, 500, 25);
				    		  
				    	  }
				      }.start();
				}
				return;
			}
			catch(NumberFormatException | NullPointerException ex) {
				
				JOptionPane.showMessageDialog(null,
                        "Invalid Bet Amount or Bet Type. Please insert a valid numeric amount", "Error",
                        JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
		}
		else if(e.getActionCommand().equals("Close")) {
			page.dispose();
		}
		
	}

}
