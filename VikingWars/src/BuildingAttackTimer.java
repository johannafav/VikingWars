/*
 * VIKING WARS
 * CMSC 137 LABORATORY PROJECT
 * 1st Semester AY 2015-2016
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class BuildingAttackTimer implements ActionListener{
	
	Defense attacker;
	Unit enemy;
	Timer timer;

	public BuildingAttackTimer(Defense d){
		this.attacker = d;
		this.enemy = null;
		this.timer = new Timer(500, this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(enemy == null) this.enemy = Defense.findNearestEnemy(attacker);
		else{
			if(attacker.getLife() <= 0 || enemy.getLife() <= 0){
				if(attacker.getLife() <= 0) Game.destroyBuilding(attacker);
				else if(enemy.getLife() <= 0){
					Game.destroyUnit(enemy);
					this.enemy = null;
				}
			}
			else{
				attacker.attackTroop(attacker, enemy);
			}
		}
	}
	
}
