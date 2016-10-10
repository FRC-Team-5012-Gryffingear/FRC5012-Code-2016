package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;
import com.gryffingear.y2016.config.Ports;
import com.gryffingear.y2016.utilities.LedOutput;
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
	private Arm arm = null;
	private Winch winch = null;

	LedOutput hoodLed;
	LedOutput stagerLed;
	LedOutput flashlight;
	
	private long matchTime = 0;

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
							Ports.Shooter.SHOOTER_ENCODER_PORT);
		
		stage = new Stager (Ports.Stager.STAGER_MOTOR);
		
		pixycam = new AnalogInput(Ports.Pixycam.PIXYCAM_PORT);
		
		arm = new Arm (Ports.Arm.ARM_SOLENOID);
		
		winch = new Winch (Ports.Winch.WINCH_MOTOR_A,
						   Ports.Winch.WINCH_MOTOR_B,
						   Ports.Winch.WINCH_SOLENOID,
						   Ports.Winch.CLIMBER_MOTOR,
						   Ports.Winch.CLIMBER_SENSOR);
		
		hoodLed = new LedOutput(Ports.Shooter.SHOOTER_INDICATOR_LIGHT, Ports.Pneumatics.PCM_CAN_ID);
		stagerLed = new LedOutput(Ports.Stager.STAGER_INDICATOR_LIGHT, Ports.Pneumatics.PCM_CAN_ID);
		flashlight = new LedOutput(Ports.Shooter.FLASHLIGHT_PORT, Ports.Pneumatics.PCM_CAN_ID);
		
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
					  boolean winchInput ) {

		double throttle = (leftIn + rightIn) / 2.0;
		double turning = (leftIn - rightIn) / 2.0;
		
		boolean winchBrakeState = matchTime > 134750;	// Auto Brake at 250ms from end of match.
		double wOut = 0.0; //winch Out
		double cOut = 0.0; //climber Out
		
		if (winchClimberPositiveInput) {
			cOut = 1.0;
			
		
		}else if (winchClimberNegativeInput) {
			 cOut = -1.0;
		} else {
			cOut = 0.0;
		}
		
		if (winchInput) {
			wOut = 1.0;
		} else {
			wOut = 0.0;
		}
		

		if((winch.getScaled())){
			wOut = 0.0;
			winchBrakeState = true;
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
		winch.runWinch(-wOut);
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
		
		double shooterCurrent = shoot.getCurrent();
		
		flashlight.set(shooterCurrent > 3);
		
		
		if(shooterCurrent > 20) {	// Just started spinning up...
			hoodLed.blink(500);
		} else if(shooterCurrent > 15) { // Approaching target speed...
			hoodLed.blink(250);
		} else if(shooterCurrent > 3) {	// On target, ready to shoot.
			hoodLed.set(true);
		} else {						// Not trying to shoot, turn off.
			if(matchTime > 115000) {	// Blink LEDs based on match time
				hoodLed.blink(250);
			} else if(matchTime > 105000) {
				hoodLed.blink(500);
			} else {
				hoodLed.set(false);
			}
		}
		
		if(intake.getBallStaged()) {
			stagerLed.set(true);
		} else {
			if(Math.abs(stOut) > 0.01 || Math.abs(iOut) > 0.01) {
				stagerLed.blink(500);
			} else {
				stagerLed.set(false);
			}
		}
		
		
		
		sOut = shooterInput;
		
		// Do output stuff.
		
		intake.runIntake(iOut);
		intake.setIntake(ipOut);
		stage.runStager(stOut);
		shoot.setPercentVBus(sOut);
		// shoot.setVoltage(sOut * 12.0);
	}

	/**
	 * Refreshes networktables/smartdashboard with new telemetry data.
	 */
	public void updateSmartDashboard() {
		SmartDashboard.putNumber("ShooterCurrent", shoot.getCurrent());
		SmartDashboard.putNumber("ShooterVel", shoot.getSpeed());
		SmartDashboard.putNumber("ShooterVoltage", shoot.get());
		SmartDashboard.putNumber("ShooterError", shoot.getError());
		SmartDashboard.putNumber("Gyro", drive.getYaw());	
		SmartDashboard.putNumber("Pixycam", pixycam.getVoltage());
		SmartDashboard.putBoolean("atSpeed", shoot.atSpeed());
		SmartDashboard.putNumber("ShooterOut", shoot.get());
		SmartDashboard.putNumber("DriveTotalCurrent", drive.getTotalCurrent());
		
	}
	
	/**
	 * Tells the supersystem how long we've been in teleop
	 * @param millis
	 */
	public void setTeleopTime(long millis) {
		this.matchTime = millis;
	}

	/**
	 * "pokes" subsystems that need to be updated periodically.
	 */
	public void poke() {
		intake.update();
	}

}