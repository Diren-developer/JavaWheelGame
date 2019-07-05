package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.interfaces.GameEngine;
import controllers.RemovePlayerListener;
import controllers.TableListener;

public class RemovePlayerPage extends JFrame {
	private GameEngine ge;
	private JPanel summaryPanel;
	private JTable playersJTable;
	private GameEngineCallbackGUI gui;
	private JSplitPane splitPane;
	private JPanel playerListPanel;
	private JPanel buttonPanel;
	private JButton cancelButton;
	private JButton removeButton;
	private JScrollPane scrollPane;
	private JTable playerTable;
	private DefaultTableModel model;
    
	public RemovePlayerPage(GameEngine ge,GameEngineCallbackGUI gui, DefaultTableModel model) {
		this.ge = ge;
        this.gui = gui;
        this.model = model;
		buttonPanel = new JPanel();
		splitPane = new JSplitPane();
		playerListPanel = new JPanel();
		buttonPanel = new JPanel();
		cancelButton = new JButton();
		removeButton = new JButton();
		playerListPanel = new JPanel();
		playerTable = new JTable();
		scrollPane = new JScrollPane(playersJTable);
		initGUI();
	}

	private void initGUI() {
		// TODO Auto-generated method stub
		initJFrame();
		initPlayerList();
		initButtonPane();
	}
	
	private void initJFrame() {
		setTitle("Setup Bet");
		setSize(500,300);
		setLocationRelativeTo(null); 
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPane);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(200);
        splitPane.setTopComponent(playerListPanel);
        splitPane.setBottomComponent(buttonPanel);
        splitPane.setEnabled(false);
                
        }
	
	private void initPlayerList(){
		playerTable.setModel(model);
		//add event listener for the table selection
		playerTable.addMouseListener(new TableListener(playerTable,null,gui));
		playerListPanel.setLayout(new BorderLayout(0,0));
		playerListPanel.add(playerTable, BorderLayout.CENTER);
		
	}
	
	
	private void initButtonPane() {
		buttonPanel.setLayout(new GridLayout(0,2));
		
		cancelButton.setText("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.setHorizontalAlignment(SwingConstants.CENTER);
		cancelButton.addActionListener(new RemovePlayerListener(ge,gui,this));
		
		removeButton.setText("Remove");
		removeButton.setActionCommand("Remove");
		removeButton.setHorizontalAlignment(SwingConstants.CENTER);
		removeButton.addActionListener(new RemovePlayerListener(ge,gui,this));
		
		buttonPanel.add(cancelButton);
		buttonPanel.add(removeButton);
	}
	
}
