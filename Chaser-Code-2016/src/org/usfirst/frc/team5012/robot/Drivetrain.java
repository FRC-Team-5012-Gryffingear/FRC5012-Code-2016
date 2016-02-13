package org.usfirst.frc.team5012.robot;

import edu.wpi.first.wpilibj.TalonSRX;;
public class Drivetrain {

	private TalonSRX lefta = null;
	private TalonSRX leftb = null;
	private TalonSRX righta = null;
	private TalonSRX rightb = null;




public Drivetrain (int la, int lb, int ra, int rb){
	lefta = new TalonSRX(la);
	leftb = new TalonSRX(lb);
	righta = new TalonSRX(ra);
	rightb = new TalonSRX(rb);



}

public void tankDrive(double leftv, double rightv) {
  lefta.set(-leftv);
  leftb.set(-leftv);

  righta.set(rightv);
  rightb.set(rightv);
}

public void tankDrive(double[] input) {

  tankDrive(input[0], input[1]);
}
}



