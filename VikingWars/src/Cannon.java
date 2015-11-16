/*
 * VIKING WARS
 * CMSC 137 LABORATORY PROJECT
 * 1st Semester AY 2015-2016
 */

import java.awt.Color;

public class Cannon extends Defense{
	
	public Cannon(){
		this.type = 2;
		this.life = 500;
		this.damage = 50;
		this.color = Color.green;
		this.range = 4;
	}

}
