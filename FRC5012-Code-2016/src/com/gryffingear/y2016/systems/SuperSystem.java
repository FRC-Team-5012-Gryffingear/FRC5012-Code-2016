package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;
import com.gryffingear.y2016.config.Ports;
import com.gryffingear.y2016.utilities.Looper;
import com.gryffingear.y2016.utilities.NegativeInertiaAccumulator;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SuperSystem {

	private static SuperSystem instance = null;
	public LedStrips led = null;
	public Drivetrain drive = null;
	public Intake intake = null;
	public Shooter shoot = null;
	public Compressor compressor = null;
	public Climber climb = null;
	public Stager stage = null;
	public AnalogInput pixycam = null;
	private Looper shooterSpeedLooper = null;
	// Todo: make pixy class.

	private SuperSystem() {

		drive = new Drivetrain(	Ports.Drivetrain.DRIVE_LEFT_A_PORT, 
								Ports.Drivetrain.DRIVE_LEFT_B_PORT,
								Ports.Drivetrain.DRIVE_RIGHT_A_PORT, 
								Ports.Drivetrain.DRIVE_RIGHT_B_PORT);

		led = new LedStrips(Ports.Leds.LED_STRIP_1_PORT, 
							Ports.Leds.LED_STRIP_2_PORT, 
							Ports.Leds.LED_STRIP_3_PORT);

		intake = new Intake(Ports.Intake.INTAKE_MOTOR, 
							Ports.Intake.INTAKE_SOLENOID, 
							Ports.Intake.STAGE_SENSOR);
		
		// Shoot? Yes, shoot.
		shoot = new Shooter(Ports.Shooter.SHOOTER_MOTOR_A, 
							Ports.Shooter.SHOOTER_MOTOR_B, 
							Ports.Shooter.HOOD_SOLENOID, 
							Ports.Shooter.SHOOTER_ENCODER_PORT);

		climb = new Climber(Ports.Climber.CLIMBER_SOLENOID, 
							Ports.Climber.CLIMBER_MOTOR);
		
		stage = new Stager (Ports.Stager.STAGER_MOTOR);
		
		pixycam = new AnalogInput(Ports.Pixycam.PIXYCAM_PORT);
		
		shooterSpeedLooper =  new Looper("ShooterSpeedLooper", shoot, 0.01);
		shooterSpeedLooper.start();
		
		
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

	
	private NegativeInertiaAccumulator turnNia = new NegativeInertiaAccumulator(2.0);	
	public void drive(double leftIn, double rightIn, boolean autoAim) {

		double throttle = (leftIn + rightIn) / 2.0;
		double turning = (leftIn - rightIn) / 2.0;
		
		if(!autoAim) {
			turning += turnNia.update(turning);
		} else {
			throttle = 0.0;
			double kP = Constants.SuperSystem.AUTO_AIM_KP;
			turning = kP * ((Constants.SuperSystem.AUTO_AIM_TARGET) - pixycam.getVoltage());
		}

		drive.tankDrive(throttle + turning, throttle - turning);
		
	}
	
	public void operate(double intakeInput, 
						boolean intakePos, 
						double stagerInput, 
						double shooterInput) {

		double iOut = 0.0;		// intake motor out
		boolean ipOut = false;	// intake solenoid out
		double stOut = 0.0;		// st	ager motor out
		double sOut = 0.0;		// shooter motor out
		
		if(intakeInput > 0.20) {
			iOut = -1.0;
		} else if(intakeInput < -0.20) {
			iOut = 1.0;
		} else {
			iOut = 0.0;
		}
		
		ipOut = intakePos || intakeInput > 0.9;
		
		if(stagerInput > 0.20) {
			stOut = -1.0;
		} else if(stagerInput < -0.20) {
			stOut = 1.0;
		} else {
			stOut = 0.0;
		}
		
		
		double ksp = Constants.SuperSystem.AUTO_SPEED_KP;
		
		double ksf = shooterInput * Constants.SuperSystem.AUTO_SPEED_KF;
		double target = (shooterInput);
		
		sOut = ksf + ( target - shoot.getSpeed()) * ksp;
		
		// Do output stuff.
		
		intake.runIntake(iOut);
		intake.setIntake(ipOut);
		stage.runStager(stOut);
		shoot.runShooter(sOut);
		
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("ShooterCurrent", shoot.getCurrent());
		//
		// SmartDashboard.putBoolean("extBall", intake.getBallEntered());
		// SmartDashboard.putBoolean("intBall", intake.getBallStaged());
		SmartDashboard.putNumber("DriveTotalCurrent", drive.getTotalCurrent());
	}

	public void poke() {
		intake.update();
		//shoot.update();
	}

}