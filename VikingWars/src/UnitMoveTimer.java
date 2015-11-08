import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class UnitMoveTimer implements ActionListener{
		
		Unit attacker;
		Unit enemy;
		Timer timer;

		public UnitMoveTimer(Unit u, Unit e){
			this.attacker = u;
			this.enemy = e;
			this.timer = new Timer(500, this);
			this.timer.start();
		}
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			int target[] = {enemy.getX(), enemy.getY()};
			if(attacker.readyToAttack){
				timer.stop();
				attacker.attackTimer = new AttackTimer(attacker, enemy);
			}
			else{
				attacker.move(attacker, enemy);
			}
		}
		

}
