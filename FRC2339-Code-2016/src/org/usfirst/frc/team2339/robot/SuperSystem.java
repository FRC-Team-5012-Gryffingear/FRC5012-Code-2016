package org.usfirst.frc.team2339.robot;

import org.usfirst.frc.team2339.robot.Drivetrain;
import org.usfirst.frc.team2339.robot.Intake;
import org.usfirst.frc.team2339.robot.Ports;
import org.usfirst.frc.team2339.robot.Shooter;
import edu.wpi.first.wpilibj.Compressor;

public class SuperSystem {
	
	private static SuperSystem instance = null;
	public Drivetrain drive = null;
	public Intake intake = null;
	public Shooter shooter = null;
	public Compressor compressor = null;
	
	private SuperSystem(){
		
		drive = new Drivetrain(Ports.Drivetrain.DRIVE_MOTOR_LEFT_A,
							   Ports.Drivetrain.DRIVE_MOTOR_LEFT_B,
							   Ports.Drivetrain.DRIVE_MOTOR_RIGHT_A,
							   Ports.Drivetrain.DRIVE_MOTOR_RIGHT_B);
		
		intake = new Intake(Ports.Intake.INTAKE_MOTOR, 
						Ports.Intake.INTAKE_SOLENOID);
		
		shooter = new Shooter(Ports.Shooter.SHOOTER_MOTOR_A,
							  Ports.Shooter.SHOOTER_MOTOR_B,
							  Ports.Shooter.SHOOTER_SOLENOID);
		
		compressor = new Compressor(Ports.Pneumatics.PCM_CAN_ID);
		compressor.setClosedLoopControl(true);
		compressor.start();
		
	}
	
	public static SuperSystem getInstance() {

		if (instance == null) {
			instance = new SuperSystem();
		}
		return instance;
	}

}
