package com.gryffingear.y2016.Auton.Commands;

import com.gryffingear.y2016.systems.SuperSystem;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeShooterCommand extends Command {

private double speedI = 0.0 , speedS;
boolean state = false;

boolean toggleIntakePos = false, wantIntake = false, wantLowGoal = false, wantHighGoal = false;


public IntakeShooterCommand (boolean toggleIntakePos, boolean wantIntake, boolean wantLowGoal, boolean wantHighGoal){
	this.toggleIntakePos = toggleIntakePos;
	this.wantIntake = wantIntake;
	this.wantLowGoal = wantLowGoal;
	this.wantHighGoal = wantHighGoal;
}

protected void initialize() {
	SuperSystem.getInstance().magicshot(toggleIntakePos, wantIntake, wantLowGoal, wantHighGoal);
  }
protected boolean isFinished() {

    return true;
  }

  protected void execute() {

  }

  protected void end() {

  }

  protected void interrupted() {

  }














}
