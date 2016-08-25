package org.usfirst.frc.team2339.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
public class Shooter {

	private Talon shooterMotorA = null;
	private Talon shooterMotorB = null;
	private Solenoid hoodSolenoid = null;
	
	public Shooter (int sma, int smb,int hs){
		
		shooterMotorA = new Talon(sma);
		shooterMotorB = new Talon(smb);
		hoodSolenoid = new Solenoid(hs);
	}
	
	public void setHood(boolean state){
		hoodSolenoid.set(state);
	}
	
	public void runShooter(double shooterv){
		shooterMotorA.set(-shooterv);
		shooterMotorB.set(shooterv);
	}
	
}
