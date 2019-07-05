package view;

import controllers.AddPlayersListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import model.interfaces.GameEngine;

public class AddPlayersPage extends JFrame{
		private GameEngine ge;
        private JSplitPane splitPane;
        private JPanel fieldsPanel;
        private JPanel namesPanel;
        private JPanel pointsPanel;
        private JPanel buttonsPanel;
        
        private JLabel playerName ;
        private JLabel initialPoints;
		
        private JTextField name;
        private JTextField points;
               
        private JButton addPlayer;
        private JButton cancel;
        
        private GameEngineCallbackGUI gui;
        
	public AddPlayersPage(GameEngine ge, GameEngineCallbackGUI gui) {
                //creates new instances of the split pane and JPanels
                splitPane = new JSplitPane();
                fieldsPanel = new JPanel();
                buttonsPanel = new JPanel();
                namesPanel = new JPanel();
                pointsPanel = new JPanel();
                this.ge = ge;
                this.gui = gui;
                setComponents();
	}
	
	private void setComponents() {
                setTitle("Add Players");
				setSize(300,300);
				setLocationRelativeTo(null);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                
		       /*
		       Initiate the components of this page, as well as the necessary listeners
		      */
				playerName = new JLabel("Player Name");
				initialPoints = new JLabel("Initial Points");
				
				name = new JTextField();
				points = new JTextField();
                
                addPlayer = new JButton("Add Player");
                cancel = new JButton("Cancel");
                
                playerName.setText("Player Name");
                initialPoints.setText("Initial Points");
                            
                addPlayer.setText("Add Player");
                addPlayer.setActionCommand("Add Player");
                
                addPlayer.addActionListener(new AddPlayersListener(ge,name,points,this,gui));
                cancel.setText("Cancel");
                cancel.setActionCommand("Cancel");
                cancel.addActionListener(new AddPlayersListener(ge,name,points,this,gui));
                
                setPanels();
	}
        
        private void setPanels(){
                /*
                    This page uses the Grid Layout split into top and bottom halves using the JSplitPane
                    The top half consists of the JLabels and JTextFields for inserting player data
                    The bottom half consists of the buttons used to submit the data or to cancel/close the page
                */
                getContentPane().setLayout(new GridLayout());
                getContentPane().add(splitPane);
                splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
                splitPane.setDividerLocation(150);
                splitPane.setTopComponent(fieldsPanel);
                splitPane.setBottomComponent(buttonsPanel);
                
                fieldsPanel.setLayout(new BoxLayout(fieldsPanel,BoxLayout.Y_AXIS));
                namesPanel.setLayout(new BoxLayout(namesPanel,BoxLayout.X_AXIS));
                pointsPanel.setLayout(new BoxLayout(pointsPanel,BoxLayout.X_AXIS));
                namesPanel.add(playerName);
                namesPanel.add(name);
                pointsPanel.add(initialPoints);
                pointsPanel.add(points);
                fieldsPanel.add(namesPanel);
                fieldsPanel.add(pointsPanel);
                
                buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.Y_AXIS));
                
                buttonsPanel.add(addPlayer);
                buttonsPanel.add(cancel);
         
        }

}
