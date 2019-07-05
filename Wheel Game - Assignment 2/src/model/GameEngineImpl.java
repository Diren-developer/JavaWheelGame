package model;

import java.util.ArrayList;
import java.util.Collection;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;
import java.util.Random;

public class GameEngineImpl implements GameEngine
{
	/** 
	 * collections of players, game engine callbacks, and the game wheel
	  **/
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<GameEngineCallback> ge = new ArrayList<GameEngineCallback>();
	private ArrayList<Slot>gameWheel = new ArrayList<Slot>();
	private GameEngineCallback cbLog;
	private GameEngineCallbackGUI cbGUI;
	
	/**
	* the size of the gaming wheel configured according to the assignment specification
	*/
	public static final int WHEEL_SIZE = 38; 
	
	@Override
	public void spin(int initialDelay, int finalDelay, int delayIncrement) {
		cbLog = ge.get(0);
		cbGUI = (GameEngineCallbackGUI) ge.get(1);
		Random randomSlot = new Random();
		Slot slot = null;
		int randomPos = randomSlot.nextInt(WHEEL_SIZE);//pick a random position from the wheel
		cbGUI.setBallFirstPosition(randomPos); // return initial position to wheel paint
		
		/**spin the wheel with incrementing delay
		 * the wheel is spun until the ball stops, i.e. when the initial delay is equal
		 * to or larger than the final delay **/
		while(true){
			//reset the index when the wheel reach the final index number
			//set to -1 so that the next index will be 0
			if(randomPos == WHEEL_SIZE-1) {
				randomPos = -1;
			}
			slot = gameWheel.get(++randomPos);//get the next slot in the wheel
			try {
				
				initialDelay = initialDelay + delayIncrement;
				Thread.sleep(initialDelay);
				cbLog.nextSlot(slot, this);
				cbGUI.nextSlot(slot, this);
				if(initialDelay >= finalDelay) {
					break;
				}

			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		cbGUI.result(slot,this);

	}

	@Override
	public void calculateResult(Slot winningSlot) {
		BetType type;
		//get each player's bet type, then evaluate the win-loss scenarios and 
		//calculate their final points standings
		//if the type is null (because a player skips the round)
		//continues calculate the result for next player
		for(Player player : players) {
			type = player.getBetType();
			if(type==null) {
				continue;
			}
			type.applyWinLoss(player, winningSlot);
		}
		
	}

	@Override
	public void addPlayer(Player player) {
		players.add(player);
		
	}

	@Override
	public Player getPlayer(String id) {
		for (int i = 0; i<players.size(); i++) {
			if(players.get(i).getPlayerId().equals(id)) {
				return players.get(i);
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		for (int i = 0; i<players.size(); i++) {
			if (players.get(i).equals(player)) {
				players.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		ge.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		ge.remove(gameEngineCallback);
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return players;
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		if(player.setBet(bet)) {
			player.setBetType(betType);
			return true;
		}
		return false;
	}

	@Override
	public Collection<Slot> getWheelSlots() {
		createWheel();
		
		return gameWheel;
	}
	
	private void createWheel() {
		//store the wheel numbers in an array
		int [] wheelNumbers = {0,27,10,25,29,12,8,19,31,18,6,21,33,16,4,23,35
				,14,2,0,28,9,26,30,11,7,20,32,17,5,22,34,15,3,24,36,13,1};
		
		SlotImpl slot;
		/**
		 * Create the wheel slots
		 * **/
		for(int i=0; i<WHEEL_SIZE;i++) {
			//if the slot position is even, the slot color is black
			if(i%2 == 0) {
				//if the slot position is 0, its number is 0 and the color is GREEN00
				if(i==0) {
					slot = new SlotImpl(0,Color.GREEN00,0);
					gameWheel.add(slot);
				}
				else {
					slot = new SlotImpl(i,Color.BLACK,wheelNumbers[i]);
					gameWheel.add(slot);
				}
				
			}
			//if the slot position is even, the slot color is red
			if(i%2 != 0) {
				if(i==19) {
					//if the slot position is 19 (opposite of position 0),
					//its number is 0 and the color is GREEN0
					slot = new SlotImpl(19,Color.GREEN0,0);
					gameWheel.add(slot);
				}
				else {
					slot = new SlotImpl(i,Color.RED,wheelNumbers[i]);
					gameWheel.add(slot);
				}
				
			}		
		}	
	}
}