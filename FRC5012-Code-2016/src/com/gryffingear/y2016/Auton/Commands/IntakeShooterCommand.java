package com.gryffingear.y2016.Auton.Commands;

import com.gryffingear.y2016.systems.SuperSystem;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeShooterCommand extends Command {

	boolean state = false;
	double intakeSpeed = 0.0;
	double shooterSpeed = 0.0;

	public IntakeShooterCommand(boolean state, double intakeSpeed, double shooterSpeed) {
		this.state = state;
		this.intakeSpeed = intakeSpeed;
		this.shooterSpeed = shooterSpeed;
	}

	protected void initialize() {
		
	}

	protected boolean isFinished() {

		return true;
	}

	protected void execute() {

	}

	protected void end() {

	}

	protected void interrupted() {

	}

}
