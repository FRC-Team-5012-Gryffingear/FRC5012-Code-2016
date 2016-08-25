package org.usfirst.frc.team5012.robot;

import org.usfirst.frc.team5012.robot.Ports;


public class SuperSystem {

	private static SuperSystem instance = null;
	public Shooter shoot = null;
	public Stager stage = null;
	public Intake intake = null;
	
	private SuperSystem() {
		
		shoot = new Shooter(Ports.Shooter.SHOOTER_MOTOR_A, Ports.Shooter.SHOOTER_MOTOR_B);
		stage = new Stager(Ports.Stager.STAGER_MOTOR);
		intake = new Intake(Ports.Intake.INTAKE_MOTOR);
		
	}
	
	
	public static SuperSystem getInstance() {

		if (instance == null) {
			instance = new SuperSystem();
		}
		return instance;
	}


	public void shoot(double shooterSpeed){
		shoot.runShooter(shooterSpeed);
	}
	
	public void stage(double stageSpeed){
		stage.runStager(stageSpeed);
	}
	
}
