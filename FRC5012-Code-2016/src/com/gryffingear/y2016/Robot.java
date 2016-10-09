package com.gryffingear.y2016;

import com.gryffingear.y2016.Autonomous.OntoBatterFromSpyAuton;
import com.gryffingear.y2016.Autonomous.testAuton;
import com.gryffingear.y2016.Autonomous.DriveStraight;
import com.gryffingear.y2016.Autonomous.testAuton;
import com.gryffingear.y2016.config.Ports;
import com.gryffingear.y2016.systems.SuperSystem;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
     
public class Robot extends IterativeRobot {

	Joystick driverL = new Joystick(Ports.Controls.DRIVER_PORT_A);
	Joystick driverR = new Joystick(Ports.Controls.DRIVER_PORT_B);
	Joystick operator = new Joystick(Ports.Controls.OPERATOR_PORT);

	SuperSystem bot = SuperSystem.getInstance();

	SendableChooser autonChooser = new SendableChooser();
	private CommandGroup currAuton = null;

	public void robotInit() {
		autonChooser = new SendableChooser();
		// autonChooser.addDefault("Default Auton", new DefaultAuton());
		autonChooser.addDefault("Drive Straight", new DriveStraight());
		

		SmartDashboard.putData("Autonomous mode chooser", autonChooser);

	}

	public void disabledInit() {
		if (currAuton != null) {
			System.out.println("[STATUS] Auton was running at this time. Cancelling...");
			currAuton.cancel();
			currAuton = null;
		}
	}

	public void disabledPeriodic() {

		currAuton = new testAuton();
		SmartDashboard.putString("Currently Selected Auton", currAuton.toString());
	}

	public void autonomousInit() {

		Scheduler.getInstance().add(currAuton);
		Scheduler.getInstance().enable();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {

		if (currAuton != null) {
			System.out.println("[STATUS] Auton was running at this time. Cancelling...");
			currAuton.cancel();
			currAuton = null;
		}

		Scheduler.getInstance().disable();
	}

	public void teleopPeriodic() {
		bot.poke();

		bot.drive(	driverL.getRawAxis(1),    //drivetrain left
					driverR.getRawAxis(1),    //drivetrain right
					driverR.getRawButton(1),  //autoaim
					driverL.getRawButton(1),  //cheval de frise arms
					driverL.getRawButton(3),  //climber out
					driverL.getRawButton(2),  //climber in
					driverL.getRawButton(4));  //winch in 
		
		bot.operate(operator.getRawAxis(1), //intake input
					operator.getRawButton(6), //intake position
					operator.getRawAxis(3), //stager input
					(operator.getRawButton(7) && operator.getRawButton(8)) ? 1.0 : //shooter full speed
					operator.getRawButton(8) ? 0.7500 : //shooter medium speed
					operator.getRawButton(7) ? 0.3000 : 0.0); 
					
		
		bot.updateSmartDashboard();

	}
	

}
