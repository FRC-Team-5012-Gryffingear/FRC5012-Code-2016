package org.usfirst.frc.team5012.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class Intake {

	private CANTalon intakeMotor = null;
	
	
	public Intake (int im) {
		intakeMotor = new CANTalon(im);
	}
	
	public void runIntake(double intakev){
		
		intakeMotor.set(-intakev);
		
	}
	
}
