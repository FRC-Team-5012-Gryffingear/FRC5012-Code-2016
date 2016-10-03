package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;
import com.gryffingear.y2016.config.Ports;
import com.gryffingear.y2016.config.Ports.Winch;
import com.gryffingear.y2016.utilities.Looper;
import com.gryffingear.y2016.utilities.NegativeInertiaAccumulator;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SuperSystem {

	private static SuperSystem instance = null;
	public Drivetrain drive = null;
	public Intake intake = null;
	public Shooter shoot = null;
	public Compressor compressor = null;
	public Stager stage = null;
	public AnalogInput pixycam = null;
	private Looper shooterSpeedLooper = null;
	private Arm arm = null;
	private Winch winch = null;


	private SuperSystem() {

		drive = new Drivetrain(	Ports.Drivetrain.DRIVE_LEFT_A_PORT, 
								Ports.Drivetrain.DRIVE_LEFT_B_PORT,
								Ports.Drivetrain.DRIVE_RIGHT_A_PORT, 
								Ports.Drivetrain.DRIVE_RIGHT_B_PORT, 0);

		intake = new Intake(Ports.Intake.INTAKE_MOTOR, 
							Ports.Intake.INTAKE_SOLENOID, 
							Ports.Intake.STAGE_SENSOR);
		
		// Shoot? Yes, shoot.
		shoot = new Shooter(Ports.Shooter.SHOOTER_MOTOR_A, 
							Ports.Shooter.SHOOTER_MOTOR_B, 
							Ports.Shooter.HOOD_SOLENOID, 
							Ports.Shooter.SHOOTER_ENCODER_PORT,
							Ports.Shooter.FLASHLIGHT_PORT);
		
		stage = new Stager (Ports.Stager.STAGER_MOTOR);
		
		pixycam = new AnalogInput(Ports.Pixycam.PIXYCAM_PORT);
		
		arm = new Arm (Ports.Arm.ARM_SOLENOID);
		
		winch = new Winch (Ports.Winch.WINCH_MOTOR_A,
						   Ports.Winch.WINCH_MOTOR_B,
						   Ports.Winch.WINCH_SOLNOID,
						   Ports.Winch.CLIMBER_MOTOR);
		
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
	public void drive(double leftIn, 
					  double rightIn, 
					  boolean autoAim, 
					  boolean armPos,  
					  boolean winchClimberPositiveInput,
					  boolean winchClimberNegativeInput,
					  boolean winchPositiveInput,
					  boolean winchBrakeState ) {

		double throttle = (leftIn + rightIn) / 2.0;
		double turning = (leftIn - rightIn) / 2.0;
		
		double wOut = 0.0; //winch Out
		double cOut = 0.0; //climber Out
		
		if (winchClimberPositiveInput) {
			cOut = 1.0;
		}else if (winchClimberNegativeInput) {
			 cOut = -1.0;
		} else {
			cOut = 0.0;
		}
		
		if (winchPositiveInput) {
			wOut = 1.0;
		} else {
			wOut = 0.0;
		}
		
		if (wOut > 0.0) {
			winchBrakeState = false;
		} else {
			
		}
		
		if(!autoAim) {
			turning += turnNia.update(turning);
		} else {
			throttle = 0.0;
			double kP = Constants.SuperSystem.AUTO_AIM_KP;
			turning = kP * ((Constants.SuperSystem.AUTO_AIM_TARGET) - pixycam.getVoltage());
		}

		drive.tankDrive(throttle + turning, throttle - turning);
		arm.set(armPos);
		winch.runClimber(cOut);
		winch.runWinch(wOut);
		winch.setBrake(winchBrakeState);
	}
	
	public void operate(double intakeInput, 
						boolean intakePos, 
						double stagerInput, 
						double shooterInput) {

		double iOut = 0.0;		// intake motor out
		boolean ipOut = false;	// intake solenoid out
		double stOut = 0.0;		// stager motor out
		double sOut = 0.0;		// shooter motor out
		boolean wpOut = false;
	
		
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
			if((intake.getBallStaged())) {
				if(Math.abs(shooterInput) <= 0.0) {
					stOut = 0.0;	
				}
			}
			
		} else if(stagerInput < -0.20) {
			
			stOut = 1.0;
			
		} else {

				stOut = 0.0;
				
		}
		
		
		if (Math.abs(shoot.getCurrent()) > 3) {
			shoot.setLight(true);
		}else {
			shoot.setLight(false);
		}
		
		sOut = shooterInput;
		
		// Do output stuff.
		
		intake.runIntake(iOut);
		intake.setIntake(ipOut);
		stage.runStager(stOut);
		shoot.runShooter(sOut);
		
	}

	/**
	 * Refreshes networktables/smartdashboard with new telemetry data.
	 */
	public void updateSmartDashboard() {
		SmartDashboard.putNumber("ShooterCurrent", shoot.getCurrent());
		SmartDashboard.putNumber("ShooterVel", shoot.getSpeed());
		SmartDashboard.putNumber("ShooterError", shoot.getError());
		SmartDashboard.putNumber("Gyro", drive.getYaw());
		SmartDashboard.putNumber("Pixycam", pixycam.getVoltage());
		SmartDashboard.putBoolean("atSpeed", shoot.atSpeed());
		SmartDashboard.putNumber("ShooterOut", shoot.get());
		SmartDashboard.putNumber("DriveTotalCurrent", drive.getTotalCurrent());
	}

	/**
	 * "pokes" subsystems that need to be updated periodically.
	 */
	public void poke() {
		intake.update();
	}

}