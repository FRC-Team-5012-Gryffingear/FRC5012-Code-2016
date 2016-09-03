package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;
import com.gryffingear.y2016.utilities.Debouncer;
import com.gryffingear.y2016.utilities.MovingAverage;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {

	private CANTalon shooterMotorA = null;
	private CANTalon shooterMotorB = null;

	private Solenoid hood = null;
	
	private Counter encoder = null;

	public Shooter(int sma, int smb, int hoodSol, int encPort) {

		shooterMotorA = configureTalon(new CANTalon(sma));
		shooterMotorB = configureTalon(new CANTalon(smb));
		hood = new Solenoid(hoodSol);
		
		encoder = new Counter(encPort);
		
	}

	private CANTalon configureTalon(CANTalon in) {

		in.clearStickyFaults();
		in.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		in.setVoltageRampRate(Constants.Shooter.RAMP_RATE);
		in.enableControl();
		System.out.println("[CANTalon]" + in.getDescription() + " Initialized at device ID: " + in.getDeviceID());
		return in;
	}

	public void runShooter(double shooterv) {
		shooterMotorA.set(shooterv);
		shooterMotorB.set(
				-shooterv);
	}

	public double getCurrent() {
		double answer = shooterMotorA.getOutputCurrent() + shooterMotorB.getOutputCurrent();
		answer /= 2.0;

		return answer;
	}

	Debouncer currentFilter = new Debouncer(1.00);

	private boolean m_atSpeed = false;

	public boolean atSpeed() {
		return m_atSpeed;

	}

	int currEnc = 0, prevEnc = 0;
	long prevTime = 0, currTime = 0;
	MovingAverage speedFilter = new MovingAverage(8);
	
	public double speed = 0.0; 
	public void update() {
		m_atSpeed = (getCurrent() < Constants.Shooter.AT_SPEED_CURRENT_THRESHOLD);
		
		prevEnc = currEnc;
		prevTime = currTime;
		currTime = System.currentTimeMillis();
		currEnc = encoder.get();
		speed =  ((double)(currEnc - prevEnc) / (double)(currTime - prevTime));
		SmartDashboard.putNumber("shooter_speed", speedFilter.calculate(speed));
		
	}

	public void setHood(boolean state) {
		hood.set(state);
	}

}
