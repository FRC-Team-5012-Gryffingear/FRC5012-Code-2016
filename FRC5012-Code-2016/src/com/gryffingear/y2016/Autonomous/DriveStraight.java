package com.gryffingear.y2016.Autonomous;

import com.gryffingear.y2016.Auton.Commands.ArcadeDriveCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveStraight extends CommandGroup {
	public DriveStraight() {
		this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 2.0));
		this.addSequential(new ArcadeDriveCommand(0.6, 0.0, 3.0));
	}

	public String toString() {
		return "DriveStraight";
	}
}