package com.gryffingear.y2016.Autonomous;

import com.gryffingear.y2016.Auton.Commands.ArcadeDriveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OntoBatterFromSpyAuton extends CommandGroup {

	public OntoBatterFromSpyAuton() {
		this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 11));
		this.addSequential(new ArcadeDriveCommand(-0.5, 0.0, .5));
		this.addSequential(new ArcadeDriveCommand(0.0, -0.5, .275));
		this.addSequential(new ArcadeDriveCommand(-0.5, 0.0, 1.8));
	}

}
