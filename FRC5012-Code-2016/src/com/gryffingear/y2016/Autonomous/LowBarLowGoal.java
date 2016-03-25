package com.gryffingear.y2016.Autonomous;

import com.gryffingear.y2016.Auton.Commands.ArcadeDriveCommand;
import com.gryffingear.y2016.Auton.Commands.IntakeShooterCommand;
import com.gryffingear.y2016.config.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;


	public class LowBarLowGoal extends CommandGroup {
		public LowBarLowGoal(){
			this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 2.0));
			this.addSequential(new IntakeShooterCommand(true, 0.0, 0.0));
			this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 0.5));
			this.addSequential(new ArcadeDriveCommand(0.5, 0.0, 7.0));
			this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 1.0));
			this.addSequential(new ArcadeDriveCommand(0.0, 0.5, 1.5));
			this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 0.5));
			this.addSequential(new IntakeShooterCommand(false, 0.0, 0.0));
			this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 0.5));
			this.addSequential(new ArcadeDriveCommand(0.4, 0.0, 5.0));
			this.addSequential(new IntakeShooterCommand(false, Constants.Intake.INTAKE_OUT, 0.0));
			this.addSequential(new ArcadeDriveCommand(0.1, 0.0, 3.0));
			this.addSequential(new IntakeShooterCommand(false, 0.0, 0.0));
			this.addSequential(new ArcadeDriveCommand(0.0, 0.0, 0.5));
}
		}
