package com.gryffingear.y2016.Autonomous;

import com.gryffingear.y2016.Auton.Commands.ArcadeDriveCommand;
import com.gryffingear.y2016.Auton.Commands.IntakeShooterCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DefaultAuton extends CommandGroup {
	public DefaultAuton() {
		this.addSequential(new IntakeShooterCommand(true, 0.0, 0.0));
		this.addSequential(new ArcadeDriveCommand(1.0, 0.0, 0.25));
		this.addSequential(new ArcadeDriveCommand(-1.0, 0.0, 0.125));
		this.addSequential(new ArcadeDriveCommand(0.50, 0.0, 0.4));

	}

	public String toString() {
		return "DefaultAuton";
	}
}
