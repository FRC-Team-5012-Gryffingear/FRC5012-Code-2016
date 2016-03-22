package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;
import com.gryffingear.y2016.config.Ports;
import com.gryffingear.y2016.utilities.PulseTriggerBoolean;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

		intake = new Intake(Ports.Intake.INTAKE_MOTOR, Ports.Intake.INTAKE_SOLENOID, Ports.Intake.STAGE_SENSOR);

		// Shoot? Yes, shoot.
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
	
	boolean shooting = false;

	boolean atSpeed = false;
	
	boolean intakePos = false;
	PulseTriggerBoolean intakeToggle = new PulseTriggerBoolean();
	
	public void magicshot(boolean toggleIntakePos, boolean wantIntake, boolean wantLowGoal, boolean wantHighGoal) {
		
		
		
		double intakeOut = 0.0;
		double shooterOut = 0.0;
		
		
		if(wantLowGoal) {
			intakeOut = Constants.Intake.INTAKE_OUT;
			shooting = false;
		} else if(wantHighGoal) {
			shooterOut = Constants.Shooter.SHOOTING_VOLTAGE;

			atSpeed = shoot.atSpeed();	
			if(atSpeed) {
				shooting = true;
			}
			
			intakeOut = wantIntake ? Constants.Intake.INTAKE_IN : 0.0;
		} else {
			intakeOut = wantIntake ? Constants.Intake.INTAKE_IN : 0.0;
			
			if(intake.getBallStaged()) {
				if(intakeOut > 0.0) intakeOut = 0;
			}
			
			shooting = false;
		}
		shooting = false;
		
		intakeToggle.set(toggleIntakePos);
		
	
		if(intakeToggle.get()) {
			intakePos = !intakePos;
		}
		
		intake.setIntake(intakePos);
		shoot.runShooter(shooterOut);
		intake.runIntake(intakeOut);
		led.setB(intake.getBallStaged());
		led.setA(atSpeed);
	}
	
	public void updateSmartDashboard() {
		SmartDashboard.putNumber("ShooterCurrent", shoot.getCurrent());
		SmartDashboard.putBoolean("AtSpeed", atSpeed);
		SmartDashboard.putBoolean("Shooting", shooting);
//		
	//	SmartDashboard.putBoolean("extBall", intake.getBallEntered());
//		SmartDashboard.putBoolean("intBall", intake.getBallStaged());
		SmartDashboard.putNumber("DriveTotalCurrent", drive.getTotalCurrent());
	}
	
	public void poke() {
		intake.update();
		shoot.update();
	}

}