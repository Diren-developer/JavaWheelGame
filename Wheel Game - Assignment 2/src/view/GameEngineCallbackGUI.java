package view;

import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;

public class GameEngineCallbackGUI extends GameEngineCallbackImpl{
    
        private static final Logger logger = Logger.getLogger(GameEngineCallbackGUI.class.getName());
        private GameEngineCallbackGUI gui = this;
        private DefaultTableModel players;
        private MainPage menu;
        private Vector<Object>playerData;
        private String betType;
        private ArrayList<Integer>initialPoints;
        private ArrayList<Player>playersList;
        private Player winner;
        private int i;
        
        public void initGUI(GameEngine ge){
            //a utility function to create a table model of a list of players
             this.players = createTableModel(ge);
            //create the GUI in a separate UI thread
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                        try {
                                menu = new MainPage(ge,gui,players);
                                menu.setTableModel(players);
                                menu.setVisible(true);
                        }
                        catch(Exception e) {
                                //in case of any errors, log the error to the console
                                logger.log(Level.WARNING,e.toString());
                                
                        }
                }
            });
        }
        //invokes the bet page
        public void initBetPage(GameEngine ge) {
        	SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                        try {
                        	BetPage bet = new BetPage(ge,gui,players);
                        	bet.setVisible(true);
                        }
                        catch(Exception e) {
                                //in case of any errors, log the error to the console
                                logger.log(Level.WARNING,e.toString());
                                
                        }
                }
            });
        	
        }
        
        //invokes the add players page
        public void initAddPlayersPage(GameEngine ge) {
        	SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                        try {
                        	AddPlayersPage addPlayers = new AddPlayersPage(ge,gui);
                        	addPlayers.setVisible(true);
                        }
                        catch(Exception e) {
                                //in case of any errors, log the error to the console
                                logger.log(Level.WARNING,e.toString());
                                
                        }
                }
            });
        }
        
        //invokes the remove player page
        public void initRemovePlayerPage(GameEngine ge) {
        	SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                        try {
                        	RemovePlayerPage removePlayer = new RemovePlayerPage(ge,gui,players);
                        	removePlayer.setVisible(true);
                        }
                        catch(Exception e) {
                                //in case of any errors, log the error to the console
                                logger.log(Level.WARNING,e.toString());
                                
                        }
                }
            });
        }
        
        //check if all players have set their bets
        public String[] checkPlayerBet(GameEngine ge) {
        	//array of strings to store return values
        	String[] val = new String[2];
        	//create an array list which contains a list of players' point
        	this.initialPoints = new ArrayList<>();
        	//get all players' data
        	ArrayList<Player>players=(ArrayList<Player>) ge.getAllPlayers();
        	if(players==null || players.isEmpty()) {
        		val[0] = "no players found";
        		return val;
        	}
        	//check if any players have not set their bets
        	for(Player player:players) {
        		this.initialPoints.add(player.getPoints());
        		if(player.getBetType()==null || player.getBet()==0) {
        			val[0] = "incomplete bet";
        			val[1] = player.getPlayerName();
        			
        			return val;
        		}
        		
        	}
        	val[0] = "OK";
        	return val;
        }
        
        //sets the player vector object, invoked in TableListener which handles table row selection
        public void setPlayerData(Vector<Object> player) {
        	this.playerData = player;
        }
        
        //getter function for retrieving player vector object
        public Vector<Object> getPlayerData(){
        	return this.playerData;
        }
        
        //set betType variable from the bet type dropdown component
        public void setBetType(String betType) {
        	this.betType = betType;
        }
        
        //retrieve bet type
        public String getBetType() {
        	return this.betType;
        }
        
        //function used to refresh the table with new data in the main page
        public void refreshMainTable(GameEngine ge) {
        	this.players = createTableModel(ge);
        	SwingUtilities.invokeLater(new Runnable() {
        		@Override
        		public void run() {
        			try {
        				//update the table in the main page with the new table model
        				
        				menu.setTableModel(players);
        			}
        			catch(Exception e) {
        				//in case of any errors, log the error to the console
                        logger.log(Level.WARNING,e.toString());
        			}
        		}
        	});
        }
        
        //create a new table model based on the list of all players from the GameEngine model
        public DefaultTableModel createTableModel(GameEngine ge){
            Vector<Vector<Object>>rows = new Vector<>();
            this.playersList = (ArrayList<Player>) ge.getAllPlayers();
            //create a vector of column names
            Vector<String>columns = new Vector<>();
            columns.add("ID");
            columns.add("NAME");
            columns.add("POINTS");
            columns.add("BET AMOUNT");
            columns.add("BET TYPE");
            columns.add("PREVROUND");
            for (Player player : playersList) {
                Vector<Object>data = new Vector<>();
                data.add(player.getPlayerId());
                data.add(player.getPlayerName());
                data.add(player.getPoints());
                data.add(player.getBet());
                data.add(player.getBetType());
                //if there is at least 1 winner in the previous round 
                //(this column will always be empty during the first round)
                if(winner!=null) {
                	//verify the winner data of the previous round with the actual player data in the player list
                	if(winner.getPlayerId().equals(player.getPlayerId())) {
                    	data.add("WIN");
                    }
                    else {
                    	data.add("LOSS");
                    }
                }
                rows.add(data);
            }
            //create an instance of DefaultTableModel as the table model for the table
            DefaultTableModel tableModel = new DefaultTableModel(rows,columns);
            return tableModel;
        }
	
	  @Override
	   public void nextSlot(Slot slot, GameEngine engine)
	   {
		  //update the status label with the next slot information
		  double slotsRadians = 2*Math.PI/38;
		  final int PERMX = 622; //Center of the wheel X-coordinate
		  final int PERMY = 290; //Center of the wheel Y-coordinate
		  int x;
		  int y;
		  double theta = menu.getTheta();
		  double radianTheta = Math.toRadians(theta);
		  i = i+1; 
		  x = (int) (PERMX + 147 * Math.cos(radianTheta + (i*slotsRadians))); //290
		  y = (int) (PERMY + 147 * Math.sin(radianTheta + (i*slotsRadians)));
		  menu.setXCoord(x);
		  menu.setYCoord(y);
		  
		  menu.repaint();
	  }
	  
	  //for getting the initial position of the ball base on the random number from slot
	   public void setBallFirstPosition(int pos) {
		   i = pos;
	   }

	   @Override
	   public void result(Slot result, GameEngine engine)
	   {
	      // results will be displayed in a popup window
		   winner=null;
		   //calculate game result method in Game Engine, using the result method 
		   //in the superclass(GameEngineCallbackImpl)
		   super.result(result, engine);
		   //
		   for(int i=0; i<this.initialPoints.size();i++) {
			   if(initialPoints.get(i)<playersList.get(i).getPoints()) {
				   winner = playersList.get(i);
				   break;
			   }
		   }
		   
		   //refresh the player table with new data
		   refreshMainTable(engine);
	      //create a popup menu to display the results
		   //if the winner object is not null, in other words there is 
		   if(winner != null) {
			   JOptionPane.showMessageDialog(null, "Congratulations! "+winner.getPlayerName()+" is the winner");
		   }
		   else {
			   JOptionPane.showMessageDialog(null, "There are no winners on this round");
		   }
		   this.initialPoints.clear();
		 //reset the bet for all players
		   for(Player player:playersList) {
			   player.resetBet();
			   player.setBetType(null);
		   }
		   refreshMainTable(engine);
	   }
	   

}
