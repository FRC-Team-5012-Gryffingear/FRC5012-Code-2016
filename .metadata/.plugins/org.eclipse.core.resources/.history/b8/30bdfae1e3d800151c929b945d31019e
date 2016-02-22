package com.gryffingear.y2016.systems;

import edu.wpi.first.wpilibj.TalonSRX;

public class Shooter {

	private TalonSRX shooterMotorA = null;
	private TalonSRX shooterMotorB = null;


public Shooter(int sma, int smb){ 
	
	shooterMotorA = new TalonSRX(sma);
	shooterMotorB = new TalonSRX(smb);
}

public void runShooter(double shooterv) {
	
	shooterMotorA.set(shooterv);
	shooterMotorB.set(-shooterv);
	
}


public void runShooter(double[] input) {

	runShooter(input[0]);
}





}
