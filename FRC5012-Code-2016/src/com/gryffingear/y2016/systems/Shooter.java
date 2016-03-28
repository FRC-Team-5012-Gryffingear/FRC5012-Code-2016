package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;
import com.gryffingear.y2016.utilities.Debouncer;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

public class Shooter {

	private CANTalon shooterMotorA = null;
	private CANTalon shooterMotorB = null;

	private Solenoid hood = null;

	public Shooter(int sma, int smb, int hoodSol) {

		shooterMotorA = configureTalon(new CANTalon(sma));
		shooterMotorB = configureTalon(new CANTalon(smb));
		hood = new Solenoid(hoodSol);
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
		shooterMotorA.set(-shooterv);
		shooterMotorB.set(shooterv);
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

	public void update() {
		m_atSpeed = (getCurrent() < Constants.Shooter.AT_SPEED_CURRENT_THRESHOLD);
	}

	public void setHood(boolean state) {
		hood.set(state);
	}

}
