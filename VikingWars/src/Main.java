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
import javax.swing.border.EmptyBorder;

public class Main {
	
	protected static ArrayList<String> playerNames = new ArrayList<String>();
	protected static String thisPlayer;
	
	public final static int WAITING_FOR_PLAYERS = 0;
	public final static int IN_PROGRESS = 0;
	public final static int GAME_OVER = 0;
	
	public static int gameState = WAITING_FOR_PLAYERS;
	
	public static void main(String args[]){
		JFrame frame = new JFrame("Viking Wars");
		JPanel panel = new JPanel();
		JPanel mainGamePanel = new JPanel();
		//JPanel start = new JPanel();
		JTextField enterPlayerName = new JTextField();
		JLabel playerName = new JLabel("Enter player name: ");
		Start start = new Start();
		//thisPlayer = enterPlayerName.getText();
		//playerNames.add(thisPlayer);
		panel.setBackground(new Color(245,245,245));
		panel.setLayout(new CardLayout());
		panel.add(start);
		panel.add(mainGamePanel);
		/*start.setBackground(new Color(245,245,245));
		JButton startButton = new JButton("Start game");
		startButton.setEnabled(false);*/
		start.startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == start.startButton){
					 CardLayout cl = (CardLayout)(panel.getLayout());
				     cl.show(panel, "");
				     //GameServer gs = new GameServer();
				     //ChatServer cs = new ChatServer();
				}	
			}
		});
		/*enterPlayerName.setPreferredSize(new Dimension(200, 25));
		enterPlayerName.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				startButton.setEnabled(true);
				
			}
		});
		start.add(playerName);
		start.add(enterPlayerName);
		start.add(startButton);*/
		Menu menu = new Menu();
		Game game = new Game();
		//ChatUI chat = new ChatUI();
		
		/*int portNumber = 1500;
		String serverAddress = "localhost";
		String userName = "Anonymous";

		// depending of the number of arguments provided we fall through
		switch(args.length) {
			// > javac Client username portNumber serverAddr
			case 3:
				serverAddress = args[2];
			// > javac Client username portNumber
			case 2:
				try {
					portNumber = Integer.parseInt(args[1]);
				}
				catch(Exception e) {
					System.out.println("Invalid port number.");
					System.out.println("Usage is: > java Client [username] [portNumber] [serverAddress]");
					return;
				}
			// > javac Client username
			case 1: 
				userName = args[0];
			// > java Client
			case 0:
				break;
			// invalid number of arguments
			default:
				System.out.println("Usage is: > java Client [username] [portNumber] {serverAddress]");
			return;
		}
		// create the Client object
		ChatClient client = new ChatClient(serverAddress, portNumber, userName);
		// test if we can start the connection to the Server
		// if it failed nothing we can do
		if(!client.start())
			return;
		*/
		int portNumber = 1500;
		String serverAddress = "localhost";
		String userName = "Anonymous";
		ChatUI client = new ChatUI(serverAddress, 1500);
		mainGamePanel.add(menu);
		mainGamePanel.add(game);
		mainGamePanel.add(client);
		//((FlowLayout)mainGamePanel.getLayout()).setVgap(0);
		//((FlowLayout)mainGamePanel.getLayout()).setHgap(0);
		mainGamePanel.setBorder(new EmptyBorder(5,5,5,5));
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(1190, 750));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
