/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import view.BetPage;
import view.GameEngineCallbackGUI;

/**
 *
 * @author ASUS
 */
public class SetBetPageListener implements ActionListener{
    private GameEngine ge;
    private GameEngineCallbackGUI gui;
    
    public SetBetPageListener(GameEngine ge,GameEngineCallbackGUI gui){
        super();
        this.ge= ge;
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
            gui.initBetPage(ge);
    }
}
