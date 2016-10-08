package com.gryffingear.y2016.Auton.Commands;

import com.gryffingear.y2016.systems.SuperSystem;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {

	private double speed = 0.0;
	private double timeout = 0.0;

	public ShooterCommand(double speed, double timeout) {

		this.speed = speed;
		this.timeout = timeout;
		this.setTimeout(timeout);
	}

	protected void initialize() {
		SuperSystem.getInstance().shoot.runShooter(0);
	}

	protected boolean isFinished() {

		return this.isTimedOut();
	}

	protected void execute() {

		SuperSystem.getInstance().shoot.runShooter(speed);
	}

	protected void end() {

		SuperSystem.getInstance().shoot.runShooter(0.0);

	}

	protected void interrupted() {

		SuperSystem.getInstance().shoot.runShooter(0.0);

	}
}
