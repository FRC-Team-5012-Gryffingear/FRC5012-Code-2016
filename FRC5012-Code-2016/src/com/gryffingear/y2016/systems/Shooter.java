package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;
import com.gryffingear.y2016.utilities.Debouncer;
import com.gryffingear.y2016.utilities.Loopable;
import com.gryffingear.y2016.utilities.MovingAverage;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Solenoid;

public class Shooter {

	private CANTalon shooterMotorA = null;
	private CANTalon shooterMotorB = null;

	private Solenoid hood = null;
	
	public Shooter(int sma, int smb, int hoodSol, int encPort) {

		shooterMotorA = configureTalon(new CANTalon(sma));
		shooterMotorB = configureTalon(new CANTalon(smb));

		shooterMotorB.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooterMotorB.setFeedbackDevice(CANTalon.FeedbackDevice.EncRising);

		shooterMotorB.setProfile(0);	// Might have been the secret sauce to get it working.
		shooterMotorB.configNominalOutputVoltage(0.0, 0.0);
		shooterMotorB.configPeakOutputVoltage(12.0, 0.0);	// swap these if shooter is reversed 
		shooterMotorB.setP(10);
		shooterMotorB.setI(0);
		shooterMotorB.setD(0);
		shooterMotorB.setF(40);
		shooterMotorB.enableControl();
		
		shooterMotorA.changeControlMode(CANTalon.TalonControlMode.Follower);
		shooterMotorA.set(shooterMotorB.getDeviceID());
		shooterMotorA.reverseOutput(true);
		
		hood = new Solenoid(hoodSol);
		
		
		//encoder = new Counter(encPort);
		
	}

	private CANTalon configureTalon(CANTalon in) {

		in.clearStickyFaults();
		in.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		in.setVoltageRampRate(Constants.Shooter.RAMP_RATE);
		in.enableControl();
		System.out.println("[CANTalon]" + in.getDescription() + 
				" Initialized at device ID: " + in.getDeviceID());
		return in;
	}

	public void runShooter(double shooterv) {
		shooterMotorA.set(shooterMotorB.getDeviceID());
		shooterMotorB.set(shooterv);
	}

	public double getCurrent() {
		double answer = shooterMotorA.getOutputCurrent() + 
						shooterMotorB.getOutputCurrent();
		answer /= 2.0;

		return answer;
	}

	Debouncer currentFilter = new Debouncer(1.00);

	private boolean m_atSpeed = false;

	public boolean atSpeed() {
		return m_atSpeed;

	}

//	int currEnc = 0, prevEnc = 0;
//	long prevTime = 0, currTime = 0;
//	MovingAverage speedFilter = new MovingAverage(8);
//	
//	double speed = 0.0; 
//	double filteredSpeed = 0.0;
//	
//	@Override
//	public synchronized void update() {
//		prevEnc = currEnc;
//		prevTime = currTime;
//		currTime = System.currentTimeMillis();
//		currEnc = encoder.get();
//		speed =  ((double)(currEnc - prevEnc) / (double)(currTime - prevTime));
//		filteredSpeed = speedFilter.calculate(speed);
//	}
	
	public double getSpeed() {
		//return filteredSpeed;
		
		return shooterMotorB.getEncVelocity();
	}

	public void setHood(boolean state) {
		hood.set(state);
	}

}
