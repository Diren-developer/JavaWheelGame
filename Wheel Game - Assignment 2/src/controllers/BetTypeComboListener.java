package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import view.GameEngineCallbackGUI;

public class BetTypeComboListener implements ActionListener{
	
	private JComboBox comboBox;
	private GameEngineCallbackGUI gui;
	public BetTypeComboListener(GameEngineCallbackGUI gui) {
		super();
		this.gui = gui;
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		this.comboBox = (JComboBox) e.getSource();
		gui.setBetType(this.comboBox.getSelectedItem().toString());
	}

}
