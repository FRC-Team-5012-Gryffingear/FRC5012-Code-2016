package com.gryffingear.y2016.Auton.Commands;

import edu.wpi.first.wpilibj.command.Command;

import com.gryffingear.y2016.systems.SuperSystem;

public class DriveStraightCommand extends Command {

	private double speed = 0.0;
	private double angle = 0.0;
	private double timeout = 0.0;
	public DriveStraightCommand(double speed, double timeout) {
		
		this.speed = -speed;
		this.angle = 0.0;
		this.timeout = timeout;
		this.setTimeout(timeout);
	}
	
	protected void initialize() {
		
		SuperSystem.getInstance().drive.resetGyro();
		
	}
	
	protected boolean isFinished() {
		
		return this.isTimedOut();
	}
	
	protected void execute() {
	
		double p  = 0.025;
		double error = SuperSystem.getInstance().drive.getYaw() - this.angle;
		SuperSystem.getInstance().drive.tankDrive(speed + (p * error), speed - (p * error));
	}
	
	protected void end(){
		
		SuperSystem.getInstance().drive.tankDrive(0.0, 0.0);
	}
	
	protected void interrupted() {
		
		SuperSystem.getInstance().drive.tankDrive(0.0, 0.0);
		
	}
	
}
