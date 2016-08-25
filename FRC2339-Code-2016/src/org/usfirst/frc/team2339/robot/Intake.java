package org.usfirst.frc.team2339.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake {

	private Talon intakeMotor = null;
	private Solenoid intakeSolenoid = null;
	
	public Intake (int im, int is){
		
		intakeMotor = new Talon(im);
		intakeSolenoid = new Solenoid(is);
	}
	
	public void setIntake(boolean state){
		intakeSolenoid.set(state);
	}
	
	public void runIntake(double intakev){
		intakeMotor.set(-intakev);
	}
	
}
