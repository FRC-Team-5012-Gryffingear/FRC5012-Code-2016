package com.gryffingear.y2016.Autonomous;
import com.gryffingear.y2016.Auton.Commands.ArcadeDriveCommand;
import com.gryffingear.y2016.Auton.Commands.IntakeShooterCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;



public class ShovelOfFries extends CommandGroup {
	public ShovelOfFries(){
		
		this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 2.0));
		this.addSequential(new ArcadeDriveCommand(0.5, 0.0, 3.0));
		this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 0.7));
		this.addSequential(new IntakeShooterCommand(true, 0.0, 0.0));
		this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 0.3));
		this.addSequential(new ArcadeDriveCommand(-0.3, 0.0, 0.7));
		this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 0.5));
		this.addSequential(new IntakeShooterCommand(false, 0.0, 0.0));
		this.addSequential(new ArcadeDriveCommand(1.0, 0.0, 3.0));
		this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 2.0));

		
	}
}
