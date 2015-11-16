/*
 * VIKING WARS
 * CMSC 137 LABORATORY PROJECT
 * 1st Semester AY 2015-2016
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class UnitMoveTimer implements ActionListener{
		
		Unit attacker = null;
		Defense enemy = null;
		Timer timer = new Timer(500, this);

		public UnitMoveTimer(Unit u){
			this.attacker = u;
			this.enemy = u.enemy;
			this.timer.start();
		}
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			if(attacker.readyToAttack){
				timer.stop();
				attacker.attackTimer = new AttackTimer(attacker);
			}
			else{
				attacker.setEnemy(Game.getClosestEnemy(attacker));
				attacker.move(attacker);
			}
		}
		

}
