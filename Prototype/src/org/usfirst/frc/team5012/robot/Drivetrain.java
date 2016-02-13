package org.usfirst.frc.team5012.robot;

import edu.wpi.first.wpilibj.Talon;
public class Drivetrain {

	private Talon lefta = null;
	private Talon leftb = null;
	private Talon righta = null;
	private Talon rightb = null;
	
	

public Drivetrain (int la, int lb, int ra, int rb){
	              
	lefta = new Talon (la);
	leftb = new Talon (lb);
	righta = new Talon (ra);
	rightb = new Talon (rb); 
}

public void tankdrive (double leftv, double rightv ){
lefta.set(-leftv);	
leftb.set(-leftv);
righta.set(rightv);
rightb.set(rightv);
	
} 
public void tankdrive (double[] input){
 
	tankdrive(input[0], input [1]);

}
}