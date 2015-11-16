/*
 * VIKING WARS
 * CMSC 137 LABORATORY PROJECT
 * 1st Semester AY 2015-2016
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class AttackTimer implements ActionListener{
	
	Unit attacker = null;
	Defense enemy = null;
	Timer timer = new Timer(850, this);

	public AttackTimer(Unit u){
		this.attacker = u;
		this.enemy = u.getEnemy();
		this.timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(enemy == null){
			timer.stop();
			attacker.setEnemy(Game.getClosestEnemy(attacker));
			attacker.moveTimer = new UnitMoveTimer(attacker);
		}
		else{
			attacker.attackEnemy(attacker, enemy);
		}
	}
	
}
