import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class ChatUI extends JPanel implements ActionListener{
	
	JTextField chatBox = new JTextField();
	static JTextArea messagesPanel = new JTextArea();
	JLabel chatLabel = new JLabel("Global Chat");
	Client client;
	String username = "Johan";
	String server = "localhost";
	
	public ChatUI(String host, int port){
		this.setPreferredSize(new Dimension(250, 700));
		this.setOpaque(false);
		chatBox.setPreferredSize(new Dimension(240, 45));
		messagesPanel.setPreferredSize(new Dimension(240,600));
		messagesPanel.setBackground(new Color(230,230,250));
		messagesPanel.setEditable(false);
		messagesPanel.setMargin(new Insets(10,10,10,10));
		chatLabel.setBackground(new Color(230,230,250));
		chatLabel.setBorder(new EmptyBorder(10,80,10,0));
		chatLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		chatLabel.setOpaque(true);
		chatLabel.setPreferredSize(new Dimension(240, 35));
		this.add(chatLabel);
		this.add(messagesPanel);
		this.add(chatBox);
		client = new Client(server, port, username, this);
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
