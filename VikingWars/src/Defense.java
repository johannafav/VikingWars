import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Defense implements ActionListener{
	
	protected int type;
	protected int x;
	protected int y;
	protected int life;
	protected int damage;
	protected int id;
	protected Color color;
	protected int range;
	protected BuildingAttackTimer buildingAttackTimer;
	protected boolean readyToAttack = false;
	protected Unit enemy;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void attackTroop(Defense attacker, Unit enemy){
		if(attacker.getLife() > 0 && enemy.getLife() > 0){
			enemy.setLife(enemy.getLife() - attacker.getDamage());
			if(enemy.getLife() > 20) Game.square[enemy.getX()][enemy.getY()].setBackground(Color.ORANGE);
			else Game.square[enemy.getX()][enemy.getY()].setBackground(Color.RED);
			System.out.println(attacker.getClass().toString() + " attacked " + enemy.getClass().toString());
			System.out.println(enemy.getClass().toString() + " life: " + enemy.getLife());
			System.out.println(attacker.getClass().toString() + " life: " +attacker.getLife());
		}
		if(attacker.getLife() <= 0){
			Game.square[attacker.getX()][attacker.getY()].setBackground(Color.BLACK);
			Game.remove(attacker.getX(), attacker.getY());
			//enemy.readyToAttack = false;
			//Game.destroyBuilding(attacker);
			System.out.println(attacker.getClass().toString()+" destroyed");
		}
		if(enemy.getLife() <= 0){
			Game.square[enemy.getX()][enemy.getY()].setBackground(Color.BLACK);
			Game.remove(enemy.getX(), enemy.getY());
			attacker.buildingAttackTimer.enemy = null;
			attacker.readyToAttack = false;
			//Game.destroyUnit(enemy);
			System.out.println(enemy.getClass().toString()+" destroyed");
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
	
	public void setColor(Color c){
		this.color = c;
	}
	
	public static Unit findNearestEnemy(Defense d){
		Unit u = null;
		int defenseRange = d.getRange();
		int defenseX = d.getX();
		int defenseY = d.getY();
		double minDistance = 50;
		double temp = 0;
		minDistance = Math.sqrt(Math.pow((defenseX - defenseRange) - d.getX(), 2) + Math.pow((defenseY - defenseRange) - d.getY(), 2));
		for(int i = defenseX - defenseRange; i  < (defenseX + defenseRange) - 1; i++){
			for(int j = defenseY - defenseRange; j < (defenseY + defenseRange) - 1; j++){
				if(Game.field[i][j] > 4){
					temp = Math.sqrt(Math.pow(i - d.getX(), 2) + Math.pow(j - d.getY(), 2));
					if(temp < minDistance){
						minDistance = temp;
						u = locate(i, j);
						d.setEnemy(u);
					}
				}
			}
		}
		return u;
	}
	
	public static Unit locate(int x, int y){
		for(Barbarian b: Game.barbariansDeployed){
			if(b.getX() == x && b.getY() == y) return (Unit) b;
		}
		for(Archer a: Game.archersDeployed){
			if(a.getX() == x && a.getY() == y) return (Unit) a;
		}
		for(Wizard w: Game.wizardsDeployed){
			if(w.getX() == x && w.getY() == y) return (Unit) w;
		}
		for(WallBreaker wb: Game.wallBreakersDeployed){
			if(wb.getX() == x && wb.getY() == y) return (Unit) wb;
		}
		return null;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

}


