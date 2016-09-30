package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CANTalon;

public class Winch {
	
	private CANTalon winchMotorA = null;
	private CANTalon winchMotorB = null;
	
	private CANTalon climberMotor = null;
	
	private Solenoid winchSolenoid = null;
	
	public Winch(int wma, int wmb, int ws, int cm) {
		
		winchMotorA = configureTalon(new CANTalon (wma));
		winchMotorB = configureTalon(new CANTalon (wmb));
		
		climberMotor = configureTalon(new CANTalon (cm));
		
		winchSolenoid = new Solenoid (ws);
		
	}
	
	

	private CANTalon configureTalon(CANTalon in) {

		in.clearStickyFaults();
		in.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		in.setVoltageRampRate(Constants.Shooter.RAMP_RATE);
		in.enableBrakeMode(true);
		in.enableControl();
		System.out.println("[CANTalon]" + in.getDescription() + 
				" Initialized at device ID: " + in.getDeviceID());
		return in;
	}
	
	public void runWinch(double winchv) {
		
		winchMotorA.set(-winchv);
		
		winchMotorB.set(winchv);
		
	}
	
	public void runClimber(double climberv) {
		
		climberMotor.set(climberv);
	}
	
	public void setBrake(boolean state) {
		
		winchSolenoid.set(state);
		
	}
	
}
