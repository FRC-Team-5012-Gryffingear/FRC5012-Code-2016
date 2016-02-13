package org.usfirst.frc.team5012.robot;
import edu.wpi.first.wpilibj.Victor;
public class Intake {
	
private Victor intake = null;



public Intake (int intakeMotor){
	intake = new Victor (intakeMotor);
}

public void intake (double intakev){
	intake.set(intakev);

	
}

public void intake (double []input) {
	intake (input [0]);
	  
}
}

