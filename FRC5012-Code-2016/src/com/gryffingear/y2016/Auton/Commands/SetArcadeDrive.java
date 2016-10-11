package com.gryffingear.y2016.Auton.Commands;

import com.gryffingear.y2016.systems.SuperSystem;

import edu.wpi.first.wpilibj.command.Command;

public class SetArcadeDrive extends Command {

	private double speed = 0.0;


	public SetArcadeDrive(double speed) {
		this.speed = -speed;
		
		
	}

	protected void initialize() {
		SuperSystem.getInstance().drive.tankDrive(speed, speed );
	}

	protected boolean isFinished() {
		return true;
	}

	protected void execute() {

	}

	protected void end() {
		
	}

	protected void interrupted() {
		SuperSystem.getInstance().drive.tankDrive(0.0, 0.0);
	}
}