/*
 * VIKING WARS
 * CMSC 137 LABORATORY PROJECT
 * 1st Semester AY 2015-2016
 */

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class Start extends JPanel{
	
	JButton startButton = new JButton("Start game");
	Container container = new Container();
	JLabel title = new JLabel("Viking Wars");
	JPanel centerPanel = new JPanel();
	JTextField enterPlayerName = new JTextField(15);
	JTextField enterHostName = new JTextField(15);
	JTextField enterPortNum = new JTextField(15);
	JLabel playerName = new JLabel("Enter player name: ");
	JLabel hostName = new JLabel("Host: ");
	JLabel portNum = new JLabel("Port: ");
	static JLabel playerStatus = new JLabel("");
	
	public Start(){
		setBackground(new Color(230, 230, 250));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		initialize();
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		add(centerPanel, c);
		title.setFont(new Font("Calibri", Font.PLAIN, 20));
		startButton.setFont(new Font("Calibri", Font.PLAIN, 16));
		startButton.setBackground(new Color(230, 230, 250));
		startButton.setFocusPainted(isLightweight());
		centerPanel.setPreferredSize(new Dimension(220, 220));
		centerPanel.setBorder(new EmptyBorder(5,5,5,5));
		//centerPanel.setBorder(new LineBorder(Color.DARK_GRAY));
		centerPanel.add(title);
		centerPanel.add(container);
		centerPanel.add(startButton);
		add(centerPanel);
		
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
	}
	
	//lays out the panel containing the information needed from the player
	public void initialize(){
		enterPlayerName.setText("Johan");
		enterHostName.setText("localhost");
		enterPortNum.setText("1234");
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		fix(hostName, enterHostName);
		fix(portNum, enterPortNum);
		fix(playerName, enterPlayerName);
	}
	
	//function that adds the label and corresponding text field to the container
	public void fix(JLabel label, JTextField tf){
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		label.setFont(new Font("Calibri", Font.PLAIN, 16));
		tf.setAlignmentX(Component.LEFT_ALIGNMENT);
		tf.setFont(new Font("Calibri", Font.PLAIN, 16));
		tf.setCaretPosition(tf.getText().length());
		container.add(label);
		container.add(tf);
	}
	
}
