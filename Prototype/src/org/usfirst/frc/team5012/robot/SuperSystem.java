package org.usfirst.frc.team5012.robot;

import org.usfirst.frc.team5012.robot.Ports;

public class SuperSystem {

	public static SuperSystem instance = null;
	public Drivetrain drive = null;
	public Shooter shoot = null;
	public Intake intake = null;

	private SuperSystem() {

		drive = new Drivetrain(Ports.Drivetrain.DRIVE_LEFT_A_PORT, 
								Ports.Drivetrain.DRIVE_LEFT_B_PORT,
								Ports.Drivetrain.DRIVE_RIGHT_A_PORT, 
								Ports.Drivetrain.DRIVE_RIGHT_B_PORT);

		shoot = new Shooter(Ports.Shooter.SHOOTER, 
							Ports.Shooter.STAGER);
		
		intake = new Intake(Ports.Intake.INTAKE);

	}

	public static SuperSystem getInstance() {

		if (instance == null) {
			instance = new SuperSystem();
		}
		return instance;
	}
}
