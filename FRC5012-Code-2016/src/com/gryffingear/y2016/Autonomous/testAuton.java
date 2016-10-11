package com.gryffingear.y2016.Autonomous;

import com.gryffingear.y2016.Auton.Commands.ArcadeDriveCommand;
import com.gryffingear.y2016.Auton.Commands.SetArcadeDrive;
import com.gryffingear.y2016.Auton.Commands.ShooterCommand;
import com.gryffingear.y2016.Auton.Commands.StagerCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class testAuton extends CommandGroup {

	public testAuton() {
		
		
		this.addSequential(new ShooterCommand(.8));
		this.addSequential(new SetArcadeDrive(-.4));
		this.addSequential(new StagerCommand(-1.0, false, -1.0, 4.0));
		this.addSequential(new ShooterCommand(0));
	}

	public String toString() {
		return "TestAuton";
	}
}
