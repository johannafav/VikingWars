/*
 * VIKING WARS
 * CMSC 137 LABORATORY PROJECT
 * 1st Semester AY 2015-2016
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Menu extends JPanel{
	
	public static int barbariansLeft = 100;
	public static int archersLeft = 75;
	public static int wizardsLeft = 25;
	public static int wallBreakersLeft = 20;
	
	public static int toDeploy = 0;
	
	Container container = new Container();
	JPanel poolPanel = new JPanel();
	JPanel unitPanel = new JPanel();
	JPanel defensePanel = new JPanel();
	JPanel logsPanel = new JPanel();
	public static JButton unit[] = new JButton[4];
	public static JLabel barbarianPool = new JLabel("Barbarians Remaining: " + barbariansLeft);
	public static JLabel archerPool = new JLabel("Archers Remaining: " + archersLeft);
	public static JLabel wizardPool = new JLabel("Wizards Remaining: " + wizardsLeft);
	public static JLabel wallBreakerPool = new JLabel("Wall Breaker Remaining: " + wallBreakersLeft);
	public static JLabel unitPool = new JLabel("Unit Pool");
	JLabel legend = new JLabel("Enemy Camp Legend ");
	public Color colorPalette[] = {Color.pink, new Color(148, 0, 215), new Color(252, 231, 88), new Color(116, 186, 185)};
	public Color defenseColorPalette[] = {new Townhall().getColor(), new Wall().getColor(), new Cannon().getColor(), new ArcherTower().getColor()};
	String defenseType[] = {"Townhall", "Wall", "Cannon", "Archer Tower"};
	String unitType[] = {"Barbarian", "Archer", "Wizard", "Wall Breaker"};
	String unitCode[] = {"5", "6", "7", "8"};
	
	public Menu(){
		createStatusPane();
		createUnitPane();
		createLegend();
		setPreferredSize(new Dimension(200, 700));
		setOpaque(false);
		add(unitPool);
		add(poolPanel);
		add(unitPanel);
		add(legend);
		add(defensePanel);
	}
	
	//creates status pane that shows how many units are still available for deployment
	public void createStatusPane(){
		container.add(poolPanel);
		poolPanel.add(barbarianPool);
		poolPanel.add(archerPool);
		poolPanel.add(wizardPool);
		poolPanel.add(wallBreakerPool);
		poolPanel.setLayout(new BoxLayout(poolPanel, BoxLayout.Y_AXIS));
		poolPanel.setPreferredSize(new Dimension(190, 120));
		poolPanel.setBorder(new EmptyBorder(20, 10, 15, 5));
		poolPanel.setBackground(new Color(230,230,250));
		//poolPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		unitPool.setFont(new Font("Calibri", Font.PLAIN, 15));
		unitPool.setBackground(new Color(226,226,243));
		unitPool.setOpaque(true);
		unitPool.setPreferredSize(new Dimension(190,35));
		unitPool.setBorder(new EmptyBorder(10,65,10,10));
		barbarianPool.setFont(new Font("Calibri", Font.PLAIN, 15));
		archerPool.setFont(new Font("Calibri", Font.PLAIN, 15));
		wizardPool.setFont(new Font("Calibri", Font.PLAIN, 15));
		wallBreakerPool.setFont(new Font("Calibri", Font.PLAIN, 15));
	}
	
	//creates the collection of buttons for units to be deployed
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
	
	//creates the legend to show the player the corresponding building of each color in the grid
	public void createLegend(){
		legend.setFont(new Font("Calibri", Font.PLAIN, 15));
		legend.setBackground(new Color(226,226,243));
		legend.setOpaque(true);
		legend.setPreferredSize(new Dimension(190,35));
		legend.setBorder(new EmptyBorder(10,30,10,10));
		defensePanel.setBackground(new Color(230,230,250));
		defensePanel.setPreferredSize(new Dimension(190, 120));
		defensePanel.setLayout(new GridLayout(4, 1));
		for(int i = 0; i < 4; i++){
			JPanel p = new JPanel();
			JPanel temp = new JPanel();
			temp.setPreferredSize(new Dimension(10,10));
			temp.setBackground(defenseColorPalette[i]);
			JLabel label = new JLabel(defenseType[i]);
			label.setFont(new Font("Calibri", Font.PLAIN, 14));
			label.setBackground(defenseColorPalette[i]);
			temp.add(label);
			defensePanel.add(temp);
		}
	}
	
	//function that stores the value of the unit that the player wants to deploy
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
