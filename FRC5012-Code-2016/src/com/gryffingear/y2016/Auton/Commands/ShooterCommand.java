package com.gryffingear.y2016.Auton.Commands;

import com.gryffingear.y2016.systems.SuperSystem;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {

	private double speed = 0.0;
	

	public ShooterCommand(double speed) {

		this.speed = speed;
		
	}

	protected void initialize() {
		SuperSystem.getInstance().shoot.setPercentVBus(0);
	}

	protected boolean isFinished() {

		return true;
	}

	protected void execute() {

		SuperSystem.getInstance().shoot.setPercentVBus(speed);
	}

	protected void end() {



	}

	protected void interrupted() {

		SuperSystem.getInstance().shoot.setPercentVBus(0.0);

	}
}
