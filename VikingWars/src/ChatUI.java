/*
 * VIKING WARS
 * CMSC 137 LABORATORY PROJECT
 * 1st Semester AY 2015-2016
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class ChatUI extends JPanel implements ActionListener{
	
	JTextField chatBox = new JTextField();
	JPanel textPanel = new JPanel(new GridLayout(1,1));
	static JTextArea messagesPanel;
	JLabel chatLabel = new JLabel("Global Chat");
	Client client;
	
	public ChatUI(String host, int port, String username){
		this.setPreferredSize(new Dimension(250, 700));
		this.setOpaque(false);
		chatBox.setPreferredSize(new Dimension(240, 45));
		chatBox.setMargin(new Insets(5,5,5,5));
		messagesPanel = new JTextArea("Welcome to Viking Wars Chat Room!\nPress enter to send a message.\n\n", 36, 20);
		messagesPanel.setBackground(new Color(230,230,250));
		messagesPanel.setMargin(new Insets(10,10,10,10));
		textPanel.add(new JScrollPane(messagesPanel));
		messagesPanel.setEditable(false);
		chatLabel.setBackground(new Color(226, 226, 243));
		chatLabel.setBorder(new EmptyBorder(10,80,10,0));
		chatLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		chatLabel.setOpaque(true);
		chatLabel.setPreferredSize(new Dimension(240, 35));
		this.add(chatLabel);
		this.add(textPanel);
		this.add(chatBox);
		client = new Client(Main.host, port, username, this);
		client.start();
		chatBox.addActionListener(this);
	}
	
	// called by the Client to append text in the TextArea 
	void append(String str) {
		messagesPanel.append(str);
		messagesPanel.setCaretPosition(messagesPanel.getText().length() - 1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, chatBox.getText()));				
		chatBox.setText("");
		return;
	}
	
}
