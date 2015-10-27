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
		if(prevPositionX < enemyPositionX) left = true;
		else right = true;
		if(prevPositionY < enemyPositionY) top = true;
		else down = true;
		if((left && prevPositionX == enemyPositionX-1) || (right && prevPositionX == enemyPositionX+1)){
			if((top && prevPositionY == enemyPositionY-1) || (down && prevPositionY == enemyPositionY+1)) u.readyToAttack = true;
		}
		if(prevPositionX == enemyPositionX){
			if(top && prevPositionY < enemyPositionY-1) u.setY(prevPositionY+1);
			if(down && prevPositionY > enemyPositionY+1) u.setY(prevPositionY-1);
		}
		else if(prevPositionY == enemyPositionY){
			if(left && prevPositionX < enemyPositionX-1) u.setX(prevPositionX+1);
			if(right && prevPositionX > enemyPositionX+1) u.setX(prevPositionX-1);
		}
		else if(left){
			if(prevPositionX < enemyPositionX-1) u.setX(prevPositionX+1);
		}
		else if(right){
			if(prevPositionX > enemyPositionX+1) u.setX(prevPositionX-1);
		}
		if(top){
			if(prevPositionY < enemyPositionY-1) u.setX(prevPositionY+1);
		}
		else if(down){
			if(prevPositionY > enemyPositionY+1) u.setX(prevPositionY-1);
		}
		Game.square[prevPositionX][prevPositionY].setBackground(Color.BLACK);
		Game.square[u.getX()][u.getY()].setBackground(u.getColor());
	}
	
	public void attackEnemy(Unit attacker, Unit enemy){
		while(attacker.getLife() != 0 || enemy.getLife() != 0){
			if(attacker.getLife() == 0){
				Game.square[attacker.getX()][attacker.getY()].setBackground(Color.BLACK);
				System.out.println(attacker.getClass().toString());
			}
			if(enemy.getLife() == 0){
				Game.square[enemy.getX()][enemy.getY()].setBackground(Color.BLACK);
				System.out.println(enemy.getClass().toString());
			}
			enemy.setLife(enemy.getLife() - attacker.getDamage());
			System.out.println(attacker.getClass().toString() + " attacked " + enemy.getClass().toString());
			System.out.println("Enemy life: " + enemy.getLife());
			System.out.println("Attacker life: " +attacker.getLife());
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
