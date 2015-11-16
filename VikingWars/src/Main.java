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
	
	public static int gameState = WAITING_FOR_PLAYERS;
	
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
		JPanel mainGamePanel = new JPanel();
		Start start = new Start();
		panel.setBackground(new Color(245,245,245));
		panel.setLayout(new CardLayout());
		panel.add(start);
		panel.add(mainGamePanel);
		start.startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == start.startButton){
					 /*if(playerNames.size() < 3){
						 gameState = WAITING_FOR_PLAYERS;
						 Start.playerStatus.setText("Waiting for players"); 
					 }
					 else{*/
						 CardLayout cl = (CardLayout)(panel.getLayout());
						 cl.show(panel, "");
					     thisPlayer = start.enterPlayerName.getText();
					     host = start.enterHostName.getText();
					     System.out.println(host);
					     playerNames.add(thisPlayer);
					     ChatUI client = new ChatUI(host, 1500, thisPlayer);
					     mainGamePanel.add(client);
					     //GameServer gs = new GameServer();
					     //ChatServer cs = new ChatServer();
					 //}
				}	
			}
		});
		Menu menu = new Menu();
		Game game = new Game();
		mainGamePanel.add(menu);
		mainGamePanel.add(game);
		mainGamePanel.setBorder(new EmptyBorder(5,5,5,5));
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(1190, 750));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
