/*
 * VIKING WARS
 * CMSC 137 LABORATORY PROJECT
 * 1st Semester AY 2015-2016
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Game extends JPanel implements Constants, Runnable{
	
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
	static int field[][] = new int[25][25];
	
	static ArrayList<Barbarian> barbariansDeployed = new ArrayList<Barbarian>();
	static ArrayList<Archer> archersDeployed = new ArrayList<Archer>();
	static ArrayList<Wizard> wizardsDeployed = new ArrayList<Wizard>();
	static ArrayList<WallBreaker> wallBreakersDeployed = new ArrayList<WallBreaker>();
	static ArrayList<Cannon> cannonsDeployed = new ArrayList<Cannon>();
	static ArrayList<Wall> wallsDeployed = new ArrayList<Wall>();
	static ArrayList<ArcherTower> archerTowersDeployed = new ArrayList<ArcherTower>();
	
	JPanel panel = new JPanel();
	static JLabel status = new JLabel();
	static int gameState;
	static String playerName = Main.thisPlayer;
	static Townhall t;
	int port = 6400;
	protected DatagramSocket socket = new DatagramSocket();
	private String host;
	String serverData;
	boolean connected=false;
	Thread th =new Thread(this);
	
	public Game() throws SocketException{
		this.setLayout(new GridLayout(25, 25, 1, 1));
		this.setPreferredSize(new Dimension(700, 700));
		this.setBackground(Color.WHITE);
		setBorder(new EmptyBorder(1,1,1,1));
		status.setBackground(new Color(226,226,243));
		status.setPreferredSize(new Dimension(50,50));
		this.panel = this;
		this.host = Main.host;
		this.port = port;
		socket.setSoTimeout(100);
		th.start();
		createGameBoard();
		readFile();
	}
	
	//creates the 25x25 grid of panels that serve as the playing field
	public void createGameBoard(){
		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25; j++){
				square[i][j] = new JButton();
				square[i][j].setBackground(Color.black);
				square[i][j].setEnabled(false);
				square[i][j].setVisible(false);
				addListener(square[i][j]);
				panel.add(square[i][j]);
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
									deploy(b);
									btn.setBackground(b.getColor());
									barbariansDeployed.add(b);
									Menu.barbariansLeft--;
									//Menu.barbarianPool.setText("Barbarians Remaining: " + Menu.barbariansLeft);
									send("PLAYER "+playerName+" "+b.getType()+" "+Menu.barbariansLeft);
									b.setEnemy(getClosestEnemy(b));
									b.moveTimer = new UnitMoveTimer(b/*, b.getEnemy()*/);
								}
								else Menu.unit[0].setEnabled(false);
								break;
						case 6:	if(Menu.archersLeft != 0){
									Archer a = new Archer();
									a.setX(i);
									a.setY(j);
									deploy(a);
									btn.setBackground(a.getColor());
									archersDeployed.add(a);
									Menu.archersLeft--;
									send("PLAYER "+playerName+" "+a.getType()+" "+Menu.archersLeft);
									//Menu.archerPool.setText("Archers Remaining: " + Menu.archersLeft);
									a.setEnemy(getClosestEnemy(a));
									a.moveTimer = new UnitMoveTimer(a);
									//System.out.println("Coordinates:"+i+" "+j);
								}
								else Menu.unit[1].setEnabled(false);
								break;
						case 7:	if(Menu.wizardsLeft != 0){
									Wizard w = new Wizard();
									w.setX(i);
									w.setY(j);
									deploy(w);
									btn.setBackground(w.getColor());
									wizardsDeployed.add(w);
									Menu.wizardsLeft--;
									send("PLAYER "+playerName+" "+w.getType()+" "+Menu.wizardsLeft);
									//Menu.wizardPool.setText("Wizards Remaining: " + Menu.wizardsLeft);
									w.setEnemy(getClosestEnemy(w));
									w.moveTimer = new UnitMoveTimer(w);
								}
								else Menu.unit[2].setEnabled(false);
								break;
						case 8:	if(Menu.wallBreakersLeft != 0){
									WallBreaker wb = new WallBreaker();
									wb.setX(i);
									wb.setY(j);
									deploy(wb);
									btn.setBackground(wb.getColor());
									wallBreakersDeployed.add(wb);
									Menu.wallBreakersLeft--;
									send("PLAYER "+playerName+" "+wb.getType()+" "+Menu.wallBreakersLeft);
									//Menu.wallBreakerPool.setText("Wall Breakers Remaining: " + Menu.wallBreakersLeft);
									wb.setEnemy(getClosestEnemy(wb));
									wb.moveTimer = new UnitMoveTimer(wb);
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
	public static Defense getClosestEnemy(Unit u){
		
		int x = u.getX();
		int y = u.getY();
		double minDistance = 1500;
		double distance = 0;
		Defense enemy = new Defense();
		
		//get the troop's distance from the town hall
		distance = Math.sqrt(Math.pow(t.getX()-x, 2) + Math.pow(t.getY() - y, 2));
		if(distance < minDistance){
			minDistance = distance;
			enemy = (Defense) t;
		}
		//get the troop's distance from all the cannons
		for(Cannon c: cannonsDeployed){
			distance = Math.sqrt(Math.pow(c.getX()-x, 2) + Math.pow(c.getY() - y, 2));
			if(distance < minDistance){
				minDistance = distance;
				enemy = (Defense) c;
			}
		}
		//get the troop's distance from all the walls
		for(Wall w: wallsDeployed){
			distance = Math.sqrt(Math.pow(w.getX()-x, 2) + Math.pow(w.getY() - y, 2));
			if(distance < minDistance){
				minDistance = distance;
				enemy = (Defense) w;
			}
		}
		//get the troop's distance from all the archer towers
		for(ArcherTower at: archerTowersDeployed){
			distance = Math.sqrt(Math.pow(at.getX()-x, 2) + Math.pow(at.getY() - y, 2));
			if(distance < minDistance){
				minDistance = distance;
				enemy = (Defense) at;
			}
		}
		
		System.out.println("B: "+enemy.getX()+" "+enemy.getY() +"\n");
		return enemy;
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
						case 1:	if(hasTownhall == false){
									t = new Townhall();
									t.setX(lineNum);
									t.setY(i);
									hasTownhall = true;
								}
								square[lineNum][i].setBackground(new Color(0,128,128));
								square[lineNum][i].setEnabled(false);
								field[lineNum][i] = 1;
								break;
						case 2: Cannon c = new Cannon();
								c.buildingAttackTimer = new BuildingAttackTimer(c);
								c.setX(lineNum);
								c.setY(i);
								square[lineNum][i].setBackground(c.getColor());
								square[lineNum][i].setEnabled(false);
								field[lineNum][i] = 2;
								cannonsDeployed.add(c);
								break;
						case 3: Wall w = new Wall();
								w.setX(lineNum);
								w.setY(i);
								square[lineNum][i].setBackground(w.getColor());
								square[lineNum][i].setEnabled(false);
								field[lineNum][i] = 3;
								wallsDeployed.add(w);
								break;
						case 4: ArcherTower at = new ArcherTower();
								at.buildingAttackTimer = new BuildingAttackTimer(at);
								at.setX(lineNum);
								at.setY(i);
								square[lineNum][i].setBackground(at.getColor());
								square[lineNum][i].setEnabled(false);
								field[lineNum][i] = 4;
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
	
	public void createLog(){
		
	}
	
	//called when an enemy's building is destroyed
	//that building is removed from the list of functional buildings
	public static void destroyBuilding(Defense d){
		switch(d.getType()){
			//the first player to destroy an opponent's town hall is the winner
			case 1: //gameState = GAME_END;
					stop();
					Main.game.send("WINNER "+playerName);
					break;
			case 2: cannonsDeployed.remove(d);
					break;
			case 3: wallsDeployed.remove(d);
					break;
			case 4: archerTowersDeployed.remove(d);
					break;
		}
	}
	
	//called when an troop/unit is destroyed
	//that unit is removed from the list of deployed troops
	public static void destroyUnit(Unit u){
		switch(u.getType()){
			case 5: barbariansDeployed.remove(u);
					break;
			case 6: archersDeployed.remove(u);
					break;
			case 7: wizardsDeployed.remove(u);
					break;
			case 8: wallBreakersDeployed.remove(u);
					break;
		}
	}
	
	//called when there is a winner
	public static void stop(){
		//stops all deployed troops from moving or attacking
		for(Barbarian b: barbariansDeployed){
			b.moveTimer.timer.stop();
			b.attackTimer.timer.stop();
		}
		for(Archer a: archersDeployed){
			a.moveTimer.timer.stop();
			a.attackTimer.timer.stop();
		}
		for(Wizard w: wizardsDeployed){
			w.moveTimer.timer.stop();
			w.attackTimer.timer.stop();
		}
		for(WallBreaker wb: wallBreakersDeployed){
			wb.moveTimer.timer.stop();
			wb.attackTimer.timer.stop();
		}
		//stops all defenses from attacking
		for(ArcherTower at:archerTowersDeployed){
			at.buildingAttackTimer.timer.stop();
		}
		for(Cannon c: cannonsDeployed){
			c.buildingAttackTimer.timer.stop();
		}
		//disables the game board
		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25; j++){
				square[i][j].setEnabled(false);
			}
		}
		//disables the pool of units
		for(int i = 0; i < 4; i++){
			Menu.unit[i].setEnabled(false);
		}
	}
	
	//lay down a troop in the field
	public static void deploy(Unit u){
		field[u.getX()][u.getY()] = u.getType();
		Game.square[u.getX()][u.getY()].setBackground(u.getColor());
	}
	
	//remove a troop/building from a field
	public static void remove(int x, int y){
		field[x][y] = 0;
		Game.square[x][y].setBackground(Color.BLACK);
	}
	
	//function that returns true if the particular coordinates are occupied and false if not
	public static boolean checkIfOccupied(int x, int y){
		if(field[x][y] != 0) return true;
		else return false;
	}
	
	/**
	 * Helper method for sending data to server
	 * @param msg
	 */
	public void send(String msg){
		try{
			byte[] buf = msg.getBytes();
        	InetAddress address = InetAddress.getByName(host);
        	DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
        	socket.send(packet);
        }catch(Exception e){}
		
	}
	
	/**
	 * The juicy part!
	 */
	public void run(){
		while(true){
			try{
				Thread.sleep(1);
			}catch(Exception ioe){}
						
			//Get the data from players
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			try{
     			socket.receive(packet);
			}catch(Exception ioe){/*lazy exception handling :)*/}
			
			serverData=new String(buf);
			serverData=serverData.trim();
			
			//if (!serverData.equals("")){
			//	System.out.println("Server Data:" +serverData);
			//}

			//if player is not yet connected 
			if (!connected && serverData.startsWith("CONNECTED")){
				connected=true;
				send("WAITING"+Main.thisPlayer);
				Menu.statusLabel.setText("Waiting for players...");
				System.out.println("Connected.");
			}else if (!connected){
				System.out.println("Connecting..");
				send("CONNECT "+Main.thisPlayer);
			}else if (connected){
				//if there are still insufficient number of players
				if(serverData.startsWith("START")){
					Menu.statusLabel.setText("Game in progress");
					for(int i = 0; i < 25; i++){
						for(int j = 0; j < 25; j++){
							square[i][j].setVisible(true);
							square[i][j].setEnabled(true);
						}
					}
					repaint();
				}
				//if a player deploys a unit, update the counter corresponding to that unit
				if(serverData.startsWith("PLAYER")){
					System.out.println("Server Data:" +serverData);
					String[] playersInfo = serverData.split(":");
					System.out.println(playersInfo.length);
					for (int i=0;i<playersInfo.length;i++){
						String[] playerInfo = playersInfo[i].split(" ");
						String pname =playerInfo[1];
						int x = Integer.parseInt(playerInfo[2]);
						int y = Integer.parseInt(playerInfo[3]);
						switch(x){
							case 5: Menu.barbarianPool.setText("Barbarians Remaining: " + y);
									if(y == 0) Menu.unit[0].setEnabled(false);
									break;
							case 6: Menu.archerPool.setText("Archers Remaining: " + y);
									if(y == 0) Menu.unit[1].setEnabled(false);
									break;
							case 7: Menu.wizardPool.setText("Wizards Remaining: " + y);
									if(y == 0) Menu.unit[2].setEnabled(false);
									break;
							case 8: Menu.wallBreakerPool.setText("Wall Breakers Remaining: " + y);
									if(y == 0) Menu.unit[3].setEnabled(false);
									break;
						}
					}
				}
				//if a player has already won, stop the game
				if(serverData.startsWith("WINNER")){
					stop();
					String[] info = serverData.split(" ");
					Menu.statusLabel.setText("GAME OVER");
					//String[] playersInfo = serverData.split(":");
					/*for (int i=0;i<playersInfo.length;i++){
						String[] playerInfo = playersInfo[i].split(" ");
						String winner = playerInfo[1];*/
						//stop();
					if(info[1].equals(playerName)) JOptionPane.showMessageDialog(square[5][5], "You won!");
					if(!info[1].equals(playerName)) JOptionPane.showMessageDialog(square[5][5], info[1] + " won!");
					//}
					//JOptionPane.showMessageDialog(square[5][5], info[1] + " won!");
				}
			}			
		}
	}
	
}
