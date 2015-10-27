import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Game extends JPanel{
	
	/*
	 * Codes for Units:
	 * 1: Town Hall
	 * 2: Cannon
	 * 3: Wall
	 * 4: Archer Tower
	 * 5: Barbarian
	 * 6: Archer
	 * 7: Wizard
	 * 8: Wall Breaker
	 */

	static JButton square[][] = new JButton[25][25];
	int field[][] = new int[25][25];
	
	ArrayList<Barbarian> barbariansDeployed = new ArrayList<Barbarian>();
	ArrayList<Archer> archersDeployed = new ArrayList<Archer>();
	ArrayList<Wizard> wizardsDeployed = new ArrayList<Wizard>();
	ArrayList<WallBreaker> wallBreakersDeployed = new ArrayList<WallBreaker>();
	ArrayList<Cannon> cannonsDeployed = new ArrayList<Cannon>();
	ArrayList<Wall> wallsDeployed = new ArrayList<Wall>();
	ArrayList<ArcherTower> archerTowersDeployed = new ArrayList<ArcherTower>();
	
	Townhall t;
	
	public Game(){
		Main.gameState = Main.IN_PROGRESS;
		this.setLayout(new GridLayout(25, 25, 1, 1));
		this.setPreferredSize(new Dimension(700, 700));
		this.setBackground(Color.WHITE);
		createGameBoard();
		readFile();
		this.setBorder(new EmptyBorder(1,1,1,1));
		//move(5, 3);
	}
	
	//creates the 25x25 grid of panels that serve as the playing field
	public void createGameBoard(){
		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25; j++){
				square[i][j] = new JButton();
				square[i][j].setBackground(Color.black);
				addListener(square[i][j]);
				this.add(square[i][j]);
				field[i][j] = 0;
			}
		}
	}
	
	//specifies what would happen when a tile in the playing field is clicked
	private void addListener(JButton button){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int i = 0, j = 0;
				boolean flag = false;
				if(e.getSource() == button){
					JButton btn = (JButton) e.getSource();
					//look for the coordinates of the clicked button
					for(i = 0; i < 25; i++){
						for(j = 0; j < 25; j++){
							if(btn == square[i][j]){
								flag = true;
								break;
							}
						}
						if(flag) break;
					}
					//deploy the unit the player has chosen
					switch(Menu.toDeploy){
						case 5:	if(Menu.barbariansLeft != 0){
									Barbarian b = new Barbarian();
									b.setX(i);
									b.setY(j);
									btn.setBackground(b.getColor());
									barbariansDeployed.add(b);
									Menu.barbariansLeft--;
									Menu.barbarianPool.setText("Barbarians Remaining: " + Menu.barbariansLeft);
									b.setEnemy(getClosestEnemy(b));
									b.moveTimer = new UnitMoveTimer(b, b.getEnemy());
									b.attackTimer = new AttackTimer(b, b.getEnemy());
								}
								else Menu.unit[0].setEnabled(false);
								break;
						case 6:	if(Menu.archersLeft != 0){
									Archer a = new Archer();
									a.setX(i);
									a.setY(j);
									btn.setBackground(a.getColor());
									archersDeployed.add(a);
									Menu.archersLeft--;
									Menu.archerPool.setText("Archers Remaining: " + Menu.archersLeft);
									a.setEnemy(getClosestEnemy(a));
									a.moveTimer = new UnitMoveTimer(a, a.getEnemy());
									//System.out.println("Coordinates:"+i+" "+j);
								}
								else Menu.unit[1].setEnabled(false);
								break;
						case 7:	if(Menu.wizardsLeft != 0){
									Wizard w = new Wizard();
									w.setX(i);
									w.setY(j);
									btn.setBackground(w.getColor());
									wizardsDeployed.add(w);
									Menu.wizardsLeft--;
									Menu.wizardPool.setText("Wizards Remaining: " + Menu.wizardsLeft);
									w.setEnemy(getClosestEnemy(w));
									w.moveTimer = new UnitMoveTimer(w, w.getEnemy());
								}
								else Menu.unit[2].setEnabled(false);
								break;
						case 8:	if(Menu.wallBreakersLeft != 0){
									WallBreaker wb = new WallBreaker();
									wb.setX(i);
									wb.setY(j);
									btn.setBackground(wb.getColor());
									wallBreakersDeployed.add(wb);
									Menu.wallBreakersLeft--;
									Menu.wallBreakerPool.setText("Wall Breakers Remaining: " + Menu.wallBreakersLeft);
									wb.setEnemy(getClosestEnemy(wb));
									wb.moveTimer = new UnitMoveTimer(wb, wb.getEnemy());
								}
								else Menu.unit[3].setEnabled(false);
								break;
						default: JOptionPane.showMessageDialog(btn, "Select a unit to deploy!");
								 break;
					}
				}
			}
		});
	}
	
	//finds the enemy unit nearest to the deployed unit
	public Unit getClosestEnemy(Unit u){
		
		int x = u.getX();
		int y = u.getY();
		int target[] = {0, 0};
		double minDistance = 50;
		double distance = 0;
		Unit enemy = new Unit();
		
		//get the troop's distance from the town hall
		distance = Math.sqrt(Math.pow(t.getX()-x, 2) + Math.pow(t.getY() - y, 2));
		if(distance < minDistance){
			minDistance = distance;
			target[0] = t.getX();
			target[1] = t.getY();
			enemy = (Unit) t;
		}
		//get the troop's distance from all the cannons
		for(Cannon c: cannonsDeployed){
			distance = Math.sqrt(Math.pow(c.getX()-x, 2) + Math.pow(c.getY() - y, 2));
			if(distance < minDistance){
				minDistance = distance;
				target[0] = c.getX();
				target[1] = c.getY();
				enemy = (Unit) c;
			}
		}
		//get the troop's distance from all the walls
		for(Wall w: wallsDeployed){
			distance = Math.sqrt(Math.pow(w.getX()-x, 2) + Math.pow(w.getY() - y, 2));
			if(distance < minDistance){
				minDistance = distance;
				target[0] = w.getX();
				target[1] = w.getY();
				enemy = (Unit) w;
			}
		}
		//get the troop's distance from all the archer towers
		for(ArcherTower at: archerTowersDeployed){
			distance = Math.sqrt(Math.pow(at.getX()-x, 2) + Math.pow(at.getY() - y, 2));
			if(distance < minDistance){
				minDistance = distance;
				target[0] = at.getX();
				target[1] = at.getY();
				enemy = (Unit) at;
			}
		}
		
		System.out.println("A: "+target[0]+" "+target[1]);
		System.out.println("B: "+enemy.getX()+" "+enemy.getY() +"\n");
		return enemy;
		//move(u, target);
		//attackEnemy(u, enemy);
	}
	
	//reads the file that contains the layout of the enemy's camp
	public void readFile(){
		Path file = Paths.get("map.txt");
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
			int lineNum = 0;
			String line = null;
			boolean hasTownhall = false;
			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split("\\s+");
				for(int i = 0; i < 25; i++){
					switch(Integer.parseInt(tokens[i])){
						//case -1: square[lineNum][i].setEnabled(false);
						case 1:	if(hasTownhall == false){
									t = new Townhall();
									t.setX(lineNum);
									t.setY(i);
									hasTownhall = true;
								}
								square[lineNum][i].setBackground(Color.DARK_GRAY);
								square[lineNum][i].setEnabled(false);
								break;
						case 2: Cannon c = new Cannon();
								c.setX(lineNum);
								c.setY(i);
								square[lineNum][i].setBackground(c.getColor());
								square[lineNum][i].setEnabled(false);
								cannonsDeployed.add(c);
								break;
						case 3: Wall w = new Wall();
								w.setX(lineNum);
								w.setY(i);
								square[lineNum][i].setBackground(w.getColor());
								square[lineNum][i].setEnabled(false);
								wallsDeployed.add(w);
								break;
						case 4: ArcherTower at = new ArcherTower();
								at.setX(lineNum);
								at.setY(i);
								square[lineNum][i].setBackground(at.getColor());
								square[lineNum][i].setEnabled(false);
								archerTowersDeployed.add(at);
								break;
					}
				}
				lineNum++;
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}
	
}
