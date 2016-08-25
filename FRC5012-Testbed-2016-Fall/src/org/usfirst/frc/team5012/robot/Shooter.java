package org.usfirst.frc.team5012.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class Shooter {

	private CANTalon shooterMotorA = null;
	private CANTalon shooterMotorB = null;
	
	
	public Shooter (int ma, int mb) {
		
		shooterMotorA = configureTalon(new CANTalon(ma));
		shooterMotorB = configureTalon(new CANTalon(mb));

		
	}
	
	private CANTalon configureTalon(CANTalon in) {

		in.clearStickyFaults();
		in.enableBrakeMode(false);
		in.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		in.enableControl();
		System.out.println("[CANTalon]" + in.getDescription() + " Initialized at device ID: " + in.getDeviceID());
		return in;
	}
	
	public void runShooter(double shooterv){
		
		shooterMotorA.set(-shooterv);
		shooterMotorB.set(shooterv);
	}
	 
	
	
}

