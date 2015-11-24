/*
 * VIKING WARS
 * CMSC 137 LABORATORY PROJECT
 * 1st Semester AY 2015-2016
 */

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class Main implements Constants{
	
	protected static ArrayList<String> playerNames = new ArrayList<String>();
	protected static String thisPlayer;
	protected static String host;
	protected static Game game;
	
	public static int gameState = GAME_START;
	
	//creates main window
	public static void main(String args[]){
		try {
			UIManager.setLookAndFeel(
			        UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JFrame frame = new JFrame("Viking Wars");
		JPanel panel = new JPanel();
		JPanel loadPanel = new JPanel();
		JPanel mainGamePanel = new JPanel();
		Start start = new Start();
		panel.setBackground(new Color(245,245,245));
		panel.setLayout(new CardLayout());
		panel.add(start);
		panel.add(mainGamePanel);
		start.startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == start.startButton){
					/*while(playerNames.size() < 3){
						 gameState = WAITING_FOR_PLAYERS;
						 Start.playerStatus.setText("Waiting for players"); 
					 }*/
						/*loadPanel.add(Start.playerStatus);
						loadPanel.setPreferredSize(new Dimension(220, 250));
						CardLayout cl = (CardLayout)(loadPanel.getLayout());
						cl.show(loadPanel, "");*/
						CardLayout cl = (CardLayout)(panel.getLayout());
						 cl.show(panel, "");
					     thisPlayer = start.enterPlayerName.getText();
					     host = start.enterHostName.getText();
					     Menu.playerLabel.setText("Welcome, "+thisPlayer+"!");
					     //JPanel temp = new JPanel();
					     //temp.setPreferredSize(new Dimension(700, 700));
					     try {
								game = new Game();
								mainGamePanel.add(game);
							} catch (SocketException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					     //mainGamePanel.add(temp);
					     playerNames.add(thisPlayer);
					     ChatUI client = new ChatUI(host, 1500, thisPlayer);
					     mainGamePanel.add(client);
					     //GameServer gs = new GameServer();
					     //ChatServer cs = new ChatServer();
				}	
			}
		});
		Menu menu = new Menu();
		mainGamePanel.add(menu);
		mainGamePanel.setBorder(new EmptyBorder(5,5,5,5));
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(1190, 750));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
