package com.gryffingear.y2016.Auton.Commands;

import com.gryffingear.y2016.systems.SuperSystem;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeShooterCommand extends Command {

private double speedI = 0.0 , speedS;
boolean state = false;


public IntakeShooterCommand (double speedI, double speedS, boolean state){

this.state = state;
this.speedI = speedI;
this.speedS = speedS;

}

protected void initialize() {

    SuperSystem.getInstance().intake.runIntake(speedI);
    SuperSystem.getInstance().intake.setIntake(state);
    SuperSystem.getInstance().shoot.runShooter(speedS);
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
