package com.gryffingear.y2016.systems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake {

	private CANTalon intakeMotor = null;
	private Solenoid intakeSolenoid = null;
	private AnalogInput outerSensor = null;
	private AnalogInput innerSensor = null;

	public Intake(int im, int is, int oss, int iss) {

		intakeMotor = configureTalon(new CANTalon(im));
		intakeSolenoid = new Solenoid(is);
		outerSensor = new AnalogInput(oss);
		innerSensor = new AnalogInput(iss);
	}

	private CANTalon configureTalon(CANTalon in) {

		in.clearStickyFaults();
		in.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		in.setVoltageRampRate(96.0);
		in.enableControl();
		System.out.println("[CANTalon]" + in.getDescription() + " Initialized at device ID: " + in.getDeviceID());
		return in;
	}

	public void setIntake(boolean state) {

		intakeSolenoid.set(state);
	}

	public void runIntake(double intakev) {

		intakeMotor.set(intakev);
	}

	public void runIntake(double[] input) {

		runIntake(input[0]);
	}

	public double getOuter() {
		return outerSensor.getVoltage();
	}

	public double getInner() {
		return innerSensor.getVoltage();
	}
}
