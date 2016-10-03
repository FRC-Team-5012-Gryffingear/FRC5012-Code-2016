package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;

public class Drivetrain {

	private CANTalon lefta = null;
	private CANTalon leftb = null;
	private CANTalon righta = null;
	private CANTalon rightb = null;
	private AnalogGyro gyro = null;

	public Drivetrain(int la, int lb, int ra, int rb, int gp) {
		lefta = configureTalon(new CANTalon(la));
		leftb = configureTalon(new CANTalon(lb));
		righta = configureTalon(new CANTalon(ra));
		rightb = configureTalon(new CANTalon(rb));
		
		gyro = new AnalogGyro(gp);
		gyro.initGyro();
		gyro.calibrate();
	}

	private CANTalon configureTalon(CANTalon in) {

		in.clearStickyFaults();
		in.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		in.setVoltageRampRate(Constants.Drivetrain.RAMP_RATE);
		in.enableControl();
		System.out.println("[CANTalon]" + in.getDescription() + 
				" Initialized at device ID: " + in.getDeviceID());
		return in;
	}

	public void tankDrive(double leftv, double rightv) {
		lefta.set(-leftv);
		leftb.set(-leftv);

		righta.set(rightv);
		rightb.set(rightv);
	}

	public void tankDrive(double[] input) {
		tankDrive(input[0], input[1]);
	}

	public double getTotalCurrent() {
		double answer = this.getLeftCurrent() + 
						this.getRightCurrent();

		return answer;
	}

	public double getLeftCurrent() {
		double answer = lefta.getOutputCurrent() + 
						leftb.getOutputCurrent();

		return answer;
	}

	public double getRightCurrent() {
		double answer = righta.getOutputCurrent() + 
						rightb.getOutputCurrent();

		return answer;
	}
	
	public double getYaw() {
		return gyro.getAngle();
	}
	
	public void resetGyro() {
		gyro.reset();
	}
}
