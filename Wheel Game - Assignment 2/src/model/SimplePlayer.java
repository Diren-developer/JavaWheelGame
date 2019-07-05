package model;

import model.enumeration.BetType;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	
	private String playerName;
	private int points;
	private String id;
	private int bet;
	private BetType betType;
	
	public SimplePlayer() {
		
	}
	
	/*public SimplePlayer(String playerName, int points, String id, int bet, BetType betType) {
		this.playerName = playerName;
		this.points = points;
		this.id = id;
		this.bet = bet;
		this.betType = betType;
		
	}*/
	
	public SimplePlayer(String id, String playerName, int points) {
		this.playerName = playerName;
		this.points = points;
		this.id = id;
		
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
		
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
		
	}

	@Override
	public String getPlayerId() {
		return id;
	}

	@Override
	public boolean setBet(int bet) {
		if(bet > this.points) {
			return false;
		}
		if(bet <= 0) {//if the bet is 0, reset the bet amount to 0 and return false
			resetBet();
		}
		this.bet = bet;
		return true;
	}

	@Override
	public int getBet() {
		return bet;
	}

	@Override
	public void setBetType(BetType betType) {
		this.betType = betType;
		
	}

	@Override
	public BetType getBetType() {
		return betType;
	}

	@Override
	public void resetBet() {
		bet = 0;
	}
	
	@Override
	public String toString() {
		return "Player: id="+ id+ " name="+ playerName+ " bet="+ bet+ " betType="+ betType+ " points="+ points;
	}

}
