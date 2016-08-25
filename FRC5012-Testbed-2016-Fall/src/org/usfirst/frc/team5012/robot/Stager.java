package org.usfirst.frc.team5012.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class Stager {
	private CANTalon stagerMotorA = null;
	
	
	public Stager (int ma) {
		
		stagerMotorA = new CANTalon(ma);

		
	}
	
	public void runStager(double stagerv){
		
		stagerMotorA.set(stagerv);
	}
}
