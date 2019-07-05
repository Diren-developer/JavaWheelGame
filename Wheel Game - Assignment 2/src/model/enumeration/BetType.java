package model.enumeration;

import model.interfaces.Player;
import model.interfaces.Slot;

/**
 * Provided enum type for Further Programming representing a type of Bet<br>
 * See inline comments for where you need to provide additional code
 * 
 * @author Caspar Ryan
 * 
 */
public enum BetType
{
   RED
   {
      @Override
      public void applyWinLoss(Player player, Slot winSlot)
      {
         //winning criteria for this bet is if the slot's color is red
    	  if(winSlot.getColor().equals(Color.RED)) {
    		  //if the player wins, the total points is incremented by the amount of bet
    		  player.setPoints(player.getPoints() + player.getBet());
    	  }
    	  else {
    		  //if the player loses, the total points is decremented by the amount of bet
    		  player.setPoints(player.getPoints() - player.getBet());
    	  }
      }
   },
   BLACK
   {
	   @Override
	   public void applyWinLoss(Player player, Slot winSlot) {
		 //winning criteria for this bet is if the slot's color is red
		   if(winSlot.getColor().equals(Color.BLACK)) {
			 //if the player wins, the total points is incremented by the amount of bet
	    		  player.setPoints(player.getPoints() + player.getBet());
	    	  }
	    	  else {
	    		  //if the player loses, the total points is decremented by the amount of bet
	    		  player.setPoints(player.getPoints() - player.getBet());
	    	  }
	   }
   },
   ZEROS
   {
	   @Override
	   public void applyWinLoss(Player player, Slot winSlot) {
		   //winning criteria for this bet is if the slot's color is green0 or green00
		   if(winSlot.getColor().equals(Color.GREEN0) || winSlot.getColor().equals(Color.GREEN00)){
			   //if the player wins, the total points is incremented by the bet amount multiplied by
			   //WHEEL_SIZE/2-1
			   player.setPoints(player.getPoints() + player.getBet() * ((Slot.WHEEL_SIZE/2)-1));
		   }
		   else {
			 //if the player loses, the total points is decremented by the amount of bet
			   player.setPoints(player.getPoints() - player.getBet());
		   }
		   
	   }
   };
   
   // TODO finish this class with other enum constants
 
   /**
    * This method is to be overridden for each bet type<br>
    * see assignment specification for betting odds and how win/loss is applied
    * 
    * @param player - the player to apply the win/loss points balance adjustment
    * @param winSlot - the winning slot the ball landed on
    */
   public abstract void applyWinLoss(Player player, Slot winSlot);
}