package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;

import controllers.MenuBarListener;
import controllers.SetBetPageListener;
import controllers.SpinListener;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.interfaces.GameEngine;

public class MainPage extends JFrame{
	private int diameter = 15;
	private int x = 622; //Center of wheel X-coordinate
	private int y = 290; //Center of wheel Y-coordinate
	private double theta = -90.0;
	private JPanel contentPanel;
	private GameEngine ge;
	private JPanel summaryPanel;
	private JTable playersJTable;
	private GameEngineCallbackGUI gui;
	private JLabel statusLabel;
	private DefaultTableModel players;
	private BufferedImage image;
	private JPanel wheelPanel;
	private JMenuBar menuBar;
	private JMenu playersJMenu;
	private JMenuItem addPlayer;
	private JMenuItem removePlayer;
	private JToolBar toolbar;
	private JButton betButton;
	private JLabel wheelPlaceHolderLabel;
        
		public MainPage(GameEngine ge,GameEngineCallbackGUI gui,DefaultTableModel players) {
			this.ge = ge;
	        this.gui = gui;
	        this.players = players;
	        this.playersJTable = new JTable();
	        wheelPanel = new JPanel();
	        summaryPanel = new JPanel();
			contentPanel = new JPanel();
			menuBar = new JMenuBar();
			playersJMenu = new JMenu("Players");
			addPlayer = new JMenuItem("Add Player");
			removePlayer = new JMenuItem("Remove Player");
	        toolbar = new JToolBar();
            betButton = new JButton();
    		statusLabel = new JLabel("status");
    	    wheelPlaceHolderLabel = new JLabel();
			initGUI();	
		}
	
	//initialize GUI components and properties
	private void initGUI() {
		
		initJFrame();
		initMenuBar();
		initToolbar();	
		initWheel();
		initStatusBar();
		initSummaryPanel();
	}
	
	//Create JFrame
	private void initJFrame() {
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		setSize(800,600);
		setTitle("Assignment 2");
		setResizable(false);
	}
	
	//create the ball
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, diameter, diameter);
	}
	
	//create a menu bar
	private void initMenuBar() {
        setContentPane(contentPanel);
        setTitle("Assignment 2");
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//add event listeners for the menu bar items
		MenuBarListener listeners = new MenuBarListener(ge,gui);
		addPlayer.addActionListener(listeners);
		removePlayer.addActionListener(listeners);
		
		//add Menu option to menu bar
		playersJMenu.add(addPlayer);
		playersJMenu.add(removePlayer);
		
		//add menu to menu bar
		menuBar.add(playersJMenu);
		setJMenuBar(menuBar);
	}
	
	//Create toolbar
	private void initToolbar() {
            setContentPane(contentPanel);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            toolbar.setRollover(true);
            betButton.setText("Bet");
            toolbar.add(betButton);
            
            //add event listeners for the menu bar items
    		SetBetPageListener listeners = new SetBetPageListener(ge,gui);
    		betButton.addActionListener(listeners);
    		
            contentPanel.add(toolbar, BorderLayout.NORTH);
            toolbar.setFloatable(false);
           
	}
	//table model for the Jtable
	public void setTableModel(DefaultTableModel players) {
		this.players = players;
		playersJTable.setModel(this.players);
	}
	
	//create a summary panel
	private void initSummaryPanel() {
            setContentPane(contentPanel);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            summaryPanel.setLayout(new BorderLayout (50,50));
            contentPanel.add(summaryPanel, BorderLayout.CENTER);

            JLabel playerSummaryLabel = new JLabel();
            playerSummaryLabel.setText("Player Summary");
            summaryPanel.add(playerSummaryLabel, BorderLayout.NORTH);

            JScrollPane sp = new JScrollPane(playersJTable);
            playersJTable.setEnabled(false);
            summaryPanel.add(sp, BorderLayout.WEST);
        
	}
	
    //init Wheel
    private void initWheel() {
    	setContentPane(contentPanel);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Create Panel for wheel

        wheelPanel.setLayout(new BorderLayout (50,50));
        contentPanel.add(wheelPanel, BorderLayout.EAST);
        
    	//Create Wheel
        wheelPanel.add(wheelPlaceHolderLabel, BorderLayout.CENTER);
        try {
        	image = ImageIO.read(new File("img/roulette.png"));
        }
        catch (IOException e) {
        	System.out.println("Image not found");
        }
        
        Image scaledImage = image.getScaledInstance(315, 315, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        wheelPlaceHolderLabel.setIcon(icon);
        
        //Create spin button
        JButton spinButton = new JButton();
        spinButton.setText("SPIN");
        spinButton.setActionCommand("spin");
        spinButton.addActionListener(new SpinListener(ge,gui,spinButton));
        wheelPanel.add(spinButton, BorderLayout.SOUTH);
        //paintComponent(null);
    }
    
    //Label for status
    public void setStatusLabel(String status) {
    	this.statusLabel.setText(status);
    }
    
    //create status bar
	private void initStatusBar() {
		// create the status bar panel and shove it down the bottom of the frame
		JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new BorderLayout (0,0));
		contentPanel.add(statusPanel, BorderLayout.SOUTH);
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
	}
	
	public void setXCoord(int x) {
		this.x = x;
	}
	
	public void setYCoord(int y) {
		this.y = y;
	}

	public double getTheta() {
		return theta;
	}
	
	
}
