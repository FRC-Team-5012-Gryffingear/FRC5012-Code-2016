package com.gryffingear.y2016.Auton.Commands;

import com.gryffingear.y2016.systems.SuperSystem;

import edu.wpi.first.wpilibj.command.Command;

public class DriveToAngleCommand extends Command {

	private double speed = 0.0;
	private double angle = 0.0;
	private double timeout = 0.0;

	public DriveToAngleCommand(double speed, double angle, double timeout) {

		this.speed = speed;
		this.angle = angle;
		this.timeout = timeout;
		this.setTimeout(timeout);
	}

	public void initialize() {
		SuperSystem.getInstance().drive.resetGyro();
	}

	public boolean isFinished() {

		return this.isTimedOut();
	}

	public void execute() {

		double p = 0.065;
		double error = SuperSystem.getInstance().drive.getYaw() - this.angle;
		SuperSystem.getInstance().drive.tankDrive(p * error * speed, -p * error * speed);

	}

	public void interrupted() {

		SuperSystem.getInstance().drive.tankDrive(0.0, 0.0);
	}

	public void end() {
		SuperSystem.getInstance().drive.tankDrive(0.0, 0.0);
	}

}
