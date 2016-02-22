package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;
import com.gryffingear.y2016.config.Ports;

import edu.wpi.first.wpilibj.Compressor;

public class SuperSystem {

	private static SuperSystem instance = null;
	public LedStrips led = null;
	public Drivetrain drive = null;
	public Intake intake = null;
	public Shooter shoot = null;
	public Compressor compressor = null;

	private SuperSystem() {

		drive = new Drivetrain(Ports.Drivetrain.DRIVE_LEFT_A_PORT, Ports.Drivetrain.DRIVE_LEFT_B_PORT,
				Ports.Drivetrain.DRIVE_RIGHT_A_PORT, Ports.Drivetrain.DRIVE_RIGHT_B_PORT);

		led = new LedStrips(Ports.Leds.LED_STRIP_1_PORT, Ports.Leds.LED_STRIP_2_PORT, Ports.Leds.LED_STRIP_3_PORT,
				Ports.Leds.LED_STRIP_4_PORT);

		intake = new Intake(Ports.Intake.INTAKE_MOTOR, Ports.Intake.INTAKE_SOLENOID, Ports.Intake.OUTER_STAGE_SENSOR,
				Ports.Intake.INNER_STAGE_SENSOR);

		shoot = new Shooter(Ports.Shooter.SHOOTER_MOTOR_A, Ports.Shooter.SHOOTER_MOTOR_B);

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
	
	public void drive(double leftIn, double rightIn) {
		drive.tankDrive(leftIn, rightIn);
	}
	
	
	public void magicshot(boolean toggleIntakePos, boolean wantIntake, boolean wantLowGoal, boolean wantHighGoal) {
		
		double intakeOut = 0.0;
		double shooterOut = 0.0;
		
		if(wantLowGoal) {
			intakeOut = Constants.Intake.INTAKE_OUT;
		} else if(wantHighGoal) {
			shooterOut = Constants.Shooter.SHOOTING_VOLTAGE;
			
			if(shoot.atSpeed()) {
				intakeOut = Constants.Intake.INTAKE_IN;
			}
		} else {
			intakeOut = wantIntake ? Constants.Intake.INTAKE_IN : 0;
			
			if(intake.getBallStaged()) {
				intakeOut = 0.0;
			}
			
		}
		
		led.setA(intake.getBallStaged());
		led.setB(shoot.atSpeed());
	}

}