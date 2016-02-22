package com.gryffingear.y2016.systems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.TalonSRX;

public class Shooter {

	private CANTalon shooterMotorA = null;
	private CANTalon shooterMotorB = null;


public Shooter(int sma, int smb){ 
	
	shooterMotorA = configureTalon(new CANTalon(sma));
	shooterMotorB = configureTalon(new CANTalon(smb));
}

private CANTalon configureTalon(CANTalon in) {

    in.clearStickyFaults();
    in.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    in.setVoltageRampRate(96.0);
    in.enableControl();
    System.out.println("[CANTalon]" + in.getDescription() + " Initialized at device ID: "
        + in.getDeviceID());
    return in;
  }


public void runShooter(double shooterv) {
	
	shooterMotorA.set(shooterv);
	shooterMotorB.set(-shooterv);
	
}


public void runShooter(double[] input) {

	runShooter(input[0]);
}





}
