package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;
import com.gryffingear.y2016.config.Ports;
import com.gryffingear.y2016.utilities.NegativeInertiaAccumulator;
import com.gryffingear.y2016.utilities.PulseTriggerBoolean;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;

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

	private SuperSystem() {

		drive = new Drivetrain(Ports.Drivetrain.DRIVE_LEFT_A_PORT, Ports.Drivetrain.DRIVE_LEFT_B_PORT,
				Ports.Drivetrain.DRIVE_RIGHT_A_PORT, Ports.Drivetrain.DRIVE_RIGHT_B_PORT);

		led = new LedStrips(Ports.Leds.LED_STRIP_1_PORT, Ports.Leds.LED_STRIP_2_PORT, Ports.Leds.LED_STRIP_3_PORT);

		intake = new Intake(Ports.Intake.INTAKE_MOTOR, Ports.Intake.INTAKE_SOLENOID, Ports.Intake.STAGE_SENSOR);
		
		// Shoot? Yes, shoot.
		shoot = new Shooter(Ports.Shooter.SHOOTER_MOTOR_A, Ports.Shooter.SHOOTER_MOTOR_B, Ports.Shooter.HOOD_SOLENOID);

		climb = new Climber(Ports.Climber.CLIMBER_SOLENOID, Ports.Climber.CLIMBER_MOTOR);
		
		stage = new Stager (Ports.Stager.STAGER_MOTOR);
		
		pixycam = new AnalogInput(Ports.Pixycam.PIXYCAM_PORT);
		
		
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
			double kP = 2.0;
			turning = kP * ((3.3/2.0) - pixycam.getVoltage());
		}

		//turning = ((turning * Math.abs(turning)) + turning) / 2.0;

		drive.tankDrive(throttle + turning, throttle - turning);
		
		System.out.println("Pixycam Voltage: " + pixycam.getVoltage());
	}
	
	
	private double prevS = 0.0;
	private double sInertia = 0.0, sDecay = 0.5;
	public void operate(double intakeInput, boolean intakePos, double stagerInput, double shooterInput) {
//		bot.led.setB(operator.getRawButton(3));
//		bot.intake.runIntake(operator.getRawAxis(1) > 0.20 ? -1.0 : operator.getRawAxis(1) < -0.20 ? 1.0 : 0.0);
//		bot.intake.setIntake(operator.getRawButton(6) || operator.getRawAxis(1) > 0.9);
//		bot.stage.runStager(operator.getRawAxis(3) > 0.70 ? -1.0 : operator.getRawAxis(3) < -0.70 ? 1.0 : 0.0);		
//		bot.shoot.runShooter((operator.getRawButton(4) ? 1.0 : operator.getRawButton(8) ? 0.70 : 0.0));
//		
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
		
		
		
		// Shooter negative inertia on rise only, faster spinup times.
		
		if(shooterInput > prevS) {	// if shooter input is rising...
			sInertia = 4.0;
		}
		
		if(sInertia > sDecay) {
			sInertia -= sDecay;
		} else {
			sInertia = 0.0;
		}
		
		// Apply negative inertia
		sOut = shooterInput + sInertia;
		
		// Constrain sOut to +-1.0
		sOut = Math.max(-1.0, Math.min(1.0, sOut));
		
		
		// Do output stuff.
		
		intake.runIntake(iOut);
		intake.setIntake(ipOut);
		stage.runStager(stOut);
		shoot.runShooter(sOut);
		
		prevS = shooterInput;
	}
	
	boolean shooting = false;

	boolean atSpeed = false;

	boolean intakePos = false;

	boolean hoodPos = false;

	PulseTriggerBoolean intakeToggle = new PulseTriggerBoolean();


	// DO NOT USE THIS ANYMORE. -JPG
	public void magicshot(boolean toggleIntakePos, boolean wantIntake, boolean wantLowGoal, boolean wantHighGoal) {

		double intakeOut = 0.0;
		double shooterOut = 0.0;

		if (wantLowGoal) {
			intakeOut = Constants.Intake.INTAKE_OUT;
			shooting = false;
		} else if (wantHighGoal) {
			shooterOut = Constants.Shooter.SHOOTING_VOLTAGE;

			atSpeed = shoot.atSpeed();
			if (atSpeed) {
				shooting = true;
			}

			intakeOut = wantIntake ? Constants.Intake.INTAKE_IN : 0.0;
		} else {
			intakeOut = wantIntake ? Constants.Intake.INTAKE_IN : 0.0;

			if (intake.getBallStaged()) {
				if (intakeOut > 0.0)
					intakeOut = 0;
			}

			shooting = false;
		}
		shooting = false;

		intakeToggle.set(toggleIntakePos);

		if (intakeToggle.get()) {
			intakePos = !intakePos;
		}

		hoodPos = wantHighGoal;

		magicshotRaw(intakePos, shooterOut, intakeOut, hoodPos);

	}
	
	//public void block(double blockerSpeed){
	//	blocker.runBlocker(blockerSpeed);
	//}

	// DO NOT USE THIS ANYMORE. -JPG
	public void magicshotRaw(boolean intakeState, double shooterSpeed, double intakeSpeed, boolean hoodState) {
		intake.setIntake(intakeState);
		//shoot.runShooter(shooterSpeed);
		intake.runIntake(intakeSpeed);
		//led.setB(intake.getBallStaged());
		led.setA(atSpeed);
		shoot.setHood(hoodState);
	}
	
	

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("ShooterCurrent", shoot.getCurrent());
		SmartDashboard.putBoolean("AtSpeed", atSpeed);
		SmartDashboard.putBoolean("Shooting", shooting);
		//
		// SmartDashboard.putBoolean("extBall", intake.getBallEntered());
		// SmartDashboard.putBoolean("intBall", intake.getBallStaged());
		SmartDashboard.putNumber("DriveTotalCurrent", drive.getTotalCurrent());
	}

	public void poke() {
		intake.update();
		shoot.update();
	}

}