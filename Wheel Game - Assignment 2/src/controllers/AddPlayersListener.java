/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AddPlayersPage;
import view.GameEngineCallbackGUI;
import view.MainPage;

/**
 *
 * @author ASUS
 */
public class AddPlayersListener implements ActionListener{
    private Player player;
    private AtomicInteger count;
    private int playersAmount;
    private int id;
    private ArrayList<Player>players = new ArrayList<Player>();
    private JTextField name;
    private JTextField points;
    private GameEngine ge;
    private AddPlayersPage frame;
    private GameEngineCallbackGUI gui;
    private int pointsValue;
    private String nameString;
    
    public AddPlayersListener(GameEngine ge, JTextField name, JTextField points,AddPlayersPage frame, GameEngineCallbackGUI gui){
        super();
        this.ge = ge;
        this.name = name;
        this.points = points;
        this.frame = frame;
        this.gui = gui;
        players= (ArrayList<Player>) ge.getAllPlayers();    
    }
    
    
    @Override
	public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Add Player")){
                    nameString = this.name.getText();
                    
                    //try-catch statement, mostly used to check if the Integer parsing from string
                    //value retrieved from the points JTextField is successful
                    try{//if the player name is empty, display a popup to notify the user to insert a name
                        if(nameString.isEmpty() || nameString.isBlank()){
                            JOptionPane.showMessageDialog(null,
                                "Invalid Player Name.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        pointsValue = Integer.parseInt(this.points.getText());
                       //if the points amount is smaller or equals to 0, prompt the user to insert a value
                       //greater than 0
                        if(pointsValue <= 0){
                            JOptionPane.showMessageDialog(null,
                                "Invalid Amount. Please insert a points amount greater than 0", "Error",
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        //create a new Player object, and adds it into the list
                        //the ID is generated using the Atomic Integer, which increments the ID value automatically
                        //it is useful for doing unique-indexing operations, such as assigning ID values to new data
                        //And is also a generally thread-safe way of incrementing values
                        //source: https://stackoverflow.com/questions/4818699/practical-uses-for-atomicinteger
                        count = new AtomicInteger(ge.getAllPlayers().size());
                        id = count.incrementAndGet();
                        this.player= new SimplePlayer(String.valueOf(id),nameString,pointsValue);
                        ge.addPlayer(this.player);
                        //closes the add new player window
                        
                        frame.dispose();
                        gui.refreshMainTable(ge);//refresh the table after adding a new player
                        return;
                    }
                    catch(NumberFormatException | NullPointerException ex){
                         JOptionPane.showMessageDialog(null,
                                "Invalid Points Amount. Please insert a valid numeric amount", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    
                }//Cancel button closes the frame
                else if(e.getActionCommand().equals("Cancel")){
                    frame.dispose();
                }
                //player = new SimplePlayer((playersAmount + 1)+"",name,points);
	}
}
