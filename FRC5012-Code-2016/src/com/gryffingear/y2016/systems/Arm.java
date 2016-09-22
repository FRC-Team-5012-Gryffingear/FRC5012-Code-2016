package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Ports;
import edu.wpi.first.wpilibj.Solenoid;

public class Arm {

	private Solenoid armSolenoid = null;
	
	public Arm(int as){
		armSolenoid = new Solenoid(as);
	}
	
	public void set(boolean state){
		
		armSolenoid.set(state);
	}
	
	
}
