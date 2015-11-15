import java.awt.Color;

import javax.swing.Timer;


public class Unit{

	protected int type;
	protected int x;
	protected int y;
	protected int life;
	protected int damage;
	protected Color color;
	protected int range;
	protected UnitMoveTimer moveTimer;
	protected AttackTimer attackTimer;
	protected boolean readyToAttack = false;
	protected boolean foundEnemy = false;
	protected Defense enemy = null;
	
	//moves the troop
	public void move(Unit u){
		Defense e = u.getEnemy();
		int enemyPositionX = e.getX();
		int enemyPositionY = e.getY();
		int prevPositionX = u.getX();
		int prevPositionY = u.getY();
		if(prevPositionY < enemyPositionY-1 && !Game.checkIfOccupied(prevPositionX, prevPositionY+1)){
			u.setY(prevPositionY+1);
		}
		else if(prevPositionY > enemyPositionY+1 && !Game.checkIfOccupied(prevPositionX, prevPositionY-1)){
			u.setY(prevPositionY-1);
		}
		if(prevPositionX < enemyPositionX-1 && !Game.checkIfOccupied(prevPositionX+1, prevPositionY)){
			u.setX(prevPositionX+1);
		}
		else if(prevPositionX > enemyPositionX+1 && !Game.checkIfOccupied(prevPositionX-1, prevPositionY)){
			u.setX(prevPositionX-1);
		}
		if(prevPositionX == enemyPositionX-1 || prevPositionX == enemyPositionX+1 || prevPositionX == enemyPositionX){
			if(prevPositionY == enemyPositionY-1 || prevPositionY == enemyPositionY+1 || prevPositionY == enemyPositionY){
				u.readyToAttack = true;
				return;
			}
			if(prevPositionY < enemyPositionY-1 && !Game.checkIfOccupied(prevPositionX, prevPositionY+1)){
				u.setY(prevPositionY+1);
			}
			if(prevPositionY > enemyPositionY+1 && !Game.checkIfOccupied(prevPositionX, prevPositionY-1)){
				u.setY(prevPositionY-1);
			}
		}
		if(prevPositionY == enemyPositionY-1 || prevPositionY == enemyPositionY+1 || prevPositionY == enemyPositionY){
			if(prevPositionX == enemyPositionX-1 || prevPositionX == enemyPositionX+1 || prevPositionX == enemyPositionX){
				u.readyToAttack = true;
				return;
			}
			if(prevPositionX < enemyPositionX && !Game.checkIfOccupied(prevPositionX+1, prevPositionY)){
				u.setX(prevPositionX+1);
			}
			if(prevPositionX > enemyPositionX && !Game.checkIfOccupied(prevPositionX-1, prevPositionY)){
				u.setX(prevPositionX-1);
			}
		}
		Game.remove(prevPositionX, prevPositionY);
		Game.deploy(u);
		//Game.square[prevPositionX][prevPositionY].setBackground(Color.BLACK);
		//Game.square[u.getX()][u.getY()].setBackground(u.getColor());
	}

	//attacks the enemy
	public void attackEnemy(Unit attacker, Defense enemy){
		if(attacker.type == 8) attacker.setDamage(enemy);
		if(attacker.getLife() > 0 && enemy.getLife() > 0){
			enemy.setLife(enemy.getLife() - attacker.getDamage());
			if(enemy.getLife() <= enemy.getLife()/4) Game.square[enemy.getX()][enemy.getY()].setBackground(Color.RED);
			else Game.square[enemy.getX()][enemy.getY()].setBackground(Color.ORANGE);
			System.out.println(attacker.getClass().toString() + " attacked " + enemy.getClass().toString());
			System.out.println(enemy.getClass().toString() + " life: " + enemy.getLife());
			System.out.println(attacker.getClass().toString() + " life: " +attacker.getLife());
		}
		if(attacker.getLife() <= 0){
			Game.remove(attacker.getX(), attacker.getY());
			attacker.attackTimer.timer.stop();
			Game.destroyUnit(attacker);
			System.out.println(attacker.getClass().toString()+" destroyed");
		}
		if(enemy.getLife() <= 0){
			Game.remove(enemy.getX(), enemy.getY());
			if(attacker != null){
				attacker.readyToAttack = false;
				attacker.attackTimer.enemy = null;
			}
			Game.destroyBuilding(enemy);
			System.out.println(enemy.getClass().toString()+" destroyed");
		}
	}
	
	public void setDamage(Defense d){
		if(d.type == 3) this.damage = 100;
		else this.damage = 25;
	}
	
	public Defense getEnemy(){
		return enemy;
	}
	
	public void setEnemy(Defense e){
		this.enemy = e;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getDamage() {
		return damage;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
