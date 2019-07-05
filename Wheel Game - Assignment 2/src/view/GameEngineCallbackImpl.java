package view;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

   public GameEngineCallbackImpl()
   {
      // FINE shows wheel spinning output, INFO only shows result
      logger.setLevel(Level.FINE);
      
      //configure the console handler to show FINE level of logs
     // ConsoleHandler handler = new ConsoleHandler();
      //handler.setLevel(Level.FINE);
      //logger.addHandler(handler);
   }

   @Override
   public void nextSlot(Slot slot, GameEngine engine)
   {
      // intermediate results logged at Level.FINE
      logger.log(Level.FINE, "Next Slot: "+slot.toString());
   }

   @Override
   public void result(Slot result, GameEngine engine)
   {
      // final results logged at Level.INFO
	   
	   //calculate game result method in Game Engine
	   engine.calculateResult(result);
      logger.log(Level.INFO, "Result: "+result.toString());
      logger.log(Level.INFO, "Final Player Balances");
      //get the list of players and log their info
      for(int i=1; i<engine.getAllPlayers().size()+1;i++) {
    	  logger.log(Level.INFO, engine.getPlayer(String.valueOf(i)).toString());
      }
      // TODO: complete this method to log results
   }
}
