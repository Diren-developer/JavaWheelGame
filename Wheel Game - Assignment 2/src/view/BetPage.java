package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controllers.BetTypeComboListener;
import controllers.SetBetListener;
import controllers.TableListener;
import model.interfaces.GameEngine;

public class BetPage extends JFrame{
	
	private JSplitPane splitPane;
	private JPanel playerListPanel;
	private JPanel configPanel;
	private JLabel selectPlayerLabel;
	private JLabel nameLabel;
	private JLabel playerLabel;
	private JLabel betLabel;
	private JLabel betTypeLabel;
	private JLabel dummyLabel1;
	private JLabel dummyLabel2;
	private JTextField betTextField;
	private JComboBox betTypeComboBox;
	private JButton closeButton;
	private JButton saveButton;
	private JTable playersTable;
	private JScrollPane scrollPane;
	
	private GameEngine ge;
	private GameEngineCallbackGUI gui;
	private DefaultTableModel model;
    
	private JTable players;
    
    //to set each player's bet, select the player from the table and then add the amount of bet
    //and bet type in the text fields
    public BetPage(GameEngine ge,GameEngineCallbackGUI gui, DefaultTableModel model) {
    	String [] betTypes = {"Select Bet Types...", "RED" , "BLACK", "ZEROS"};
    	splitPane = new JSplitPane();
    	playerListPanel = new JPanel();
    	configPanel = new JPanel();
    	selectPlayerLabel = new JLabel();
    	nameLabel = new JLabel();
    	playerLabel = new JLabel();
    	betLabel = new JLabel();
    	betTypeLabel = new JLabel();
    	dummyLabel1 = new JLabel();
    	dummyLabel2 = new JLabel();
    	betTextField = new JTextField();
    	betTypeComboBox = new JComboBox(betTypes);
    	closeButton = new JButton();
    	saveButton = new JButton();
    	playersTable = new JTable();
    	scrollPane = new JScrollPane(playersTable);
    	
		this.ge = ge;
		this.gui = gui;
		this.model = model;
		initGUI();
    }
    
    private void initGUI() {
    	initJFrame();
    	initPlayerList();
    	initConfig();
        
    }
    
    private void initJFrame() {
    	setTitle("Setup Bet");
		setSize(500,300);
		setLocationRelativeTo(null);
        
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPane);
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(250);
        splitPane.setLeftComponent(playerListPanel);
        splitPane.setRightComponent(configPanel);
        splitPane.setEnabled(false);
    }
        
    private void initPlayerList() {
    	setTableModel(model);
    	playerListPanel.setLayout(new BorderLayout(50,50));
    	playerListPanel.add(selectPlayerLabel, BorderLayout.NORTH);
    	playerListPanel.add(scrollPane, BorderLayout.CENTER);
    	
    	//add event listener for the table selection
    	TableListener tListener = new TableListener(playersTable,playerLabel,gui);
    	playersTable.addMouseListener(tListener);
    	
    	selectPlayerLabel.setText("Select Player");
    	selectPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    private void setTableModel(DefaultTableModel model) {
    	playersTable.setModel(model);
    }
    
    private void initConfig() {
    	configPanel.setLayout(new GridLayout(0,2,10,10));
    	
    	nameLabel.setText("Name");
    	nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
    	
    	playerLabel.setText("Select a player from the table");
    	playerLabel.setHorizontalAlignment(SwingConstants.LEFT);
    	
    	betLabel.setText("Bet");
    	betLabel.setHorizontalAlignment(SwingConstants.LEFT);
    	
    	betTextField.setHorizontalAlignment(SwingConstants.LEFT);
    	
    	betTypeLabel.setText("Bet Type");
    	betTypeLabel.setHorizontalAlignment(SwingConstants.LEFT);
    	
    	//betTypeComboBox insert object here.
    	betTypeComboBox.setSelectedIndex(0);
    	betTypeComboBox.addActionListener(new BetTypeComboListener(gui));
    	
    	closeButton.setText("Close");
    	closeButton.setActionCommand("Close");
    	closeButton.addActionListener(new SetBetListener(ge,betTextField,this,gui));
    	saveButton.setText("Save");
    	saveButton.setActionCommand("Save");
    	saveButton.addActionListener(new SetBetListener(ge,betTextField,this,gui));
    	
    	configPanel.add(nameLabel);
    	configPanel.add(playerLabel);
    	configPanel.add(betLabel);
    	configPanel.add(betTextField);
    	configPanel.add(betTypeLabel);
    	configPanel.add(betTypeComboBox);
    	configPanel.add(dummyLabel1);
    	configPanel.add(dummyLabel2);
    	configPanel.add(closeButton);
    	configPanel.add(saveButton);
    	
    }
    	
}
