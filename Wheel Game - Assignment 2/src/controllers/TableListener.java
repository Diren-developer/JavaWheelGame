package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.interfaces.Player;
import view.BetPage;
import view.GameEngineCallbackGUI;

public class TableListener implements MouseListener{
	
	private JTable table;
	private DefaultTableModel model;
	private JLabel name;
	private Vector<Object>player;
	private GameEngineCallbackGUI gui;
	
	public TableListener(JTable table, JLabel name,GameEngineCallbackGUI gui) {
		super();
		this.table = table;
		this.name = name;
		this.gui = gui;
	}
	


	@Override
	public void mouseClicked(MouseEvent e) {
		model = (DefaultTableModel) this.table.getModel();
		int selectedRowIndex = this.table.getSelectedRow();
		try {
			this.player = model.getDataVector().elementAt(selectedRowIndex);
			if(player !=null && name!=null) {
				this.name.setText(player.get(1).toString());
			}
			else {
				return;
			}
			this.gui.setPlayerData(this.player);
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			
		}
		
		//getPlayerData(player);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
