package com.gryffingear.y2016;

import com.gryffingear.y2016.config.Ports;
import com.gryffingear.y2016.systems.SuperSystem;
import com.gryffingear.y2016.Autonomous.DefaultAuton;
import com.gryffingear.y2016.Autonomous.testAuton;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {

	Joystick driverL = new Joystick(Ports.Controls.DRIVER_PORT_A);
	Joystick driverR = new Joystick(Ports.Controls.DRIVER_PORT_B);
	Joystick operator = new Joystick(Ports.Controls.OPERATOR_PORT);

	SuperSystem bot = SuperSystem.getInstance();
	
	SendableChooser autonChooser = new SendableChooser();
	Command autonomousCommand;

	public void robotInit() {
	autonChooser = new SendableChooser();
	autonChooser.addDefault("Default Auton", new DefaultAuton());
	autonChooser.addObject("Test Auton", new testAuton());
	SmartDashboard.putData("Autonomous mode chooser", autonChooser);
	}
	
	
	
	public void autonomousInit(){
		autonomousCommand = (Command) autonChooser.getSelected();
		autonomousCommand.start();
	}
	
	public void autonomousPeriodic(){
		Scheduler.getInstance().run();
	}
	
	public void teleopPeriodic() {
		
		bot.poke();

		bot.drive(driverL.getRawAxis(1), driverR.getRawAxis(1));

		bot.magicshot(operator.getRawButton(6), operator.getRawButton(2), operator.getRawButton(7),
				operator.getRawButton(8));
		
		bot.updateSmartDashboard();

	}
}
