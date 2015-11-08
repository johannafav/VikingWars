import java.awt.Color;

import javax.swing.Timer;


public class Unit {

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
	protected Unit enemy;
	
	public void move(Unit u, Unit e){
		int enemyPositionX = e.getX();
		int enemyPositionY = e.getY();
		int prevPositionX = u.getX();
		int prevPositionY = u.getY();
		boolean left = false, right = false, top = false, down= false, inPositionX = false, inPositionY = false;
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
		Game.remove(u.getX(), u.getY());
		Game.deploy(u);
		Game.square[prevPositionX][prevPositionY].setBackground(Color.BLACK);
		Game.square[u.getX()][u.getY()].setBackground(u.getColor());
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void attackEnemy(Unit attacker, Unit enemy){
		while(attacker.getLife() > 0 && enemy.getLife() > 0){
			enemy.setLife(enemy.getLife() - attacker.getDamage());
			System.out.println(attacker.getClass().toString() + " attacked " + enemy.getClass().toString());
			System.out.println("Enemy life: " + enemy.getLife());
			System.out.println("Attacker life: " +attacker.getLife());
		}
		if(attacker.getLife() <= 0){
			Game.square[attacker.getX()][attacker.getY()].setBackground(Color.BLACK);
			Game.remove(enemy.getX(), enemy.getY());
			enemy.readyToAttack = false;
			Game.destroyUnit(enemy);
			System.out.println(attacker.getClass().toString());
		}
		if(enemy.getLife() <= 0){
			Game.square[enemy.getX()][enemy.getY()].setBackground(Color.BLACK);
			Game.remove(enemy.getX(), enemy.getY());
			enemy.readyToAttack = false;
			Game.destroyUnit(enemy);
			System.out.println(enemy.getClass().toString());
		}
	}
	
	public Unit getEnemy(){
		return enemy;
	}
	
	public void setEnemy(Unit e){
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
	
	public void findNearestEnemy(int x, int y){
		
	}
	
}
