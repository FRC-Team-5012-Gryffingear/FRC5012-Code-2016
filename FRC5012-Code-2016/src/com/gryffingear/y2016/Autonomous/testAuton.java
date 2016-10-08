package com.gryffingear.y2016.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.gryffingear.y2016.Auton.Commands.DriveToAngleCommand;

public class testAuton extends CommandGroup {

	public testAuton() {
		
		this.addSequential(new DriveToAngleCommand(0.5, 90, 1.0));
		this.addSequential(new DriveToAngleCommand(0.5, 90, 1.0));
		this.addSequential(new DriveToAngleCommand(0.5, 90, 1.0));
		this.addSequential(new DriveToAngleCommand(0.5, 90, 1.0));
	}

	public String toString() {
		return "TestAuton";
	}
}
