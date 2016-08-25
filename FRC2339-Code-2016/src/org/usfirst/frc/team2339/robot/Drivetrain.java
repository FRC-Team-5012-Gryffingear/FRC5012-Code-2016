package org.usfirst.frc.team2339.robot;

import edu.wpi.first.wpilibj.Talon;

public class Drivetrain {

	private Talon leftA = null;
	private Talon leftB = null;
	private Talon rightA = null;
	private Talon rightB = null;
	
	public Drivetrain (int la, int lb, int ra, int rb){
		leftA = new Talon(la);
		leftB = new Talon(lb);
		rightA = new Talon(ra);
		rightB = new Talon(rb);
	}
	
	public void tankDrive(double leftv, double rightv){
		leftA.set(leftv);
		leftB.set(leftv);
		
		rightA.set(-rightv);
		rightB.set(-rightv);
	}
	
	public void tankDrive(double[] input){
		tankDrive(input[0], input[1]);
	}
	
}
