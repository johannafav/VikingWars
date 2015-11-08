import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Start extends JPanel{
	
	JButton startButton = new JButton("Start game");
	Container container = new Container();
	JPanel centerPanel = new JPanel();
	JTextField enterPlayerName = new JTextField();
	JTextField enterHostName = new JTextField();
	JTextField enterPortNum = new JTextField();
	JLabel playerName = new JLabel("Enter player name: ");
	JLabel hostName = new JLabel("Host: ");
	JLabel portNum = new JLabel("Port: ");
	static JLabel playerStatus = new JLabel("");
	
	public Start(){
		//container.add(this);
		//this.setBackground(new Color(245,245,245));
		//this.setLayout(new BorderLayout());
		enterPlayerName.setText("Johan");
		enterHostName.setText("localhost");
		enterPortNum.setText("1234");
		//startButton.setEnabled(false);
		enterPlayerName.setPreferredSize(new Dimension(200, 25));
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
		/*centerPanel.add(hostName);
		centerPanel.add(enterHostName);
		centerPanel.add(portNum);
		centerPanel.add(enterPortNum);
		centerPanel.add(playerName);
		centerPanel.add(enterPlayerName);
		centerPanel.add(startButton);
		centerPanel.setPreferredSize(new Dimension(100,100));
		centerPanel.setBackground(new Color(230, 230, 250));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));*/
		
		this.add(hostName);
		this.add(enterHostName);
		this.add(portNum);
		this.add(enterPortNum);
		this.add(playerName);
		this.add(enterPlayerName);
		this.add(startButton);
		this.add(playerStatus);
		//this.setPreferredSize(new Dimension(100,100));
		this.setBackground(new Color(230, 230, 250));
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.add(centerPanel, BorderLayout.CENTER);
	}
	
}
