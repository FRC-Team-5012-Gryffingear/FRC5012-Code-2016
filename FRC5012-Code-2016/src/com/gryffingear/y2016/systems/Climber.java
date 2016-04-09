package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

public class Climber {

	private Solenoid climberSolenoid = null;
	private CANTalon climberMotor = null;

	private CANTalon configureTalon(CANTalon in) {

		in.clearStickyFaults();
		in.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		in.setVoltageRampRate(Constants.Intake.RAMP_RATE);
		in.enableControl();
		System.out.println("[CANTalon]" + in.getDescription() + " Initialized at device ID: " + in.getDeviceID());
		return in;
	}

	public Climber(int cs, int cm){
		climberSolenoid = new Solenoid(cs);
		climberMotor = new CANTalon(cm);
	}
	
	public void setClimber(boolean state) {

		climberSolenoid.set(state);
	}
	
	public void runClimber(double climberv) {

		climberMotor.set(climberv);
	}
}
