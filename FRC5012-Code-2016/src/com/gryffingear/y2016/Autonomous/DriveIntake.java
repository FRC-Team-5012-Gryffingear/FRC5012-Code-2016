package com.gryffingear.y2016.Autonomous;

import com.gryffingear.y2016.Auton.Commands.ArcadeDriveCommand;
import com.gryffingear.y2016.Auton.Commands.IntakeShooterCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveIntake extends CommandGroup {
	public DriveIntake() {

		this.addSequential(new IntakeShooterCommand(true, 0.0, 0.0));
		this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 2.0));
		this.addSequential(new ArcadeDriveCommand(0.5, 0.0, 5.0));

	}

	public String toString() {
		return "DriveIntake";
	}
}