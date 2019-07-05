package client;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import validate.Validator;
import view.GameEngineCallbackImpl;
import view.MainPage;
import view.GameEngineCallbackGUI;

/**
 * <pre> Simple console client for Further Programming assignment 1, 2019
 * <b>NOTE:</b> This code will not compile until you have implemented code for the supplied interfaces!
 * 
 * You must be able to compile your code WITHOUT modifying this class.
 * Additional testing should be done by copying and adding to this class while ensuring this class still works.
 * 
 * The provided Validator.jar will check if your code adheres to the specified interfaces!</pre>
 * 
 * @author Caspar Ryan
 * 
 */
public class SimpleTestClient
{
   private static final Logger logger = Logger.getLogger(SimpleTestClient.class.getName());

   public static void main(String args[])
   {
      final GameEngine gameEngine = new GameEngineImpl();
      final GameEngineCallbackGUI gec = new GameEngineCallbackGUI();
      // call method in Validator.jar to test *structural* correctness
      // just passing this does not mean it actually works .. you need to test yourself!
      // pass false if you want to show minimal logging (pass/fail) .. (i.e. ONLY once it passes)
      Validator.validate(true);
      
      // add logging callback class
      gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
      //add GUI callback class
      gameEngine.addGameEngineCallback(gec);
      
      // check the wheel creation is correct by inspecting logs
      logWheel(gameEngine.getWheelSlots());
      
      //call the function to initialize the GUI from GameEngineCallbackGUI class
      gec.initGUI(gameEngine);
      
      // TODO reset bets for next round if you were playing again
   }

   // private helper method to log wheel slots
   private static void logWheel(Collection<Slot> wheel)
   {
      logger.log(Level.INFO, "LOGGING WHEEL DATA CREATED BY GameEngineImpl");
      for (Slot slot : wheel)
         logger.log(Level.INFO, slot.toString());
      logger.log(Level.INFO, "END WHEEL LOG\n");
   }
}
