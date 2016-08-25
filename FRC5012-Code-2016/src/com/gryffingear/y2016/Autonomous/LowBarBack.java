package com.gryffingear.y2016.Autonomous;

import com.gryffingear.y2016.Auton.Commands.ArcadeDriveCommand;
import com.gryffingear.y2016.Auton.Commands.IntakeShooterCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarBack extends CommandGroup {
	public LowBarBack() {
		this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 2.0));
		//this.addSequential(new IntakeShooterCommand(true, 0.0, 0.0));
		this.addSequential(new ArcadeDriveCommand(0.20, 0.9, 20.0));
		/*this.addSequential(new ArcadeDriveCommand(.5, 0.0, 5.0));
		this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 1.0));
		this.addSequential(new IntakeShooterCommand(false, 0.0, 0.0));
		this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 1.0));
		this.addSequential(new IntakeShooterCommand(true, 0.0, 0.0));
		this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 0.5));
		this.addSequential(new ArcadeDriveCommand(-.5, 0.0, 5.0));
		this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 2.0));
		*/
	}

	public String toString() {
		return "LowBarBack";
	}
}