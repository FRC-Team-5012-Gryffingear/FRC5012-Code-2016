package com.gryffingear.y2016;

import com.gryffingear.y2016.config.Ports;
import com.gryffingear.y2016.systems.SuperSystem;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	Joystick joy1 = new Joystick(Ports.Controls.DRIVER_PORT_A);
	Joystick joy2 = new Joystick(Ports.Controls.DRIVER_PORT_B);
	Joystick operator = new Joystick(Ports.Controls.OPERATOR_PORT);

	SuperSystem bot = SuperSystem.getInstance();

	public void teleopPeriodic() {

		// Drive controls
		bot.drive.tankDrive(joy1.getRawAxis(1), joy2.getRawAxis(1));
		// LED controls
		bot.led.setA(bot.intake.getBallStaged());
		bot.led.setB(bot.intake.getBallEntered());
		bot.led.setC(operator.getRawButton(3));
		bot.led.setD(operator.getRawButton(4));
		// Intake controls
		bot.intake.runIntake(operator.getRawAxis(1));
		bot.intake.setIntake(operator.getRawButton(5));
		// Shooter controls
		bot.shoot.runShooter(operator.getRawAxis(3));

		// System.out.println("Outer: " + bot.intake.getOuter());
		// System.out.println("Inner: " +bot.intake.getInner());
	}
}
