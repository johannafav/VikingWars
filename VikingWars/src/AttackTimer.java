import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class AttackTimer implements ActionListener{
	
	Unit attacker;
	Unit enemy;
	Timer timer;

	public AttackTimer(Unit u, Unit e){
		this.attacker = u;
		this.enemy = e;
		this.timer = new Timer(1000, this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(attacker.getLife() <= 0 || enemy.getLife() <= 0){
			timer.stop();
			//attacker.moveTimer.timer.start();
		}
		else{
			attacker.attackEnemy(attacker, enemy);
		}
	}
	
}
