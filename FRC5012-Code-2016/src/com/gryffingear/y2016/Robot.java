package com.gryffingear.y2016;

import com.gryffingear.y2016.Autonomous.DefaultAuton;
import com.gryffingear.y2016.Autonomous.DriveIntake;
import com.gryffingear.y2016.Autonomous.DriveStraight;
import com.gryffingear.y2016.Autonomous.testAuton;
import com.gryffingear.y2016.config.Ports;
import com.gryffingear.y2016.systems.SuperSystem;

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
      //autonChooser.addDefault("Default Auton", new DefaultAuton());
		autonChooser.addDefault("Do Nothing", new testAuton());
		autonChooser.addObject("Drive Straight", new DriveStraight());
		autonChooser.addObject("Drive + Intake", new DriveIntake());
		SmartDashboard.putData("Autonomous mode chooser", autonChooser);
		
	}
	
	
	
	public void autonomousInit(){
		
		if(currAuton != null){
			System.out.println("[STATUS] Auton was running at this time. Cancelling...");
			currAuton.cancel();
			currAuton = null;
		}
		
		
		currAuton = (CommandGroup) autonChooser.getSelected();
		Scheduler.getInstance().add(currAuton);
		Scheduler.getInstance().enable();
	}
	
	public void autonomousPeriodic(){
		Scheduler.getInstance().run();
	}
	
	public void teleopInit(){
	
		if(currAuton != null){
			System.out.println("[STATUS] Auton was running at this time. Cancelling...");
			currAuton.cancel();
			currAuton = null;
		}
		
		Scheduler.getInstance().disable();
	}
	
	public void teleopPeriodic() {		
		bot.poke();

		bot.drive(driverL.getRawAxis(1), driverR.getRawAxis(1));

		bot.magicshot(operator.getRawButton(6), operator.getRawButton(2), operator.getRawButton(7),
				operator.getRawButton(8));
		
		bot.updateSmartDashboard();

	}
	
	
}
