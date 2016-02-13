package org.usfirst.frc.team5012.robot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.CANTalon;;
public class Shooter {

	private Victor shooter = null;
	private CANTalon stager = null;
	

	
	public Shooter (int shooterMotor, int stagerMotor){
		shooter= new Victor (shooterMotor);
		stager = new CANTalon (stagerMotor);
	}
	
	public void shooter (double shooterv, double stagerv){
		shooter.set(shooterv);
		stager.set(stagerv);
	}

	public void shooter (double[] input) {

		  shooter(input[0], input[1]);
	
	
	
	
	
	
	
	
}	
}

