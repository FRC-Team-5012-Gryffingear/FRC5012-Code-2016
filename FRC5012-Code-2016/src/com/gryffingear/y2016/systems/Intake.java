package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;
import com.gryffingear.y2016.utilities.Debouncer;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake {

	private CANTalon intakeMotor = null;
	private Solenoid intakeSolenoid = null;
	private AnalogInput StageSensor = null;
	

	public Intake(int im, int is, int ss) {

		intakeMotor = configureTalon(new CANTalon(im));
		intakeSolenoid = new Solenoid(is);
		StageSensor = new AnalogInput(ss);
		
	}

	private CANTalon configureTalon(CANTalon in) {

		in.clearStickyFaults();
		in.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		in.setVoltageRampRate(Constants.Intake.RAMP_RATE);
		in.enableControl();
		System.out.println("[CANTalon]" + in.getDescription() + " Initialized at device ID: " + in.getDeviceID());
		return in;
	}

	public void setIntake(boolean state) {

		intakeSolenoid.set(state);
	}

	public void runIntake(double intakev) {

		intakeMotor.set(-intakev);
	}

	public double getBump() {
		return StageSensor.getVoltage();
	}

	

	
	private Debouncer stagedFilter = new Debouncer(0.075);
	
	
	private boolean m_ballStaged = false;
	
	

	public boolean getBallStaged() {
		return m_ballStaged;
	}
	
	public void update() {
		m_ballStaged = (getBump() < Constants.Intake.BALL_SENSOR_THRESHOLD);
		
	}
}
