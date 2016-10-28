package com.gryffingear.y2016.Autonomous;

import com.gryffingear.y2016.Auton.Commands.DriveStraightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class testAuton extends CommandGroup {

	public testAuton() {
		this.addSequential(new DriveStraightCommand(0.75, 2.0));
		this.addSequential(new DriveStraightCommand(0.5, 1.0));
		
	}

	public String toString() {
		return "TestAuton";
	}
}
