/*package org.usfirst.frc.team5012.robot.Auton.Commands;
 
import org.usfirst.frc.team5012.robot.SuperSystem;
import org.usfirst.frc.team5012.robot.Intake;

public class IntakeCommand extends Commands	{
 private double speed = 0.0;
 

		  private double timeout = 0.0; {
			  
		  }

		  public IntakeCommand(double speed, double timeout) {

		    this.speed = -speed;
		   this.timeout = timeout;
	
		  }

		  protected void initialize() {

		  
		  }

		  protected boolean isFinished() {

		    return this.isTimedOut();
		  }

		  protected void execute() {

		  }

		  protected void end() {

		    SuperSystem.getInstance().drive.Intake(0.0, 0.0);
		  }

		  protected void interrupted() {

		    SuperSystem.getInstance().drive.Intake(0.0, 0.0);
		  }
}
*/
