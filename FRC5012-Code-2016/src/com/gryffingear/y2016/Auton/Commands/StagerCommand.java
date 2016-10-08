package com.gryffingear.y2016.Auton.Commands;

import com.gryffingear.y2016.systems.SuperSystem;

import edu.wpi.first.wpilibj.command.Command;

public class StagerCommand extends Command {

	private double intakeSpeed = 0.0;
	private boolean intakeState = false;
	private double stagerSpeed = 0.0;
	private double timeout = 0.0;

	public StagerCommand(double intakeSpeed, boolean intakeState, double stagerSpeed, double timeout) {

		this.intakeSpeed = intakeSpeed;
		this.intakeState = intakeState;
		this.stagerSpeed = stagerSpeed;
		this.timeout = timeout;
		this.setTimeout(timeout);
	}

	protected void initialize() {
		SuperSystem.getInstance().intake.runIntake(0.0);
		SuperSystem.getInstance().intake.setIntake(false);
		SuperSystem.getInstance().stage.runStager(0.0);

	}

	protected boolean isFinished() {

		return this.isTimedOut();
	}

	protected void execute() {
		SuperSystem.getInstance().intake.runIntake(intakeSpeed);
		SuperSystem.getInstance().intake.setIntake(intakeState);
		SuperSystem.getInstance().stage.runStager(stagerSpeed);

	}

	protected void end() {
		SuperSystem.getInstance().intake.runIntake(0.0);
		SuperSystem.getInstance().intake.setIntake(false);
		SuperSystem.getInstance().stage.runStager(0.0);
	}

	protected void interrupted() {
		SuperSystem.getInstance().intake.runIntake(0.0);
		SuperSystem.getInstance().intake.setIntake(false);
		SuperSystem.getInstance().stage.runStager(0.0);
	}

}
