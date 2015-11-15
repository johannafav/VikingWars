import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Menu extends JPanel{
	
	public static int barbariansLeft = 5;
	public static int archersLeft = 5;
	public static int wizardsLeft = 5;
	public static int wallBreakersLeft = 5;
	
	public static int toDeploy = 0;
	
	Container container = new Container();
	JPanel poolPanel = new JPanel();
	JPanel unitPanel = new JPanel();
	JPanel logsPanel = new JPanel();
	public static JButton unit[] = new JButton[4];
	public static JLabel barbarianPool = new JLabel("Barbarians Remaining: " + barbariansLeft);
	public static JLabel archerPool = new JLabel("Archers Remaining: " + archersLeft);
	public static JLabel wizardPool = new JLabel("Wizards Remaining: " + wizardsLeft);
	public static JLabel wallBreakerPool = new JLabel("Wall Breaker Remaining: " + wallBreakersLeft);
	public static JLabel unitPool = new JLabel("Unit Pool");
	public Color colorPalette[] = {Color.pink, new Color(148, 0, 215), new Color(252, 231, 88), new Color(116, 186, 185)};
	String unitType[] = {"Barbarian", "Archer", "Wizard", "Wall Breaker"};
	String unitCode[] = {"5", "6", "7", "8"};
	
	public Menu(){
		createStatusPane();
		createUnitPane();
		this.setPreferredSize(new Dimension(200, 700));
		this.setOpaque(false);
		this.add(unitPool);
		this.add(poolPanel);
		this.add(unitPanel);
	}
	
	public void createStatusPane(){
		container.add(poolPanel);
		poolPanel.add(barbarianPool);
		poolPanel.add(archerPool);
		poolPanel.add(wizardPool);
		poolPanel.add(wallBreakerPool);
		poolPanel.setLayout(new BoxLayout(poolPanel, BoxLayout.Y_AXIS));
		poolPanel.setPreferredSize(new Dimension(190, 120));
		poolPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		poolPanel.setBackground(new Color(230,230,250));
		unitPool.setFont(new Font("Calibri", Font.PLAIN, 15));
		unitPool.setBackground(new Color(230,230,250));
		unitPool.setOpaque(true);
		unitPool.setPreferredSize(new Dimension(190,35));
		unitPool.setBorder(new EmptyBorder(10,65,10,0));
		barbarianPool.setFont(new Font("Calibri", Font.PLAIN, 15));
		archerPool.setFont(new Font("Calibri", Font.PLAIN, 15));
		wizardPool.setFont(new Font("Calibri", Font.PLAIN, 15));
		wallBreakerPool.setFont(new Font("Calibri", Font.PLAIN, 15));
	}
	
	public void createUnitPane(){
		unitPanel.setLayout(new GridLayout(2, 2, 5, 5));
		unitPanel.setBackground(new Color(230,230,250));
		for(int i = 0; i < 4; i++){
			unit[i] = new JButton(unitType[i]);
			unit[i].setName(unitCode[i]);
			unit[i].setPreferredSize(new Dimension(90, 30));
			unit[i].setFont(new Font("Calibri", Font.PLAIN, 14));
			unit[i].setMargin(new Insets(1,1,1,1));
			unit[i].setBackground(colorPalette[i]);
			unit[i].setFocusPainted(isLightweight());
			addListener(unit[i]);
			unitPanel.add(unit[i]);
		}
	}
	
	public void addListener(JButton button){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == button){
					toDeploy = Integer.parseInt(button.getName());
				}	
			}
		});
	}
	
}
